package com.example.admin.calandburn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Admin on 10/11/2558.
 */
public class FoodTABLE {

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String TABLE_FOOD = "foodTABLE";
    public static final String COLUMN_ID_FOOD = "_id";
    public static final String COLUMN_NAME_FOOD = "namefood";
    public static final String COLUMN_CAL_FOOD = "calfood";
    public static final String COLUMN_ABOUT_FOOD = "aboutfood";

    public FoodTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();




    } // Contructor

    //Read All Data
    public String[] readAllDataFood(int intColumn) {

        String[] strResult = null;
        Cursor objCursor = readSQLite.rawQuery("SELECT * FROM foodTABLE WHERE aboutfood='1'"
                , null);
        if (objCursor != null) {

            strResult = new String[objCursor.getCount()];
            objCursor.moveToFirst();

           for (int i=0; i<objCursor.getCount(); i++) {

               switch (intColumn) {
                   case 1:
                       strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_NAME_FOOD));
                       break;
                   case 2:
                       strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_CAL_FOOD));
                       break;
                   case 3:
                       strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_ABOUT_FOOD));
                       break;
               }   // switch

               objCursor.moveToNext();

           }    // for
        }   // if
        objCursor.close();


        return strResult;
    }
    public String[] readAllDataDessert (int intColumn) {

        String[] strResult = null;
        Cursor objCursor = readSQLite.rawQuery("SELECT * FROM foodTABLE WHERE aboutfood='2'"
                , null);
        if (objCursor != null) {

            strResult = new String[objCursor.getCount()];
            objCursor.moveToFirst();

            for (int i=0; i<objCursor.getCount(); i++) {

                switch (intColumn) {
                    case 1:
                        strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_NAME_FOOD));
                        break;
                    case 2:
                        strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_CAL_FOOD));
                        break;
                    case 3:
                        strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_ABOUT_FOOD));
                        break;
                }   // switch

                objCursor.moveToNext();

            }    // for
        }   // if
        objCursor.close();


        return strResult;
    }

    public String[] readAllDataDrink(int intColumn) {

        String[] strResult = null;
        Cursor objCursor = readSQLite.rawQuery("SELECT * FROM foodTABLE WHERE aboutfood='3'"
                , null);
        if (objCursor != null) {

            strResult = new String[objCursor.getCount()];
            objCursor.moveToFirst();

            for (int i=0; i<objCursor.getCount(); i++) {

                switch (intColumn) {
                    case 1:
                        strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_NAME_FOOD));
                        break;
                    case 2:
                        strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_CAL_FOOD));
                        break;
                    case 3:
                        strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_ABOUT_FOOD));
                        break;
                }   // switch

                objCursor.moveToNext();

            }    // for
        }   // if
        objCursor.close();


        return strResult;
    }


    //Add New Value To SQLite
    public long addNewFoodToSQLite (String strNameFood, double douCalFood, String strAboutFood) {

        ContentValues objContentValue = new ContentValues();
        objContentValue.put(COLUMN_NAME_FOOD, strNameFood);
        objContentValue.put(COLUMN_CAL_FOOD, douCalFood);
        objContentValue.put(COLUMN_ABOUT_FOOD, strAboutFood);

        return writeSQLite.insert(TABLE_FOOD, null, objContentValue);
    }   //addNewValueToSQLite

} // FoodTABLE
