/*   
 * To change this license header, choose License Headers in Project Properties.   
 * To change this template file, choose Tools | Templates   
 * and open the template in the editor.   
 */   
package  resources;   
   
import database.connect.ConnectDB;  
import java.math.BigDecimal;   
import java.sql.Connection; 
import java.sql.PreparedStatement;   
import java.sql.ResultSet;   
import java.sql.ResultSetMetaData;   
import java.sql.RowId;   
import java.sql.SQLException;   
import java.sql.Timestamp;   
import java.sql.Types;   
import java.text.ParseException;   
import java.text.SimpleDateFormat;   
import java.util.Date;   
import javax.swing.JOptionPane;   
   
/**   
 *   
 * @author tahir hussain   
 */   
public class query1 {   
   
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
        Integer col_len = colName.length;   
   
        try {   
            ResultSetMetaData meta = resultSet.getMetaData();   
            for (Integer i = 0; i < col_len; i++) {   
                if (colName[i] != null && !colName[i].equals("") && values[i] != null && !values[i].equals("")) {   
                    System.out.println(" ============== >>>>>> " + colName[i] + " <<<<<<=================    " + values[i]);   
   
                    switch (meta.getColumnType(i + 1)) {   
                        case Types.ARRAY:   
                            System.out.println("ARRAY");   
                            break;   
                        case Types.BIGINT:   
                            System.out.println("Colname " + colName[i] + "  position: " + getPosition(colName, values, i));   
                            pre.setInt(col_len, i);   
                            pre.setLong(getPosition(colName, values, i), (Long)values[i]);   
                            break;   
   
                        case Types.BINARY:   
                            System.out.println("Colname " + colName[i] + "  position: " + getPosition(colName, values, i));   
   
                            pre.setInt(getPosition(colName, values, i), (Integer) values[i]);   
                            break;   
   
                        case Types.BIT:   
                            System.out.println("Colname " + colName[i] + "  position: " + getPosition(colName, values, i));   
   
                            pre.setInt(getPosition(colName, values, i), (Integer)values[i]);   
                            break;   
   
                        case Types.BLOB:   
                            System.out.println("Colname " + colName[i] + "  position: " + getPosition(colName, values, i));   
                            // pre.setBlob(getPosition(colName, values, i),);   
                            break;   
   
                        case Types.BOOLEAN:   
                            System.out.println("Colname " + colName[i] + "  position: " + getPosition(colName, values, i));   
                            pre.setBoolean(getPosition(colName, values, i), (Boolean )values[i]);   
                            break;   
                        case Types.CHAR:   
                            System.out.println("Colname " + colName[i] + "  position: " + getPosition(colName, values, i));   
                            pre.setString(getPosition(colName, values, i), ""+values[i]);   
                            break;   
                        case Types.CLOB:   
                            System.out.println("CLOB");   
                            //pre.setClob(getPosition(colName,values,i),);   
                            break;   
                        case Types.DATALINK:   
                            System.out.println("DATALINK");   
                            break;   
                        case Types.DATE:   
                            System.out.println("Colname " + colName[i] + "  position: " + getPosition(colName, values, i));   
                            System.out.println("DATE");   
                            if(values[i] instanceof java.sql.Date){   
                            pre.setDate(getPosition(colName, values, i),(java.sql.Date)values[i]);   
                            }   
                            else   
                            pre.setDate(getPosition(colName, values, i),new java.sql.Date(((java.util.Date)values[i]).getDate()));   
                            break;   
                        case Types.DECIMAL:   
                            System.out.println("DECIMAL");   
                            pre.setBigDecimal(getPosition(colName, values, i), (BigDecimal)(values[i]));   
                            break;   
                        case Types.DISTINCT:   
                            System.out.println("DISTINCT");   
                            //pre.setDouble(getPosition(colName, values, i), Double.parseDouble(values[i]));   
                            break;   
                        case Types.DOUBLE:   
                            System.out.println("Colname " + colName[i] + "  position: " + getPosition(colName, values, i));   
                            pre.setDouble(getPosition(colName, values, i), (Double)values[i]);   
                            break;   
                        case Types.FLOAT:   
                            System.out.println("Colname " + colName[i] + "  position: " + getPosition(colName, values, i));   
                            pre.setFloat(getPosition(colName, values, i), (Float)values[i]);   
                            break;   
                        case Types.INTEGER:   
                            System.out.println("INTEGER");   
                            pre.setInt(getPosition(colName, values, i), (Integer)values[i]);   
                            break;   
                        case Types.JAVA_OBJECT:   
                            System.out.println("JAVA_OBJECT");   
                            pre.setObject(getPosition(colName, values, i), values[i]);   
                            break;   
                        case Types.LONGNVARCHAR:   
                            System.out.println("LONGNVARCHAR");   
                            pre.setString(getPosition(colName, values, i),""+ values[i]);   
                            break;   
                        case Types.LONGVARBINARY:   
                            System.out.println("LONGVARBINARY");   
                            pre.setLong(getPosition(colName, values, i), (Long) values[i]);   
                            break;   
                        case Types.LONGVARCHAR:   
                            System.out.println("LONGVARCHAR");   
                            pre.setString(getPosition(colName, values, i),""+ values[i]);   
                            break;   
                        case Types.NCHAR:   
                            System.out.println("NCHAR");   
                            pre.setString(getPosition(colName, values, i),""+ values[i]);   
                            break;   
                        case Types.NCLOB:   
                            System.out.println("NCLOB");   
   
                            break;   
                        case Types.NULL:   
                            System.out.println("NULL");   
                            //pre.setNull(getPosition(colName, values, i), (Integer)values[i]);   
                            break;   
                        case Types.NUMERIC:   
                            System.out.println("NUMERIC");   
                            pre.setDouble(getPosition(colName, values, i), (Double) values[i]);   
   
                            break;   
                        case Types.NVARCHAR:   
                            System.out.println("NVARCHAR");   
                            pre.setString(getPosition(colName, values, i), ""+values[i]);   
   
                            break;   
                        case Types.OTHER:   
                            System.out.println("OTHER");   
                            break;   
                        case Types.REAL:   
                            System.out.println("REAL");   
   
                            pre.setDouble(getPosition(colName, values, i), (Double) values[i]);   
   
                            break;   
                        case Types.REF:   
                            System.out.println("REF");   
                            //setFun += String.format(colVar.concat("Txt") + ".getText(),\n");   
                            break;   
                        case Types.ROWID:   
                            System.out.println("ROWID");   
                            //pre.setRowId(getPosition(colName, values, i),   values[i]);   
                            break;   
                        case Types.SMALLINT:   
                            System.out.println("Colname " + colName[i] + "  position: " + getPosition(colName, values, i));   
                            System.out.println(values[i]);   
                            pre.setInt(getPosition(colName, values, i), (Integer)values[i]);   
                            //pre.setShort(getPosition(colName,values,i),Short.parseShort(values[i]));   
                            break;   
                        case Types.SQLXML:   
                            System.out.println("SQLXML");   
   
                            break;   
   
                        case Types.STRUCT:   
   
                            System.out.println("STRUCT");   
                            break;   
                        case Types.TIME:   
                        case Types.TIMESTAMP:   
                        case Types.TIMESTAMP_WITH_TIMEZONE:   
                            System.out.println("Colname " + colName[i] + "  position: " + getPosition(colName, values, i));   
                            System.out.println("Postion:- " + getPosition(colName, values, i));   
                             
                            if(values[i] instanceof java.sql.Date){   
                            pre.setTimestamp(getPosition(colName, values, i),new java.sql.Timestamp(((java.sql.Date)values[i]).getTime()));   
                            }   
                            else   
                            pre.setTimestamp(getPosition(colName, values, i),new Timestamp(((java.util.Date)values[i]).getTime()));   
                           
                        case Types.TINYINT:   
                            System.out.println("Colname " + colName[i] + "  position: " + getPosition(colName, values, i));   
                            pre.setInt(getPosition(colName, values, i), (Integer)values[i]);   
                            //pre.setShort(getPosition(colName,values,i),Short.parseShort(values[i]));   
                            break;   
                        case Types.VARBINARY:   
                            System.out.println("VARBINARY");   
                            pre.setInt(getPosition(colName, values, i), (Integer)values[i]);   
                            break;   
                        case Types.VARCHAR:   
                            System.out.println("Colname " + colName[i] + "  position: " + getPosition(colName, values, i) + " " + values[i]);   
                            pre.setString(getPosition(colName, values, i), ""+values[i]);   
                    }   
   
                }   
   
            }   
   
        } catch (SQLException e) {   
            JOptionPane.showMessageDialog(null, e);   
        } catch (NumberFormatException e) {   
            JOptionPane.showMessageDialog(null, e);   
        }   
   
