public class Logic {
    static int CurrentDepartment=-1;
    static int CurrentStudent=-1;
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

            if (i+1==App.dataBase.getLoggedInStudents().get(CurrentStudent).courses.size())
                return;
        }
        System.out.println("Shoma Hich Darsi Ra Akhz Nakarde Id");
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
            System.out.println("\t\t" + App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getProfessor());
            System.out.println("\t\t" + App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getCode());
            System.out.println("\t\t" + App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getSize());
            System.out.println("\t\t" + App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getCredit());
            System.out.println("\t\t" + App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getClassTime());
            System.out.println("\t\t" + App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getExamTime());
            if (App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i) instanceof SpecializedCourse){
                System.out.println("\t\t" + "Takhasosi");
            }
            else {
                System.out.println("\t\t" + "Omoomi");
            }
        }
    }

    static void CheckTheAddedCourse(String code) throws IllegalArgumentException{
        for (int i=0;i<App.dataBase.getDepartments().get(CurrentDepartment).courses.size();i++){
            if (App.dataBase.getDepartments().get(CurrentDepartment).courses.get(i).getCode().equals(code))
                throw new IllegalArgumentException();
        }
    }
}