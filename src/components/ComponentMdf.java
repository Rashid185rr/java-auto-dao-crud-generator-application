/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// here 
package components;

import com.toedter.calendar.JDateChooser;
import database.table.Column1;
import database.table.Table1;
import java.sql.Types;
import java.util.Arrays;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import main.Functions;

/**
 *
 * @author tahir hussain
 */
public class ComponentMdf {

    public static String getValues(Object[] object, String[] name) {
        String str = "";
        Integer i = 0;
        for (Object obj : object) {
            str = str + ",";

            if (obj instanceof JTextField) {
                str = str + name[i++] + ".getText();\n";
            } else if (obj instanceof JComboBox) {
                str = String.format(str + name[i++] + ".getSelectedItem();\n");
            } else if (obj instanceof JCheckBox || obj instanceof JRadioButton) {
                str = String.format(str + name[i++] + ".getName();\n");
            } else if (obj instanceof JDateChooser) {
                str = String.format(str + name[i++] + ".getName();\n");
            }

            System.out.println(str);
        }

        str = str.replaceFirst(",", "");
        System.out.println("value: --------------------> " + str);
        return str;
    }

    public static String get(String[] str) {
        String s = "";
        Integer i = 0;
        for (String ab : str) {

            if (ab.endsWith("Txt")) {
                s += String.format(str[i] + ".getText();\n");
            } else if (ab.endsWith("Ts")) {
                s += String.format(str[i] + ".getDate();\n");
            } else if (ab.endsWith("Rd")) {
                s += String.format(str[i] + ".getText();\n");
            } else if (ab.endsWith("Rd")) {
                s += String.format(str[i] + ".getText();\n");
            }
        }
        return s;
    }
 

    public static String getBeanConstructor(Table1 table, String tableName, String tableVar) {
        String setFun= String.format(tableVar + "Bean=new " + tableName + "Bean(\n");
        Column1[] column=table.getColumn();
        Integer length = column.length;

        for (Integer i=0; i<length; i++) {
            //String colName = Functions.getJcName(column[i].getColName());
            String colVar = Functions.getName(column[i].getColName());
            setFun += parseDataFromField(column[i], colVar) + ",\n";
        }
        setFun = setFun.substring(0, setFun.lastIndexOf(",")).concat(");");
        return setFun;
    }
    
    public static String getBeanConstructorWithAuto(Table1 table, String tableName, String tableVar) {
        String setFun= String.format(tableVar + "Bean=new " + tableName + "Bean(\n");
        Column1[] column=table.getColumn();
        Integer index=-1;
        if(table.isAnyAutoIncrement())
            index=table.getAutoIncrementIndex();

        Integer length = column.length;
        for (Integer i=0; i<length; i++) {
            String colName = Functions.getJcName(column[i].getColName());
            String colVar = Functions.getName(column[i].getColName());
            if(i == index) {
                setFun+=String.format(parseDataFromVar(column[i].getColName(),column[i])+"+1,\n");
            } else {
                setFun += String.format(parseDataFromField(column[i], colVar) + ",\n");
            }

        }
        
        setFun = setFun.substring(0, setFun.lastIndexOf(",")).concat(");");
        return setFun;
    }

