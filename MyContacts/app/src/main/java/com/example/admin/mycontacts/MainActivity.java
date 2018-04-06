package com.example.admin.mycontacts;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private CursorAdapter cursorAdapter;
    Cursor c;
    DbOpenHelper db;

    ContactsCursorAdapter contactsCursorAdapter;
    public static final String TABLE_CONTACTS = "contacts";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This is How you initialise db ,database and cursor
        db = new DbOpenHelper(MainActivity.this);
        SQLiteDatabase database = db.getReadableDatabase();
        c = database.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);
        Log.i("Cursor", c.toString());

        //THIS IS CALLED INITIALISATION of adapter
        cursorAdapter = new ContactsCursorAdapter(this, null, 0);
        contactsCursorAdapter = new ContactsCursorAdapter(MainActivity.this, c, 0);


        final ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(cursorAdapter);

        //registerForContextMenu(list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getLoaderManager().initLoader(0, null, this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                View getEmpIdView = li.inflate(R.layout.dialog_get_name_phone, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                // set dialog_get_name_phone.xml to alertdialog builder
                alertDialogBuilder.setView(getEmpIdView);

                final EditText nameInput = (EditText) getEmpIdView.findViewById(R.id.editTextDialogNameInput);
                final EditText phoneInput = (EditText) getEmpIdView.findViewById(R.id.editTextDialogPhoneInput);

                // set dialog message

                alertDialogBuilder
                        .setCancelable(true)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                String phone = phoneInput.getText().toString();
                                if (phone.matches("")) {
                                    Toast.makeText(MainActivity.this, "You did not enter number", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                insertContact(nameInput.getText().toString(), phone);
                               // restartLoader();

                            }
                        }).create()
                        .show();

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                ad.setTitle("Delete");
                ad.setMessage("Sure you want to delete record ?");
                final int pos = position;
                ad.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if( c != null && c.moveToPosition(pos) ){

                            //c.moveToPosition(pos);
                            //String myName = c.getString(c.getColumnIndexOrThrow(DbOpenHelper.CONTACT_NAME));
                            //Log.v(myName,"Name*******************************"+myName);
                            db.delete(c.getInt(c.getColumnIndex(DbOpenHelper.CONTACT_ID)));

                        }



                        //db.delete(c.getInt(c.getColumnIndex(DbOpenHelper.CONTACT_ID)));
                        c = db.getAll();
                        contactsCursorAdapter.swapCursor(c);
                        list.setAdapter(contactsCursorAdapter);
                        contactsCursorAdapter.notifyDataSetChanged();
                        cursorAdapter.notifyDataSetChanged();

                        restartLoader();
                    }
                });
                ad.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                ad.show();
                return false;
            }
        });

    }


    private void insertContact(String contactName, String contactPhone) {
        ContentValues values = new ContentValues();
        values.put(DbOpenHelper.CONTACT_NAME, contactName);
        values.put(DbOpenHelper.CONTACT_PHONE, contactPhone);
        Uri contactUri = getContentResolver().insert(ContactsProvider.CONTENT_URI, values);
        Toast.makeText(this, "Created Contact " + contactName, Toast.LENGTH_LONG).show();

    }

    private void deleteAllContacts() {

        getContentResolver().delete(ContactsProvider.CONTENT_URI, null, null);
        restartLoader();
        Toast.makeText(this, "All Contacts Deleted", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.deleteAllContacts:
                deleteAllContacts();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void restartLoader() {
        getLoaderManager().restartLoader(0, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, ContactsProvider.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }


}
