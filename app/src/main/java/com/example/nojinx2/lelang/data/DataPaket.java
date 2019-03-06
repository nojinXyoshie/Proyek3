package com.example.nojinx2.lelang.data;

import java.io.Serializable;

public class DataPaket implements Serializable {

    @com.google.gson.annotations.SerializedName("id_penjual")
    private String idPenjual;
    @com.google.gson.annotations.SerializedName("id_barang")
    private String idBarang;
    @com.google.gson.annotations.SerializedName("nama_ikan")
    private String nama_ikan;
    @com.google.gson.annotations.SerializedName("no_hp")
    private String noHp;
    @com.google.gson.annotations.SerializedName("email")
    private String email;
    @com.google.gson.annotations.SerializedName("berat_ikan")
    private String berat_ikan;
    @com.google.gson.annotations.SerializedName("alamat")
    private String alamat;
    @com.google.gson.annotations.SerializedName("harga_awal")
    private String harga_awal;
    @com.google.gson.annotations.SerializedName("foto_ikan")
    private String foto_ikan;
    @com.google.gson.annotations.SerializedName("deskripsi")
    private String deskripsi;

    public String getIdPenjual() {
        return idPenjual;
    }

    public void setIdPenjual(String idPenjual) {
        this.idPenjual = idPenjual;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaIkan() {
        return nama_ikan;
    }

    public void setNamaIkan(String nama_ikan) {
        this.nama_ikan = nama_ikan;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBerat_ikan() {
        return berat_ikan;
    }

    public void setBerat_ikan(String berat_ikan) {
        this.berat_ikan = berat_ikan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getHarga_awal() {
        return harga_awal;
    }

    public void setHarga_awal(String harga_awal) {
        this.harga_awal = harga_awal;
    }

    public String getFoto_ikan() {
        return foto_ikan;
    }

    public void setFoto_ikan(String foto_ikan) {
        this.foto_ikan = foto_ikan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

}