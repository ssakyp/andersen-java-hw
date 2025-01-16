package com.andersen.coworking.services;

import com.andersen.coworking.roles.Admin;
import com.andersen.coworking.roles.Client;

import java.util.Scanner;

public class MenuService {
    private final Admin admin;
    private final Client client;

    public MenuService(Admin admin, Client client) {
        this.admin = admin;
        this.client = client;
    }

    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("""
                Welcome to the Coworking Space Reservation App!
                1. Admin Login
                2. Client Login
                3. Exit
                Enter your choice:""");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> admin.menu();
                case 2 -> client.menu();
                case 3 -> {
                    running = false;
                    System.out.println("Thank you for using Coworking Space Reservation App!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
