package week_1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {

    private static final String db_url = "jdbc:mysql://localhost:3306/";
    private static final String User = "chamamme";
    private static final String Pass = "1Am@Kr0fr0m$$";

    public static void main(String[] args) {
       if(connect() != null){
           System.out.println("We are connected:");
       }
    }

    public static Connection connect(){
        try{
            return DriverManager.getConnection(db_url, User, Pass);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
