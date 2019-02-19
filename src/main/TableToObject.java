/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import database.connect.ConnectDB;
import database.table.Column1;
import java.sql.SQLException;
import java.sql.Types;
import database.connect.ConnectDB;
import database.connect.DB;
import database.table.Table1;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tahir hussain
 */
public class TableToObject {

    private enum TYPE {
        CONSTRUCTOR, METHOD
    }

    public static String getSetMethods(Table1 table) {
        String tableName = Functions.getJcName(table.getTableName());
        String tableNameVar = Functions.getName(table.getTableName());
        //for setMethod
        StringBuilder sb = new StringBuilder();

        Column1[] col = table.getColumn();
        Integer i = 0;
        Integer length = col.length;
        for (; i < length; i++) {
            System.out.println(get(col[i], i, TYPE.CONSTRUCTOR));
            sb.append(tableNameVar + "." + get(col[i], i, TYPE.CONSTRUCTOR));
        }
        return sb.append(String.format("\n")).toString();
    }

    public static String getConstructor(Table1 table) {
        String tableName = Functions.getJcName(table.getTableName());
        //for constructor
        StringBuilder sb = new StringBuilder(String.format(" new  " + tableName + "Bean(\n"
        ));

        Column1[] col = table.getColumn();
        Integer i = 0;
        Integer length = col.length;
        for (; i < length; i++) {
            System.out.println(get(col[i], i, TYPE.CONSTRUCTOR));
//            String v=i==0?",\n":"";
            sb.append(get(col[i], i, TYPE.CONSTRUCTOR) + (i == length - 1 ? "" : ",\n"));
        }

        return sb.append(")").toString();
    }

