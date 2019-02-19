/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.connect.DB;
import database.table.Table1;

 
/**
 *
 * @author tahir hussain
 */
public class Database {

    public Database(){
    
    }
    
    public Database(String dbName,Table1[] tables){
        this.dbName=dbName;
        this.tables=tables;
        password="";
        driver="com.mysql.jdbc.Driver";
        userName="root";
    
    
    }
    
    
    public Database(DB DBType, String driver, String dbName, String URL, String userName, String password) {
        
        this.DBType = DBType;
        this.driver = driver;
        this.dbName = dbName;
        this.URL = URL;
        this.userName = userName;
        this.password = password;
    }
    
    public Database(Table1[] tables, DB DBType, String driver, String dbName, String URL, String userName, String password) {
        this.tables = tables;
        this.DBType = DBType;
        this.driver = driver;
        this.dbName = dbName;
        this.URL = URL;
        this.userName = userName;
        this.password = password;
    }
    

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public Table1[] getTables() {
        return tables;
    }

    public void setTables(Table1[] tables) {
        this.tables = tables;
    }

    @Override
    public String toString(){
        System.out.println("================    Table   ===============");
        String table="";
//        System.out.println("Number of Table:"+ tables.length);
        if(tables!=null)
            for(Table1 t:tables){
                table=String.format(table+"  "+t+"\n");
            }
            
        return driver+","+dbName+","+URL+","+userName+","+password+","+table;
    }
    
    
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    private Table1[] tables;
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public DB getDBType() {
        return DBType;
    }

    public void setDBType(DB DBType) {
        this.DBType = DBType;
    }
    
    

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

     
    DB DBType;
    private String driver;

    private String dbName;
    private String URL;
    private String userName;
    private String password;
    
}
