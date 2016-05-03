package com.example.admin.calandburn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputProfile extends AppCompatActivity {

    private UserTABLE objUserTABLE;
    private EditText nameEditText, ageEditText, heightEditText, weightEditText;
    private RadioGroup sexRadioGroup;
    private RadioButton maleRadioButton, femaleRadioButton;
    private Spinner myACTSpinner;
    private String nameString;
    private String ageString;
    private String heightString;
    private String weightString;
    private String sexString = "male";
    private String actString;
    private String factorString;
    private String dateString;
    private String bmiString;
    private String bmrString;
    private int idString;
    private String[] choiceStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_profile);

        //Bind Widget
        bindWidget();

        connectDataBase();

        //Create Spinner
        createSpinner();

        // Sex RadioButton
        sexRadio();

    }   // onCreateๅ123

    private void sexRadio() {

        sexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton:
                        sexString = "male";
                        break;
                    case R.id.radioButton2:
                        sexString = "female";
                        break;
                }
            }
        });

    } // sexRadio

    private void createSpinner() {

        choiceStrings = getResources().getStringArray(R.array.my_act);

        final String[] factorStrings = {"1","1.2", "1.375", "1.55", "1.725", "1.9"};
        final int[] idStrings = {0,1,2,3,4,5};

        ArrayAdapter<String> objArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, choiceStrings);
        myACTSpinner.setAdapter(objArrayAdapter);

        myACTSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                actString = choiceStrings[position];
                factorString = factorStrings[position];
                idString = idStrings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                actString = choiceStrings[0];
                factorString = factorStrings[0];
                //idString = idStrings[0];
            }
        });

    }   // createSpinner

    private void bindWidget() {

        nameEditText = (EditText) findViewById(R.id.editText);
        ageEditText = (EditText) findViewById(R.id.editText2);
        heightEditText = (EditText) findViewById(R.id.editText3);
        weightEditText = (EditText) findViewById(R.id.editText4);
        sexRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        maleRadioButton = (RadioButton) findViewById(R.id.radioButton);
        femaleRadioButton = (RadioButton) findViewById(R.id.radioButton2);
        myACTSpinner = (Spinner) findViewById(R.id.spinner2);

    }

    private void connectDataBase() {
        objUserTABLE = new UserTABLE(this);
    }


    public void onSaveDataClick(View v) {

        nameString = nameEditText.getText().toString().trim();
        ageString = ageEditText.getText().toString().trim();
        heightString = heightEditText.getText().toString().trim();
        weightString = weightEditText.getText().toString().trim();

        // check Complete
        if (checkSpace() || checkSpinner() ) {

            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(InputProfile.this,
                    "ข้อมูลไม่ครบถ้วน",
                    "กรุณากรอกข้อมูลให้ครบ");


        } else {

            // Complete
            confirmProfile();

        } //if

    }   // onSaveData

    private void confirmProfile() {
        // find CurrentDate
        DateFormat myDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date currentDate = new Date();
        dateString = myDateFormat.format(currentDate);
        Log.d("cal", "Date = " + dateString);

        // Find BMI
        double douWeight = Double.parseDouble(weightString);
        double douHeight = Double.parseDouble(heightString);
        double douAge = Double.parseDouble(ageString);

        double douBMI = douWeight / (Math.pow(douHeight/100, 2));
      //  bmiString = Double.toString(douBMI);
        bmiString = String.format("%.2f", douBMI);
        Log.d("cal", "Weight = " + douWeight);
        Log.d("cal", "Height = " + douHeight);
        Log.d("cal", "BMI = " + bmiString);

        // Find BMR
        double douBMR = 0;
        switch (MaleOrFemale()) {
            case 0: // male
                douBMR = 66 + (13.7 * douWeight) + (5 * douHeight) - (6.8 * douAge);
                break;
            case 1: // female
                douBMR = 665 + (9.6 * douWeight) + (1.8 * douHeight) - (4.7 * douAge);
                break;
        } // switch


        bmrString = String.format("%.2f", douBMR);

        objUserTABLE.addValueToUser(dateString,
                nameString,
                sexString,
                ageString,
                heightString,
                weightString,
                idString,
                factorString,
                bmiString,
                bmrString);

        Intent objIntent = new Intent(InputProfile.this, ProfileActivity.class);
        startActivity(objIntent);
        finish();


    } // confirm

    private int MaleOrFemale() {

        int intResult = 0;
        if (sexString.equals("male")) {
            intResult = 0;
        } else {
            intResult = 1;
        }

        return intResult;
    }

    private boolean checkSpinner() {

        boolean bolSpinner = true;

        if (actString.equals(choiceStrings[0])) {

            bolSpinner = true;

        } else {

            bolSpinner = false;

        }

        return bolSpinner;
    }

    private boolean checkSpace() {
        return nameString.equals("") ||
                ageString.equals("") ||
                heightString.equals("") ||
                weightString.equals("");
    }


}   // Main Class
