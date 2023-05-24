/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Tan Hung
 */
public class Patient extends Person {

    private String diagnosis;
    private Date admissionDate;
    private Date dischargeDate;
    private String nurseAssigned;

    public Patient() {
    }

    public Patient(String id, String name, int age, String gender, String address, String phone,
            String diagnosis, Date admissionDate, Date dischargeDate, String nurseAssigned) {
        super(id, name, age, gender, address, phone);
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.nurseAssigned = nurseAssigned;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getNurseAssigned() {
        return nurseAssigned;
    }

    public void setNurseAssigned(String nurseAssigned) {
        this.nurseAssigned = nurseAssigned;
    }
//    public List<Nurse> getNurseAssigned() {
//        return nurseAssigned;
//    }
//
//    public void setNurseAssigned(List<Nurse> nurseAssigned) {
//        this.nurseAssigned = nurseAssigned;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String admissionDateString = dateFormat.format(admissionDate);
        String dischargeDateString = dateFormat.format(dischargeDate);

        return "ID: " + id + "\t"
                + "Name: " + name + "\t"
                + "Age: " + age + "\t"
                + "Gender: " + gender + "\t"
                + "Address: " + address + "\t"
                + "Phone: " + phone + "\t"
                + "Diagnosis: " + diagnosis + "\t"
                + "Admission Date: " + admissionDateString + "\t"
                + "Discharge Date: " + dischargeDateString + "\t"
                + "Nurse Assigned: " + nurseAssigned;
    }
}
