import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static List<Reservation> reservationList = new ArrayList<>();
    private static int reservationCounter = 1;
    private Admin admin;

    public Client(Admin admin) {
        this.admin = admin;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean isClientRunning = true;

        while (isClientRunning) {
            System.out.println("""
                --- Client Menu ---
                1. Browse available spaces
                2. Make a reservation
                3. View my reservations
                4. Cancel a reservation
                5. Exit to Main Menu.
                Enter your choice:""");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    browseSpaces();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    cancelReservation(scanner);
                    break;
                case 5:
                    isClientRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void browseSpaces() {
        System.out.println("\n--- Available Workspaces ---");
        boolean found = false;
        for (Workspace workspace : admin.getWorkspaceList()) {
            if (workspace.isAvailable()) {
                System.out.println(workspace);
                found = true;
            }
        }

        if (!found)
            System.out.println("No available workspaces at the moment.");
    }

    private void makeReservation(Scanner scanner) {
        System.out.print("Enter Workspace ID to reserve: ");
        int workspaceId = scanner.nextInt();
        scanner.nextLine();

        Workspace workspace = admin.getWorkspaceList().stream()
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
        reservationList.add(reservation);

        workspace.setAvailable(false);
        System.out.println("Reservation successful! Your Reservation ID is " + reservation.getReservationId());
    }

    private void viewReservations(){
        System.out.println("\n--- My Reservations ---");
        if (reservationList.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservationList) {
                System.out.println(reservation);
            }
        }
    }

    private void cancelReservation(Scanner scanner) {
        System.out.print("Enter Reservation ID to cancel: ");
        int reservationId = scanner.nextInt();

        Reservation reservation = reservationList.stream()
                .filter(r -> r.getReservationId() == reservationId)
                .findFirst()
                .orElse(null);

        if (reservation == null) {
            System.out.println("Reservation not found. ");
            return;
        }

        reservationList.remove(reservation);

        Workspace workspace = admin.getWorkspaceList().stream()
                .filter(w -> w.getId() == reservation.getWorkspaceId())
                .findFirst()
                .orElse(null);

        if(workspace != null) {
            workspace.setAvailable(true);
        }

        System.out.println("Reservation canceled successfully");
    }
}
