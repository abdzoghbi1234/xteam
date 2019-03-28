/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dell
 */
public class DatabaseConnection 
{
    private static String url = "jdbc:mysql://localhost:3306/pi";
    private static String login = "root";
    private static String mdp = "";
    private static Connection connection;
    
    
  private DatabaseConnection(){    
  }
  
  public static Connection getInstance() throws SQLException {
    if(connection == null || connection.isClosed()){
        try {
            connection = DriverManager.getConnection(url, login, mdp);
        } catch (SQLException e) {
            System.out.println("Database Connection Creation Failed : " + e.getMessage());
        }
    }
    return connection;   
  }   
}
