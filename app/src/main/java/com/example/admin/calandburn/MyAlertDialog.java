package com.example.admin.calandburn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Admin on 5/2/2559.
 */
public class MyAlertDialog {

    public void myDialog(Context context, String strTitle, String strMessage) {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(context);
        objBuilder.setIcon(R.drawable.icon_myaccount);
        objBuilder.setTitle(strTitle);
        objBuilder.setMessage(strMessage);
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objBuilder.show();

    } // myDialog

} // main class
