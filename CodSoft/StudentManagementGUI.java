//Task--->5
// STUDENT MANAGEMENT SYSTEM

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class Student {
    private String name;
    private int rollNumber;
    private String grade;
    private int age;
    private String phone;

    public Student(String name, int rollNumber, String grade, int age, String phone) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.age = age;
        this.phone = phone;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String toFileString() {
        return name + "," + rollNumber + "," + grade + "," + age + "," + phone;
    }

    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        return new Student(parts[0], Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), parts[4]);
    }

    public String getDetails() {
        return "Name: " + name + ", Roll: " + rollNumber + ", Grade: " + grade + ", Age: " + age + ", Phone: " + phone;
    }
}

class StudentManagementSystem {
    private final String FILE_NAME = "students.txt";
    private ArrayList<Student> students = new ArrayList<>();

    public StudentManagementSystem() {
        loadFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    public String getAllStudents() {
        if (students.isEmpty())
            return "No students found!";
        StringBuilder sb = new StringBuilder();
        for (Student s : students) {
            sb.append(s.getDetails()).append("\n");
        }
        return sb.toString();
    }

    public String searchStudent(int roll) {
        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                return s.getDetails();
            }
        }
        return "Student not found!";
    }

    public String removeStudent(int roll) {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getRollNumber() == roll) {
                it.remove();
                saveToFile();
                return "Student removed successfully!";
            }
        }
        return "Student not found!";
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                writer.println(s.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(Student.fromFileString(line));
            }
        } catch (IOException ignored) {}
    }
}

public class StudentManagementGUI extends JFrame {
    private StudentManagementSystem sms = new StudentManagementSystem();

    private JTextField nameField, rollField, gradeField, ageField, phoneField, searchRollField;
    private JTextArea outputArea;

    public StudentManagementGUI() {
        setTitle("Student Management System - GUI");
        setSize(600, 600);
        setLayout(new BorderLayout());

        //For Input
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        nameField = new JTextField();
        rollField = new JTextField();
        gradeField = new JTextField();
        ageField = new JTextField();
        phoneField = new JTextField();
        searchRollField = new JTextField();

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Roll Number:"));
        inputPanel.add(rollField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Phone:"));
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("Search/Remove Roll No:"));
        inputPanel.add(searchRollField);

        add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton addBtn = new JButton("Add Student");
        JButton showBtn = new JButton("Show All");
        JButton searchBtn = new JButton("Search");
        JButton removeBtn = new JButton("Remove");

        buttonPanel.add(addBtn);
        buttonPanel.add(showBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(removeBtn);

        add(buttonPanel, BorderLayout.CENTER);

        // Output Area
        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        // Button Actions
        addBtn.addActionListener(e -> addStudent());
        showBtn.addActionListener(e -> outputArea.setText(sms.getAllStudents()));
        searchBtn.addActionListener(e -> searchStudent());
        removeBtn.addActionListener(e -> removeStudent());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addStudent() {
        try {
            String name = nameField.getText().trim();
            int roll = Integer.parseInt(rollField.getText().trim());
            String grade = gradeField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || grade.isEmpty() || phone.isEmpty()) {
                outputArea.setText("All fields are required!");
                return;
            }

            sms.addStudent(new Student(name, roll, grade, age, phone));
            outputArea.setText("Student added successfully!");

            // Clear inputs
            nameField.setText("");
            rollField.setText("");
            gradeField.setText("");
            ageField.setText("");
            phoneField.setText("");

        } catch (NumberFormatException ex) {
            outputArea.setText("Invalid number format in roll/age.");
        }
    }

    private void searchStudent() {
        try {
            int roll = Integer.parseInt(searchRollField.getText().trim());
            String result = sms.searchStudent(roll);
            outputArea.setText(result);
        } catch (NumberFormatException ex) {
            outputArea.setText("Please enter valid roll number.");
        }
    }

    private void removeStudent() {
        try {
            int roll = Integer.parseInt(searchRollField.getText().trim());
            String result = sms.removeStudent(roll);
            outputArea.setText(result);
        } catch (NumberFormatException ex) {
            outputArea.setText("Please enter valid roll number.");
        }
    }

    public static void main(String[] args) {
        new StudentManagementGUI();
    }
}
