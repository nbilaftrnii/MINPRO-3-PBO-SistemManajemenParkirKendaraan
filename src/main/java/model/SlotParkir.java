/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class SlotParkir {
    public static final String KOSONG = "Kosong";
    public static final String TERISI = "Terisi";
 
    private final int idSlot;
    private final String nomorSlot;
    private final String jenisSlot;
    private String statusSlot;
 
    public SlotParkir(int idSlot, String nomorSlot, String jenisSlot) {
        this.idSlot = idSlot;
        this.nomorSlot = nomorSlot;
        this.jenisSlot = jenisSlot;
        this.statusSlot = KOSONG;
    }
 
    public int getIdSlot() {
        return idSlot;
    }
 
    public String getNomorSlot() {
        return nomorSlot;
    }
 
    public String getJenisSlot() {
        return jenisSlot;
    }
 
    public String getStatusSlot() {
        return statusSlot;
    }
 
    public boolean isKosong() {
        return statusSlot.equals(KOSONG);
    }
 
    public void setStatusSlot(String statusSlot) {
        if (!KOSONG.equals(statusSlot) && !TERISI.equals(statusSlot)) {
            throw new IllegalArgumentException("Status slot hanya Kosong atau Terisi!");
        }
        this.statusSlot = statusSlot;
    }
}