    public static String parseDataFromField(Column1 col, String colVar) {

        switch (col.getDataType()) {
            case Types.ARRAY:
                System.out.println("ARRAY");
                return colVar.concat("Txt") + ".getText()";
            case Types.BIGINT:
                System.out.println("BIGINT");
                return colVar.concat("Txt") + ".getText()";

            case Types.BINARY:
                System.out.println("BINARY");
                return "Integer.parseInt(" + colVar.concat("Txt") + ".getText())";

            case Types.BIT:
                System.out.println("BIT");
                return "Integer.parseInt(" + colVar.concat("Txt") + ".getText())";

            case Types.BLOB:
                System.out.println("BLOB");
//                return colVar.concat("Txt") + ".getText().getBytes()";
return "";
            case Types.BOOLEAN:
                System.out.println("BOOLEAN");
                return "Boolean.parseBoolean(" + colVar.concat("Rd") + ".getText())";

            case Types.CHAR:
                System.out.println("CHAR");
                return colVar.concat("Txt") + ".getText()";

            case Types.CLOB:
                System.out.println("CLOB");
                return colVar.concat("Txt") + ".getText().getBytes()";

            case Types.DATALINK:
                System.out.println("DATALINK");
                return colVar.concat("Txt") + ".getText()";

            case Types.DATE:
                System.out.println("DATE");

                return "new java.sql.Date("+colVar.concat("Date") + ".getDate().getTime())";

            case Types.DECIMAL:
                System.out.println("DECIMAL");
                return "new BigDecimal(" + colVar.concat("Txt") + ".getText())";

            case Types.DISTINCT:
                System.out.println("DISTINCT");
                return colVar.concat("Txt") + ".getText()";

            case Types.DOUBLE:
                System.out.println("DOUBLE");

                return "Double.parseDouble(" + colVar.concat("Txt") + ".getText())";

            case Types.FLOAT:
                System.out.println("FLOAT");

                return "Float.parseFloat(" + colVar.concat("Txt") + ".getText())";

            case Types.INTEGER:
                System.out.println("INTEGER");

                return "Integer.parseInt(" + colVar.concat("Txt") + ".getText())";

            case Types.JAVA_OBJECT:
                System.out.println("JAVA_OBJECT");
                return colVar.concat("Txt") + ".getText()";

            case Types.LONGNVARCHAR:
                System.out.println("LONGNVARCHAR");
                return colVar.concat("Txt") + ".getText()";

            case Types.LONGVARBINARY:
                System.out.println("LONGVARBINARY");

//                return "Long.parseBlob( " + colVar.concat("Txt") + ".getText())";
                return "";
            case Types.LONGVARCHAR:
                System.out.println("LONGVARCHAR");
                return colVar.concat("Txt") + ".getText()";

            case Types.NCHAR:
                System.out.println("NCHAR");
                return colVar.concat("Txt") + ".getText()";

            case Types.NCLOB:
                System.out.println("NCLOB");
                return colVar.concat("Txt") + ".getText().getBytes()";

            case Types.NULL:
                System.out.println("NULL");
                return colVar.concat("Txt") + ".getText()";

            case Types.NUMERIC:
                System.out.println("NUMERIC");
                return "Double.parseDouble(" + colVar.concat("Txt") + ".getText())";

            case Types.NVARCHAR:
                System.out.println("NVARCHAR");
                return colVar.concat("Txt") + ".getText()";

            case Types.OTHER:
                System.out.println("OTHER");
                return colVar.concat("Txt") + ".getText()";

            case Types.REAL:
                System.out.println("REAL");

                return "Double.parseDouble(" + colVar.concat("Txt") + ".getText())";

            case Types.REF:
                System.out.println("REF");
                return colVar.concat("Txt") + ".getText()";

            case Types.ROWID:
                System.out.println("ROWID");
                return colVar.concat("Txt") + ".getText()";

            case Types.SMALLINT:
                System.out.println("SMALLINT");
                return "Integer.parseInt(" + colVar.concat("Txt") + ".getText())";

            case Types.SQLXML:
                System.out.println("SQLXML");
            case Types.STRUCT:
                System.out.println("STRUCT");
            case Types.TIME:
            case Types.TIMESTAMP:
            case Types.TIMESTAMP_WITH_TIMEZONE:
                return "new java.sql.Timestamp("+ colVar.concat("Ts") + ".getDate().getTime())";

            case Types.TINYINT:
                return "Integer.parseInt(" + colVar.concat("Txt") + ".getText())";

            case Types.VARBINARY:
                System.out.println("VARBINARY");

                return "Integer.parseInt(" + colVar.concat("Txt") + ".getText())";

            case Types.VARCHAR:
                return colVar.concat("Txt") + ".getText()";
        }
        return null;
    }
    
