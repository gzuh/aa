/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.util.Scanner;

/**
 *
 * @author Tan Hung
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hospital hospital = new Hospital();

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Nurse's Management");
            System.out.println("2. Patient's Management");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("\nNurse's Management:");
                    System.out.println("1. Create a Nurse");
                    System.out.println("2. Find the Nurse");
                    System.out.println("3. Update the Nurse");
                    System.out.println("4. Delete the Nurse");
                    System.out.print("Enter your choice: ");
                    int nurseChoice = Integer.parseInt(scanner.nextLine());
                    switch (nurseChoice) {
                        case 1:
                            hospital.createNurse();
                            break;
                        case 2:
                            hospital.findNurse();
                            break;
                        case 3:
                            hospital.updateNurse();
                            break;
                        case 4:
                            hospital.deleteNurse();
                            break;
                        default:
                            System.out.println("Invalid choice!");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("\nPatient's Management:");
                    System.out.println("1. Add a Patient");
                    System.out.println("2. Display Patients");
                    System.out.println("3. Sort the Patients list");
                    System.out.println("4. Save Data");
                    System.out.println("5. Load Data");
                    System.out.print("Enter your choice: ");
                    int patientChoice = Integer.parseInt(scanner.nextLine());
                    switch (patientChoice) {
                        case 1:
                            hospital.addPatient();
                            break;
                        case 2:
                            hospital.displayPatients();
                            break;
                        case 3:
                            hospital.sortPatients(scanner);
                            break;
                        case 4:
                            hospital.saveData();
                            break;
                        case 5:
                            hospital.loadData();
                            break;
                        default:
                            System.out.println("Invalid choice!");
                            break;
                    }
                    break;
                case 3:
                    hospital.quit();
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (choice != 3);

        scanner.close();
    }
}