package com.example.bhisma.inventorysystem.model;

public class DataBarang {
    private String id_barang;
    private String nama_barang;
    private String harga_barang;
    private String stok_barang;
    private String key;

    public DataBarang() {
    }

    public DataBarang(String id_barang, String nama_barang, String harga_barang, String stok_barang) {
        this.id_barang = id_barang;
        this.nama_barang = nama_barang;
        this.harga_barang = harga_barang;
        this.stok_barang = stok_barang;
    }

    public String getId_barang() {
        return id_barang;
    }

    public void setId_barang(String id_barang) {
        this.id_barang = id_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(String harga_barang) {
        this.harga_barang = harga_barang;
    }

    public String getStok_barang() {
        return stok_barang;
    }

    public void setStok_barang(String stok_barang) {
        this.stok_barang = stok_barang;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
