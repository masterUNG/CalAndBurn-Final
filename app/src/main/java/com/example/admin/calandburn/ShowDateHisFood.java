package com.example.admin.calandburn;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowDateHisFood extends AppCompatActivity {

    private TextView dateTextView, totalCalTextView;
    private ListView dateListView;
    private CalaryTable objCalaryTable;

    private String strDate1;
    private String[] strDate = null;
    private String[] strName = null;
    private String[] amountStrings, calStrings;
    private double totalCalADouble = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_date_his_food);

        bindWidter();

        showDate();

        createListDailyPlan();
    }   // Main Method

    public void clickBackShowDateHisFood(View view) {
        finish();
    }

    private void createListDailyPlan() {
        objCalaryTable=new CalaryTable(this);
        try {

            SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);
            //สร้าง Cursor ที่ Column date = วันที่ ที่ถูก Intent มา
            Cursor objcursor = objSqLiteDatabase.rawQuery("SELECT * FROM calary_table WHERE Date = " + "'" + strDate1 + "'", null);
            objcursor.moveToFirst();

            strName = new String[objcursor.getCount()];
            strDate = new String[objcursor.getCount()];
            amountStrings = new String[objcursor.getCount()];
            calStrings = new String[objcursor.getCount()];

            for (int i = 0; i < objcursor.getCount(); i++) {

                strName[i] = objcursor.getString(objcursor.getColumnIndex("Food"));
                strDate[i] = objcursor.getString(objcursor.getColumnIndex("Date"));
                amountStrings[i] = objcursor.getString(objcursor.getColumnIndex("Amount"));
                calStrings[i] = objcursor.getString(objcursor.getColumnIndex("CalFood"));

                totalCalADouble = totalCalADouble + Double.parseDouble(calStrings[i]);

                objcursor.moveToNext();
            }//for

            objcursor.close();

            totalCalTextView.setText("Total = " + Double.toString(totalCalADouble) + " cal");

            HistoryAdapter historyAdapter = new HistoryAdapter(this,
                    strName, amountStrings, calStrings);
           dateListView.setAdapter(historyAdapter);



        }catch (Exception e) {
            Toast.makeText(ShowDateHisFood.this, "วันนี้ไม่มีงานอะไร", Toast.LENGTH_LONG).show();
        }
    }



    private void showDate() {
        strDate1 = getIntent().getStringExtra("Date");
        dateTextView.setText(strDate1);
    }

    private void bindWidter() {
        dateTextView = (TextView) findViewById(R.id.textView70);
        dateListView = (ListView) findViewById(R.id.listView5);
        totalCalTextView = (TextView) findViewById(R.id.textView74);

    }
} // Main Class
