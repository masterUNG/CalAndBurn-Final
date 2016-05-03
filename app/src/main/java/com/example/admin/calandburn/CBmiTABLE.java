package com.example.admin.calandburn;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Admin on 10/11/2558.
 */
public class CBmiTABLE {

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public CBmiTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    } //Constuctor
} // CBmiTABLE
