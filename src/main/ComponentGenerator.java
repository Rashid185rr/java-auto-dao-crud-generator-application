/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import database.table.Column1;
import database.table.Table1;
import java.awt.Rectangle;
import java.sql.Types;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import database.Database;
import fr.FrameInfo;
import static main.File1.getFile;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 *
 * @author tahir hussain
 */
public class ComponentGenerator {

    private static String getColNames(String allField) {
        String colName = "";
        String[] al = allField.split(",");
        for (String s : al) {
            colName += "," + s.concat(".getName()");
        }
        colName = colName.replaceFirst(",", "");
        return colName;
    }

    public ComponentGenerator(Database db) {

    }

    public static void setComponents(String thisProjPath, String newProjPath,FrameInfo frame,String fileName, Database db, Table1 table, char type, List<Component1> list) {
//normal size    
//    File fileName=new File(file);
//       Integer prefSizeX=1500;
        Integer prefSizeY = 1024;
        Integer compSizeX = 250;
        Integer compSizeY = 50;

        Column1[] column = table.getColumn();
        String tableName = Functions.getJcName(table.getTableName());
        String tableVar = Functions.getName(table.getTableName());

        Integer col_len = column.length;
        Integer x = 200, y = 200, width = 0, height = 0;

//        x=x/col_len;
//        y=y/col_len;
//        
        Integer index = 0;
        JTextField textField = null;
        JLabel lbl = null;
        JDateChooser chooser = null;
//        String importText = "import javax.swing.JTextField;";
//        
        String importDate = String.format("import java.util.Date;\n");
        String importDateChooser = String.format("import com.toedter.calendar.JDateChooser;\n");
        String importQueue = String.format("import java.awt.EventQueue;\n");
        String importPass = String.format("import javax.swing.JPasswordField()\n");
        String menuBar = String.format("import javax.swing.JMenuBar()\n");
        String menu = String.format("import javax.swing.JMenu;\n");
        String userItem = String.format("import javax.swing.JMenuItem()\n");
        //String bigInt = String.format("import java.math.BigInteger;\n");
        String importRdBtn = String.format("import javax.swing.JRadioButton;\n");
        String importChBox = String.format("import javax.swing.JCheckBox;");
        String importBlob = String.format("import java.sql.Blob;\n");
        String importClob = String.format("import java.sql.Clob;\n");
        String importTimestamp = String.format("import java.sql.Timestamp;\n");
        String importBigDec = String.format("import java.math.BigDecimal;\n");

//        import pos.com.daoimp.UserDaoImp;
        String colName = "";
        String atComponent = "";
        String function = "";

        StringBuilder fileContent = new StringBuilder();
        StringBuilder allImported = new StringBuilder();
        StringBuilder allInit = new StringBuilder();
        StringBuilder allFunction = new StringBuilder();
        StringBuilder declaration = new StringBuilder();
        String heading = "";
        allImported.append(String.format("import javax.swing.JTextField;\n"
                + "import javax.swing.JTable;\n"
                + "import javax.swing.JScrollPane;\n"
                + "import java.sql.ResultSet;\n"
                + "import javax.swing.JLabel;\n"
                + "import java.awt.ActiveEvent;\n"
                + "import java.awt.event.ActionEvent;\n"
                + "import javax.swing.ImageIcon;\n"
                + "import javax.swing.ImageIcon;\n"
                + "import javax.swing.BorderFactory;\n"
        ));

        String value = "";
        for (Component1 com : list) {
            System.out.println("comp34" + com.getComName());

            System.out.println(com);
            colName = Functions.getName(com.getColumn().getColName());
            com.setCompHeading(com.getColumn().getColName());
            String col_name = com.getComName();

            System.out.println("col_name--->" + col_name);
            switch (com.getColumn().getDataType()) {
                case Types.ARRAY:
                    System.out.println("ARRAY");
                    com.setComName(colName.concat("Txt"));
                    allInit.append(getTextField(com, fileName, col_name));
                    declaration.append(String.format("JTextField " + com.getComName() + ";\n"));
                    break;
                case Types.BIGINT:
                    System.out.println("BIGINT");
//                    if (bigInt != null) {
//                        allImported.append(bigInt);
//                    }
                    com.setComName(colName.concat("Txt"));
                    allInit.append(getTextField(com, fileName, col_name));
                    //allFunction.append(menu);
//                    bigInt = null;
                    declaration.append(String.format("JTextField " + com.getComName() + ";\n"));
                    break;

                case Types.BINARY:
                case Types.BIT:
                    com.setComName(colName.concat("Txt"));
                    allInit.append(getTextField(com, fileName, col_name));
                    declaration.append(String.format("JTextField " + com.getComName() + ";\n"));
                    break;
                case Types.BLOB:
                    System.out.println("BLOB");
                   /*
                    if (importBlob != null) {
                        allImported.append(importBlob);
                    }
                    com.setComName(colName.concat("Txt"));
                    allInit.append(getTextField(com, fileName, col_name));
                    declaration.append(String.format("JTextField " + com.getComName() + ";\n"));
                    importBlob = null;
*/                    
break;
                case Types.BOOLEAN:
                    System.out.println("BOOLEAN");
                    if (importRdBtn != null) {
                        allImported.append(importRdBtn);
                    }
                    com.setComName(com.getColumn().getColName().concat("Rd"));
                    allInit.append(getTextField(com, fileName, col_name));
                    importRdBtn = null;
                    allInit.append(getLabel(com, colName, ""));
                    declaration.append(String.format("JRadioBox " + com.getComName()));

                    break;
                case Types.CHAR:
                    System.out.println("CHAR");
                    com.setComName(colName.concat("Txt"));
                    allInit.append(getTextField(com, fileName, col_name));
                    declaration.append(String.format("JTextField " + com.getComName() + ";\n"));
                    break;
                case Types.CLOB:
                    System.out.println("CLOB");
                    if (importClob != null) {
                        allImported.append(importClob);
                    }
                    com.setComName(colName.concat("Txt"));
                    allInit.append(getTextField(com, fileName, col_name));
                    declaration.append(String.format("JTextField " + com.getComName() + ";\n"));
                    importClob = null;
                    break;
                case Types.DATALINK:
                    System.out.println("DATALINK");
                    com.setComName(colName.concat("Txt"));
                    allInit.append(getTextField(com, fileName, col_name));
                    declaration.append(String.format("JTextField " + com.getComName() + ";\n"));
                    break;
                case Types.DATE:
                    System.out.println("DATE");
                    com.setComName(com.getColumn().getColName().concat("Date"));
                    if (importDateChooser != null) {
                        allImported.append(importDateChooser);
                    }
                    if (importDate != null) {
                        allImported.append(importDate);
                    }

                    allInit.append(getDateChooser(com, fileName, importDateChooser, col_name));
                    declaration.append(String.format("JDateChooser " + com.getComName() + ";\n"));
                    importDateChooser = null;

                    allInit.append(getLabel(com, colName, ""));
                    importDate = null;
                    break;
                case Types.DECIMAL:

                    System.out.println("DECIMAL");
                    if (importBigDec != null) {
                        allImported.append(importBigDec);
                    }
                    com.setComName(colName.concat("Txt"));
                    allInit.append(getTextField(com, fileName, col_name));
                    declaration.append(String.format("JTextField " + com.getComName() + ";\n"));
                    importBigDec = null;
                    break;
                case Types.DISTINCT:
                case Types.DOUBLE:
                case Types.FLOAT:
                case Types.INTEGER:
                case Types.JAVA_OBJECT:
                case Types.LONGNVARCHAR:
                case Types.LONGVARBINARY:// here blob type used, it has been left for you _-_ 
                case Types.LONGVARCHAR:
                case Types.NCHAR:
                    System.out.println("NCHAR");
                    com.setComName(colName.concat("Txt"));
                    allInit.append(getTextField(com, fileName, col_name));
                    declaration.append(String.format("JTextField " + com.getComName() + ";\n"));
                    break;
                case Types.NCLOB:
                    System.out.println("NCLOB");
                    if (importClob != null) {
                        allImported.append(importClob);
                    }
                    com.setComName(colName.concat("Txt"));
                    allInit.append(getTextField(com, fileName, col_name));
                    declaration.append(String.format("JTextField " + com.getComName() + ";\n"));
                    importClob = null;
                    break;
                case Types.NULL:
                case Types.NUMERIC:
                    System.out.println("NUMERIC");
                    com.setComName(colName.concat("Txt"));
                    allInit.append(getTextField(com, fileName, col_name));
                    declaration.append(String.format("JTextField " + com.getComName() + ";\n"));
                    break;
                case Types.NVARCHAR:
                case Types.OTHER:
                case Types.REAL:
                    System.out.println("REAL");
                    com.setComName(colName.concat("Txt"));
                    declaration.append(String.format("JTextField " + com.getComName() + ";\n"));
                    allInit.append(getTextField(com, fileName, col_name));
                    break;
                case Types.REF:
                    System.out.println("REF");
                    com.setComName(colName.concat("Txt"));
                    allInit.append(getTextField(com, fileName, col_name));
                    break;
                case Types.ROWID:
                    System.out.println("ROWID");
                case Types.SMALLINT:
                    System.out.println("SMALLINT");
                    com.setComName(colName.concat("Txt"));
                    allInit.append(getTextField(com, fileName, col_name));
                    declaration.append(String.format("JTextField " + com.getComName() + ";\n"));
                    break;
                case Types.SQLXML:
                    System.out.println("SQLXML");
                    com.setComName(colName.concat("Txt"));
                    allInit.append(getTextField(com, fileName, col_name));
                    break;
                case Types.STRUCT:
                    System.out.println("STRUCT");
                    com.setComName(colName.concat("Txt"));
                    allInit.append(getTextField(com, fileName, col_name));
                    declaration.append(String.format("JTextField " + com.getComName() + ";\n"));
                    break;
                case Types.TIME:
                case Types.TIMESTAMP:
                case Types.TIMESTAMP_WITH_TIMEZONE:
                case Types.TIME_WITH_TIMEZONE:
                    System.out.println("TIME_WITH_TIMEZONE");
                    if (importDateChooser != null) {
                        allImported.append(importDateChooser);
                    }
                    if (importDate != null) {
                        allImported.append(importDate);
                    }
                    if (importTimestamp != null) {
                        allImported.append(importTimestamp);
                    }
                    com.setComName(colName.concat("Ts"));
                    System.out.println("Component name" + com.getComName() + " " + tableName);
                    allInit.append(getDateChooser(com, fileName, importDateChooser, col_name));
                    importDateChooser = null;
                    importDate = null;
                    importTimestamp = null;
                    declaration.append(String.format("JDateChooser " + com.getComName() + ";\n"));

                    break;
                case Types.TINYINT:
                case Types.VARBINARY:
                case Types.VARCHAR:
                    String c = colName;
                    com.setComName(colName.concat("Txt"));
                    allInit.append(getTextField(com, fileName, col_name));
                    declaration.append(String.format("JTextField " + com.getComName() + ";\n"));
                    break;

                default:
                    break;
            }
            allInit.append(getLabel(com, colName, ""));
            declaration.append(String.format("JLabel " + colName.concat("lbl") + ";\n"));
            heading += ",\"" + com.getCompHeading() + "\"";
            if (com.getComName().endsWith("Txt")) {
                allFunction.append(functionFocusGainLost(com, "", ""));
            }

            if (com.getComName().endsWith("Ts")) {
                value += "," + com.getComName().concat(".getDate()");
            } else if (com.getComName().endsWith("Date")) {
                value += "," + com.getComName().concat(".getDate()");
            } else if (com.getComName().endsWith("Txt") || com.getComName().endsWith("Rd")) {
                value += "," + com.getComName().concat(".getText()");

            } else if (com.getComName().endsWith("Combo")) {
                value += "," + String.format(com.getComName().concat(".getSelectedItem()"));
            }

        }

        System.out.println("=============\n\n\n\n\n\n=============");
        String impVar = Functions.getName(table.getTableName());
        String daoVar = Functions.getName(table.getTableName());
        //setTable(fileName, table, type,  comp);
        String fi = "";
        //import
        allImported.append(String.format("import com.utility.TableMdf;\n"));
        allImported.append(String.format("import com.beans." + tableName + "Bean;\n"));
        allImported.append(String.format("import com.daoimpl." + tableName + "DaoImpl;\n"));
        allImported.append(String.format("import com.utility.DateFormatter;\n"));
        allImported.append(String.format("import com.utility.Query;\n"));

        declaration.append(String.format(tableName + "Bean " + daoVar + "Bean;\n"));
        declaration.append(String.format(tableName + "DaoImpl " + impVar + "DaoImpl;\n"));
        declaration.append("JScrollPane scrollPane;\n");
        //constructor
        StringBuilder constr = new StringBuilder();
        constr.append("super(\""+frame.getTitle()+"\");\n");
        constr.append(
                impVar + "DaoImpl=new " + tableName + "DaoImpl();\n"
        );

        
        //constr.append((table.getPrimaryColumn() != null ? table.getPrimaryColumn().getColName() : table.getExceptPriCol().getColName()) + " =" + tableVar + "DaoImpl.getAutoIncrement();\n");
        //declaration.append("String " + (table.getPrimaryColumn() != null ? table.getPrimaryColumn().getColName() : table.getExceptPriCol().getColName()) + ";\n");
        if (table.isAnyAutoIncrement()) {
            declaration.append("String " + table.getAutoIncrementCol().getColName() + ";\n");
            constr.append(table.getAutoIncrementCol().getColName() + "= " + tableVar + "DaoImpl.getAutoIncrement();\n");

        }//String.format("fillTable(" +tableVar+"DaoImpl.get"+tableName+"AllRecords()");\n"
        //function
//        allFunction.append(String.format("    public void fillTable(ResultSet resultSet){\n"
//                + "       table.setModel(TableMdf.getDefaultTableModel(resultSet));\n"
//                + "    }\n"));

        allFunction.append(mouseClicked("table", "fillFieldsFromTable();"));

        System.out.println("\n============");
        System.out.println(allInit);
        System.out.println("\n===================== \n");
        //allInit.append(getButton(new Component1(),"",""));

        allImported.append("import javax.swing.JButton;\n");

        String allField = getAllFieldName(list);
        declaration.append("Object[] obj;\n");
        //writing manually because you will find it every frame 

// here I wrote initComponents        

constr.append("initComponents() ;\n");
        constr.append("obj=new Object[]{" + allField + "};\n");
        constr.append("heading=new String[]{" + heading.replaceFirst(",", "") + "};\n");
        constr.append("setValues();\n");
        constr.append(" colName=new String[]{" + getColNames(allField) + "};\n");
        declaration.append("String[] heading;\n");
        allFunction.append("public void setValues(){\nvalues=new Object[]{" + value.replaceFirst(",", "") + "};}\n");
        declaration.append("Object[] values;\n");
        declaration.append("String[] colName;\n");
 allImported.append("import com.utility.CheckComponent;");
int btnX=10;
 if(frame.getBackBtn()){
        declaration.append("JButton backBtn;\n");
        //writing path manually 
        allInit.append(getButton(new Component1("backBtn", "BACK", null, null, btnX, 530, 140, 50),
                "/com/images/"
                + getCopyPastedImageName(newProjPath + "images/",
                        thisProjPath + "/src/frame/newsoftframe/images/back/")));
        //this function will be implemented in AddFunction class
        allFunction.append(actionPerformed("backBtn", "back" + "();"));
btnX+=200;

 }       
// add button
if(frame.getInsertBtn()){
        declaration.append("JButton addBtn;\n");
        String projectName = Functions.getJcName(db.getDbName());
        //writing path manually 
        allInit.append(getButton(new Component1("addBtn", "ADD", null, null, btnX, 530, 140, 50),
                "/com/images/"
                + getCopyPastedImageName(newProjPath + "images/",
                        thisProjPath + "/src/frame/newsoftframe/images/add/")));
        //Column1 column, String bgColor, String fgColor, Integer xx, Integer yy, Integer compSizeX, Integer compSizeY
        //this function will be implemented in AddFunction class
        allFunction.append(actionPerformed("addBtn", "add" + tableName + "();"));
btnX+=200;
}

// delete button
if(frame.getDeleteBtn()){
declaration.append("JButton deleteBtn;\n");

        //writing path manually 
        allInit.append(getButton(new Component1("deleteBtn", "DELETE", null, null, btnX, 530, 140, 50),
                "/com/images/"
                + getCopyPastedImageName(newProjPath + "images/",
                        thisProjPath + "/src/frame/newsoftframe/images/delete/")));

        //this function will be implemented in AddFunction class
        allFunction.append(actionPerformed("deleteBtn", "delete" + tableName + "();"));
btnX+=200;
}
if(frame.getUpdateBtn()){
        // update button
        declaration.append("JButton updateBtn;\n");
        //writing path manually 
        allInit.append(getButton(new Component1("updateBtn", "UPDATE", null, null, btnX, 530, 140, 50),
                "/com/images/"
                + getCopyPastedImageName(newProjPath + "images/",
                        thisProjPath + "/src/frame/newsoftframe/images/update/")));

        //this function will be implemented in AddFunction class
        allFunction.append(actionPerformed("updateBtn", "update" + tableName + "();"));
btnX+=200;
}
       
if(frame.getViewBtn()){
// view button
        declaration.append("JButton viewBtn;\n");
        //writing path manually 
        allInit.append(getButton(new Component1("viewBtn", "VIEW", null, null, btnX, 530, 140, 50),
                "/com/images/"
                + getCopyPastedImageName(newProjPath + "images/",
                        thisProjPath + "/src/frame/newsoftframe/images/view/")));
        //this function will be implemented in AddFunction class
        allFunction.append(actionPerformed("viewBtn", "view" + tableName + "();"));
btnX+=200;
}

if(frame.getNextBtn()){
        declaration.append("JButton nextBtn;\n");
        //writing path manually 
        allInit.append(getButton(new Component1("nextBtn", "NEXT", null, null, btnX, 530, 140, 50),
                "/com/images/"
                + getCopyPastedImageName(newProjPath + "images/",
                        thisProjPath + "/src/frame/newsoftframe/images/next/")));
        //this function will be implemented in AddFunction class
        allFunction.append(actionPerformed("nextBtn", "next" + "();"));
btnX+=200;
}

if(frame.getSearchTxt()){
        // adding searchTxt
        Component1 comp = new Component1("searchTxt", "Search " + tableName, "53,59,72", "189,195,199",btnX, 530, 255, 50);
        //alternative solution _-_
        allInit.append(getTextField(comp, fileName, "search"));
        allFunction.append(functionFocusGainLost(comp, "", "//write code for focus lost"));//"\nfillTable("+ tableVar+"DaoImpl.get"+tableName+"AllRecords());\n"
        allInit.append(addKeyListenerWithKeyPressed(comp.getComName()));
        allFunction.append(getKeyPressedFunction(comp.getComName(), String.format("\n//do it\n")));
        allInit.append(addKeyListenerWithKeyReleased(comp.getComName()));
        allFunction.append(getKeyReleasedFunction(comp.getComName(),
                String.format(
                        //                        + "if(searchTxt.getText()==null || searchTxt.getText().equals(\"\") || searchTxt.getText().equals(\"Search "+tableName+"\"))"
                        //                        + "\nfillTable("+ tableVar+"DaoImpl.get"+tableName+"AllRecords());\n")));
                        "TableMdf.tableSort(table,searchTxt);\n")));
        declaration.append(String.format("JTextField " + comp.getComName() + ";\n"));
        allImported.append("import java.awt.event.KeyEvent;\n");
btnX+=200;
}
        //allFunction.append(mouseClicked("table",""));
        File1.writeFile(fileName, allImported.toString(), constr.toString(), allInit.toString(), allFunction.toString() + declaration.toString());
        //defualt table widht and height      
        setTable(fileName, table, 10, 617, 1350, 573);

    }

