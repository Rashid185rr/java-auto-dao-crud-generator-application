/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import components.ComponentMdf;
import database.Database;
import database.table.Column1;
import database.table.Table1;
import fr.AppInfo;
import fr.FrameInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger; 

/**
 *
 * @author tahir hussain
 */
public class DaoPattern {
 

    private static String getAutoValue(String filePath, Table1 table, Database db) {
        String auto = new String();
        //single auto increment 
        auto += String.format(
                "public String getAutoIncrement(){\n"
                + "String autoValue=\"\";\n"
                + getTry(db, table, null)).replace("QUERYHERE", "\"SELECT " + table.getAutoIncrementCol().getColName() + " from " + table.getTableName() + ""
                + " order by " + table.getAutoIncrementCol().getColName() + " desc limit 1" + "\"");

        auto = auto.replace("SETFUNCTION", "ResultSet rs=pre.executeQuery();\n"
                + ""
                + "while(rs.next())\n"
                + "autoValue=rs.getString(\"" + table.getPrimaryColName() + "\");\n"
        );
        auto = auto.replace("RETURNVALUE", "return autoValue; ");
        return auto;
    }

    public DaoPattern(Database db) {

    }

   public static void createDaoPattern(AppInfo thisAppInfo,AppInfo newAppInfo,Database db) {
        //C:/Users/tahir hussain/Documents/
        String thisProPath=thisAppInfo.getPath();
        String newProPath=newAppInfo.getPath();
        
        String projectName = Functions.getJcName(db.getDbName());
        Table1[] tables = db.getTables();
        newProPath += "/"+projectName+"/src/com/";
        System.out.println("newProPath:"+newProPath);
        createDirectories(newProPath);
        createDaoDirectories(newProPath);
        createConnectionFile(thisProPath,newProPath,db);
        List<FrameInfo> frameList=newAppInfo.getFrameList();
        int ind=0;
        for (Table1 table : tables) {
            createBeanFile(newProPath.concat("beans/"), table);
            createDaoFile(newProPath.concat("dao/"), table);
            createDaoImpFile(newProPath.concat("daoimpl/"), table, db);
            
            System.out.println("thisProPath_-_"+thisProPath);
            createUtilityFiles(thisProPath,newProPath,db);
            createFrameFile(thisProPath,newProPath,frameList.get(ind++),db, table, newProPath+"frame/");
        
        }

    }

