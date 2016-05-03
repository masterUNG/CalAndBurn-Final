package com.example.admin.calandburn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by masterUNG on 5/3/16 AD.
 */
public class HistoryAdapter extends BaseAdapter{

    //Explicit
    private Context context;
    private String[] foodStrings, amountStrings, calStrings;

    public HistoryAdapter(Context context,
                          String[] foodStrings,
                          String[] amountStrings,
                          String[] calStrings) {
        this.context = context;
        this.foodStrings = foodStrings;
        this.amountStrings = amountStrings;
        this.calStrings = calStrings;
    }

    @Override
    public int getCount() {
        return foodStrings.length;
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

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.history_listview, viewGroup, false);

        TextView foodTextView = (TextView) view1.findViewById(R.id.textView71);
        foodTextView.setText(foodStrings[i]);

        TextView amountTextView = (TextView) view1.findViewById(R.id.textView72);
        amountTextView.setText(amountStrings[i]);

        TextView calTextView = (TextView) view1.findViewById(R.id.textView73);
        calTextView.setText(calStrings[i]);

        return view1;
    }
}   // Main Class
