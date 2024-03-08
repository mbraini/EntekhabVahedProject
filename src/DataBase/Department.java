package DataBase;

import DataBase.Course;

import java.util.ArrayList;
public class Department {
    String name;
    ArrayList<Course> courses;
    Department(String name,ArrayList<Course> courses){
        this.name=name;
        this.courses=courses;
    }
    public ArrayList<Course> getCourses(){
        return courses;
    }
    public String getName(){
        return name;
    }
}