package com.example.admin.calandburn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectCakeFood extends AppCompatActivity {

    private FoodTABLE objCakeFoodTABLE;
    private ListView cakeListView;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_cake_food);

        cakeListView = (ListView) findViewById(R.id.cakelistview);

        connectDataBase();

        createCakeListView();


    } // Main Method

    private void createCakeListView() {

        final String[] strTitle = objCakeFoodTABLE.readAllDataDessert(1);
        final String[] strDetail = objCakeFoodTABLE.readAllDataDessert(2);
        String[] strIcon = objCakeFoodTABLE.readAllDataDessert(3);
        int[] intIcon = new int[strIcon.length];
        for (int i = 0; i < strIcon.length; i++) {

                intIcon[i] = R.drawable.cake;

        }   //for

        MyAdapter objMyAdapter = new MyAdapter(SelectCakeFood.this, strTitle, strDetail, intIcon);
        cakeListView.setAdapter(objMyAdapter);

        cakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                myAlertDialog(strTitle[i], strDetail[i]);

            }   // event
        });
    }

    private void connectDataBase() {
        objCakeFoodTABLE = new FoodTABLE(this);
    }

    private void myAlertDialog(final String strFood, final String strFactor) {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);

        DateFormat myDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        final String strDate = myDateFormat.format(currentDate);  // strDate

        final EditText objEditText = new EditText(SelectCakeFood.this);
        objEditText.setInputType(InputType.TYPE_CLASS_NUMBER);

        objBuilder.setTitle("วันที่ " + strDate);
        objBuilder.setMessage(strFood + "\n\n" + " 1 หน่วย" + " = " + strFactor +" calorie" +"\n"
                + "ใส่จำนวนบริโภค :");

        objBuilder.setView(objEditText);

        objBuilder.setPositiveButton("เพิ่ม", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                try {

                    String strAmount = objEditText.getText().toString().trim();
                    confirmAlertDialog(strDate, strFood, strFactor, strAmount);
                    dialogInterface.dismiss();

                } catch (Exception e) {
                    Toast.makeText(SelectCakeFood.this, "กรุณาระบุจำนวน", Toast.LENGTH_SHORT).show();
                }

            }   // event
        });
        objBuilder.show();

    }   // myAlertDialog

    private void confirmAlertDialog(final String strDate, final String strFood, String strFactor, final String strAmount) {

        double douFactor = Double.parseDouble(strFactor);
        double douAmount = Double.parseDouble(strAmount);
        double douAnswer = douFactor * douAmount;
        final String strAnswer = Double.toString(douAnswer);

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setTitle(strFood);
        objBuilder.setMessage(strFactor + " x " + strAmount + " = " + strAnswer + " calorie");
        objBuilder.setPositiveButton("เพิ่ม", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                updateCalaryTABLE(strDate, strFood, strAmount, strAnswer);

                dialogInterface.dismiss();
            }
        });
        objBuilder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        objBuilder.show();

    }   // confirmAlertDialog

    private void updateCalaryTABLE(String strDate,
                                   String strFood,
                                   String strAmount,
                                   String strAnswer) {

        MyManage myManage = new MyManage(this);
        myManage.addCalary(strDate, strFood, strAmount, strAnswer);
        Toast.makeText(SelectCakeFood.this, "บันทึกรายการ " + strFood + " เรียบร้อยแล้ว", Toast.LENGTH_SHORT).show();

    }
    public void onCalClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

} //Main Class
