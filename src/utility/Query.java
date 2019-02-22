/*        
 * To change this license header, choose License Headers in Project Properties.        
 * To change this template file, choose Tools | Templates        
 * and open the template in the editor.        
 */
package utility;

import database.Database;
import database.connect.ConnectDB;
import database.connect.ConnectDBs;
import database.connect.DB;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author tahir hussain
 */

/*

It is an alternative of the daoimpl class. If things not going to work, this class will fill part of daoimpl. 

 */
public class Query {

    public static String[] getValueForDB(Object[] obj) {
        String[] value = new String[obj.length];

        for (Object object : obj) {
            /*            
        if(object instanceof String){        
                    
                    
            }        
            else        
                if(object instanceof Integer){        
                        
                }        
            else        
                if(object instanceof Long){        
                                    
            }        
            else        
                if(object instanceof Double){        
                                        
                                        
                }             
            else        
                    if(object instanceof Timestamp){        
                            
                    }        
            else        
                        if(object instanceof Date){        
                                
                                
                        }        
                                      
             */

            System.out.println("Object: " + object);
        }

        return value;
    }

    public static PreparedStatement getPreparedStatement(String[] colName, Object[] values, PreparedStatement pre, ResultSet resultSet) {
        //String tableName, String[] colName, String[] values, String andOr        
//colName is where all column name exits,       
// values of colName exist inside the values variable, colName and values must be correct      
//      
        try {
            System.out.println("col Names");
            for (String c : colName) {
                System.out.println(c);
            }
            System.out.println("col values");
            for (Object c : values) {
                System.out.println(c);
            }
            System.out.println("======end value===");
            ResultSetMetaData meta = resultSet.getMetaData();
            Integer col_len = meta.getColumnCount();
            System.out.println("" + meta.getColumnName(1));
            int co = colName.length;
            int index = 0;
            for (int j = 0; j < co; j++) {

                for (int i = 0; i < col_len; i++) {

                    System.out.println(" " + colName[j] + "  << checkin >> " + meta.getColumnName(i + 1));
                    if (colName[j].equals(meta.getColumnName(i + 1))) {

                        System.out.println("\n\n");
                        System.out.println(colName[j] + "<-- Matched -->" + meta.getColumnName(i + 1));
                        switch (meta.getColumnType(i + 1)) {
                            case Types.ARRAY:
                                System.out.println("ARRAY");
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                break;
                            case Types.BIGINT:
                                System.out.println("Colname " + colName[index] + "  position: " + index);

                                pre.setLong(index + 1, Long.parseLong(values[index++].toString()));
                                break;

                            case Types.BINARY:
                                System.out.println("Colname " + colName[index] + "  position: " + index);

                                pre.setInt(index + 1, Integer.parseInt(values[index++].toString()));
                                break;

                            case Types.BIT:
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                pre.setBoolean(index + 1, Boolean.parseBoolean(values[index++].toString()));
                                break;

                            case Types.BLOB:
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                // pre.setBlob(index+1,);      
                                break;

                            case Types.BOOLEAN:
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                pre.setBoolean(index + 1, Boolean.parseBoolean(values[index++].toString()));
                                break;
                            case Types.CHAR:
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                pre.setString(index + 1, values[index++].toString());
                                break;
                            case Types.CLOB:
                                System.out.println("CLOB");
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                //pre.setClob(getPosition(colName,values,i),);      
                                break;
                            case Types.DATALINK:
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                System.out.println("DATALINK");
                                break;
                            case Types.DATE:
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                System.out.println("DATE");
                                if (values[index] instanceof java.sql.Date) {
                                    System.out.println("sql date");

                                    pre.setDate(index + 1, (java.sql.Date) values[index++]);
                                } else {
                                    System.out.println("util date:" + (new java.sql.Date(((java.util.Date) values[index]).getTime())));
                                    pre.setDate(index + 1, new java.sql.Date(((java.util.Date) values[index++]).getTime()));
                                }
                                break;
                            case Types.DECIMAL:
                                System.out.println("DECIMAL");
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                pre.setBigDecimal(index + 1, new BigDecimal(values[index++].toString()));
                                break;
                            case Types.DISTINCT:
                                System.out.println("DISTINCT");
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                //pre.setDouble(index+1, Double.parseDouble(values[index++].toString()));      
                                break;
                            case Types.DOUBLE:
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                pre.setDouble(index + 1, Double.parseDouble(values[index++].toString()));
                                break;
                            case Types.FLOAT:
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                pre.setFloat(index + 1, Float.parseFloat(values[index++].toString()));
                                break;
                            case Types.INTEGER:
                                System.out.println("INTEGER");
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                pre.setInt(index + 1, Integer.parseInt(values[index++].toString()));
                                break;
                            case Types.JAVA_OBJECT:
                                System.out.println("JAVA_OBJECT");
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                pre.setObject(index + 1, values[index++]);
                                break;
                            case Types.LONGNVARCHAR:
                                System.out.println("LONGNVARCHAR");
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                pre.setString(index + 1, values[index++].toString());
                                break;
                            case Types.LONGVARBINARY:
                                System.out.println("LONGVARBINARY");
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                System.out.println("    <--------- Blob file ---------> ");
//                                pre.setBlob(index + 1, Long.parseLong(values[index++].toString()));

                                break;
                            case Types.LONGVARCHAR:
                                System.out.println("LONGVARCHAR");
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                pre.setString(index + 1, values[index++].toString());
                                break;
                            case Types.NCHAR:
                                System.out.println("NCHAR");
                                System.out.println("Colname " + colName[index] + "  position: " + index + " " + values[index]);
                                pre.setString(index + 1, values[index++].toString());
                                break;
                            case Types.NCLOB:
                                System.out.println("NCLOB");
                                System.out.println("Colname " + colName[index] + "  position: " + index + " " + values[index]);

                                break;
                            case Types.NULL:
                                System.out.println("NULL");
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                //pre.setNull(index+1, Integer.parseInt(values[index++]));      
                                break;
                            case Types.NUMERIC:
                                System.out.println("NUMERIC");
                                System.out.println("Colname " + colName[index] + "  position: " + index + " " + values[index]);
                                pre.setDouble(index + 1, Double.parseDouble(values[index++].toString()));

                                break;
                            case Types.NVARCHAR:
                                System.out.println("NVARCHAR");
                                System.out.println("Colname " + colName[index] + "  position: " + index + " " + values[index]);
                                pre.setString(index + 1, values[index++].toString());

                                break;
                            case Types.OTHER:
                                System.out.println("OTHER");
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                break;
                            case Types.REAL:
                                System.out.println("REAL");
                                System.out.println("Colname " + colName[index] + "  position: " + index + " " + values[index]);

                                pre.setDouble(index + 1, Double.parseDouble(values[index++].toString()));

                                break;
                            case Types.REF:
                                System.out.println("REF");
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                //setFun += String.format(colVar.concat("Txt") + ".getText(),\n");      
                                break;
                            case Types.ROWID:
                                System.out.println("ROWID");
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                //pre.setRowId(index+1,   values[index++]);      
                                break;
                            case Types.SMALLINT:
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                System.out.println(values[index].toString());
                                pre.setInt(index + 1, Integer.parseInt(values[index++].toString()));
                                //pre.setShort(getPosition(colName,values,i),Short.parseShort(values[index++].toString()));      
                                break;
                            case Types.SQLXML:
                                System.out.println("SQLXML");
                                System.out.println("Colname " + colName[index] + "  position: " + index);

                                break;

                            case Types.STRUCT:

                                System.out.println("STRUCT");
                                break;
                            case Types.TIME:
                            case Types.TIMESTAMP:
                            case Types.TIMESTAMP_WITH_TIMEZONE:
                                System.out.println("Colname " + colName[index] + "  position: " + index);

                                if (values[index] instanceof java.sql.Date) {
                                    pre.setTimestamp(index + 1, new java.sql.Timestamp(((java.sql.Date) values[index++]).getTime()));
                                } else {
                                    pre.setTimestamp(index + 1, new Timestamp(((java.util.Date) values[index++]).getTime()));
                                }
                                break;
                            case Types.TINYINT:
                                System.out.println("Colname " + colName[index] + "  position: " + index);
                                pre.setInt(index + 1, Integer.parseInt(values[index++].toString()));
                                //pre.setShort(getPosition(colName,values,i),Short.parseShort(values[index++].toString()));      
                                break;
                            case Types.VARBINARY:
                                System.out.println("VARBINARY");
                                System.out.println("Colname " + colName[index] + "  position: " + index + " " + values[index]);
                                pre.setInt(index + 1, Integer.parseInt(values[index++].toString()));
                                break;
                            case Types.VARCHAR:
                                System.out.println("Colname " + colName[index] + "  position: " + index + " " + values[index]);
                                pre.setString(index + 1, values[index++].toString());
                        }

                        System.out.println("\n\n");
                        break;
                    }
                }

            }

        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (InputMismatchException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }

        return pre;
    }

    public static Integer insert(String dbName, String tableVar, String[] colName, Object[] values, String[] heading) {
        Integer i = -1;
        System.out.println(" insertion--->" + dbName + " " + tableVar + " ");
        //generating insert query;        

        String col = getColumnsAndValues(colName, values, heading);
        String[] columns = col.split(",");
        Object[] objectValues = getValues(values, heading);

        int length = columns.length;
        String ac = "(" + col + ") ";
        String acv = "";
        for (int j = 0; j < length; j++) {
            acv += ",?";
        }
        ac += " VALUES (" + acv.replaceFirst(",", "") + " )";
        String insertQuery = " INSERT INTO " + tableVar + " " + ac;
        Connection con = ConnectDB.getConnection(dbName);
        System.out.println("QUERY-------------->" + insertQuery);

        try {
            PreparedStatement pre = con.prepareStatement(insertQuery);
            pre = getPreparedStatement(columns, objectValues, pre, getResultSet(dbName, tableVar));
            i = pre.executeUpdate();
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Inserted");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }

        return i;

    }

    public static String getColumnsAndValues(String[] columns, Object[] values, String[] heading) {
        int i = 0;
        int length = columns.length;
        String col = "";
        do {
            if (values[i] == null || values[i].toString().trim().equals("")
                    || values[i].toString().equals("null") || values[i].toString().equals(heading[i])); else {
                col += "," + columns[i];
            }
            i++;
        } while (i != length);
        return col.replaceFirst(",", "");
    }

//    public static Integer queryUpdate(Database db,String tableName,String priOrUniCol,String[] colName, Object[] values,ResultSet resultSet) {        
    public static Integer update(String dbName, String tableVar, String priColName, String[] colName, Object[] values, String[] heading) {
        Integer i = 0;
        String query = "UPDATE " + tableVar + " SET ";
        Integer len = colName.length;
        String cl = "";
        String priCol = "";
        List<Object> objectValues = new ArrayList<>();
        String colN = "";
        for (Integer j = 0; j < len; j++) {
            if (colName[j].equals(priColName)) {
                priCol = values[j].toString();
                continue;
            }

            if (values[j] == null || values[j].toString().trim().equals("") || values[j].toString().trim().equals("null")
                    || colName[j].equals(priColName) || values[j].toString().equals(heading[j])) {
                continue;
            } else {
                objectValues.add(values[j]);
            }
            cl += "," + colName[j] + "=?";
            colN += "," + colName[j];
        }
        colN = colN.replaceFirst(",", "");
        cl = cl.replaceFirst(",", "");
        query = query + "   " + cl + " where " + priColName + "=" + priCol;
        System.out.println("update query:----->" + query);
        try {
            PreparedStatement pre = ConnectDB.getConnection(dbName).prepareStatement(query);
            pre = getPreparedStatement(colN.split(","), objectValues.toArray(), pre, getResultSet(dbName, tableVar));

            i = pre.executeUpdate();
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "row has been updated ");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        if (i == 1) {
            JOptionPane.showMessageDialog(null, "Updated");
        }
        return i;
    }

    public static Integer delete(String dbName, String tableVar, String[] colName, Object[] values, String[] heading) { 
        Integer i = -1; 
        String query = "DELETE FROM " + tableVar; 
        int len = values.length; 
        List<Object> objectValues = new ArrayList<>(); 
        String whereStatement = " WHERE "; 
        String colN = ""; 
 int count=0;
        for (Integer j = 0; j < len; j++) { 
            if (values[j] == null || values[j].toString().trim().equals("") || values[j].toString().trim().equals("null") 
                    || values[j].toString().equals(heading[j])) { 
                continue; 
            } 
            objectValues.add(values[j]); 
            if(count!=0)
            whereStatement += "  AND " + colName[j] + "=?"; 
            else{
                whereStatement+=colName[j]+"=?";
            count++;
            }
                colN += "," + colName[j]; 
        } 
        colN = colN.replaceFirst(",", ""); 
        query+=whereStatement;
        Connection con = ConnectDB.getConnection(dbName); 
        System.out.println("Query----> " + query); 
 
        try { 
            PreparedStatement pre = con.prepareStatement(query); 
            pre = getPreparedStatement(colN.split(","), objectValues.toArray(), pre, getResultSet(dbName, tableVar)); 
            i=pre.executeUpdate(); 
         if (i == 1) {
                JOptionPane.showMessageDialog(null, "Deleted" + tableVar);
            }
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
 
        return i; 
    } 

    public static ResultSet getResultSet(String dbName, String tableVar) {
        ResultSet rs = null;
        try {
            rs = ConnectDB.getConnection(dbName).prepareStatement("SELECT * FROM " + tableVar).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

//            
//    public static String getValue(String[] colName){        
//        String str="";        
//        for(String s:colName){        
//        String.format( "%s %n", (str+=" "+s));        
//        }        
//    return str;        
//    }        
    public static Integer getPosition(String[] colName, Object[] values, Integer columnIndex) {
        Integer col_len = colName.length;
        //left selected        
        Integer ls = 0;
        // left not selected        
        Integer lns = 0;

        for (Integer i = columnIndex - 1; i >= 0; i--) {
            if (colName[i] != null && !colName[i].equals("") && values[i] != null && !values[i].toString().equals("")) {
                ls++;
            } else if ((colName[i] == null || colName[i].equals("")) || (values[i] == null || values[i].toString().equals(""))) {
                lns++;
            }
        }

        return columnIndex - lns + 1;
    }

    private static Object[] getValues(Object[] values, String[] heading) {

        List<Object> obj = new ArrayList<>();
        int i = 0;
        int length = values.length;
        do {
            if (values[i] == null || values[i].toString().trim().equals("")
                    || values[i].toString().equals("null") || values[i].toString().equals(heading[i])); else {
                obj.add(values[i]);
            }
            i++;
        } while (i != length);

        return obj.toArray();
    }

    public static void main(String[] args) {

        //executeScriptFile(new Database(DB.MySQL, "com.mysql.jdbc.Driver", "", "jdbc:mysql://localhost:3306", "root", ""), new File("C:/Users/tahir hussain/Desktop/geekpos.sql"));
        //getDBNames(new Database(DB.MySQL, "com.mysql.jdbc.Driver", "/jdbs", "jdbc:mysql://localhost:3306", "root", ""));
    }

    public static String executeScriptFile(Database db, File file) {
        String dbName = "";
        Connection connection = ConnectDB.getConnection(db);

        try {
            System.out.println("Database:\n" + db);

//            Connection connection = ConnectDB.getConnection(db);
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            if (connection == null) {
                JOptionPane.showMessageDialog(null, "Not Connected");
                return null;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String instruction = "";
            String line = "";
            StringBuffer sqlFileContent = new StringBuffer();
            int counter=0;
            //create database and save it's content to the sqlFileContent
            while ((line = reader.readLine()) != null) {
                if (line.contains("Database:")) {
                    try {
                        System.out.println("Executing " + instruction + line);
                        int index = line.indexOf("Database:");
                        int cIndex = index + 9 + line.substring(index + 9).indexOf("`");
                        System.out.println(index + " " + cIndex + " ");
                        dbName = line.substring(cIndex + 1, 1 + cIndex + line.substring(cIndex + 1).indexOf("`")).trim();
                        int i = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS " + dbName + ";").executeUpdate();
                        connection.prepareStatement("USE " + dbName + ";").executeUpdate();
                        if (i == 1) {
                            JOptionPane.showMessageDialog(null, "Database created");
                        }
                    } catch (SQLException e) {
                        System.out.println("exist");
                    }
                } else if (!line.trim().startsWith("--")) {

                    if (line.contains("USE") && counter==0) {
                        int start = line.indexOf("USE");
                        start = start + line.substring(start).indexOf("`") + 1;
                        System.out.println("--------->> Rest contents  \n"+line.substring(start));
                        
                        
                        
                        int end = start + line.substring(start).indexOf("`");
                        System.out.println(line.substring(start, end));
                        dbName = line.substring(start, end);
                    counter++;
                    }

                    sqlFileContent.append(line);
                }

            }

            String[] queries = sqlFileContent.toString().split(";");
            int successful = 0;
            int unsuccessful = 0;
            int count = 0;
            int len = queries.length;
            try {
                if (dbName == null || dbName.trim().equals("")) {
                    dbName = isDBexists(db, dbName);
                    connection.prepareStatement("create database if not exists " + dbName).executeUpdate();
                    connection.prepareStatement("USE " + dbName).executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            while (count != len) {
                try {
                    System.out.println(connection.prepareStatement(queries[count]).executeUpdate());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                count++;

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Exception" + e);
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Exception" + ex);
            return null;
        }

        return dbName;
    }

    public static String isDBexists(Database db, String dbName) {

        if (dbName == null || dbName.trim().equals("")) {
            int counter = 0;

            try {
                Connection con = ConnectDB.getConnection(db);
                DatabaseMetaData meta = con.getMetaData();
                ResultSet rs = meta.getCatalogs();
                while (rs.next()) {
                    System.out.println("--->" + rs.getString(1));
                    counter++;
                }
                return "mydb" + counter;
            } catch (SQLException e) {

            }
        }

        return dbName;
    }

//defualt connection    
    public static String[] getDBNames() {
        Connection con = ConnectDBs.getConnection();
        List<String> dbNames = new ArrayList<>();
        try {
            DatabaseMetaData meta = con.getMetaData();
            ResultSet rs = meta.getCatalogs();
            while (rs.next()) {
                System.out.println("Catalog:" + rs.getString(1));
                dbNames.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dbNames.toArray(new String[0]);
    }

    public static String[] getDBNames(Database db) {
        Connection con = ConnectDBs.getConnection(db);
        List<String> dbNames = new ArrayList<>();
        try {
            DatabaseMetaData meta = con.getMetaData();
            ResultSet rs = meta.getCatalogs();
            while (rs.next()) {
                System.out.println("Catalog:" + rs.getString(1));
                dbNames.add(rs.getString(1));
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dbNames.toArray(new String[0]);
    }
}
