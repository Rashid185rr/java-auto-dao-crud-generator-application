/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame.jdbs;

/* I almost took too much time(1 week)-_- to bring it to conclusion state. You must do it with your own logic.
   34% project time given to this trifle class, neverthless you put your own logic first if it doesn't work for you.
   I get bored whenever I move cursor to this file. 
   Finally done at Sunday,12:17 am , 17 February 2019

 */

import database.Database;
import database.table.Column1;
import database.table.Table1;
import fr.AppInfo;
import fr.FrameInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 *
 *
 * @author tahir hussain
 */
public class TableFrame1 extends javax.swing.JFrame {

    /**
     * Creates new form TableFrame1
     */
    private boolean[][] buttons;

    int btnTableIndex;
    Database database;
    Table1[] table;
    AppInfo thisAppInfo;
    AppInfo newAppInfo;
    String appName;
    String[] colName;
    int tableIndex;
    FrameInfo frame;
    LinkedList<Table1> selectedTables;
    LinkedList<Column1> unselectedColumns;
    LinkedList<Column1> selectedColumns;

    DefaultListModel<String> from;
    DefaultListModel<String> to;

    public TableFrame1() {
        initComponents();
        table = null;
        //  columnIndex=0;
        frame = new FrameInfo();

        from = new DefaultListModel<>();
        to = new DefaultListModel<>();

        selectedColumns = new LinkedList<>();
        selectedTables = new LinkedList();
        //  bgLBL.setIcon(getResizedImageIcon(getClass().getResource("/images/tower.jpg")));
        setValues();
    }

    public TableFrame1(AppInfo thisAppInfo, AppInfo newAppInfo, Database database) {
        initComponents();
        this.thisAppInfo = thisAppInfo;

        this.newAppInfo = newAppInfo;

        System.out.println("New App --->" + newAppInfo);
        appNameTxt.setText(newAppInfo.getAppTitle());
        this.database = database;
        this.table = database.getTables();

        frame = new FrameInfo();
        frame.setTitle(appName);

        from = new DefaultListModel<>();
        to = new DefaultListModel<>();
        
        selectedTables = new LinkedList();
        selectedColumns = new LinkedList();
        unselectedColumns = new LinkedList();
        buttons = new boolean[table.length][8];
        btnTableIndex = 0;
        setValues();

    }
//
//    public void setBtn(){
//        //default value --> false
//        buttons=new boolean[table.length][8];
//        for(int i=0; i<buttons.length; i++){
//            for(int j=0; j<buttons[0].length; j++){
//                buttons[i][j]=false;
//            }
//        }
//        
//    }

    /*
    public TableFrame1(String appName, Table1[] table) {
        initComponents();
        this.table = table;
        if (table != null) {
//            System.out.println("Selected tables:" + table.length);
        }

        frame = new FrameInfo();
        frame.setTitle(appName);
        frameList = new LinkedList<>();
        from = new DefaultListModel<>();
        to = new DefaultListModel<>();

        appNameTxt.setText(appName);

        selectedTables = new LinkedList();
        selectedColumns = new LinkedList();
        unselectedColumns = new LinkedList();
        //   bgLBL.setIcon(getResizedImageIcon(getClass().getResource("/images/tower.jpg")));
        setValues();
    }
     */
    public TableFrame1(Database database) {
        initComponents();
        this.table = database.getTables();
        this.database = database;
        frame = new FrameInfo();

        from = new DefaultListModel<>();
        to = new DefaultListModel<>();
        selectedTables = new LinkedList();
        selectedColumns = new LinkedList();
        unselectedColumns = new LinkedList();
        //  bgLBL.setIcon(getResizedImageIcon(getClass().getResource("/images/tower.jpg")));
        setValues();
    }

    public DefaultListModel setFromModel(Table1 table) {
        from = new DefaultListModel<>();
        Column1[] columns = table.getColumn();
        List<String> itemList = new LinkedList<>();
        for (Column1 col : columns) {
            from.addElement(col.getColName());
        }
        return from;

    }

