package com.example.admin.calandburn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    }

    public void onBMIClick(View view) {
        Intent BMI = new Intent(this, InfoBMI.class);
        startActivity(BMI);
    }

    public void onBMRClick(View view) {
        Intent BMI = new Intent(this, infoBMR.class);
        startActivity(BMI);
    }

    public void onMainMenuClick(View v){
        //Intent i = new Intent(getApplicationContext(),MainActivity.class);
        //startActivity(i);
        this.finish();
    }
}