    public static String getValueWithoutParse(Column1 col, String colVar) {

        switch (col.getDataType()) {
            case Types.ARRAY:
                System.out.println("ARRAY");
                return colVar.concat("Txt") + ".getText()";
            case Types.BIGINT:
                System.out.println("BIGINT");
                return  colVar.concat("Txt") + ".getText()";

            case Types.BINARY:
                System.out.println("BINARY");
                return colVar.concat("Txt") + ".getText()";

            case Types.BIT:
                System.out.println("BIT");
                return colVar.concat("Txt") + ".getText()";

            case Types.BLOB:
                System.out.println("BLOB");
//                return colVar.concat("Txt") + ".getText()";
                return "";
            case Types.BOOLEAN:
                System.out.println("BOOLEAN");
                return colVar.concat("Rd") + ".getText()";

            case Types.CHAR:
                System.out.println("CHAR");
                return colVar.concat("Txt") + ".getText()";

            case Types.CLOB:
                System.out.println("CLOB");
                return colVar.concat("Txt") + ".getText()";

            case Types.DATALINK:
                System.out.println("DATALINK");
                return colVar.concat("Txt") + ".getText()";

            case Types.DATE:
                System.out.println("DATE");

                return colVar.concat("Date") + ".getDate()";

            case Types.DECIMAL:
                System.out.println("DECIMAL");
                return colVar.concat("Txt") + ".getText()";

            case Types.DISTINCT:
                System.out.println("DISTINCT");
                return colVar.concat("Txt") + ".getText()";

            case Types.DOUBLE:
                System.out.println("DOUBLE");

                return colVar.concat("Txt") + ".getText()";

            case Types.FLOAT:
                System.out.println("FLOAT");

                return colVar.concat("Txt") + ".getText()";

            case Types.INTEGER:
                System.out.println("INTEGER");

                return colVar.concat("Txt") + ".getText()";

            case Types.JAVA_OBJECT:
                System.out.println("JAVA_OBJECT");
                return colVar.concat("Txt") + ".getText()";

            case Types.LONGNVARCHAR:
                System.out.println("LONGNVARCHAR");
                return colVar.concat("Txt") + ".getText()";

            case Types.LONGVARBINARY:
                System.out.println("LONGVARBINARY");

                return   colVar.concat("Txt") + ".getText()";

            case Types.LONGVARCHAR:
                System.out.println("LONGVARCHAR");
                return colVar.concat("Txt") + ".getText()";

            case Types.NCHAR:
                System.out.println("NCHAR");
                return colVar.concat("Txt") + ".getText()";

            case Types.NCLOB:
                System.out.println("NCLOB");
                return colVar.concat("Txt") + ".getText()";

            case Types.NULL:
                System.out.println("NULL");
                return colVar.concat("Txt") + ".getText()";

            case Types.NUMERIC:
                System.out.println("NUMERIC");
                return  colVar.concat("Txt") + ".getText()";

            case Types.NVARCHAR:
                System.out.println("NVARCHAR");
                return colVar.concat("Txt") + ".getText()";

            case Types.OTHER:
                System.out.println("OTHER");
                return colVar.concat("Txt") + ".getText()";

            case Types.REAL:
                System.out.println("REAL");

                return  colVar.concat("Txt") + ".getText()";

            case Types.REF:
                System.out.println("REF");
                return colVar.concat("Txt") + ".getText()";

            case Types.ROWID:
                System.out.println("ROWID");
                return colVar.concat("Txt") + ".getText()";

            case Types.SMALLINT:
                System.out.println("SMALLINT");
                return colVar.concat("Txt") + ".getText()";

            case Types.SQLXML:
                System.out.println("SQLXML");

            case Types.STRUCT:

                System.out.println("STRUCT");

            case Types.TIME:
            case Types.TIMESTAMP:
            case Types.TIMESTAMP_WITH_TIMEZONE:
                return "new java.sql.Timestamp("+ colVar.concat("Ts") + ".getDate().getTime())";

            case Types.TINYINT:
                return colVar.concat("Txt") + ".getText()";

            case Types.VARBINARY:
                return colVar.concat("Txt") + ".getText()";

            case Types.VARCHAR:
                return colVar.concat("Txt") + ".getText()";
        }
        return null;
    }

