package com.example.admin.calandburn;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class listviewShowSearch extends BaseAdapter {

    private Context objContext;
    private String[] titleHead;
    private int[] iconInts;


    public listviewShowSearch(Context objContext, String[] titleHead) {
        this.objContext = objContext;
        this.titleHead = titleHead;
        this.iconInts = iconInts;

    }   // Constructor

    @Override
    public int getCount() {
        return titleHead.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View objView1 = objLayoutInflater.inflate(R.layout.listviewshowsearch, viewGroup, false);

        //Setup Title
        TextView titleTextView = (TextView) objView1.findViewById(R.id.idhead);
        titleTextView.setText(titleHead[i]);



        return objView1;
    }
}
