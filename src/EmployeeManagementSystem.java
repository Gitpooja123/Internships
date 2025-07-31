//Task 3: Employee Management System

import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    void display() {
        System.out.println("ID: " + id + " | Name: " + name + " | Salary: ₹" + salary);
    }
}
public class EmployeeManagementSystem {
    static ArrayList<Employee> employees = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public static void addEmployee() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();
        employees.add(new Employee(id, name, salary));
        System.out.println("Employee added successfully.\n");
    }
    public static void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.\n");
            return;
        }
        System.out.println("----- Employee List -----");
        for (Employee emp : employees) {
            emp.display();
        }
        System.out.println();
    }
    public static void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();
        for (Employee emp : employees) {
            if (emp.id == id) {
                sc.nextLine();
                System.out.print("Enter New Name: ");
                emp.name = sc.nextLine();
                System.out.print("Enter New Salary: ");
                emp.salary = sc.nextDouble();
                System.out.println("Employee updated successfully.\n");
                return;
            }
        }
        System.out.println("Employee not found.\n");
    }
    public static void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();
        for (Employee emp : employees) {
            if (emp.id == id) {
                employees.remove(emp);
                System.out.println("Employee deleted successfully.\n");
                return;
            }
        }
        System.out.println("Employee not found.\n");
    }
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> updateEmployee();
                case 4 -> deleteEmployee();
                case 5 -> System.out.println("Exit");
                default -> System.out.println("Invalid choice!\n");
            }
        } while (choice != 5);
    }
}