    /*    public static String getBeanConstructorWithPri(Table1 table, String tableName, String tableVar, Column1[] column) {
//         String setFun = 
//                String.format( tableName +"Bean "+ tableVar + "Bean=new " + tableName + "Bean(\n");
        String setFun
                = String.format(tableVar + "Bean=new " + tableName + "Bean(\n");

        for (Column1 col : column) {

            String colName = Functions.getJcName(col.getColName());
            String colVar = Functions.getName(col.getColName());
        
        }
    
    }
     */
    public static String parseDataFromVar(String colName, Column1 column) {
        String setFun = "";

        switch (column.getDataType()) {
            case Types.ARRAY:
                System.out.println("ARRAY");
                //setFun += String.format("Integer.parseInt("+colName+")\n");
                
            case Types.BIGINT:
                System.out.println("BIGINT");
                return colName;
                
            case Types.BINARY:
                System.out.println("BINARY");
                return "Integer.parseInt(" + colName + ")";
                
            case Types.BIT:
                System.out.println("BIT");
                return "Integer.parseInt(" + colName + ")";
                
            case Types.BLOB:
                System.out.println("BLOB");
//                return colName + ".getBytes()";
                return "";
            case Types.BOOLEAN:
                System.out.println("BOOLEAN");
                return "Boolean.parseBoolean(" + colName + ")";
                
            case Types.CHAR:
                System.out.println("CHAR");
                return colName + "";

                
            case Types.CLOB:
                System.out.println("CLOB");
                return colName + ".getBytes()";
                
            case Types.DATALINK:
                System.out.println("DATALINK");
                return "Integer.parseInt(" + colName + ")";
                
            case Types.DATE:
                System.out.println("DATE");
                return "new java.sql.Date(" + colName + ".getDate().getTime())";
                
            case Types.DECIMAL:
                System.out.println("DECIMAL");
                return "new BigDecimal(" + colName + ")";
                
            case Types.DISTINCT:
                System.out.println("DISTINCT");
                return "Integer.parseInt(" + colName + ")";
                
            case Types.DOUBLE:
                System.out.println("DOUBLE");

                return "Double.parseDouble(" + colName + ")";
                
            case Types.FLOAT:
                System.out.println("FLOAT");

                return "Float.parseFloat(" + colName + ")";
                
            case Types.INTEGER:
                System.out.println("INTEGER");

                return "Integer.parseInt(" + colName + ")";
                
            case Types.JAVA_OBJECT:
                System.out.println("JAVA_OBJECT");
                return colName;
                
            case Types.LONGNVARCHAR:
                System.out.println("LONGNVARCHAR");
                return colName;

                
            case Types.LONGVARBINARY:
                System.out.println("LONGVARBINARY");

                return "Long.parseLong(" + colName + ")";
                
            case Types.LONGVARCHAR:
                System.out.println("LONGVARCHAR");
                return colName;
                
            case Types.NCHAR:
                System.out.println("NCHAR");
                return colName;

                
            case Types.NCLOB:
                System.out.println("NCLOB");
                return colName + ".getBytes()";
                
            case Types.NULL:
                System.out.println("NULL");
                return colName;
                
            case Types.NUMERIC:
                System.out.println("NUMERIC");
                return "Double.parseDouble(" + colName + ")";
                
            case Types.NVARCHAR:
                System.out.println("NVARCHAR");
                return colName;

                
            case Types.OTHER:
                System.out.println("OTHER");
                return colName;
                
            case Types.REAL:
                System.out.println("REAL");

                return "Double.parseDouble(" + colName + ")";
                
            case Types.REF:
                System.out.println("REF");
                return colName;
                
            case Types.ROWID:
                System.out.println("ROWID");
                return colName;
                
            case Types.SMALLINT:
                System.out.println("SMALLINT");
                return "Integer.parseInt(" + colName + ")";
                
            case Types.SQLXML:
                System.out.println("SQLXML");
              case Types.STRUCT:

                System.out.println("STRUCT");
                
            case Types.TIME:

                System.out.println("TIME");
                return "new java.sql.Timestamp("+ colName + ".getDate()).getDate().getTime())";
                
            case Types.TIMESTAMP:
                System.out.println("TIMESTAMP");

                return "new java.sql.Timestamp("+ colName + ".getDate()).getDate().getTime())";
                
            case Types.TIMESTAMP_WITH_TIMEZONE:

                return "new java.sql.Timestamp("+ colName + ".getDate()).getDate().getTime())";
                
            case Types.TINYINT:
                return "Integer.parseInt(" + colName + ")";
                
            case Types.VARBINARY:
                System.out.println("VARBINARY");

                return "Integer.parseInt(" + colName + ")";
                
            case Types.VARCHAR:
                return colName;

                
            default:

                
        }

        return setFun;
    }

