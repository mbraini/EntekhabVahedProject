package DataBase;

import java.util.ArrayList;

public abstract class Course {
    private String name;
    private String professor;
    private String code;
    private int size;
    private int credit;
    private ArrayList<String> ClassTime;
    private String ExamTime;
    private int CurrentSize;

    public Course(String name, String professor, String code, int size, int credit, ArrayList<String> classTime, String examTime) {
        this.name = name;
        this.professor = professor;
        this.code = code;
        this.size = size;
        this.credit = credit;
        ClassTime = classTime;
        ExamTime = examTime;
        CurrentSize=0;
    }

    public String getName() {
        return name;
    }

    public String getProfessor() {
        return professor;
    }

    public String getCode() {
        return code;
    }

    public int getSize() {
        return size;
    }

    public int getCredit() {
        return credit;
    }

    public ArrayList<String> getClassTime() {
        return ClassTime;
    }

    public String getExamTime() {
        return ExamTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setClassTime(ArrayList<String> classTime) {
        ClassTime = classTime;
    }

    public void setExamTime(String examTime) {
        ExamTime = examTime;
    }

    public int getCurrentSize() {
        return CurrentSize;
    }

    public void setCurrentSize(int currentSize) {
        CurrentSize = currentSize;
    }
    public void AddCurrentSize(){
        CurrentSize++;
    }
}
