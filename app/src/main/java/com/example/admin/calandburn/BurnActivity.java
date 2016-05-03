package com.example.admin.calandburn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class BurnActivity extends AppCompatActivity {

    // Explicit
    private ActivityTABLE objActivityTABLE;
    private ListView actListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burn);

        actListView = (ListView) findViewById(R.id.listView2);

        connectDataBase();

        createListView();
    } // On Create

    private void createListView() {

        final String[] strNameAct = objActivityTABLE.readAllDataActivity(1);
        final String[] strBurnAct = objActivityTABLE.readAllDataActivity(2);
        String[] strAboutAct = objActivityTABLE.readAllDataActivity(3);

        int[] intIcon = new int[strAboutAct.length];
        for (int i = 0; i < strAboutAct.length; i++) {



            if (strAboutAct[i].equals(" 1")) {
                intIcon[i] = R.drawable.burn1;
                Log.d("5March", "strAboutAct[" + i + "] = " + strAboutAct[i]);
            } else {
                intIcon[i] = R.drawable.burn2;
            }   // if

        } //for

        MyActList objMyActList = new MyActList(BurnActivity.this, strNameAct,
                strBurnAct, intIcon);
        actListView.setAdapter(objMyActList);

        actListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                myAlertDialog(strNameAct[position], strBurnAct[position]);

            } // Event
        });



    } // Create ListView



    private void myAlertDialog(final String strAct, final String strFactor) {

        Intent intent = new Intent(this, UpdateBurnTABLE.class);
        intent.putExtra("Act", strAct);
        intent.putExtra("Factor", strFactor);
        startActivity(intent);

    } // myAlertDialog

    private void connectDataBase() {
        objActivityTABLE = new ActivityTABLE(this);
    }

    public void backMainClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

} // Main Class
