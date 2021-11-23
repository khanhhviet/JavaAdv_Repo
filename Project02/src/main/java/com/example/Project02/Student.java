package com.example.Project02;

import org.springframework.stereotype.Component;

@Component
public class Student {
    private int id;
    private String firstName;
    private double gpa;
    private String email;
    private String gender;

    public Student() {
    }

    public Student(int id, String fname, double gpa, String email, String gender) {
        this.id = id;
        this.firstName = fname;
        this.gpa = gpa;
        this.email = email;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fname='" + firstName + '\'' +
                ", gpa=" + gpa +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