    public static String functionFocusGainLost(Component1 com, String focusGainContent, String focusLostContent) {

        return String.format(getFocusGainedFunction(com.getComName(), "if(!" + com.getComName() + ".getText().equals(\"" + com.getCompHeading() + "\")){\n" + com.getComName() + ".setText("
                + com.getComName() + ".getText());\n }"
                + "else{\n"
                + com.getComName() + ".setText(\"\");\n}\n"
                + focusGainContent + "\n"
        )
                + "\n"
                + getFocusLostFunction(com.getComName(), "if(" + com.getComName() + ".getText()==null || " + com.getComName() + ".getText().equals(\"\")){\n"
                        + focusLostContent + "\n"
                        + com.getComName() + ".setText(\"" + com.getCompHeading() + "\");\n}\n"
                )
                + "\n"
        );
    }

    public static String getAllFieldName(List<Component1> list) {
        String str = "";
        for (Component1 com : list) {
            str += "," + com.getComName();
        }
        str = str.replaceFirst(",", "");

        return str;
    }

    public static String addButtonFunction(String tableName, String tableVar, String functionName) {

        String add = String.format("public void " + functionName + "(" + tableName + "Bean " + tableVar + "Bean){\n"
                + "if(CheckComponent.isAllFilled(obj)){\n"
                //+ tableVar+" = "+tableName+"Bean( ;\n"
                //+tableName+"DaoImpl.insert"+tableName+"("+tableVar+");\n"
                + "System.out.println(CheckComponent.getValues(obj)\n);\n"
                + "}\n}\n");
        return add;
    }
//it is the path of the pic which exists inside the sub folder of this project 