    public static Object get(Column1 col, Integer i, TYPE t) {

        String colName = Functions.getJcName(col.getColName());
        String returnValue = null;
        if (TYPE.CONSTRUCTOR == t.CONSTRUCTOR) {
            returnValue = new String("resultSet.get#Type(#)");
        } else if (TYPE.METHOD == t.METHOD) {
            returnValue = new String(String.format("set" + colName
                    + "resultSet.get#Type(#),\n"));
        }

        switch (col.getDataType()) {
            case Types.ARRAY:
                System.out.println("ARRAY");
                returnValue = returnValue.replace("#Type", "Int");

                break;
            case Types.BIGINT:
                System.out.println("BIGINT");
                returnValue = returnValue.replace("#Type", "String");
                break;

            case Types.BINARY:
                System.out.println("BINARY");
                returnValue = returnValue.replace("#Type", "Int");
                break;
            case Types.BIT:
                System.out.println("BIT");
                returnValue = returnValue.replace("#Type", "Int");
                break;
            case Types.BLOB:
                System.out.println("BLOB");
                returnValue = returnValue.replace("#Type", "Blob");
                break;
            case Types.BOOLEAN:
                System.out.println("BOOLEAN");
                returnValue = returnValue.replace("#Type", "String");
                break;
            case Types.CHAR:
                System.out.println("CHAR");
                returnValue = returnValue.replace("#Type", "String");
                break;
            case Types.CLOB:
                System.out.println("CLOB");
                returnValue = returnValue.replace("#Type", "CLOB");
                break;
            case Types.DATALINK:
                System.out.println("DATALINK");
                returnValue = returnValue.replace("#Type", "String");
                break;
            case Types.DATE:
                System.out.println("DATE");
                returnValue = returnValue.replace("#Type", "Date");
                break;
            case Types.DECIMAL:
                System.out.println("DECIMAL");

                returnValue = returnValue.replace("#Type", "BigDecimal");
                break;
            case Types.DISTINCT:
                System.out.println("DISTINCT");
                returnValue = returnValue.replace("#Type", "String");
                break;
            case Types.DOUBLE:
                System.out.println("DOUBLE");
                returnValue = returnValue.replace("#Type", "Double");
                break;
            case Types.FLOAT:
                System.out.println("FLOAT");
                returnValue = returnValue.replace("#Type", "Float");
                break;
            case Types.INTEGER:
                System.out.println("INTEGER");

                returnValue = returnValue.replace("#Type", "Int");
                break;
            case Types.JAVA_OBJECT:
                System.out.println("JAVA_OBJECT");

                returnValue = returnValue.replace("#Type", "Object");
                break;
            case Types.LONGNVARCHAR:
                System.out.println("LONGNVARCHAR");
                returnValue = returnValue.replace("#Type", "NString");
                break;
            case Types.LONGVARBINARY:
                System.out.println("LONGVARBINARY");
                returnValue = returnValue.replace("#Type", "Long");
                break;
            case Types.LONGVARCHAR:
                System.out.println("LONGVARCHAR");
                returnValue = returnValue.replace("#Type", "String");
                break;
            case Types.NCHAR:
                System.out.println("NCHAR");
                returnValue = returnValue.replace("#Type", "NString");
                break;
            case Types.NCLOB:
                System.out.println("NCLOB");
                returnValue = returnValue.replace("#Type", "String");
                break;
            case Types.NULL:
                System.out.println("NULL");
                returnValue = returnValue.replace("#Type", "String");
                break;
            case Types.NUMERIC:
                System.out.println("NUMERIC");

                returnValue = returnValue.replace("#Type", "Double");

                break;
            case Types.NVARCHAR:
                System.out.println("NVARCHAR");

                returnValue = returnValue.replace("#Type", "Double");
                break;
            case Types.OTHER:
                System.out.println("OTHER");
                returnValue = returnValue.replace("#Type", "String");
                break;
            case Types.REAL:
                System.out.println("REAL");

                returnValue = returnValue.replace("#Type", "Double");

                break;
            case Types.REF:
                System.out.println("REF");
                returnValue = returnValue.replace("#Type", "String");

                break;
            case Types.ROWID:
                System.out.println("ROWID");
                returnValue = returnValue.replace("#Type", "String");
                break;
            case Types.SMALLINT:
                System.out.println("SMALLINT");
                returnValue = returnValue.replace("#Type", "Int");

                break;
            case Types.SQLXML:
                System.out.println("SQLXML");
                returnValue = returnValue.replace("#Type", "SQLXML");

                break;

            case Types.STRUCT:

                System.out.println("STRUCT");
                returnValue = returnValue.replace("#Type", "Struct");
                break;
            case Types.TIME:

                System.out.println("TIME");

                returnValue = returnValue.replace("#Type", "Timestamp");
                break;
            case Types.TIMESTAMP:
                System.out.println("TIMESTAMP");
                returnValue = returnValue.replace("#Type", "Timestamp");

                break;
            case Types.TIMESTAMP_WITH_TIMEZONE:

                returnValue = returnValue.replace("#Type", "Timestamp");
                break;
            case Types.TINYINT:
                returnValue = returnValue.replace("#Type", "Int");
                break;
            case Types.VARBINARY:
                System.out.println("VARBINARY");
                returnValue = returnValue.replace("#Type", "Int");
                break;
            case Types.VARCHAR:
                returnValue = returnValue.replace("#Type", "String");
                break;
            default:

                break;
        }

        return returnValue = returnValue.replace("(#)", "(" + String.valueOf(i + 1) + ")");
    }

