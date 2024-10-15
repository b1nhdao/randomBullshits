package com.example.qlns;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {
    // Khai báo các tham số hằng cho database
    public static final String dbName = "note.db";
    public static final int dbVersion = 1;

    // Khai báo các tham số hằng của table cần tạo
    public static final String tbName = "tblNote";
    public static final String id = "ID";
    public static final String title = "title";
    public static final String date = "date";
    public static final String content = "content";

    public dbHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    // Tạo bảng
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + tbName + " ( " +
                id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                title + " TEXT, " +
                date + " TEXT, " +
                content + " TEXT);";
        db.execSQL(sql); // Thực hiện câu lệnh tạo bảng
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tbName);
        onCreate(db);
    }
}
