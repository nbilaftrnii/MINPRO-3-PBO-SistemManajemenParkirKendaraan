/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Kendaraan;
import model.Motor;
import model.Mobil;
import model.Petugas;
import model.SlotParkir;
import model.Parkir;
import service.ParkirService;
import view.ParkirView;

/**
 *
 * @author ASUS
 */
public class ParkirController {
    private final ParkirService service;
    private final ParkirView view;
    private Petugas petugasAktif;
 
    public ParkirController(ParkirService service, ParkirView view) {
        this.service = service;
        this.view = view;
    }
 
    public void jalankan() {
        petugasAktif = masukPetugas();
        if (petugasAktif == null) {
            view.pesan("Login gagal 3 kali. Program ditutup.");
            return;
        }
 
        boolean berjalan = true;
        while (berjalan) {
            view.tampilkanMenu(petugasAktif.getNamaPetugas());
            tampilkanRingkasanSlot();
 
            int pilihan = view.inputIntPositif("Pilih menu: ");
            switch (pilihan) {
                case 1:
                    tambahKendaraan();
                    break;
                case 2:
                    masukParkir();
                    break;
                case 3:
                    view.tampilkanParkir(service.getAllParkir());
                    break;
                case 4:
                    keluarParkir();
                    break;
                case 5:
                    hapusParkir();
                    break;
                case 6:
                    cariParkir();
                    break;
                case 7:
                    view.tampilkanSlot(service.getAllSlot());
                    break;
                case 8:
                    berjalan = false;
                    view.pesan("Sampai jumpa, " + petugasAktif.getNamaPetugas() + "!");
                    break;
                default:
                    view.pesan("Menu hanya 1 sampai 8!");
            }
        }
    }
 
    private Petugas masukPetugas() {
        view.tampilkanHeaderLogin();
        for (int percobaan = 1; percobaan <= 3; percobaan++) {
            String nama = view.inputNamaPetugas();
            String password = view.inputPassword("Password: ");
 
            Petugas petugas = service.login(nama, password);
            if (petugas != null) {
                view.pesan("Login berhasil. Selamat bertugas, " + petugas.getNamaPetugas() + "!");
                return petugas;
            }
            view.pesan("Nama atau password salah! (percobaan " + percobaan + "/3)");
        }
        return null;
    }
 
    private void tampilkanRingkasanSlot() {
        view.tampilkanRingkasanSlot(
                service.hitungSlotKosong("Motor"), service.hitungTotalSlot("Motor"),
                service.hitungSlotKosong("Mobil"), service.hitungTotalSlot("Mobil"));
    }
 
    //Tambah Kendaraan
    private void tambahKendaraan() {
        System.out.println("\n=== TAMBAH KENDARAAN ===");
        String plat = view.inputTidakKosong("Nomor Plat: ");
 
        if (service.cariKendaraan(plat) != null) { // overloading: cari by plat
            view.pesan("Nomor plat sudah terdaftar!");
            return;
        }
 
        String jenis = view.inputJenisKendaraan();
        String merk = view.inputMerk();
        String warna = view.inputWarna();
        int id = service.generateIdKendaraan();
 
        Kendaraan kendaraan;
        if (jenis.equals("Motor")) {
            int cc = view.inputIntRentang("Kapasitas mesin (cc, 50-2000): ", 50, 2000);
            kendaraan = new Motor(id, plat, merk, warna, cc);
        } else {
            int pintu = view.inputIntRentang("Jumlah pintu (2-6): ", 2, 6);
            kendaraan = new Mobil(id, plat, merk, warna, pintu);
        }
 
        service.tambahKendaraan(kendaraan);
        view.pesan("Data kendaraan berhasil ditambahkan. ID Kendaraan: " + id);
    }
 
