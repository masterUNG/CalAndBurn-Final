package com.example.admin.calandburn;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Admin on 10/11/2558.
 */
public class CBmrTABLE {

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public CBmrTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    } // Constructor
} // CBmrTABLE