    public static Object getConstructor(Column1 col, Object obj) {

        String colName = Functions.getJcName(col.getColName());
        String returnValue = null;
        returnValue = new String(String.format("set" + colName + "(resultSet.get#Type(#);\n"));

        switch (col.getDataType()) {
            case Types.ARRAY:
                System.out.println("ARRAY");
                returnValue = returnValue.replace("#Type", "Array");
                break;
            case Types.BIGINT:
                System.out.println("BIGINT");
                if (obj.equals("null")) {
                    returnValue = returnValue.replace("(#)", "(null)");
                }
                break;

            case Types.BINARY:
                System.out.println("BINARY");

                if (obj.equals("null")) {
                    returnValue = returnValue.replace("(#)", "(null)");
                }
                break;
            case Types.BIT:
                System.out.println("BIT");
                break;
            case Types.BLOB:
                System.out.println("BLOB");
                
                if (obj.equals("null")) {
                    returnValue = returnValue.replace("(#)", "(null)");
                }
                break;
            case Types.BOOLEAN:
                System.out.println("BOOLEAN");
                break;
            case Types.CHAR:
                System.out.println("CHAR");

                String str = (String) obj;
                if (str.equals("null")) {
                    returnValue = returnValue.replace("(#)", "(null)");
                }

                break;
            case Types.CLOB:
                System.out.println("CLOB");
               if (obj.equals("null")) {
                    returnValue = returnValue.replace("(#)", "(null)");
                }
                break;
            case Types.DATALINK:
                System.out.println("DATALINK");
                break;
            case Types.DATE:
                System.out.println("DATE");

                if (obj.equals("null")) {
                    returnValue = returnValue.replace("(#)", "(null)");
                }
                break;
            case Types.DECIMAL:
                System.out.println("DECIMAL");

                returnValue = returnValue.replace("#Type", "BigDecimal");
                if (obj.equals("null")) {
                    returnValue = returnValue.replace("(#)", "(null)");
                }
                break;
            case Types.DISTINCT:
                System.out.println("DISTINCT");
                break;
            case Types.DOUBLE:
                System.out.println("DOUBLE");
                break;
            case Types.FLOAT:
                System.out.println("FLOAT");
                break;
            case Types.INTEGER:
                System.out.println("INTEGER");

                if (obj.equals("null")) {
                    returnValue = returnValue.replace("(#)", "(null)");
                }
                break;
            case Types.JAVA_OBJECT:
                System.out.println("JAVA_OBJECT");

                if (obj.equals("null")) {
                    returnValue = returnValue.replace("(#)", "(null)");
                }
                break;
            case Types.LONGNVARCHAR:
                System.out.println("LONGNVARCHAR");

                if (obj.equals("null")) {
                    returnValue = returnValue.replace("(#)", "(null)");
                }
                break;
            case Types.LONGVARBINARY:
                System.out.println("LONGVARBINARY");
                                
                if (obj.equals("null")) {
                    returnValue = returnValue.replace("(#)", "");
                }

                break;
            case Types.LONGVARCHAR:
                System.out.println("LONGVARCHAR");
                break;
            case Types.NCHAR:
                System.out.println("NCHAR");
                if (obj.equals("null")) {
                    return returnValue = returnValue.replace("(#)", "(null)");
                }
                break;
            case Types.NCLOB:
                System.out.println("NCLOB");
                break;
            case Types.NULL:
                System.out.println("NULL");
                break;
            case Types.NUMERIC:
                System.out.println("NUMERIC");
                if (obj.equals("null")) {
                    return returnValue = returnValue.replace("(#)", "(null)");
                }

                break;
            case Types.NVARCHAR:
                System.out.println("NVARCHAR");

                if (obj.equals("null")) {
                    return returnValue = returnValue.replace("(#)", "(null)");
                }
                break;
            case Types.OTHER:
                System.out.println("OTHER");
                break;
            case Types.REAL:
                System.out.println("REAL");
                if (obj.equals("null")) {
                    return returnValue = returnValue.replace("(#)", "(null)");
                }
                break;
            case Types.REF:
                System.out.println("REF");
                break;
            case Types.ROWID:
                System.out.println("ROWID");
                break;
            case Types.SMALLINT:
                System.out.println("SMALLINT");
                if (obj.equals("null")) {
                    return returnValue = returnValue.replace("(#)", "(null)");
                }
//                    Integer intData=(int)obj;

                break;
            case Types.SQLXML:
                System.out.println("SQLXML");

                break;

            case Types.STRUCT:

                System.out.println("STRUCT");
                break;
            case Types.TIME:

                System.out.println("TIME");
                if (obj.equals("0000-00-00 00-00-00")) {
                    return returnValue = returnValue.replace("(#)", "(null)");
                }

                break;
            case Types.TIMESTAMP:
                System.out.println("TIMESTAMP");
                if (obj.equals("0000-00-00 00-00-00")) {
                    return returnValue = returnValue.replace("(#)", "(null)");
                }

                break;
            case Types.TIMESTAMP_WITH_TIMEZONE:
                if (obj.equals("0000-00-00 00-00-00")) {
                    return returnValue = returnValue.replace("(#)", "(null)");
                }

                break;
            case Types.TINYINT:
                if (obj.equals("null")) {
                    return returnValue = returnValue.replace("(#)", "(null)");
                }

                break;
            case Types.VARBINARY:
                System.out.println("VARBINARY");
                if (obj.equals("null")) {
                    return returnValue = returnValue.replace("(#)", "(null)");
                }

                break;
            case Types.VARCHAR:
                if (obj.equals("null")) {
                    return returnValue = returnValue.replace("(#)", "(null)");
                }

                break;
            default:

                break;

        }

        returnValue = returnValue.replace("#Type", "String");
        return returnValue = returnValue.replace("(#)", "(" + obj + ")");

    }

}
