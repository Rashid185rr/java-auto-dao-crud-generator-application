/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.connect.ConnectDB;
import database.connect.ConnectDBs;
import database.connect.DB;
import database.table.Column1;
import database.table.Table1;
import main.Functions;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tahir hussain
 */
public class TableDB {

    private String tableName;

    public static Table1[] getTables(String dbName, String[] tables) {
        Table1[] table = new Table1[tables.length];

        try {
            //checking
            System.out.println("getTables--> "+dbName);
            String[] primaryKeyCol = getPrimaryKeys(ConnectDBs.getConnection(dbName), tables);

            System.out.println("colum keys\n");

            for (String sa : primaryKeyCol) {
                System.out.println("sa======================>>>>>" + sa);
            }

            DatabaseMetaData meta = ConnectDBs.getConnection(dbName).getMetaData();

            ResultSet rsa = meta.getColumns(null, null, null, null);

            int col_len = rsa.getMetaData().getColumnCount();
            System.out.println("checking column");
            int i = 0;
            int j = 0;
            List<Column1> list = new ArrayList<>();
            String tableName = "";
            int tableIndex = 0;

            while (rsa.next()) {

                if (tableName.equals("")) {
                    tableName = rsa.getString(3);
                }

                if (!tableName.equals(rsa.getString(3))) {
                    //                System.out.println("====>>> " + tableName);
                    Column1[] col = new Column1[list.size()];
                    int index = 0;

                    for (Column1 c : list) {

                        col[index++] = c;
                    }
                    table[j] = new Table1(tableName, col, primaryKeyCol[j++]);
                    tableName = rsa.getString(3);
                    //                  System.out.println("list:"+list);
                    tableIndex++;
                    list.clear();

                    System.out.println("............................. > " + tableIndex + " " + tableName);
                }
//                System.out.println(primaryKeyCol[i] + " ==" + rsa.getString(4));

                boolean bool = false;
                if (tableIndex != primaryKeyCol.length && primaryKeyCol[tableIndex] != null) {

                    for (int k = 0; k < primaryKeyCol.length; k++) {

                        if (primaryKeyCol[k] != null && primaryKeyCol[k].equals(rsa.getString(4))) {
                            System.out.println("Primary key");
                            bool = true;
                        }
                    }

                }

                list.add(new Column1(rsa.getString(1),
                        rsa.getString(2), rsa.getString(3), rsa.getString(4).replace(" ", ""), rsa.getInt(5), rsa.getString(6), rsa.getInt(7), rsa.getInt(8), rsa.getInt(9), rsa.getInt(10),
                        rsa.getInt(11), rsa.getString(12), rsa.getString(13), rsa.getInt(14), rsa.getInt(15), rsa.getInt(16), rsa.getInt(17), rsa.getString(18),
                        rsa.getString(19), rsa.getString(20), rsa.getString(21), rsa.getShort(22), rsa.getString(23),
                        null, bool)//rsa.getString(24)
                );

            }

            Column1[] col = new Column1[list.size()];
            int index = 0;
            for (Column1 c : list) {
                col[index++] = c;
            }
            table[j] = new Table1(tableName, col, primaryKeyCol[j++]);
            list.clear();
            //           System.out.println("checking column end");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("\n ================== checking table================ \n");
        for (Table1 d : table) {
            System.out.println(d);
        }
        System.out.println("\n =================    ending checking ===================\n");
        return table;
    }

    public static String[] getPrimaryKeys(Connection con, String[] tables) throws SQLException {

        String[] column = new String[tables.length];
        System.out.println("column==========> ");
        int i = 0;
        for (String tableName : tables) {
            column[i++] = getPrimaryKey(con, tableName);
        }
        for (String s : column) {
            System.out.println(s);
        }

        return column;
    }

    public static String getPrimaryKey(Connection con, String tableName) {
        System.out.println("tableName:" + tableName);
        
        String colName = null;
        try {
            ResultSet rs = con.getMetaData().getPrimaryKeys(null, null, tableName);

            if (rs.next()) {
                colName = rs.getString("COLUMN_NAME");
            }
            System.out.println("colName:" + colName);
            return colName;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colName;
    }

    /*
    public static Table1[] getTables(String[] table){
        Table1[] tables=new Table1[table.length];
        
        int i=0;
        for(String t:table){
            tables[i]=new Table1(t,getColumns(t));
            System.out.println(tables[i]);
        }
        
        return tables;
    }
     */
    public int getColumnType(int i) {

        switch (i) {
            case Types.ARRAY:
                return Types.ARRAY;

            case Types.BIGINT:

                return Types.BIGINT;

            case Types.BINARY:
                return Types.BINARY;

            case Types.BIT:
                return Types.BIT;
            case Types.BLOB:
                return Types.BLOB;

            case Types.BOOLEAN:
                return Types.BLOB;

            case Types.CHAR:
                return Types.CHAR;

            case Types.CLOB:
                return Types.CLOB;

            case Types.DATALINK:
                return Types.DATALINK;

            case Types.DATE:
                return Types.DATE;

            case Types.DECIMAL:
                return Types.DECIMAL;

            case Types.DISTINCT:
                return Types.DISTINCT;
            case Types.DOUBLE:
                return Types.DOUBLE;
            case Types.FLOAT:
                return Types.FLOAT;
            case Types.INTEGER:

                return Types.INTEGER;

            default:
                break;
        }

        return -1;
    }

}
