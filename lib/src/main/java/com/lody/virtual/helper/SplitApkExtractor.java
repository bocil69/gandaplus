package com.lody.virtual.helper;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Extracts split APK archives (.xapk, .apks, .apkm) and selects the correct
 * set of APKs for the current device (base + matching ABI split + matching DPI split).
 *
 * <p>Supported archive formats:
 * <ul>
 *   <li>{@code .apk}  — returned as-is (single item list)</li>
 *   <li>{@code .xapk} — ZIP container with APK entries</li>
 *   <li>{@code .apks} — ZIP container (APKs by APKSIG format)</li>
 *   <li>{@code .apkm} — ZIP container (APKMirror format)</li>
 * </ul>
 */
public final class SplitApkExtractor {

    private static final String TAG = "SplitApkExtractor";

    // ABI identifiers used in split APK file names
    private static final List<String> ABI_ARM64 = Arrays.asList(
            "arm64_v8a", "arm64-v8a", "arm64");
    private static final List<String> ABI_ARMEABI = Arrays.asList(
            "armeabi_v7a", "armeabi-v7a", "armeabi");
    private static final List<String> ABI_X86_64 = Arrays.asList(
            "x86_64");
    private static final List<String> ABI_X86 = Arrays.asList(
            "x86");

    // DPI bucket names used in split APK file names (highest to lowest)
    private static final String[] DPI_NAMES = {
            "xxxhdpi", "xxhdpi", "xhdpi", "hdpi", "mdpi", "ldpi", "tvdpi"
    };
    private static final int[] DPI_VALUES = {
            640, 480, 320, 240, 160, 120, 213
    };

    private SplitApkExtractor() {}

    /**
     * Extract (if needed) and select the correct APKs from {@code archiveFile}.
     *
     * @param archiveFile the .apk / .xapk / .apks / .apkm file
     * @param context     used for cache dir and display metrics
     * @return ordered list: [base.apk, abi_split?, dpi_split?]
     */
    public static List<File> extractSplitApks(File archiveFile, Context context) {
        List<File> result = new ArrayList<>();
        if (archiveFile == null || !archiveFile.exists()) {
            Log.e(TAG, "Archive file does not exist: " + archiveFile);
            return result;
        }

        String name = archiveFile.getName().toLowerCase(Locale.ROOT);
        if (name.endsWith(".apk")) {
            // Plain APK — no extraction needed
            result.add(archiveFile);
            return result;
        }

        // All other supported formats are ZIP containers
        if (!name.endsWith(".xapk") && !name.endsWith(".apks") && !name.endsWith(".apkm")) {
            Log.w(TAG, "Unknown archive extension, treating as plain APK: " + name);
            result.add(archiveFile);
            return result;
        }

        File extractDir = getExtractionDir(archiveFile, context);
        if (!extractDir.exists()) {
            extractDir.mkdirs();
        }

        try {
            List<File> extracted = unzipApks(archiveFile, extractDir);
            if (extracted.isEmpty()) {
                Log.e(TAG, "No APKs found inside archive: " + archiveFile);
                return result;
            }

            File baseApk = findBaseApk(extracted);
            if (baseApk == null) {
                Log.e(TAG, "Could not identify base APK inside: " + archiveFile);
                // Fallback: return the first APK found
                result.add(extracted.get(0));
                return result;
            }
            result.add(baseApk);

            for (File featureSplit : findFeatureSplits(extracted, baseApk)) {
                addIfAbsent(result, featureSplit);
            }

            // Select ABI split
            File abiSplit = findBestAbiSplit(extracted, baseApk);
            if (abiSplit != null) {
                addIfAbsent(result, abiSplit);
                Log.d(TAG, "Selected ABI split: " + abiSplit.getName());
            }

            // Select DPI split
            File dpiSplit = findBestDpiSplit(extracted, baseApk, context);
            if (dpiSplit != null) {
                addIfAbsent(result, dpiSplit);
                Log.d(TAG, "Selected DPI split: " + dpiSplit.getName());
            }

            for (File localeSplit : findLocaleSplits(extracted, baseApk, context)) {
                addIfAbsent(result, localeSplit);
                Log.d(TAG, "Selected locale split: " + localeSplit.getName());
            }

        } catch (Exception e) {
            Log.e(TAG, "Failed to extract archive: " + archiveFile, e);
        }

        return result;
    }

    // ─── Private helpers ──────────────────────────────────────────────────────

