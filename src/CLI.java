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
            ////////
        }
        else if (nextLine.equals("2")){
            ///////
        }
        else {
            System.out.println("Lotfan Dobare Vared Konid");
            LogIn();
        }
    }
}