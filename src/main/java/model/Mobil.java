/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Mobil extends Kendaraan{
    private static final double TARIF_PER_JAM = 5000;
    private static final double TARIF_MAKSIMAL = 50000;
    private int jumlahPintu;
 
    public Mobil(int idKendaraan, String nomorPlat, String merk, String warna, int jumlahPintu) {
        super(idKendaraan, nomorPlat, merk, warna);
        setJumlahPintu(jumlahPintu);
    }
 
    public int getJumlahPintu() {
        return jumlahPintu;
    }
 
    public void setJumlahPintu(int jumlahPintu) {
        if (jumlahPintu < 2 || jumlahPintu > 6) {
            throw new IllegalArgumentException("Jumlah pintu mobil harus 2-6!");
        }
        this.jumlahPintu = jumlahPintu;
    }
 
    @Override
    public String getJenisKendaraan() {
        return "Mobil";
    }
 
    @Override
    protected double getTarifPerJam() {
        return TARIF_PER_JAM;
    }
 
    @Override
    protected double getTarifMaksimal() {
        return TARIF_MAKSIMAL;
    }
 
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.printf("%-17s: %d%n", "Jumlah Pintu", jumlahPintu);
    }
}