    /**
     * Unzip all .apk entries from the archive into {@code destDir}.
     * Returns the list of extracted APK files.
     */
    private static List<File> unzipApks(File archiveFile, File destDir) throws IOException {
        List<File> apks = new ArrayList<>();
        try (ZipFile zip = new ZipFile(archiveFile)) {
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (entry.isDirectory()) continue;

                // Only extract APK files at the top level or one directory deep
                String fileName = new File(entryName).getName();
                if (!fileName.toLowerCase(Locale.ROOT).endsWith(".apk")) continue;

                File outFile = new File(destDir, fileName);
                // Skip if already extracted and size matches
                if (outFile.exists() && outFile.length() == entry.getSize()) {
                    apks.add(outFile);
                    continue;
                }

                try (InputStream is = zip.getInputStream(entry);
                     FileOutputStream fos = new FileOutputStream(outFile)) {
                    byte[] buf = new byte[32768];
                    int read;
                    while ((read = is.read(buf)) != -1) {
                        fos.write(buf, 0, read);
                    }
                }
                apks.add(outFile);
                Log.d(TAG, "Extracted: " + outFile.getAbsolutePath());
            }
        }
        return apks;
    }

    /**
     * Identify the base APK among the extracted files.
     * Priority: file named exactly "base.apk", then any APK whose name does NOT
     * start with "split_" or "config.".
     */
    private static File findBaseApk(List<File> apks) {
        for (File f : apks) {
            if (f.getName().equalsIgnoreCase("base.apk")) return f;
        }
        for (File f : apks) {
            String n = f.getName().toLowerCase(Locale.ROOT);
            if (!n.startsWith("split_") && !n.startsWith("config.")) return f;
        }
        return null;
    }

    private static List<File> findFeatureSplits(List<File> apks, File baseApk) {
        List<File> featureSplits = new ArrayList<>();
        for (File apk : apks) {
            if (apk.equals(baseApk)) {
                continue;
            }
            String normalizedName = normalizeSplitName(apk.getName());
            if (!isConfigSplit(normalizedName)) {
                featureSplits.add(apk);
            }
        }
        return featureSplits;
    }

    /**
     * Select the best ABI split for this device.
     * Preference order: arm64-v8a > armeabi-v7a > x86_64 > x86.
     */
    private static File findBestAbiSplit(List<File> apks, File baseApk) {
        String[] preferredAbi = getPreferredAbiOrder();
        for (String abi : preferredAbi) {
            for (File f : apks) {
                if (f.equals(baseApk)) continue;
                String n = normalizeSplitName(f.getName());
                if (n.contains(abi)) {
                    return f;
                }
            }
        }
        return null;
    }

    /**
     * Returns ABI identifiers in preference order for this device.
     */
    private static String[] getPreferredAbiOrder() {
        String[] supportedAbis;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            supportedAbis = Build.SUPPORTED_ABIS;
        } else {
            //noinspection deprecation
            supportedAbis = new String[]{Build.CPU_ABI, Build.CPU_ABI2};
        }
        List<String> order = new ArrayList<>();
        for (String abi : supportedAbis) {
            String norm = abi.replace("-", "_").toLowerCase(Locale.ROOT);
            if (!order.contains(norm)) order.add(norm);
        }
        // Always include fallbacks
        if (!order.contains("arm64_v8a")) order.add("arm64_v8a");
        if (!order.contains("armeabi_v7a")) order.add("armeabi_v7a");
        return order.toArray(new String[0]);
    }

    /**
     * Select the best DPI split for the device's screen density.
     */
    private static File findBestDpiSplit(List<File> apks, File baseApk, Context context) {
        int deviceDpi = getDeviceDpi(context);
        String preferredDpi = findNearestDpiBucket(deviceDpi);
        Log.d(TAG, "Device DPI=" + deviceDpi + ", preferred bucket=" + preferredDpi);

        // First, look for exact match
        for (File f : apks) {
            if (f.equals(baseApk)) continue;
            String n = normalizeSplitName(f.getName());
            if (n.contains(preferredDpi)) return f;
        }

        // Fall back through DPI buckets from highest to lowest
        for (String dpi : DPI_NAMES) {
            if (dpi.equals(preferredDpi)) continue;
            for (File f : apks) {
                if (f.equals(baseApk)) continue;
                String n = normalizeSplitName(f.getName());
                if (isAbiSplit(n)) continue;

                if (n.contains(dpi)) return f;
            }
        }
        return null;
    }

    private static List<File> findLocaleSplits(List<File> apks, File baseApk, Context context) {
        List<File> localeSplits = new ArrayList<>();
        for (String localeToken : getPreferredLocaleTokens(context)) {
            for (File apk : apks) {
                if (apk.equals(baseApk)) {
                    continue;
                }
                String normalizedName = normalizeSplitName(apk.getName());
                if (!isConfigSplit(normalizedName) || isAbiSplit(normalizedName) || containsDpiToken(normalizedName)) {
                    continue;
                }
                if (containsToken(normalizedName, localeToken)) {
                    localeSplits.add(apk);
                }
            }
        }
        return localeSplits;
    }

    private static List<String> getPreferredLocaleTokens(Context context) {
        List<String> tokens = new ArrayList<>(2);
        Locale locale = null;
        if (context != null && context.getResources() != null) {
            Configuration configuration = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && configuration.getLocales() != null
                    && !configuration.getLocales().isEmpty()) {
                locale = configuration.getLocales().get(0);
            } else {
                //noinspection deprecation
                locale = configuration.locale;
            }
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        String language = locale.getLanguage() == null ? "" : locale.getLanguage().toLowerCase(Locale.ROOT);
        String country = locale.getCountry() == null ? "" : locale.getCountry().toLowerCase(Locale.ROOT);
        if (!language.isEmpty() && !country.isEmpty()) {
            tokens.add(language + "_" + country);
        }
        if (!language.isEmpty()) {
            tokens.add(language);
        }
        return tokens;
    }

    private static boolean containsDpiToken(String normalizedName) {
        for (String dpi : DPI_NAMES) {
            if (normalizedName.contains(dpi)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isAbiSplit(String normalizedName) {
        for (String abi : ABI_ARM64) if (normalizedName.contains(abi.replace("-", "_"))) return true;
        for (String abi : ABI_ARMEABI) if (normalizedName.contains(abi.replace("-", "_"))) return true;
        for (String abi : ABI_X86_64) if (normalizedName.contains(abi.replace("-", "_"))) return true;
        for (String abi : ABI_X86) if (normalizedName.contains(abi.replace("-", "_"))) return true;
        return false;
    }

    private static boolean isConfigSplit(String normalizedName) {
        return normalizedName.startsWith("config.")
                || normalizedName.startsWith("split_config.")
                || normalizedName.contains(".config.")
                || normalizedName.contains("_config_");
    }

    private static String normalizeSplitName(String fileName) {
        return fileName.toLowerCase(Locale.ROOT).replace('-', '_');
    }

    private static boolean containsToken(String normalizedName, String token) {
        String boundarySafe = "_" + normalizedName.replace('.', '_') + "_";
        String normalizedToken = token.toLowerCase(Locale.ROOT).replace('-', '_');
        return boundarySafe.contains("_" + normalizedToken + "_");
    }

    private static void addIfAbsent(List<File> files, File candidate) {
        if (candidate == null) {
            return;
        }
        for (File file : files) {
            if (candidate.equals(file)) {
                return;
            }
        }
        files.add(candidate);
    }

    private static int getDeviceDpi(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            wm.getDefaultDisplay().getMetrics(metrics);
            return metrics.densityDpi;
        }
        return DisplayMetrics.DENSITY_DEFAULT;
    }

    /**
     * Find the DPI bucket name nearest to the given density value.
     */
    private static String findNearestDpiBucket(int dpi) {
        int bestIdx = 0;
        int bestDiff = Integer.MAX_VALUE;
        for (int i = 0; i < DPI_VALUES.length; i++) {
            int diff = Math.abs(DPI_VALUES[i] - dpi);
            if (diff < bestDiff) {
                bestDiff = diff;
                bestIdx = i;
            }
        }
        return DPI_NAMES[bestIdx];
    }

    /**
     * Returns a stable extraction directory for a given archive, scoped under
     * the app's cache dir so the OS can clean it up if space is needed.
     */
    private static File getExtractionDir(File archiveFile, Context context) {
        String hash = md5Short(archiveFile.getAbsolutePath());
        return new File(context.getCacheDir(), "split_apk_tmp" + File.separator + hash);
    }

    private static String md5Short(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes("UTF-8"));
            return new BigInteger(1, digest).toString(16).substring(0, 8);
        } catch (Exception e) {
            return String.valueOf(Math.abs(input.hashCode()));
        }
    }
}
