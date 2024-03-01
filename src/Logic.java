import java.util.ArrayList;

public class Logic {
    static int CurrentDepartment=-1;
    static int CurrentStudent=-1;
    static int CurrentCourse=-1;
    Logic(){

    }

    void ShowCoursesOfDepartment(){
        System.out.println("Department Of " + App.dataBase.getDepartments().get(CurrentDepartment).name);
        for (int i=0;i<App.dataBase.getDepartments().get(CurrentDepartment).courses.size();i++){
            System.out.println("\t" + App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getName());
        }
    }

    static void ShowStudentCourses(){
        for (int i=0;i<App.dataBase.getLoggedInStudents().get(CurrentStudent).courses.size();i++){
            System.out.println(App.dataBase.getLoggedInStudents().get(CurrentStudent).courses.get(i).getName());
            System.out.println("\t" + App.dataBase.getLoggedInStudents().get(CurrentStudent).courses.get(i).getProfessor());
            System.out.println("\t" + App.dataBase.getLoggedInStudents().get(CurrentStudent).courses.get(i).getCode());
            System.out.println("\t" + App.dataBase.getLoggedInStudents().get(CurrentStudent).courses.get(i).getSize());
            System.out.println("\t" + App.dataBase.getLoggedInStudents().get(CurrentStudent).courses.get(i).getCredit());
            System.out.println("\t" + App.dataBase.getLoggedInStudents().get(CurrentStudent).courses.get(i).getClassTime());
            System.out.println("\t" + App.dataBase.getLoggedInStudents().get(CurrentStudent).courses.get(i).getExamTime());
            if ((App.dataBase.getLoggedInStudents().get(CurrentStudent).courses.get(i) instanceof SpecializedCourse)){
                System.out.println("\t" + "Takhasosi");
            }
            else {
                System.out.println("\t" + "Omoomi");
            }
        }
    }

    static void ShowDepartments(){
        for (int i=0;i<App.dataBase.getDepartments().size();i++){
            System.out.println((i+1) + "-" + App.dataBase.getDepartments().get(i).name);
        }
    }

    static void ShowCoursesOfDepartmentWithDetail(){
        System.out.println("Department Of " + App.dataBase.getDepartments().get(CurrentDepartment).name + ":");
        for (int i=0;i<App.dataBase.getDepartments().get(CurrentDepartment).courses.size();i++){
            System.out.println("\t" + App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getName());
            System.out.println("\t\t" + "Naam Ostad : " + App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getProfessor());
            System.out.println("\t\t" + "Code : " + App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getCode());
            System.out.println("\t\t" + "Zarfiat : " + App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getSize());
            System.out.println("\t\t" + "Tedad Vahed : " + App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getCredit());
            System.out.println("\t\t" + "Zaman Class : " + App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getClassTime());
            System.out.println("\t\t" + "Zaman Emtehan : " + App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getExamTime());
            if (App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i) instanceof SpecializedCourse){
                System.out.println("\t\t" + "Noa Dars : " + "Takhasosi");
            }
            else {
                System.out.println("\t\t" + "Noa Dars : " + "Omoomi");
            }
        }
    }

    static void CheckTheAddedCourse(String code) throws IllegalArgumentException{
        for (int i=0;i<App.dataBase.getDepartments().get(CurrentDepartment).courses.size();i++){
            if (App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getCode().equals(code))
                throw new IllegalArgumentException();
        }
    }

    static boolean CheckSizeForAttend(){
        if (App.dataBase.getDepartments().get(CurrentDepartment).courses.get(CurrentCourse).getCurrentSize()<App.dataBase.getDepartments().get(CurrentDepartment).courses.get(CurrentCourse).getSize()){
            return true;
        }
        else {
            ShowDepartments();
        }
        return false;
    }

    static boolean CheckGeneralCourseForAttend(){
        int Count=0;
        Count=Count+App.dataBase.getDepartments().get(CurrentDepartment).courses.get(CurrentCourse).getCredit();
        for (int i=0;i<App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().size();i++){
            if (App.dataBase.getLoggedInStudents().get(CurrentStudent).getCourses().get(i) instanceof GeneralCourse)
                Count++;
        }
        if (Count>5){
            return false;
        }
        return true;
    }

    static boolean CourseCreditLimitation(){
        int count=0;
        for (int i=0;i<App.dataBase.getLoggedInStudents().get(CurrentStudent).courses.size();i++){
            count+=App.dataBase.getLoggedInStudents().get(CurrentStudent).courses.get(i).getCredit();
        }
        count+=App.dataBase.getDepartments().get(CurrentDepartment).courses.get(CurrentCourse).getCredit();
        if (count>20){
            return false;
        }
        return true;
    }

    static boolean CourseClassInterference(){
        for (int i=0;i<App.dataBase.getDepartments().get(CurrentDepartment).courses.get(CurrentCourse).getClassTime().size();i++){
            String time=App.dataBase.getDepartments().get(CurrentDepartment).courses.get(CurrentCourse).getClassTime().get(i);
            String start=time.substring(0,time.indexOf(" "));
            time=time.substring(time.indexOf(" ")+1);
            time=time.substring(time.indexOf(" ")+1);
            String end=time.substring(0,time.indexOf(" "));
            time=time.substring(time.indexOf(" ")+1);
            String day=time;
            for (int j=0;j<App.dataBase.getLoggedInStudents().get(CurrentStudent).courses.size();j++){
                for (int k=0;k<App.dataBase.getLoggedInStudents().get(CurrentStudent).courses.get(j).getClassTime().size();k++) {
                    time = App.dataBase.getLoggedInStudents().get(CurrentStudent).courses.get(j).getClassTime().get(k);
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

    static ArrayList<String> TimeHelper(String start,String end){
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
}