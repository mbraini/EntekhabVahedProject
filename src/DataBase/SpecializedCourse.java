package DataBase;

import DataBase.Course;

import java.util.ArrayList;

public class SpecializedCourse extends Course {
    public SpecializedCourse(String name, String professor, String code, int size, int credit, ArrayList<String> classTime, String examTime) {
        super(name, professor, code, size, credit, classTime, examTime);
    }
}