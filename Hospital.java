/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Tan Hung
 */
public class Hospital {

    HashMap<String, Nurse> nurses = new HashMap<>();
    HashMap<String, Patient> patients = new HashMap<>();
    private boolean dataChanged;
    private static final Validation vl = new Validation();

    public Hospital(HashMap<String, Nurse> nurses, HashMap<String, Patient> patients) {
        this.nurses = nurses;
        this.patients = patients;
    }

    Hospital() {
    }

    public void displayNurses() {
        if (nurses.isEmpty()) {
            System.out.println("No nurses found.");
        } else {
            System.out.println("List of Nurses:");
            for (Nurse nurse : nurses.values()) {
                System.out.println(nurse.toString());
                System.out.println("------------------------------");
            }
        }
    }

    public void createNurse() {
        String id = "";
        String staffID = "";
        String name;
        int age;
        String gender;
        String address;
        String phone;
        String department;
        String shift = "";
        double salary;

        Scanner scanner = new Scanner(System.in);
        int check = 0;

        do {
            System.out.print("Input ID: ");
            id = scanner.nextLine();
            if (id.isEmpty()) {
                System.out.println("Invalid data! ID is required.");
                check = 0;
            } else {
                check = 1;
            }
        } while (check == 0);

        check = 0;

        do {
            try {
                System.out.print("Input staff ID: ");
                staffID = scanner.nextLine();
                if (nurses.containsKey(staffID)) {
                    throw new Exception();
                }
                check = 1;
            } catch (Exception e) {
                System.out.println("The staff ID is existing!");
                check = 0;
            }
        } while (check == 0);

        check = 0;

        do {
            System.out.print("Input name: ");
            name = scanner.nextLine();
            System.out.print("Input age: ");
            age = Integer.parseInt(scanner.nextLine());
            System.out.print("Input gender: ");
            gender = scanner.nextLine();
            System.out.print("Input address: ");
            address = scanner.nextLine();
            System.out.print("Input phone: ");
            phone = scanner.nextLine();
            System.out.print("Input department: ");
            department = scanner.nextLine();
//            System.out.print("Input shift: ");
//            shift = scanner.nextLine();

            do {
                System.out.print("Input shift: ");
                shift = scanner.nextLine();
                if (shift.isEmpty()) {
                    System.out.println("Invalid data! Shift is required.");
                    check = 0;
                } else {
                    check = 1;
                }
            } while (check == 0);

            System.out.print("Input salary: ");
            salary = Double.parseDouble(scanner.nextLine());

            if (name.isEmpty() || gender.isEmpty() || address.isEmpty() || phone.isEmpty() || department.isEmpty() || shift.isEmpty() || salary <= 0) {
                System.out.println("Invalid data! All fields are required.");
                check = 0;
            } else if (department.length() < 3 || department.length() > 50) {
                System.out.println("The department must be between 3 and 50 characters long.");
                check = 0;
            } else if (!phone.matches("\\d{10}")) {
                System.out.println("Invalid phone format! Enter a 10-digit number.");
                check = 0;
            } else {
                check = 1;
            }
        } while (check == 0);

        Nurse nurse = new Nurse(staffID, name, age, gender, address, phone, id, department, shift, salary);

        nurses.put(staffID, nurse);
        dataChanged = true;
        System.out.println("Nurse created successfully!");
    }

