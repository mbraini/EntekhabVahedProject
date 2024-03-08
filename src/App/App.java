package App;
import CLI.CLI;
import DataBase.DataBase;

public class App implements Runnable {
    public static CLI cli;
    public static DataBase dataBase;

    public App(){
        dataBase=new DataBase();
        cli=new CLI();
    }

    public void run(){
        cli.LogIn();
    }
}