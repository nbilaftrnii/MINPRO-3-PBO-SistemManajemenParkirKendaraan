/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
/**
 *
 * @author ASUS
 */
public class Parkir {
    public static final String MASIH_PARKIR = "Masih Parkir";
    public static final String SELESAI = "Selesai";
     public static final DateTimeFormatter FORMAT_WAKTU =
            DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm")
                    .withResolverStyle(ResolverStyle.STRICT);
 
    private final int idParkir;
    private final Kendaraan kendaraan;
    private final Petugas petugas;
    private final SlotParkir slot;
    private final String waktuMasuk;
    private String waktuKeluar;
    private String statusParkir;
    private String metodePembayaran;
    private double jumlahBayar;
    private String statusPembayaran;
 
    public Parkir(int idParkir, Kendaraan kendaraan, Petugas petugas,
                  SlotParkir slot, String waktuMasuk) {
        this.idParkir = idParkir;
        this.kendaraan = kendaraan;
        this.petugas = petugas;
        this.slot = slot;
        this.waktuMasuk = waktuMasuk;
        this.waktuKeluar = "-";
        this.statusParkir = MASIH_PARKIR;
        this.metodePembayaran = "-";
        this.jumlahBayar = 0;
        this.statusPembayaran = "Belum Bayar";
    }
 
    public int getIdParkir() {
        return idParkir;
    }
 
    public Kendaraan getKendaraan() {
        return kendaraan;
    }
 
    public Petugas getPetugas() {
        return petugas;
    }
 
    public SlotParkir getSlot() {
        return slot;
    }
 
    public String getWaktuMasuk() {
        return waktuMasuk;
    }
 
    public String getWaktuKeluar() {
        return waktuKeluar;
    }
 
    public String getStatusParkir() {
        return statusParkir;
    }
 
    public String getMetodePembayaran() {
        return metodePembayaran;
    }
 
    public double getJumlahBayar() {
        return jumlahBayar;
    }
 
    public String getStatusPembayaran() {
        return statusPembayaran;
    }
 
    public void setWaktuKeluar(String waktuKeluar) {
        if (waktuKeluar == null || waktuKeluar.trim().isEmpty()) {
            throw new IllegalArgumentException("Waktu keluar tidak boleh kosong!");
        }
        this.waktuKeluar = waktuKeluar;
    }
 
    public void setStatusParkir(String statusParkir) {
        if (!MASIH_PARKIR.equals(statusParkir) && !SELESAI.equals(statusParkir)) {
            throw new IllegalArgumentException("Status parkir tidak valid!");
        }
        this.statusParkir = statusParkir;
    }
 
    public void setMetodePembayaran(String metodePembayaran) {
        if (!"Cash".equals(metodePembayaran) && !"QRIS".equals(metodePembayaran)
                && !"-".equals(metodePembayaran)) {
            throw new IllegalArgumentException("Metode pembayaran hanya Cash atau QRIS!");
        }
        this.metodePembayaran = metodePembayaran;
    }
 
    public void setJumlahBayar(double jumlahBayar) {
        if (jumlahBayar < 0) {
            throw new IllegalArgumentException("Jumlah bayar tidak boleh negatif!");
        }
        this.jumlahBayar = jumlahBayar;
    }
 
    public void setStatusPembayaran(String statusPembayaran) {
        if (!"Belum Bayar".equals(statusPembayaran) && !"Belum Lunas".equals(statusPembayaran)
                && !"Lunas".equals(statusPembayaran)) {
            throw new IllegalArgumentException("Status pembayaran tidak valid!");
        }
        this.statusPembayaran = statusPembayaran;
    }
 
    public double hitungTarif(int lamaJam) {
        return kendaraan.hitungTarif(lamaJam);
    }
 
    public String hitungWaktuKeluar(int lamaJam) {
        LocalDateTime masuk = LocalDateTime.parse(waktuMasuk, FORMAT_WAKTU);
        return masuk.plusHours(lamaJam).format(FORMAT_WAKTU);
    }
 
    private void cetak(String label, Object nilai) {
        System.out.printf("%-17s: %s%n", label, nilai);
    }
 
    public void tampilkanInfo() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("       🚗 Data on MY PARKIR GW 🚗");
        System.out.println("========================================");
        System.out.println("ID Parkir        : " + idParkir);
        System.out.println("Nomor Plat       : " + kendaraan.getNomorPlat());
        System.out.println("Jenis Kendaraan  : " + kendaraan.getJenisKendaraan());
        System.out.println("Slot             : " + slot.getNomorSlot());
        System.out.println("Petugas          : " + petugas.getNamaPetugas());
        System.out.println("Waktu Masuk      : " + waktuMasuk);
        System.out.println("Waktu Keluar     : " + waktuKeluar);
        System.out.println("Status Parkir    : " + statusParkir);
        System.out.println("Status Pembayaran: " + statusPembayaran);
        System.out.println("----------------------------------------");
    }
 
    public void cetakStruk(int lamaJam) {
        int tarif = (int) hitungTarif(lamaJam);
        int bayar = (int) jumlahBayar;
        int kembalian = Math.max(0, bayar - tarif);
 
        System.out.println();
        System.out.println("========================================");
        System.out.println("              💰 Receipt");
        System.out.println("             MY PARKIR GW");
        System.out.println("========================================");
        System.out.println("ID Parkir        : " + idParkir);
        System.out.println("Nomor Plat       : " + kendaraan.getNomorPlat());
        System.out.println("Jenis Kendaraan  : " + kendaraan.getJenisKendaraan());
        System.out.println("Slot             : " + slot.getNomorSlot());
        System.out.println("Petugas          : " + petugas.getNamaPetugas());
        System.out.println("----------------------------------------");
        System.out.println("Waktu Masuk      : " + waktuMasuk);
        System.out.println("Waktu Keluar     : " + waktuKeluar);
        System.out.println("Lama Parkir      : " + lamaJam + " jam");
        System.out.println("----------------------------------------");
        System.out.println("Total Tarif      : Rp" + tarif);
        System.out.println("Metode Pembayaran: " + metodePembayaran);
        System.out.println("Jumlah Bayar     : Rp" + bayar);
        System.out.println("Kembalian        : Rp" + kembalian);
        System.out.println("Status Pembayaran: " + statusPembayaran);
        System.out.println("========================================");
        System.out.println("              Thank You");
        System.out.println("         HATI-HATI DI JALAN!");
        System.out.println("========================================");
    }
}