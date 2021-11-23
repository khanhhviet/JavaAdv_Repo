package com.example.Project02;

import java.util.ArrayList;

public class StudentList {
    private ArrayList<Student> list = new ArrayList<>();

    public StudentList() {}

    public ArrayList<Student> getList() {
        return list;
    }

    public ArrayList<Student> addStudent(Student student){
        list.add(student);
        return list;
    }

    @Override
    public String toString() {
        return "StudentList{" +
                "list=" + list +
                '}';
    }
}