    public DefaultListModel setToModel(Table1 table) {
        to = new DefaultListModel<>();
        if (selectedTables.size() > 0 && selectedTables.contains(table)) {
            Column1[] columns = selectedTables.get(tableIndex).getColumn();
            for (Column1 col : columns) {
                to.addElement(col.getColName());
            }
        }
        return to;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        tableNameLBL = new javax.swing.JLabel();
        tabPane = new javax.swing.JTabbedPane();
        tableTab1 = new javax.swing.JPanel();
        selectedScroll = new javax.swing.JScrollPane();
        selectedList = new javax.swing.JList<>();
        allColumnRd1 = new javax.swing.JRadioButton();
        nextTable = new javax.swing.JButton();
        prevTable = new javax.swing.JButton();
        unselectedScroll = new javax.swing.JScrollPane();
        unselectedList = new javax.swing.JList<>();
        removeBtn = new javax.swing.JLabel();
        addBtn = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tableTab2 = new javax.swing.JPanel();
        allBtnRD = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        insertChk1 = new javax.swing.JCheckBox();
        deleteChk1 = new javax.swing.JCheckBox();
        updateChk1 = new javax.swing.JCheckBox();
        viewChk1 = new javax.swing.JCheckBox();
        cancelChk1 = new javax.swing.JCheckBox();
        nextChk1 = new javax.swing.JCheckBox();
        backChk1 = new javax.swing.JCheckBox();
        searchChk1 = new javax.swing.JCheckBox();
        appLBL = new javax.swing.JLabel();
        appNameTxt = new javax.swing.JTextField();
        backBtn = new javax.swing.JButton();
        appBuilderBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Table");
        setAlwaysOnTop(true);
        setFocusTraversalPolicyProvider(true);
        setMaximumSize(new java.awt.Dimension(1920, 1200));
        setMinimumSize(new java.awt.Dimension(1520, 1200));
        setPreferredSize(new java.awt.Dimension(1920, 1200));
        setSize(new java.awt.Dimension(1920, 1200));

        jScrollPane2.setMaximumSize(new java.awt.Dimension(1920, 1200));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(1500, 1225));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(1920, 1200));

        jPanel1.setBackground(new java.awt.Color(53, 59, 72));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1920, 1200));
        jPanel1.setMinimumSize(new java.awt.Dimension(1400, 1200));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1200));

        tableNameLBL.setBackground(new java.awt.Color(204, 204, 204));
        tableNameLBL.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        tableNameLBL.setForeground(new java.awt.Color(255, 255, 255));
        tableNameLBL.setText("Table Name");

        tabPane.setBackground(new java.awt.Color(53, 59, 72));
        tabPane.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        tabPane.setMinimumSize(new java.awt.Dimension(1024, 900));
        tabPane.setOpaque(true);

        tableTab1.setBackground(new java.awt.Color(53, 59, 72));
        tableTab1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        tableTab1.setMaximumSize(new java.awt.Dimension(1400, 800));
        tableTab1.setMinimumSize(new java.awt.Dimension(1024, 1024));
        tableTab1.setPreferredSize(new java.awt.Dimension(873, 417));

        selectedScroll.setBackground(new java.awt.Color(53, 59, 72));

        selectedList.setBackground(new java.awt.Color(53, 59, 72));
        selectedList.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        selectedList.setForeground(new java.awt.Color(255, 255, 255));
        selectedList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                selectedListValueChanged(evt);
            }
        });
        selectedScroll.setViewportView(selectedList);

        allColumnRd1.setBackground(new java.awt.Color(53, 59, 72));
        allColumnRd1.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        allColumnRd1.setForeground(new java.awt.Color(255, 255, 255));
        allColumnRd1.setText("Select All");
        allColumnRd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allColumnRd1ActionPerformed(evt);
            }
        });

        nextTable.setBackground(new java.awt.Color(87, 101, 116));
        nextTable.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        nextTable.setForeground(new java.awt.Color(255, 255, 255));
        nextTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frame/newsoftframe/images/next/next (2).png"))); // NOI18N
        nextTable.setText("NEXT TABLE");
        nextTable.setHideActionText(true);
        nextTable.setIconTextGap(5);
        nextTable.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/frame/newsoftframe/images/next/next (3).png"))); // NOI18N
        nextTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextTableActionPerformed(evt);
            }
        });

        prevTable.setBackground(new java.awt.Color(87, 101, 116));
        prevTable.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        prevTable.setForeground(new java.awt.Color(255, 255, 255));
        prevTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frame/newsoftframe/images/back/back (6).png"))); // NOI18N
        prevTable.setText("PREVIOUS TABLE");
        prevTable.setAutoscrolls(true);
        prevTable.setFocusCycleRoot(true);
        prevTable.setIconTextGap(5);
        prevTable.setOpaque(true);
        prevTable.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/frame/newsoftframe/images/back/back (4).png"))); // NOI18N
        prevTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevTableActionPerformed(evt);
            }
        });

        unselectedScroll.setBackground(new java.awt.Color(53, 59, 72));

        unselectedList.setBackground(new java.awt.Color(53, 59, 72));
        unselectedList.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        unselectedList.setForeground(new java.awt.Color(255, 255, 255));
        unselectedList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                unselectedListValueChanged(evt);
            }
        });
        unselectedScroll.setViewportView(unselectedList);

        removeBtn.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        removeBtn.setForeground(new java.awt.Color(255, 255, 255));
        removeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frame/newsoftframe/images/back/back (4).png"))); // NOI18N
        removeBtn.setText("REMOVE");
        removeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeBtnMouseClicked(evt);
            }
        });

        addBtn.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frame/newsoftframe/images/next/next (3).png"))); // NOI18N
        addBtn.setText("ADD");
        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addBtnMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("SELECTED COLUMNS");

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("UNSELECTED COLUMNS");

        javax.swing.GroupLayout tableTab1Layout = new javax.swing.GroupLayout(tableTab1);
        tableTab1.setLayout(tableTab1Layout);
        tableTab1Layout.setHorizontalGroup(
            tableTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tableTab1Layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(tableTab1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(tableTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prevTable, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tableTab1Layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(unselectedScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tableTab1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(allColumnRd1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(tableTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(selectedScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(nextTable, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        tableTab1Layout.setVerticalGroup(
            tableTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tableTab1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(tableTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(tableTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tableTab1Layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(prevTable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(unselectedScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tableTab1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(allColumnRd1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tableTab1Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(removeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(selectedScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tableTab1Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(nextTable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        tabPane.addTab("Table", new javax.swing.ImageIcon(getClass().getResource("/frame/newsoftframe/images/add/db (1).png")), tableTab1); // NOI18N

        tableTab2.setBackground(new java.awt.Color(53, 59, 72));
        tableTab2.setForeground(new java.awt.Color(255, 255, 255));
        tableTab2.setPreferredSize(new java.awt.Dimension(1458, 673));
        tableTab2.setLayout(null);

        allBtnRD.setBackground(new java.awt.Color(53, 59, 72));
        allBtnRD.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        allBtnRD.setForeground(new java.awt.Color(255, 255, 255));
        allBtnRD.setText("Select All");
        allBtnRD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allBtnRDMouseClicked(evt);
            }
        });
        allBtnRD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allBtnRDActionPerformed(evt);
            }
        });
        tableTab2.add(allBtnRD);
        allBtnRD.setBounds(30, 160, 220, 50);

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Buttons");
        tableTab2.add(jLabel2);
        jLabel2.setBounds(20, 60, 364, 73);

        insertChk1.setBackground(new java.awt.Color(53, 59, 72));
        insertChk1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        insertChk1.setForeground(new java.awt.Color(255, 255, 255));
        insertChk1.setText("INSERT");
        insertChk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertChk1ActionPerformed(evt);
            }
        });
        tableTab2.add(insertChk1);
        insertChk1.setBounds(70, 210, 158, 42);

        deleteChk1.setBackground(new java.awt.Color(53, 59, 72));
        deleteChk1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        deleteChk1.setForeground(new java.awt.Color(255, 255, 255));
        deleteChk1.setText("DELETE");
        deleteChk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteChk1ActionPerformed(evt);
            }
        });
        tableTab2.add(deleteChk1);
        deleteChk1.setBounds(70, 250, 158, 42);

        updateChk1.setBackground(new java.awt.Color(53, 59, 72));
        updateChk1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        updateChk1.setForeground(new java.awt.Color(255, 255, 255));
        updateChk1.setText("UPDATE");
        updateChk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateChk1ActionPerformed(evt);
            }
        });
        tableTab2.add(updateChk1);
        updateChk1.setBounds(70, 290, 158, 42);

        viewChk1.setBackground(new java.awt.Color(53, 59, 72));
        viewChk1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        viewChk1.setForeground(new java.awt.Color(255, 255, 255));
        viewChk1.setText("VIEW");
        viewChk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewChk1ActionPerformed(evt);
            }
        });
        tableTab2.add(viewChk1);
        viewChk1.setBounds(70, 330, 158, 42);

        cancelChk1.setBackground(new java.awt.Color(53, 59, 72));
        cancelChk1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        cancelChk1.setForeground(new java.awt.Color(255, 255, 255));
        cancelChk1.setText("CANCEL");
        cancelChk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelChk1ActionPerformed(evt);
            }
        });
        tableTab2.add(cancelChk1);
        cancelChk1.setBounds(70, 370, 158, 42);

        nextChk1.setBackground(new java.awt.Color(53, 59, 72));
        nextChk1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        nextChk1.setForeground(new java.awt.Color(255, 255, 255));
        nextChk1.setText("NEXT");
        nextChk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextChk1ActionPerformed(evt);
            }
        });
        tableTab2.add(nextChk1);
        nextChk1.setBounds(70, 450, 158, 42);

        backChk1.setBackground(new java.awt.Color(53, 59, 72));
        backChk1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        backChk1.setForeground(new java.awt.Color(255, 255, 255));
        backChk1.setText("BACK");
        backChk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backChk1ActionPerformed(evt);
            }
        });
        tableTab2.add(backChk1);
        backChk1.setBounds(70, 410, 158, 42);

        searchChk1.setBackground(new java.awt.Color(53, 59, 72));
        searchChk1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        searchChk1.setForeground(new java.awt.Color(255, 255, 255));
        searchChk1.setText("SEARCH");
        searchChk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchChk1ActionPerformed(evt);
            }
        });
        tableTab2.add(searchChk1);
        searchChk1.setBounds(70, 490, 158, 42);

        tabPane.addTab("Buttons", tableTab2);

        appLBL.setBackground(new java.awt.Color(53, 59, 72));
        appLBL.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        appLBL.setForeground(new java.awt.Color(255, 255, 255));
        appLBL.setText("Application Name");

        appNameTxt.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        appNameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appNameTxtActionPerformed(evt);
            }
        });

        backBtn.setBackground(new java.awt.Color(87, 101, 116));
        backBtn.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frame/newsoftframe/images/back/back (6).png"))); // NOI18N
        backBtn.setText("BACK");
        backBtn.setHideActionText(true);
        backBtn.setIconTextGap(5);
        backBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/frame/newsoftframe/images/back/back (4).png"))); // NOI18N
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        appBuilderBtn.setBackground(new java.awt.Color(87, 101, 116));
        appBuilderBtn.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        appBuilderBtn.setForeground(new java.awt.Color(255, 255, 255));
        appBuilderBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frame/newsoftframe/images/next/next (2).png"))); // NOI18N
        appBuilderBtn.setText("NEXT");
        appBuilderBtn.setHideActionText(true);
        appBuilderBtn.setIconTextGap(5);
        appBuilderBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/frame/newsoftframe/images/next/next (3).png"))); // NOI18N
        appBuilderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appBuilderBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(appLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(appNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(410, 410, 410)
                        .addComponent(tableNameLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(tabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(530, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(appBuilderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(250, 250, 250))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(appLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(appNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(97, 97, 97)
                .addComponent(tableNameLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backBtn)
                    .addComponent(appBuilderBtn))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectedListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_selectedListValueChanged

    }//GEN-LAST:event_selectedListValueChanged

    private void allColumnRd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allColumnRd1ActionPerformed
        if (allColumnRd1.isSelected()) {
            setAllValuesFrom(unselectedList, from, selectedList, to);
        } else {
            setAllValuesFrom(selectedList, to, unselectedList, from);
        }

    }//GEN-LAST:event_allColumnRd1ActionPerformed

    private void nextTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextTableActionPerformed
        next();
//        System.out.println(" selected tables-->" + Arrays.asList(selectedTables));
        if (tableIndex < table.length - 1) {
            tableIndex++;
        }
        setValues();
//set the button values of next table
        setButtons();

        allColumnRd1.setSelected(false);
    }//GEN-LAST:event_nextTableActionPerformed

    private void prevTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevTableActionPerformed
        selectedColumns.clear();
        to.clear();
        from.clear();
        unselectedColumns.clear();
        boolean bool = false;

        tableIndex--;
//        btnTableIndex--;
        if (tableIndex < 0) {
            tableIndex = 0;
        }

//        System.out.println("Table Index:" + tableIndex);
        tableNameLBL.setText(table[tableIndex].getTableName());
        int index = -1;
        for (Table1 t : selectedTables) {
            if (t.getTableName().equals(table[tableIndex].getTableName())) {
                bool = true;
                index++;
                break;
            }
            index++;
        }
        if (bool) {
            setButtons();
            Column1[] c = selectedTables.get(index).getColumn();
            label:
            for (Column1 col : table[tableIndex].getColumn()) {
                for (int i = 0; i < c.length; i++) {
                    if (col.equals(c[i])) {
                        selectedColumns.add(col);
                        to.addElement(col.getColName());
                        continue label;
                    }
                }
                unselectedColumns.add(col);
                from.addElement(col.getColName());
            }
        } else {
            System.out.println("adding to unselectedColumns ");
            for (Column1 col : table[tableIndex].getColumn()) {
                unselectedColumns.add(col);
                from.addElement(col.getColName());
//                unselectAllBtn();
            }
        }
        allColumnRd1.setSelected(false);

    }//GEN-LAST:event_prevTableActionPerformed


    private void unselectedListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_unselectedListValueChanged

    }//GEN-LAST:event_unselectedListValueChanged

    private void removeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeBtnMouseClicked
        setValuesFrom(selectedList, to, unselectedList, from);
    }//GEN-LAST:event_removeBtnMouseClicked

    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseClicked
        setValuesFrom(unselectedList, from, selectedList, to);
    }//GEN-LAST:event_addBtnMouseClicked

    private void allBtnRDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allBtnRDMouseClicked
        // doing it manually irritated me.
        // alternatives exist
        if (allBtnRD.isSelected()) {
            frame.setBackBtn(true);
            frame.setDeleteBtn(true);
            frame.setInsertBtn(true);
            frame.setIsTable(true);
            frame.setNextBtn(true);
            frame.setUpdateBtn(true);
            frame.setViewBtn(true);
            insertChk1.setSelected(true);
            insertChk1.setEnabled(false);
            updateChk1.setSelected(true);
            updateChk1.setEnabled(false);
            backChk1.setSelected(true);
            backChk1.setEnabled(false);
            nextChk1.setEnabled(false);
            nextChk1.setSelected(true);
            viewChk1.setEnabled(false);
            viewChk1.setSelected(true);
            deleteChk1.setSelected(true);
            deleteChk1.setEnabled(false);
            cancelChk1.setSelected(true);
            cancelChk1.setEnabled(false);
            searchChk1.setSelected(true);
            searchChk1.setEnabled(false);
        } else {
            frame.setBackBtn(false);
            frame.setDeleteBtn(false);
            frame.setInsertBtn(false);
            frame.setIsTable(false);
            frame.setNextBtn(false);
            frame.setUpdateBtn(false);
            frame.setViewBtn(false);
            insertChk1.setSelected(false);
            insertChk1.setEnabled(true);
            updateChk1.setSelected(false);
            updateChk1.setEnabled(true);
            backChk1.setSelected(false);
            backChk1.setEnabled(true);
            nextChk1.setEnabled(true);
            nextChk1.setSelected(false);
            viewChk1.setEnabled(true);
            viewChk1.setSelected(false);
            deleteChk1.setSelected(false);
            deleteChk1.setEnabled(true);
            cancelChk1.setSelected(false);
            cancelChk1.setEnabled(true);
            searchChk1.setSelected(false);
            searchChk1.setEnabled(true);
        }
    }//GEN-LAST:event_allBtnRDMouseClicked

    private void insertChk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertChk1ActionPerformed
        if (insertChk1.isSelected()) {
            buttons[tableIndex][0] = true;
        } else {
            buttons[tableIndex][0] = false;
        }

    }//GEN-LAST:event_insertChk1ActionPerformed

    private void deleteChk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteChk1ActionPerformed
        if (deleteChk1.isSelected()) {
            buttons[tableIndex][1] = true;
        } else {
            buttons[tableIndex][1] = false;
        }
    }//GEN-LAST:event_deleteChk1ActionPerformed

    private void updateChk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateChk1ActionPerformed
        if (updateChk1.isSelected()) {
            buttons[tableIndex][2] = true;
        } else {
            buttons[tableIndex][2] = false;
        }
    }//GEN-LAST:event_updateChk1ActionPerformed

    private void viewChk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewChk1ActionPerformed
        if (viewChk1.isSelected()) {
            buttons[tableIndex][3] = true;
        } else {
            buttons[tableIndex][3] = false;
        }
    }//GEN-LAST:event_viewChk1ActionPerformed

    private void cancelChk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelChk1ActionPerformed
        if (cancelChk1.isSelected()) {
            buttons[tableIndex][4] = true;
        } else {
            buttons[tableIndex][4] = false;
        }
    }//GEN-LAST:event_cancelChk1ActionPerformed

    private void nextChk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextChk1ActionPerformed
        if (nextChk1.isSelected()) {
            buttons[tableIndex][6] = true;
        } else {
            buttons[tableIndex][6] = false;
        }
    }//GEN-LAST:event_nextChk1ActionPerformed

    private void backChk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backChk1ActionPerformed

        if (backChk1.isSelected()) {
            buttons[tableIndex][5] = true;
        } else {
            buttons[tableIndex][5] = false;
        }
    }//GEN-LAST:event_backChk1ActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
       
        new MySQLFrame().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_backBtnActionPerformed

    private void appBuilderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appBuilderBtnActionPerformed

        next();
        allColumnRd1.setSelected(false);
        Table1[] t = selectedTables.toArray(new Table1[0]);
        List<Table1> unselected = new ArrayList<>();
        int indexx = 0;
        for (Table1 d : t) {
            if (!isSelected(d)) {
                unselected.add(d);
            }
        }
        database.setTables(t);
        List<FrameInfo> frameList = new ArrayList<>();
    //selected table Index
        int selIndex=0;        
