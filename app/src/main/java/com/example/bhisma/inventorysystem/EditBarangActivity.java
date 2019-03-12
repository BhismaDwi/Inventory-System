package com.example.bhisma.inventorysystem;

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

public class EditBarangActivity extends AppCompatActivity {

    Button jEditBarang;
    EditText jetEditIdBarang, jetEditNamaBarang, jetEditHargaBarang, jetEditStokBarang;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        jetEditIdBarang     = (EditText) findViewById(R.id.editIdBarang);
        jetEditNamaBarang   = (EditText) findViewById(R.id.editNamaBarang);
        jetEditHargaBarang  = (EditText) findViewById(R.id.editHargaBarang);
        jetEditStokBarang   = (EditText) findViewById(R.id.editStokBarang);
        jEditBarang         = (Button) findViewById(R.id.editBarang);

        jetEditIdBarang.setEnabled(false);

        database = FirebaseDatabase.getInstance().getReference();
        getData();

        jEditBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Menyimpan Data yang diinputkan User kedalam Variable
                String getId    = jetEditIdBarang.getText().toString();
                String getNama  = jetEditNamaBarang.getText().toString();
                String getHarga = jetEditHargaBarang.getText().toString();
                String getStok  = jetEditStokBarang.getText().toString();

                //Mengecek agar tidak ada data yang kosong, saat proses update
                if(getId.isEmpty() || getNama.isEmpty() || getHarga.isEmpty() || getStok.isEmpty()){
                    Toast.makeText(EditBarangActivity.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    /*
                      Menjalankan proses update data.
                      Method Setter digunakan untuk mendapakan data baru yang diinputkan User.
                    */
                    DataBarang setBarang = new DataBarang();
                    setBarang.setId_barang(jetEditIdBarang.getText().toString());
                    setBarang.setNama_barang(jetEditNamaBarang.getText().toString());
                    setBarang.setHarga_barang(jetEditHargaBarang.getText().toString());
                    setBarang.setStok_barang(jetEditStokBarang.getText().toString());
                    updateBarang(setBarang);
                }
            }
        });
    }

    //Menampilkan data yang akan di update
    private void getData(){
        //Menampilkan data dari item yang dipilih sebelumnya
        final String intentId       = getIntent().getExtras().getString("dataId");
        final String intentNama     = getIntent().getExtras().getString("dataNama");
        final String intentHarga    = getIntent().getExtras().getString("dataHarga");
        final String intentStok     = getIntent().getExtras().getString("dataStok");
        jetEditIdBarang.setText(intentId);
        jetEditNamaBarang.setText(intentNama);
        jetEditHargaBarang.setText(intentHarga);
        jetEditStokBarang.setText(intentStok);
    }

    //Proses Update data yang sudah ditentukan
    private void updateBarang(DataBarang barang){
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("Barang")
                .child(getKey)
                .setValue(barang)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        jetEditIdBarang.setText("");
                        jetEditNamaBarang.setText("");
                        jetEditHargaBarang.setText("");
                        jetEditStokBarang.setText("");
                        Toast.makeText(EditBarangActivity.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

}
