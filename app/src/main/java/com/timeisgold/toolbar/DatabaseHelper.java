package com.timeisgold.toolbar;
import android.content.Context;
import android
        .database
        .sqlite
        .SQLiteDatabase;
import android
        .database
        .sqlite
        .SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {
    static final String TABLE_NAME = "People";
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createQuery = "CREATE TABLE " + TABLE_NAME + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "NAME TEXT NOT NULL, " + "AGE INTEGER, " + "PHONE INTEGER );";
        sqLiteDatabase.execSQL(createQuery);
    }
    @Override public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String createQuery = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        sqLiteDatabase.execSQL(createQuery);
    }
}