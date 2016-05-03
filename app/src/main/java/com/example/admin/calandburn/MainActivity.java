package com.example.admin.calandburn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    //Explicit
    private UserTABLE objUserTABLE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create & Connected
        objUserTABLE = new UserTABLE(this);

        //Check userTABLE
        checkUserTABLE();

        //กำหนด Listener ให้กับปุ่ม "Information"
        ImageButton btnProfile = (ImageButton) findViewById(R.id.pro1);
        btnProfile.setOnClickListener(this);

    } //onCreate

    @Override
    protected void onRestart() {
        super.onRestart();

        checkUserTABLE();

    }

    private void checkUserTABLE() {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM userTABLE", null);
        if (objCursor.getCount() <= 0) {

            Log.d("cal1", "objCursor = null");
            Intent objIntent = new Intent(MainActivity.this, InputProfile.class);
            startActivity(objIntent);

        } else {
            objCursor.close();
            Log.d("cal1", "objCursor = Have Data");
        }

    }   // checkUserTABLe


    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }   // ข้อมูลส่วนตัวผู้ใช้

    public void onInforClicked(View v) {
        Intent i = new Intent(getApplicationContext(), Information.class);
        startActivity(i);
    }   // ข้อมูลน่ารู้

    public void onCalClick(View v) {
        Intent c = new Intent(getApplicationContext(), SearchFood.class);
        startActivity(c);
    }   // อาหาร

    public void onBurnClick(View v) {
        Intent b = new Intent(getApplicationContext(), BurnActivity.class);
        startActivity(b);
    }   // กิจกรรม

    public void onReportClick(View v) {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c.getTime());
        Cursor calaryReportCursor = objSqLiteDatabase.rawQuery("SELECT * FROM calary_table", null);
        Cursor burnReportCursor = objSqLiteDatabase.rawQuery("SELECT * FROM burn_table", null);
        Cursor calaryDateReportCursor = objSqLiteDatabase.rawQuery("SELECT * FROM calary_table WHERE Date = '" + formattedDate + "'", null);
        Cursor burnDateReportCursor = objSqLiteDatabase.rawQuery("SELECT * FROM burn_table WHERE Date = '" + formattedDate + "'", null);
        if (calaryReportCursor.getCount() <= 0&&burnReportCursor.getCount() <= 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("ไม่มีรายการที่กระทำในขณะนี้");
            builder.setCancelable(false);
            builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
        else if(calaryDateReportCursor.getCount() <= 0&&burnDateReportCursor.getCount() <= 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("ไม่มีรายการที่กระทำในวันนี้");
            builder.setCancelable(false);
            builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();}
        else if(((calaryDateReportCursor.getCount() <= 0)&&(burnDateReportCursor.getCount() >0))||((calaryDateReportCursor.getCount() <= 0)&&(burnDateReportCursor.getCount() >0))){
            Intent r = new Intent(getApplicationContext(), ReportActivity.class);
            startActivity(r);}
        else  {
            Intent r = new Intent(getApplicationContext(), ReportActivity.class);
            startActivity(r);
        }
        }   // รายงาน
} // MainClass
