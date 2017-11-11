package com.deepshikha.hackathonapp.database;

/**
 * Created by megha on 11/11/17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    static int version = 1;
    public static final String DATABASE_NAME = "ConnectMe";
    public static final String LABEL_TABLE_NAME = "LABELS";
    public static final String COLUMN_LABEL_VALUE = "LABEL_VALUE";
    public static final String COLUMN_USERNAME = "USERNAME";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LABEL_TABLE = "CREATE TABLE " + LABEL_TABLE_NAME + "(" +
               // "LABEL_ID" + " INTEGER PRIMARY KEY ," +
                COLUMN_USERNAME + " STRING, " +
                COLUMN_LABEL_VALUE + " STRING" +
                ")";

        db.execSQL(CREATE_LABEL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {}

}