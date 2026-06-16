import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

enum Gender {
    MALE, FEMALE, OTHER
}

enum BloodGroup {
    A_POSITIVE("A+"), A_NEGATIVE("A-"),
    B_POSITIVE("B+"), B_NEGATIVE("B-"),
    O_POSITIVE("O+"), O_NEGATIVE("O-"),
    AB_POSITIVE("AB+"), AB_NEGATIVE("AB-");

    private final String value;

    BloodGroup(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}

enum AppointmentStatus {
    PENDING, CONFIRMED, CANCELLED
}

abstract class Person {
    private final int personId;
    private final String name;
    private final int age;
    private final Gender gender;
    private final String phoneNumber;
    private final String address;

    public Person(int personId, String name, int age, Gender gender, String phoneNumber, String address) {
        this.personId = personId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public abstract String getRole();
}

class Patient extends Person {
    private final String patientId;
    private final BloodGroup bloodGroup;
    private final String disease;

    public Patient(int personId, String name, int age, Gender gender, String phoneNumber, String address,
                   String patientId, BloodGroup bloodGroup, String disease) {
        super(personId, name, age, gender, phoneNumber, address);
        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.disease = disease;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getRole() {
        return "Patient";
    }

    public String getFormattedRecord() {
        return """
                ========== PATIENT RECORD ==========
                Patient ID: %s
                Patient Name: %s
                Disease: %s
                Blood Group: %s
                ------------------------------------
                """.formatted(patientId, getName(), disease, bloodGroup);
    }
}

class Doctor extends Person {
    private final String doctorId;
    private final String specialization;

    public Doctor(int personId, String name, int age, Gender gender, String phoneNumber, String address,
                  String doctorId, String specialization) {
        super(personId, name, age, gender, phoneNumber, address);
        this.doctorId = doctorId;
        this.specialization = specialization;
    }

    public String getRole() {
        return "Doctor";
    }

    public String getSpecialization() {
        return specialization;
    }
}

class Appointment {
    private final int appointmentId;
    private final Patient patient;
    private final Doctor doctor;
    private final String date;
    private final String time;
    private final AppointmentStatus status;

    public Appointment(int appointmentId, String date, String time, AppointmentStatus status,
                       Patient patient, Doctor doctor) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.time = time;
        this.status = status;
        this.patient = patient;
        this.doctor = doctor;
    }

    public String getAppointmentSummary() {
        return "Appointment ID: " + appointmentId +
                "\nPatient: " + patient.getName() +
                "\nDoctor: " + doctor.getName() +
                "\nSpecialization: " + doctor.getSpecialization() +
                "\nDate: " + date +
                "\nTime: " + time +
                "\nStatus: " + status;
    }
}

interface Payment {
    String processPayment(int amount);
}

class CardPayment implements Payment {
    public String processPayment(int amount) {
        return "Card payment of Rs. " + amount + " processed successfully.";
    }
}

class Bill {
    private final int billId;
    private final int totalAmount;
    private final Payment paymentMethod;

    public Bill(int billId, int consultationCharge, int laboratoryCharge, int medicalCharge, Payment paymentMethod) {
        this.billId = billId;
        this.totalAmount = consultationCharge + laboratoryCharge + medicalCharge;
        this.paymentMethod = paymentMethod;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public String generateBill() {
        return """
                Bill ID: %d
                Total Amount: Rs. %d
                %s
                """.formatted(billId, totalAmount, paymentMethod.processPayment(totalAmount));
    }
}

class FileManager {
    private final String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public void saveData(String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();
        }
    }

    public String readData() throws IOException {
        File file = new File(fileName);

        if (!file.exists()) {
            return "No records found yet.";
        }

        StringBuilder records = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                records.append(line).append("\n");
            }
        }

        return records.toString();
    }
}

public class HospitalDashboard extends JFrame {
    private final FileManager fileManager = new FileManager("MediCareRecords.txt");

    private JTextField txtPatientId;
    private JTextField txtPatientName;
    private JTextField txtAge;
    private JTextField txtDisease;

    private JComboBox<Gender> comboGender;
    private JComboBox<BloodGroup> comboBloodGroup;

    private JTextArea txtOutputConsole;
    private JTextArea txtFileViewer;

    public HospitalDashboard() {
        setTitle("MediCare Hospital Management System");
        setSize(900, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        applyLookAndFeel();
        add(createHeader(), BorderLayout.NORTH);
        add(createTabs(), BorderLayout.CENTER);
    }

    private void applyLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setBackground(new Color(41, 128, 185));
        header.setPreferredSize(new Dimension(900, 70));

        JLabel title = new JLabel("MEDICARE MANAGEMENT SYSTEM");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        header.add(title);
        return header;
    }

    private JTabbedPane createTabs() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        tabs.addTab("Patient Registration", createPatientPanel());
        tabs.addTab("Treatment Workflow", createWorkflowPanel());
        tabs.addTab("Saved Records", createFilePanel());

