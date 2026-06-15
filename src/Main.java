import java.io.*;
// 1. Abstract Class: Person
abstract class Person {
    private int personID;
    private String name;
    private int age;
    private String gender;
    private String phoneNumber;
    private String address;

    public Person() {
    }

    public Person(int personID, String name, int age, String gender, String phoneNumber, String address) {
        this.personID = personID;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Invalid age.");
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public abstract void performRole();

    public void displayPersonDetails() {
        System.out.println("Person ID: " + personID);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Address: " + address);
    }
}

// 2. chill Patient Class
class Patient extends Person {
    private String patientId;
    private String bloodGroup;
    private String disease;
    private String medicalHistory;
    private String admissionDate;

    public Patient() {
    }

    public Patient(int personID, String name, int age, String gender, String phoneNumber, String address,
                   String patientId, String bloodGroup, String disease, String medicalHistory, String admissionDate) {
        super(personID, name, age, gender, phoneNumber, address);
        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.disease = disease;
        this.medicalHistory = medicalHistory;
        this.admissionDate = admissionDate;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    @Override
    public void performRole() {
        System.out.println("Patient role: Receives treatment and keeps medical records in the hospital system.");
    }

    public void displayPatientDetails() {
        displayPersonDetails();
        System.out.println("Patient ID: " + patientId);
        System.out.println("Blood Group: " + bloodGroup);
        System.out.println("Disease: " + disease);
        System.out.println("Medical History: " + medicalHistory);
        System.out.println("Admission Date: " + admissionDate);
    }
}

// 3. Doctor Class
class Doctor extends Person {
    private String doctorId;
    private String specialization;
    private String qualification;
    private String experience;
    private int consultationFee;

    public Doctor() {
    }

    public Doctor(int personID, String name, int age, String gender, String phoneNumber, String address,
                  String doctorId, String specialization, String qualification, String experience, int consultationFee) {
        super(personID, name, age, gender, phoneNumber, address);
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.qualification = qualification;
        this.experience = experience;
        this.consultationFee = consultationFee;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(int consultationFee) {
        this.consultationFee = consultationFee;
    }

    @Override
    public void performRole() {
        System.out.println("Doctor role: Checks patients, gives consultation, and manages treatment.");
    }

    public void displayDoctorDetails() {
        displayPersonDetails();
        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Specialization: " + specialization);
        System.out.println("Qualification: " + qualification);
        System.out.println("Experience: " + experience);
        System.out.println("Consultation Fee: " + consultationFee);
    }
}

// 4. Appointment Class - Composition
class Appointment {
    private int appointmentId;
    private String appointmentDate;
    private String appointmentTime;
    private boolean status;
    private Patient patient;
    private Doctor doctor;

    public Appointment() {
    }

    public Appointment(int appointmentId, String appointmentDate, String appointmentTime,
                       boolean status, Patient patient, Doctor doctor) {
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.patient = patient;
        this.doctor = doctor;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void displayAppointmentDetails() {
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Appointment Date: " + appointmentDate);
        System.out.println("Appointment Time: " + appointmentTime);
        System.out.println("Status: " + (status ? "Confirmed" : "Pending"));
        System.out.println("Patient Name: " + patient.getName());
        System.out.println("Doctor Name: " + doctor.getName());
        System.out.println("Doctor Specialization: " + doctor.getSpecialization());
    }
}

// 5. Payment Interface
interface Payment {
    void processPayment();
    void generateReceipt();
}

// 6. Card Payment Class
class CardPayment implements Payment {
    @Override
    public void processPayment() {
        System.out.println("Processing card payment.");
        System.out.println("Card details validated successfully.");
    }

    @Override
    public void generateReceipt() {
        System.out.println("Digital receipt generated successfully.");
    }
}

// 7. Cash Payment Class
class CashPayment implements Payment {
    @Override
    public void processPayment() {
        System.out.println("Processing cash payment.");
    }

    @Override
    public void generateReceipt() {
        System.out.println("Cash payment receipt generated successfully.");
    }
}

// 8. Bill Class - Composition with Payment
class Bill {
    private int billId;
    private int consultationCharge;
    private int laboratoryCharge;
    private int medicalCharge;
    private int totalAmount;
    private Payment payment;

