/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import components.ComponentMdf;
import database.Database;
import database.table.Column1;
import database.table.Table1;
import fr.AppInfo;
import fr.FrameInfo;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.File1;
import main.Functions;

/**
 *
 * @author tahir hussain
 */
public class AddFunctions {

    public static void addFunction(AppInfo newAppInfo,Database db) {
        String newProjPath=newAppInfo.getPath();
        Table1[] t = db.getTables();
        String projectName = Functions.getJcName(db.getDbName());
 
        
int i=0;
int length=t.length;
List<FrameInfo> list=newAppInfo.getFrameList();
int index=0;
for (Table1 tab : t) {
            String tableName = Functions.getJcName(tab.getTableName());
            String tableVar = Functions.getName(tab.getTableName());
            
            StringBuffer sb = new StringBuffer(1024);
//C:\Users\tahir hussain\JDBsProject\Crudsoftware\src\com\frame
//get insert function
if(list.get(index).getInsertBtn())
            sb.append(insertFunctions(db.getDbName(),tab, tableName, tableVar));
if(list.get(index).getDeleteBtn())
            sb.append(getDeleteFunction(db.getDbName(),tab, tableName, tableVar, "delete"));
if(list.get(index).getUpdateBtn())
            sb.append(getUpdateFunction(db.getDbName(),tab, tableName, tableVar, "update"));
if(list.get(index).getViewBtn())
            sb.append(getViewFun(list.get(index),tab, tableName, tableVar, "view" + tableName));
if(list.get(index).getNextBtn() && list.get(index).getBackBtn())
            sb.append(getBN(tab,t[i==0?length-1:i-1],t[ i==(length-1)?0:i+1]));
else if(list.get(index).getNextBtn())
            sb.append(getN(tab,t[i==(length-1)?0:i+1]));
else if(list.get(index).getBackBtn())
            sb.append(getB(tab,t[i==0?length-1:i-1]));
sb.append(getFillTableFunction(tab,tableName,tableVar,"fillTable"));
sb.append(getFillFieldFunction("fillFieldsFromTable"));

            File1.writeWithReplace(
                    //\Sakila\src/com/frame/ActorFrame.java
                    new File(newProjPath+"/"+projectName+"/src/com/frame/" + tableName + "Frame.java"),
                    "//.",
                    sb.toString());
index++;
i++;        
}
    }

    public static StringBuilder insertFunctions(String dbName,Table1 table, String tableName, String tableVar) {

        StringBuilder funContent = new StringBuilder();
        
        String colName=null;
        if(table.getPrimaryColumn()==null){
            colName=Functions.getName(table.getExceptPriColName());
        }
        else
            colName=table.getPrimaryColName();
        
        
        //String dbName,String tableVar,String priColName,String[] colName,String[] values
        
        funContent.append(String.format(
                "public void add" + tableName + "(){ \n int update=-1;\n"
                        + "setValues();\n"
                + "try{"
                        + "if(CheckComponent.isAllFilled(obj,heading)){\n"
                +   ComponentMdf.getBeanConstructor(table, tableName, tableVar)+";\n"
                + "\n"
                + " update=" +tableVar + "DaoImpl.insert" + tableName + "(" + tableVar + "Bean);\n"
                + "}"
                                + "if(update!=1){\n"
                                + "update=Query.insert(\""+dbName+"\",\""+ table.getTableName()+"\",colName,values,heading);\n"
                                + "}"
                        + "if(update!=1){\n"
                        + ComponentMdf.getBeanConstructorWithAuto(table, tableName, tableVar)+ ";\n"
                        + " update=" +tableVar + "DaoImpl.insert" + tableName + "(" + tableVar + "Bean);\n"
                                + "}"
                                + "if(update==1)\n"
                                + "\nfillTable("+ tableVar+"DaoImpl.get"+tableName+"AllRecords());\n"
                                        + "}catch(NullPointerException ex){\n ex.printStackTrace();\n}\n"
                                 + "catch(NumberFormatException ex){\n ex.printStackTrace();\n}\n"
                                + "\n}\n"
        ));

        return funContent;
    }

    public static StringBuilder getUpdateFunction(String dbName,Table1 table, String tableName, String tableVar, String operation) {
        StringBuilder funContent = new StringBuilder();
        String colName=null;
        if(table.getPrimaryColumn()==null){
            colName=Functions.getName(table.getExceptPriColName());
        }
        else
            colName=table.getPrimaryColName();
        funContent.append(String.format(
                
                 "public void " + operation + tableName + "(){int update=-1;\n"
                         + "setValues();\n"
                + " try{"
                         + "if(CheckComponent.isAllFilled(obj,heading)){\n"
                + tableVar + "Bean = new " + tableName + "Bean( );\n"
                + ComponentMdf.setBeanMethod(tableVar, table.getColumn()) + "\n"
                + "update="+tableVar + "DaoImpl." + operation + tableName + "(" + tableVar + "Bean);\n"
                        + "\n"
                + "} if(update!=1){\n"
                + "update=Query.update(\""+dbName+"\",\""+ table.getTableName()+"\",\""+colName+"\",colName,values,heading);\n"
                + "} if(update==1)\n"
                        + "fillTable("+ tableVar+"DaoImpl.get"+tableName+"AllRecords());\n"
                          + "}catch(NullPointerException ex){\n ex.printStackTrace();\n}\n"
                                 + "catch(NumberFormatException ex){\n ex.printStackTrace();\n}\n"
                                + "}\n"
                        + "\n"
        ));
        return funContent;
    }

