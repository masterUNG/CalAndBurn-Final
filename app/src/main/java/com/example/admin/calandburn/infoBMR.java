package com.example.admin.calandburn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class infoBMR extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_bmr);
    }
    public void onClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
