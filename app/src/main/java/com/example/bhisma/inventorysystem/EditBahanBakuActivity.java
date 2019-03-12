package com.example.bhisma.inventorysystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bhisma.inventorysystem.model.DataBahanBaku;
import com.example.bhisma.inventorysystem.model.DataBarang;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditBahanBakuActivity extends AppCompatActivity {

    Button jEditBahanBaku;
    EditText jetEditIdBahanBaku, jetEditNamaBahanBaku, jetEditHargaBahanBaku, jetEditStokBahanBaku;
    private DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bahan_baku);

        jetEditIdBahanBaku     = (EditText) findViewById(R.id.editIdBahanBaku);
        jetEditNamaBahanBaku   = (EditText) findViewById(R.id.editNamaBahanBaku);
        jetEditHargaBahanBaku  = (EditText) findViewById(R.id.editHargaBahanBaku);
        jetEditStokBahanBaku   = (EditText) findViewById(R.id.editStokBahanBaku);
        jEditBahanBaku         = (Button) findViewById(R.id.editBahanBaku);

        jetEditIdBahanBaku.setEnabled(false);

        database = FirebaseDatabase.getInstance().getReference();
        getData();

        jEditBahanBaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Menyimpan Data yang diinputkan User kedalam Variable
                String getId    = jetEditIdBahanBaku.getText().toString();
                String getNama  = jetEditNamaBahanBaku.getText().toString();
                String getHarga = jetEditHargaBahanBaku.getText().toString();
                String getStok  = jetEditStokBahanBaku.getText().toString();

                //Mengecek agar tidak ada data yang kosong, saat proses update
                if(getId.isEmpty() || getNama.isEmpty() || getHarga.isEmpty() || getStok.isEmpty()){
                    Toast.makeText(EditBahanBakuActivity.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    /*
                      Menjalankan proses update data.
                      Method Setter digunakan untuk mendapakan data baru yang diinputkan User.
                    */
                    DataBahanBaku setBahanBaku = new DataBahanBaku();
                    setBahanBaku.setId_bahan_baku(jetEditIdBahanBaku.getText().toString());
                    setBahanBaku.setNama_bahan_baku(jetEditNamaBahanBaku.getText().toString());
                    setBahanBaku.setHarga_bahan_baku(jetEditHargaBahanBaku.getText().toString());
                    setBahanBaku.setStok_bahan_baku(jetEditStokBahanBaku.getText().toString());
                    updateBahanBaku(setBahanBaku);
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
        jetEditIdBahanBaku.setText(intentId);
        jetEditNamaBahanBaku.setText(intentNama);
        jetEditHargaBahanBaku.setText(intentHarga);
        jetEditStokBahanBaku.setText(intentStok);
    }

    //Proses Update data yang sudah ditentukan
    private void updateBahanBaku(DataBahanBaku bahanbaku){
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("BahanBaku")
                .child(getKey)
                .setValue(bahanbaku)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        jetEditIdBahanBaku.setText("");
                        jetEditNamaBahanBaku.setText("");
                        jetEditHargaBahanBaku.setText("");
                        jetEditStokBahanBaku.setText("");
                        Toast.makeText(EditBahanBakuActivity.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