    public static String getType(Column1 column) {
        switch (column.getDataType()) {
            case Types.ARRAY:
                System.out.println("ARRAY");
                //setFun += String.format("Integer.parseInt("+colName+")\n");
                return "Array";
            case Types.BIGINT:
                System.out.println("BIGINT");
                return "String";
            case Types.BINARY:
                System.out.println("BINARY");
                return "Integer";
            case Types.BIT:
                System.out.println("BIT");
                return "Integer";
            case Types.BLOB:
                System.out.println("BLOB");
                return "Blob";
            case Types.BOOLEAN:
                System.out.println("BOOLEAN");
                return "Boolean";
            case Types.CHAR:
                System.out.println("CHAR");
                return "String";
            case Types.CLOB:
                System.out.println("CLOB");
                return "Clob";
            case Types.DATALINK:
                System.out.println("DATALINK");
                return "Datalink";
            case Types.DATE:
                System.out.println("DATE");
                return "Date";
            case Types.DECIMAL:
                System.out.println("DECIMAL");
                return "BigDecimal";

            case Types.DISTINCT:
                System.out.println("DISTINCT");
                return "Distinct";
            case Types.DOUBLE:
                System.out.println("DOUBLE");
                return "Double";
            case Types.FLOAT:
                System.out.println("FLOAT");
                return "Float";
            case Types.INTEGER:
                System.out.println("INTEGER");
                return "Integer";

            case Types.JAVA_OBJECT:
                System.out.println("JAVA_OBJECT");
                return "Object";
            case Types.LONGNVARCHAR:
                System.out.println("LONGNVARCHAR");
                return "String";
            case Types.LONGVARBINARY:
                System.out.println("LONGVARBINARY");
                return "Long";
            case Types.LONGVARCHAR:
                System.out.println("LONGVARCHAR");
                return "String";
            case Types.NCHAR:
                System.out.println("NCHAR");
                return "String";
            case Types.NCLOB:
                System.out.println("NCLOB");
                return "NClob";
            case Types.NULL:
                System.out.println("NULL");
                return "null";
            case Types.NUMERIC:
                System.out.println("NUMERIC");
                return "Double";
            case Types.NVARCHAR:
                System.out.println("NVARCHAR");
                return "String";
            case Types.OTHER:
                System.out.println("OTHER");
                return "Other";
            case Types.REAL:
                System.out.println("REAL");
                return "Double";
            case Types.REF:
                System.out.println("REF");
                return "Ref";
            case Types.ROWID:
                System.out.println("ROWID");
                return "RowId";
            case Types.SMALLINT:
                System.out.println("SMALLINT");
                return "Integer";
            case Types.SQLXML:
                System.out.println("SQLXML");
                return "SQLXML";
            case Types.STRUCT:
                System.out.println("STRUCT");
                return "STRUCT";
            case Types.TIME:
                System.out.println("TIME");
                return "Timestamp";
            case Types.TIMESTAMP:
                System.out.println("TIMESTAMP");
                return "Timestamp";

            case Types.TIMESTAMP_WITH_TIMEZONE:
                return "Timestamp";
            case Types.TINYINT:
                return "Integer";
            case Types.VARBINARY:
                System.out.println("VARBINARY");
                return "Integer";

            case Types.VARCHAR:
                return "String";

            default:

                
        }

        return null;
    }

