package com.example.admin.calandburn;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 3/5/16 AD.
 */
public class MyManage {

    //Explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase;

    public static final String table_calary = "calary_table";
    public static final String table_burn = "burn_table";

    public static final String column_id = "_id";
    public static final String column_Food = "Food";
    public static final String column_Amount = "Amount";
    public static final String column_CalFood = "CalFood";
    public static final String column_Exercise = "Exercise";
    public static final String column_Hour = "Hour";
    public static final String column_CalBurn = "CalBurn";
    public static final String column_date = "Date";

    public MyManage(Context context) {

        //Connected Database
        myOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = myOpenHelper.getWritableDatabase();

    }   // Constructor

    public long addBurn(String strDate,
                        String strExercise,
                        String strHour,
                        String strCalBurn) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_date, strDate);
        contentValues.put(column_Exercise, strExercise);
        contentValues.put(column_Hour, strHour);
        contentValues.put(column_CalBurn, strCalBurn);

        return writeSqLiteDatabase.insert(table_burn, null, contentValues);
    }


    public long addCalary(String strDate,
                          String strFood,
                          String strAmount,
                          String strCalFood) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_date, strDate);
        contentValues.put(column_Food, strFood);
        contentValues.put(column_Amount, strAmount);
        contentValues.put(column_CalFood, strCalFood);

        return writeSqLiteDatabase.insert(table_calary, null, contentValues);
    }

}   // Main Class
