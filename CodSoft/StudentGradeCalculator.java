//Task--->2
//Student Grade Calculator
import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Student Grade Calculator!");

        System.out.print("Enter the number of subjects that you have too calculate: ");
        int subjects = sc.nextInt();
        sc.nextLine(); // buffer clear

        String[] subjectNames = new String[subjects];
        int[] marks = new int[subjects];
        int totalMarks = 0;

        // Input subject names and marks
        for (int i = 0; i < subjects; i++) {
            System.out.print("\nEnter name of subject " + (i + 1) + ": ");
            subjectNames[i] = sc.nextLine();

            System.out.print("Enter marks for " + subjectNames[i] + " (out of 100): ");
            marks[i] = sc.nextInt();
            sc.nextLine();

            totalMarks += marks[i];
        }

        // Calculate percentage
        double percentage = (double) totalMarks / subjects;

        // Grade based on percentage ranges
        String grade;
        String message;
        if (percentage >= 90 && percentage <= 100) {
            System.out.println("*****Outstanding Performance*****");
            grade = "A+";
        } else if (percentage >= 80 && percentage < 90) {
            System.out.println("*****Excellent Work*****");
            grade = "A";
        } else if (percentage >= 70 && percentage < 80) {
            System.out.println("*****Very Good Job*****");
            grade = "B";
        } else if (percentage >= 60 && percentage < 70) {
            System.out.println("*****Good Effort*****");
            grade = "C";
        } else if (percentage >= 50 && percentage < 60) {
            System.out.println("*****Needs Improvement*****");
            grade = "D";
        } else if (percentage >= 0 && percentage < 50) {
            grade = "F";
            System.out.println("*****Work Harder Next Time*****");

        } else {
            grade = "Invalid Percentage";
        }

        // Display result
        System.out.println("\nResult Summary:");
        for (int i = 0; i < subjects; i++) {
            System.out.println( subjectNames[i] + ": " + marks[i] + "/100");
        }

        System.out.println("Total Marks: " + totalMarks);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Grade: " + grade);

        sc.close();
    }
}
