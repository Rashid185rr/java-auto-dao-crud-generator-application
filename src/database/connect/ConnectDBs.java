/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.connect;

import database.Database;
import frame.jdbs.InformUser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author tahir hussain
 */
public class ConnectDBs {

    static Connection con;
    static String dbName;
    Enum db;
    Database database;

    public ConnectDBs() {
//default  //(DB DBType, String driver, String dbName, String URL, String userName, String password) 
        database = new Database(DB.MySQL, "com.mysql.jdbc.Driver","","jdbc:mysql://localhost:3306", "root", "");
        try {
            System.out.println("--->>>>>>>"+database.getDriver()+" \n "+database.getURL()+" "+database.getUserName()+" "+database.getPassword());
            Class.forName(database.getDriver());
        con = DriverManager.getConnection(database.getURL(),database.getUserName(), database.getPassword());
             System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {///src/frame/newsoftframe/images/cancel/can.png
//           ex.printStackTrace();
           new InformUser("JDBC Error", ex.toString(), "/frame/newsoftframe/images/information/info (3).png", "/frame/newsoftframe/images/cancel/can.png", "/frame/newsoftframe/images/ok/ok.png").setVisible(true);
        }
    }
    
public static void main(String[] a){
    new ConnectDBs();
    
}
    public ConnectDBs(String dbName) {
        database = new Database(DB.MySQL, "com.mysql.jdbc.Driver",dbName,"jdbc:mysql://localhost:3306", "root", "");

        try {
            Class.forName(database.getDriver());
System.out.println(database.getURL()+ "/"+database.getDbName()+" "+ database.getUserName()+" "+database.getPassword());
            con = DriverManager.getConnection(database.getURL() + "/"+database.getDbName(), database.getUserName(), database.getPassword());
            //con.prepareStatement("").e;

            System.out.println("connected");
        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
        } catch (SQLException ex) {///src/frame/newsoftframe/images/cancel/can.png
        ex.printStackTrace();    //new InformUser("JDBC Error", ex.toString(), "/frame/newsoftframe/images/information/info (3).png", "/frame/newsoftframe/images/cancel/can.png", "/frame/newsoftframe/images/ok/ok.png").setVisible(true);
        }
    }

    public ConnectDBs(String dbName, String userName, String pwd) {
        database = new Database(DB.MySQL, "com.mysql.jdbc.Driver",dbName, "jdbc:mysql://localhost:3306", userName, pwd);

        try {
            Class.forName(database.getDriver());
            con = DriverManager.getConnection(database.getURL() + "/"+database.getDbName(), database.getUserName(), database.getPassword());
            //con.prepareStatement("").e;

            System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {///src/frame/newsoftframe/images/cancel/can.png
ex.printStackTrace();            
//new InformUser("JDBC Error", ex.toString(), "/frame/newsoftframe/images/information/info (3).png", "/frame/newsoftframe/images/cancel/can.png", "/frame/newsoftframe/images/ok/ok.png").setVisible(true);
            
        }

    }

    public ConnectDBs(Database database) {

        try {
            Class.forName(database.getDriver());
            System.out.println("Trying to connect database");
            
            
            con = DriverManager.getConnection(database.getURL() + "/"+database.getDbName(), database.getUserName(), database.getPassword());
            System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {///src/frame/newsoftframe/images/cancel/can.png
            ex.printStackTrace();
            new InformUser("JDBC Error", ex.toString(), "/frame/newsoftframe/images/information/info (3).png", "/frame/newsoftframe/images/cancel/can.png", "/frame/newsoftframe/images/ok/ok.png").setVisible(true);
        }

    }

    public static Connection getConnection() {
        new ConnectDBs();
        return con;
    }

    public static Connection getConnection(String dbName) {
        new ConnectDBs(dbName);
        return con;
    }

    public static Connection getConnection(Database db) {
        new ConnectDBs(db);
        
        
        
        return con;
    }

    public static Connection getConnection(String dbName, String userName, String password) {
        new ConnectDBs(dbName, userName, password);
        return con;
    }

}
