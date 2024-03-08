package DataBase;

import DataBase.Course;

import java.util.ArrayList;
public class Student {
    String ID;
    ArrayList<Course> courses;

    public Student(String ID, ArrayList<Course> courses) {
        this.ID = ID;
        this.courses = courses;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public String getID() {
        return ID;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}