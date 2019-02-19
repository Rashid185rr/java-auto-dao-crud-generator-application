/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame.jdbs;

import database.Database;
import database.TableDB;
import javax.swing.DefaultListModel;
import database.connect.ConnectDB;
import database.connect.ConnectDBs;
import database.connect.DB;
import database.table.Table1;
import fr.AppInfo;
import fr.FrameInfo;
import function.AddFunctions;
import java.awt.Cursor;
import java.awt.Image;
import java.io.File;
//import java.io.FileFilter;
import java.net.URL;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileFilter;
import main.DaoPattern;
import main.Functions;
import utility.Query;
import function.AddFunctions;
import genproject.NetBeansFile;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author tahir hussain
 */
public class MySQLFrame extends javax.swing.JFrame {

    /**
     * Creates new form MySQLFrame
     */
    File sqlFile;
    Database database;
    List<Database> dbList;
    String newProjPath;
    String thisProjPath;

    public MySQLFrame() {
        super("MySQL");
        dbList = new ArrayList<>();
        database = new Database(DB.MySQL, "com.mysql.jdbc.Driver", "jdbs", "jdbc:mysql://localhost:3306", "root", "");
        newProjPath = System.getProperty("user.home").replace("\\", "/") + "/JDBsProject";
        thisProjPath = new File("MySQLFrame.java").getAbsolutePath().replace("\\", "/");
        thisProjPath = thisProjPath.replace("/MySQLFrame.java", "");
        //"C:/Users/tahir hussain/NewProject/JDBS/src/nbfiles/
        System.out.println("New Project Path:---->" + newProjPath);
        System.out.println("This Project Path:---->" + thisProjPath);

        System.out.println("initializing database");
        initComponents();

        newProjPathLBL.setText(newProjPath);

        // bgLBL.setIcon(getResizedImageIcon(getClass().getResource("/images/tower.jpg")));
        //setBackground(new Color(0,0,0,50));
        executeBar.setVisible(false);
        fillLabel();

    }
     
    public MySQLFrame(Database database) {
        super("MySQL");
        dbList = new ArrayList<>();
        this.database = database;
        initComponents();
        //bgLBL.setIcon(getResizedImageIcon(getClass().getResource("/images/tower.jpg")));
        executeBar.setVisible(false);
        fillLabel();
    }

