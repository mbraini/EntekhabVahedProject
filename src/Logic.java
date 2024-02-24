public class Logic {
    static int CurrentDepartment=-1;
    static int CurrentStudent=-1;
    Logic(){

    }

    void ShowCoursesOfDepartment(){
        System.out.println("Department Of " + App.dataBase.getDepartments().get(CurrentDepartment).name);
        for (int i=0;i<App.dataBase.getDepartments().get(CurrentDepartment).courses.size();i++){
            System.out.println("\t" + (i+1) + "-" + App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getName());
        }
    }

    void AddLogInStudent(){

    }
}