    void findNurse() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter nurse's name or part of the name: ");
        String searchName = scanner.nextLine();
        boolean found = false;
        for (Nurse nurse : nurses.values()) {
            if (nurse.getName() != null && nurse.getName().contains(searchName)) {
                found = true;
                // Display the nurse's information
                System.out.println("Nurse found:");
                System.out.println(nurse.toString()); // Assuming you have implemented the toString() method in the Nurse class
            }
        }
        if (!found) {
            System.out.println("The nurse does not exist.");
        }

    }

    public void updateNurse() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the staff ID of the nurse to update: ");
        String staffID = scanner.nextLine();

        if (nurses.containsKey(staffID)) {
            Nurse nurse = nurses.get(staffID);

            // Display the current nurse's information
            System.out.println("Current nurse information:");
            System.out.println("Staff ID: " + nurse.getStaffID());
            System.out.println("Name: " + nurse.getName());
            System.out.println("Age: " + nurse.getAge());
            System.out.println("Gender: " + nurse.getGender());
            System.out.println("Address: " + nurse.getAddress());
            System.out.println("Phone: " + nurse.getPhone());
            System.out.println("Department: " + nurse.getDepartment());
            System.out.println("Shift: " + nurse.getShift());
            System.out.println("Salary: " + nurse.getSalary());

            // Prompt the user for updated values of nurse's properties
            System.out.println("Enter the updated information (leave empty if no change):");
            System.out.print("Name: ");
            String updatedName = scanner.nextLine();
            System.out.print("Age: ");
            String updatedAgeString = scanner.nextLine();
            // Prompt for other properties in a similar manner
            System.out.print("Gender: ");
            String updatedGender = scanner.nextLine();
            System.out.print("Address: ");
            String updatedAddress = scanner.nextLine();
            System.out.print("Phone: ");
            String updatedPhone = scanner.nextLine();
            System.out.print("Department: ");
            String updatedDepartment = scanner.nextLine();
            System.out.print("Shift: ");
            String updatedShift = scanner.nextLine();
            System.out.print("Salary: ");
            String updatedSalary = scanner.nextLine();
            // Update the nurse object if there are changes
            if (!updatedName.isEmpty()) {
                nurse.setName(updatedName);
            }
            if (!updatedAgeString.isEmpty()) {
                int updatedAge = Integer.parseInt(updatedAgeString);
                nurse.setAge(updatedAge);
            }
            // Update other properties in a similar manner

            System.out.println("Nurse information updated successfully.");
        } else {
            System.out.println("The nurse does not exist.");
        }
    }

    private boolean nurseHasTasks(Nurse nurse) {
        for (Patient patient : patients.values()) {
            if (patient.getNurseAssigned().equals(nurse.getStaffID())) {
                return true;
            }
        }
        return false;
    }

    public void deleteNurse() {
        Scanner scanner = new Scanner(System.in);
        String staffID;

        do {
            System.out.print("Enter the staff ID of the nurse to delete: ");
            staffID = scanner.nextLine();
        } while (staffID.trim().isEmpty());

        if (nurses.containsKey(staffID)) {
            Nurse nurse = nurses.get(staffID);

            if (nurseHasTasks(nurse)) {
                System.out.println("The nurse cannot be deleted as they have assigned tasks.");
            } else {
                nurses.remove(staffID);
                dataChanged = true;
                System.out.println("Nurse deleted successfully!");
            }
        } else {
            System.out.println("The nurse does not exist.");
        }
    }

//    public void deleteNurse() {
//        Scanner scanner = new Scanner(System.in);
//        String staffID;
//        
//        
//        do {
//            System.out.print("Enter the staff ID of the nurse to delete: ");
//            staffID = scanner.nextLine();
//        } while (staffID.trim().isEmpty());
//
//        if (nurses.containsKey(staffID)) {
//            Nurse nurse = nurses.get(staffID);
//            //
//            System.out.println(nurseHasTasks(nurse));
//            
//            if (nurseHasTasks(nurse)) {
//                System.out.println("The nurse cannot be deleted as they have assigned tasks.");
//            } else {
//                nurses.remove(staffID);
//                System.out.println("Nurse deleted successfully!");
//            }
//        } else {
//            System.out.println("The nurse does not exist.");
//        }
//    }
//
//    private boolean nurseHasTasks(Nurse nurse) {
//        for (Patient patient : patients.values()) {
//            if (patient.getNurseAssigned().contains(nurse.toString())) {
//                return true;
//            }
//        }
//        return false;
//    }
    public void addPatient() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter patient ID: ");
        String patientID = scanner.nextLine();

        // Check if the patient ID already exists
        if (patients.containsKey(patientID)) {
            System.out.println("Patient ID already exists!");
            return;
        }

        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();

        System.out.print("Enter patient age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter patient gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter patient address: ");
        String address = scanner.nextLine();

        System.out.print("Enter patient phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter patient diagnosis: ");
        String diagnosis = scanner.nextLine();

        System.out.print("Enter patient admission date (yyyy-MM-dd): ");
        String admissionDateString = scanner.nextLine();
        Date admissionDate = parseDate(admissionDateString);

        System.out.print("Enter patient discharge date (yyyy-MM-dd): ");
        String dischargeDateString = scanner.nextLine();
        Date dischargeDate = parseDate(dischargeDateString);

        System.out.println("Available Nurses:");
        displayNurses(); // Display the information of all nurses

        System.out.print("Enter nurse assigned to the patient (Staff ID): ");
        String nurseAssigned = scanner.nextLine();

        // Check if the nurse exists
        if (!nurses.containsKey(nurseAssigned)) {
            System.out.println("Invalid nurse! Nurse with Staff ID " + nurseAssigned + " does not exist.");
            return;
        }

