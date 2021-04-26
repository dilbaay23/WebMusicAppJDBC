package repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static utility.DataCredentials.*;

/**
 * Created by Moon on 24/04/2021
 */
public class JdbcFacade {
    private Connection connection = null;
    private Statement statement = null;

    public void getConnection(){
        try {
           // Class.forName(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql){
        try {
            return statement.executeQuery(sql);
        }catch (Exception e){
            return null;
        }
    }


    public void close(){
        try {
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
