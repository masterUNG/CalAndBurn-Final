package com.example.admin.calandburn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Admin on 31/3/2559.
 */
public class InfoBMI extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor_bmi);
    }

    public void onClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
