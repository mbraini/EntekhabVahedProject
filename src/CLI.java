import java.util.ArrayList;
import java.util.Scanner;
public class CLI {
    private final Scanner sc=new Scanner(System.in);

    public CLI() {

    }

    void LogIn(){
        System.out.println("1-Admin");
        System.out.println("2-Student");
        String nextLine=sc.nextLine();
        if (nextLine.equals("1")){
            AdminLogIn();
        }
        else if (nextLine.equals("2")){
            StudentLogIn();
        }
        else {
            System.out.println("Lotfan Dobare Vared Konid");
            LogIn();
        }
    }

    void AdminLogIn(){
        System.out.println("lotfan Nam Karbari Khod Ra Vared Konid");
        String nextLine=sc.nextLine();
        if (nextLine.equals("Admin")){
            AdminMainPage();
        }
        else {
            System.out.println("Nam Karbari Eshtebah Bood");
            AdminLogIn();
        }
    }

    void AdminMainPage(){
        System.out.println("Daneshkade Mored Nazar Ra Entekhab Knid");
        for (int i=1;i<=App.dataBase.getDepartments().size();i++){
            System.out.println(i + "-" + App.dataBase.getDepartments().get(i-1).name);
        }
        try {
            int nextLine=Integer.valueOf(sc.nextLine());
            if (nextLine>App.dataBase.getDepartments().size() || nextLine<1){
                throw new IllegalArgumentException();
            }
            else {
                Logic.CurrentDepartment=nextLine - 1;
                AdminGettingCoursesOfDepartment();
            }
        }
        catch (Exception e){
            System.out.println("Lotfan Shomare Daneshkade Mored Nazar Ra Dorost Vared Konid");
            AdminMainPage();
            return;
        }
    }

    void AdminGettingCoursesOfDepartment(){
        App.logic.ShowCoursesOfDepartment();
        System.out.println("1-Ezafe Kardan Dars Be DaneshKade");
        System.out.println("2-Hazf Dars Az Daneshkade");
        System.out.println("3-Entekhab Dars Va Didan Daneshjoohay Sabtnami");
        System.out.println("4-Afzayesh Zarfiat Dars");
        String nextLine=sc.nextLine();
        if (nextLine.equals("1")){
            AdminAddCourseToDepartment();
        }
        else if (nextLine.equals("2")){
            App.dataBase.RemoveCourse();
        }
        else if (nextLine.equals("3")){
            AdminChooseCourseToCheckStudents();
        }
        else if (nextLine.equals("4")){
            AdminSizeP();
        }
        else {
            System.out.println("Lotfan Adad Gozine Khode Ra Vared Konid");
            AdminGettingCoursesOfDepartment();
        }
    }

    void AdminAddCourseToDepartment(){
        App.dataBase.AddCourse();
    }

    void AdminChooseCourseToCheckStudents(){
        System.out.println("Naam Dars Mored Nazar Ra Vared Konid");
        String nextLine=sc.nextLine();
        for (int i=0;i<App.dataBase.getDepartments().get(Logic.CurrentDepartment).courses.size();i++){
            if (App.dataBase.getDepartments().get(Logic.CurrentDepartment).courses.get(i).getName().equals(nextLine)) {
                Logic.CurrentCourse=i;
                AdminCheckStudents();
                return;
            }
        }
        System.out.println("Dars Mored Nazar Yaft Nashod");
        AdminChooseCourseToCheckStudents();
    }

    void AdminCheckStudents(){
        App.dataBase.ShowLoggedInStudentsForTheCourse();
        System.out.println("1-Ezafe Kardan Daneshjoo Be Dars");
        System.out.println("2-Hazf Daneshjoo Az Dars");
        String nextLine=sc.nextLine();
        if (nextLine.equals("1")){
            AdminAddStudentToTheCourse();
        }
        else if (nextLine.equals("2")){
            AdminRemoveStudentFromTheCourse();
        }
        else {
            AdminCheckStudents();
        }
    }

