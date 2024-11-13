package week_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String db_url = "jdbc:sql://localhost:3306/";
    private static final String User = "chamamme";
    private static final String Pass = "1Am@Kr0fr0m$$";

    private static Connection connection;
    public static Connection connectDB(){
        try{
            connection = DriverManager.getConnection(db_url, User, Pass);
            return connection;
        }catch (SQLException e){
            System.out.println("Couldn't connect to database :" + e.getMessage());
            return null;
        }
    }

    public void closeConnection(){
        try{
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
//            throw new RuntimeException(e);
        }
    }
}
