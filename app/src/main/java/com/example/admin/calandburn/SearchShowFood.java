package com.example.admin.calandburn;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchShowFood extends AppCompatActivity {

    private String searchString;
    private String[] strName ;
    private ListView searchListView;
    private SearchFood objSearchFood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_show_food);
        searchListView = (ListView) findViewById(R.id.searchfood);
        Intent extras = getIntent();
        String text = extras.getStringExtra("test");
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM foodTABLE WHERE namefood LIKE " + "'" + text + "%'", null);
        // strName = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {

           strName[i] = cursor.getString(cursor.getColumnIndex(FoodTABLE.COLUMN_NAME_FOOD));

            cursor.moveToNext();
        }//for

        cursor.close();
       // strName="ddddd";
        listviewShowSearch objNameAdapter = new listviewShowSearch (SearchShowFood.this, strName);
        searchListView.setAdapter(objNameAdapter);
        //objSearchFood=new SearchFood();
        /*String[][] arrData = null;
        String[] intIcon = new String[arrData.length];
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM foodTABLE WHERE namefood LIKE " + "'" + searchString + "%'", null);
        if(cursor != null)
        {
            if (cursor.moveToFirst()) {
                arrData = new String[cursor.getCount()][cursor.getColumnCount()];
                int i= 0;
                do {
                    arrData[i][1] = (cursor.getString(1));

                    //arrData[i][5] = (cursor.getDouble(5));
                    listviewShowSearch objMyAdapter = new listviewShowSearch(SearchShowFood.this,arrData[i]);

                    searchListView.setAdapter(objMyAdapter);

                    i++;
                } while (cursor.moveToNext());

            }
        }*/


    }
}