//        System.out.print("Enter nurse assigned to the patient: ");
//        String nurseAssigned = scanner.nextLine();
//
        // Create a new Patient object
        Patient patient = new Patient(patientID, name, age, gender, address, phone, diagnosis,
                admissionDate, dischargeDate, nurseAssigned);

        // Add the patient to the patient list
        patients.put(patientID, patient);

        System.out.println("Patient added successfully!");
    }

    // Helper method to parse a string date into a Date object
    private Date parseDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format! Please enter a date in the format yyyy-MM-dd.");
            return null;
        }
    }

    public void displayPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        System.out.println("Patient List:");
        System.out.println("-----------------------");
        for (Patient patient : patients.values()) {
            System.out.println("Patient ID: " + patient.getId());
            System.out.println("Name: " + patient.getName());
            System.out.println("Age: " + patient.getAge());
            System.out.println("Gender: " + patient.getGender());
            System.out.println("Address: " + patient.getAddress());
            System.out.println("Phone: " + patient.getPhone());
            System.out.println("Diagnosis: " + patient.getDiagnosis());
            System.out.println("Admission Date: " + patient.getAdmissionDate());
            System.out.println("Discharge Date: " + patient.getDischargeDate());
            System.out.println("Nurse Assigned: " + patient.getNurseAssigned());
            System.out.println("-----------------------");
        }
    }

    public void sortPatients(Scanner scanner) {
        List<Patient> patientList = new ArrayList<>(patients.values());

        System.out.println("\nSort Patients:");
        System.out.println("1. Sort by Patient ID");
        System.out.println("2. Sort by Patient's Name");
        System.out.print("Enter your choice for the sorted field: ");
        int fieldChoice = Integer.parseInt(scanner.nextLine());

        String sortedField;
        switch (fieldChoice) {
            case 1:
                sortedField = "patient id";
                break;
            case 2:
                sortedField = "patient's name";
                break;
            default:
                System.out.println("Invalid choice for the sorted field.");
                return;
        }

        System.out.println("Sort Order:");
        System.out.println("1. Ascending (ASC)");
        System.out.println("2. Descending (DESC)");
        System.out.print("Enter your choice for the sort order: ");
        int orderChoice = Integer.parseInt(scanner.nextLine());

        String sortOrder;
        switch (orderChoice) {
            case 1:
                sortOrder = "ASC";
                break;
            case 2:
                sortOrder = "DESC";
                break;
            default:
                System.out.println("Invalid choice for the sort order.");
                return;
        }

        // Sort the patientList based on the specified field and sort order
        Comparator<Patient> comparator;
        if (sortedField.equalsIgnoreCase("patient id")) {
            comparator = Comparator.comparing(Patient::getId);
        } else {
            comparator = Comparator.comparing(Patient::getName);
        }

        if (sortOrder.equalsIgnoreCase("DESC")) {
            comparator = comparator.reversed();
        }

        Collections.sort(patientList, comparator);

        // Display the sorted patient list
        System.out.println("\nSorted Patient List:");
        System.out.println("-----------------------");
        System.out.println("No. Patient Id Admission Date Full name Phone Diagnosis");

        for (int i = 0; i < patientList.size(); i++) {
            Patient patient = patientList.get(i);
            System.out.printf("%d %s %s %s %s %s\n", i + 1, patient.getId(),
                    patient.getAdmissionDate(), patient.getName(), patient.getPhone(), patient.getDiagnosis());
        }
    }

    public void saveData() {
        try {
            // save nurse data to nurses.dat file
            FileWriter fileOut = new FileWriter("nurses.txt");
            BufferedWriter objectOut = new BufferedWriter(fileOut);
            objectOut.write(nurses.toString());
            objectOut.close();
            fileOut.close();

            // save patient data to patients.dat file
            fileOut = new FileWriter("patients.txt");
            objectOut = new BufferedWriter(fileOut);
            objectOut.write(patients.toString());
            objectOut.close();
            fileOut.close();

            System.out.println("Data saved successfully.");
        } catch (Exception e) {
            System.out.println("Error occurred while saving data: " + e.getClass().getSimpleName() + ": " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void loadData() {
        try {
            // Load nurses data from file
            FileReader nurseFileReader = new FileReader("nurses.txt");
            BufferedReader nurseBufferedReader = new BufferedReader(nurseFileReader);

            String nurseLine;
            while ((nurseLine = nurseBufferedReader.readLine()) != null) {
                // Parse nurseLine and create Nurse object
                // Add Nurse object to nurses HashMap
            }

            nurseBufferedReader.close();
            nurseFileReader.close();

            // Load patients data from file
            FileReader patientFileReader = new FileReader("patients.txt");
            BufferedReader patientBufferedReader = new BufferedReader(patientFileReader);

            String patientLine;
            while ((patientLine = patientBufferedReader.readLine()) != null) {
                // Parse patientLine and create Patient object
                // Add Patient object to patients HashMap
            }

            patientBufferedReader.close();
            patientFileReader.close();
            System.out.println("Data loaded successfully.");
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

//    public void loadData() {
//        try {
//            // Load nurses data from file
//            FileInputStream fis = new FileInputStream("nurses.txt");
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            nurses = (HashMap<String, Nurse>) ois.readObject();
//            ois.close();
//            fis.close();
//
//            // Load patients data from file
//            fis = new FileInputStream("patients.txt");
//            ois = new ObjectInputStream(fis);
//            patients = (HashMap<String, Patient>) ois.readObject();
//            ois.close();
//            fis.close();
//
//            System.out.println("Data loaded successfully.");
//        } catch (Exception e) {
//            System.out.println("Error loading data: " + e.getMessage());
//        }
//    }
    public void quit() {
        Scanner scanner = new Scanner(System.in);
        if (dataChanged) {
            System.out.println("There are unsaved changes. Do you want to save before exiting? (Y/N)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                saveData();
            }
        }
        System.out.println("Exiting the program...");
        System.exit(0);
    }

//    public void saveData() {
//        try {
//            // Save the collection of nurses to nurses.dat
//            FileOutputStream nursesFileOut = new FileOutputStream("nurses.dat");
//            ObjectOutputStream nursesObjOut = new ObjectOutputStream(nursesFileOut);
//            nursesObjOut.writeObject(nurses);
//            nursesObjOut.close();
//            nursesFileOut.close();
//
//            // Save the collection of patients to patients.dat
//            FileOutputStream patientsFileOut = new FileOutputStream("patients.dat");
//            ObjectOutputStream patientsObjOut = new ObjectOutputStream(patientsFileOut);
//            patientsObjOut.writeObject(patients);
//            patientsObjOut.close();
//            patientsFileOut.close();
//
//            System.out.println("Data saved successfully.");
//        } catch (IOException e) {
//            System.out.println("Error saving data: " + e.getMessage());
//        }
//    }
//
//    public void loadData() {
//        try {
//            // Load the collection of patients from patients.dat
//            FileInputStream patientsFileIn = new FileInputStream("patients.dat");
//            ObjectInputStream patientsObjIn = new ObjectInputStream(patientsFileIn);
//            patients = (HashMap<String, Patient>) patientsObjIn.readObject();
//            patientsObjIn.close();
//            patientsFileIn.close();
//
//            System.out.println("Data loaded successfully.");
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Error loading data: " + e.getMessage());
//        }
//    }
}
