import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    private String id;
    private String name;
    private String department;
    private double salary;

    public Employee(String id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Department: " + department + ", Salary: $" + salary;
    }
}

public class EmployeeManagementApp {
    private static ArrayList<Employee> employeeList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Employee Management Application");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee");
            System.out.println("3. Update Employee Salary");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewEmployee();
                    break;
                case 3:
                    updateSalary();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        System.out.println("Thank you for using the Employee Management Application!");
        scanner.close();
    }

    private static void addEmployee() {
        System.out.print("Enter employee ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee department: ");
        String department = scanner.nextLine();
        System.out.print("Enter employee salary: $");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Employee employee = new Employee(id, name, department, salary);
        employeeList.add(employee);
        System.out.println("Employee added successfully.");
    }

    private static void viewEmployee() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            System.out.println("Employee List:");
            for (Employee employee : employeeList) {
                System.out.println(employee.toString());
            }
        }
    }

    private static void updateSalary() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees to update salary.");
        } else {
            System.out.print("Enter employee ID to update salary: ");
            String id = scanner.nextLine();

            boolean found = false;
            for (Employee employee : employeeList) {
                if (employee.getId().equals(id)) {
                    System.out.print("Enter new salary: $");
                    double newSalary = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    employee.setSalary(newSalary);
                    found = true;
                    System.out.println("Salary updated successfully.");
                    break;
                }
            }

            if (!found) {
                System.out.println("Employee with ID " + id + " not found.");
            }
        }
    }

    private static void deleteEmployee() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees to delete.");
        } else {
            System.out.print("Enter employee ID to delete: ");
            String id = scanner.nextLine();

            boolean removed = employeeList.removeIf(employee -> employee.getId().equals(id));
            if (removed) {
                System.out.println("Employee with ID " + id + " deleted successfully.");
            } else {
                System.out.println("Employee with ID " + id + " not found.");
            }
        }
    }
}
