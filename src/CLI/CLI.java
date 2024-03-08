package CLI;

import App.App;
import Logic.Logic;

import java.util.Scanner;
public class CLI {
    private final Scanner sc=new Scanner(System.in);

    public CLI() {

    }

    public void LogIn(){
        System.out.println("1-Admin");
        System.out.println("2-Student");
        System.out.println("Lotfan Moshakhas Konid Ba Kodam Onvan Vared Mishavid");
        System.out.println("Dar Tool Barname Ba Vared Kardan \"cancel\" Mitavanid Be In Safhe Bargardid");
        String nextLine=sc.nextLine();
        if (nextLine.equals("1")){
            System.out.println("Dar Tool Barname Ba Vared Kardan \"back\" Mitavanid Be Safhe Ghabl Bargardid");
            AdminLogIn();
        }
        else if (nextLine.equals("2")){
            System.out.println("Dar Tool Barname Ba Vared Kardan \"back\" Mitavanid Be Safhe Ghabl Bargardid");
            StudentLogIn();
        }
        else {
            System.out.println("Lotfan Dobare Vared Konid");
            LogIn();
        }
    }

    public void AdminLogIn(){
        System.out.println("lotfan Nam Karbari Khod Ra Vared Konid");
        String nextLine=sc.nextLine();
        if (nextLine.equals("Admin")){
            AdminMainPage();
        }
        else if (nextLine.equals("back")){
            LogIn();
            return;
        }
        else if (nextLine.equals("cancel")){
            LogIn();
            return;
        }
        else {
            System.out.println("Nam Karbari Eshtebah Bood");
            AdminLogIn();
        }
    }

