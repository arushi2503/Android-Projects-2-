package com.example.admin.basicuidesign2;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.example.admin.basicuidesign2.data.ContactContract.ContactEntry;

public class ContactCursorAdapter extends CursorAdapter {

    public ContactCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView phSummaryTextView = (TextView) view.findViewById(R.id.phone_summary);
        TextView emSummaryTextView = (TextView) view.findViewById(R.id.email_summary);

        // Find the columns of pet attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(ContactEntry.COLUMN_CONTACT_NAME);
        int phoneColumnIndex = cursor.getColumnIndex(ContactEntry.COLUMN_CONTACT_PHONE);
        int emailColumnIndex = cursor.getColumnIndex(ContactEntry.COLUMN_CONTACT_EMAIL);

        // Read the pet attributes from the Cursor for the current pet
        String contactName = cursor.getString(nameColumnIndex);
        String contactPhone = cursor.getString(phoneColumnIndex);
        String contactEmail = cursor.getString(emailColumnIndex);


        if (TextUtils.isEmpty(contactEmail)) {
            contactEmail = context.getString(R.string.unknown_email);
        }

        // Update the TextViews with the attributes for the current pet
        nameTextView.setText(contactName);
        phSummaryTextView.setText(contactPhone);
        emSummaryTextView.setText(contactEmail);
    }
}