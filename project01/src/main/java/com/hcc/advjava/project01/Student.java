package com.hcc.advjava.project01;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class Student {

    private int id;
    private String fName;
    private String mail;
    private String gender;
    @Autowired
    private CourseList course;

    public Student(){
//        System.out.println("Student Constructor");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public CourseList getCourse() {
        return course;
    }

    public void setCourse(CourseList course) {
        this.course = course;
    }

    public Student(int id, String fName, String mail, String gender, CourseList list) {
        this.id = id;
        this.fName = fName;
        this.mail = mail;
        this.gender = gender;
        this.course = list;
    }

    private int getGradePoint(String grade){
        int gradePoint=0;
        if(grade.trim().compareToIgnoreCase("A")==0)
            gradePoint=4;
        if(grade.trim().compareToIgnoreCase("B")==0)
            gradePoint=3;
        if(grade.trim().compareToIgnoreCase("C")==0)
            gradePoint=2;
        if(grade.trim().compareToIgnoreCase("D")==0)
            gradePoint=1;
        return gradePoint;
    }

    public double calGPA(){
        double gpa=0.0;
        int totalCreditHours =0;
        int totalGradePoint=0;

        if(!course.getCourseList().isEmpty()){
            for(Course e: course.getCourseList()){
                totalCreditHours+=e.getCreditHour();
                int gradePoint= e.getCreditHour()*getGradePoint(e.getGrade());
                totalGradePoint+=gradePoint;
            }
            gpa=(double) totalGradePoint/totalCreditHours;
            return gpa;
        }
        return gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", mail='" + mail + '\'' +
                ", gender='" + gender + '\'' +
                ", courses=" + course +
                '}';
    }
}
