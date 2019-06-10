package com.jkit.mynotesapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

public class DBManager {
    public DatabaseHelper dbHelper;
    public Context context;
    public SQLiteDatabase database;

    public DBManager(Context c) {
        this.context = c;
    }

    public DBManager open() throws SQLException{
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void insert(String title, String des){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.TITLE, title);
        contentValues.put(DatabaseHelper.DES, des);

        database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    public Cursor featch(){
        String[] colum = new String[] { DatabaseHelper._ID, DatabaseHelper.TITLE, DatabaseHelper.DES };

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, colum, null, null, null, null, null);

        if(colum != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String title, String des){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.TITLE, title);
        contentValues.put(DatabaseHelper.DES, des);

        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + "=" + _id, null);

        return i;
    }

    public void delete(long _id){
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}
