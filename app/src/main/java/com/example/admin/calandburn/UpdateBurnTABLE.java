package com.example.admin.calandburn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateBurnTABLE extends AppCompatActivity {

    //Explicit
    private EditText hourEditText, minEditText;
    private String fullHourString, exterciseString, callBurnString, dateString;
    private TextView dateTextView, actTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_burn_table);

        //Bind Widget
        bindWidget();

        //Show Date&Act
        showdateAnAct();

    }   // Main Method

    private void showdateAnAct() {

        exterciseString = getIntent().getStringExtra("Act");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        dateString = dateFormat.format(date);

        dateTextView.setText(dateString);
        actTextView.setText(exterciseString);


    }   // showdateAnAct

    public void clickSaveUpdate(View view) {

        String strHour = hourEditText.getText().toString().trim();
        String strMinus = minEditText.getText().toString().trim();

        double douHour, douCalBurn;
        double douFactor = Double.parseDouble(getIntent().getStringExtra("Factor"));

        if (checkHrMin(strHour, strMinus)) {
            Toast.makeText(UpdateBurnTABLE.this, "กรุณาระบุเวลาที่ใช้", Toast.LENGTH_SHORT).show();
        } else {
            douHour = calculateHour(strHour, strMinus);
            fullHourString = String.format("%.2f", douHour);
            Log.d("5MarchV2", "douHour ==> " + douHour);
            douCalBurn = douHour * douFactor;
            callBurnString = String.format("%.2f", douCalBurn);

            updateToBurnTable();

        }

    }   // clickSaveUpdate

    private void updateToBurnTable() {

        MyManage myManage = new MyManage(this);
        myManage.addBurn(dateString, exterciseString, fullHourString, callBurnString);
        Toast.makeText(UpdateBurnTABLE.this,
                "ได้บันทึก " + exterciseString + " เรียบร้อยแล้ว ",
                Toast.LENGTH_SHORT).show();
        finish();

    }   // updateToBurnTable

    private double calculateHour(String strHour, String strMinus) {

        if (strHour.equals("")) {
            strHour = "0";
        }

        if (strMinus.equals("")) {
            strMinus = "0";
        }

        double douHour, douMin, douAnswer;
        douHour = Double.parseDouble(strHour);
        douMin = Double.parseDouble(strMinus);
        douAnswer = douHour + (douMin/60);

        return douAnswer;
    }

    private boolean checkHrMin(String strHour, String strMinus) {

        boolean bolStatus = false;  // true ให้กรองใหม่, false ไม่ต้อง

        if (strHour.equals("")) {

            if (strMinus.equals("")) {
                bolStatus = true;
            } else {
                bolStatus = false;
            }

        }   // if

        return bolStatus;
    }

    private void bindWidget() {
        hourEditText = (EditText) findViewById(R.id.editText5);
        minEditText = (EditText) findViewById(R.id.editText6);
        dateTextView = (TextView) findViewById(R.id.textView19);
        actTextView = (TextView) findViewById(R.id.textView24);
    }

}   //  Main Class
