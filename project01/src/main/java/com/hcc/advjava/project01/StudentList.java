package com.hcc.advjava.project01;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class StudentList {
    private ArrayList<Student> studentList = new ArrayList<>();

    public StudentList() {
//        System.out.println("StudentList constructor");
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public ArrayList<Student> addStudent(Student student){
        getStudentList().add(student);
        return studentList;
    }

    public ArrayList<Student> searchByName(String name){
        String studentName = name.trim();
        ArrayList<Student> list = new ArrayList<>();
        studentList.forEach(e->{
            if (e.getfName().equals(studentName)) {
                list.add(e);
            }
        } );

        return list;
    }

    public ArrayList<Student> searchByCourseNo(String courseNo){
        String courseName = courseNo.trim();
        ArrayList<Student> list = new ArrayList<>();

        studentList.forEach(e->{
//            System.out.println(e);
            for(int i = 0; i < e.getCourse().getCourseList().size(); i++){
                if(e.getCourse().getCourseList().get(i).getCourseNo().equals(courseName)){
                    list.add(e);
                }
            }
        });
        return list;
    }
}