    /*
    public ImageIcon getResizedImageIcon(URL url) {
        ImageIcon icon = new ImageIcon(url);
        Image image = icon.getImage();
        System.out.println(bgLBL.getWidth() + "  " + bgLBL.getHeight());
        image = image.getScaledInstance(bgLBL.getWidth(), bgLBL.getHeight(), Image.SCALE_SMOOTH);
        icon.setImage(image);
        return icon;
    }
     */
    public void fillLabel() {

        String[] dbNames = Query.getDBNames();
        DefaultListModel<String> list = new DefaultListModel<>();

        for (String as : dbNames) {
            list.addElement(String.format(as + " \n"));
        }
        databaseList.setModel(list);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sqlLBL = new javax.swing.JLabel();
        executeBtn = new javax.swing.JButton();
        userNameTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        sqlPathLBL1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        softwareName = new javax.swing.JTextField();
        newProjPathLBL = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        databaseList = new javax.swing.JList<>();
        selectAllRd = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        pwdTxt = new javax.swing.JPasswordField();
        savePathBtn = new javax.swing.JButton();
        executeBar = new javax.swing.JProgressBar();
        createBtn = new javax.swing.JButton();
        customizeBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 600));
        setSize(new java.awt.Dimension(3200, 1600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sqlLBL.setBackground(new java.awt.Color(53, 59, 72));
        sqlLBL.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        sqlLBL.setForeground(new java.awt.Color(255, 255, 255));
        sqlLBL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frame/newsoftframe/images/add/db (1).png"))); // NOI18N
        sqlLBL.setText("SQL File");
        sqlLBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sqlLBLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sqlLBLMouseEntered(evt);
            }
        });
        getContentPane().add(sqlLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(1730, 400, -1, 60));

        executeBtn.setBackground(new java.awt.Color(87, 101, 116));
        executeBtn.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        executeBtn.setForeground(new java.awt.Color(255, 255, 255));
        executeBtn.setText("Execute");
        executeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeBtnActionPerformed(evt);
            }
        });
        getContentPane().add(executeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1720, 460, 140, 40));

        userNameTxt.setBackground(new java.awt.Color(53, 59, 72));
        userNameTxt.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        userNameTxt.setForeground(new java.awt.Color(189, 195, 199));
        userNameTxt.setText("root");
        userNameTxt.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true), javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(102, 102, 102))));
        getContentPane().add(userNameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, 260, 50));

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("User Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 180, 50));

        sqlPathLBL1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        sqlPathLBL1.setForeground(new java.awt.Color(255, 255, 255));
        sqlPathLBL1.setText("sql file path...");
        getContentPane().add(sqlPathLBL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 320, 330, 50));

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, 180, 50));

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Software Name");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 180, 50));

        softwareName.setEditable(false);
        softwareName.setBackground(new java.awt.Color(53, 59, 72));
        softwareName.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        softwareName.setForeground(new java.awt.Color(189, 195, 199));
        softwareName.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true), javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(102, 102, 102))));
        getContentPane().add(softwareName, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, 260, 50));

        newProjPathLBL.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        newProjPathLBL.setForeground(new java.awt.Color(255, 255, 255));
        newProjPathLBL.setText("save your file...");
        newProjPathLBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newProjPathLBLMouseClicked(evt);
            }
        });
        getContentPane().add(newProjPathLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 980, 450, 50));

        databaseList.setBackground(new java.awt.Color(53, 59, 72));
        databaseList.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        databaseList.setForeground(new java.awt.Color(189, 195, 199));
        databaseList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        databaseList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        databaseList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                databaseListMouseClicked(evt);
            }
        });
        databaseList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                databaseListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(databaseList);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 600, 180, 370));

        selectAllRd.setBackground(new java.awt.Color(53, 59, 72));
        selectAllRd.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        selectAllRd.setForeground(new java.awt.Color(255, 255, 255));
        selectAllRd.setText("Select All");
        selectAllRd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllRdActionPerformed(evt);
            }
        });
        getContentPane().add(selectAllRd, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 550, 250, 40));

        jPanel1.setBackground(new java.awt.Color(53, 59, 72));
        jPanel1.setForeground(new java.awt.Color(127, 140, 141));
        jPanel1.setMinimumSize(new java.awt.Dimension(1024, 1024));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1200));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pwdTxt.setBackground(new java.awt.Color(53, 59, 72));
        pwdTxt.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        pwdTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwdTxtActionPerformed(evt);
            }
        });
        jPanel1.add(pwdTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 410, 260, 50));

        savePathBtn.setBackground(new java.awt.Color(87, 101, 116));
        savePathBtn.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        savePathBtn.setForeground(new java.awt.Color(255, 255, 255));
        savePathBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frame/newsoftframe/images/file/folder.png"))); // NOI18N
        savePathBtn.setText("CHANGE PATH");
        savePathBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePathBtnActionPerformed(evt);
            }
        });
        jPanel1.add(savePathBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 980, 280, 50));

        executeBar.setBackground(new java.awt.Color(53, 59, 72));
        executeBar.setForeground(new java.awt.Color(0, 0, 153));
        jPanel1.add(executeBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 850, 380, 60));

        createBtn.setBackground(new java.awt.Color(87, 101, 116));
        createBtn.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        createBtn.setForeground(new java.awt.Color(255, 255, 255));
        createBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frame/newsoftframe/images/add/java (1).png"))); // NOI18N
        createBtn.setText("CREATE");
        createBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBtnActionPerformed(evt);
            }
        });
        jPanel1.add(createBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1700, 980, 170, 40));

        customizeBtn.setBackground(new java.awt.Color(87, 101, 116));
        customizeBtn.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        customizeBtn.setForeground(new java.awt.Color(255, 255, 255));
        customizeBtn.setText("CUTOMIZE");
        customizeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customizeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(customizeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1520, 980, 170, 40));

        backBtn.setBackground(new java.awt.Color(87, 101, 116));
        backBtn.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frame/newsoftframe/images/back/back (6).png"))); // NOI18N
        backBtn.setText("BACK");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        jPanel1.add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 980, 170, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1200));

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 67)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MySQL");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 250, 130));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sqlLBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sqlLBLMouseClicked
        sqlFile = getSQLFile();
        if (sqlFile != null) {
            executeBtn.setToolTipText("Create Database");
        }
        System.out.println();

    }//GEN-LAST:event_sqlLBLMouseClicked


    private void savePathBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePathBtnActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        int in = fileChooser.showSaveDialog(null);
        
        if (in == JFileChooser.APPROVE_OPTION) {
            if (fileChooser.getSelectedFile().isDirectory()) {
                newProjPath = fileChooser.getSelectedFile().getAbsolutePath();
                if (newProjPath != null) {
                    newProjPathLBL.setText(newProjPath);
                }
            }
        }
    }//GEN-LAST:event_savePathBtnActionPerformed
    private void createBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBtnActionPerformed
