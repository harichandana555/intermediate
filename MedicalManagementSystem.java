import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Models
class User {
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}

class Appointment {
    private String patientName;
    private String doctorName;
    private String date;
    private String time;

    public Appointment(String patientName, String doctorName, String date, String time) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}

class Database {
    private HashMap<String, User> users = new HashMap<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public Database() {
        // Initialize sample users and appointments (for demonstration purposes)
        users.put("admin", new User("admin", "admin123", "Admin"));
        users.put("doctor", new User("doctor", "doctor123", "Doctor"));
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
}

// Main Application
public class MedicalManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Database database = new Database();
    private static User currentUser;

    public static void main(String[] args) {
        System.out.println("Welcome to Virtual Medicine Home");

        // User Authentication
        authenticateUser();

        if (currentUser != null) {
            // Main Menu based on user role
            if (currentUser.getRole().equalsIgnoreCase("Admin")) {
                adminMenu();
            } else if (currentUser.getRole().equalsIgnoreCase("Doctor")) {
                doctorMenu();
            }
        }

        scanner.close();
    }

    private static void authenticateUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = database.getUser(username);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful.");
            currentUser = user;
        } else {
            System.out.println("Invalid username or password.");
            currentUser = null;
        }
    }

    private static void adminMenu() {
        System.out.println("\nAdmin Menu");
        while (true) {
            System.out.println("1. View Appointments");
            System.out.println("2. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAppointments();
                    break;
                case 2:
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void doctorMenu() {
        System.out.println("\nDoctor Menu");
        while (true) {
            System.out.println("1. View Appointments");
            System.out.println("2. Add Appointment");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAppointments();
                    break;
                case 2:
                    addAppointment();
                    break;
                case 3:
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void viewAppointments() {
        ArrayList<Appointment> appointments = database.getAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No appointments available.");
        } else {
            System.out.println("Appointments:");
            for (Appointment appointment : appointments) {
                System.out.println("Patient: " + appointment.getPatientName() +
                        ", Doctor: " + appointment.getDoctorName() +
                        ", Date: " + appointment.getDate() +
                        ", Time: " + appointment.getTime());
            }
        }
    }

    private static void addAppointment() {
        System.out.print("Enter patient name: ");
        String patientName = scanner.nextLine();
        System.out.print("Enter doctor name: ");
        String doctorName = scanner.nextLine();
        System.out.print("Enter date (MM/DD/YYYY): ");
        String date = scanner.nextLine();
        System.out.print("Enter time (HH:MM AM/PM): ");
        String time = scanner.nextLine();

        Appointment appointment = new Appointment(patientName, doctorName, date, time);
        database.addAppointment(appointment);
        System.out.println("Appointment added successfully.");
    }
}
