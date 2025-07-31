//Task 1: Library Management System with JDBC

package LibraryManagement;

import java.sql.*;
import java.util.Scanner;

public class LibrarySystem {
    // Database connection
    static final String DB_URL = "jdbc:mysql://localhost:3306/library_db";
    static final String USER = "root"; // ← apna username
    static final String PASS = "DrE@m131"; // ← apna password

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. View All Books");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBook(sc);
                    break;
                case 2:
                    borrowBook(sc);
                    break;
                case 3:
                    returnBook(sc);
                    break;
                case 4:
                    viewAllBooks();
                    break;
                case 5:
                    System.out.println("Exit.");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void addBook(Scanner sc) {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();

        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO Books (title, author) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void borrowBook(Scanner sc) {
        System.out.print("Enter book ID to borrow: ");
        int bookId = sc.nextInt();

        try (Connection conn = getConnection()) {
            String check = "SELECT available FROM Books WHERE book_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(check);
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                boolean isAvailable = rs.getBoolean("available");
                if (isAvailable) {
                    String borrow = "UPDATE Books SET available = FALSE WHERE book_id = ?";
                    PreparedStatement borrowStmt = conn.prepareStatement(borrow);
                    borrowStmt.setInt(1, bookId);
                    borrowStmt.executeUpdate();
                    System.out.println("Book borrowed successfully!");
                } else {
                    System.out.println("Book is not available.");
                }
            } else {
                System.out.println("Book ID not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void returnBook(Scanner sc) {
        System.out.print("Enter book ID to return: ");
        int bookId = sc.nextInt();

        try (Connection conn = getConnection()) {
            String sql = "UPDATE Books SET available = TRUE WHERE book_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookId);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Book returned successfully!");
            } else {
                System.out.println("Book ID not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void viewAllBooks() {
        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM Books";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\nBook ID | Title               | Author             | Available");
            System.out.println("---------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("book_id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                boolean available = rs.getBoolean("available");

                String status = available ? "Yes" : "No";
                System.out.printf("%-7d | %-18s | %-18s | %-9s%n", id, title, author, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

