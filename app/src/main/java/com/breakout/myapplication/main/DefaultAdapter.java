package com.breakout.myapplication.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.breakout.myapplication.R;

import java.util.List;

public class DefaultAdapter extends BaseAdapter {

    List<NavElement> elements;

    LayoutInflater inflater;
    DefaultAdapter(Context context, List<NavElement> elements) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.elements = elements;
    }

    @Override
    public int getCount() { return elements.size(); }

    @Override
    public Object getItem(int position) {
        return elements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.list_default_lay, parent, false);
        ((TextView) convertView.findViewById(R.id.nav_text)).setText(elements.get(position).getTitle());
        ((ImageView) convertView.findViewById(R.id.nav_img)).setImageResource(elements.get(position).getImage());
        return convertView;
    }



}
