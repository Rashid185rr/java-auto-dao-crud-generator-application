/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package format;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tahir hussain
 */
public class ConnectDB {
   static Connection con;
    static String dbName;
   
    private ConnectDB(){
         
        try{
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName,"root", "");
                System.out.println("connected");
            }catch(SQLException ex){
                ex.printStackTrace();
            }
    }
    
    public static Connection getConnection(String dbn){
        dbName=dbn;
        
        if(con==null){
            new ConnectDB();
        }
        return con;
    }
}
