package DataBase;
import App.App;
import Logic.Logic;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class DataBase {
    private static String StringClassTime;
    private static String StringExamTime;
    private ArrayList<Department> departments;
    private ArrayList<Student> LoggedInStudents;
    public DataBase(){
        departments=new ArrayList<>();
        LoggedInStudents=new ArrayList<>();
        String depName="";
        try {
            File HardCore=new File("E:\\Univercity\\Term 2\\AP\\HW1\\EntekhabVahed\\src\\DataBase\\HardCore.txt");
            FileInputStream fis=new FileInputStream(HardCore);
            Scanner sc=new Scanner(fis);
            while (sc.hasNextLine()){
                String type="";
                ArrayList<Course> courses=new ArrayList<>();
                while (true) {
                    String nextLine=sc.nextLine();
                    String name="";
                    if (nextLine.contains("Omoomi")){
                        type="Omoomi";
                        continue;
                    }
                    else if (nextLine.contains("Department of") && courses.size()!=0){
                        departments.add(new Department(depName,courses));
                        depName=nextLine.substring(14,nextLine.length()-1);
                        type="Takhasosi";
                        break;
                    }
                    else if (nextLine.contains("Department of") && courses.size()==0){
                        depName=nextLine.substring(14,nextLine.length()-1);
                        type="Takhasosi";
                        continue;
                    }
                    else if (nextLine.contains("Takhasosi")){
                        type="Takhasosi";
                        continue;
                    }
                    else {
                        name = nextLine.substring(2,nextLine.length()-1);
                    }
                    String professor = sc.nextLine().substring(3);
                    String code = sc.nextLine().substring(3);
                    int size = sc.nextInt();
                    int credit = sc.nextInt();
                    sc.nextLine();
                    String ClassTime = sc.nextLine().substring(3);
                    String examTime = sc.nextLine().substring(3);
                    ArrayList<String> classTime=new ArrayList<>();
                    for (int i=0;i<ClassTime.length();i++){
                        if (ClassTime.charAt(i)=='/'){
                            classTime.add(ClassTime.substring(0,i));
                            ClassTime=ClassTime.substring(i+1);
                            i=-1;
                        }
                    }
                    classTime.add(ClassTime);
                    if (type.equals("Takhasosi")){
                        courses.add(new SpecializedCourse(name,professor,code,size,credit,classTime,examTime));
                    }
                    else if (type.equals("Omoomi")){
                        courses.add(new GeneralCourse(name,professor,code,size,credit,classTime,examTime));
                    }
                    if (!sc.hasNextLine()){
                        departments.add(new Department(depName,courses));
                        break;
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("file not found");
        }
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public ArrayList<Student> getLoggedInStudents() {
        return LoggedInStudents;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    public void setLoggedInStudents(ArrayList<Student> loggedInStudents) {
        LoggedInStudents = loggedInStudents;
    }

    public void AddLoggedInStudent(String ID){
        for (int i=0;i<LoggedInStudents.size();i++){
            if (LoggedInStudents.get(i).getID().equals(ID)){
                return;
            }
        }
        LoggedInStudents.add(new Student(ID,new ArrayList<>()));
    }

    public void getClassTime(){
        Scanner sc = new Scanner(System.in);
        String day,start,end;
        System.out.println("Rooz Class :");
        day = sc.nextLine();
        if (day.equals("back")){
            App.cli.AdminGettingCoursesOfDepartment();
            return;
        }
        else if (day.equals("cancel")){
            App.cli.LogIn();
            return;
        }
        if (!day.equals("Sunday") && !day.equals("Monday") && !day.equals("Tuesday") && !day.equals("Wednesday") && !day.equals("Thursday") && !day.equals("Friday") && !day.equals("Saturday")){
            System.out.println("Ba Format Moshakhas Shode Hamkhani Nadarad");
            getClassTime();
            return;
        }
        System.out.println("Zaman Shoroo Class :");
        start = sc.nextLine();
        if (start.equals("back")){
            App.cli.AdminGettingCoursesOfDepartment();
            return;
        }
        else if (start.equals("cancel")){
            App.cli.LogIn();
            return;
        }
        if (!Logic.CheckTime(start)){
            System.out.println("Ba Format Moshakhas Shode Hamkhani Nadarad");
            getClassTime();
            return;
        }
        System.out.println("Zaman Payan Class :");
        end = sc.nextLine();
        if (end.equals("back")){
            App.cli.AdminGettingCoursesOfDepartment();
            return;
        }
        else if (end.equals("cancel")){
            App.cli.LogIn();
            return;
        }
        if (!Logic.CheckTime(end)){
            System.out.println("Ba Format Moshakhas Shode Hamkhani Nadarad");
            getClassTime();
            return;
        }
        StringClassTime += start + " to " + end + " " + day;
        System.out.println("1-Ezafe Kardan Saat Digar");
        System.out.println("2-Por Kardan Baghie Moshakhasat");
        String nextLine = sc.nextLine();
        if (nextLine.equals("back")){
            App.cli.AdminGettingCoursesOfDepartment();
            return;
        }
        else if (nextLine.equals("cancel")){
            App.cli.LogIn();
            return;
        }
        else if (nextLine.equals("1")){
            StringClassTime += "/";
            getClassTime();
        }
        else {
            return;
        }
    }

    public void getExamTime(){
        Scanner sc = new Scanner(System.in);
        String day,start,end;
        System.out.println("Rooz Emtehan :");
        day = sc.nextLine();
        if (day.equals("back")){
            App.cli.AdminGettingCoursesOfDepartment();
            return;
        }
        else if (day.equals("cancel")){
            App.cli.LogIn();
            return;
        }
        if (!Logic.CheckDay(day)){
            System.out.println("Ba Format Moshakhas Shode Hamkhani Nadarad");
            getExamTime();
            return;
        }
        System.out.println("Zaman Shoroo Emtehan :");
        start = sc.nextLine();
        if (start.equals("back")){
            App.cli.AdminGettingCoursesOfDepartment();
            return;
        }
        else if (start.equals("cancel")){
            App.cli.LogIn();
            return;
        }
        if (!Logic.CheckTime(start)){
            System.out.println("Ba Format Moshakhas Shode Hamkhani Nadarad");
            getExamTime();
            return;
        }
        System.out.println("Zaman Payan Emtehan");
        end = sc.nextLine();
        if (end.equals("back")){
            App.cli.AdminGettingCoursesOfDepartment();
            return;
        }
        else if (end.equals("cancel")){
            App.cli.LogIn();
            return;
        }
        if (!Logic.CheckTime(end)){
            System.out.println("Ba Format Moshakhas Shode Hamkhani Nadarad");
            getExamTime();
            return;
        }
        StringExamTime += start + " to " + end + " " + day;
    }

    public void AddCourse(){
        StringClassTime = "";
        StringExamTime = "";
        Scanner sc=new Scanner(System.in);
        int size=-1,credit=-1;
        System.out.println("Loftan Etelaat Dars Mored Nazar Ra Kamel Vared Konid");
        System.out.println("Nam Dars :");
        String name=sc.nextLine();
        if (name.equals("back")){
            App.cli.AdminGettingCoursesOfDepartment();
            return;
        }
        else if (name.equals("cancel")){
            App.cli.LogIn();
            return;
        }
        System.out.println("Nam Ostad :");
        String professor=sc.nextLine();
        if (professor.equals("back")){
            App.cli.AdminGettingCoursesOfDepartment();
            return;
        }
        else if (professor.equals("cancel")){
            App.cli.LogIn();
            return;
        }
        System.out.println("code dars :");
        String code=sc.nextLine();
        if (code.equals("back")){
            App.cli.AdminGettingCoursesOfDepartment();
            return;
        }
        else if (code.equals("cancel")){
            App.cli.LogIn();
            return;
        }
        try {
            String nextLine;
            Logic.CheckTheAddedCourse(code);
            System.out.println("zarfiat dars :");
            nextLine=sc.nextLine();
            if (nextLine.equals("back")){
                App.cli.AdminGettingCoursesOfDepartment();
                return;
            }
            else if (nextLine.equals("cancel")){
                App.cli.LogIn();
                return;
            }
            size=Integer.valueOf(nextLine);
            if (size < 0)
                throw new NumberFormatException();
            System.out.println("tedad vahed");
            nextLine=sc.nextLine();
            if (nextLine.equals("back")){
                App.cli.AdminGettingCoursesOfDepartment();
                return;
            }
            else if (nextLine.equals("cancel")){
                App.cli.LogIn();
                return;
            }
            credit=Integer.valueOf(nextLine);
            if (credit <= 0){
                throw new NumberFormatException();
            }
        }
        catch (NumberFormatException e){
            System.out.println("Eshtebah Vared Kardid, Lotfan dobare Etelaat Dars Ra Vared Konid");
            AddCourse();
            return;
        }
        catch (IllegalArgumentException e){
            System.out.println("Kod Dars Tekrari Ast, Etelaat Ra Dobare Vared Konid");
            AddCourse();
            return;
        }
        System.out.println("Zaman Class :");
        System.out.println("\tLotfan Mesl Nemoone Vared Konid");
        System.out.println("\tRooz Class: Monday");
        System.out.println("\tZaman Shoroo Class : 9:00");
        System.out.println("\tZaman Payan Class : 14:05");
        getClassTime();
        System.out.println("Zaman Entehan :");
        System.out.println("\tLotfan Mesl Nemoone Vared Konid");
        System.out.println("\tRooz Emtehan: 1402/03/09");
        System.out.println("\tZaman Shoroo Emtehan : 9:00");
        System.out.println("\tZaman Payan Emtehan : 14:05");
        getExamTime();

        String ExamTime=StringExamTime;
        String ClassTime = StringClassTime;
        ArrayList<String> classTime=new ArrayList<>();
        for (int i=0;i<ClassTime.length();i++){
            if (ClassTime.charAt(i)=='/'){
                classTime.add(ClassTime.substring(0,i));
                ClassTime=ClassTime.substring(i+1);
                i=-1;
            }
        }
        classTime.add(ClassTime);
        System.out.println("Omoomi Ya Takhasosi :");
        String type=sc.nextLine();
        if (type.equals("Omoomi")){
            App.dataBase.getDepartments().get(Logic.CurrentDepartment).courses.add(new GeneralCourse(name,professor,code,size,credit,classTime,ExamTime));
        }
        else if (type.equals("Takhasosi")){
            App.dataBase.getDepartments().get(Logic.CurrentDepartment).courses.add(new SpecializedCourse(name,professor,code,size,credit,classTime,ExamTime));
        }
        else if (type.equals("back")){
            App.cli.AdminGettingCoursesOfDepartment();
            return;
        }
        else if (type.equals("cancel")){
            App.cli.LogIn();
            return;
        }
        else {
            System.out.println("Lotfan Dorost Vared Konid Noaa Dars Ra");
            AddCourse();
            return;
        }
        System.out.println("Dars Jadid Sabt Shod");
        System.out.println("1-Ezafe Kardan Dars Jadid");
        System.out.println("2-Bargasht Be Meno Ghabl");
        String nextLine=sc.nextLine();
        if (nextLine.equals("1")){
            AddCourse();
            return;
        }
        else if (nextLine.equals("back")){
            App.cli.AdminGettingCoursesOfDepartment();
            return;
        }
        else if (nextLine.equals("cancel")){
            App.cli.LogIn();
            return;
        }
        else {
            App.cli.AdminGettingCoursesOfDepartment();
        }
    }

    public void RemoveCourse(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Baray Hazf Dars, Code Dars Ra Vared Konid");
        String nextLine=sc.nextLine();
        if (nextLine.equals("back")){
            App.cli.AdminGettingCoursesOfDepartment();
            return;
        }
        else if (nextLine.equals("cancel")){
            App.cli.LogIn();
            return;
        }
        for (int i = 0; i<departments.get(Logic.CurrentDepartment).courses.size(); i++){
            if (departments.get(Logic.CurrentDepartment).courses.get(i).getCode().equals(nextLine)){
                FixLoggedInStudentCourse(nextLine);
                departments.get(Logic.CurrentDepartment).courses.remove(i);
                System.out.println("Dars Ba Movafaghiat Hazf Shod");
                System.out.println("1-Hazf Dars Digar Az Daneshkade");
                System.out.println("2-Bazgasht Be Meno Ghabl");
                nextLine=sc.nextLine();
                if (nextLine.equals("1")){
                    RemoveCourse();
                    return;
                }
                else if (nextLine.equals("cancel")){
                    App.cli.LogIn();
                    return;
                }
                else {
                    App.cli.AdminGettingCoursesOfDepartment();
                    return;
                }
            }
        }
        System.out.println("Code Dars Yaft Nashod!");
        System.out.println("1-Hazf Dars Digar");
        System.out.println("2-Bazgasht Be Meno Ghabl");
        nextLine = sc.nextLine();
        if (nextLine.equals("1")){
            App.cli.AdminRemoveCourse();
            return;
        }
        else if (nextLine.equals("cancel")){
            App.cli.LogIn();
            return;
        }
        else {
            App.cli.AdminGettingCoursesOfDepartment();
            return;
        }
    }

    public void FixLoggedInStudentCourse(String code){
        for (int i = 0;i < LoggedInStudents.size();i++){
            for (int j = 0;j < LoggedInStudents.get(i).courses.size();j++){
                if (LoggedInStudents.get(i).courses.get(j).getCode().equals(code)){
                    LoggedInStudents.get(i).courses.remove(j);
                    break;
                }
            }
        }
    }

    public void ShowLoggedInStudentsForTheCourse(){
        System.out.println(departments.get(Logic.CurrentDepartment).courses.get(Logic.CurrentCourse).getName() + " Students With ID :");
        for (int i=0;i<LoggedInStudents.size();i++){
            for (int j=0;j<LoggedInStudents.get(i).courses.size();j++){
                if (LoggedInStudents.get(i).courses.get(j).getCode().equals(departments.get(Logic.CurrentDepartment).courses.get(Logic.CurrentCourse).getCode())){
                    System.out.println("\t" + LoggedInStudents.get(i).getID());
                }
            }
        }
    }

    public void AttendCourse(){
        App.dataBase.getDepartments().get(Logic.CurrentDepartment).courses.get(Logic.CurrentCourse).AddCurrentSize();
        LoggedInStudents.get(Logic.CurrentStudent).courses.add(App.dataBase.getDepartments().get(Logic.CurrentDepartment).courses.get(Logic.CurrentCourse));
        System.out.println("Dars Mored Nazar Ba Movafaghiat Akhz Shod");
        System.out.println("1-Akhz Dars Digar");
        System.out.println("2-Bazgasht Be Meno Ghabl");
        Scanner sc=new Scanner(System.in);
        String nextLine=sc.nextLine();
        if (nextLine.equals("1")){
            App.cli.ShowDepartmentCourseForStudent();
            return;
        }
        else if (nextLine.equals("cancel")){
            App.cli.LogIn();
            return;
        }
        else {
            App.cli.ShowDepartments();
            return;
        }
    }
}