package Logic;

import App.App;
import DataBase.GeneralCourse;
import DataBase.SpecializedCourse;

import java.util.ArrayList;

public class Logic {
    public static int CurrentDepartment=-1;
    public static int CurrentStudent=-1;
    public static int CurrentCourse=-1;
    public static void ShowStudentCourses(){
        for (int i = 0; i< App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().size(); i++){
            System.out.println(App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().get(i).getName());
            System.out.println("\t" + "Naam Ostad : " + App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().get(i).getProfessor());
            System.out.println("\t" + "Code : " + App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().get(i).getCode());
            System.out.println("\t" + "Zarfiat : " + App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().get(i).getSize());
            System.out.println("\t" + "Tedad Sabtnami : " + App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().get(i).getCurrentSize());
            System.out.println("\t" + "Tedad Vahed : " + App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().get(i).getCredit());
            System.out.print("\tZaman Class : ");
            for (int j = 0;j < App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(i).getClassTime().size();j++){
                System.out.print(App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(i).getClassTime().get(j));
                if (j+1 != App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(i).getClassTime().size())
                    System.out.print("/");
            }
            System.out.println();            System.out.println("\t" + "Zaman Emtehan : " + App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().get(i).getExamTime());
            if ((App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().get(i) instanceof SpecializedCourse)){
                System.out.println("\t" + "Noa Dars : Takhasosi");
            }
            else {
                System.out.println("\t" + "Noa Dars : Omoomi");
            }
        }
    }

    public static void ShowDepartments(){
        for (int i=0;i<App.dataBase.getDepartments().size();i++){
            System.out.println((i+1) + "-" + "Department Of " + App.dataBase.getDepartments().get(i).getName());
        }
    }

    public static void ShowCoursesOfDepartmentWithDetail(){
        System.out.println("DataBase.Department Of " + App.dataBase.getDepartments().get(CurrentDepartment).getName() + ":");
        for (int i=0;i<App.dataBase.getDepartments().get(CurrentDepartment).getCourses().size();i++){
            System.out.println("\t" + App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(i).getName());
            System.out.println("\t\t" + "Naam Ostad : " + App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(i).getProfessor());
            System.out.println("\t\t" + "Code : " + App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(i).getCode());
            System.out.println("\t\t" + "Zarfiat : " + App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(i).getSize());
            System.out.println("\t\t" + "Tedad Sabtnami : " + App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(i).getCurrentSize());
            System.out.println("\t\t" + "Tedad Vahed : " + App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(i).getCredit());
            System.out.print("\t\tZaman Class : ");
            for (int j = 0;j < App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(i).getClassTime().size();j++){
                System.out.print(App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(i).getClassTime().get(j));
                if (j+1 != App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(i).getClassTime().size())
                    System.out.print("/");
            }
            System.out.println();
            System.out.println("\t\t" + "Zaman Emtehan : " + App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(i).getExamTime());
            if (App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(i) instanceof SpecializedCourse){
                System.out.println("\t\t" + "Noa Dars : " + "Takhasosi");
            }
            else {
                System.out.println("\t\t" + "Noa Dars : " + "Omoomi");
            }
        }
    }

    public static void CheckTheAddedCourse(String code) throws IllegalArgumentException{
        for (int i=0;i<App.dataBase.getDepartments().get(CurrentDepartment).getCourses().size();i++){
            if (App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(i).getCode().equals(code))
                throw new IllegalArgumentException();
        }
    }

    public static boolean CheckSizeForAttend(){
        if (App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(CurrentCourse).getCurrentSize()<App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(CurrentCourse).getSize()){
            return true;
        }
        return false;
    }

    public static boolean CheckGeneralCourseForAttend(){
        int Count=0;
        if (App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(CurrentCourse) instanceof GeneralCourse)
            Count=Count+App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(CurrentCourse).getCredit();
        for (int i=0;i<App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().size();i++){
            if (App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().get(i) instanceof GeneralCourse)
                Count+=App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().get(i).getCredit();
        }
        if (Count>5){
            return false;
        }
        return true;
    }

    public static boolean CourseCreditLimitation(){
        int count=0;
        for (int i=0;i<App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().size();i++){
            count+=App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().get(i).getCredit();
        }
        count+=App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(CurrentCourse).getCredit();
        if (count>20){
            return false;
        }
        return true;
    }

