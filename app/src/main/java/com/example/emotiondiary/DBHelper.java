package com.example.emotiondiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "diary_db";
    private static final String DB_TABLE = "diary_table";
    private static final String KEY_DATE = "Date";
    private static final String KEY_EMOTION = "Emotion";
    private static final String KEY_CONTENTS = "Contents";
    private static final String KEY_IMG = "Img";

    private static final String dbData =  "CREATE TABLE diary_table (" +
            "Date TEXT, " +
            "Img BLOB, " +
            "Contents TEXT, " +
            "Emotion TEXT)";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(dbData);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert (String date, byte[] img, String contents, String emotion) throws SQLException {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_DATE,date);
        cv.put(KEY_IMG,img);
        cv.put(KEY_CONTENTS,contents);
        cv.put(KEY_EMOTION, emotion);
        db.insert(DB_TABLE,null, cv);
    }

    public void update (String _query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete (String _query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }
}
