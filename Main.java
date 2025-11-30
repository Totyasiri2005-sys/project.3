package project3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RegistrationSystem rs = new RegistrationSystem();

        int choice;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Enter Student Details");
            System.out.println("2. Enter Marks");
            System.out.println("3. Display Marks Info");
            System.out.println("4. Display Student Details");
            System.out.println("5. Modify Marks");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.next();
            }

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    String id;
                    do {
                        System.out.print("Enter KKU ID (9 digits, starts with 4): ");
                        id = sc.next();
                        if (!rs.isValidKKUId(id)) {
                            System.out.println("Invalid KKU ID. Try again.");
                        }
                    } while (!rs.isValidKKUId(id));

                    System.out.print("Enter student name: ");
                    String name = sc.next();

                    int num;
                    do {
                        System.out.print("Enter number of courses (1–10): ");
                        num = sc.nextInt();
                        if (!rs.isValidNumCourses(num)) {
                            System.out.println("Invalid number of courses.");
                        }
                    } while (!rs.isValidNumCourses(num));

                    System.out.print("Graduate (G) or Undergraduate (U)? ");
                    String t = sc.next();

                    Student st = t.equalsIgnoreCase("U") ?
                            new Undergraduate(id, name, num) :
                            new Graduate(id, name, num);

                    rs.addStudent(st);
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    rs.enterMarks(sc.next(), sc);
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    rs.displayMarks(sc.next());
                    break;

                case 4:
                    rs.showAllStudents();
                    break;

                case 5:
                    System.out.print("Enter ID: ");
                    rs.modifyMarks(sc.next(), sc);
                    break;

                case 6:
                    rs.saveToFile();
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);
    }
}