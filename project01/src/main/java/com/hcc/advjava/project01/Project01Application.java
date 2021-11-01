package com.hcc.advjava.project01;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Project01Application {

	public static StudentList parseJSOn(String url) throws ParseException {
		Client client =  Client.create();
		WebResource webResource = client.resource(url);

		ClientResponse clientResponse  = webResource.accept("application/json").get(ClientResponse.class);
		if(clientResponse.getStatus() != 200){
			throw new RuntimeException("Failed" + clientResponse);
		}
		JSONArray pageData = (JSONArray) new JSONParser().parse(clientResponse.getEntity(String.class));
//		System.out.println("page data: \n"+pageData);

		StudentList studentList = new StudentList();
		for(Object o: pageData) {
			JSONObject studentObject = (JSONObject) o;
//			System.out.println("Student: "+student);

			int id = Integer.parseInt(studentObject.get("id").toString());
			String fName = String.valueOf(studentObject.get("first_name"));
			String mail = String.valueOf(studentObject.get("email"));
			String gender = String.valueOf(studentObject.get("gender"));

			JSONArray courseListObject = (JSONArray) studentObject.get("course");
			CourseList courses = new CourseList();
			if (courseListObject != null) {
				for (Object obj : courseListObject) {
					JSONObject courseList = (JSONObject) obj;

					String courseNo = String.valueOf(courseList.get("courseNo"));
					String grade = String.valueOf(courseList.get("grade"));
					int creditHours = Integer.parseInt(courseList.get("creditHours").toString());
					Course course = new Course(courseNo, grade, creditHours);
					courses.addCourse(course);
				}
			}
			Student student = new Student(id, fName, mail, gender, courses);
			studentList.addStudent(student);
		}
		return studentList;
//		Iterator<Object> it = pageData.iterator();
//
//		StudentList list = new  StudentList();
//		ArrayList<Course> courses = new ArrayList<>();
//		while (it.hasNext()){
//			JSONObject jsonObject = (JSONObject) it.next();
//
//			int id =Integer.parseInt(String.valueOf(jsonObject.get("id")).trim());
//			String fName = String.valueOf(jsonObject.get("first_name")).trim();
//			String mail = String.valueOf(jsonObject.get("email")).trim();
//			String gender = String.valueOf(jsonObject.get("gender")).trim();
//
//			// nested object:  course data
//			JSONArray courseList = (JSONArray) jsonObject.get("course");
////			System.out.println( courseList);
//
//			ArrayList<Course> tempList = new ArrayList<>();
//
//			if(courseList!= null) {
//
//				Iterator<JSONObject> courseIt = courseList.iterator();
//
//				while (courseIt.hasNext()) {
//					JSONObject object = courseIt.next();
////					System.out.println(object);
//
//					int creditHours =Integer.parseInt(String.valueOf(object.get("creditHours")));
//					String courseNo=String.valueOf(object.get("courseNo"));
//					String grade = String.valueOf(object.get("grade"));
////					System.out.println(creditHours);
////					System.out.println(courseNo);
////					System.out.println(grade);
//					Course course = new Course(courseNo,grade,creditHours);
////					System.out.println(course);
//
//					tempList.add(course);
//				}
//				courses=tempList;
//			}
//			Student student =new Student(id,fName,gender,mail,courses);
//			student.setId(id);
//			student.setfName(fName);
//			student.setGender(gender);
//			student.setMail(mail);
//			student.setCourses(courseList);
//			System.out.println(student);
//			list.addStudent(student);

//		}
//		return list.getStudentList();
	}

	public static void main(String[] args) throws ParseException {
		ConfigurableApplicationContext context = SpringApplication.run(Project01Application.class, args);

		StudentList studentList = parseJSOn("https://hccs-advancejava.s3.amazonaws.com/student_course.json");
		System.out.println(studentList.getStudentList());

		System.out.println("Search result: \n" + studentList.searchByName("Caleb"));
		System.out.println(studentList.searchByName(" Alex "));
		System.out.println(studentList.searchByCourseNo("CS12"));
		System.out.println(studentList.searchByCourseNo("ABC"));

		studentList.getStudentList().forEach(e -> {
//			System.out.println(e.getCourse());
			if (!e.getCourse().getCourseList().isEmpty()) {
				System.out.println("GPA of " + e.getfName() + ": " + e.calGPA());
			} else {
				System.out.println("Cannot calculate " + e.getfName() + "'s GPA");
			}
		});

	}
}
