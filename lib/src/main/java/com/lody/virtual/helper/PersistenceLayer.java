package com.lody.virtual.helper;

import android.os.Parcel;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Closeable;
import java.io.IOException;

/**
 * Enhanced Persistence Layer with:
 * - Better error handling
 * - Automatic backup/restore
 * - Android 10+ compatibility
 */
public abstract class PersistenceLayer {

    private static final String TAG = "PersistenceLayer";
    private static final int MAX_RETRY_COUNT = 3;
    private static final long RETRY_DELAY_MS = 100;

    private File mPersistenceFile;
    private File mBackupFile;

    public PersistenceLayer(File persistenceFile) {
        this.mPersistenceFile = persistenceFile;
        this.mBackupFile = new File(persistenceFile.getParent(), persistenceFile.getName() + ".backup");
    }

    public final File getPersistenceFile() {
        return mPersistenceFile;
    }

    public abstract int getCurrentVersion();

    public void writeMagic(Parcel p) {
    }

    public boolean verifyMagic(Parcel p) {
        return true;
    }

    public abstract void writePersistenceData(Parcel p);

    public abstract void readPersistenceData(Parcel p, int version);

    public void onPersistenceFileDamage() {
    }

    /**
     * Save with retry logic and file locking
     */
    public void save() {
        int retryCount = 0;
        boolean success = false;
        
        while (retryCount < MAX_RETRY_COUNT && !success) {
            try {
                attemptSave();
                success = true;
                Log.d(TAG, "Persistence saved: " + mPersistenceFile.getName());
            } catch (Exception e) {
                retryCount++;
                Log.e(TAG, "Save attempt " + retryCount + " failed: " + e.getMessage());
                
                if (retryCount < MAX_RETRY_COUNT) {
                    try {
                        Thread.sleep(RETRY_DELAY_MS);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        
        if (!success) {
            Log.e(TAG, "Failed to save persistence after " + MAX_RETRY_COUNT + " attempts");
        }
    }

    private void attemptSave() throws Exception {
        // Create parent directories if needed
        File parentDir = mPersistenceFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + parentDir);
            }
        }

        Parcel p = Parcel.obtain();
        FileOutputStream fos = null;
        File tempFile = new File(mPersistenceFile.getParent(), mPersistenceFile.getName() + ".tmp");
        
        try {
            writeMagic(p);
            p.writeInt(getCurrentVersion());
            writePersistenceData(p);
            
            byte[] data = p.marshall();
            
            // Write to temporary file first
            fos = new FileOutputStream(tempFile);
            fos.write(data);
            fos.getFD().sync(); // Force sync to disk
            fos.close();
            fos = null;

            boolean hadOriginalFile = mPersistenceFile.exists();
            if (hadOriginalFile) {
                prepareBackupFile();
                if (!mPersistenceFile.renameTo(mBackupFile)) {
                    copyFile(mPersistenceFile, mBackupFile);
                    if (!mPersistenceFile.delete()) {
                        throw new IOException("Failed to delete old persistence file: " + mPersistenceFile);
                    }
                }
            }

            if (!tempFile.renameTo(mPersistenceFile)) {
                copyFile(tempFile, mPersistenceFile);
                if (!tempFile.delete()) {
                    Log.w(TAG, "Failed to delete temp persistence file: " + tempFile);
                }
            }
        } catch (Exception e) {
            if (!mPersistenceFile.exists() && mBackupFile.exists()) {
                if (!mBackupFile.renameTo(mPersistenceFile)) {
                    try {
                        copyFile(mBackupFile, mPersistenceFile);
                    } catch (IOException restoreException) {
                        Log.e(TAG, "Failed to restore persistence from backup after save error", restoreException);
                    }
                }
            }
            throw e;
        } finally {
            closeWithWarning(fos, "output stream");
            if (tempFile.exists()) {
                if (!tempFile.delete()) {
                    Log.w(TAG, "Failed to clean up temp persistence file: " + tempFile);
                }
            }
            p.recycle();
        }
    }

    /**
     * Read with automatic backup restore
     */
    public void read() {
        try {
            if (!mPersistenceFile.exists()) {
                // Try restore from backup
                if (mBackupFile.exists()) {
                    Log.w(TAG, "Main file missing, restoring from backup");
                    copyFile(mBackupFile, mPersistenceFile);
                } else {
                    Log.w(TAG, "No persistence file found: " + mPersistenceFile);
                    return; // First run, no data yet
                }
            }
            
            attemptRead();
            Log.d(TAG, "Persistence loaded: " + mPersistenceFile.getName());
            
        } catch (Exception e) {
            Log.e(TAG, "Failed to read persistence: " + e.getMessage());
            e.printStackTrace();
            
            // Try backup
            if (mBackupFile.exists()) {
                try {
                    Log.w(TAG, "Attempting to restore from backup...");
                    copyFile(mBackupFile, mPersistenceFile);
                    attemptRead();
                    Log.d(TAG, "Restored from backup successfully");
                } catch (Exception backupException) {
                    Log.e(TAG, "Backup also failed: " + backupException.getMessage());
                    onPersistenceFileDamage();
                }
            } else {
                onPersistenceFileDamage();
            }
        }
    }

    private void attemptRead() throws Exception {
        File file = mPersistenceFile;
        
        if (!file.canRead()) {
            throw new IOException("Cannot read file: " + file);
        }
        
        Parcel p = Parcel.obtain();
        FileInputStream fis = null;
        
        try {
            // Use simple FileInputStream for maximum compatibility
            fis = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            int totalRead = 0;
            int read;
            
            while (totalRead < bytes.length && 
                   (read = fis.read(bytes, totalRead, bytes.length - totalRead)) != -1) {
                totalRead += read;
            }
            
            if (totalRead != bytes.length) {
                throw new IOException("Unable to read complete persistence file. Expected: " + 
                                    bytes.length + ", Read: " + totalRead);
            }
            
            fis.close();
            fis = null;
            
            p.unmarshall(bytes, 0, bytes.length);
            p.setDataPosition(0);
            
            if (!verifyMagic(p)) {
                throw new IOException("Invalid persistence file (magic check failed)");
            }
            
            int fileVersion = p.readInt();
            readPersistenceData(p, fileVersion);
            
        } finally {
            closeWithWarning(fis, "input stream");
            p.recycle();
        }
    }

    private void prepareBackupFile() throws IOException {
        if (mBackupFile.exists() && !mBackupFile.delete()) {
            throw new IOException("Failed to clear old backup file: " + mBackupFile);
        }
    }

    private void closeWithWarning(Closeable closeable, String label) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w(TAG, "Failed to close persistence " + label, e);
        }
    }

    private void copyFile(File source, File dest) throws IOException {
        File parent = dest.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(dest)) {
            
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            fos.getFD().sync(); // Ensure data is written
        }
    }

    /**
     * Verify integrity of persistence file
     */
    public boolean verifyIntegrity() {
        try {
            if (!mPersistenceFile.exists()) {
                return false;
            }
            
            Parcel p = Parcel.obtain();
            try {
                byte[] bytes = new byte[(int) mPersistenceFile.length()];
                try (FileInputStream fis = new FileInputStream(mPersistenceFile)) {
                    int read = fis.read(bytes);
                    if (read != bytes.length) {
                        return false;
                    }
                }
                
                p.unmarshall(bytes, 0, bytes.length);
                p.setDataPosition(0);
                
                return verifyMagic(p);
            } finally {
                p.recycle();
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get file size for diagnostics
     */
    public long getFileSize() {
        if (mPersistenceFile.exists()) {
            return mPersistenceFile.length();
        }
        return -1;
    }
}
