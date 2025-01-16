# Coworking Space Reservation App

This is a coworking space reservation app built using Java. The app allows users to browse available coworking spaces, make reservations, view their reservations, and cancel them. Admin users can add, remove, and manage spaces.

## Features

- **Client Features:**
    - Browse available coworking spaces.
    - Make reservations for available spaces.
    - View existing reservations.
    - Cancel reservations.

- **Admin Features:**
    - Add new coworking spaces.
    - Remove coworking spaces.
    - View all coworking spaces.
    - Mark coworking spaces as available or unavailable.

## Technologies Used

- Java
- Maven
- IntelliJ IDEA (IDE)
- Git and GitHub for version control

## Setup

To run the project locally, follow these steps:

1. **Clone the repository:**

    ```bash
    git clone https://github.com/ssakyp/andersen-java-hw.git
    cd andersen-java-hw
    ```

2. **Build the project using Maven:**

   If you have Maven installed, run the following command to compile and install the dependencies:

    ```bash
    mvn clean install
    ```

   Alternatively, you can build the project directly from IntelliJ IDEA.

3. **Run the application:**

   You can run the application directly from your IDE (e.g., IntelliJ IDEA) or use Maven to run it:

    ```bash
    mvn exec:java -Dexec.mainClass="com.andersen.coworking.main.Main"
    ```

4. **Enjoy the application!**

   The app will start and you can interact with the menu for both the admin and client roles.

## How to Contribute

Feel free to fork this project, make changes, and create a pull request. Contributions are always welcome!

## License

This project is open-source and available under the [MIT License](LICENSE).
