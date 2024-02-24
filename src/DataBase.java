import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class DataBase {
    private ArrayList<Department> departments;
    private ArrayList<Student> LoggedInStudents;
    DataBase(){
        departments=new ArrayList<>();
        LoggedInStudents=new ArrayList<>();
        String depName="";
        try {
            File HardCore=new File("src/HardCore.txt");
            FileInputStream fis=new FileInputStream(HardCore);
            Scanner sc=new Scanner(fis);
            while (sc.hasNextLine()){
                ArrayList<Course> courses=new ArrayList<>();
                while (true) {
                    String nextLine=sc.nextLine();
                    String name="";
                    if (nextLine.contains("Omoomi")){
                        break;
                    }
                    else if (nextLine.contains("Department of")){
                        depName=nextLine.substring(14,nextLine.length()-1);
                        continue;
                    }
                    else if (nextLine.contains("Takhasosi")){
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
                    String classTime = sc.nextLine().substring(3);
                    String examTime = sc.nextLine().substring(3);
                    courses.add(new SpecializedCourse(name,professor,code,size,credit,classTime,examTime));
                }
                while (true) {
                    String nextLine=sc.nextLine();
                    String name;
                    if (nextLine.contains("Department of")){
                        departments.add(new Department(depName,courses));
                        depName=nextLine.substring(14,nextLine.length()-1);
                        break;
                    }
                    else {
                        name = nextLine.substring(2,nextLine.length()-1);
                    }
                    String professor = sc.nextLine().substring(3);
                    String code = sc.nextLine().substring(3);
                    int size = sc.nextInt();
                    int credit = sc.nextInt();
                    sc.nextLine();
                    String classTime = sc.nextLine().substring(3);
                    String examTime = sc.nextLine().substring(3);
                    courses.add(new GeneralCourse(name,professor,code,size,credit,classTime,examTime));
                    if (!sc.hasNextLine()){
                        departments.add(new Department(depName,courses));
                        break;
                    }
                }
            }
        }
        catch (Exception e){

        }
        for (int i=0;i<departments.size();i++){
            System.out.println(departments.get(i).name);
            for (int j=0;j<departments.get(i).courses.size();j++){
                System.out.println(departments.get(i).courses.get(j).name);
            }
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
}