package com.pnp.phongphung.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by PhongPhung on 4/4/2018.
 */

public class CustomAdapter extends BaseAdapter{
    Context context;
    ArrayList<ThoiTiet> arrayList;

    public CustomAdapter(Context context, ArrayList<ThoiTiet> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
       return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.dong_listview,null);

        ThoiTiet thoiTiet = arrayList.get(i);

        TextView txtDay = view.findViewById(R.id.textviewNgay);
        TextView txtStatus = view.findViewById(R.id.textviewTrangthai);
        TextView txtMaxTemp = view.findViewById(R.id.textviewMaxTemp);
        TextView txtMinTemp = view.findViewById(R.id.textviewMinTemp);
        ImageView imgStatus = view.findViewById(R.id.imageTrangthai);

        txtDay.setText(thoiTiet.getDay());
        txtStatus.setText(thoiTiet.getStatus());
        txtMaxTemp.setText(thoiTiet.getMaxTemp()+"°C");
        txtMinTemp.setText(thoiTiet.getMinTemp()+"°C");

        Picasso.with(context).load("http://openweathermap.org/img/w/" + thoiTiet.getImage() + ".png").into(imgStatus);
        return view;
    }
}
