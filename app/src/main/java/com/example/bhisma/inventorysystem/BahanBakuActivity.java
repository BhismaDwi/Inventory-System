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

import com.example.bhisma.inventorysystem.adapter.DataBahanBakuAdapter;
import com.example.bhisma.inventorysystem.adapter.DataBarangAdapter;
import com.example.bhisma.inventorysystem.model.DataBahanBaku;
import com.example.bhisma.inventorysystem.model.DataBarang;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BahanBakuActivity extends AppCompatActivity implements DataBahanBakuAdapter.dataListener {

    Button jTambahBahanBaku;
    ListView jlistBahanBaku;
    ArrayList<DataBahanBaku> dataBahanBakuArrayList;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bahan_baku);

        jTambahBahanBaku   = (Button) findViewById(R.id.TambahBahanBaku);
        jlistBahanBaku     = (ListView) findViewById(R.id.listBahanBaku);

        jTambahBahanBaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_simpan_bahan_baku = new Intent(BahanBakuActivity.this, SimpanBahanBakuActivity.class);
                startActivity(intent_simpan_bahan_baku);
            }
        });

        GetData();

        jlistBahanBaku.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                                bundle.putString("dataId", dataBahanBakuArrayList.get(position).getId_bahan_baku());
                                bundle.putString("dataNama", dataBahanBakuArrayList.get(position).getNama_bahan_baku());
                                bundle.putString("dataHarga", dataBahanBakuArrayList.get(position).getHarga_bahan_baku());
                                bundle.putString("dataStok", dataBahanBakuArrayList.get(position).getStok_bahan_baku());
                                bundle.putString("getPrimaryKey", dataBahanBakuArrayList.get(position).getKey());
                                Intent intent = new Intent(view.getContext(), EditBahanBakuActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                break;
                            case 1:
                                onDeleteData(dataBahanBakuArrayList.get(position), position);
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
        reference.child("BahanBaku")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Inisialisasi ArrayList
                        dataBahanBakuArrayList = new ArrayList<>();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            //Mapping data pada DataSnapshot ke dalam objek mahasiswa
                            DataBahanBaku bahanbaku = snapshot.getValue(DataBahanBaku.class);

                            //Mengambil Primary Key, digunakan untuk proses Update dan Delete
                            bahanbaku.setKey(snapshot.getKey());
                            dataBahanBakuArrayList.add(bahanbaku);
                        }

                        DataBahanBakuAdapter DataBahanBakuAdapter = new DataBahanBakuAdapter(getBaseContext(),dataBahanBakuArrayList);

                        //Memasang Adapter pada listview
                        jlistBahanBaku.setAdapter(DataBahanBakuAdapter);

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
    public void onDeleteData(DataBahanBaku data, int position) {
        if(reference != null){
            reference.child("BahanBaku")
                    .child(data.getKey())
                    .removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(BahanBakuActivity.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
