package com.example.Project02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class StudentController {

    StudentList list = Project02Application.readFile("student.txt");

    @GetMapping
    public ArrayList<Student> listStudents(){
        return list.getList();
    }

    @GetMapping("/name/{name}")
    public ArrayList<Student> searchResult(@PathVariable String name) {
        ArrayList<Student> resultSet = new ArrayList<>();
        list.getList().forEach(e -> {
            if (e.getFirstName().equals(name.trim())) {
                resultSet.add(e);
            }
        });
        return resultSet;
    }

    @GetMapping("/student")
    public ArrayList<Student> gpaAndGender(@RequestParam String gpa, @RequestParam String gender) {
        ArrayList<Student> resultSet = new ArrayList<>();
        list.getList().forEach(e -> {
            if ((e.getGpa() == Double.parseDouble(gpa)) && (e.getGender().compareToIgnoreCase(gender.trim()) == 0)) {
                resultSet.add(e);
            }
        });
        return resultSet;
    }

    @GetMapping("/gpa")
    public double avgGPA() {
        double totalGPA = list.getList().stream().mapToDouble(Student::getGpa).sum();

        return (double) totalGPA / list.getList().size();
    }
}
