public class App {
    static CLI cli;
    static DataBase dataBase;

    App(){
        dataBase=new DataBase();
        cli=new CLI();
    }

    public void run(){
        cli.LogIn();
    }
}