    public static String setBeanMethod(String tableVar, Column1[] column) {
        String setFun = "";
        for (Column1 col : column) {

            String colName = Functions.getJcName(col.getColName());
            String colVar = Functions.getName(col.getColName());
            switch (col.getDataType()) {
                case Types.ARRAY:
                    System.out.println("ARRAY");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");
                    break;
                case Types.BIGINT:
                    System.out.println("BIGINT");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");
                    break;
                case Types.BINARY:
                    System.out.println("BINARY");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(Integer.parseInt(" + colVar.concat("Txt") + ".getText()));\n");
                    break;
                case Types.BIT:
                    System.out.println("BIT");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(Integer.parseInt(" + colVar.concat("Txt") + ".getText()));\n");
                    break;
                case Types.BLOB:
                    System.out.println("BLOB");
//                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText().getBytes());\n");
                    break;
                case Types.BOOLEAN:
                    System.out.println("BOOLEAN");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(Boolean.parseBoolean(" + colVar.concat("Rd") + ".getText()));\n");
                    break;
                case Types.CHAR:
                    System.out.println("CHAR");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");

                    break;
                case Types.CLOB:
                    System.out.println("CLOB");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText().getBytes());\n");
                    break;
                case Types.DATALINK:
                    System.out.println("DATALINK");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");
                    break;
                case Types.DATE:
                    System.out.println("DATE");

                    setFun += String.format(tableVar + "Bean.set" + colName + "(new java.sql.Date("+colVar.concat("Date") + ".getDate().getTime()));\n");
                    break;
                case Types.DECIMAL:
                    System.out.println("DECIMAL");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(new BigDecimal(" + colVar.concat("Txt") + ".getText()));\n");
                    break;
                case Types.DISTINCT:
                    System.out.println("DISTINCT");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");
                    break;
                case Types.DOUBLE:
                    System.out.println("DOUBLE");

                    setFun += String.format(tableVar + "Bean.set" + colName + "(Double.parseDouble(" + colVar.concat("Txt") + ".getText()));\n");
                    break;
                case Types.FLOAT:
                    System.out.println("FLOAT");

                    setFun += String.format(tableVar + "Bean.set" + colName + "(Float.parseFloat(" + colVar.concat("Txt") + ".getText()));\n");
                    break;
                case Types.INTEGER:
                    System.out.println("INTEGER");

                    setFun += String.format(tableVar + "Bean.set" + colName + "(Integer.parseInt(" + colVar.concat("Txt") + ".getText()));\n");
                    break;
                case Types.JAVA_OBJECT:
                    System.out.println("JAVA_OBJECT");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");

                    break;
                case Types.LONGNVARCHAR:
                    System.out.println("LONGNVARCHAR");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");

                    break;
                case Types.LONGVARBINARY:
                    System.out.println("LONGVARBINARY");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");
                    break;
                case Types.LONGVARCHAR:
                    System.out.println("LONGVARCHAR");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");
                    break;
                case Types.NCHAR:
                    System.out.println("NCHAR");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");

                    break;
                case Types.NCLOB:
                    System.out.println("NCLOB");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");
                    break;
                case Types.NULL:
                    System.out.println("NULL");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");
                    break;
                case Types.NUMERIC:
                    System.out.println("NUMERIC");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(Double.parseDouble(" + colVar.concat("Txt") + ".getText()));\n");
                    break;
                case Types.NVARCHAR:
                    System.out.println("NVARCHAR");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");

                    break;
                case Types.OTHER:
                    System.out.println("OTHER");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");
                    break;
                case Types.REAL:
                    System.out.println("REAL");

                    setFun += String.format(tableVar + "Bean.set" + colName + "(Double.parseDouble(" + colVar.concat("Txt") + ".getText()));\n");
                    break;
                case Types.REF:
                    System.out.println("REF");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");
                    break;
                case Types.ROWID:
                    System.out.println("ROWID");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");
                    break;
                case Types.SMALLINT:
                    System.out.println("SMALLINT");

                    setFun += String.format(tableVar + "Bean.set" + colName + "(Integer.parseInt(" + colVar.concat("Txt") + ".getText()));\n");
                    break;
                case Types.SQLXML:
                    System.out.println("SQLXML");

                    break;

                case Types.STRUCT:

                    System.out.println("STRUCT");
                    break;
                case Types.TIME:

                    System.out.println("TIME");

                    setFun += String.format(tableVar + "Bean.set" + colName + "(new java.sql.Timestamp("+ colVar.concat("Ts") + ".getDate().getTime()));\n");
                    break;
                case Types.TIMESTAMP:
                    System.out.println("TIMESTAMP");
                    setFun += String.format(tableVar + "Bean.set" + colName + "(new java.sql.Timestamp("+ colVar.concat("Ts") + ".getDate().getTime()));\n");
                    break;
                case Types.TIMESTAMP_WITH_TIMEZONE:

                    setFun += String.format(tableVar + "Bean.set" + colName + "(new java.sql.Timestamp("+ colVar.concat("Ts") + ".getDate().getTime()));\n");
                    break;
                case Types.TINYINT:

                    setFun += String.format(tableVar + "Bean.set" + colName + "(Integer.parseInt(" + colVar.concat("Txt") + ".getText()));\n");
                    break;
                case Types.VARBINARY:
                    System.out.println("VARBINARY");

                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");
                    break;
                case Types.VARCHAR:
                    setFun += String.format(tableVar + "Bean.set" + colName + "(" + colVar.concat("Txt") + ".getText());\n");

                    break;
                default:

                    break;
            }
        }
        return setFun;
    }

    public static String getSetFunction(String tableName, Column1[] col1) {
        Integer i = 1;
        StringBuilder setFunction = new StringBuilder();
        String commaColumns = new String("(");
        //    tableName + ".get" + colName + "());\n";    
        for (Column1 column : col1) {
            String colName = Functions.getJcName(column.getColName());
            setFunction.append(String.format(getSet(column, i++, tableName + ".get" + colName + "()") + ";\n"));
            commaColumns += "," + column.getColName();
        }

        commaColumns = commaColumns.replaceFirst(",", "");//,"");
        commaColumns += ") VALUES ";

        String c = "";
        for (Integer j = 1; j < i; j++) {
            c += ",?";
        }
        c = c.replaceFirst(",", "(");
        commaColumns += c.concat(")");

        System.out.println("prepareStatement \n" + commaColumns + " \n setFunction:\n " + setFunction);
        return commaColumns + "_-_" + setFunction.toString();
    }

    public static String getSet(Column1 column, Integer i, String value) {
        StringBuilder setFunction = new StringBuilder();
        switch (column.getDataType()) {
            case Types.ARRAY:
                System.out.println("ARRAY");
                // commaColumns.append(String.format("set ;\n"));
                break;
            case Types.BIGINT:
                System.out.println("BIGINT");
                return "pre.setString(" + i + "," + value + ")";

            case Types.BINARY:
                System.out.println("BINARY");
                return "pre.setInt(" + i + "," + value + ")";

            case Types.BIT:
                System.out.println("BIT");
                return "pre.setInt(" + i + "," + value + ")";

            case Types.BLOB:
                System.out.println("BLOB");
//                return "pre.setBLOB(" + i + "," + value + ")";
                return "";
            case Types.BOOLEAN:
                System.out.println("BOOLEAN");
                return "pre.setBoolean(" + i + "," + value + ")";

            case Types.CHAR:
                System.out.println("CHAR");
                return "pre.setString(" + i + "," + value + ")";

            case Types.CLOB:
                System.out.println("CLOB");
                return "pre.setClob(" + i + "," + value + ")";

            case Types.DATALINK:
                System.out.println("DATALINK");
                return "pre.setDataLink(" + i + "," + value + ")";

            case Types.DATE:
                System.out.println("DATE");
                return "pre.setDate(" + i + "," + value + ")";

            case Types.DECIMAL:
                System.out.println("DECIMAL");
                return "pre.setBigDecimal(" + i + "," + value + ")";

            case Types.DISTINCT:
                System.out.println("DISTINCT");
                return "pre.setDistinct(" + i + "," + value + ")";

            case Types.DOUBLE:
                System.out.println("DOUBLE");
                return "pre.setDouble(" + i + "," + value + ")";

            case Types.FLOAT:
                System.out.println("FLOAT");
                return "pre.setFloat(" + i + "," + value + ")";

            case Types.INTEGER:
                System.out.println("INTEGER");
                return "pre.setInt(" + i + "," + value + ")";
            case Types.JAVA_OBJECT:
                System.out.println("JAVA_OBJECT");
                return "pre.setObject(" + i + "," + value + ")";

            case Types.LONGNVARCHAR:
                System.out.println("LONGNVARCHAR");
                return "pre.setString(" + i + "," + value + ")";

            case Types.LONGVARBINARY:
                System.out.println("LONGVARBINARY");
//                return "pre.setBlob(" + i + "," + value + ")";
                return "";
            case Types.LONGVARCHAR:
                System.out.println("LONGVARCHAR");
                return "pre.setString(" + i + "," + value + ")";

            case Types.NCHAR:
                System.out.println("NCHAR");
                return "pre.setInt(" + i + "," + value + ")";

            case Types.NCLOB:
                System.out.println("NCLOB");
                return "pre.setInt(" + i + "," + value + ")";

            case Types.NULL:
                System.out.println("NULL");
                return "pre.setInt(" + i + "," + value + ")";

            case Types.NUMERIC:
                System.out.println("NUMERIC");
                return "pre.setInt(" + i + "," + value + ")";

            case Types.NVARCHAR:
                System.out.println("NVARCHAR");
                return "pre.setNString(" + i + "," + value + ")";

            case Types.OTHER:
                System.out.println("OTHER");
                return "pre.setInt(" + i + "," + value + ")";

            case Types.REAL:
                System.out.println("REAL");

                return "pre.setDouble(" + i + "," + value + ")";

            case Types.REF:
                System.out.println("REF");
                return "pre.setRef(" + i + "," + value + ")";

            case Types.ROWID:
                System.out.println("ROWID");

                return "pre.setInt(" + i + "," + value + ")";

            case Types.SMALLINT:
                System.out.println("SMALLINT");
                return "pre.setInt(" + i + "," + value + ")";

            case Types.SQLXML:
                System.out.println("SQLXML");
                return "pre.setXML(" + i + "," + value + ")";

            case Types.STRUCT:

                System.out.println("STRUCT");
                return "pre.setStruct(" + i + "," + value + ")";

            case Types.TIME:

                System.out.println("TIME");
                return "pre.setTimestamp(" + i + "," + value + ")";

            case Types.TIMESTAMP:
                System.out.println("TIMESTAMP");
                return "pre.setTimestamp(" + i + "," + value + ")";

            case Types.TIMESTAMP_WITH_TIMEZONE:
                System.out.println("TIMESTAMP_WITH_TIMEZONE");
                return "pre.setTimestamp(" + i + "," + value + ")";

            case Types.TIME_WITH_TIMEZONE:
                return "pre.setTimestamp(" + i + "," + value + ")";

            case Types.TINYINT:
                return "pre.setInt(" + i + "," + value + ")";

            case Types.VARBINARY:
                System.out.println("VARBINARY");
                return "pre.setInt(" + i + "," + value + ")";

            case Types.VARCHAR:
                System.out.println("VARCHAR");
                //pre.setInt(getPosition(colName, values, i),  (int)values[i]);
                return "pre.setString(" + i + "," + value + ")";

            default:

        }

        return null;
    }
}
