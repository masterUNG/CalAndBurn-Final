package com.example.admin.calandburn;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Admin on 2/4/2559.
 */
public class HisFoodData extends AppCompatActivity {


    // Explicit
    private String[] dateAllStrings, dateStrings; // DateALL เวลาที่ซ้ำได้
    private ListView listView;
    private ArrayList<String> stringArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.his_food_data);

        //Bind Widget
        listView = (ListView) findViewById(R.id.calorielistview);

        //Read All SQLite
        readAllSQLiteFood();

    } // Main Method

    private void readAllSQLiteFood() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + MyManage.table_calary, null);
        cursor.moveToFirst();
        int intCount = cursor.getCount();

        dateStrings = new String[intCount];
        String[] foodStrings = new String[intCount];
        String[] amountStrings = new String[intCount];
        String[] calFoodStrings = new String[intCount];

        for (int i = 0; i < intCount; i++) {
            dateStrings[i] = cursor.getString(1);
            cursor.moveToNext();
        }   // For
        cursor.close();

        findDate(dateStrings);

        // Create ListView
        CreatListView();


    }

    private void CreatListView() {

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,stringArrayList);
        listView.setAdapter(stringArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String strDateClick = stringArrayList.get(i);

                Log.d("3May", "Data ==> " + strDateClick);

                Intent objIntent = new Intent(HisFoodData.this, ShowDateHisFood.class);
                objIntent.putExtra("Date", strDateClick);
                startActivity(objIntent);

            }   // event
        });


    }
   /* private int findIDbyPosition(int position) {

        int intID = 1;
        String tag = "masterEVE";

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM  calary_table WHERE Date='" + strDate1 + "'", null);
        objCursor.moveToFirst();
        objCursor.moveToPosition(position);
        //Log.d(tag, "Position = " + position);
       // Log.d(tag, "ID = " + objCursor.getString(objCursor.getColumnIndex(timeTABEL.COLUMN_ID)));

        return Integer.parseInt(objCursor.getString(objCursor.getColumnIndex(MyManage.column_date)));
    }*/


    private String[] findDate(String[] dateStrings) {

        stringArrayList = new ArrayList<String>();
        for (int i = 0; i < dateStrings.length; i++) {
            stringArrayList.add(dateStrings[i]);

        }   // For
        Object[] objects = stringArrayList.toArray();
        for (Object myObj : objects) {
            if (stringArrayList.indexOf(myObj) != stringArrayList.lastIndexOf(myObj)) {
                stringArrayList.remove(stringArrayList.lastIndexOf(myObj));
            } // if

        }   // For myObj



            return new String[0];
    }


} // Main Class
