/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */

//Interface, dibuat sebagai kontrak untuk semua yang punya tarif parkir.
//Kendaraan (dan turunannya Motor/Mobil) wajib menyediakan isinya.
public interface Bertarif {
    double hitungTarif(int lamaJam);
}