    void AdminAddStudentToTheCourse(){
        System.out.println("Baray Ezafe Kardan Daneshjoo Be Dars, Shomare Daneshjooii Mored Nazar Ra Vared Konid");
        String nextLine=sc.nextLine();
        App.dataBase.AddLoggedInStudent(nextLine);
        for (int i=0;i<App.dataBase.getLoggedInStudents().size();i++){
            if (App.dataBase.getLoggedInStudents().get(i).getID().equals(nextLine)){
                for (int j=0;j<App.dataBase.getLoggedInStudents().get(i).getCourses().size();j++){
                    if (App.dataBase.getLoggedInStudents().get(i).getCourses().get(j).getCode().equals(App.dataBase.getDepartments().get(Logic.CurrentDepartment).courses.get(Logic.CurrentCourse).getCode())){
                        App.dataBase.getLoggedInStudents().get(i).courses.remove(j);
                        break;
                    }
                }
                App.dataBase.getLoggedInStudents().get(i).courses.add(App.dataBase.getDepartments().get(Logic.CurrentDepartment).courses.get(Logic.CurrentCourse));
            }
        }
        System.out.println("Daneshjoo Mored Nazar Ba Movafaghiat Be Dars Ezafe Shod");
        System.out.println("1-Ezafe Kardan Daneshjoo Digar");
        System.out.println("2-Bazgasht Be Meno Ghabl");
        nextLine=sc.nextLine();
        if (nextLine.equals("1")){
            AdminAddStudentToTheCourse();
        }
        else {
            AdminCheckStudents();
        }
    }

    void AdminRemoveStudentFromTheCourse(){
        System.out.println("Baray Hazf Kardan Daneshjoo Az Dars, Shomare Daneshjooii Mored Nazar Ra Vared Konid");
        String nextLine=sc.nextLine();
        for (int i=0;i<App.dataBase.getLoggedInStudents().size();i++){
            if (App.dataBase.getLoggedInStudents().get(i).getID().equals(nextLine)){
                for (int j=0;j<App.dataBase.getLoggedInStudents().get(i).getCourses().size();j++){
                    if (App.dataBase.getLoggedInStudents().get(i).getCourses().get(j).getCode().equals(App.dataBase.getDepartments().get(Logic.CurrentDepartment).courses.get(Logic.CurrentCourse).getCode())){
                        App.dataBase.getLoggedInStudents().get(i).courses.remove(j);
                        System.out.println("Hazf Daneshjoo Az Dars Ba Movafaghiat Anjam Shod");
                        System.out.println("1-Hazf Daneshjoo Digar");
                        System.out.println("2-Bazgasht Be Meno Ghabl");
                        nextLine=sc.nextLine();
                        if (nextLine.equals("1")){
                            AdminRemoveStudentFromTheCourse();
                        }
                        else {
                            AdminCheckStudents();
                        }
                        return;
                    }
                }
                System.out.println("Daneshjoo Mored Nazar In Dars Ra Akhz Nakarde");
                AdminRemoveStudentFromTheCourse();
            }
        }
        System.out.println("Daneshjoo Mored Nazar In Dars Ra Akhz Nakarde");
        AdminRemoveStudentFromTheCourse();
    }

    void AdminSizeP(){
        System.out.println("Lotfan Naam Dars Ra Baray Afzayesh Zarfiat Vared Konid");
        String nextLine=sc.nextLine();
        for (int i=0;i<App.dataBase.getDepartments().get(Logic.CurrentDepartment).courses.size();i++){
            if (App.dataBase.getDepartments().get(Logic.CurrentDepartment).courses.get(i).getName().equals(nextLine)){
                try {
                    System.out.println("Afzayesh Zarfiat Be Meghdar :");
                    int SizeP=Integer.valueOf(sc.nextLine());
                    App.dataBase.getDepartments().get(Logic.CurrentDepartment).courses.get(i).setSize(App.dataBase.getDepartments().get(Logic.CurrentDepartment).courses.get(i).getSize()+SizeP);
                    System.out.println("Afzayesh Zarfiat Ba Movafaghiat Anjam Shod");
                    System.out.println("1-Afzayesh Zarfiat Dars Digar");
                    System.out.println("2-Bazgasht Be Meno Ghabl");
                    nextLine=sc.nextLine();
                    if (nextLine.equals("1")){
                        AdminSizeP();
                        return;
                    }
                    else {
                        AdminGettingCoursesOfDepartment();
                        return;
                    }
                }
                catch (Exception e){
                    System.out.println("Eshtebah Vared Kardid");
                    i--;
                }
            }
        }
        System.out.println("Naam Dars Ra Dorost Vared Konid");
        AdminSizeP();
    }

