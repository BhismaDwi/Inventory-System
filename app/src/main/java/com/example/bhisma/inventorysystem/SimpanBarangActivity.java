package com.example.bhisma.inventorysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bhisma.inventorysystem.model.DataBarang;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SimpanBarangActivity extends AppCompatActivity {

    Button jsimpanBarang;
    EditText jetidBarang, jetnamaBarang, jethargaBarang, jetstokBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpan_barang);

        jsimpanBarang  = (Button) findViewById(R.id.simpanBarang);
        jetidBarang    = (EditText) findViewById(R.id.idBarang);
        jetnamaBarang  = (EditText) findViewById(R.id.namaBarang);
        jethargaBarang = (EditText) findViewById(R.id.hargaBarang);
        jetstokBarang  = (EditText) findViewById(R.id.stokBarang);

        jsimpanBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Mendapatkan Instance dari Database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference getReference;

                //Menyimpan Data yang diinputkan User kedalam Variable
                String getId    = jetidBarang.getText().toString();
                String getNama  = jetnamaBarang.getText().toString();
                String getHarga = jethargaBarang.getText().toString();
                String getStok  = jetstokBarang.getText().toString();

                getReference = database.getReference(); // Mendapatkan Referensi dari Database

                if (getId.isEmpty() || getNama.isEmpty() || getHarga.isEmpty() || getStok.isEmpty()){
                    //Jika Ada, maka akan menampilkan pesan singkan seperti berikut ini.
                    Toast.makeText(SimpanBarangActivity.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else{
//                    Toast.makeText(SimpanBarangActivity.this, "Data boleh ada yang kosong", Toast.LENGTH_SHORT).show();

                    getReference.child("Barang").push()
                            .setValue(new DataBarang(getId, getNama, getHarga, getStok))
                            .addOnSuccessListener(SimpanBarangActivity.this , new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    //Peristiwa ini terjadi saat user berhasil menyimpan datanya kedalam Database
                                    jetidBarang.setText("");
                                    jetnamaBarang.setText("");
                                    jethargaBarang.setText("");
                                    jetstokBarang.setText("");
                                    Toast.makeText(SimpanBarangActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                }
            }
        });
    }
}