//"C:/Users/tahir hussain/Documents/JDBC1/"+
        int clickValue = -1;
        System.out.println(dbList);
        if (dbList.size() > 0) {
//            InformUser us = new InformUser("Creating Java App", "It will All Tables and Columns",
//                    "/frame/newsoftframe/images/information/info (1).png",
//                    "/frame/newsoftframe/images/cancel/can.png",
//                    "/frame/newsoftframe/images/ok/ok.png");
//            us.setVisible(true);
//            clickValue = us.getClickValue();
//                    System.out.println("Click Value:"+clickValue);
            clickValue = JOptionPane.showConfirmDialog(null, "It will create all Tables and Columns of the ");
            if (clickValue == 0) {
                System.out.println("dbList:" + dbList);
                
                for (Database db : dbList) {
                    assignTablesToDB(db);
                    if(db.getTables()!=null){
                        //default frame
                        List<FrameInfo> frameList=new ArrayList();
                        Table1[] table=db.getTables();
                        for(int i=0; i<table.length; i++){
                            frameList.add(new FrameInfo(table[i]));
                        }
                        AppInfo newAppInfo=new AppInfo(newProjPath,Functions.getJcName(db.getDbName()),frameList);
                        System.out.println("Database -------- Name:"+db.getDbName());
                    DaoPattern.createDaoPattern(new AppInfo(thisProjPath,"jdbs","JDBS"),newAppInfo, db);
                    AddFunctions.addFunction(newAppInfo, db);
                    System.out.println("-----=-=-=-=-=------------->"+thisProjPath+"/src/nbfiles/");
                    NetBeansFile.copyNetbeansFile(thisProjPath+"/src/nbfiles/", newProjPath, Functions.getJcName(db.getDbName()));
                    //C:\Users\tahir hussain\Documents\CrudeProjects\Sakila
                    //File newProjectFile=new File("C:/Users/tahir hussain/Documents/JDBC1/Sakila");
                    softwareName.setText(Functions.getJcName(db.getDbName()));
                    }else{
                        System.out.println("No tables");
                    }
                    
                    }
          dbList.clear();
            } else {

            }
            executeBar.setVisible(false);
        }


    }//GEN-LAST:event_createBtnActionPerformed

   
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed

    }//GEN-LAST:event_backBtnActionPerformed

    private void executeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeBtnActionPerformed

        if (sqlFile != null) {
            //DB.MySQL, "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306", "jdbs", "root", "");
            if (accept(userNameTxt) && accept(pwdTxt)) {
                database.setUserName(userNameTxt.getText());
                database.setPassword(pwdTxt.getText());
            }
            database.setDbName("");
            executeBar.setVisible(true);
            System.out.println("checking database: ----------> " + database);
            String dbName = Query.executeScriptFile(database, sqlFile);
//            dbName = Functions.getJcName(dbName);
            database.setDbName(dbName);
            //String dbName=Query.executeScriptFile(database, new File("C:/Users/tahir hussain/Desktop/geekpos.sql"));
            executeBar.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (dbName != null && !dbName.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Database " + sqlFile.getName() + " has been created");
            }
            System.out.println("PROJECT NAME " + dbName);
            softwareName.setEditable(true);

            softwareName.setText(Functions.getJcName(dbName));
            dbList.add(new Database(DB.MySQL, "com.mysql.jdbc.Driver", dbName, "jdbc:mysql://localhost:3306", "root", ""));

        } else {
            //   JOptionPane.showMessageDialog(null,"");
            executeBtn.setToolTipText("Insert SQL File...");
        }

    }//GEN-LAST:event_executeBtnActionPerformed

    private void sqlLBLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sqlLBLMouseEntered
//        sqlFile = getSQLFile();
//        if (sqlFile == null) {
//            throw new NullPointerException("File is null");
//        }

