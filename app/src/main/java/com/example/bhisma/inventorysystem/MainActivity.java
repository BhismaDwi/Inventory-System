package com.example.bhisma.inventorysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout jbtnBarang, jbtnBahanBaku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jbtnBarang      = (LinearLayout) findViewById(R.id.MenuBarang);
        jbtnBahanBaku   = (LinearLayout) findViewById(R.id.MenuBahanBaku);

        jbtnBarang.setOnClickListener(this);
        jbtnBahanBaku.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.MenuBarang:
//                Toast.makeText(MainActivity.this,"Menu Barang", Toast.LENGTH_SHORT).show();
                Intent intent_barang = new Intent(MainActivity.this, BarangActivity.class);
                startActivity(intent_barang);
                break;
            case R.id.MenuBahanBaku:
//                Toast.makeText(MainActivity.this,"Menu Bahan Baku", Toast.LENGTH_SHORT).show();
                Intent intent_bahan_baku = new Intent(MainActivity.this, BahanBakuActivity.class);
                startActivity(intent_bahan_baku);
                break;
        }
    }
}
