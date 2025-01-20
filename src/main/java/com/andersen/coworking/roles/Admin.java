package com.andersen.coworking.roles;

import com.andersen.coworking.exception.WorkspaceNotFoundException;
import com.andersen.coworking.models.Workspace;
import com.andersen.coworking.models.WorkspaceType;
import com.andersen.coworking.utils.FileManager;

import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class Admin {
    private Set<Workspace> workspaceSet = new HashSet<>();
    private final FileManager workspaceFileManager;

    public Admin(FileManager workspaceFileManager) {
        this.workspaceFileManager = workspaceFileManager;
        this.workspaceSet = new HashSet<>(workspaceFileManager.readItems(Workspace.class));
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean isAdminRunning = true;

        while (isAdminRunning) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addWorkspace(scanner);
                case 2 -> {
                    try {
                        removeWorkspace(scanner);
                    } catch (WorkspaceNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> viewWorkspace();
                case 4 -> markAllWorkspacesAvailable();
                case 5 -> isAdminRunning = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        workspaceFileManager.writeItems(workspaceSet, Workspace.class);
    }

    public void displayMenu() {
        System.out.println("""
                --- Admin Menu ---
                1. Add a new coworking space
                2. Remove a coworking space
                3. View all the coworking spaces
                4. Mark all the coworking spaces available
                5. Exit to Main Menu
                Enter your choice:""");
    }

    private void addWorkspace(Scanner scanner) {
        System.out.print("Enter Workpsace ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (workspaceSet.stream().anyMatch(workspace -> workspace.getId() == id)) {
            System.out.println("Workspace ID already exists! Please choose another one.");
            return;
        }

        System.out.println("Enter Workspace Type. Available Workspace types are: ");
        for (WorkspaceType type : WorkspaceType.values()) {
            System.out.println("- " + type);
        }
        String typeInput = scanner.nextLine();

        WorkspaceType workspaceType;
        try {
            workspaceType = WorkspaceType.valueOf(typeInput.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Workspace Type! Please choose valid workspace type.");
            return;
        }

        System.out.print("Enter Workspace Price: ");
        double price = scanner.nextDouble();

        System.out.println("Is the Workspace Available? (true/false): ");
        boolean isAvailable = scanner.nextBoolean();

        Workspace workspace = new Workspace(id, price, isAvailable, workspaceType);
        workspaceSet.add(workspace);
        System.out.println("Workspace ID: " + id + " added successfully to the list.");
    }

    private void removeWorkspace(Scanner scanner) throws WorkspaceNotFoundException {
        System.out.println("Enter Workspace ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Optional<Workspace> workspaceOptional = workspaceSet.stream()
                    .filter(workspace -> workspace.getId() == id)
                    .findFirst();

            Workspace workspace = workspaceOptional.orElseThrow(() ->
                    new WorkspaceNotFoundException(String.format("Workspace with ID %d not found!", id)));
            workspaceSet.remove(workspace);
            System.out.println("Workspace ID: " + id + " removed successfully from the list.");
        } catch (WorkspaceNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewWorkspace() {
        System.out.println("\n--- List of Workspaces ---");
        if (workspaceSet.isEmpty()) {
            System.out.println("No Workspaces available!");
        } else {
            for (Workspace workspace : workspaceSet) {
                System.out.println(workspace);
            }
        }
    }

    public Set<Workspace> getWorkspaceSet() {
        return workspaceSet;
    }

    private void markAllWorkspacesAvailable() {
        workspaceSet.forEach(workspace -> workspace.setAvailable(true));
    }

}
