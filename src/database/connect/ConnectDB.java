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
public class ConnectDB {

    static Connection con;
    static String dbName;
    Enum db;
    Database database;

    private ConnectDB() {
        //defualt
        //DB DBType, String driver, String dbName, String URL, String userName, String password
        database = new Database(DB.MySQL, "com.mysql.jdbc.Driver","jdbs", "jdbc:mysql://localhost:3306", "root", "");

        try {
            Class.forName(database.getDriver());
            con = DriverManager.getConnection(database.getURL() +"/"+dbName, database.getUserName(), database.getPassword());
            //con.prepareStatement("").e;

            System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {///src/frame/newsoftframe/images/cancel/can.png
            new InformUser("JDBC Error", ex.toString(), "/frame/newsoftframe/images/information/info (1).png", "/frame/newsoftframe/images/cancel/can.png", "/frame/newsoftframe/images/ok/ok.png").setVisible(true);
        }
    }

    private ConnectDB(String dbName) {
        database = new Database(DB.MySQL, "com.mysql.jdbc.Driver",dbName, "jdbc:mysql://localhost:3306", "root", "");

        try {
            Class.forName(database.getDriver());
            con = DriverManager.getConnection(database.getURL() +"/"+database.getDbName(), database.getUserName(), database.getPassword());
            //con.prepareStatement("").e;

            System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {///src/frame/newsoftframe/images/cancel/can.png
            //new InformUser("JDBC Error", ex.toString(), "/frame/newsoftframe/images/information/info (1).png", "/frame/newsoftframe/images/cancel/can.png", "/frame/newsoftframe/images/ok/ok.png").setVisible(true);
        }
    }

    private ConnectDB(String dbName, String userName, String pwd) {
        database = new Database(DB.MySQL, "com.mysql.jdbc.Driver",dbName, "jdbc:mysql://localhost:3306",  userName, pwd);

        try {
            Class.forName(database.getDriver());
            con = DriverManager.getConnection(database.getURL() +"/"+database.getDbName(), database.getUserName(), database.getPassword());
            //con.prepareStatement("").e;

            System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {///src/frame/newsoftframe/images/cancel/can.png
            //new InformUser("JDBC Error", ex.toString(), "/frame/newsoftframe/images/information/info (1).png", "/frame/newsoftframe/images/cancel/can.png", "/frame/newsoftframe/images/ok/ok.png").setVisible(true);
        }

    }

    private ConnectDB(Database database) {

        try {
            Class.forName(database.getDriver());
//            if(database.getDbName()!=null && database.getDbName().trim().equals(""))
//            con = DriverManager.getConnection(database.getURL()+ database.getDbName(), database.getUserName(), database.getPassword());
//            else
            System.out.println("Trying to connect database");
            con = DriverManager.getConnection(database.getURL()+"/"+database.getDbName(), database.getUserName(), database.getPassword());

            //con.prepareStatement("").e;
            System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {///src/frame/newsoftframe/images/cancel/can.png
            new InformUser("JDBC Error", ex.toString(), "/frame/newsoftframe/images/information/info (1).png", "/frame/newsoftframe/images/cancel/can.png", "/frame/newsoftframe/images/ok/ok.png").setVisible(true);
        }
}

    public static Connection getConnection(Enum db, String dbn) {
        dbName = dbn;
        if (con == null) {
            new ConnectDB();
        }
        return con;
    }

    public static Connection getConnection(Database db) {
        System.out.println("ddd");
        try {

            if (con == null) {
                new ConnectDB(db);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return con;
    }

    public static Connection getConnection(String dbName) {

        try {

            if (con == null) {
                new ConnectDB(dbName);
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return con;
    }

    public static Connection getConnection(String dbName, String userName, String password) {

        if (con == null) {
            new ConnectDB(dbName, userName, password);
        }
        return con;
    }
}