    public Bill() {
    }

    public Bill(int billId, int consultationCharge, int laboratoryCharge, int medicalCharge, Payment payment) {
        this.billId = billId;
        this.consultationCharge = consultationCharge;
        this.laboratoryCharge = laboratoryCharge;
        this.medicalCharge = medicalCharge;
        this.payment = payment;
        this.totalAmount = calculateBill();
    }

    public int getBillId() {
        return billId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int calculateBill() {
        return consultationCharge + laboratoryCharge + medicalCharge;
    }

    public void payBill() {
        payment.processPayment();
        payment.generateReceipt();
    }

    public void displayBillDetails() {
        System.out.println("Bill ID: " + billId);
        System.out.println("Consultation Charge: " + consultationCharge);
        System.out.println("Laboratory Charge: " + laboratoryCharge);
        System.out.println("Medical Charge: " + medicalCharge);
        System.out.println("Total Amount: " + totalAmount);
    }
}

// 9. File Handling Class
class FileManager {
    private String fileName;

    public FileManager() {
    }

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public void saveData(String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(data);
            bw.newLine();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void readData() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("\n======== Saved Hospital Records ========");

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please save data first.");
        } catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }
}

// 10. Main Class
public class Main {
    public static void main(String[] args) {

        Patient patient = new Patient(
                101,
                "Hafsa",
                25,
                "Female",
                "031313799",
                "Doll02",
                "P001",
                "O+",
                "Fever",
                "Diabetes, High Blood Pressure",
                "11/07/2026"
        );

        Doctor doctor = new Doctor(
                102,
                "Dr Ahmed Abdullah",
                30,
                "Male",
                "02356771",
                "Somalia MQS",
                "D101",
                "Cardiologist",
                "PhD",
                "5 Years",
                4500
        );

        System.out.println("========== Patient Registered Successfully ==========");
        patient.displayPatientDetails();

        System.out.println("\n========== Doctor Details ==========");
        doctor.displayDoctorDetails();

        System.out.println("\n========== Runtime Polymorphism ==========");
        Person person1 = patient;
        Person person2 = doctor;

        person1.performRole();
        person2.performRole();

        Appointment appointment = new Appointment(
                1001,
                "11/07/2026",
                "11:30 PM",
                true,
                patient,
                doctor
        );

        System.out.println("\n========== Appointment Scheduled Successfully ==========");
        appointment.displayAppointmentDetails();

        Payment payment = new CardPayment();

        Bill bill = new Bill(
                102,
                2000,
                1500,
                1000,
                payment
        );

        System.out.println("\n========== Bill Details ==========");
        bill.displayBillDetails();

        System.out.println("\n========== Payment Process ==========");
        bill.payBill();
        FileManager fileManager = new FileManager("MediCareRecords.txt");

        fileManager.saveData(
                "========== PATIENT RECORD ==========\n" +
                        "Patient ID: " + patient.getPatientId() + "\n" +
                        "Patient Name: " + patient.getName() + "\n" +
                        "Disease: " + patient.getDisease() + "\n" +
                        "Blood Group: " + patient.getBloodGroup() + "\n"
        );
        fileManager.saveData(
                "========== DOCTOR RECORD ==========\n" +
                        "Doctor ID: " + doctor.getDoctorId() + "\n" +
                        "Doctor Name: " + doctor.getName() + "\n" +
                        "Specialization: " + doctor.getSpecialization() + "\n"
        );
        fileManager.saveData(
                "========== APPOINTMENT RECORD ==========\n" +
                        "Appointment ID: " + appointment.getAppointmentId() + "\n" +
                        "Appointment Date: " + appointment.getAppointmentDate() + "\n" +
                        "Appointment Time: " + appointment.getAppointmentTime() + "\n"
        );
        fileManager.saveData(
                "========== BILL RECORD ==========\n" +
                        "Bill ID: " + bill.getBillId() + "\n" +
                        "Total Amount: " + bill.getTotalAmount() + "\n"
        );
        fileManager.saveData(
                "========== PAYMENT RECORD ==========\n" +
                        "Payment Method: Card Payment\n"
        );
        fileManager.readData();
    }
}