import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomClassLoader customClassLoader = new CustomClassLoader();

        try {
            Class<?> dynamicClass = customClassLoader.loadClass("Admin");

           System.out.println("Class loaded: " + dynamicClass.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        FileManager workspaceFileManager = new FileManager("workspaceState");
        Admin admin = new Admin(workspaceFileManager);
        Client client = new Client(admin);

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