    public static boolean CourseClassInterference(){
        for (int i=0;i<App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(CurrentCourse).getClassTime().size();i++){
            String time=App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(CurrentCourse).getClassTime().get(i);
            String start=time.substring(0,time.indexOf(" "));
            time=time.substring(time.indexOf(" ")+1);
            time=time.substring(time.indexOf(" ")+1);
            String end=time.substring(0,time.indexOf(" "));
            time=time.substring(time.indexOf(" ")+1);
            String day=time;
            for (int j=0;j<App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().size();j++){
                for (int k=0;k<App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().get(j).getClassTime().size();k++) {
                    time = App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().get(j).getClassTime().get(k);
                    String start2=time.substring(0,time.indexOf(" "));
                    time=time.substring(time.indexOf(" ")+1);
                    time=time.substring(time.indexOf(" ")+1);
                    String end2=time.substring(0,time.indexOf(" "));
                    time=time.substring(time.indexOf(" ")+1);
                    String day2=time;
                    if (day.equals(day2)) {
                        ArrayList<String> T1 = TimeHelper(start, end);
                        ArrayList<String> T2 = TimeHelper(start2, end2);
                        int flag = T1.size();
                        T1.removeAll(T2);
                        if (T1.size() != flag) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static ArrayList<String> TimeHelper(String start,String end){
        int hour=Integer.valueOf(start.substring(0,start.indexOf(':')));
        int min=Integer.valueOf(start.substring(start.indexOf(':')+1));
        ArrayList<String> answer=new ArrayList<>();
        while (!start.equals(end)){
            answer.add(start);
            String a="";
            min+=1;
            if (min==60){
                min=0;
                hour+=1;
            }
            if (hour==24){
                hour=0;
            }
            a+=hour + ":";
            if (min<10){
                a+="0";
            }
            a+=min;
            start=a;
        }
        return answer;
    }

    public static boolean CourseExamInterference(){
        String exam = App.dataBase.getDepartments().get(CurrentDepartment).getCourses().get(CurrentCourse).getExamTime();
        String start=exam.substring(0,exam.indexOf(" "));
        exam=exam.substring(exam.indexOf(" ")+1);
        exam=exam.substring(exam.indexOf(" ")+1);
        String end=exam.substring(0,exam.indexOf(" "));
        exam=exam.substring(exam.indexOf(" ")+1);
        String day=exam;
        for (int i=0;i<App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().size();i++){
            exam = App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().get(i).getExamTime();
            String start2=exam.substring(0,exam.indexOf(" "));
            exam=exam.substring(exam.indexOf(" ")+1);
            exam=exam.substring(exam.indexOf(" ")+1);
            String end2=exam.substring(0,exam.indexOf(" "));
            exam=exam.substring(exam.indexOf(" ")+1);
            String day2=exam;
            if (day.equals(day2)){
                ArrayList<String> T1 = TimeHelper(start, end);
                ArrayList<String> T2 = TimeHelper(start2, end2);
                int flag = T1.size();
                T1.removeAll(T2);
                if (T1.size() != flag) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean IsInStudentAttendedCourse(String code){
        for (int i=0;i<App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().size();i++){
            if (App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().get(i).getCode().equals(code))
                return true;
        }
        return false;
    }

    public static void RemoveStudentAttendedCourse(String code){
        for (int i=0;i<App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().size();i++){
            if (App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().get(i).getCode().equals(code)) {
                App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().remove(i);
                return;
            }
        }
    }

    public static boolean IsAttended(){
        int count = 0;
        for (int i=0;i<App.dataBase.getLoggedInStudents().size();i++){
            for (int j=0;j<App.dataBase.getLoggedInStudents().get(i).getCourses().size();j++){
                if (App.dataBase.getLoggedInStudents().get(i).getCourses().get(j).getCode().equals(App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().get(Logic.CurrentCourse).getCode())){
                    count ++;
                }
            }
        }
        if (count != 0){
            return true;
        }
        return false;
    }

    public static boolean CheckTime(String time){
        if (!time.contains(":")){
            return false;
        }
        String first,second;
        try{
            first = time.substring(0,time.indexOf(":"));
            second = time.substring(time.indexOf(":") + 1);
            if (second.length()!=2){
                return false;
            }
            int IntFirst = Integer.valueOf(first);
            int IntSecond = Integer.valueOf(second);
            if (IntFirst >= 24 || IntFirst < 0 || IntSecond >= 60 || IntSecond < 0){
                return false;
            }
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public static boolean CheckDay(String time){
        String year,month,day;
        try {
            year = time.substring(0,time.indexOf("/"));
            time = time.substring(time.indexOf("/") + 1);
            month = time.substring(0,time.indexOf("/"));
            time = time.substring(time.indexOf("/") + 1);
            day = time;
            if (year.length() != 4 || month.length() != 2 || day.length() != 2)
                return false;
            int IntYear = Integer.valueOf(year);
            int IntMonth = Integer.valueOf(month);
            int IntDay = Integer.valueOf(day);
            if (IntDay > 31 || IntDay <= 0 || IntMonth > 12 || IntMonth <= 0){
                return false;
            }
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}