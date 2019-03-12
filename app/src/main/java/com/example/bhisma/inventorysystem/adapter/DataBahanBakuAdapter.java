package com.example.bhisma.inventorysystem.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bhisma.inventorysystem.R;
import com.example.bhisma.inventorysystem.model.DataBahanBaku;
import com.example.bhisma.inventorysystem.model.DataBarang;

import java.util.ArrayList;

public class DataBahanBakuAdapter extends ArrayAdapter<DataBahanBaku> {
    private ArrayList<DataBahanBaku> dataBahanBakuArrayList;
    private Context context;

    //Membuat Interfece
    public interface dataListener{
        void onDeleteData(DataBahanBaku data, int position);
    }

    public DataBahanBakuAdapter(@NonNull Context context, ArrayList<DataBahanBaku> dataBahanBakuArrayList){
        super(context, R.layout.view_bahan_baku, dataBahanBakuArrayList);
        this.dataBahanBakuArrayList = dataBahanBakuArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        DataBahanBaku DataBahanBaku = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.view_bahan_baku,parent,false);

        DataBahanBakuAdapter.ViewHolder viewHolder = new DataBahanBakuAdapter.ViewHolder();

        viewHolder.id_bahan_baku = convertView.findViewById(R.id.id_bahan_baku);
        viewHolder.nama_bahan_baku = convertView.findViewById(R.id.nama_bahan_baku);
        viewHolder.harga_bahan_baku = convertView.findViewById(R.id.harga_bahan_baku);
        viewHolder.stok_bahan_baku = convertView.findViewById(R.id.stok_bahan_baku);

        viewHolder.id_bahan_baku.setText(DataBahanBaku.getId_bahan_baku());
        viewHolder.nama_bahan_baku.setText(DataBahanBaku.getNama_bahan_baku());
        viewHolder.harga_bahan_baku.setText(DataBahanBaku.getHarga_bahan_baku());
        viewHolder.stok_bahan_baku.setText(DataBahanBaku.getStok_bahan_baku());

        return convertView;
    }

    static class ViewHolder {
        public TextView id_bahan_baku,nama_bahan_baku,harga_bahan_baku,stok_bahan_baku;
    }
}