        return tabs;
    }

    private JPanel createPatientPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        JPanel form = new JPanel(new GridLayout(6, 2, 12, 15));

        txtPatientId = new JTextField();
        txtPatientName = new JTextField();
        txtAge = new JTextField();
        txtDisease = new JTextField();

        comboGender = new JComboBox<>(Gender.values());
        comboBloodGroup = new JComboBox<>(BloodGroup.values());

        form.add(new JLabel("Patient ID:"));
        form.add(txtPatientId);

        form.add(new JLabel("Full Name:"));
        form.add(txtPatientName);

        form.add(new JLabel("Age:"));
        form.add(txtAge);

        form.add(new JLabel("Gender:"));
        form.add(comboGender);

        form.add(new JLabel("Blood Group:"));
        form.add(comboBloodGroup);

        form.add(new JLabel("Disease:"));
        form.add(txtDisease);

        JButton saveButton = new JButton("Register Patient");
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveButton.setBackground(new Color(39, 174, 96));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(this::registerPatient);

        panel.add(form, BorderLayout.NORTH);
        panel.add(saveButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createWorkflowPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JButton workflowButton = new JButton("Run Complete Hospital Workflow");
        workflowButton.setBackground(new Color(142, 68, 173));
        workflowButton.setForeground(Color.WHITE);
        workflowButton.setFocusPainted(false);
        workflowButton.addActionListener(e -> runWorkflow());

        txtOutputConsole = new JTextArea();
        txtOutputConsole.setEditable(false);
        txtOutputConsole.setFont(new Font("Consolas", Font.PLAIN, 13));

        panel.add(workflowButton, BorderLayout.NORTH);
        panel.add(new JScrollPane(txtOutputConsole), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createFilePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JButton refreshButton = new JButton("Load Saved Records");
        refreshButton.setBackground(new Color(52, 73, 94));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(e -> loadRecords());

        txtFileViewer = new JTextArea();
        txtFileViewer.setEditable(false);
        txtFileViewer.setFont(new Font("Consolas", Font.PLAIN, 13));

        panel.add(refreshButton, BorderLayout.NORTH);
        panel.add(new JScrollPane(txtFileViewer), BorderLayout.CENTER);

        return panel;
    }

    private void registerPatient(ActionEvent event) {
        try {
            validatePatientInput();

            String patientId = txtPatientId.getText().trim();
            String name = txtPatientName.getText().trim();
            int age = Integer.parseInt(txtAge.getText().trim());
            String disease = txtDisease.getText().trim();

            Patient patient = new Patient(
                    patientId.hashCode(),
                    name,
                    age,
                    (Gender) comboGender.getSelectedItem(),
                    "0300-1234567",
                    "Main Clinic Address",
                    patientId,
                    (BloodGroup) comboBloodGroup.getSelectedItem(),
                    disease
            );

            fileManager.saveData(patient.getFormattedRecord());

            JOptionPane.showMessageDialog(this, "Patient registered successfully.");
            clearPatientForm();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Age must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validatePatientInput() {
        if (txtPatientId.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Patient ID is required.");
        }

        if (txtPatientName.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name is required.");
        }

        if (txtAge.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Age is required.");
        }

        if (txtDisease.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Disease is required.");
        }
    }

    private void clearPatientForm() {
        txtPatientId.setText("");
        txtPatientName.setText("");
        txtAge.setText("");
        txtDisease.setText("");
        comboGender.setSelectedIndex(0);
        comboBloodGroup.setSelectedIndex(0);
    }

    private void runWorkflow() {
        Patient patient = new Patient(
                101,
                "Hafsa",
                25,
                Gender.FEMALE,
                "031313799",
                "Lahore",
                "P001",
                BloodGroup.O_POSITIVE,
                "Fever"
        );

        Doctor doctor = new Doctor(
                102,
                "Dr Ahmed Abdullah",
                30,
                Gender.MALE,
                "02356771",
                "Somalia MQS",
                "D101",
                "Cardiologist"
        );

        Appointment appointment = new Appointment(
                1001,
                "11/07/2026",
                "11:30 PM",
                AppointmentStatus.CONFIRMED,
                patient,
                doctor
        );

        Bill bill = new Bill(501, 2000, 1500, 1000, new CardPayment());

        txtOutputConsole.setText("");

        txtOutputConsole.append("========== POLYMORPHISM ==========\n");
        txtOutputConsole.append(patient.getName() + " role: " + patient.getRole() + "\n");
        txtOutputConsole.append(doctor.getName() + " role: " + doctor.getRole() + "\n\n");

        txtOutputConsole.append("========== APPOINTMENT ==========\n");
        txtOutputConsole.append(appointment.getAppointmentSummary());
        txtOutputConsole.append("\n\n");

        txtOutputConsole.append("========== BILLING ==========\n");
        txtOutputConsole.append(bill.generateBill());
    }

    private void loadRecords() {
        try {
            txtFileViewer.setText(fileManager.readData());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Unable to read records.", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HospitalDashboard().setVisible(true));
    }
}