//all tables 
        for (int i = 0; i < table.length; i++) {
            if (isSelected(table[i])) {
                FrameInfo frame = new FrameInfo();
                frame.setAppTitle(selectedTables.get(selIndex).getTableName());
                
                for (int j = 0; j < buttons[i].length; j++) {
                    switch (j) {
                        case 0:
                            frame.setInsertBtn(buttons[i][j]);
                            break;
                        case 1:
                            frame.setDeleteBtn(buttons[i][j]);
                            break;
                        case 2:
                            frame.setUpdateBtn(buttons[i][j]);
                            break;
                        case 3:
                            frame.setViewBtn(buttons[i][j]);
                            break;
                        case 4:
                            frame.setCancelBtn(buttons[i][j]);
                            break;
                        case 5:
                            frame.setBackBtn(buttons[i][j]);
                            break;
                        case 6:
                            frame.setNextBtn(buttons[i][j]);
                            break;
                        case 7:
                            frame.setSearchTxt(buttons[i][j]);
                            break;
                    
                    }
                }
                //default every frame must have at least one table
                frame.setTable(selectedTables.get(selIndex));
                frameList.add(frame);
            selIndex++;
            }
            System.out.println();
        }
        if(appNameTxt!=null && !appNameTxt.getText().trim().equals(""))
                newAppInfo.setAppTitle(appNameTxt.getText());
        newAppInfo.setFrameList(frameList);
