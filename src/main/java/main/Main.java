/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import model.*;
import service.ParkirService;
import controller.ParkirController;
import view.ParkirView;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
 
        ParkirService service = new ParkirService();
        ParkirView view = new ParkirView(input);
        ParkirController controller = new ParkirController(service, view);
 
        controller.jalankan();
 
        input.close();
    }
}