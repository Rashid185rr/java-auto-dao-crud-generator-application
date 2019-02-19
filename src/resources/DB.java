/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
public class DB {

    public static String[] getValueForDB(Object[] obj){
    String[] value=new String[obj.length];
         
    for(Object object:obj){
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
        
        System.out.println("Object: "+object); 
    }
    
    return value;
    }
    
    
    
  public static PreparedStatement setPreparedStatement(String[] colName, Object[] values, PreparedStatement pre, ResultSet resultSet) {
        //String tableName, String[] colName, String[] values, String andOr
        int col_len = colName.length;

        
        System.out.println("String column names");
        for(String col:colName){
        System.out.println(col);
         
        }
System.out.println("values");        
    
for(int i=0; i<values.length; i++){
    System.out.println(values[i]);
    }

        try {
            ResultSetMetaData meta = resultSet.getMetaData();

            for (int i = 0; i < col_len; i++) {

                if (colName[i] != null && !colName[i].equals("") && values[i] != null && !values[i].equals("")) {
                System.out.println(" ============== >>>>>> "+colName[i]+" <<<<<<=================    "+values[i]);
                    
                  if (meta.getColumnType(i + 1) == Types.DATE) {
                        
                         System.out.println( "Colname "+ colName[i]+"  position: "+getPosition(colName, values, i));
                        System.out.println("DATE");
                        java.sql.Date date=null;
                       /* if(values[i] instanceof java.sql.Date)    
                            date=DateFormatter.getDate((java.sql.Date)values[i]);
                        else
                            date=DateFormatter.getDate((java.util.Date)values[i] );
                        */
                       if(values[i] instanceof java.sql.Date)    
                       date=(java.sql.Date)values[i];
                       else
                        date=new java.sql.Date(((java.util.Date)values[i]).getDate());   
                             pre.setDate(getPosition(colName, values, i),date);
                    }
                 else
                    if (meta.getColumnType(i + 1) == Types.VARCHAR) {
                        System.out.println( " varchar ");
                        
                        System.out.println( "Colname "+ colName[i]+"  position: "+getPosition(colName, values, i)+" "+values[i]);
                        
                        pre.setString(getPosition(colName, values, i), String.valueOf(values[i]));
                    } else if (meta.getColumnType(i + 1) == Types.INTEGER) {
                        System.out.println( "Colname "+ colName[i]+"  position: "+getPosition(colName, values, i));
                        
                        pre.setInt(getPosition(colName, values, i),  (int)values[i]);

                    } else if (meta.getColumnType(i + 1) == Types.BIGINT) {
                        System.out.println( "Colname "+ colName[i]+"  position: "+getPosition(colName, values, i));
                         pre.setLong(getPosition(colName, values, i), (Long)values[i]);

                    } else if (meta.getColumnType(i + 1) == Types.CHAR) {
                        System.out.println( "Colname "+ colName[i]+"  position: "+getPosition(colName, values, i));
                         pre.setString(getPosition(colName, values, i), String.valueOf(values[i]));

                    } else if (meta.getColumnType(i + 1) == Types.FLOAT) {
                         System.out.println( "Colname "+ colName[i]+"  position: "+getPosition(colName, values, i));
                        pre.setFloat(getPosition(colName, values, i), (Float)values[i]);

                    } else if (meta.getColumnType(i + 1) == Types.BOOLEAN) {
                        System.out.println( "Colname "+ colName[i]+"  position: "+getPosition(colName, values, i));
                         pre.setBoolean(getPosition(colName, values, i), (Boolean)values[i]);

                    } else if (meta.getColumnType(i + 1) == Types.DOUBLE) {
                         System.out.println( "Colname "+ colName[i]+"  position: "+getPosition(colName, values, i));
                        pre.setDouble(getPosition(colName, values, i), (Double)values[i]);
                    } else if (meta.getColumnType(i + 1) == Types.TINYINT) {
                     System.out.println( "Colname "+ colName[i]+"  position: "+getPosition(colName, values, i));
                           pre.setInt(getPosition(colName, values, i), (Integer)values[i]);
                        //pre.setShort(getPosition(colName,values,i),Short.parseShort(values[i]));

                    } else if (meta.getColumnType(i + 1) == Types.TIMESTAMP) {
                     System.out.println( "Colname "+ colName[i]+"  position: "+getPosition(colName, values, i));
                            System.out.println("Postion:- " + getPosition(colName, values, i));
                            
                            pre.setTimestamp(getPosition(colName, values, i),(Timestamp)values[i]);

                    } else if (meta.getColumnType(i + 1) == Types.SMALLINT) {
                       System.out.println( "Colname "+ colName[i]+"  position: "+getPosition(colName, values, i));
                         System.out.println(values[i]);
                        pre.setInt(getPosition(colName, values, i), (Integer)values[i]);
                        //pre.setShort(getPosition(colName,values,i),Short.parseShort(values[i]));
                    } else if (meta.getColumnType(i + 1) == Types.BLOB) {
                      System.out.println( "Colname "+ colName[i]+"  position: "+getPosition(colName, values, i));
                          
                      //pre.setBlob(   ,() );
                    }

                }

            }
            
         } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,e);
        }

        return pre;
    }

  public static ResultSet queryExecuter(String[] colName, Object[] values, PreparedStatement pre, ResultSet resultSet){
        
      try{
      PreparedStatement pre1=setPreparedStatement(colName, values, pre, resultSet);
      resultSet=pre.executeQuery();
      
      }catch(SQLException e){
      e.printStackTrace();
      
      }
      
      return resultSet;
  } 
  public static int queryUpdate(String[] colName, Object[] values, PreparedStatement pre, ResultSet resultSet){
     int i=0;
      try{
      PreparedStatement pre1=setPreparedStatement(colName, values, pre, resultSet);
      i=pre.executeUpdate();
      
      }catch(SQLException e){
      e.printStackTrace();
      
      }
      
   return i;
  }
    public static int getPosition(String[] colName, Object[] values, int columnIndex) {
        int col_len = colName.length;
        //left selected
        int ls = 0;
        // left not selected
        int lns = 0;

        for (int i = columnIndex - 1; i >= 0; i--) {
            if (colName[i] != null && !colName[i].equals("") && values[i] != null && !String.valueOf(values[i]).equals("")) {
                ls++;
            } else if ((colName[i] == null || colName[i].equals("")) || (values[i] == null || String.valueOf(values[i]).equals(""))) {
                lns++;
            }
        }

        return columnIndex - lns + 1;
    }
    
}
