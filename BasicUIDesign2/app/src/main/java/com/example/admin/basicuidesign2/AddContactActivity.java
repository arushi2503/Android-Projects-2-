package com.example.admin.basicuidesign2;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.admin.basicuidesign2.data.ContactContract.ContactEntry;

public class AddContactActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_CONTACT_LOADER = 0;


    private Uri mCurrentContactUri;

    private EditText mNameEditText;

    private EditText mEmailEditText;

    private EditText mPhoneEditText;

    private Spinner mGenderSpinner;

    private int mGender = ContactEntry.GENDER_UNKNOWN;

    private boolean mContactHasChanged = false;


    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mContactHasChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Intent intent = getIntent();
        mCurrentContactUri = intent.getData();

        if (mCurrentContactUri == null) {

            setTitle(getString(R.string.editor_activity_title_new_contact));

            invalidateOptionsMenu();
        } else {

            setTitle(getString(R.string.editor_activity_title_edit_contact));

            getLoaderManager().initLoader(EXISTING_CONTACT_LOADER, null, this);
        }

        mNameEditText = (EditText) findViewById(R.id.edit_contact_name);
        mEmailEditText = (EditText) findViewById(R.id.edit_contact_email);
        mPhoneEditText = (EditText) findViewById(R.id.edit_contact_phone);
        mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);

        mNameEditText.setOnTouchListener(mTouchListener);
        mEmailEditText.setOnTouchListener(mTouchListener);
        mPhoneEditText.setOnTouchListener(mTouchListener);
        mGenderSpinner.setOnTouchListener(mTouchListener);
        setupSpinner();
    }

    private void setupSpinner() {

        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = ContactEntry.GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = ContactEntry.GENDER_FEMALE;
                    } else {
                        mGender = ContactEntry.GENDER_UNKNOWN;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = ContactEntry.GENDER_UNKNOWN;
            }
        });
    }

    private void saveContact() {
        String nameString = mNameEditText.getText().toString().trim();
        String emailString = mEmailEditText.getText().toString().trim();
        String phoneString = mPhoneEditText.getText().toString().trim();

        if (mCurrentContactUri == null &&
                TextUtils.isEmpty(nameString) && TextUtils.isEmpty(emailString) &&
                TextUtils.isEmpty(phoneString) && mGender == ContactEntry.GENDER_UNKNOWN) {
            return;
        }

        ContentValues values = new ContentValues();
        values.put(ContactEntry.COLUMN_CONTACT_NAME, nameString);
        values.put(ContactEntry.COLUMN_CONTACT_EMAIL, emailString);
        values.put(ContactEntry.COLUMN_CONTACT_GENDER, mGender);
        values.put(ContactEntry.COLUMN_CONTACT_PHONE, phoneString);

        if (mCurrentContactUri == null) {

            Uri newUri = getContentResolver().insert(ContactEntry.CONTENT_URI, values);

            if (newUri == null) {
                Toast.makeText(this, getString(R.string.editor_insert_contact_failed),
                        Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, getString(R.string.editor_insert_contact_successful),
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            int rowsAffected = getContentResolver().update(mCurrentContactUri, values, null, null);

            if (rowsAffected == 0) {

                Toast.makeText(this, getString(R.string.editor_update_contact_error),
                        Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, getString(R.string.editor_update_contact_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (mCurrentContactUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:

                saveContact();
                finish();
                return true;

            case R.id.action_delete:

                showDeleteConfirmationDialog();
                return true;

            case android.R.id.home:
                if (!mContactHasChanged) {
                    NavUtils.navigateUpFromSameTask(AddContactActivity.this);
                    return true;
                }

                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                                NavUtils.navigateUpFromSameTask(AddContactActivity.this);
                            }
                        };

                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // If the pet hasn't changed, continue with handling back button press
        if (!mContactHasChanged) {
            super.onBackPressed();
            return;
        }

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };

        showUnsavedChangesDialog(discardButtonClickListener);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String[] projection = {
                ContactEntry._ID,
                ContactEntry.COLUMN_CONTACT_NAME,
                ContactEntry.COLUMN_CONTACT_EMAIL,
                ContactEntry.COLUMN_CONTACT_GENDER,
                ContactEntry.COLUMN_CONTACT_PHONE };

        return new CursorLoader(this,   // Parent activity context
                mCurrentContactUri,         // Query the content URI for the current pet
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndex(ContactEntry.COLUMN_CONTACT_NAME);
            int emailColumnIndex = cursor.getColumnIndex(ContactEntry.COLUMN_CONTACT_EMAIL);
            int genderColumnIndex = cursor.getColumnIndex(ContactEntry.COLUMN_CONTACT_GENDER);
            int phoneColumnIndex = cursor.getColumnIndex(ContactEntry.COLUMN_CONTACT_PHONE);

            String name = cursor.getString(nameColumnIndex);
            String email = cursor.getString(emailColumnIndex);
            int gender = cursor.getInt(genderColumnIndex);
            String phone = cursor.getString(phoneColumnIndex);

            // Update the views on the screen with the values from the database
            mNameEditText.setText(name);
            mEmailEditText.setText(email);
            mPhoneEditText.setText(phone);

            switch (gender) {
                case ContactEntry.GENDER_MALE:
                    mGenderSpinner.setSelection(1);
                    break;
                case ContactEntry.GENDER_FEMALE:
                    mGenderSpinner.setSelection(2);
                    break;
                default:
                    mGenderSpinner.setSelection(0);
                    break;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mNameEditText.setText("");
        mEmailEditText.setText("");
        mPhoneEditText.setText("");
        mGenderSpinner.setSelection(0); // Select "Unknown" gender
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDeleteConfirmationDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                deleteContact();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteContact() {

        if (mCurrentContactUri != null) {

            int rowsDeleted = getContentResolver().delete(mCurrentContactUri, null, null);

            if (rowsDeleted == 0) {

                Toast.makeText(this, getString(R.string.editor_delete_contact_failed),
                        Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, getString(R.string.editor_delete_contact_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }

        finish();
    }
}