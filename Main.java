import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        Admin admin = new Admin();
        Client client = new Client();

        while (running) {
            System.out.println("""
                Welcome to the Coworking Space Reservation App!
                1. Admin Login
                2. Client Login
                3. Exit
                Enter your choice:""");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    admin.menu();
                    break;
                case 2:
                    client.menu();
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