    void StudentLogIn(){
        System.out.println("Lotfan Shomare Daneshjooii Khod Ra Vared Konid");
        try {
            String ID=sc.nextLine();
            for (int i=0;i<ID.length();i++){
                if ((int)ID.charAt(i)<48 || (int)ID.charAt(i)>57){
                    throw new IllegalArgumentException();
                }
            }
            App.dataBase.AddLoggedInStudent(ID);
            for (int i=0;i<App.dataBase.getLoggedInStudents().size();i++){
                if (App.dataBase.getLoggedInStudents().get(i).getID().equals(ID)){
                    Logic.CurrentStudent=App.dataBase.getLoggedInStudents().size()-1;
                    StudentMainPage();
                    return;
                }
            }
        }
        catch (Exception e){
            System.out.println("Lotfan Dorost Vared Konid");
            StudentLogIn();
            return;
        }
    }

    void StudentMainPage(){
        System.out.println("1-List Droos Sabtnami");
        System.out.println("2-List Droos Erae Shode");
        String nextLine=sc.nextLine();
        if (nextLine.equals("1")){
            ShowStudentAttendedCourses();
        }
        else if (nextLine.equals("2")){
            ShowDepartments();
        }
        else {
            System.out.println("Lotfan Adad Gozine Mored Nazar Ra Vared Konid");
            StudentMainPage();
        }
    }

    void ShowStudentAttendedCourses(){
        Logic.ShowStudentCourses();
        if (App.dataBase.getLoggedInStudents().get(Logic.CurrentStudent).courses.isEmpty()){
            System.out.println("Shoma Darsi Ra Akhz Nakarde Id , Lotfan Kalame Back Ra type Konid");
        }
        else {
            System.out.println("Ba Vared Kardan Kod Dars, Aan Dars Ra Hazf Konid");
        }
        String nextLine=sc.nextLine();
        ///////////////////////////////////////////Code Dars Ra Vared Mikonad ////////////////////////////////////////////////
    }

    void ShowDepartments(){
        Logic.ShowDepartments();
        System.out.println("Daneshkade Mored Nazar Ra Entekhab Konid");
        try {
            int nextLine=Integer.valueOf(sc.nextLine());
            if (nextLine<1 || nextLine>App.dataBase.getDepartments().size()){
                throw new IllegalArgumentException();
            }
            Logic.CurrentDepartment=nextLine-1;
            ShowDepartmentCourseForStudent();
        }
        catch (Exception e){
            System.out.println("Lotfan Daneshkade Mored Nazar Ra ba Vared Kardan Shomare Vared Konid");
            ShowDepartments();
            return;
        }
    }

    void ShowDepartmentCourseForStudent(){
        Logic.ShowCoursesOfDepartmentWithDetail();
        System.out.println("Lotfan Ba Vared Kardan Kod Dars, Dars Mored Nazar Ra Akhz Konid");
        String nextLine=sc.nextLine();
        for (int i=0;i<App.dataBase.getDepartments().get(Logic.CurrentDepartment).courses.size();i++){
            if (App.dataBase.getDepartments().get(Logic.CurrentDepartment).courses.get(i).getCode().equals(nextLine)){
                Logic.CurrentCourse=i;
                StudentAttendCourse();
                return;
            }
        }
        ShowDepartmentCourseForStudent();
    }

    void StudentAttendCourse(){
        for (int i=0;i<App.dataBase.getLoggedInStudents().get(Logic.CurrentStudent).courses.size();i++){
            if (App.dataBase.getLoggedInStudents().get(Logic.CurrentStudent).courses.get(i).getCode().equals(App.dataBase.getDepartments().get(Logic.CurrentDepartment).courses.get(Logic.CurrentCourse).getCode())){
                System.out.println("Shoma Ghablan In Dars Ra Akhz Karde Id");
                ShowDepartmentCourseForStudent();
                return;
            }
        }
        if (Logic.CheckSizeForAttend()){
            App.dataBase.AttendCourse();
        }
    }
}