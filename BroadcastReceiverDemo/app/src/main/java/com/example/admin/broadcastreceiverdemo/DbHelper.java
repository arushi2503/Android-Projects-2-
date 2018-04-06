package com.example.admin.broadcastreceiverdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="numberDb";
    public static final int DATABASE_VERSION= 1;
    public static final String TABLE_NAME="incomingInfo";
    public static final String COLUMN_ID = "_id";
    public static final String INCOMING_NUMBER = "incomingNumber";
    public static final String CREATE = "CREATE TABLE " + TABLE_NAME +
            "(" + COLUMN_ID + " integer primary key autoincrement, " + INCOMING_NUMBER + " text);";
    public static final String DROP_TABLE = "drop table if exists " + TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
    //Save info in the database
    public boolean saveNumber(String number){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(INCOMING_NUMBER,number);
        db.insert(TABLE_NAME,null,contentValues);
        return true;
    }
    //Read info from the database
    public Cursor readNumber(SQLiteDatabase database){
        String[] projection = {"id",INCOMING_NUMBER};

        return (database.query(TABLE_NAME,projection,null,null,null,null,null));
    }

    public Cursor getAllNumbers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + TABLE_NAME, null );
        return res;
    }
}
