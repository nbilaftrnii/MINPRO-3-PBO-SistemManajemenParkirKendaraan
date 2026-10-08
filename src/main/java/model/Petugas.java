/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */

//Hanya petugas yang boleh login ke sistem
//Petugas yang masuk ke sistem otomatis tercatat di setiap transaksi parkir.

public class Petugas {
    private String namaPetugas;
    private String password;
 
    public Petugas(String namaPetugas, String password) {
        setNamaPetugas(namaPetugas);
        setPassword(password);
    }
 
    public String getNamaPetugas() {
        return namaPetugas;
    }
 
    public void setNamaPetugas(String namaPetugas) {
        if (namaPetugas == null || namaPetugas.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama petugas tidak boleh kosong!");
        }
        this.namaPetugas = namaPetugas.trim();
    }
 
    public void setPassword(String password) {
    if (password == null || password.trim().isEmpty()) {
        throw new IllegalArgumentException("Password tidak boleh kosong!");
    }
    this.password = password;
}
 
    public boolean cekLogin(String nama, String password) {
        return namaPetugas.equals(nama.trim()) && this.password.equals(password);
    }
}