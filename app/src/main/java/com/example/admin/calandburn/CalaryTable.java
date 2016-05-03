package com.example.admin.calandburn;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Admin on 3/4/2559.
 */
public class CalaryTable extends AppCompatActivity {

    private SQLiteDatabase readSqLiteDatabase;
    private MyOpenHelper objMyOpenHelper;

    private static final String Table_Calary = "calary_table";
    private static final String Column_Id = "_id";
    private static final String Column_Date = "Date";
    private static final String Column_Food = "Food";
    private static final String Column_Amount = "Amount";
    private static final String Column_Calfood = "CalFood";

    public CalaryTable(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Contructor

    public String[] ReadAllCalaryTable(int intColumn) {

        String[] strResult = null;
        Cursor CalaryTableCursor = readSqLiteDatabase.query(Table_Calary, new String[]{Column_Id, Column_Date,
                Column_Food, Column_Amount, Column_Calfood}, null, null, null, null, null);

        if (CalaryTableCursor != null) {

            strResult = new String[CalaryTableCursor.getCount()];
            CalaryTableCursor.moveToFirst();

            for (int i = 0; i < CalaryTableCursor.getCount(); i++) {

                switch (intColumn) {
                    case 1:
                        strResult[i] = CalaryTableCursor.getString(CalaryTableCursor.getColumnIndex(Column_Date));
                        break;
                    case 2:
                        strResult[i] = CalaryTableCursor.getString(CalaryTableCursor.getColumnIndex(Column_Food));
                        break;
                    case 3:
                        strResult[i] = CalaryTableCursor.getString(CalaryTableCursor.getColumnIndex(Column_Amount));
                        break;
                    case 4:
                        strResult[i] = CalaryTableCursor.getString(CalaryTableCursor.getColumnIndex(Column_Calfood));
                        break;
                }   // switch

                CalaryTableCursor.moveToNext();

            }

        }
        CalaryTableCursor.close();

        return strResult;
    }

}
