package com.andersen.coworking.roles;

import com.andersen.coworking.models.Reservation;
import com.andersen.coworking.models.Workspace;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Client {
    private static Map<Integer, Reservation> reservationMap = new HashMap<>();
    private static int reservationCounter = 1;
    private final Admin admin;

    public Client(Admin admin) {
        this.admin = admin;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean isClientRunning = true;

        while (isClientRunning) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> browseAvailableSpaces();
                case 2 -> makeReservation(scanner);
                case 3 -> viewReservations();
                case 4 -> cancelReservation(scanner);
                case 5 -> isClientRunning = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void displayMenu() {
        System.out.println("""
                --- Client Menu ---
                1. Browse available spaces
                2. Make a reservation
                3. View my reservations
                4. Cancel a reservation
                5. Exit to Main Menu.
                Enter your choice:""");
    }

    private void browseAvailableSpaces() {
        System.out.println("\n--- Available Workspaces ---");
        boolean found = false;
        for (Workspace workspace : admin.getWorkspaceSet()) {
            if (workspace.isAvailable()) {
                System.out.println(workspace.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available workspaces at the moment");
        }
    }

    private void makeReservation(Scanner scanner) {
        System.out.print("Enter Workspace ID to reserve: ");
        int workspaceId = scanner.nextInt();
        scanner.nextLine();

        Workspace workspace = admin.getWorkspaceSet().stream()
                .filter(w -> w.getId() == workspaceId && w.isAvailable())
                .findFirst()
                .orElse(null);

        if (workspace == null) {
            System.out.println("Invalid Workspace ID or the workspace is not available.");
            return;
        }

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your reservation date DD-MM-YYYY: ");
        String date = scanner.nextLine();

        System.out.print("Enter start time HH:MM: ");
        String startTime = scanner.nextLine();

        System.out.print("Enter end time HH:MM: ");
        String endTime = scanner.nextLine();

        Reservation reservation = new Reservation(reservationCounter++, workspaceId, name, date, startTime, endTime);
        reservationMap.put(reservation.getReservationId(), reservation);

        workspace.setAvailable(false);
        System.out.println("Reservation successful. Your Reservation ID: " + reservation.getReservationId());
    }

    private void viewReservations() {
        System.out.println("\n--- My Reservations ---");
        if (reservationMap.isEmpty()) {
            System.out.println("No reservation found.");
        } else {
            for (Reservation reservation : reservationMap.values()) {
                System.out.println(reservation);
            }
        }
    }

    private void cancelReservation(Scanner scanner) {
        System.out.print("Enter Reservation ID to cancel: ");
        int reservationId = scanner.nextInt();

        Reservation reservation = reservationMap.get(reservationId);

        if (reservation == null) {
            System.out.println("Reservation not found.");
            return;
        }

        reservationMap.remove(reservationId);

        Workspace workspace = admin.getWorkspaceSet().stream()
                .filter(w -> w.getId() == reservation.getWorkspaceId())
                .findFirst()
                .orElse(null);

        if (workspace == null) {
            workspace.setAvailable(false);
        }

        System.out.println("Reservation cancelled successfully.");
    }
}
