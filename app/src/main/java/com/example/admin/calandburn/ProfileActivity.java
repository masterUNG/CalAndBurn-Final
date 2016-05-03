package com.example.admin.calandburn;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    // Explicit
    private TextView nameTextView, sexTextView, ageTextView, standardTextView,
            heightTextView, weightTextView, actTextView, bmiTextView,
            weightStdTextView, bmrTextView;
    private String nameString, sexString, ageString, heightString, weightString,
             bmiString, weightStdString, bmrString, factorString, sumBmrString, idString;
    private int actAnInt, standardAnInt;
    private double BmrADouble, FactorADouble, sumBmrADouble;
    private Spinner myACTSpinner;
    private String[] choiceStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Bind Widget

        bindWidget();

        // Show View
        showView();




    } // Main Method

    @Override
    protected void onRestart() {
        super.onRestart();
        showView();
    }

    private void showView() {



        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM " + UserTABLE.TABLE_USER, null);
        objCursor.moveToFirst();
        nameString = objCursor.getString(objCursor.getColumnIndex(UserTABLE.COLUMN_name));
        sexString = objCursor.getString(objCursor.getColumnIndex(UserTABLE.COLUMN_sex));
        ageString = objCursor.getString(objCursor.getColumnIndex(UserTABLE.COLUMN_age));
        heightString = objCursor.getString(objCursor.getColumnIndex(UserTABLE.COLUMN_height));
        weightString = objCursor.getString(objCursor.getColumnIndex(UserTABLE.COLUMN_weight));
        actAnInt = objCursor.getInt(objCursor.getColumnIndex(UserTABLE.COLUMN_myact));
        bmiString = objCursor.getString(objCursor.getColumnIndex(UserTABLE.COLUMN_bmiuser));
        weightStdString = findMyAlertWeight(bmiString);
        bmrString = objCursor.getString(objCursor.getColumnIndex(UserTABLE.COLUMN_bmruser));
        factorString = objCursor.getString(objCursor.getColumnIndex(UserTABLE.COLUMN_factor));
        BmrADouble=Double.parseDouble(bmrString);
        FactorADouble = Double.parseDouble(factorString);
        sumBmrADouble = BmrADouble * FactorADouble;
        String sumBmrADoubles = String.format("%.2f", sumBmrADouble);
        sumBmrString = sumBmrADoubles;


        if(actAnInt==1){
            actTextView.setText("นั่งทำงาน ไม่ได้ออกกำลังกาย");
        }
        else if(actAnInt==2)
        {
            actTextView.setText("ออกกำลังกายเล็กน้อย 1-3 วัน");
        }
        else if(actAnInt==3)
        {
            actTextView.setText("ออกกำลังกายปานกลาง 3-5 วัน");
        }
        else if(actAnInt==4)
        {
            actTextView.setText("ออกกำลังกายสม่ำเสมอ 6-7 วัน");
        }
        else
        {
            actTextView.setText("ออกกำลังกายอย่างหนักเช้าเย็น");
        } //spin Activity
        Log.d("gg=", String.valueOf(actAnInt));

        objCursor.close();
        nameTextView.setText(nameString);
        sexTextView.setText(sexString);
        ageTextView.setText(ageString);
        heightTextView.setText(heightString + " cm.");
        weightTextView.setText(weightString + " kg.");

        bmiTextView.setText(bmiString);
        weightStdTextView.setText(weightStdString);
        bmrTextView.setText(sumBmrString);

    } // Show View

    private String findMyAlertWeight(String bmiString) {

        String[] resultStrings = getResources().getStringArray(R.array.my_alert);
        String myResult = null;
        double douBMI = Double.parseDouble(bmiString);

        if (douBMI < 18.5) {
            myResult = resultStrings[0];
        } else if (douBMI < 22.9) {
            myResult = resultStrings[1];
        } else if (douBMI < 24.0) {
            myResult = resultStrings[2];
        } else if (douBMI < 29.9) {
            myResult = resultStrings[3];
        } else if (douBMI < 39.9) {
            myResult = resultStrings[4];
        } else {
            myResult = resultStrings[5];
        }

        return myResult;
    }

    private void bindWidget() {

        nameTextView = (TextView) findViewById(R.id.textView2);
        sexTextView = (TextView) findViewById(R.id.textView4);
        ageTextView = (TextView) findViewById(R.id.textView6);
        heightTextView = (TextView) findViewById(R.id.textView8);
        weightTextView = (TextView) findViewById(R.id.textView10);
        actTextView = (TextView) findViewById(R.id.textView12);
        bmiTextView = (TextView) findViewById(R.id.textView14);
        weightStdTextView = (TextView) findViewById(R.id.textView16);
        bmrTextView = (TextView) findViewById(R.id.textView18);
        myACTSpinner = (Spinner) findViewById(R.id.spinner2);

    } // Main Method

    public void clickMainMenu(View view) {
        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        finish();
    } //Click Main Manu

    public void onClickEditProfile(View view) {
        Intent edit = new Intent(this, EditProfile.class);
        startActivity(edit);
    } //Click Edit Profile

} // Main Class
