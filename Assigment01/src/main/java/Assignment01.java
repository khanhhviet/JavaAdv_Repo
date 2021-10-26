import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;

public class Assignment01 {
    public static StudentList parseJSOn(String url) throws ParseException {
        Client client =  Client.create();
        WebResource webResource = client.resource(url);

        ClientResponse clientResponse  = webResource.accept("application/json").get(ClientResponse.class);
        if(clientResponse.getStatus() != 200){
            throw new RuntimeException("Failed" + clientResponse);
        }

        JSONArray jsonArray = (JSONArray) new JSONParser().parse(clientResponse.getEntity(String.class));

        Iterator<Object> it = jsonArray.iterator();

        StudentList list = new  StudentList();
        while (it.hasNext()){
            JSONObject jsonObject = (JSONObject) it.next();
            int id          = Integer.parseInt(String.valueOf(jsonObject.get("id")));
            String name     = String.valueOf(jsonObject.get("first_name")).trim();
            String gender   = String.valueOf(jsonObject.get("gender")).trim();
            String mail     = String.valueOf(jsonObject.get("email")).trim();
            String gpa      = String.valueOf(jsonObject.get("gpa")).trim();

            Student student = new Student(id,name,gpa,mail,gender);
            list.addStudent(student);
        }
        return list;
    }

    public static void main(String[] args) throws ParseException {
        StudentList list = parseJSOn("https://hccs-advancejava.s3.amazonaws.com/student.json");
        System.out.println(list.getStudentList());
        list.searchByName(" Alex ");
        list.searchByName("  ");
    }

}
