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
 
    // Setter dengan kondisi: data tidak boleh kosong
    public void setNomorPlat(String nomorPlat) {
        this.nomorPlat = wajibIsi(nomorPlat, "Nomor plat").toUpperCase();
    }
 
    public void setMerk(String merk) {
        this.merk = wajibIsi(merk, "Merk");
    }
 
    public void setWarna(String warna) {
        this.warna = wajibIsi(warna, "Warna");
    }
 
    private static String wajibIsi(String nilai, String namaData) {
        if (nilai == null || nilai.trim().isEmpty()) {
            throw new IllegalArgumentException(namaData + " tidak boleh kosong!");
        }
        return nilai.trim();
    }
 
    // ===== Abstract method: wajib di-override oleh Motor dan Mobil =====
    public abstract String getJenisKendaraan();
 
    protected abstract double getTarifPerJam();
 
    protected abstract double getTarifMaksimal();
 
    // Rumus tarif ditulis SATU kali di sini (dari interface Bertarif),
    // angka tarifnya diambil dari subclass masing-masing.
    @Override
    public double hitungTarif(int lamaJam) {
        return Math.min(getTarifPerJam() * lamaJam, getTarifMaksimal());
    }
 
    public void tampilkanInfo() {
        System.out.printf("%-17s: %d%n", "ID Kendaraan", idKendaraan);
        System.out.printf("%-17s: %s%n", "Nomor Plat", nomorPlat);
        System.out.printf("%-17s: %s%n", "Jenis Kendaraan", getJenisKendaraan());
        System.out.printf("%-17s: %s%n", "Merk", merk);
        System.out.printf("%-17s: %s%n", "Warna", warna);
    }
}