    private void masukParkir() {
        System.out.println("\n=== MASUK PARKIR ===");
        view.tampilkanKendaraan(service.getAllKendaraan());
 
        int idKendaraan = view.inputIntPositif("ID Kendaraan: ");
        Kendaraan kendaraan = service.cariKendaraan(idKendaraan); // overloading: cari by ID
        if (kendaraan == null) {
            view.pesan("Kendaraan tidak ditemukan!");
            return;
        }
        if (service.sedangParkir(kendaraan)) {
            view.pesan("Kendaraan ini masih parkir!");
            return;
        }
 
        SlotParkir slot = service.cariSlotKosong(kendaraan.getJenisKendaraan());
        if (slot == null) {
            view.pesan("Maaf, slot " + kendaraan.getJenisKendaraan() + " sudah penuh!");
            return;
        }
 
        String waktuMasuk = view.inputWaktu("Waktu Masuk");
        Parkir parkir = new Parkir(service.generateIdParkir(), kendaraan,
                petugasAktif, slot, waktuMasuk);
        service.tambahParkir(parkir);
 
        view.pesan("Berhasil! Arahkan kendaraan ke slot " + slot.getNomorSlot()
                + ". ID Parkir: " + parkir.getIdParkir());
    }
 
    //Keluar Parkir (update)
    private void keluarParkir() {
        System.out.println("\n=== KELUAR PARKIR ===");
        view.tampilkanParkir(service.getAllParkir());
 
        int id = view.inputIntPositif("ID Parkir yang keluar: ");
        Parkir parkir = service.cariById(id);
        if (parkir == null) {
            view.pesan("Data parkir tidak ditemukan!");
            return;
        }
        if (parkir.getStatusParkir().equals(Parkir.SELESAI)) {
            view.pesan("Kendaraan ini sudah keluar dan selesai dibayar.");
            return;
        }
 
        view.pesan("Waktu masuk  : " + parkir.getWaktuMasuk());
        int lamaJam = view.inputIntPositif("Lama Parkir (jam): ");
 
        // Waktu keluar otomatis menyesuaikan waktu masuk + lama parkir
        String waktuKeluar = parkir.hitungWaktuKeluar(lamaJam);
        view.pesan("Waktu keluar : " + waktuKeluar + " (otomatis)");
 
        String metode = view.inputMetodePembayaran();
        int tarif = (int) parkir.hitungTarif(lamaJam);
        view.pesan("Total tarif : Rp" + tarif);
 
        int jumlahBayar;
        if (metode.equals("QRIS")) {
            jumlahBayar = tarif; //otomatis
            view.pesan("Pembayaran QRIS sesuai tarif: Rp" + tarif);
        } else {
            jumlahBayar = view.inputIntPositif("Jumlah Bayar: Rp");
            if (jumlahBayar < tarif) {
                view.pesan("Pembayaran kurang! Data belum dapat diselesaikan.");
                return;
            }
        }
 
        service.updateParkir(parkir, waktuKeluar, lamaJam, metode, jumlahBayar);
        view.pesan("Kendaraan berhasil keluar.");
        parkir.cetakStruk(lamaJam);
    }
 
    //Hapus
    private void hapusParkir() {
        System.out.println("\n=== HAPUS DATA PARKIR ===");
        view.tampilkanParkir(service.getAllParkir());
 
        int id = view.inputIntPositif("ID Parkir yang dihapus: ");
        Parkir parkir = service.cariById(id);
        if (parkir == null) {
            view.pesan("Data parkir tidak ditemukan!");
            return;
        }
 
        System.out.println("\nData yang akan dihapus:");
        parkir.tampilkanInfo();
 
        if (view.konfirmasiHapus()) {
            service.hapusParkir(parkir);
            view.pesan("Data parkir berhasil dihapus.");
        } else {
            view.pesan("Penghapusan dibatalkan.");
        }
    }
 
    //Cari
    private void cariParkir() {
        System.out.println("\n=== CARI DATA PARKIR ===");
        int id = view.inputIntPositif("ID Parkir: ");
        Parkir parkir = service.cariById(id);
        if (parkir == null) {
            view.pesan("Data parkir tidak ditemukan!");
            return;
        }
        parkir.tampilkanInfo();
    }
}