/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class ConnexionManager {

    private static Connection con = null;
    public static final String URLDB= "jdbc:mysql://localhost:3306/mglsi_news";
    public static final String USERDB="root";
    public static final String PWDDB="";

    public static Connection getConnection() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URLDB, USERDB, PWDDB);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    
     public static void closeConnection(Connection connection) {
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
