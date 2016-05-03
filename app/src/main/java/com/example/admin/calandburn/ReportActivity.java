package com.example.admin.calandburn;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportActivity extends AppCompatActivity {

    //Explicit
    private TextView dateTextView, calaryTextView, burnTextView, BMRTextView, sumCalorie;
    private ImageView faceImageView;
    private ListView calaryListView, burnListView;
    private Button historyButton, editButton;
    private Double caloryADouble, burnADouble, factorADouble, bmrADouble, sumBmrADouble;
    private SQLiteDatabase sqLiteDatabase;
    private String lastDateString, bmruserString, factorString, sumBmrString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //Bind Widget
        bindWidget();

        //Read All calaryTABLE
        readAllCalary();

        //Read All burnTABLE
        readAllBurn();

        //Find BMRUser
        findBMRUser();

        //Read Where on Date of calaryTABLE
        whereCalaryTABLE();

        //Read Where on Date of burnTABLE
        whereBurnTABLE();

        //Show Result Cal
        showResultCal();

    }   // Main Method

    private void showResultCal() {

        double douCal = Double.parseDouble(calaryTextView.getText().toString());
        double douBurn = Double.parseDouble(burnTextView.getText().toString());

        double douSumCalorie = douCal - douBurn;
        TextView textView = (TextView) findViewById(R.id.textView35);
        textView.setText(Double.toString(douSumCalorie));

    }


    private void whereBurnTABLE() {

        Cursor whereBurnCursor = sqLiteDatabase.rawQuery("SELECT * FROM burn_table WHERE Date = " + "'" + lastDateString + "'", null);
        whereBurnCursor.moveToFirst();
        int intCount = whereBurnCursor.getCount();
        String[] calBurnStrings = new String[intCount];
        double sumCalBurn = 0;

        for (int i=0;i<intCount;i++) {
            calBurnStrings[i] = whereBurnCursor.getString(whereBurnCursor.getColumnIndex(MyManage.column_CalBurn));
            sumCalBurn = sumCalBurn + Double.parseDouble(calBurnStrings[i]);
        }   //for
        whereBurnCursor.close();
        burnTextView.setText(Double.toString(sumCalBurn));

    }   // whereBurn

    private void whereCalaryTABLE() {

        Cursor whereCalaryCursor = sqLiteDatabase.rawQuery("SELECT * FROM calary_table WHERE Date = " + "'"
                + lastDateString + "'" + "ORDER BY Date" , null);
        whereCalaryCursor.moveToFirst();
        int intCount = whereCalaryCursor.getCount();
        String[] calFoodStrings = new String[intCount];
        double sumCalFood = 0;

        for (int i=0;i<intCount;i++) {

            calFoodStrings[i] = whereCalaryCursor.getString(whereCalaryCursor.getColumnIndex(MyManage.column_CalFood));
            sumCalFood = sumCalFood + Double.parseDouble(calFoodStrings[i]);

            whereCalaryCursor.moveToNext();
        }   // for

        whereCalaryCursor.close();
        calaryTextView.setText(Double.toString(sumCalFood));

        calaryListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                sqLiteDatabase.execSQL("DELETE FROM calary_table WHERE _id='6'");


                return true;
            }
        });

    }   // whereCalary

    private void findBMRUser() {

        Cursor bmrCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + UserTABLE.TABLE_USER, null);
        bmrCursor.moveToFirst();

        bmruserString = bmrCursor.getString(bmrCursor.getColumnIndex(UserTABLE.COLUMN_bmruser));
        factorString = bmrCursor.getString(bmrCursor.getColumnIndex(UserTABLE.COLUMN_factor));
        factorADouble = Double.parseDouble(factorString);
        bmrADouble = Double.parseDouble(bmruserString);
        sumBmrADouble = bmrADouble * factorADouble;
        String sumBmrADoubles = String.format("%.2f", sumBmrADouble);
        sumBmrString = sumBmrADoubles;
        BMRTextView.setText(sumBmrString);
        bmrCursor.close();

    }   // find BMRUser set TextView

    private void readAllCalary() {

        sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c.getTime());
        Cursor calaryCursor = sqLiteDatabase.rawQuery("SELECT * FROM calary_table WHERE Date = '"+formattedDate+"'", null);
        calaryCursor.moveToFirst();
        if(calaryCursor.getCount()<=0){
            String[] foodCalaryStrings = new String[1];
            foodCalaryStrings[0]="ไม่มีข้อมูล";
            String[] amountCalaryStrings = new String[1];
            foodCalaryStrings[0]=" ";
            String[] calCalaryStrings = new String[1];
            foodCalaryStrings[0]=" ";
            String[] dateCalaryStrings = new String[1];
            dateCalaryStrings[0]=" ";
            MyShowAct myShowAct = new MyShowAct(ReportActivity.this, foodCalaryStrings, dateCalaryStrings, amountCalaryStrings, calCalaryStrings);
            calaryListView.setAdapter(myShowAct);
        }
        else {
            String[] idCalStrings = new String[calaryCursor.getCount()];
            String[] dateCalaryStrings = new String[calaryCursor.getCount()];
            String[] foodCalaryStrings = new String[calaryCursor.getCount()];
            String[] amountCalaryStrings = new String[calaryCursor.getCount()];
            String[] calCalaryStrings = new String[calaryCursor.getCount()];
            int[] iconInts = new int[calaryCursor.getCount()];

            for (int i = 0; i < calaryCursor.getCount(); i++) {

                dateCalaryStrings[i] = calaryCursor.getString(calaryCursor.getColumnIndex(MyManage.column_date));
                foodCalaryStrings[i] = calaryCursor.getString(calaryCursor.getColumnIndex(MyManage.column_Food));
                amountCalaryStrings[i] = calaryCursor.getString(calaryCursor.getColumnIndex(MyManage.column_Amount));
                calCalaryStrings[i] = calaryCursor.getString(calaryCursor.getColumnIndex(MyManage.column_CalFood));
                iconInts[i] = R.drawable.icon_myaccount;
                Log.d("data=", dateCalaryStrings[i]);
                Log.d("food=", foodCalaryStrings[i]);
                Log.d("amou=", amountCalaryStrings[i]);
                Log.d("cal=", calCalaryStrings[i]);
                calaryCursor.moveToNext();
            }   //for


            lastDateString = dateCalaryStrings[calaryCursor.getCount() - 1];
            dateTextView.setText(lastDateString);

            calaryCursor.close();

            //Create ListView
            MyShowAct myShowAct = new MyShowAct(ReportActivity.this, foodCalaryStrings, dateCalaryStrings, amountCalaryStrings, calCalaryStrings);
            calaryListView.setAdapter(myShowAct);
        }

    }   // readAllCalary

    private void readAllBurn() {

        sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c.getTime());
        Cursor calaryCursor = sqLiteDatabase.rawQuery("SELECT * FROM burn_table WHERE Date = '"+formattedDate+"'", null);


            calaryCursor.moveToFirst();
        if(calaryCursor.getCount()<=0){
            String[] excCalaryStrings = new String[1];
            excCalaryStrings[0]=" ";
            String[] hourCalaryStrings = new String[1];
            hourCalaryStrings[0]=" ";
            String[] calBurnCalaryStrings = new String[1];
            calBurnCalaryStrings[0]=" ";
            String[] dateCalaryStrings = new String[1];
            dateCalaryStrings[0]="ไม่มีข้อมูล";
            MyShowAct myShowAct = new MyShowAct(ReportActivity.this, excCalaryStrings, dateCalaryStrings, hourCalaryStrings, calBurnCalaryStrings);
            burnListView.setAdapter(myShowAct);
        }
        else {
            String[] dateCalaryStrings = new String[calaryCursor.getCount()];
            String[] excCalaryStrings = new String[calaryCursor.getCount()];
            String[] hourCalaryStrings = new String[calaryCursor.getCount()];
            String[] calBurnCalaryStrings = new String[calaryCursor.getCount()];

            for (int i = 0; i < calaryCursor.getCount(); i++) {

                dateCalaryStrings[i] = calaryCursor.getString(calaryCursor.getColumnIndex(MyManage.column_date));
                excCalaryStrings[i] = calaryCursor.getString(calaryCursor.getColumnIndex(MyManage.column_Exercise));
                hourCalaryStrings[i] = calaryCursor.getString(calaryCursor.getColumnIndex(MyManage.column_Hour));
                calBurnCalaryStrings[i] = calaryCursor.getString(calaryCursor.getColumnIndex(MyManage.column_CalBurn));

                Log.d("data=", dateCalaryStrings[i]);
                Log.d("food=", excCalaryStrings[i]);
                Log.d("amou=", hourCalaryStrings[i]);
                Log.d("cal=", calBurnCalaryStrings[i]);
                calaryCursor.moveToNext();
            }   //for


            lastDateString = dateCalaryStrings[calaryCursor.getCount() - 1];
            dateTextView.setText(lastDateString);

            calaryCursor.close();

            //Create ListView
            MyShowAct myShowAct = new MyShowAct(ReportActivity.this, excCalaryStrings, dateCalaryStrings, hourCalaryStrings, calBurnCalaryStrings);
            burnListView.setAdapter(myShowAct);
        }


    }   // readAllBurn

    private void bindWidget() {



        dateTextView = (TextView) findViewById(R.id.textView25);
        BMRTextView = (TextView) findViewById(R.id.textView29);
        calaryTextView = (TextView) findViewById(R.id.textView26);
        burnTextView = (TextView) findViewById(R.id.textView27);
        faceImageView = (ImageView) findViewById(R.id.imvFace);
        calaryListView = (ListView) findViewById(R.id.listView3);
        burnListView = (ListView) findViewById(R.id.listView4);
        historyButton = (Button) findViewById(R.id.button4);
        editButton = (Button) findViewById(R.id.button5);


    }   // bindWidget

    public void onHisFoodClick(View view) {

        Intent hisfood = new Intent(this, HisFoodData.class);
        startActivity(hisfood);

    }

    public void onCalClick(View v){
        //Intent i = new Intent(getApplicationContext(),MainActivity.class);
        //startActivity(i);
        this.finish();
    }
}   // Main Class