//selected table also passed as an argument below constructor, frameList 
        new AppBuilder(thisAppInfo, newAppInfo, unselected.toArray(new Table1[0]), selectedTables.toArray(new Table1[0])).setVisible(true);
        setVisible(false);

//        System.out.println("" + newAppInfo.getAppTitle());
//       FrameInfo =newAppInfo.getFrameList();
//        System.out.println("Button values-----> ");

    }//GEN-LAST:event_appBuilderBtnActionPerformed

    public void setFrameBtn(int j, boolean bool) {

    }
    private void allBtnRDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allBtnRDActionPerformed

        if (allBtnRD.isSelected()) {
            setAllBtn(true);
        } else {
            setAllBtn(false);
        }


    }//GEN-LAST:event_allBtnRDActionPerformed

    private void searchChk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchChk1ActionPerformed
       
         if (searchChk1.isSelected()) {
            buttons[tableIndex][7] = true;
        } else {
            buttons[tableIndex][7] = false;
        }
        
    }//GEN-LAST:event_searchChk1ActionPerformed

    private void appNameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appNameTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_appNameTxtActionPerformed

    public boolean isSelected(Table1 t) {
        for (Table1 tab : selectedTables) {
            if (tab.getTableName().equals(t.getTableName())) {
                return true;
            }
        }
        return false;
    }

    public void next() {

        if (tableIndex < table.length) {
            if (to.isEmpty() && from.isEmpty()) {
                throw new NullPointerException("Both empty?");
            }

            if (!from.isEmpty() && !to.isEmpty()) {
                Column1[] a = table[tableIndex].getColumn();
                for (int i = 0; i < to.size(); i++) {
                    for (int j = 0; j < a.length; j++) {
                        if (to.get(i).equals(a[j].getColName()) && !selectedColumns.contains(a[j])) {
//                            System.out.println("!from.isEmpty() && !to.isEmpty()");
                            selectedColumns.add(a[j]);
                            unselectedColumns.remove(a[j]);
                            break;
                        }
                    }
                }

                for (int i = 0; i < from.size(); i++) {
                    for (int j = 0; j < a.length; j++) {
                        if (from.get(i).equals(a[j].getColName()) && !unselectedColumns.contains(a[j])) {
//                            System.out.println("!from.isEmpty() && !to.isEmpty()");
                            unselectedColumns.add(a[j]);
                            selectedColumns.remove(a[j]);
                            break;
                        }
                    }
                }
            }
            if (selectedColumns.size() != 0) {
                removeIfExists();
                selectedTables.add(
                        new Table1(table[tableIndex].getTableName(), selectedColumns.toArray(new Column1[0]), table[tableIndex].getPrimaryColName()));
//                setAllBtn(false);
//buttons of the current table               
                setButtons();

//                System.out.println("Table-->" + table[tableIndex].getTableName());
                for (int i = 0; i < buttons[0].length; i++) {
                    System.out.print(buttons[tableIndex][i] + "  ");
                }

                //frameList.add(frame);
                selectedColumns.clear();
                to.clear();
            }
            from.clear();
            unselectedColumns.clear();
        }
    }

    public void setButtons() {

        if (isSelected(table[tableIndex])) {
            boolean bool = true;
            Column1[] col = table[tableIndex].getColumn();
            System.out.println("Index-->" + tableIndex);
            for (int j = 0; j < buttons[0].length; j++) {
                if (buttons[tableIndex][j]) {
                    inSwitch(j, true);
                    bool = false;
                } else {
                    inSwitch(j, false);
                }
            }
            if (bool) {
                System.out.println("all must be selected");
            }
            /*      if (bool) {
//           System.out.println("All are selected-->" + table[tableIndex].getTableName());
            setAllBtn(true);
        }
             */
        } else {
            setAllBtn(false);
            System.out.println("\nTable is not selected yet-->" + table[tableIndex].getTableName());
        }

    }

    public void inSwitch(int j, boolean bool) {
        switch (j) {
            case 0:
                insertChk1.setSelected(bool);
                break;
            case 1:
                deleteChk1.setSelected(bool);
                break;
            case 2:
                updateChk1.setSelected(bool);
                break;
            case 3:
                viewChk1.setSelected(bool);
                break;
            case 4:
                cancelChk1.setSelected(bool);
                break;
            case 5:
                backChk1.setSelected(bool);
                break;
            case 6:
                nextChk1.setSelected(bool);
                break;
            case 7:
                searchChk1.setSelected(bool);
                break;
        
        }

    }

    public void setAllBtn(boolean bool) {
        //manually assigning the boolean value, 
        for (int j = 0; j < buttons[0].length; j++) {
            buttons[tableIndex][j] = bool;
        }
        
        allBtnRD.setSelected(bool);
        insertChk1.setSelected(bool);
        deleteChk1.setSelected(bool);
        updateChk1.setSelected(bool);
        cancelChk1.setSelected(bool);
        viewChk1.setSelected(bool);
        backChk1.setSelected(bool);
        nextChk1.setSelected(bool);
        searchChk1.setSelected(bool);
    
    }

    public boolean isAllBtnSelected() {
        return allBtnRD.isSelected();
    }

    public void removeIfExists() {
        boolean bool = false;
        int index = -1;
        for (Table1 j : selectedTables) {
            if (j.getTableName().equals(table[tableIndex].getTableName())) {
//                selectedTables.remove(j);
                index++;
                bool = true;
                break;
            }
            index++;
        }

        if (bool) {
            selectedTables.remove(index);
        }

    }

    public void display() {
//        System.out.println("Selected Tables ");
        for (Table1 t : selectedTables) {
            System.out.println(t);
        }
    }

    public void setValues() {
        if (table != null) {
            if (tableIndex < table.length) {
                if (!selectedTables.isEmpty()) {
//                    System.out.println("table:-->" + table[tableIndex]);
//                    if (selectedTables.contains(table[tableIndex])) {

                    if (isSelected(table[tableIndex])) {
                        setColumns();

                        if (!unselectedColumns.isEmpty()) {
                            from = new DefaultListModel<>();
                            int size = unselectedColumns.size();
                            for (int i = 0; i < size; i++) {
                                from.addElement(unselectedColumns.get(i).getColName());
                            }
                            unselectedList.setModel(from);
                        }
                        if (!selectedColumns.isEmpty()) {
                            System.out.println("!selectedColumns.isEmpty()");
                            to = new DefaultListModel<>();
                            System.out.println(selectedColumns.size());
                            int size = selectedColumns.size();
                            System.out.println(Arrays.asList(selectedColumns));
                            for (int i = 0; i < size; i++) {
                                to.addElement(selectedColumns.get(i).getColName());
                            }
                            selectedList.setModel(to);
                        } else {
                            System.out.println("else part");
                            to = new DefaultListModel<>();
                            selectedList.setModel(to);
                        }
                        System.out.println("Selecting Xo");
//                        setButtons();
                    } else {
//                        System.out.println("Table is not selected----------");
                        unselectedList.setModel(setFromModel(table[tableIndex]));
                        for (Column1 co : table[tableIndex].getColumn()) {
                            unselectedColumns.add(co);
                        }
                        to.removeAllElements();
                        selectedList.setModel(setToModel(table[tableIndex]));
                    }
//                    setFrame();

                } else {
//                    System.out.println("Table is not selected.................");
                    unselectedList.setModel(setFromModel(table[tableIndex]));
                    for (Column1 co : table[tableIndex].getColumn()) {
                        unselectedColumns.add(co);
                    }
                    to.removeAllElements();
                    selectedList.setModel(setToModel(table[tableIndex]));
                }
                tableNameLBL.setText(table[tableIndex].getTableName());

            }
        }

    }

    public void setColumns() {
        selectedColumns.clear();
        unselectedColumns.clear();
        to.clear();
        from.clear();
        int index = -1;
        boolean bool = false;
        for (Table1 t : selectedTables) {
            if (t.getTableName().equals(table[tableIndex].getTableName())) {
                bool = true;
                index++;
                break;

            }
            index++;
        }
        if (bool) {
            Column1[] c = selectedTables.get(index).getColumn();
            label:
            for (Column1 col : table[tableIndex].getColumn()) {
                for (int i = 0; i < c.length; i++) {
                    if (col.equals(c[i])) {
                        selectedColumns.add(col);
//                        to.addElement(col.getColName());
                        continue label;
                    }
                }
                unselectedColumns.add(col);
//                from.addElement(col.getColName());
            }
        } else {
            for (Column1 col : table[tableIndex].getColumn()) {
                unselectedColumns.add(col);

//                from.addElement(col.getColName());
            }
        }
//here set model of the list but doing it at setValues() funtion
    }

    public Column1[] getUnselectedCol(String tableName) {

        for (Table1 s : selectedTables) {

            if (s.getTableName().equals(tableName)) {

                for (Column1 col : s.getColumn()) {

                }
            }
        }
        return null;
    }

    public void setFrameButtons() {
        System.out.println("Setting buttons");
        if (allBtnRD.isSelected()) {

            System.out.println("All selected ");
            frame.setAllBtn(true);
        } else if (!insertChk1.isSelected() && !deleteChk1.isSelected() && !updateChk1.isSelected() && !viewChk1.isSelected()
                && !cancelChk1.isSelected() && !backChk1.isSelected() && !nextChk1.isSelected()) {

            System.out.println("all not selected");
            frame.setAllBtn(true);

        } else {
            System.out.println("INSERT");
            if (insertChk1.isSelected()) {
                frame.setInsertBtn(true);
            }
            if (deleteChk1.isSelected()) {
                frame.setInsertBtn(true);
            }
            if (updateChk1.isSelected()) {
                frame.setInsertBtn(true);
            }
            if (viewChk1.isSelected()) {

//                System.out.println("vIEW");
                frame.setInsertBtn(true);
            }
            if (cancelChk1.isSelected()) {
                frame.setInsertBtn(true);
            }
            if (backChk1.isSelected()) {
                frame.setInsertBtn(true);
            }
            if (nextChk1.isSelected()) {
                frame.setInsertBtn(true);
            }

        }

    }

    public void setAllValuesFrom(JList fromList, DefaultListModel from, JList toList, DefaultListModel to) {
        int i = 0;
        while (!from.isEmpty()) {
            to.addElement(from.remove(0));
        }
        for (Column1 t : table[tableIndex].getColumn()) {
            selectedColumns.add(t);
        }

        fromList.setModel(from);
        toList.setModel(to);

    }

    public void setValuesFrom(JList<String> fromList, DefaultListModel from, JList<String> toList, DefaultListModel to) {
        List<String> list = fromList.getSelectedValuesList();
        for (String s : list) {
            to.addElement(s);
            from.removeElement(s);
        }
        fromList.setModel(from);
        toList.setModel(to);

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
            java.util.logging.Logger.getLogger(TableFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TableFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TableFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TableFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TableFrame1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addBtn;
    private javax.swing.JRadioButton allBtnRD;
    private javax.swing.JRadioButton allColumnRd1;
    private javax.swing.JButton appBuilderBtn;
    private javax.swing.JLabel appLBL;
    private javax.swing.JTextField appNameTxt;
    private javax.swing.JButton backBtn;
    private javax.swing.JCheckBox backChk1;
    private javax.swing.JCheckBox cancelChk1;
    private javax.swing.JCheckBox deleteChk1;
    private javax.swing.JCheckBox insertChk1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JCheckBox nextChk1;
    private javax.swing.JButton nextTable;
    private javax.swing.JButton prevTable;
    private javax.swing.JLabel removeBtn;
    private javax.swing.JCheckBox searchChk1;
    private javax.swing.JList<String> selectedList;
    private javax.swing.JScrollPane selectedScroll;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JLabel tableNameLBL;
    private javax.swing.JPanel tableTab1;
    private javax.swing.JPanel tableTab2;
    private javax.swing.JList<String> unselectedList;
    private javax.swing.JScrollPane unselectedScroll;
    private javax.swing.JCheckBox updateChk1;
    private javax.swing.JCheckBox viewChk1;
    // End of variables declaration//GEN-END:variables
}
