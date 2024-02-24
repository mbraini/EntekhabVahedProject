import java.util.Scanner;
public class CLI {
    private final Scanner sc=new Scanner(System.in);
    CLI(){
        LogIn();
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
            ////////////////
        }
        else {
            System.out.println("Nam Karbari Eshtebah Bood");
            AdminLogIn();
        }
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
            StudentMainPage();
        }
        catch (Exception e){
            System.out.println("Lotfan Dorost Vared Konid");
            StudentLogIn();
        }
    }

    void StudentMainPage(){
        System.out.println("1-List Droos Sabtnami");
        System.out.println("2-List Droos Erae Shode");
        String nextLine=sc.nextLine();
        if (nextLine.equals("1")){
            //////////////////Show The Courses that he attended
        }
        else if (nextLine.equals("2")){
            /////////////////Show the Departments
        }
        else {
            System.out.println("Lotfan Adad Gozine Mored Nazar Ra Vared Konid");
            StudentMainPage();
        }
    }
}