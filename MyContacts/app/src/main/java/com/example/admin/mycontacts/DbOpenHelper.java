package com.example.admin.mycontacts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbOpenHelper extends SQLiteOpenHelper {

    //Constants for db name and version
    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;

    //Constants for table and columns
    public static final String TABLE_CONTACTS = "contacts";
    public static final String CONTACT_ID = "_id";
    public static final String CONTACT_NAME = "contactName";
    public static final String CONTACT_PHONE = "contactPhone";
    public static final String CONTACT_CREATED_ON = "contactCreationTimeStamp";

    public static final String[] ALL_COLUMNS =
            {CONTACT_ID,CONTACT_NAME,CONTACT_PHONE,CONTACT_CREATED_ON};

    //Create Table
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_CONTACTS + " (" +
                    CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CONTACT_NAME + " TEXT, " +
                    CONTACT_PHONE + " TEXT, " +
                    CONTACT_CREATED_ON + " TEXT default CURRENT_TIMESTAMP" +
                    ")";
    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.w(DATABASE_NAME, CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONTACTS);
        onCreate(sqLiteDatabase);
    }

    public void delete(int anInt) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CONTACTS,CONTACT_ID+"=?",new String[]{String.valueOf(anInt)});
        db.close();
    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT  * FROM "+TABLE_CONTACTS, null);
        return c;
    }
}
