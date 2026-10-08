/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import model.Kendaraan;
import model.Parkir;
import model.SlotParkir;

/**
 *
 * @author ASUS
 */
public class ParkirView {
    private final Scanner input;
 
    public ParkirView(Scanner input) {
        this.input = input;
    }
 
    // ===== Validasi input =====
    public int inputIntPositif(String pesan) {
        while (true) {
            try {
                System.out.print(pesan);
                int angka = Integer.parseInt(input.nextLine().trim());
                if (angka > 0) {
                    return angka;
                }
                System.out.println("Input harus lebih dari 0!");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }
 
    public int inputIntRentang(String pesan, int min, int max) {
        while (true) {
            int angka = inputIntPositif(pesan);
            if (angka >= min && angka <= max) {
                return angka;
            }
            System.out.println("Input harus antara " + min + " sampai " + max + "!");
        }
    }
 
    public String inputTidakKosong(String pesan) {
        while (true) {
            System.out.print(pesan);
            String data = input.nextLine().trim();
            if (!data.isEmpty()) {
                return data;
            }
            System.out.println("Input tidak boleh kosong!");
        }
    }
 
    // Password disembunyikan (tidak terlihat saat diketik).
    // Jika tidak ada konsol asli (mis. Output NetBeans), otomatis jadi input biasa.
    public String inputPassword(String pesan) {
        java.io.Console konsol = System.console();
        while (true) {
            String data;
            if (konsol != null) {
                char[] karakter = konsol.readPassword(pesan);
                data = (karakter == null) ? "" : new String(karakter).trim();
            } else {
                System.out.print(pesan);
                data = input.nextLine().trim();
            }
            if (!data.isEmpty()) {
                return data;
            }
            System.out.println("Input tidak boleh kosong!");
        }
    }
 
    public String inputMerk() {
        while (true) {
            String merk = inputTidakKosong("Merk: ");
            if (merk.matches(".*[a-zA-Z].*")) {
                return merk;
            }
            System.out.println("Merk harus mengandung huruf!");
        }
    }
 
    public String inputWarna() {
        while (true) {
            String warna = inputTidakKosong("Warna: ");
            if (warna.matches("[a-zA-Z ]+")) {
                return warna;
            }
            System.out.println("Warna hanya boleh berisi huruf!");
        }
    }
 
    public String inputNamaPetugas() {
        while (true) {
            String nama = inputTidakKosong("Nama Petugas: ");
            if (nama.matches("[a-zA-Z ]+")) {
                return nama;
            }
            System.out.println("Nama hanya boleh berisi huruf!");
        }
    }
 
    // Mengembalikan "Motor" atau "Mobil" (sudah dirapikan huruf besar-kecilnya)
    public String inputJenisKendaraan() {
        while (true) {
            String jenis = inputTidakKosong("Jenis Kendaraan (Motor/Mobil): ");
            if (jenis.equalsIgnoreCase("Motor")) {
                return "Motor";
            }
            if (jenis.equalsIgnoreCase("Mobil")) {
                return "Mobil";
            }
            System.out.println("Jenis kendaraan hanya Motor atau Mobil!");
        }
    }
 
    // Mengembalikan "Cash" atau "QRIS"
    public String inputMetodePembayaran() {
        while (true) {
            String metode = inputTidakKosong("Metode Pembayaran (Cash/QRIS): ");
            if (metode.equalsIgnoreCase("Cash")) {
                return "Cash";
            }
            if (metode.equalsIgnoreCase("QRIS")) {
                return "QRIS";
            }
            System.out.println("Metode pembayaran hanya Cash atau QRIS!");
        }
    }
 
    // Format waktu: DD-MM-YYYY HH:MM, dicek benar-benar valid
    public String inputWaktu(String label) {
        while (true) {
            String waktu = inputTidakKosong(label + " [format DD-MM-YYYY HH:MM, contoh 24-09-2026 17:30]: ");
            try {
                LocalDateTime.parse(waktu, Parkir.FORMAT_WAKTU);
                return waktu;
            } catch (DateTimeParseException e) {
                System.out.println("Format/tanggal salah! Gunakan DD-MM-YYYY HH:MM.");
            }
        }
    }
 
    public boolean konfirmasiHapus() {
        while (true) {
            String pilihan = inputTidakKosong("Yakin ingin menghapus data? (Y/T): ");
            if (pilihan.equalsIgnoreCase("Y")) {
                return true;
            }
            if (pilihan.equalsIgnoreCase("T")) {
                return false;
            }
            System.out.println("Masukkan Y atau T!");
        }
    }
 
    // ===== Tampilan =====
    private void baris(String teks) {
        System.out.printf("|%-51s|%n", teks);
    }
 
    private String tengah(String teks) {
        int kiri = Math.max(0, (51 - teks.length()) / 2);
        StringBuilder hasil = new StringBuilder();
        for (int i = 0; i < kiri; i++) {
            hasil.append(' ');
        }
        return hasil.append(teks).toString();
    }
 
    public void tampilkanHeaderLogin() {
        System.out.println();
        System.out.println("|===================================================|");
        baris(tengah("🚗 MY PARKIR GW 🚗"));
        baris(tengah("LOGIN"));
        System.out.println("|===================================================|");
    }
 
    public void tampilkanMenu() {
        System.out.println();
        System.out.println("|===================================================|");
        baris("");
        baris(tengah("🚗 MY PARKIR GW 🚗"));
        baris(tengah("SISTEM MANAJEMEN PARKIR KENDARAAN"));
        baris("");
        System.out.println("|===================================================|");
        baris("");
        baris("   [1]  Masuk Parkir (daftar + slot otomatis)");
        baris("   [2]  Lihat Data Parkir");
        baris("   [3]  Keluar Parkir (hitung & bayar)");
        baris("   [4]  Hapus Data Parkir");
        baris("   [5]  Cari Data Parkir");
        baris("   [6]  Lihat Slot Parkir");
        baris("   [7]  Keluar");
        baris("");
        System.out.println("-----------------------------------------------------");
    }
 
    public void tampilkanRingkasanSlot(int motorKosong, int motorTotal,
                                       int mobilKosong, int mobilTotal) {
        System.out.println("Slot kosong -> Motor: " + motorKosong + "/" + motorTotal
                + " | Mobil: " + mobilKosong + "/" + mobilTotal);
    }
 
    public void tampilkanKendaraan(ArrayList<Kendaraan> daftar) {
        System.out.println("\n========== DATA KENDARAAN ==========");
        for (Kendaraan kendaraan : daftar) {
            kendaraan.tampilkanInfo(); // polymorphism: Motor / Mobil
            System.out.println("------------------------------------");
        }
    }
 
    public void tampilkanSlot(ArrayList<SlotParkir> daftar) {
        System.out.println("\n========== DATA SLOT PARKIR ==========");
        System.out.printf("%-8s %-8s %-8s%n", "Slot", "Jenis", "Status");
        for (SlotParkir slot : daftar) {
            System.out.printf("%-8s %-8s %-8s%n",
                    slot.getNomorSlot(), slot.getJenisSlot(), slot.getStatusSlot());
        }
    }
 
    public void tampilkanParkir(ArrayList<Parkir> daftar) {
        System.out.println();
        if (daftar.isEmpty()) {
            System.out.println("Belum ada data parkir.");
            return;
        }
        for (Parkir parkir : daftar) {
            parkir.tampilkanInfo();
        }
    }
 
    public void pesan(String pesan) {
        System.out.println(pesan);
    }
}