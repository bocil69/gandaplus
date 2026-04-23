package com.fufufu.katrina.backup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/* loaded from: classes5.dex */
public class DBAdapter {
    private static final String DATABASE_NAME = "katrinakill";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DBAdapter";
    private DatabaseHelper DBHelper;
    private final Context context;
    private SQLiteDatabase db;
    private int openedConnections = 0;

    public DBAdapter(Context context) {
        this.context = context;
        this.DBHelper = new DatabaseHelper(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DBAdapter.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            Statics.LogD("DatabaseHelper -> onCreate");
            sQLiteDatabase.execSQL("create table blackList ([packageName] nvarchar(50));");
            sQLiteDatabase.execSQL("create table whiteList ([packageName] nvarchar(50));");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.android.chrome');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.apps.docs');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.apps.books');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.apps.maps');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.apps.plus');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.android.browser');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.android.calculator2');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.android.gallery3d');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.gm');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.music');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.talk');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.vending');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.android.vending');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.gsf');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.gms');");
            sQLiteDatabase.execSQL("insert into whiteList([packageName]) values('com.fufufu.katrina.backup');");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Statics.LogD("DatabaseHelper -> onUpgrade");
            onCreate(sQLiteDatabase);
        }
    }

    public synchronized DBAdapter open() throws SQLException {
        DBAdapter dBAdapter;
        try {
            this.openedConnections++;
            this.db = this.DBHelper.getWritableDatabase();
            dBAdapter = this;
        } catch (Exception e) {
            e.printStackTrace();
            dBAdapter = null;
        }
        return dBAdapter;
    }

    public synchronized void close() {
        int i = this.openedConnections - 1;
        this.openedConnections = i;
        if (i == 0) {
            this.DBHelper.close();
        }
    }

    public long insert(String str, ContentValues contentValues) {
        long j;
        try {
            open();
            j = this.db.insertOrThrow(str, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            j = -1;
        }
        close();
        return j;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int update(java.lang.String r7, android.content.ContentValues r8, android.content.ContentValues r9) {
        /*
            r6 = this;
            java.lang.String r0 = ""
            if (r9 == 0) goto L42
            java.util.Set r1 = r9.keySet()     // Catch: java.lang.Exception -> L3b
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Exception -> L3b
            r2 = r0
        Ld:
            boolean r3 = r1.hasNext()     // Catch: java.lang.Exception -> L39
            if (r3 != 0) goto L14
            goto L41
        L14:
            java.lang.Object r3 = r1.next()     // Catch: java.lang.Exception -> L39
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Exception -> L39
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L39
            java.lang.String r5 = java.lang.String.valueOf(r2)     // Catch: java.lang.Exception -> L39
            r4.<init>(r5)     // Catch: java.lang.Exception -> L39
            if (r2 != r0) goto L27
            r5 = r0
            goto L29
        L27:
            java.lang.String r5 = " AND "
        L29:
            r4.append(r5)     // Catch: java.lang.Exception -> L39
            r4.append(r3)     // Catch: java.lang.Exception -> L39
            java.lang.String r3 = "=?"
            r4.append(r3)     // Catch: java.lang.Exception -> L39
            java.lang.String r2 = r4.toString()     // Catch: java.lang.Exception -> L39
            goto Ld
        L39:
            r0 = move-exception
            goto L3e
        L3b:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L3e:
            r0.printStackTrace()
        L41:
            r0 = r2
        L42:
            r1 = 0
            if (r9 == 0) goto L83
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Set r9 = r9.valueSet()
            java.util.Iterator r9 = r9.iterator()
        L52:
            boolean r3 = r9.hasNext()
            if (r3 != 0) goto L66
            int r9 = r2.size()
            java.lang.String[] r9 = new java.lang.String[r9]
            java.lang.Object[] r9 = r2.toArray(r9)
            r1 = r9
            java.lang.String[] r1 = (java.lang.String[]) r1
            goto L83
        L66:
            java.lang.Object r3 = r9.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            r4.toString()
            java.lang.Object r3 = r3.getValue()
            if (r3 != 0) goto L7b
            r3 = r1
            goto L7f
        L7b:
            java.lang.String r3 = r3.toString()
        L7f:
            r2.add(r3)
            goto L52
        L83:
            r6.open()
            android.database.sqlite.SQLiteDatabase r9 = r6.db
            int r7 = r9.update(r7, r8, r0, r1)
            r6.close()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fufufu.katrina.backup.DBAdapter.update(java.lang.String, android.content.ContentValues, android.content.ContentValues):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int delete(java.lang.String r7, android.content.ContentValues r8) {
        /*
            r6 = this;
            java.lang.String r0 = ""
            if (r8 == 0) goto L42
            java.util.Set r1 = r8.keySet()     // Catch: java.lang.Exception -> L3b
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Exception -> L3b
            r2 = r0
        Ld:
            boolean r3 = r1.hasNext()     // Catch: java.lang.Exception -> L39
            if (r3 != 0) goto L14
            goto L41
        L14:
            java.lang.Object r3 = r1.next()     // Catch: java.lang.Exception -> L39
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Exception -> L39
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L39
            java.lang.String r5 = java.lang.String.valueOf(r2)     // Catch: java.lang.Exception -> L39
            r4.<init>(r5)     // Catch: java.lang.Exception -> L39
            if (r2 != r0) goto L27
            r5 = r0
            goto L29
        L27:
            java.lang.String r5 = " AND "
        L29:
            r4.append(r5)     // Catch: java.lang.Exception -> L39
            r4.append(r3)     // Catch: java.lang.Exception -> L39
            java.lang.String r3 = "=?"
            r4.append(r3)     // Catch: java.lang.Exception -> L39
            java.lang.String r2 = r4.toString()     // Catch: java.lang.Exception -> L39
            goto Ld
        L39:
            r0 = move-exception
            goto L3e
        L3b:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L3e:
            r0.printStackTrace()
        L41:
            r0 = r2
        L42:
            r1 = 0
            if (r8 == 0) goto L83
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Set r8 = r8.valueSet()
            java.util.Iterator r8 = r8.iterator()
        L52:
            boolean r3 = r8.hasNext()
            if (r3 != 0) goto L66
            int r8 = r2.size()
            java.lang.String[] r8 = new java.lang.String[r8]
            java.lang.Object[] r8 = r2.toArray(r8)
            r1 = r8
            java.lang.String[] r1 = (java.lang.String[]) r1
            goto L83
        L66:
            java.lang.Object r3 = r8.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            r4.toString()
            java.lang.Object r3 = r3.getValue()
            if (r3 != 0) goto L7b
            r3 = r1
            goto L7f
        L7b:
            java.lang.String r3 = r3.toString()
        L7f:
            r2.add(r3)
            goto L52
        L83:
            r6.open()
            android.database.sqlite.SQLiteDatabase r8 = r6.db
            int r7 = r8.delete(r7, r0, r1)
            r6.close()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fufufu.katrina.backup.DBAdapter.delete(java.lang.String, android.content.ContentValues):int");
    }

    public Cursor select(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        try {
            open();
        } catch (Exception e) {
            e = e;
        }
        try {
            return this.db.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return null;
        }
    }
}
