package com.example.bhisma.inventorysystem.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bhisma.inventorysystem.BarangActivity;
import com.example.bhisma.inventorysystem.R;
import com.example.bhisma.inventorysystem.model.DataBarang;

import java.util.ArrayList;

public class DataBarangAdapter extends ArrayAdapter<DataBarang> {
    private ArrayList<DataBarang> dataBarangArrayList;
    private Context context;

    //Membuat Interfece
    public interface dataListener{
        void onDeleteData(DataBarang data, int position);
    }

    public DataBarangAdapter(@NonNull Context context, ArrayList<DataBarang> dataBarangArrayList){
        super(context, R.layout.view_barang, dataBarangArrayList);
        this.dataBarangArrayList = dataBarangArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        DataBarang DataBarang = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.view_barang,parent,false);

        ViewHolder viewHolder = new ViewHolder();

        viewHolder.id_barang = convertView.findViewById(R.id.id_barang);
        viewHolder.nama_barang = convertView.findViewById(R.id.nama_barang);
        viewHolder.harga_barang = convertView.findViewById(R.id.harga_barang);
        viewHolder.stok_barang = convertView.findViewById(R.id.stok_barang);

        viewHolder.id_barang.setText(DataBarang.getId_barang());
        viewHolder.nama_barang.setText(DataBarang.getNama_barang());
        viewHolder.harga_barang.setText(DataBarang.getHarga_barang());
        viewHolder.stok_barang.setText(DataBarang.getStok_barang());

        return convertView;
    }

    static class ViewHolder {
        public TextView id_barang,nama_barang,harga_barang,stok_barang;
    }

}
