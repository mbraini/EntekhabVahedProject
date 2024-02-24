import java.util.ArrayList;
public class Student {
    String ID;
    ArrayList<Course> courses;

    public Student(String ID, ArrayList<Course> courses) {
        this.ID = ID;
        this.courses = courses;
    }
}