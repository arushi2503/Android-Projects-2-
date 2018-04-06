package com.example.admin.basicuidesign2;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.admin.basicuidesign2.ContactCursorAdapter;
import com.example.admin.basicuidesign2.data.ContactContract.ContactEntry;

public class ShowContactActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int CONTACT_LOADER = 0;

    ContactCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contacts);


        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowContactActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });

        ListView contactListView = (ListView) findViewById(R.id.list);


        View emptyView = findViewById(R.id.empty_view);
        contactListView.setEmptyView(emptyView);

        mCursorAdapter = new ContactCursorAdapter(this, null);
        contactListView.setAdapter((ListAdapter) mCursorAdapter);

        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(ShowContactActivity.this, AddContactActivity.class);

                Uri currentContactUri = ContentUris.withAppendedId(ContactEntry.CONTENT_URI, id);

                intent.setData(currentContactUri);
                startActivity(intent);
            }
        });

        getLoaderManager().initLoader(CONTACT_LOADER, null, this);
    }

    private void insertContact() {
        ContentValues values = new ContentValues();
        values.put(ContactEntry.COLUMN_CONTACT_NAME, "ABC");
        values.put(ContactEntry.COLUMN_CONTACT_EMAIL, "abc@gmail.com");
        values.put(ContactEntry.COLUMN_CONTACT_GENDER, ContactEntry.GENDER_MALE);
        values.put(ContactEntry.COLUMN_CONTACT_PHONE, "9999999999");

        Uri newUri = getContentResolver().insert(ContactEntry.CONTENT_URI, values);
    }

    private void deleteAllContacts() {
        int rowsDeleted = getContentResolver().delete(ContactEntry.CONTENT_URI, null, null);
        Log.v("ShowContactActivity", rowsDeleted + " rows deleted from Contact database");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertContact();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                deleteAllContacts();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String[] projection = {
                ContactEntry._ID,
                ContactEntry.COLUMN_CONTACT_NAME,
                ContactEntry.COLUMN_CONTACT_EMAIL,
                ContactEntry.COLUMN_CONTACT_PHONE};

        return new CursorLoader(this,   // Parent activity context
                ContactEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mCursorAdapter.swapCursor(null);
    }
}