        return pre;   
    }   
   
    public static Integer insert(String dbName, String tableVar, String[] colName, Object[] values, String[] heading) {   
        Integer i = -1;   
        System.out.println(" insertion--->" + dbName + " " + tableVar + " ");   
        //generating insert query;   
        String insertQuery=" INSERT INTO "+tableVar+" "+getColumnsAndValues(colName,values,heading);   
           
        Connection con=ConnectDB.getConnection(dbName); 
 try{ 
      
     PreparedStatement pre=con.prepareStatement(insertQuery); 
    pre=getPreparedStatement(colName, values,pre,  getResultSet(dbName,tableVar)); 
  
 }catch(SQLException ex){ 
  
 } 
  
 return i;   
       
    }   
   
    public static String getColumnsAndValues(String[] columns,Object[] values,String[] heading){   
        int i=0;   
        int length=columns.length;   
        String col="";   
        String val="";   
        do{   
              
         if(values[i]==null || values[i].toString().trim().equals("")  
                 || values[i].toString().equals("null") || values[i].toString().equals(heading[i])){   
             System.out.println("Column-->"+columns[i]+" value is empty hence skip it \n");   
         }else{   
             col+=","+columns[i];   
             val+=",?";   
         }     
           
         i++;   
        }while(i!=length);   
       
    return "("+col.replaceFirst(",","")+" VALUES "+val.replaceFirst(",","")+")";   
    }   
    
    /*   
    public static ResultSet query(String[] colName, Object[] values, PreparedStatement pre, ResultSet resultSet) {   
        ResultSet resultSet=null;   
  /*      try {   
            PreparedStatement pre1 = getPreparedStatement(colName, values, pre, resultSet);   
            resultSet = pre.executeQuery();   
   
        } catch (SQLException e) {   
            e.printStackTrace();   
        }   
       
        return resultSet;   
    }   
     */   
