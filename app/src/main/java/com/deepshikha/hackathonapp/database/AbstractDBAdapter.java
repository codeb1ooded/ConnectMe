package com.deepshikha.hackathonapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by megha on 11/11/17.
 */

public class AbstractDBAdapter{

    Context mContext;
    DatabaseHelper mdbHelper;
    SQLiteDatabase mDatabase;

    public AbstractDBAdapter(Context context){
        mContext = context;
        mdbHelper = new DatabaseHelper(mContext);
    }

    public AbstractDBAdapter open() throws SQLException {
        mDatabase = mdbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public void insertLabel(String username, String labelvalue){
        ContentValues values = new ContentValues();
        values.put("USERNAME", username);
        values.put("LABEL_VALUE", labelvalue);

        open();
        mDatabase.insert(DatabaseHelper.LABEL_TABLE_NAME, null, values);
        close();
    }

    public ArrayList<UsernameModel> getSimilarUsers(String keyword){
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.LABEL_TABLE_NAME + " WHERE " +
                "LABEL_VALUE" + " LIKE " + "\'%" + keyword + "%\'";
        ArrayList<UsernameModel> users = new ArrayList<>();

        open();
        Cursor cursor = mDatabase.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            UsernameModel user = new UsernameModel();
            user.label =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LABEL_VALUE));
            user.username =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME));
            users.add(user);
        }
        cursor.close();
        close();
        return users;
    }

    public ArrayList<UsernameModel> getAllUsers(){
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.LABEL_TABLE_NAME + " ORDER BY USERNAME";
        ArrayList<UsernameModel> users = new ArrayList<>();

        open();
        Cursor cursor = mDatabase.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            UsernameModel user = new UsernameModel();
            user.label =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LABEL_VALUE));
            user.username =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME));
            users.add(user);
        }
        cursor.close();
        close();
        return users;
    }

    public void deleteLabelTable(){
        open();
        mDatabase.delete(DatabaseHelper.LABEL_TABLE_NAME, null, null);
        close();
    }

}
