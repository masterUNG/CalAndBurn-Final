package com.example.admin.calandburn;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Admin on 10/11/2558.
 */
public class ActivityTABLE {
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String TABLE_ACTIVITY = "activityTABLE";
    public static final String COLUMN_ID_ACTIVITY = "_id";
    public static final String COLUMN_NAME_ACTIVITY = "nameact";
    public static final String COLUMN_BURN_ACTIVITY = "burnact";
    public static final String COLUMN_ABOUT_ACTIVITY = "aboutact";

    public ActivityTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    } //Consteutor

    public String[] readAllDataActivity(int intColumn) {

        String[] strResult = null;
        Cursor objCursor = readSQLite.query(TABLE_ACTIVITY,
                new String[]{COLUMN_ID_ACTIVITY, COLUMN_NAME_ACTIVITY, COLUMN_BURN_ACTIVITY, COLUMN_ABOUT_ACTIVITY},
                null, null, null, null, null);

        if (objCursor != null) {

            strResult = new String[objCursor.getCount()];
            objCursor.moveToFirst();

            for (int i = 0; i < objCursor.getCount(); i++) {

                switch (intColumn) {
                    case 1:
                        strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_NAME_ACTIVITY));
                        break;
                    case 2:
                        strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_BURN_ACTIVITY));
                        break;
                    case 3:
                        strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_ABOUT_ACTIVITY));
                        break;
                } // switch
                objCursor.moveToNext();
            } // for
        } // if
        objCursor.close();

        return strResult;
    }
} //ActivityTABLE
