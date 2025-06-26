import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

class User {
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}

class UserManager {
    private List<User> users = new ArrayList<>();

    public UserManager() {
        users.add(new User("admin", "admin123", "ADMIN"));
        users.add(new User("doctor", "doc123", "DOCTOR"));
    }

    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}

class Patient {
    private String name;
    private String dateOfBirth;
    private String medicalHistory;
    private String contactDetails;
    private List<String> testReports = new ArrayList<>();
    private String appointmentDate;

    public Patient(String name, String dateOfBirth, String medicalHistory, String contactDetails) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.medicalHistory = medicalHistory;
        this.contactDetails = contactDetails;
    }

    public static Patient fromString(String line) {
        String[] parts = line.split("\\|");
        Patient p = new Patient(parts[0], parts[1], parts[2], parts[3]);
        if (parts.length > 4) {
            String[] reports = parts[4].split(";");
            p.testReports = new ArrayList<String>(Arrays.asList(reports));
        }
        if (parts.length > 5) {
            p.appointmentDate = parts[5];
        }
        return p;
    }

    @Override
    public String toString() {
        String reports = String.join(";", testReports);
        return name + "|" + dateOfBirth + "|" + medicalHistory + "|" + contactDetails + "|" + reports + "|" + appointmentDate;
    }

    public String display() {
        return String.format("%-20s %-12s %-30s %-20s", name, dateOfBirth, medicalHistory.replace(";", "\n"), contactDetails);
    }

    public String getName() { return name; }
    public String getMedicalHistory() { return medicalHistory; }
    public String getAppointmentDate() { return appointmentDate; }

    public void addMedicalHistory(String newEntry) {
        String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        if (medicalHistory == null || medicalHistory.isEmpty()) {
            medicalHistory = "- " + newEntry + " (" + today + ")";
        } else {
            medicalHistory += ";\n- " + newEntry + " (" + today + ")";
        }
    }

    public void addTestReport(String report) {
        testReports.add(report);
    }

    public List<String> getTestReports() {
        return testReports;
    }

    public void setAppointmentDate(String date) {
        this.appointmentDate = date;
    }
}


class Payment {
    private String patientName;
    private String service;
    private double amount;
    private boolean isPaid;
    private String paymentDate;

    public Payment(String patientName, String service, double amount) {
        this.patientName = patientName;
        this.service = service;
        this.amount = amount;
        this.isPaid = false;
        this.paymentDate = "-";
    }

    public static Payment fromString(String line) {
        String[] parts = line.split("\\|");
        Payment p = new Payment(parts[0], parts[1], Double.parseDouble(parts[2]));
        p.isPaid = Boolean.parseBoolean(parts[3]);
        p.paymentDate = parts[4];
        return p;
    }

    public void pay() {
        isPaid = true;
        paymentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public boolean isPaid() { return isPaid; }
    public String getPatientName() { return patientName; }

    @Override
    public String toString() {
        return patientName + "|" + service + "|" + amount + "|" + isPaid + "|" + paymentDate;
    }

    public String display() {
        return patientName + " - " + service + " : " + amount + " TL (" + (isPaid ? "Paid on " + paymentDate : "Pending") + ")";
    }
}

class DataManager {
    private static final String PATIENT_FILE = "patients.txt";
    private static final String PAYMENT_FILE = "payments.txt";
    private static final String PATIENT_BACKUP = "patients_backup.txt";
    private static final String PAYMENT_BACKUP = "payments_backup.txt";

    public List<Patient> loadPatients() {
        return loadPatientsFromFile(PATIENT_FILE);
    }

    public List<Patient> loadPatientsBackup() {
        return loadPatientsFromFile(PATIENT_BACKUP);
    }

