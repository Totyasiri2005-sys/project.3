package project3;

import java.io.*;
import java.util.*;

public class RegistrationSystem {

    private ArrayList<Student> students = new ArrayList<>();
    private final String fileName = "students.txt";

    public RegistrationSystem() {
        loadFromFile();
    }

    // VALIDATIONS
    public boolean isValidKKUId(String id) {
        if (id.length() != 9) return false;
        if (!id.matches("\\d{9}")) return false;
        return id.charAt(0) == '4';
    }

    public boolean isValidNumCourses(int n) {
        return n >= 1 && n <= 10;
    }

    public boolean isValidMark(double m) {
        return m >= 0 && m <= 100;
    }

    public void addStudent(Student s) {
        students.add(s);
        saveToFile();
        System.out.println("Student added successfully!");
    }

    public Student findStudent(String id) {
        for (Student s : students)
            if (s.kkuId.equals(id)) return s;
        return null;
    }

    public void enterMarks(String id, Scanner sc) {
        Student s = findStudent(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        for (int i = 0; i < s.numCourses; i++) {
            double mark;
            do {
                System.out.print("Enter mark for course " + (i + 1) + " (0-100): ");
                while (!sc.hasNextDouble()) {
                    System.out.println("Please enter a valid number.");
                    sc.next();
                }
                mark = sc.nextDouble();
                if (!isValidMark(mark))
                    System.out.println("Invalid mark. Must be 0–100.");
            } while (!isValidMark(mark));

            s.marks[i] = mark;
        }

        saveToFile();
        System.out.println("Marks saved!");
    }

    public void displayMarks(String id) {
        Student s = findStudent(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        s.displayStudentDetails();
        System.out.println("Average: " + s.calculateAverage());
        System.out.println("Max: " + s.findMax());
        System.out.println("Min: " + s.findMin());
        System.out.println(s.isPass() ? "✔ PASS" : "✘ FAIL");
    }

    public void modifyMarks(String id, Scanner sc) {
        Student s = findStudent(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        for (int i = 0; i < s.numCourses; i++) {
            System.out.print("New mark for course " + (i + 1) + ": ");
            s.marks[i] = sc.nextDouble();
        }
        saveToFile();
        System.out.println("Marks updated!");
    }

    public void showAllStudents() {
        for (Student s : students)
            System.out.println(s.kkuId + " - " + s.name);
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {

            for (Student s : students) {

                pw.print((s instanceof Undergraduate ? "U" : "G") + ",");
                pw.print(s.kkuId + ",");
                pw.print(s.name + ",");
                pw.print(s.numCourses);

                for (double m : s.marks)
                    pw.print("," + m);

                pw.println();
            }
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }

    public void loadFromFile() {
        try (Scanner sc = new Scanner(new File(fileName))) {

            while (sc.hasNextLine()) {
                String[] arr = sc.nextLine().split(",");
                String type = arr[0];
                String id = arr[1];
                String name = arr[2];
                int num = Integer.parseInt(arr[3]);

                Student st = type.equals("U") ?
                        new Undergraduate(id, name, num) :
                        new Graduate(id, name, num);

                for (int i = 0; i < num; i++)
                    st.marks[i] = Double.parseDouble(arr[4 + i]);

                students.add(st);
            }
        } catch (Exception e) {
            System.out.println("No previous data found.");
        }
    }
}