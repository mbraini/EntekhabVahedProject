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
}