//setting databse driver 

    }//GEN-LAST:event_sqlLBLMouseEntered

    private void newProjPathLBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newProjPathLBLMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_newProjPathLBLMouseClicked

    private void selectAllRdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllRdActionPerformed
        if (selectAllRd.isSelected()) {
            DefaultListModel<String> list = (DefaultListModel) databaseList.getModel();
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No Database");
            } else {
                String[] obj = new String[list.size()];
                int len = list.size();
                for (int i = 0; i < len; i++) {
                    obj[i] = list.get(i).trim();
                }
                for (int i = 0; i < len; i++) {
                    if (!dbList.contains(obj[i])) {
                        System.out.println("==============            ==========>"+obj[i]);
                        dbList.add(new Database(DB.MySQL, "com.mysql.jdbc.Driver", obj[i++], "jdbc:mysql://localhost:3306", "root", ""));
                    }
                }
            }
            System.out.println(dbList);
        }

    }//GEN-LAST:event_selectAllRdActionPerformed

    private void databaseListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_databaseListValueChanged
        System.out.println(databaseList.getSelectedValue());
        dbList = new ArrayList<>();
        if (!databaseList.isSelectionEmpty()) {
            List<String> list = databaseList.getSelectedValuesList();
            int len = list.size();
            System.out.println("list-->" + list.size());
            for(int i = 0; i<len; i++) {
                if (!dbList.contains(list.get(i).trim())) {
                    dbList.add(new Database(DB.MySQL, "com.mysql.jdbc.Driver", list.get(i).trim(), "jdbc:mysql://localhost:3306", "root", ""));
                    softwareName.setText(Functions.getJcName(list.get(i).trim()));
                }
                System.out.println(">>>");
            }
            System.out.println("--->" + dbList);
        }
    }//GEN-LAST:event_databaseListValueChanged
    /*
    public static Connection getConnection(String dbName) {
        System.out.println("Connection1");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, "root", "");
            ResultSet rs = con.prepareStatement("SELECT * FROM  film").executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }*/
    private void databaseListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_databaseListMouseClicked


    }//GEN-LAST:event_databaseListMouseClicked

    private void customizeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customizeBtnActionPerformed
        int length = dbList.size();
        System.out.println("------->" + length);
        if (length > 0) {

            for (Database db : dbList) {
                if (db.getTables() == null) {
                    assignTablesToDB(db);
                }
                
                System.out.println(softwareName.getText());
                if (db.getTables() != null) {
                    System.out.println("\n\n\n\n\n\n\n\n\n");
                    
                    new TableFrame1(new AppInfo(thisProjPath,"JDBS","JDBS"),new AppInfo(newProjPath,db.getDbName(),softwareName.getText()),db).setVisible(true);
                    this.setVisible(false);
                }
            }
        }
    }//GEN-LAST:event_customizeBtnActionPerformed

    private void pwdTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwdTxtActionPerformed

    }//GEN-LAST:event_pwdTxtActionPerformed

    private boolean accept(JTextField field) {
        if (field.getText() == null || field.getText().trim().equals("")) {
            return false;
        }
        return true;
    }

    public File getSQLFile() {

        JFileChooser fileChooser = new JFileChooser("C:", FileSystemView.getFileSystemView());
        int ap = fileChooser.showOpenDialog(null);

        FileFilter filter = new FileFilter() {
            File file;

            @Override
            public boolean accept(File file) {
                this.file = file;
                if (file == null) {
                    return false;
                }
                if (file.isDirectory()) {
                    return false;
                }
                if (file.getName().endsWith(".sql") || file.getName().endsWith(".txt")) {
                    return true;
                }
                return false;
            }

            @Override
            public String getDescription() {
                return ".sql or .txt file";
            }
        };
        // filter.accept(new File(".sql"));
        fileChooser.setFileFilter(filter);
        fileChooser.addChoosableFileFilter(filter);
        if (ap == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    } public void assignTablesToDB(Database db) {
        executeBar.setVisible(true);
        System.out.println("-->" + db.getDbName());
        try {
            Connection con = null;
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getURL() + "/" + db.getDbName(), db.getUserName(), db.getPassword());
            DatabaseMetaData meta = con.getMetaData();
            ResultSet rs = meta.getTables(null, null, null, null);
            String table = "";
            int i = 0;

            while (rs.next()) {
                table += "," + rs.getString(3);
                System.out.println(table);
                i++;
            }
            System.out.println(table);
            rs.close();
            con.close();
            table = table.replaceFirst(",", "");
            if (table != null && !table.equals("")) {
                System.out.println("dbname--->" + db.getDbName());
                db.setTables(TableDB.getTables(db.getDbName(), table.split(",")));

            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        executeBar.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MySQLFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MySQLFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MySQLFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MySQLFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MySQLFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton createBtn;
    private javax.swing.JButton customizeBtn;
    private javax.swing.JList<String> databaseList;
    private javax.swing.JProgressBar executeBar;
    private javax.swing.JButton executeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel newProjPathLBL;
    private javax.swing.JPasswordField pwdTxt;
    private javax.swing.JButton savePathBtn;
    private javax.swing.JRadioButton selectAllRd;
    private javax.swing.JTextField softwareName;
    private javax.swing.JLabel sqlLBL;
    private javax.swing.JLabel sqlPathLBL1;
    private javax.swing.JTextField userNameTxt;
    // End of variables declaration//GEN-END:variables
}
