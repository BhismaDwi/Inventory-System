package com.example.bhisma.inventorysystem.model;

public class DataBahanBaku {
    private String id_bahan_baku;
    private String nama_bahan_baku;
    private String harga_bahan_baku;
    private String stok_bahan_baku;
    private String key;

    public DataBahanBaku() {
    }

    public DataBahanBaku(String id_bahan_baku, String nama_bahan_baku, String harga_bahan_baku, String stok_bahan_baku) {
        this.id_bahan_baku = id_bahan_baku;
        this.nama_bahan_baku = nama_bahan_baku;
        this.harga_bahan_baku = harga_bahan_baku;
        this.stok_bahan_baku = stok_bahan_baku;
    }

    public String getId_bahan_baku() {
        return id_bahan_baku;
    }

    public void setId_bahan_baku(String id_bahan_baku) {
        this.id_bahan_baku = id_bahan_baku;
    }

    public String getNama_bahan_baku() {
        return nama_bahan_baku;
    }

    public void setNama_bahan_baku(String nama_bahan_baku) {
        this.nama_bahan_baku = nama_bahan_baku;
    }

    public String getHarga_bahan_baku() {
        return harga_bahan_baku;
    }

    public void setHarga_bahan_baku(String harga_bahan_baku) {
        this.harga_bahan_baku = harga_bahan_baku;
    }

    public String getStok_bahan_baku() {
        return stok_bahan_baku;
    }

    public void setStok_bahan_baku(String stok_bahan_baku) {
        this.stok_bahan_baku = stok_bahan_baku;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
