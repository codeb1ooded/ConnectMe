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
    public static final String CHAT_TABLE_NAME = "CHAT";
    public static final String COLUMN_MESSAGE = "MESSAGE";
    public static final String COLUMN_TIMESTAMP = "TIMESTAMP";
    public static final String COLUMN_USER = "USER_ME";

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

        String CREATE_CHAT_TABLE = "CREATE TABLE " + CHAT_TABLE_NAME + "(" +
                // "LABEL_ID" + " INTEGER PRIMARY KEY ," +
                COLUMN_USERNAME + " STRING, " +
                COLUMN_MESSAGE + " STRING, " +
                COLUMN_TIMESTAMP + " INTEGER, " +
                COLUMN_USER + " INTEGER " +     // 0: ME 1: Front person
                ")";

        db.execSQL(CREATE_LABEL_TABLE);
        db.execSQL(CREATE_CHAT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {}

}