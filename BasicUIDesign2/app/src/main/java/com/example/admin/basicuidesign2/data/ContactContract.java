package com.example.admin.basicuidesign2.data;

import android.net.Uri;
import android.content.ContentResolver;
import android.provider.BaseColumns;

public final class ContactContract {


    private ContactContract() {}

    public static final String CONTENT_AUTHORITY = "com.example.admin.basicuidesign2";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_CONTACTS = "contacts";

    public static final class ContactEntry implements BaseColumns {


        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CONTACTS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CONTACTS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CONTACTS;


        public final static String TABLE_NAME = "contacts";


        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_CONTACT_NAME ="name";

        public final static String COLUMN_CONTACT_EMAIL = "email";

        public final static String COLUMN_CONTACT_GENDER = "gender";

        public final static String COLUMN_CONTACT_PHONE = "phone";


        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;


        public static boolean isValidGender(int gender) {
            if (gender == GENDER_UNKNOWN || gender == GENDER_MALE || gender == GENDER_FEMALE) {
                return true;
            }
            return false;
        }
    }

}