    public void AdminMainPage(){
        Logic.ShowDepartments();
        System.out.println("Daneshkade Mored Nazar Ra Entekhab Knid");
        String Next_line=sc.nextLine();
        if (Next_line.equals("back")){
            AdminLogIn();
            return;
        }
        else if (Next_line.equals("cancel")){
            LogIn();
            return;
        }
        try {
            int nextLine=Integer.valueOf(Next_line);
            if (nextLine> App.dataBase.getDepartments().size() || nextLine<1){
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

    public void AdminGettingCoursesOfDepartment(){
        Logic.ShowCoursesOfDepartmentWithDetail();
        System.out.println("1-Ezafe Kardan Dars Be DaneshKade");
        System.out.println("2-Hazf Dars Az Daneshkade");
        System.out.println("3-Entekhab Dars Va Didan Daneshjoohay Sabtnami");
        System.out.println("4-Afzayesh Zarfiat Dars");
        String nextLine=sc.nextLine();
        if (nextLine.equals("1")){
            AdminAddCourseToDepartment();
        }
        else if (nextLine.equals("2")){
            AdminRemoveCourse();
        }
        else if (nextLine.equals("3")){
            AdminChooseCourseToCheckStudents();
        }
        else if (nextLine.equals("4")){
            AdminSizeP();
        }
        else if (nextLine.equals("back")){
            AdminMainPage();
            return;
        }
        else if (nextLine.equals("cancel")){
            LogIn();
            return;
        }
        else {
            System.out.println("Lotfan Adad Gozine Khode Ra Vared Konid");
            AdminGettingCoursesOfDepartment();
        }
    }

    public void AdminAddCourseToDepartment(){
        App.dataBase.AddCourse();
    }

    public void AdminRemoveCourse(){
        App.dataBase.RemoveCourse(); /////////////////////////////////////////////Remove The DataBase.Course Form Logged In Students DataBase.Course
    }

    public void AdminChooseCourseToCheckStudents(){
        System.out.println("Lotfan Code Dars Mored Nazar Ra Vared Konid");
        String nextLine=sc.nextLine();
        if (nextLine.equals("back")){
            AdminGettingCoursesOfDepartment();
            return;
        }
        else if (nextLine.equals("cancel")){
            LogIn();
            return;
        }
        for (int i = 0; i<App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().size(); i++){
            if (App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().get(i).getCode().equals(nextLine)) {
                Logic.CurrentCourse=i;
                AdminCheckStudents();
                return;
            }
        }
        System.out.println("Dars Mored Nazar Yaft Nashod");
        AdminChooseCourseToCheckStudents();
    }

    public void AdminCheckStudents(){
        if (!Logic.IsAttended()){
            System.out.println("In Dars Tavasot Daneshjooii Akhz Nashode");
        }
        else {
            App.dataBase.ShowLoggedInStudentsForTheCourse();
        }
        System.out.println("1-Ezafe Kardan Daneshjoo Be Dars");
        System.out.println("2-Hazf Daneshjoo Az Dars");
        String nextLine=sc.nextLine();
        if (nextLine.equals("1")){
            AdminAddStudentToTheCourse();
        }
        else if (nextLine.equals("2")){
            AdminRemoveStudentFromTheCourse();
        }
        else if (nextLine.equals("back")){
            AdminChooseCourseToCheckStudents();
            return;
        }
        else if (nextLine.equals("cancel")){
            LogIn();
            return;
        }
        else {
            AdminCheckStudents();
        }
    }

    public void AdminAddStudentToTheCourse(){
        System.out.println("Baray Ezafe Kardan Daneshjoo Be Dars, Shomare Daneshjooii Mored Nazar Ra Vared Konid");
        String nextLine=sc.nextLine();
        if (nextLine.equals("back")){
            AdminCheckStudents();
            return;
        }
        else if (nextLine.equals("cancel")){
            LogIn();
            return;
        }
        App.dataBase.AddLoggedInStudent(nextLine);
        for (int i=0;i<App.dataBase.getLoggedInStudents().size();i++){
            if (App.dataBase.getLoggedInStudents().get(i).getID().equals(nextLine)){
                for (int j=0;j<App.dataBase.getLoggedInStudents().get(i).getCourses().size();j++){
                    if (App.dataBase.getLoggedInStudents().get(i).getCourses().get(j).getCode().equals(App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().get(Logic.CurrentCourse).getCode())){
                        System.out.println("Daneshjoo Ghablan Dars Ra Akhz Karde Ast");
                        System.out.println("1-Ezafe Kardan Daneshjoo Digar");
                        System.out.println("2-Bazgasht Be Meno Ghabl");
                        nextLine = sc.nextLine();
                        if (nextLine.equals("1")){
                            AdminAddStudentToTheCourse();
                        }
                        else if (nextLine.equals("cancel")){
                            LogIn();
                        }
                        else {
                            AdminCheckStudents();
                        }
                        return;
                    }
                }
                App.dataBase.getLoggedInStudents().get(i).getCourses().add(App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().get(Logic.CurrentCourse));
                App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().get(Logic.CurrentCourse).AddCurrentSize();
                if (App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().get(Logic.CurrentCourse).getSize()<App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().get(Logic.CurrentCourse).getCurrentSize()){
                    App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().get(Logic.CurrentCourse).setSize(App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().get(Logic.CurrentCourse).getCurrentSize());
                }
            }
        }
        System.out.println("Daneshjoo Mored Nazar Ba Movafaghiat Be Dars Ezafe Shod");
        System.out.println("1-Ezafe Kardan Daneshjoo Digar");
        System.out.println("2-Bazgasht Be Meno Ghabl");
        nextLine=sc.nextLine();
        if (nextLine.equals("1")){
            AdminAddStudentToTheCourse();
        }
        else if (nextLine.equals("cancel")){
            LogIn();
            return;
        }
        else {
            AdminCheckStudents();
        }
    }

    public void AdminRemoveStudentFromTheCourse(){
        System.out.println("Baray Hazf Kardan Daneshjoo Az Dars, Shomare Daneshjooii Mored Nazar Ra Vared Konid");
        String nextLine=sc.nextLine();
        if (nextLine.equals("back")){
            AdminCheckStudents();
            return;
        }
        else if (nextLine.equals("cancel")){
            LogIn();
            return;
        }
        for (int i=0;i<App.dataBase.getLoggedInStudents().size();i++){
            if (App.dataBase.getLoggedInStudents().get(i).getID().equals(nextLine)){
                for (int j=0;j<App.dataBase.getLoggedInStudents().get(i).getCourses().size();j++){
                    if (App.dataBase.getLoggedInStudents().get(i).getCourses().get(j).getCode().equals(App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().get(Logic.CurrentCourse).getCode())){
                        App.dataBase.getLoggedInStudents().get(i).getCourses().get(j).setCurrentSize(App.dataBase.getLoggedInStudents().get(i).getCourses().get(j).getCurrentSize()-1);
                        App.dataBase.getLoggedInStudents().get(i).getCourses().remove(j);
                        System.out.println("Hazf Daneshjoo Az Dars Ba Movafaghiat Anjam Shod");
                        System.out.println("1-Hazf Daneshjoo Digar");
                        System.out.println("2-Bazgasht Be Meno Ghabl");
                        nextLine=sc.nextLine();
                        if (nextLine.equals("1")){
                            AdminRemoveStudentFromTheCourse();
                        }
                        else if (nextLine.equals("cancel")){
                            LogIn();
                            return;
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

    public void AdminSizeP(){
        System.out.println("Lotfan Code Dars Ra Baray Afzayesh Zarfiat Vared Konid");
        String nextLine=sc.nextLine();
        if (nextLine.equals("back")){
            AdminGettingCoursesOfDepartment();
            return;
        }
        else if (nextLine.equals("cancel")){
            LogIn();
            return;
        }
        for (int i = 0; i<App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().size(); i++){
            if (App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().get(i).getCode().equals(nextLine)){
                try {
                    System.out.println("Afzayesh Zarfiat Be Meghdar :");
                    nextLine=sc.nextLine();
                    if (nextLine.equals("back")){
                        AdminGettingCoursesOfDepartment();
                        return;
                    }
                    else if (nextLine.equals("cancel")){
                        LogIn();
                        return;
                    }
                    int SizeP=Integer.valueOf(nextLine);
                    App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().get(i).setSize(App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().get(i).getSize()+SizeP);
                    System.out.println("Afzayesh Zarfiat Ba Movafaghiat Anjam Shod");
                    System.out.println("1-Afzayesh Zarfiat Dars Digar");
                    System.out.println("2-Bazgasht Be Meno Ghabl");
                    nextLine=sc.nextLine();
                    if (nextLine.equals("1")){
                        AdminSizeP();
                        return;
                    }
                    else if (nextLine.equals("cancel")){
                        LogIn();
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
        System.out.println("Code Dars Ra Dorost Vared Konid");
        AdminSizeP();
    }

    public void StudentLogIn(){
        System.out.println("Lotfan Shomare Daneshjooii Khod Ra Vared Konid");
        try {
            String ID=sc.nextLine();
            if (ID.equals("back")){
                LogIn();
                return;
            }
            else if (ID.equals("cancel")){
                LogIn();
                return;
            }
            for (int i=0;i<ID.length();i++){
                if ((int)ID.charAt(i)<48 || (int)ID.charAt(i)>57){
                    throw new IllegalArgumentException();
                }
            }
            App.dataBase.AddLoggedInStudent(ID);
            for (int i=0;i<App.dataBase.getLoggedInStudents().size();i++){
                if (App.dataBase.getLoggedInStudents().get(i).getID().equals(ID)){
                    Logic.CurrentStudent=i;
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

    public void StudentMainPage(){
        System.out.println("1-List Doroos Sabtnami");
        System.out.println("2-List Doroos Erae Shode");
        String nextLine=sc.nextLine();
        if (nextLine.equals("1")){
            ShowStudentAttendedCourses();
        }
        else if (nextLine.equals("2")){
            ShowDepartments();
        }
        else if (nextLine.equals("back")){
            StudentLogIn();
            return;
        }
        else if (nextLine.equals("cancel")){
            LogIn();
            return;
        }
        else {
            System.out.println("Lotfan Adad Gozine Mored Nazar Ra Vared Konid");
            StudentMainPage();
        }
    }

    void ShowStudentAttendedCourses(){
        Logic.ShowStudentCourses();
        String nextLine;
        if (App.dataBase.getLoggedInStudents().get(Logic.CurrentStudent).getCourses().isEmpty()){
            System.out.println("Shoma Darsi Ra Akhz Nakarde Id , Lotfan Kalame \"back\" Ra type Konid");
            nextLine = sc.nextLine();
            if (nextLine.equals("cancel")){
                LogIn();
                return;
            }
            else {
                StudentMainPage();
                return;
            }
        }
        else {
            System.out.println("Ba Vared Kardan Kod Dars, Aan Dars Ra Hazf Konid");
            nextLine = sc.nextLine();
            if (nextLine.equals("cancel")){
                LogIn();
                return;
            }
            else if (nextLine.equals("back")){
                StudentMainPage();
                return;
            }
            if (Logic.IsInStudentAttendedCourse(nextLine)){
                System.out.println("Dars Ba Movafaghiat Hazf Shod");
                Logic.RemoveStudentAttendedCourse(nextLine);
                System.out.println("1-Hazf Dars Digar");
                System.out.println("2-Bazgasht Be Meno Ghabl");
                nextLine = sc.nextLine();
                if (nextLine.equals("1")){
                    ShowStudentAttendedCourses();
                }
                else if (nextLine.equals("cancel")){
                    LogIn();
                }
                else {
                    StudentMainPage();
                    return;
                }
            }
            else {
                System.out.println("Kod Dars Mored Nazar Yaft Nashod");
                System.out.println("1-Hazf Dobare Dars");
                System.out.println("2-Bazgasht Be Meno Ghabl");
                nextLine = sc.nextLine();
                if (nextLine.equals("1")){
                    ShowStudentAttendedCourses();
                    return;
                }
                else if (nextLine.equals("cancel")){
                    LogIn();
                    return;
                }
                else {
                    StudentMainPage();
                    return;
                }
            }
        }
    }

    public void ShowDepartments(){
        Logic.ShowDepartments();
        System.out.println("Daneshkade Mored Nazar Ra Entekhab Konid");
        try {
            String NextLine=sc.nextLine();
            if (NextLine.equals("back")){
                StudentMainPage();
                return;
            }
            else if (NextLine.equals("cancel")){
                LogIn();
                return;
            }
            int nextLine=Integer.valueOf(NextLine);
            if (nextLine<1 || nextLine>App.dataBase.getDepartments().size()){
                throw new IllegalArgumentException();
            }
            Logic.CurrentDepartment=nextLine-1;
            ShowDepartmentCourseForStudent();
            return;
        }
        catch (Exception e){
            System.out.println("Lotfan Daneshkade Mored Nazar Ra ba Vared Kardan Shomare Vared Konid");
            ShowDepartments();
            return;
        }
    }

    public void ShowDepartmentCourseForStudent(){
        Logic.ShowCoursesOfDepartmentWithDetail();
        System.out.println("Lotfan Ba Vared Kardan Kod Dars, Dars Mored Nazar Ra Akhz Konid");
        String nextLine=sc.nextLine();
        if (nextLine.equals("back")){
            ShowDepartments();
            return;
        }
        else if (nextLine.equals("cancel")){
            LogIn();
            return;
        }
        for (int i = 0; i<App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().size(); i++){
            if (App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().get(i).getCode().equals(nextLine)){
                Logic.CurrentCourse=i;
                StudentAttendCourse();
                return;
            }
        }
        System.out.println("Kod Dars Yaft Nashod");
        System.out.println("1-Akhz Dars Digar");
        System.out.println("2-Bazgasht Be Meno Ghabl");
        nextLine = sc.nextLine();
        if (nextLine.equals("1")){
            ShowDepartmentCourseForStudent();
            return;
        }
        else if (nextLine.equals("cancel")){
            LogIn();
            return;
        }
        else {
            ShowDepartments();
            return;
        }
    }

    public void StudentAttendCourse(){
        for (int i = 0; i<App.dataBase.getLoggedInStudents().get(Logic.CurrentStudent).getCourses().size(); i++){
            if (App.dataBase.getLoggedInStudents().get(Logic.CurrentStudent).getCourses().get(i).getCode().equals(App.dataBase.getDepartments().get(Logic.CurrentDepartment).getCourses().get(Logic.CurrentCourse).getCode())){
                System.out.println("Shoma Ghablan In Dars Ra Akhz Karde Id");
                System.out.println("1-Akhz Dars Digar");
                System.out.println("2-Bazgasht Be Meno Ghabl");
                String nextLine=sc.nextLine();
                if (nextLine.equals("1")){
                    ShowDepartmentCourseForStudent();
                }
                else if (nextLine.equals("cancel")){
                    LogIn();
                }
                else {
                    ShowDepartments();
                }
                return;
            }
        }
        if (Logic.CheckSizeForAttend() && Logic.CheckGeneralCourseForAttend() && Logic.CourseCreditLimitation() && Logic.CourseClassInterference() && Logic.CourseExamInterference()){
            App.dataBase.AttendCourse();
        }
        else {
            System.out.println("Shoma Nemitavanid Dars Ra Akhz Konid ,Be Dalil :");
            if (!Logic.CheckSizeForAttend()){
                System.out.println("\t - Zarfiat Kafi Baray In Dars Vojood Nadarad");
            }
            if (!Logic.CheckGeneralCourseForAttend()){
                System.out.println("\t - Tedad Vahed Hay Omoomi Shoma Az 5 Bishtar Mishavad");
            }
            if (!Logic.CourseCreditLimitation()){
                System.out.println("\t - Tedad VahedHay Shoma Az 20 Bishtar Mishavad");
            }
            if (!Logic.CourseClassInterference()){
                System.out.println("\t - In Dars Ba Yeki Az Dars Hay Shoma Tadakhol Darad Dar Zaman Class");
            }
            if (!Logic.CourseExamInterference()){
                System.out.println("\t - In Dars Ba Yeki Az Dars Hay Shoma Tadakhol Darad Dar Zaman Emtehan");
            }
            System.out.println("1-Akhz Dars Digar");
            System.out.println("2-Bazgasht Be Meno Ghabl");
            String nextLine=sc.nextLine();
            if (nextLine.equals("1")){
                ShowDepartmentCourseForStudent();
            }
            else if (nextLine.equals("cancel")){
                LogIn();
            }
            else {
                ShowDepartments();
            }
        }
    }
}