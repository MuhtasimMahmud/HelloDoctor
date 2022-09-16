package com.application.HelloDoctor.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Column(unique = true)
    private String email;
    private int age;
    private String password;
    private String prescription = "";
    private String role;
    private String imageUrl;
    private String address;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate appointment_date;
    private String AppointmentStatus;



    public User(){

    }

    public User(int id, String name, String email, int age, String password, String prescription, String role, String imageUrl, String address, LocalDate appointment_date, String appointmentStatus) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.password = password;
        this.prescription = prescription;
        this.role = role;
        this.imageUrl = imageUrl;
        this.address = address;
        this.appointment_date = appointment_date;
        AppointmentStatus = appointmentStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(LocalDate appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getAppointmentStatus() {
        return AppointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        AppointmentStatus = appointmentStatus;
    }
}
