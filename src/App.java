public class App {
    static CLI cli;
    static DataBase dataBase;
    Logic logic;

    App(){
        cli=new CLI();
        dataBase=new DataBase();
        logic=new Logic(dataBase);
    }

    public void run(){

    }
}