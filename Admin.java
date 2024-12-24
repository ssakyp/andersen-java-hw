import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {
    private static List<Workspace> workspaceList;
    private WorkspaceFileManager workspaceFileManager;

    public Admin(WorkspaceFileManager workspaceFileManager) {
        this.workspaceFileManager = workspaceFileManager;
        this.workspaceList = workspaceFileManager.readWorkspaces();
    }

    public Admin(){};

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean isAdminRunning = true;
        while (isAdminRunning) {
            System.out.println("""
                --- Admin Menu ---
                1. Add a new coworking space
                2. Remove a coworking space
                3. View all coworking spaces
                4. Exit to Main Menu
                Enter your choice:""");

            int choice = scanner.nextInt();
            scanner.nextLine(); // for new line

            switch (choice) {
                case 1:
                    addWorkspace(scanner);
                    break;
                case 2:
                    try {
                        removeWorkspace(scanner);
                    } catch (WorkspaceNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    viewWorkspace(scanner);
                    break;
                case 4:
                    isAdminRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        workspaceFileManager.writeWorkspaces(workspaceList);
    }

    private void addWorkspace(Scanner scanner) {
        System.out.print("Enter Workspace ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // for new line

        System.out.print("Enter Workspace Type (Open Space, Private Room, etc.): ");
        String type = scanner.nextLine();

        System.out.print("Enter Workspace Price: ");
        double price = scanner.nextDouble();

        System.out.print("Is the Workspace Available? (true/false): ");
        boolean isAvailable = scanner.nextBoolean();

        Workspace workspace = new Workspace(id, isAvailable, type, price);
        workspaceList.add(workspace);
        System.out.println("Workspace added successfully.");
    }

    private void removeWorkspace(Scanner scanner) throws WorkspaceNotFoundException {
        System.out.print("Enter Workspace ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removed = workspaceList.removeIf(workspace -> workspace.getId() == id);
        if(!removed) {
            throw new WorkspaceNotFoundException("Workspace with ID " + id + " not found.");
        } else
        System.out.println("Workspace removed successfully.");

    }

    private void viewWorkspace(Scanner scanner) {
        System.out.println("\n--- List of Workspaces ---");
        if (workspaceList.isEmpty())
            System.out.println("No Workspace available");
        else {
            for (Workspace workspace : workspaceList) {
                System.out.println(workspace);
            }
        }
    }

    public List<Workspace> getWorkspaceList() {
        return workspaceList;
    }
}
