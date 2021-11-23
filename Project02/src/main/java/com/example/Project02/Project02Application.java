package com.example.Project02;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class Project02Application {
    public static StudentList readFile(String fileName){
        StudentList list = new StudentList();
        try {
            FileReader readFile = new FileReader(fileName);
            BufferedReader br = new BufferedReader(readFile);
            String line;
            int i =0;
            while((line = br.readLine())!= null){
                if(i!=0){
//                    System.out.println(line);
                    String[] tokens = line.split(",");
//                    System.out.println(Arrays.toString(tokens));
                    Student student = new Student(Integer.parseInt(tokens[0]),tokens[1],Double.parseDouble(tokens[2]),tokens[3],tokens[4]);
//                    System.out.println(student);
                    list.addStudent(student);
                }
                i++;
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

	public static void main(String[] args) {
		SpringApplication.run(Project02Application.class, args);
    }

}
