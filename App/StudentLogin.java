package App;

import java.sql.*;
import java.util.Scanner;

public class StudentLogin {
    static Scanner cin = new Scanner(System.in);

    public static void displayStudentLoginPage() {
        System.out.println("Student Login Page");
        System.out.println("\n1.Sign-in\n2.Go Back");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                // Add sign-in logic here
                System.out.println("Enter your username: ");
                String username = cin.next();
                System.out.println("Enter your password: ");
                String password = cin.next();
                if (verifyStudent(username, password)) {
                    System.out.println("Sign-in successful....");
                } else {
                    System.out.println("Invalid username or password. Please try again.");
                    displayStudentLoginPage();
                }
                break;
            case 2:
                App.displayHomePage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayStudentLoginPage();
                break;
        }
    }

    public static boolean verifyStudent(String username, String password) {

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD)) {
            String query = "SELECT * FROM students WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }
}
