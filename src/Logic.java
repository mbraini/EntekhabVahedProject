public class Logic {
    DataBase dataBase;
    static int CurrentDepartment=-1;
    public Logic(DataBase dataBase){
        this.dataBase=dataBase;
    }

    void ShowCoursesOfDepartment(){
        System.out.println("Department Of " + dataBase.getDepartments().get(CurrentDepartment).name);
        for (int i=0;i<dataBase.getDepartments().get(CurrentDepartment).courses.size();i++){
            System.out.println("\t" + (i+1) + "-" + dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getName());
        }
    }
}