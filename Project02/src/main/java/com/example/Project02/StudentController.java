package com.example.Project02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@RestController
public class StudentController {

    public static StudentList readFile(String fileName){
        StudentList list = new StudentList();
        try {
            FileReader readFile = new FileReader(fileName);
            BufferedReader br = new BufferedReader(readFile);

            String header = br.readLine();
            String line;

            while((line = br.readLine())!= null){
//                    System.out.println(line);
                String[] tokens = line.split(",");
//                    System.out.println(Arrays.toString(tokens));
                Student student = new Student(Integer.parseInt(tokens[0]),tokens[1],Double.parseDouble(tokens[2]),tokens[3],tokens[4]);
//                    System.out.println(student);
                list.addStudent(student);
//                line=br.readLine();
            }
            br.close();
            readFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    StudentList list = readFile("student.txt");

    /**
     *
     * @return all student
     */
    @GetMapping()
    public ArrayList<Student> listStudents(){
        return list.getList();
    }

    /**
     *
     * @param name
     * @return set of students same name
     */
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

    /**
     * @param gpa
     * @param gender
     * @return set of students
     */
    @GetMapping("/student")
    public ArrayList<Student> gpaAndGender(@RequestParam String gpa, @RequestParam String gender) {
        ArrayList<Student> resultSet = new ArrayList<>();
        list.getList().forEach(e -> {
            if ((e.getGpa() == Double.parseDouble(gpa)) && (e.getGender().compareToIgnoreCase(gender.trim()) == 0)) {
                resultSet.add(e);
            }
        });
        resultSet.forEach(e-> System.out.println(e));
        return resultSet;
    }

    /**
     * @return average gpa
     */
    @GetMapping("/gpa")
    public double avgGPA() {
        double totalGPA = list.getList().stream().mapToDouble(Student::getGpa).sum();
        return (double) totalGPA / list.getList().size();
    }
}
