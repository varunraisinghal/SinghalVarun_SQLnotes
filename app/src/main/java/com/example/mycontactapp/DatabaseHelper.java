package com.example.mycontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contact2019.db";
    public static final String TABLE_NAME = "Contact2019_table";
    public static final String ID = "ID";
    public static final String COLUMN_NAME_CONTACT = "contact";
    public static final String COLUMN_NAME_PHONE = "phone";
    public static final String COLUMN_NAME_ADDRESS = "address";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID  + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_CONTACT + " TEXT" +
                    COLUMN_NAME_PHONE + "TEXT,"
                    + COLUMN_NAME_ADDRESS + "TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS "  + TABLE_NAME;

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null,  1);
        SQLiteDatabase db = this.getWritableDatabase(); //FOR TEST ONLY, REMOVE LATER
        Log.d("MyContactApp","DatabaseHelper: constructed the DatabaseHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.d("MyContactApp","DatabaseHelper: creating db");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public boolean insertData(String name, String number, String address)
    {
        Log.d("MyContactApp","DatabaseHelper: inserting data");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_CONTACT,name);
        contentValues.put(COLUMN_NAME_PHONE, number);
        contentValues.put(COLUMN_NAME_ADDRESS,address);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
        {
            Log.d("MyContactApp","DatabaseHelper: Contact insert - FAILED");
            return false;
        }
        else
        {
            Log.d("MyContactApp","DatabaseHelper: Contact insert - PASSED");
            return true;
        }
    }
    public Cursor getAllData()
    {
        //Log.d here
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from  " + TABLE_NAME,null);
        return res;
    }
}