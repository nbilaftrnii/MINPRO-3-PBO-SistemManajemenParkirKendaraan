/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ParkirService {
    private static final int JUMLAH_SLOT_MOTOR = 25;
    private static final int JUMLAH_SLOT_MOBIL = 10;
 
    private final ArrayList<Petugas> daftarPetugas = new ArrayList<>();
    private final ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();
    private final ArrayList<SlotParkir> daftarSlot = new ArrayList<>();
    private final ArrayList<Parkir> daftarParkir = new ArrayList<>();
 
    private int idKendaraanBerikutnya = 2;
    private int idParkirBerikutnya = 2;
 
    public ParkirService() {
        //Akun petugas (tambah/ubah akun dilakukan di sini)
        daftarPetugas.add(new Petugas("Jai", "12345"));
        daftarPetugas.add(new Petugas("Ahmad", "54321"));
 
        buatSlot("A", "Motor", JUMLAH_SLOT_MOTOR);
        buatSlot("B", "Mobil", JUMLAH_SLOT_MOBIL);
 
        //Dummy data kendaraan & parkir
        Kendaraan kendaraan = new Motor(1, "KT 1234 AB", "Honda Vario", "Hitam", 125);
        daftarKendaraan.add(kendaraan);
 
        tambahParkir(new Parkir(1, kendaraan, daftarPetugas.get(0),
                cariSlotKosong("Motor"), "24-09-2026 08:00"));
    }
 
    private void buatSlot(String awalan, String jenis, int jumlah) {
        for (int i = 1; i <= jumlah; i++) {
            daftarSlot.add(new SlotParkir(awalan + String.format("%02d", i), jenis));
        }
    }
 
    public Petugas login(String nama, String password) {
        for (Petugas petugas : daftarPetugas) {
            if (petugas.cekLogin(nama, password)) {
                return petugas;
            }
        }
        return null;
    }
 
    //Generate ID
    public int generateIdKendaraan() {
        return idKendaraanBerikutnya++;
    }
 
    public int generateIdParkir() {
        return idParkirBerikutnya++;
    }
 
    //Kendaraan
    public void tambahKendaraan(Kendaraan kendaraan) {
        daftarKendaraan.add(kendaraan);
    }
 
    // Overloading, cari berdasarkan ID
    public Kendaraan cariKendaraan(int id) {
        for (Kendaraan kendaraan : daftarKendaraan) {
            if (kendaraan.getIdKendaraan() == id) {
                return kendaraan;
            }
        }
        return null;
    }
 
    // Overloading, cari berdasarkan nomor plat
    public Kendaraan cariKendaraan(String nomorPlat) {
        for (Kendaraan kendaraan : daftarKendaraan) {
            if (kendaraan.getNomorPlat().equalsIgnoreCase(nomorPlat.trim())) {
                return kendaraan;
            }
        }
        return null;
    }
 
    //Slot
    public SlotParkir cariSlotKosong(String jenis) {
        for (SlotParkir slot : daftarSlot) {
            if (slot.isKosong() && slot.getJenisSlot().equalsIgnoreCase(jenis)) {
                return slot;
            }
        }
        return null;
    }
 
    public int hitungSlotKosong(String jenis) {
        return hitungSlot(jenis, true);
    }
 
    public int hitungTotalSlot(String jenis) {
        return hitungSlot(jenis, false);
    }
 
    private int hitungSlot(String jenis, boolean hanyaKosong) {
        int jumlah = 0;
        for (SlotParkir slot : daftarSlot) {
            if (slot.getJenisSlot().equalsIgnoreCase(jenis) && (!hanyaKosong || slot.isKosong())) {
                jumlah++;
            }
        }
        return jumlah;
    }
 
    //Parkir
    public boolean sedangParkir(Kendaraan kendaraan) {
        for (Parkir parkir : daftarParkir) {
            if (parkir.getKendaraan() == kendaraan
                    && parkir.getStatusParkir().equals(Parkir.MASIH_PARKIR)) {
                return true;
            }
        }
        return false;
    }
 
    public void tambahParkir(Parkir parkir) {
        daftarParkir.add(parkir);
        parkir.getSlot().isi();
    }
 
    public ArrayList<Parkir> getAllParkir() {
        return daftarParkir;
    }
 
    public Parkir cariById(int id) {
        for (Parkir parkir : daftarParkir) {
            if (parkir.getIdParkir() == id) {
                return parkir;
            }
        }
        return null;
    }
 
    public void updateParkir(Parkir parkir, String waktuKeluar, String metodePembayaran,
                             int jumlahBayar, int tarif) {
        parkir.setWaktuKeluar(waktuKeluar);
        parkir.setMetodePembayaran(metodePembayaran);
        parkir.setJumlahBayar(jumlahBayar);
        parkir.setStatusParkir(Parkir.SELESAI);
        parkir.setStatusPembayaran(jumlahBayar >= tarif ? "Lunas" : "Belum Lunas");
 
        // Kendaraan keluar >> slot kosong lagi
        parkir.getSlot().kosongkan();
    }
 
    public void hapusParkir(Parkir parkir) {
        // Slot dikosongkan hanya jika kendaraan masih parkir
        if (parkir.getStatusParkir().equals(Parkir.MASIH_PARKIR)) {
            parkir.getSlot().kosongkan();
        }
        daftarParkir.remove(parkir);
    }
}