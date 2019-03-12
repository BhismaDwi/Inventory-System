package com.example.bhisma.inventorysystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bhisma.inventorysystem.adapter.DataBarangAdapter;
import com.example.bhisma.inventorysystem.model.DataBarang;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BarangActivity extends AppCompatActivity implements DataBarangAdapter.dataListener {

    Button jTambahBarang;
    ListView jlistBarang;
    ArrayList<DataBarang> dataBarangArrayList;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);

        jTambahBarang   = (Button) findViewById(R.id.TambahBarang);
        jlistBarang     = (ListView) findViewById(R.id.listBarang);

        jTambahBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_simpan_barang = new Intent(BarangActivity.this, SimpanBarangActivity.class);
                startActivity(intent_simpan_barang);
            }
        });

        GetData();

        jlistBarang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, final int position, long l) {
                final String[] action = {"Update", "Delete"};
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setItems(action,  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                Bundle bundle = new Bundle();
                                bundle.putString("dataId", dataBarangArrayList.get(position).getId_barang());
                                bundle.putString("dataNama", dataBarangArrayList.get(position).getNama_barang());
                                bundle.putString("dataHarga", dataBarangArrayList.get(position).getHarga_barang());
                                bundle.putString("dataStok", dataBarangArrayList.get(position).getStok_barang());
                                bundle.putString("getPrimaryKey", dataBarangArrayList.get(position).getKey());
                                Intent intent = new Intent(view.getContext(), EditBarangActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                break;
                            case 1:
                                onDeleteData(dataBarangArrayList.get(position), position);
                                break;
                        }
                    }
                });
                alert.create();
                alert.show();
            }
        });

    }

    private void GetData(){
        Toast.makeText(getApplicationContext(),"Mohon Tunggu Sebentar...", Toast.LENGTH_LONG).show();
        //Mendapatkan Referensi Database
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Barang")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Inisialisasi ArrayList
                        dataBarangArrayList = new ArrayList<>();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            //Mapping data pada DataSnapshot ke dalam objek mahasiswa
                            DataBarang barang = snapshot.getValue(DataBarang.class);

                            //Mengambil Primary Key, digunakan untuk proses Update dan Delete
                            barang.setKey(snapshot.getKey());
                            dataBarangArrayList.add(barang);
                        }

                        DataBarangAdapter DataBarangAdapter = new DataBarangAdapter(getBaseContext(),dataBarangArrayList);
                        //Inisialisasi Adapter dan data Mahasiswa dalam bentuk Array
//                        adapter = new RecyclerViewAdapter(dataMahasiswa, MyListData.this);

                        //Memasang Adapter pada RecyclerView
                        jlistBarang.setAdapter(DataBarangAdapter);

                        Toast.makeText(getApplicationContext(),"Data Berhasil Dimuat", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
              /*
                Kode ini akan dijalankan ketika ada error dan
                pengambilan data error tersebut lalu memprint error nya
                ke LogCat
               */
                        Toast.makeText(getApplicationContext(),"Data Gagal Dimuat", Toast.LENGTH_LONG).show();
                        Log.e("MyListActivity", databaseError.getDetails()+" "+databaseError.getMessage());
                    }
                });
    }

    @Override
    public void onDeleteData(DataBarang data, int position) {
        if(reference != null){
            reference.child("Barang")
                    .child(data.getKey())
                    .removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(BarangActivity.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
