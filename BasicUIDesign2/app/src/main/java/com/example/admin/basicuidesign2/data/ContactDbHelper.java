package com.example.admin.basicuidesign2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.basicuidesign2.data.ContactContract.ContactEntry;

public class ContactDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = ContactDbHelper.class.getSimpleName();


    private static final String DATABASE_NAME = "contact.db";
    private static final int DATABASE_VERSION = 1;


    public ContactDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the Contact table
        String SQL_CREATE_CONTACTS_TABLE =  "CREATE TABLE " + ContactEntry.TABLE_NAME + " ("
                + ContactEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ContactEntry.COLUMN_CONTACT_NAME + " TEXT NOT NULL, "
                + ContactEntry.COLUMN_CONTACT_EMAIL + " TEXT, "
                + ContactEntry.COLUMN_CONTACT_GENDER + " INTEGER NOT NULL, "
                + ContactEntry.COLUMN_CONTACT_PHONE + " TEXT NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_CONTACTS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
        db.execSQL("DROP TABLE IF EXISTS " + ContactEntry.TABLE_NAME);
    }
}