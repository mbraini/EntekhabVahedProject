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
    }

    void StudentLogIn(){
        System.out.println("Lotfan Shomare Daneshjooii Khod Ra Vared Konid");
        try {
            int ID=sc.nextInt();
            /////////////////Add StudentLogIn  and Check LogedIn Students who Has LoggedIn Before
        }
        catch (Exception e){
            System.out.println("Lotfan Dorost Vared Konid");
            sc.next();
            StudentLogIn();
        }
    }
}