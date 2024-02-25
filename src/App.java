public class App {
    static CLI cli;
    static DataBase dataBase;
    static Logic logic;

    App(){
        dataBase=new DataBase();
        logic=new Logic();
        cli=new CLI();
        cli.LogIn();
    }

    public void run(){

    }
}