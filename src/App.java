public class App {
    static CLI cli;
    static DataBase dataBase;
    static Logic logic;

    App(){
        dataBase=new DataBase();
        logic=new Logic(dataBase);
        cli=new CLI();
    }

    public void run(){

    }
}