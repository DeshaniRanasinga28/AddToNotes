package com.jkit.mynotesapplication.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.jkit.mynotesapplication.R;

public class CustomCursorAdapter extends CursorAdapter {

    public CustomCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_items, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView idTxtView = (TextView)view.findViewById(R.id.idTxtView);
        idTxtView.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));

        TextView titleTxtView = (TextView)view.findViewById(R.id.titleTxtView);
        titleTxtView.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));

        TextView notesTxtView = (TextView)view.findViewById(R.id.noteTxtView);
        notesTxtView.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));

    }








}
