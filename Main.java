import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Coworking Space Reservation App!");
            System.out.println("1. Admin Login");
            System.out.println("2. Client Login");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Admin.menu();
                    break;
                case 2:
                    Client.menu();
                    break;
                case 3:
                    running = false;
                    System.out.println("Thank you for using our App. Bye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}
