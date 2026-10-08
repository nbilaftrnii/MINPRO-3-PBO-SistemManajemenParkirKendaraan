/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Motor extends Kendaraan {
    private static final double TARIF_PER_JAM = 2000;
    private static final double TARIF_MAKSIMAL = 20000;
    private int kapasitasCC;
 
    public Motor(int idKendaraan, String nomorPlat, String merk, String warna, int kapasitasCC) {
        super(idKendaraan, nomorPlat, merk, warna);
        setKapasitasCC(kapasitasCC);
    }
 
    public int getKapasitasCC() {
        return kapasitasCC;
    }
 
    public void setKapasitasCC(int kapasitasCC) {
        if (kapasitasCC < 50 || kapasitasCC > 2000) {
            throw new IllegalArgumentException("Kapasitas mesin motor harus 50-2000 cc!");
        }
        this.kapasitasCC = kapasitasCC;
    }
 
    @Override
    public String getJenisKendaraan() {
        return "Motor";
    }
 
    @Override
    public double hitungTarif(int lamaJam) {
        return Math.min(TARIF_PER_JAM * lamaJam, TARIF_MAKSIMAL);
    }
 
    @Override
    public void tampilkanInfo() {
        System.out.println("- Data Motor -");
        super.tampilkanInfo();
        System.out.println("Kapasitas CC : " + kapasitasCC + " cc");
    }
}