    private List<Patient> loadPatientsFromFile(String filename) {
        List<Patient> patients = new ArrayList<Patient>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                patients.add(Patient.fromString(line));
            }
        } catch (IOException e) {}
        return patients;
    }

    public List<Payment> loadPayments() {
        return loadPaymentsFromFile(PAYMENT_FILE);
    }

    public List<Payment> loadPaymentsBackup() {
        return loadPaymentsFromFile(PAYMENT_BACKUP);
    }

    private List<Payment> loadPaymentsFromFile(String filename) {
        List<Payment> payments = new ArrayList<Payment>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                payments.add(Payment.fromString(line));
            }
        } catch (IOException e) {}
        return payments;
    }

    public void savePatients(List<Patient> patients) {
        saveToFile(PATIENT_FILE, patients);
        saveToFile(PATIENT_BACKUP, patients);
    }

    public void savePayments(List<Payment> payments) {
        saveToFile(PAYMENT_FILE, payments);
        saveToFile(PAYMENT_BACKUP, payments);
    }

    private void saveToFile(String filename, List<?> data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Object o : data) {
                pw.println(o.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving to " + filename + ": " + e.getMessage());
        }
    }
}


public class PatientManagementSystem {
    private static List<Patient> patients = new ArrayList<Patient>();
    private static List<Payment> payments = new ArrayList<Payment>();
    private static DataManager dataManager = new DataManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserManager userManager = new UserManager();

        patients = dataManager.loadPatients();
        payments = dataManager.loadPayments();

        int attempts = 0;
        User user = null;
        while (attempts < 3 && user == null) {
            System.out.print("Username: ");
            String username = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();
            user = userManager.authenticate(username, password);
            if (user == null) {
                attempts++;
                System.out.println("Incorrect credentials. Attempts left: " + (3 - attempts));
            }
        }

        if (user == null) {
            System.out.println("Access denied.");
            return;
        }

        System.out.println("Welcome, " + user.getRole());
        showUpcomingAppointments();