    public static void createBeanFile(String path, Table1 table) {
      System.out.println("createBeanFile:"+path);
        File beanFile = createFile(path + Functions.getJcName(table.getTableName().concat("Bean.java")));
        String tableName = Functions.getJcName(table.getTableName());
        FileWriter writer = null;

        try {
            writer = new FileWriter(beanFile);
            writer.write(getContent(table).toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static StringBuilder getContent(Table1 table) {

        String tableName = Functions.getJcName(table.getTableName());

        StringBuilder imp = new StringBuilder(String.format("\tpackage com.beans;\n\n"));

        StringBuilder constructor = new StringBuilder(String.format("public " + tableName + "Bean(){\n}"));

        constructor.append(String.format("public " + tableName + "Bean("));

        StringBuilder var = new StringBuilder(String.format("public class " + tableName + "Bean{\n\n"));
        StringBuilder getSet = new StringBuilder();

        StringBuilder thisVar = new StringBuilder();
        for (Column1 col : table.getColumn()) {
            String colName = col.getColName();
            String col1 = Functions.getJcName(col.getColName());
            thisVar.append(String.format("this." + colName + "=" + colName + ";\n"));
            switch (col.getDataType()) {
                case Types.ARRAY:
                    System.out.println("ARRAY");

                    if (imp.indexOf("Array") == -1) {
                        imp.append("import java.util.Array;");
                    }
                    constructor.append(String.format("Array " + colName + ","));
                    var.append(String.format("\tprivate Array " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "Array"));

                    break;
                case Types.BIGINT:
                    System.out.println("BIGINT");
//                    if (imp.indexOf("BigInteger") == -1) {
//                        imp.append("import java.math.BigInteger;\n");
//                    }
//                    
                    constructor.append(String.format("String " + colName + ","));
                    var.append(String.format("\tprivate String " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "String"));
                    break;

                case Types.BINARY:
                    System.out.println("BINARY");

                    constructor.append(String.format("Integer " + colName + ","));
                    var.append(String.format("\tprivate Integer " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "int"));
                    break;
                case Types.BIT:
                    System.out.println("BIT");
                    constructor.append(String.format("Integer " + colName + ","));
                    var.append(String.format("\tprivate Integer " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "int"));
                    break;
                case Types.BLOB:
                    System.out.println("BLOB");
                  /*  if (imp.indexOf("Blob") == -1) {
                        System.out.println("appending blob");
                        imp.append(String.format("import java.sql.Blob;\n"));
                    }
                    constructor.append(String.format("Blob " + colName + ","));
                    var.append(String.format("\tprivate Blob " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "Blob"));
                    */
                    break;
                case Types.BOOLEAN:
                    System.out.println("BOOLEAN");
                    constructor.append(String.format("boolean " + colName + ","));
                    var.append(String.format("private boolean " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "boolean"));
                    break;
                case Types.CHAR:
                    System.out.println("CHAR");
                    constructor.append(String.format("String " + colName + ","));
                    var.append(String.format("\tprivate String " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "String"));
                    break;
                case Types.CLOB:
                    System.out.println("CLOB");
                    /*
                    if (imp.indexOf("Clob") == -1) {
                        imp.append(String.format("import java.sql.Clob;\n"));
                    }
                    constructor.append(String.format("Clob " + colName + ","));
                    var.append(String.format("\tprivate Clob " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "Clob"));
*/
                    break;
                case Types.DATALINK:
                    System.out.println("DATALINK");
                    constructor.append(String.format("String " + colName + ","));
                    var.append(String.format("\tprivate String " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "String"));
                    break;
                case Types.DATE:
                    System.out.println("DATE");

                    //String.format("Date "+colName+"\n");
                    if (imp.indexOf("Date") == -1) {
                        imp.append(String.format("import java.sql.Date;\n"));
                    }

                    constructor.append(String.format("Date " + colName + ","));

                    var.append(String.format("private Date " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "Date"));

                    break;
                case Types.DECIMAL:
                    System.out.println("DECIMAL");
                    if (imp.indexOf("BigDecimal") == -1) {
                        imp.append(String.format("import java.math.BigDecimal;\n"));
                    }

                    String.format("Double " + colName + "\n");
                    constructor.append(String.format("BigDecimal " + colName + ","));

                    var.append(String.format("private BigDecimal " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "BigDecimal"));
                    break;
                case Types.DISTINCT:
                    System.out.println("DISTINCT");
                    constructor.append(String.format("String " + colName + ","));
                    var.append(String.format("\tprivate String " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "String"));

                    break;
                case Types.DOUBLE:
                    System.out.println("DOUBLE");
                    constructor.append(String.format("Double " + colName + ","));
                    var.append(String.format("private Double " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "double"));
                    break;
                case Types.FLOAT:
                    System.out.println("FLOAT");
                    constructor.append(String.format("float " + colName + ","));

                    var.append(String.format("private float  " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "float"));
                    break;
                case Types.INTEGER:
                    System.out.println("INTEGER");
                    constructor.append(String.format("Integer " + colName + ","));
                    var.append(String.format("private Integer " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "int"));

                    break;
                case Types.JAVA_OBJECT:
                    System.out.println("JAVA_OBJECT");
                    constructor.append(String.format("Object " + colName + ","));
                    var.append(String.format("private Object " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "Object"));
                    break;
                case Types.LONGNVARCHAR:
                    System.out.println("LONGNVARCHAR");
                    constructor.append(String.format("String " + colName + ","));

                    var.append(String.format("private String " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "String"));
                    break;
                case Types.LONGVARBINARY:
                    System.out.println("LONGVARBINARY");
                   /*
                    if (imp.indexOf("Blob") == -1) {
                        System.out.println("appending blob");
                        imp.append(String.format("import java.sql.Blob;\n"));
                    }
                    
                    constructor.append(String.format("Blob " + colName + ","));

                    var.append(String.format("private Blob " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "Blob"));
                    */
                    
                    break;
                case Types.LONGVARCHAR:
                    System.out.println("LONGVARCHAR");
                    constructor.append(String.format("String " + colName + ","));
                    var.append(String.format("private String " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "String"));
                    break;
                case Types.NCHAR:
                    System.out.println("NCHAR");
                    constructor.append(String.format("String " + colName + ","));
                    var.append(String.format("private String " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "String"));
                    break;
                case Types.NCLOB:
                    System.out.println("NCLOB");
                    constructor.append(String.format("NClob " + colName + ","));
                    var.append(String.format("\tprivate NClob " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "NClob"));
                    break;
                case Types.NULL:
                    System.out.println("NULL");
                    constructor.append(String.format("String " + colName + ","));
                    var.append(String.format("\tprivate String " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "String"));
                    break;
                case Types.NUMERIC:
                    System.out.println("NUMERIC");
                    constructor.append(String.format("Double " + colName + ","));

                    var.append(String.format("private Double " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "double"));

                    break;
                case Types.NVARCHAR:
                    System.out.println("NVARCHAR");
                    constructor.append(String.format("String " + colName + ","));
                    var.append(String.format("private String " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "String"));
                    break;
                case Types.OTHER:
                    System.out.println("OTHER");
                    constructor.append(String.format("String " + colName + ","));
                    var.append(String.format("\tprivate String " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "String"));
                    break;
                case Types.REAL:
                    System.out.println("REAL");

                    constructor.append(String.format("Double " + colName + ","));
                    var.append(String.format("private Double " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "double"));

                    break;
                case Types.REF:
                    System.out.println("REF");
                    constructor.append(String.format("String " + colName + ","));
                    var.append(String.format("\tprivate String " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "String"));
                    break;
                case Types.ROWID:
                    System.out.println("ROWID");
                    constructor.append(String.format("String " + colName + ","));
                    var.append(String.format("\tprivate String " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "String"));
                    break;
                case Types.SMALLINT:
                    System.out.println("SMALLINT");
                    //String.format("Integer "+colName+"\n");
                    constructor.append(String.format("Integer " + colName + ","));
                    var.append(String.format("private Integer " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "int"));
                    break;
                case Types.SQLXML:
                    System.out.println("SQLXML");

                    constructor.append(String.format("SQLXML " + colName + ","));
                    var.append(String.format("\tprivate SQLXML " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "SQLXML"));

                    break;

                case Types.STRUCT:

                    System.out.println("STRUCT");
                    constructor.append(String.format("STRUCT " + colName + ","));
                    var.append(String.format("\tprivate STRUCT " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "STRUCT"));
                    break;
                case Types.TIME:

                    System.out.println("TIME");
                    //String.format("Timestamp "+colName+"\n");

                    if (imp.indexOf("Timestamp") == -1) {
                        imp.append(String.format("import java.sql.Timestamp;\n"));
                    }
                    constructor.append(String.format("Timestamp " + colName + ","));

                    var.append(String.format("private Timestamp " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "Timestamp"));
                    break;
                case Types.TIMESTAMP:

                    System.out.println("TIMESTAMP");

                    if (imp.indexOf("Timestamp") == -1) {
                        imp.append(String.format("import java.sql.Timestamp;\n"));
                    }
                    constructor.append(String.format("Timestamp " + colName + ","));

                    var.append(String.format("private Timestamp " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "Timestamp"));

                    break;
                case Types.TIMESTAMP_WITH_TIMEZONE:
                    constructor.append(String.format("Timestamp " + colName + ","));

                    var.append(String.format("private Timestamp " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "Timestamp"));
                    break;
                case Types.TINYINT:

                    constructor.append(String.format("Integer " + colName + ","));
                    var.append(String.format("private Integer " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "int"));

                    break;
                case Types.VARBINARY:
                    System.out.println("VARBINARY");

                    constructor.append(String.format("Integer " + colName + ","));
                    var.append(String.format("private Integer " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "String"));
                    break;
                case Types.VARCHAR:
                    constructor.append(String.format("String " + colName + ","));
                    //String.format("String "+colName+"\n");
                    var.append(String.format("private String " + colName + ";\n"));
                    getSet.append(getSG(col1, colName, "String"));

                    break;
                default:

                    break;

            }

        }
        constructor.deleteCharAt(constructor.length() - 1).append(String.format("){\n"));
        constructor.append(thisVar.append(String.format("}\n")));

        //constructor.append(String.format( (constructor.toString().split(",")).concat("}\n")));
        return imp.append(String.format(var.toString().concat("\n\n") + constructor.toString())).append(getSet.toString().concat("\n\n}"));
    }

    public static String getSG(String col1, String colName, String colType) {
        String getSet = String.format("\n public void set" + col1 + "(" + colType + " " + colName + "){\nthis." + colName
                + "=" + colName) + ";\n}\n"
                + String.format("\n public " + colType + " get" + col1 + "(){\n return this." + colName + ";\n\n}");
        return getSet;
    }

    public static File createFile(String path) {
System.out.println("---> path:"+path);
        File file = new File(path);
        try {

            if (file.isDirectory()) {
                file.mkdirs();
            }

            if (!file.exists() && !file.isDirectory()) {
                file.createNewFile();
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return file;
    }

    public static void createDirectories(String path) {
        File1.createDirectories(path);
    }

    public static void createDirectory(String path, Table1 table) {
        File1.createDirectory(path + Functions.getName(table.getTableName()));
    }

    public static void createDaoDirectories(String path) {
        createDir(path, "beans");
        createDir(path, "dao");
        createDir(path, "daoimpl");
        createDir(path, "utility");
        createDir(path, "database");

    }

    public static void createDir(String path, String dirName) {
        File file = new File(path + dirName);
        if (!file.exists()) {
            file.mkdirs();
        }

    }

    public static void createDaoFile(String dao, Table1 table) {

        StringBuilder imp = new StringBuilder(String.format("package com.dao;\n"));
        StringBuilder fun = new StringBuilder();
        String tableName = Functions.getJcName(table.getTableName());
        String tb = Functions.getName(table.getTableName());

        imp.append(String.format("import com.beans." + tableName + "Bean;\n"));
        imp.append(String.format("import java.sql.ResultSet;\n"));
        imp.append(String.format("import java.util.List;\n"));
        imp.append(String.format("public interface " + tableName + "Dao{\n"));

//        fun.append(String.format("ResultSet get" + tableName + "AllRecords(" + tableName + "Bean " + tb + ");\n"));
        fun.append(String.format("ResultSet get" + tableName + "AllRecords( );\n"));
        Column1 co = table.getPrimaryColumn();
         
        if(co==null && table.getExceptPriCol()!=null)
            co = table.getExceptPriCol();
        

//            fun.append(String.format(tableName+" get"+tableName+"AllRecordById( "+table.getTableName()+");\n"));
        fun.append(String.format("Integer delete"+tableName+"("+tableName+"Bean "+tb+");\n"));
        fun.append(String.format("Integer delete" + tableName + "(" + ComponentMdf.getType(co) + " " + co.getColName() + ");\n"));
        fun.append(String.format("Integer insert" + tableName + "(" + tableName + "Bean " + tb + ");\n"));
        fun.append(String.format("Integer update" + tableName + "(" + tableName + "Bean " + tb + ");\n"));
        fun.append(String.format("List<" + tableName + "Bean> get" + tableName + "List();\n"));

        File file = createFile(dao + tableName.concat("Dao.java"));

        FileWriter writer = null;

        try {

            writer = new FileWriter(file);
            writer.write(String.format(imp.append(String.format("\n\n " + fun.toString())).toString() + "\n"));

            writer.write("}");
            writer.close();
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void createDaoImpFile(String filePath, Table1 table, Database db) {

        String tableName = Functions.getJcName(table.getTableName());
        File file = createFile(filePath + tableName.concat("DaoImpl.java"));

        FileWriter fw = null;
        try {

            fw = new FileWriter(file);
            fw.write(String.format(getImportFromDao(filePath, tableName)));

            StringBuilder b = new StringBuilder(getImplementedFuncion(filePath, table, db));
            fw.write(b.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getImportFromDao(String filePath, String tableName) {
        String imported = new String();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath.replace("impl/", "/" + tableName.concat("Dao.java"))));
            String line = "";
            Stack<Character> stack = new Stack<>();
            while ((line = br.readLine()) != null) {

                if (line.contains("{")) {
                    stack.push('{');
                }
                if (line.contains("}")) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    imported += String.format(line + "\n");
                }

            }
            imported = imported.replace("}", "");
            imported = imported.replaceFirst("dao", "daoimpl");//.replaceFirst("Dao","DaoImpl");
            /*          imported = imported.concat(String.format(
                    "import com.dao." + tableName + "Dao;\n "
                    + "import java.sql.PreparedStatement;\n "
                    + "import com.database.connect.ConnectDB;\n"
                    + "import com.database.connect.DB;\n"
                    + "import java.sql.Connection;\n"
                    + "import com.utility.CheckComponent;\n"
                    + "import java.sql.Types;\n"
                    + "import java.util.ArrayList;"
                    + "import java.sql.Timestamp"
                    + "import java."
            
            ));
             */
            imported = imported.concat(String.format(
                    "import com.dao." + tableName + "Dao;\n "
                    + "import com.database.connect.ConnectDB;\n"
                    + "import com.database.connect.DB;\n"
                    + "import java.sql.*;\n"
                    + "import com.utility.CheckComponent;\n"
                    + "import java.util.*;\n"
                            + "import javax.swing.JOptionPane;\n"
            ));

//            
//            
//            Pattern p = Pattern.compile(imported.toString());
//            Matcher matcher = p.matcher("import+[ ]+[a-zA-Z0-9];");
//            while (matcher.find()) {
//                imported += matcher.group();
//            }
//            
            imported = imported.concat(String.format("import com.beans." + tableName + "Bean;\n import java.sql.SQLException; \n"));
            //check all class name and find that if class name exists 
            //in the impl class then write import above the class keyword and below package
            //List<String> fileNames=checkToImportClass(filePath,tableName);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return imported.toString();
    }

    public static List<String> checkToImportClass(String filePath, String tableName) {

        //list all file then find if name exists
        List<String> fileName = new ArrayList<>();
        File1.getFileNameFrom(new File(filePath.substring(0, filePath.indexOf("com"))), new ArrayList<>());

        return fileName;
    }

    public static String getImplementedFuncion(String filePath, Table1 table, Database db) {
        StringBuilder tableName = new StringBuilder(Functions.getJcName(table.getTableName()));
        String tab=tableName.toString()+"Dao.java";
        StringBuilder function = new StringBuilder();
       
        Column1 co=null;
        if(table.getPrimaryColumn()==null){
            co=table.getExceptPriCol();
        }
        else
            co=table.getPrimaryColumn();
        
        String tableVar=Functions.getName(table.getTableName());
        
        try {
            File file = new File(filePath.replace("impl/", "/" + tab));

            if (file.exists()) {
                BufferedReader bf = new BufferedReader(new FileReader(new File(filePath.replace("impl/", "/" + tab))));
                StringBuilder sb = new StringBuilder();
                String line = "";
                Stack<Character> stack = new Stack<>();
                String classDeclare = "";
                String hr = "";
                while ((line = bf.readLine()) != null) {
                    hr += line;
                    if (line.contains("{")) {
                        stack.push('{');
                        sb.append(line.substring(line.indexOf("{") + 1));
                        continue;
                    }

                    if (line.contains("}")) {
                        stack.pop();
                    }
                    if (stack.isEmpty()) {
                        continue;
                    }

                    sb.append(line);

                }

                Integer curlyBracket = hr.substring(hr.indexOf("interface")).indexOf("{");
                hr = hr.substring(hr.indexOf("interface") + 9);

                classDeclare = hr.substring(0, hr.indexOf("{")).trim();

                hr = hr.substring(hr.indexOf("{") + 1, hr.indexOf("}"));

                String[] s = hr.toString().split(";");

                function.append(String.format("public class " + classDeclare + "Impl implements " + classDeclare + "{\n"));

                for (String sa : s) {

                    StringBuilder fun = new StringBuilder();
                    if (sa.contains("(")) {

                        //fun.append( getImplementedFuncion(sa,table,db));
                        String connectionClassName = "ConnectDB";
                        if (sa.contains("insert")) {
                            fun.append(String.format(getInsertPreStatement(db, table, sa) + "\n"));

                        } else if (sa.contains("delete" + tableName + "(" + ComponentMdf.getType(co) + " " + co.getColName() )) {
                            fun.append(String.format(getDeletePreStatement(db, table, sa) + "\n"));
                        }else
                            if(sa.contains("delete"+tableName+"("+tableName+"Bean "+tableVar+")")){
                                System.out.println("yesBean");
                                fun.append(getDeleteBeanPreStatement(db, table, sa));
                            }
                        
                        else if (sa.contains("update")) {
                            fun.append(String.format(getUpdatePreStatement(db, table, sa) + "\n"));
                        } else if (sa.contains("All")) {
                            fun.append(String.format(getAllPreStatement(db, table, sa) + "\n"));
                        } else if (sa.contains("List")) {
                            fun.append(String.format(getListPreStatement(db, table, sa) + "\n"));
                        }
                        
                        //if(sa.contains("get"))

                        function.append(fun);
                    }

                }

                if (table.isAnyAutoIncrement()) {
                    function.append(getAutoValue(filePath, table, db));
                }

            } else {
                System.out.println("file doesn't exist");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DaoPattern.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.format(function.toString().concat("\n}"));

    }

    public static String getAllPreStatement(Database db, Table1 table, String declare) {

        String functionContent = getTry(db, table, declare);
        functionContent = functionContent.replace("QUERYHERE", "\"SELECT * FROM " + table.getTableName() + "\"");
        functionContent = functionContent.replace("SETFUNCTION", " var=pre.executeQuery();\n");
        return functionContent;
    }

    public static String getPreparedStatement(Database db, Table1 table, String declare) {
        String functionContent = getTry(db, table, declare);
        return functionContent;
    }

    public static String getQueryPreStatement(Database db, Table1 table, String declare) {
        String functionContent = getTry(db, table, declare);

        return functionContent;
    }

    public static String getInsertPreStatement(Database db, Table1 table, String declare) {
        String functionContent = getTry(db, table, declare);
           String[] s=null;
        if(table.isAnyAutoIncrement())
             s= ComponentMdf.getSetFunction(Functions.getName(table.getTableName()), table.getColumn()).split("_-_");
        else
            s=ComponentMdf.getSetFunction(Functions.getName(table.getTableName()), table.getColumnExceptAuto()).split("_-_");
        
        System.out.println(s[0]);
        String query = s[0];
        String setFunction = s[1];

        query = "\"INSERT INTO " + table.getTableName() + " " + query + "\"";
        functionContent = functionContent.replace("QUERYHERE", query);
        functionContent = functionContent.replace("SETFUNCTION", String.format(setFunction + "\n var=pre.executeUpdate(); \n if(var==1){ JOptionPane.showMessageDialog(null,\"Inserted \" );\n}\n"));
        return functionContent;
    }

    public static String getUpdatePreStatement(Database db, Table1 table, String declare) {
        String functionContent = getTry(db, table, declare);
        String tableName = Functions.getJcName(table.getTableName());
        String tableVar=Functions.getName(table.getTableName());
        Column1 column = null;
        Column1[] columns=table.getColumn();
        //
        if (table.getPrimaryColumn() == null) {
            column = table.getExceptPriCol();
        } else if(table.getPrimaryColumn()!=null){
            column = table.getPrimaryColumn();
        }
 
        String columnStr = "";
         int ind=1;
         StringBuffer setFunction=new StringBuffer();
         for (Column1 c : columns) {
            if (c.getColName().equals(column.getColName())) continue;//skip it
            
            columnStr += "," + c.getColName() + "=?";
            setFunction.append(ComponentMdf.getSet(c,ind++,tableVar+".get"+Functions.getJcName(c.getColName())+"()")+"; \n");
}
        
        columnStr = columnStr.replaceFirst(",", "");

        //String[] s = ComponentMdf.getSetFunction(Functions.getName(table.getTableName()), columns).split("_-_");
        String query = "UPDATE " + table.getTableName() + " SET " + columnStr + " where " + column.getColName() + "=?";
setFunction.append(ComponentMdf.getSet(column,ind++,tableVar+".get"+Functions.getJcName(column.getColName())+"()")+"; \n");
        functionContent = functionContent.replace("QUERYHERE", "\"" + query + "\"");
       
           System.out.println("tableName"+tableName);
             
        functionContent = functionContent.replace("SETFUNCTION", setFunction.append("\n var=pre.executeUpdate(); if(var==1){ JOptionPane.showMessageDialog(null,\"Updated\" );\n}\n"));
        return functionContent;
    }

    public static String addPrimaryKeyAtLast(String str, String primaryKey) {
        str = str.trim();
        String[] d = str.split(";");
        Integer length = d.length;
        String colName = Functions.getJcName(primaryKey);
        String last = "";
        String setFun = "";
        Integer i = 0;
        for (String a : d) {
            if (a.contains(colName)) {
                last = String.format("\n" + a.replace("" + (i + 1), "" + (length + 1)));
            }
            setFun += String.format(a + ";");
            i++;
        }
        setFun += String.format(last + ";");

        return setFun;
    }

    /*
    some work is remaining.In future,may I need this.
    public static String firstLastSwap(String str, String primaryKey) {
        String seperated = "";
        String[] s = str.split(";");

        System.out.println("setFunction:--------------- >         <--------------------\n ");
         
            System.out.println(Arrays.toString(s));
            
        Integer length = s.length-1;

        primaryKey=Functions.getJcName(primaryKey);
        for (Integer i = 0; i < length; i++) {
            if (s[i].contains(primaryKey)) {
                System.out.println("////////////////////////////////// length="+length+" i="+i);
                
                String t = String.format(s[i].replace(""+(i+1),String.valueOf(length))+"\n");
                s[i] = String.format(s[length-1].replace(String.valueOf(length),""+(i+1))+"\n");
                s[length-1] = t;
            }
            seperated += String.format(s[i].concat(";\n"));
        }
        return seperated;
    }
     */
    public static String getPrimaryColName(Table1 table) {
        String colName = "";
        Column1[] column = table.getColumn();
        for (Column1 col : column) {

            if (col.isPrimaryKey()) {
                return col.getColName();
            }
        }

        return null;
    }

    public static String getDeletePreStatement(Database db, Table1 table, String declare) {
        String functionContent = getTry(db, table, declare);
        String tableName = Functions.getJcName(table.getTableName());
       
        Column1 column = null;
        if (table.getPrimaryColumn() == null) {
            System.out.println("Not primary column:" + table.getExceptPriCol());
            column = table.getExceptPriCol();
        } else {
            System.out.println("Primary column:" + table.getPrimaryColumn());
            column = table.getPrimaryColumn();
        }
        String query = "DELETE FROM " + table.getTableName() + " where " + column.getColName() + "=?";
        //String setFunction="pre.set"+ComponentMdf.getType(column)+"(1,"+column.getColName()+");\n";

        String setFunction = ComponentMdf.getSet(column, 1, column.getColName()) + "; \n var=pre.executeUpdate(); \n if(var==1){ JOptionPane.showMessageDialog(null,\"Deleted \" );\n}\n";
        functionContent = functionContent.replace("QUERYHERE", "\"" + query + "\"");
        functionContent = functionContent.replace("SETFUNCTION", setFunction);

        return functionContent;
    }

    // bean deletion 
     public static String getDeleteBeanPreStatement(Database db, Table1 table, String declare) {
        String functionContent = getTry(db, table, declare);
        String tableName=Functions.getJcName(table.getTableName());
          String tableVar=Functions.getName(table.getTableName());
          String columnStr = "";
            columnStr = columnStr.replaceFirst(",", "");
            String setFunction="";
        Integer i=1;
        String a="";
        for(Column1 column:table.getColumn()){
          setFunction += ComponentMdf.getSet(column, i++, tableVar+".get"+Functions.getJcName(column.getColName())+"()") + ";\n";
          a+=","+column.getColName()+"=?";
        }
        a=a.replaceFirst(",", "");
        a=a.replace(","," AND ");
        String query = "DELETE FROM " + table.getTableName() + " where " + a;
        functionContent = functionContent.replace("QUERYHERE", "\"" + query + "\"");
        functionContent = functionContent.replace("SETFUNCTION", setFunction+"var=pre.executeUpdate(); \nif(var==1){ JOptionPane.showMessageDialog(null,\"Deleted \" );\n}\n");

        return functionContent;
    }
    
    public static String getAllViewPreStatement(Database db, Table1 table, String declare) {
        String functionContent = getTry(db, table, declare);

        return functionContent;
    }

    public static String getTry(Database db, Table1 table, String declare) {
        String connectionClassName = "ConnectDB";
        StringBuilder functionContent = new StringBuilder();

        if (declare != null && !declare.trim().equals("")) {

            String returnStatement = "";
            Integer index = declare.indexOf("(");
            returnStatement = declare;
            if (index != -1) {
                returnStatement = returnStatement.replaceFirst("public", "");
                returnStatement = returnStatement.replaceFirst("static", "");
                returnStatement = returnStatement.trim();

                returnStatement = returnStatement.substring(0, returnStatement.trim().indexOf("("));
                returnStatement = returnStatement.trim();
                returnStatement = returnStatement.substring(0, returnStatement.indexOf(" ")).trim();

            }

            functionContent.append(String.format(" @Override \n public " + String.format(declare + "{\n ")));

            switch (returnStatement) {
                case "Long":
                    functionContent.append(returnStatement + " var=-1l;\n");
                    break;
                case "Double":
                case "Integer":
                case "Short":
                case "Float":
                case "Byte":
                    functionContent.append(returnStatement + " var=-1;\n");
                    break;
                case "Character":
                    functionContent.append(returnStatement + " var='\'0';\n");
                    break;
                case "Boolean":
                    functionContent.append(returnStatement + " var=false;\n");
                    break;
                default:
                    functionContent.append(returnStatement + " var=null;\n");
                    break;

            }
        }//else get only try 

        functionContent.append(String.format("\n try{ \n"));

        functionContent.append(String.format(""
                + "Class.forName(\"com.mysql.jdbc.Driver\")\n;"
                + "Connection connection=" + connectionClassName + ".getConnection(\"" + db.getDbName() + "\","
                + "\"" + db.getUserName() + "\","
                + "\"" + db.getPassword() + "\""
                + ");\n"));

        String commaColumns = String.format("PreparedStatement pre=connection.prepareStatement(QUERYHERE);\nSETFUNCTION\n");
        
        functionContent.append(commaColumns);
        functionContent.append(String.format("}catch(ClassNotFoundException ex){"
                + "ex.printStackTrace();\n"
                + "}"
                + ""
                + "catch(SQLException exception){\n"
                + "JOptionPane.showMessageDialog(null,exception.getMessage());\n}"));

        if (declare != null && !declare.trim().equals("")) {
            functionContent.append("return var;\n}");
        } else// else function 
        {
            functionContent.append("RETURNVALUE"
                    + "}");
        }

        return functionContent.toString();

    }

    public static Integer getPosition(String[] colName, Object[] values, Integer columnIndex) {
        Integer col_len = colName.length;
        //left selected
        Integer ls = 0;
        // left not selected
        Integer lns = 0;

        for (Integer i = columnIndex - 1; i >= 0; i--) {
            if (colName[i] != null && !colName[i].equals("") && values[i] != null && !String.valueOf(values[i]).equals("")) {
                ls++;
            } else if ((colName[i] == null || colName[i].equals("")) || (values[i] == null || String.valueOf(values[i]).equals(""))) {
                lns++;
            }
        }

        return columnIndex - lns + 1;
    }

    public static String getListPreStatement(Database db, Table1 table, String declare) {
        String functionContent = getTry(db, table, declare);
        String tableName = Functions.getJcName(table.getTableName());
        functionContent = functionContent.replace("QUERYHERE", "\"" + "SELECT * FROM " + table.getTableName() + "\"");

        String setFunction = String.format("ResultSet resultSet=pre.executeQuery();\n"
                + " var =new ArrayList<>();\n"
                + "while(resultSet.next()){\n"
                //+ TableToObject.getSetMethods(table)
                + "var.add(" + TableToObject.getConstructor(table) + ");\n"
                + "}\n");

        functionContent = functionContent.replace("SETFUNCTION", setFunction);

        return functionContent.toString();

    }

    public static void createUtilityFiles(String thisProPath,String newProPath,Database db) {
        String projectName = Functions.getJcName(db.getDbName());
        //C:/Users/tahir hussain/Documents/JDBC1/CrudSoft
//        copyFiles(db, "C:/Users/tahir hussain/NewProject/JDBS/src/utility/",
//                "C:/Users/tahir hussain/Documents/JDBC1/" + projectName + "/src/com/utility/", FileType.FILE);
//  
    copyFiles(db, thisProPath+"/src/utility/",
                newProPath +"utility/", FileType.FILE);
    
    }

    public static void createConnectionFile(String thisProPath,String newProPath,Database db) {

        String projectName = Functions.getJcName(db.getDbName());
        //URL url=getClass().getResource("C:/Users/tahir hussain/NewProject/JDBS/src/database/connect");
        File source = new File(thisProPath+"/src/db/");

        File destine = new File(newProPath+"database/connect/");
//        String destine="C:/Users/tahir hussain/Documents/JDBC1/" + projectName + "/com/";
        if (!destine.exists()) {
            destine.mkdirs();
        }
        File[] f = source.listFiles();
        for (File a : f) {
            System.out.println("Pooja:"+f.length+"  "+a.getAbsolutePath());
            try {
                System.out.println("new Project path:"+newProPath);
                File1.textToJava(a,newProPath+"database/connect/");
//File file=createFile("C:/Users/tahir hussain/Documents/JDBC1/"+projectName+"/com/database/connect/"+a.getName());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DaoPattern.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}

    public static void copyFiles(Database db, String from, String to, FileType fileType) {
        String projectName = Functions.getJcName(db.getDbName());
        File source = new File(from);
        File destine = new File(to);

        if (!destine.exists()) {
            destine.mkdirs();
        }
        System.out.println(fileType == FileType.JAVA ? ".java" : ".notjava");

        File[] f = source.listFiles(fileType == FileType.JAVA ? new FilterFile(".java") : new ExcludeFilterFile(".java"));

        for (File a : f) {
            System.out.println("FileName-----------------------  >>> >>> >>>" + a.getAbsolutePath());
            try {
                File1.textToJava(a, to);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DaoPattern.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public static void createFrameFile(String thisProPath,String newProPath,FrameInfo frame,Database db, Table1 table, String destinePath) {
        
        
        
        File destine = new File(destinePath);
        if (!destine.exists()) {
            destine.mkdirs();
        }

        // now create frame files
        //path of frame1 file
        System.out.println(thisProPath+"/src/main/frame1"+"  destine---------> "+destinePath);
        
        System.out.println(thisProPath+"/src/main/frame1");
        
        File frameFile = new File(thisProPath+"/src/main/frame1");

        /*
        List<Component1> list=new ArrayList<>();
        Table1[] table=db.getTables();
        for(Table1 t:table){
            String tableName=Functions.getJcName(t.getTableName());
            destine=new File(destinePath+tableName.concat(".java"));
            File1.createFile(frame,destine);

            Integer i=1;
 
        Integer x=255;
        Integer y=60;
        Integer j=1;

        for(Column1 col:t.getColumn()){
            y=60*i;
            if(y<530)
            list.add(new Component1(col,"WHITE", "WHITE",x,y,250,30));
            else{
            x+=x;
            i=0;
            }
        i++;
        }
        
        ComponentGenerator.setComponents(destine.getAbsolutePath(), t, 'V', list);        
        }
  
         */
        List<Component1> list = new ArrayList<>();
        String tableName = Functions.getJcName(table.getTableName());
        destine = new File(destinePath + tableName.concat("Frame.java"));
        File1.createFile(frameFile, destine);
        Integer i = 1;
        Integer x = 255;
        Integer y = 60;
        Integer j = 1;

        for (Column1 col : table.getColumn()) {
            y = 60 * i;
            list.add(new Component1(col, "53,59,72", "204,204,204", x, y, 250, 40));
            if (y >= 480) {
                x += (2*x);
                i = 0;
            }
            i++;
        }
        ComponentGenerator.setComponents(thisProPath,newProPath,frame,destine.getAbsolutePath(), db, table, 'V', list);
    }

    private enum FileType {
        FILE, JAVA, ALL, CLASS
    }
}
