package com.example.admin.calandburn;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Admin on 10/11/2558.
 */
public class UserTABLE {

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String TABLE_USER = "userTABLE";
    public static final String COLUMN_id_user = "_id";
    public static final String COLUMN_date = "date";
    public static final String COLUMN_name = "name";
    public static final String COLUMN_sex = "sex";
    public static final String COLUMN_age = "age";
    public static final String COLUMN_height = "height";
    public static final String COLUMN_weight = "weight";
    public static final String COLUMN_myact = "myact";
    public static final String COLUMN_factor = "factor";
    public static final String COLUMN_bmiuser = "bmiuser";
    public static final String COLUMN_bmruser = "bmruser";

    public UserTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    } // Constructor

    public long addValueToUser(String strDate,
                               String strName,
                               String strSex,
                               String strAge,
                               String strHeight,
                               String strWeight,
                               int strMyACT,
                               String strFactor,
                               String strBMIuser,
                               String strBMRuser) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_date, strDate);
        objContentValues.put(COLUMN_name, strName);
        objContentValues.put(COLUMN_sex, strSex);
        objContentValues.put(COLUMN_age, strAge);
        objContentValues.put(COLUMN_height, strHeight);
        objContentValues.put(COLUMN_weight, strWeight);
        objContentValues.put(COLUMN_myact, strMyACT);
        objContentValues.put(COLUMN_factor, strFactor);
        objContentValues.put(COLUMN_bmiuser, strBMIuser);
        objContentValues.put(COLUMN_bmruser, strBMRuser);

        return writeSQLite.insert(TABLE_USER, null, objContentValues);
    }

} // UserTABLE