        boolean running = true;
        while (running) {
            printMenu(user);
            String choiceStr = sc.nextLine();
            int choice = -1;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (Exception e) {
            }

            if (user.getRole().equals("ADMIN")) {
                running = handleAdminChoice(choice, sc);
            } else {
                running = handleDoctorChoice(choice, sc);
            }
        }
    }

    private static void printMenu(User user) {
        System.out.println("\n===== " + user.getRole() + " Menu =====");
        if (user.getRole().equals("ADMIN")) {
            System.out.println("1. Register Patient");
            System.out.println("2. List Patients");
            System.out.println("3. Search Patients");
            System.out.println("4. Add Payment");
            System.out.println("5. View Payments");
            System.out.println("6. Make Payment");
            System.out.println("7. View Medical Record");
            System.out.println("8. Update Medical Record");
            System.out.println("9. Add Test Report");
            System.out.println("10. Share Patient Info");
            System.out.println("11. Data Recovery");
            System.out.println("12. Logout");
        } else {
            System.out.println("1. Register Patient");
            System.out.println("2. List Patients");
            System.out.println("3. Search Patients");
            System.out.println("4. View Medical Record");
            System.out.println("5. Update Medical Record");
            System.out.println("6. Logout");
        }
        System.out.print("Choose: ");
    }

    private static boolean handleAdminChoice(int choice, Scanner sc) {
        switch (choice) {
            case 1:
                registerPatient(sc);
                break;
            case 2:
                listPatients();
                break;
            case 3:
                searchPatients(sc);
                break;
            case 4:
                addPayment(sc);
                break;
            case 5:
                viewPayments();
                break;
            case 6:
                makePayment(sc);
                break;
            case 7:
                viewMedicalRecord(sc);
                break;
            case 8:
                updateMedicalRecord(sc);
                break;
            case 9:
                addTestReport(sc);
                break;
            case 10:
                sharePatientInfo(sc);
                break;
            case 11:
                recoverData();
                break;
            case 12:
                saveAll();
                System.out.println("Logout successful.");
                return false;
            default:
                System.out.println("Invalid choice.");
        }
        return true;
    }

    private static boolean handleDoctorChoice(int choice, Scanner sc) {
        switch (choice) {
            case 1:
                registerPatient(sc);
                break;
            case 2:
                listPatients();
                break;
            case 3:
                searchPatients(sc);
                break;
            case 4:
                viewMedicalRecord(sc);
                break;
            case 5:
                updateMedicalRecord(sc);
                break;
            case 6:
                saveAll();
                System.out.println("Logout successful.");
                return false;
            default:
                System.out.println("Invalid choice.");
        }
        return true;
    }

    private static void showUpcomingAppointments() {
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println("\nUpcoming Appointments:");
        for (Patient p : patients) {
            if (p.getAppointmentDate() != null && !p.getAppointmentDate().isEmpty()) {
                if (p.getAppointmentDate().compareTo(today) >= 0) {
                    System.out.println("- " + p.getName() + ": " + p.getAppointmentDate());
                }
            }
        }
    }

    private static void registerPatient(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("DOB: ");
        String dob = sc.nextLine();
        System.out.print("Medical History: ");
        String history = sc.nextLine();
        System.out.print("Contact: ");
        String contact = sc.nextLine();
        System.out.print("Appointment Date (yyyy-MM-dd): ");
        String appDate = sc.nextLine();

        Patient p = new Patient(name, dob, history, contact);
        p.setAppointmentDate(appDate);
        patients.add(p);
        System.out.println("Patient registered.");
    }

    private static void listPatients() {
        for (Patient p : patients) {
            System.out.println(p.display());
        }
    }

    private static void searchPatients(Scanner sc) {
        System.out.print("Search keyword: ");
        String keyword = sc.nextLine().toLowerCase();
        for (Patient p : patients) {
            if (p.display().toLowerCase().contains(keyword)) {
                System.out.println(p.display());
            }
        }
    }

    private static void viewPayments() {
        for (Payment p : payments) {
            System.out.println(p.display());
        }
    }

    private static void makePayment(Scanner sc) {
        System.out.print("Patient Name: ");
        String name = sc.nextLine();
        for (Payment p : payments) {
            if (p.getPatientName().equalsIgnoreCase(name) && !p.isPaid()) {
                p.pay();
                System.out.println("Payment completed.");
                return;
            }
        }
        System.out.println("No unpaid payments found.");
    }

    private static void addPayment(Scanner sc) {
        System.out.print("Patient Name: ");
        String name = sc.nextLine();
        System.out.print("Service: ");
        String service = sc.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(sc.nextLine());
        payments.add(new Payment(name, service, amount));
        System.out.println("Payment added.");
    }

    private static void viewMedicalRecord(Scanner sc) {
        System.out.print("Patient Name: ");
        String name = sc.nextLine();
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(name)) {
                System.out.println("Medical History:");
                System.out.println(p.getMedicalHistory().replace(";", "\n"));
                System.out.println("Test Reports: " + p.getTestReports());
                return;
            }
        }
        System.out.println("Patient not found.");
    }

    private static void updateMedicalRecord(Scanner sc) {
        System.out.print("Patient Name: ");
        String name = sc.nextLine();
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(name)) {
                System.out.print("New Medical History Entry: ");
                String entry = sc.nextLine();
                p.addMedicalHistory(entry);
                System.out.println("Medical record updated.");
                return;
            }
        }
        System.out.println("Patient not found.");
    }

    private static void addTestReport(Scanner sc) {
        System.out.print("Patient Name: ");
        String name = sc.nextLine();
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(name)) {
                System.out.print("Test Report Description: ");
                String report = sc.nextLine();
                p.addTestReport(report);
                System.out.println("Test report added.");
                return;
            }
        }
        System.out.println("Patient not found.");
    }

    private static void sharePatientInfo(Scanner sc) {
        System.out.print("Patient Name to share info: ");
        String name = sc.nextLine();
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(name)) {
                System.out.println("Sharing patient info securely:");
                System.out.println(p.display());
                return;
            }
        }
        System.out.println("Patient not found.");
    }

    private static void recoverData() {
        System.out.println("Recovering data from backup...");
        patients = dataManager.loadPatientsBackup();
        payments = dataManager.loadPaymentsBackup();
        System.out.println("Recovery complete.");
    }

    private static void saveAll() {
        dataManager.savePatients(patients);
        dataManager.savePayments(payments);
    }
}


