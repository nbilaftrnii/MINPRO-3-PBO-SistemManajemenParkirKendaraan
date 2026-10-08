/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public abstract class Kendaraan implements Bertarif {
    private final int idKendaraan;
    private String nomorPlat;
    private String merk;
    private String warna;
 
    public Kendaraan(int idKendaraan, String nomorPlat, String merk, String warna) {
        this.idKendaraan = idKendaraan;
        setNomorPlat(nomorPlat);
        setMerk(merk);
        setWarna(warna);
    }
 
    public int getIdKendaraan() {
        return idKendaraan;
    }
 
    public String getNomorPlat() {
        return nomorPlat;
    }
 
    public String getMerk() {
        return merk;
    }
 
    public String getWarna() {
        return warna;
    }
 
    public void setNomorPlat(String nomorPlat) {
        if (nomorPlat == null || nomorPlat.trim().isEmpty()) {
            throw new IllegalArgumentException("Nomor plat tidak boleh kosong!");
        }
        this.nomorPlat = nomorPlat.trim().toUpperCase();
    }
 
    public void setMerk(String merk) {
        if (merk == null || merk.trim().isEmpty()) {
            throw new IllegalArgumentException("Merk tidak boleh kosong!");
        }
        this.merk = merk.trim();
    }
 
    public void setWarna(String warna) {
        if (warna == null || warna.trim().isEmpty()) {
            throw new IllegalArgumentException("Warna tidak boleh kosong!");
        }
        this.warna = warna.trim();
    }
 
    public abstract String getJenisKendaraan();
 
    public void tampilkanInfo() {
        System.out.println("ID Kendaraan : " + idKendaraan);
        System.out.println("Nomor Plat   : " + nomorPlat);
        System.out.println("Jenis        : " + getJenisKendaraan());
        System.out.println("Merk         : " + merk);
        System.out.println("Warna        : " + warna);
    }
}