/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.io.Serializable;

/**
 *
 * @author Tan Hung
 */
public class Nurse extends Person implements Serializable {

    protected String staffID;
    protected String department;
    protected String shift;
    protected double salary;

    public Nurse() {
    }

//    public Nurse(String id, String name, int age, String gender, String address, String phone, 
//            String staffID, String department, String shirft, double salary) {
//        super(id, name, age, gender, address, phone);
//        this.staffID = staffID;
//        this.department = department;
//        this.shift = shift;
//        this.salary = salary;
//    }
    public Nurse(String id, String name, int age, String gender, String address, String phone,
            String staffID, String department, String shift, double salary) {
        super(id, name, age, gender, address, phone);
        this.staffID = staffID;
        this.department = department;
        this.shift = shift;
        this.salary = salary;
    }

    //Getter and Setter
    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

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
        return  "Staff ID: " + staffID + "\t"
                + "Name: " + name + "\t"
                + "Age: " + age + "\t"
                + "Gender: " + gender + "\t"
                + "Address: " + address + "\t"
                + "Phone: " + phone + "\t"
                + "ID: " + id + "\t"
                + "Department: " + department + "\t"
                + "Shift: " + shift + "\t"
                + "Salary: " + salary;
    }

}
