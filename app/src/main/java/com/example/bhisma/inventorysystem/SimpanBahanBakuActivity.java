package com.example.bhisma.inventorysystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bhisma.inventorysystem.model.DataBahanBaku;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SimpanBahanBakuActivity extends AppCompatActivity {

    Button jsimpanBahanBaku;
    EditText jetidBahanBaku, jetnamaBahanBaku, jethargaBahanBaku, jetstokBahanBaku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpan_bahan_baku);

        jsimpanBahanBaku  = (Button) findViewById(R.id.simpanBahanBaku);
        jetidBahanBaku    = (EditText) findViewById(R.id.idBahanBaku);
        jetnamaBahanBaku  = (EditText) findViewById(R.id.namaBahanBaku);
        jethargaBahanBaku = (EditText) findViewById(R.id.hargaBahanBaku);
        jetstokBahanBaku  = (EditText) findViewById(R.id.stokBahanBaku);

        jsimpanBahanBaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Mendapatkan Instance dari Database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference getReference;

                //Menyimpan Data yang diinputkan User kedalam Variable
                String getId    = jetidBahanBaku.getText().toString();
                String getNama  = jetnamaBahanBaku.getText().toString();
                String getHarga = jethargaBahanBaku.getText().toString();
                String getStok  = jetstokBahanBaku.getText().toString();

                getReference = database.getReference(); // Mendapatkan Referensi dari Database

                if (getId.isEmpty() || getNama.isEmpty() || getHarga.isEmpty() || getStok.isEmpty()){
                    //Jika Ada, maka akan menampilkan pesan singkan seperti berikut ini.
                    Toast.makeText(SimpanBahanBakuActivity.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else{
                    getReference.child("BahanBaku").push()
                            .setValue(new DataBahanBaku(getId, getNama, getHarga, getStok))
                            .addOnSuccessListener(SimpanBahanBakuActivity.this , new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    //Peristiwa ini terjadi saat user berhasil menyimpan datanya kedalam Database
                                    jetidBahanBaku.setText("");
                                    jetnamaBahanBaku.setText("");
                                    jethargaBahanBaku.setText("");
                                    jetstokBahanBaku.setText("");
                                    Toast.makeText(SimpanBahanBakuActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                }
            }
        });

    }
}
