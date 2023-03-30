package com.example.pengaduanmasyarakat.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.pengaduanmasyarakat.Model.KecamatanModel;

import java.util.List;

public class KecamatanAdapter extends ArrayAdapter<KecamatanModel> {
    public KecamatanAdapter(@NonNull Context context, List<KecamatanModel> kecamatan){
        super(context, android.R.layout.simple_spinner_item, kecamatan);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setText(getItem(position).getKecamatan());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getDropDownView(position, convertView, parent);
        view.setText(getItem(position).getKecamatan());
        return view;
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(getItem(position).getIdKecamtan());
    }



}