//    public static Integer queryUpdate(Database db,String tableName,String priOrUniCol,String[] colName, Object[] values,ResultSet resultSet) {   
    public static Integer update(String dbName, String tableVar, String priColName, String[] colName, Object[] values, String[] heading) {   
        Integer i = 0;   
        String query = "UPDATE " + tableVar + " SET ";   
        Integer len = colName.length;   
        String cl = "";   
        String priCol = "";   
        for (Integer j = 0; j < len; j++) {   
   
//            if (colName[j] != null && !colName[j].equals("")   
//                    && values[j] != null && !values[j].trim().equals("")   
//                    && !values[j].equals(":null") && !values[j].equals(heading[j]) && colName[j].equals(priColName)) {   
//                cl += "," + colName[j].concat("=?");   
//               
//            }   
//             
            if (colName[j].equals(colName[j])) {   
                priCol = values[j].toString();   
            }   
            if (values[j] == null || values[j].toString().trim().equals("") || values[j].toString().trim().equals(":null") || colName[j].equals(priColName)) {   
                values[j] = "";   
                continue;   
            }   
            cl += "," + colName[j] + "=?";   
        }   
        cl = cl.replaceFirst(",", "");   
        query = query + cl + " where " + priColName + "=" + priCol;   
        System.out.println("update query:----->" + query);   
        try {   
            PreparedStatement pre = ConnectDB.getConnection(dbName).prepareStatement(query);   
            pre = getPreparedStatement(colName, values, pre, getResultSet(dbName, tableVar));   
   
            i = pre.executeUpdate();   
            JOptionPane.showMessageDialog(null, "i=" + i);   
        } catch (SQLException e) {   
            e.printStackTrace();   
   
        }   
   
        if (i == 1) {   
            JOptionPane.showMessageDialog(null, "1 row updated");   
        }   
        return i;   
    }   
   
    public static Integer delete(String dbName, String tableVar, String priCol, String[] colName, Object[] values, String[] heading) {   
        Integer i = -1;   
        System.out.println(" deletion--->" + dbName + " " + tableVar + " " + " " + priCol);   
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
            if (colName[i] != null && !colName[i].equals("") && values[i] != null && !values[i].equals("")) {   
                ls++;   
            } else if ((colName[i] == null || colName[i].equals("")) || (values[i] == null || values[i].equals(""))) {   
                lns++;   
            }   
        }   
   
        return columnIndex - lns + 1;   
    }   
   
}   