    public static String getViewFun(FrameInfo frame,Table1 table, String tableName, String tableVar, String funName) {
        StringBuilder funContent = new StringBuilder();
        //here is if function which will use the code
        //funContent.append();
        String search="";
                if(frame.getSearchTxt()){
                       search= "else if(searchTxt!=null && searchTxt.getText().equals(\"\") "
                        + " && searchTxt.getText().equals(\"Search "+tableName+"\")){\n"
                + "TableMdf.tableSort(table,searchTxt);\n"
                + "}else";
                                }
                
        funContent.append(String.format(
                " public void " + funName + "(){\n"
                        + "setValues();\n"
                + "if(CheckComponent.isAllFilled(obj,heading)){\n"
                + tableVar + "Bean = new " + tableName + "Bean();\n"
                + ComponentMdf.setBeanMethod(tableVar, table.getColumn()) + "\n"
                //+ tableVar + "DaoImpl." + operation + tableName + "(" + tableVar + "Bean);\n}\n"
                + "}"
                +search
                + ""
                + "{"
                + "\nfillTable("+ tableVar+"DaoImpl.get"+tableName+"AllRecords());\n"
                + "\n}"
                + "}\n"
        ));
        return funContent.toString();
    }

    private static String getDeleteFunction(String dbName,Table1 table, String tableName, String tableVar, String delete) {
        StringBuilder funContent = new StringBuilder();
        //here is if function which will use the code
        //funContent.append();
        Column1 column = table.getPrimaryColumn() != null ? table.getPrimaryColumn() : table.getExceptPriCol();
        String colName="";

        
        if(table.getPrimaryColumn()==null){
            colName=table.getExceptPriColName();
        }else
            colName=table.getPrimaryColName();
        
        
                
        funContent.append(String.format(
                " public void delete" + tableName + "(){\n int update=-1;\n"
                        + "setValues();\n"
                + "try{"
                        + "if(CheckComponent.isAllFilled(obj,heading)){\n"
                + tableVar + "Bean = new " + tableName + "Bean();\n"
                + ComponentMdf.setBeanMethod(tableVar, table.getColumn()) + "\n"
                //+ tableVar + "DaoImpl." + operation + tableName + "(" + tableVar + "Bean);\n}\n"
                + ""
                 //+ "}else if(searchTxt"+".getText()!=null && "+searchTxt+".getText().equals(\"\"))"
                        //+ " && "searchTxt".getText().equals(\""+Search "\")){\n"
                            + "update= "+tableVar+"DaoImpl.delete"+tableName+"("+tableVar+"Bean);\n"
                        + "}if (update!=1) {"
                 + "update=Query.delete(\""+dbName+"\",\""+ table.getTableName()+"\",colName,values,heading);\n"
                       // + "}if(update!=0 && "+ ComponentMdf.getValueWithoutParse(column,Functions.getName(column.getColName()))+"!=null ){"
                            +"}if(update!=1){\n"
                         + "update="+ tableVar + "DaoImpl.delete" + tableName + "("
                        + ComponentMdf.parseDataFromField(column, Functions.getName(column.getColName()))+");\n"
                + "}\n"
                        + "//update=Query.executeUpdate();\n"
                        + "if(update==1)"
                         + "\nfillTable("+ tableVar+"DaoImpl.get"+tableName+"AllRecords());\n"
                                 + "}catch(NullPointerException ex){\n ex.printStackTrace();\n}\n"
                                 + "catch(NumberFormatException ex){\n ex.printStackTrace();\n}\n"
                + "}\n"
        ));
        return funContent.toString();
    }

    private static String getBN(Table1 t, Table1 pre,Table1 next ) {
            String nextName=Functions.getJcName(next.getTableName());
            String backName=Functions.getJcName(pre.getTableName());
//            String currentName=Functions.getJcName(t.getTableName());
           
        return  String.format("public void next(){\n"
                  +"new "+nextName+"Frame().setVisible(true);\n"
                + "this.setVisible(false);\n}\n"
                + "public void back(){\n"
                + "new "+backName+"Frame().setVisible(true);\n"
                + "this.setVisible(false);\n"
                + "}");
        
    }
    
    private static String getN(Table1 t, Table1 next  ) {
            String nextName=Functions.getJcName(next.getTableName());
    
return  String.format("public void next(){\n"
                  +"new "+nextName+"Frame().setVisible(true);\n"
                + "this.setVisible(false);\n}\n");
        
    }
    
    private static String getB(Table1 t, Table1 pre) {
            String backName=Functions.getJcName(pre.getTableName());
    
return  String.format("public void back(){\n"
                + "new "+backName+"Frame().setVisible(true);\n"
                + "this.setVisible(false);\n"
                + "}");
        
    }
    
    private static String getFillTableFunction(Table1 table,String tableName,String tableVar,String functionName){
        
        return String.format(""
                + "public void "+functionName+"(ResultSet resultSet){"
                + "table.setModel(TableMdf.getDefaultTableModel("+tableVar+"DaoImpl.get"+tableName+"AllRecords()));\n"
                + "}");
    
    
    }

    private static String getFillFieldFunction(String funName) {
        
        return String.format("\n" +
"    public void "+ funName+"(){\n" +
"\n" +
"      int row = table.getSelectedRow();\n" +
"        int column = table.getColumnCount();\n" +
"        Object[] a = new Object[column];\n" +
"        for (int i = 0; i < column; i++) {\n" +
"            a[i] = table.getValueAt(row,i);\n" +
"           CheckComponent.fillValueToField(obj[i], a[i]);}\n"
+"}//.\n");
    
    
    
    }
}
