import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

        while(isAdminRunning) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1 -> addWorkspace(scanner);
                case 2 -> {
                    try {
                        removeWorkspace(scanner);
                    } catch (WorkspaceNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> viewWorkspaces();
                case 4 -> markAllWorkspacesAvailable();
                case 5 -> isAdminRunning = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        workspaceFileManager.writeItems(workspaceSet, Workspace.class);
    }

    private void displayMenu(){
        System.out.println("""
                --- Admin Menu ---
                1. Add a new coworking space
                2. Remove a coworking space
                3. View all coworking spaces
                4. Mark all coworking spaces available
                5. Exit to Main Menu
                Enter your choice:""");
    }

    private void addWorkspace(Scanner scanner) {
        System.out.print("Enter Workspace ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // for new line

        if (workspaceSet.stream().anyMatch(workspace -> workspace.getId() == id)) {
            System.out.println("Workspace ID already exists. Please use a unique ID.");
            return;
        }

        System.out.print("Enter Workspace Type (Open Space, Private Room, etc.): ");
        String type = scanner.nextLine();

        System.out.print("Enter Workspace Price: ");
        double price = scanner.nextDouble();

        System.out.print("Is the Workspace Available? (true/false): ");
        boolean isAvailable = scanner.nextBoolean();

        Workspace workspace = new Workspace(id, isAvailable, type, price);
        workspaceSet.add(workspace);
        System.out.println("Workspace added successfully.");
    }

    private void removeWorkspace(Scanner scanner) throws WorkspaceNotFoundException {
        System.out.print("Enter Workspace ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Optional<Workspace> workspaceOptional = workspaceSet.stream()
                    .filter(workspace -> workspace.getId() == id)
                    .findFirst();

                    Workspace workspace = workspaceOptional.orElseThrow(() ->
                        new WorkspaceNotFoundException("Workspace with ID " + id + " not found."));
                    workspaceSet.remove(workspace);
                    System.out.println("Workspace removed successfully.");
                    } catch (WorkspaceNotFoundException e) {
                         System.out.println(e.getMessage());;
                    }

    }

    private void viewWorkspaces() {
        System.out.println("\n--- List of Workspaces ---");
        if (workspaceSet.isEmpty())
            System.out.println("No Workspace available");
        else {
            for (Workspace workspace : workspaceSet) {
                System.out.println(workspace);
            }
        }
    }

    public Set<Workspace> getWorkspaceList() {
        return workspaceSet;
    }

    private void markAllWorkspacesAvailable() {
        workspaceSet.forEach(workspace -> workspace.setAvailable(true));
    }
}