    public static String getImagePath(String filePath) {
        System.out.println("filePath is " + filePath);
        File file = new File(filePath);
        String[] f = file.list();

        for (String fa : f) {
            System.out.println(fa);
        }
        Integer length = f.length;
        return filePath + (f[(int) (Math.random() * length)]);
    }

    public static String getCopyPastedImageName(String newProject, String filePath) {
        System.out.println("getCopyPastedImageName:" + filePath);
        String selected = getImagePath(filePath);
        System.out.println("This image is copying:" + selected);

        System.out.println(" newProject:" + newProject + "   filePath-->" + filePath);
        File imgFile = new File(selected);
        System.out.println(newProject);
        File destineFile = new File(newProject);
        if (!destineFile.exists()) {
            destineFile.mkdir();
        }

        BufferedOutputStream out = null;
        BufferedInputStream inp = null;
        System.out.println("---> " + newProject + imgFile.getName());
        try {
            destineFile = new File(newProject + imgFile.getName());
            if (!destineFile.exists()) {
                destineFile.createNewFile();
            }

            out = new BufferedOutputStream(new FileOutputStream(destineFile));
            inp = new BufferedInputStream(new FileInputStream(imgFile));

            byte[] b = new byte[1024];
            Integer length = b.length;

            while (inp.read(b, 0, length) != -1) {
                out.write(b, 0, length);

            }
            out.close();
            inp.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destineFile.getName();
    }

    public static void getButtons() {

//        btnUpdate.setBackground(new java.awt.Color(11, 92, 49));
//        btnUpdate.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
//        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
//        btnUpdate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        btnUpdate.setText("Update Certificate");
//        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//        btnUpdate.setOpaque(true);
//        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                btnUpdateMouseClicked(evt);
//            }
//        });
    }

    public static String getFocusGained(String compName) {

        String s = String.format(compName + ".addFocusListener(new java.awt.event.FocusAdapter() {\n"
                + "public void focusGained(java.awt.event.FocusEvent evt) {\n"
                + compName + "FocusGained(evt);\n"
                + "}\n"
                + "});");
        return s;
    }

    public static String getFocusGainedFunction(String compName, String funContent) {

        String s = String.format(" private void " + compName + "FocusGained(java.awt.event.FocusEvent evt) {\n"
                //                + compName + ".setBorder(null);\n"
                + funContent + "\n"
                + "}\n");
        return s;
    }
//field.setBorder(BorderFactory.createEtchedBorder(Color.RED, Color.RED));

    public static String getFocusLost(String compName) {

        String s = String.format(compName + ".addFocusListener(new java.awt.event.FocusAdapter() {\n"
                + "public void focusLost(java.awt.event.FocusEvent evt) {\n"
                //+ "if(" + compName + ".getText()!=null || " + compName + ".getText().trim().equals(\"\")){\n"
                //  + compName + ".setBorder(BorderFactory.createEtchedBorder(Color.RED, Color.RED));\n"
                // + "}else{\n"
                //+ compName + ".setBorder(BorderFactory.createEtchedBorder(Color.GREEN, Color.GREEN));\n"
                //+ "}"

                + compName + "FocusLost(evt);\n"
                + "}\n"
                + "});");
        return s;
    }

    public static String getFocusLostFunction(String compName, String funContent) {
        String s = String.format(" private void " + compName + "FocusLost(java.awt.event.FocusEvent evt) {\n"
                + funContent + "\n"
                + "}\n");
        return s;
    }

    public static String getTextField(Component1 com, String fileName, String col_name) {
        String colName = com.getComName();
        Integer defaultSize = com.getColumn() != null ? com.getColumn().getColumnSize() : 250;
        String atComponent = String.format(colName + " =new JTextField(" + defaultSize + ");\n"
                + colName + ".setToolTipText(\"" + com.getCompHeading() + "\");\n"
                + colName + ".setBounds(" + com.getX() + "," + (com.getY()) + ","
                + com.getCompSizeX() + "," + com.getCompSizeY() + ");\n"
                + colName + ".setName(\"" + col_name + "\");\n"
                + colName + ".setFont(new java.awt.Font(\"sansserif\", 1, 20));\n"
                + colName + ".setBorder(\n"
                + "new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED,\n"
                + "new java.awt.Color(153, 153, 153),\n"
                + "new java.awt.Color(153, 153, 153), null, null));\n");
        String event = "";
        String addAction = "";
        String bool = com.getColumn() != null ? com.getColumn().getIsAutoIncrememnt() : "NO";
        if (bool.equals("YES")) {
            //atComponent += String.format(colName + ".setEditable(false);\n");
            atComponent += colName + ".setText(\"\");\n";
            atComponent += String.format(colName + ".setForeground(new java.awt.Color(204, 204, 204));\n"
                    + colName + ".setBackground(Color.GRAY);\n"
            );

        } else {
            atComponent += String.format(
                    colName + ".setText(\"" + com.getCompHeading() + "\");\n"
                    + colName + ".setForeground(new java.awt.Color(" + com.getFgColor() + "));\n"
                    + colName + ".setBackground(new java.awt.Color(" + com.getBgColor() + "));\n"
            );
            addAction = String.format(getFocusGained(com.getComName()) + "\n" + getFocusLost(com.getComName()));
        }

        atComponent = atComponent + String.format("panel.add(" + colName
                + ",new org.netbeans.lib.awtextra.AbsoluteConstraints(" + com.getX() + "," + (com.getY()) + ","
                + com.getCompSizeX() + "," + com.getCompSizeY() + "));\n");

        //File1.writeFile(fileName, atComponent + addAction, event + "JTextField " + colName + ";");
        return atComponent + addAction;
    }

    public static String getDateChooser(Component1 com, String fileName, String importText, String col_name) {
        String colName = com.getComName();
        String atComponent = String.format(colName + "=new JDateChooser();\n"
                + colName + ".setBounds(" + com.getX() + "," + (com.getY()) + "," + com.getCompSizeX() + "," + com.getCompSizeY() + ");\n"
                + colName + ".setName(\"" + col_name + "\");\n"
                + colName + ".setBackground(new java.awt.Color(" + com.getBgColor() + "));\n"
                + colName + ".setFont(new java.awt.Font(\"sansserif\", 0, 14));\n");

//                            if(col.getIsAutoIncrememnt().equals("YES"))
//                                atComponent+=colName+".setEditable(false);";
//                  
        atComponent = atComponent + String.format("panel.add(" + colName
                + ",new org.netbeans.lib.awtextra.AbsoluteConstraints("
                + com.getX() + "," + (com.getY()) + "," + com.getCompSizeX() + "," + com.getCompSizeY() + "));\n");

//                    if (com.getColumn().getIsAutoIncrememnt().equals("YES"))
//                        atComponent=colName+".setEnabled(false)";
        //File1.writeFile(fileName, atComponent, "JDateChooser " + colName + ";");
        System.out.println("!@#$%" + atComponent);
        return atComponent;

    }

    public static String getAction(String com) {

        String action = com + ".addActionListener(new java.awt.event.ActionListener() {\n"
                + "@Override\n"
                + "public void actionPerformed(java.awt.event.ActionEvent evt) {\n"
                + com + "ActionPerformed(evt);\n}\n});";

        return action;

    }

    public static String actionPerformed(String comName, String functionContent) {

        String function
                = "public void " + comName + "ActionPerformed(ActionEvent event){\n"
                + functionContent
                + " \n}";

        return function;
    }

    public static void setTable(String fileName, Table1 table, Integer x, Integer y, Integer width, Integer height) {

        String tableCode = String.format(
                "scrollPane = new javax.swing.JScrollPane();\n"
                + "        table = new javax.swing.JTable();\n"
                + "        scrollPane.setBackground(new java.awt.Color(53,59,72));\n"
                + "        scrollPane.setForeground(new java.awt.Color(53,59,72));\n"
                + "        scrollPane.setAutoscrolls(true);\n"
                //                + "        scrollPane.setBackground(new java.awt.Color(73, 73, 73));\n"
                //                + "        scrollPane.setForeground(new java.awt.Color(153, 17, 255));\n"
                //                + "        scrollPane.setAutoscrolls(true);\n"
                + "         scrollPane.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));\n"
                + "        table.setBackground(new java.awt.Color(53,59,72));\n"
                + "         table.setFillsViewportHeight(true);\n"
                + "         table.setRowHeight(30);\n"
                + "         table.setShowHorizontalLines(true);\n"
                + "        table.setShowVerticalLines(true);\n"
                + "        table.setSurrendersFocusOnKeystroke(true);\n"
                + "         "
                + "        table.setFont(new java.awt.Font(\"sansserif\", 1, 20));\n"
                + "        table.setForeground(new java.awt.Color(255, 255, 255));\n"
                + "         table.setModel(TableMdf.getDefaultTableModel(" + Functions.getName(table.getTableName()) + "DaoImpl.get" + Functions.getJcName(table.getTableName()) + "AllRecords()));\n"
                +"          panel.add(scrollPane);\n"
                +           "scrollPane.setBounds("+x + "," + y + "," + width + "," + height + ");\n"
        );

        File1.writeFile(fileName, "import javax.swing.JTable;", "", tableCode + addMouseListener("table"), "JTable table;");

    }

    public static String addMouseListener(String comName) {

        return comName + ".addMouseListener(new java.awt.event.MouseAdapter() {\n"
                + "            public void mouseClicked(java.awt.event.MouseEvent evt) {\n"
                + "                " + comName + "MouseClicked(evt);\n"
                + "            }\n"
                + "        });\n"
                + "        scrollPane.setViewportView(table);";

    }

    public static String mouseClicked(String comName, String funContent) {
        return " private void " + comName + "MouseClicked(java.awt.event.MouseEvent evt) {\n" + funContent + "\n}";

    }

    public static String getLabel(Component1 com, String name, String funContent) {
        String lbl = "";
        String colName = Functions.getName(name).concat("lbl");

        lbl = String.format(""
                + colName + "=new JLabel();\n"
                + colName + ".setText(\"" + com.getCompHeading() + "\");\n"
                + colName + ".setBounds(" + (com.getX() - 200) + "," + (com.getY()) + ","
                + com.getCompSizeX() + "," + com.getCompSizeY() + ");\n"
                + colName + ".setFont(new java.awt.Font(\"sansserif\", 1, 20));\n"
                + colName + ".setForeground(new java.awt.Color(255, 255, 255));\n"
                + colName + ".setBackground(new java.awt.Color(" + com.getBgColor() + "));\n"
                + "panel.add(" + colName
                + ",new org.netbeans.lib.awtextra.AbsoluteConstraints(" + (com.getX() - 200) + "," + (com.getY()) + ","
                + com.getCompSizeX() + "," + com.getCompSizeY() + "));\n");

        return lbl;
    }

    public static String getButton(Component1 com, String imagePath) {
        String btn = "";
        String colName = Functions.getName(com.getComName());
        btn = String.format(""
                + colName + "=new JButton();\n"
                + colName + ".setText(\"" + com.getCompHeading() + "\");\n"
                + colName + ".setBounds(" + com.getX() + "," + com.getY() + ","
                + com.getCompSizeX() + "," + com.getCompSizeY() + ");\n"
                + colName + ".setFont(new java.awt.Font(\"sansserif\", 1, 20));\n"
                + colName + ".setIcon(new ImageIcon(getClass().getResource(\"" + imagePath + "\")));\n"
                + colName + ".setBorder(\n"
                + "new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED,\n"
                + "new java.awt.Color(153, 153, 153),\n"
                + "new java.awt.Color(153, 153, 153), null, null));\n"
                + colName + ".setForeground(new java.awt.Color(73, 73, 73));\n"
                + "panel.add(" + colName
                + ",new org.netbeans.lib.awtextra.AbsoluteConstraints(" + com.getX() + "," + com.getY() + ","
                + com.getCompSizeX() + "," + com.getCompSizeY() + "));"
                + ""
                + getAction(colName)
                + "\n");

        return btn;

    }

    public static String getButton(String btnName, String btnLabel, String imagePath) {
        String btn = String.format(
                btnName + " =new JButton();\n"
                + btnName + ".setBackground(new java.awt.Color(102, 102, 102));\n"
                + btnName + ".setFont(new java.awt.Font(\"sansserif\", 1, 18)); // NOI18N\n"
                + btnName + ".setIcon(new javax.swing.ImageIcon(getClass().getResource(\"" + imagePath + "\"))); // hero put random image here _-_\n"
                + btnName + ".setText(\"" + btnLabel + "\");\n"
                + btnName + ".setToolTipText(\"" + btnLabel + "\");\n"
                + btnName + ".addActionListener(new java.awt.event.ActionListener() {\n"
                + "public void actionPerformed(java.awt.event.ActionEvent evt) {\n"
                + btnName + "ActionPerformed(evt);\n"
                + "}\n"
                + "});\n");

        return btn;
    }

    public static String addKeyListenerWithKeyReleased(String compName) {
        return compName + ".addKeyListener(new java.awt.event.KeyAdapter() {\n"
                + "public void keyReleased(java.awt.event.KeyEvent evt){\n"
                + compName + "KeyReleased(evt);\n"
                + "}\n"
                + "});\n";
    }

    public static String addKeyListenerWithKeyPressed(String compName) {
        return compName + ".addKeyListener(new java.awt.event.KeyAdapter() {\n"
                + "public void keyPressed(java.awt.event.KeyEvent evt) {\n"
                + compName + "KeyPressed(evt);\n"
                + "}\n"
                + "});";
    }

    private static String getKeyPressedFunction(String comName, String functionContent) {

        return String.format(
                "public void " + comName + "KeyPressed(java.awt.event.KeyEvent event){\n"
                + functionContent + "\n"
                + "}\n");

    }

    private static String getKeyReleasedFunction(String comName, String functionContent) {

        return String.format(
                "public void " + comName + "KeyReleased(java.awt.event.KeyEvent event){\n"
                + functionContent + "\n"
                + "}\n");

    }

}
