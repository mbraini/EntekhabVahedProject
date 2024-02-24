import java.util.ArrayList;
public class Department {
    String name;
    ArrayList<Course> courses;
    Department(String name,ArrayList<Course> courses){
        this.name=name;
        this.courses=courses;
    }
}