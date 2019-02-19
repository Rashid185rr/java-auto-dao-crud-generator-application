/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.table;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tahir hussain
 */
public final class Table1 {

    public Table1() {

    }

    public Table1(Column1[] column, String primaryColName) {
        this.column = column;
        this.primaryColName = primaryColName;
        setColumn();
        anyAutoincrement();
        
        
        
         
    }

    public Table1(String tableName, Column1[] column, String primaryColName) {
        this.tableName = tableName;
        this.column = column;
        this.primaryColName = primaryColName;
        setColumn();
        anyAutoincrement();
    }
 
    
    public Column1 getPrimaryColumn() {
        if (primaryColName == null) {
            return null;
        }

        return column[primaryColIndex];
    }

    public int getPrimaryColIndex() {
        return this.primaryColIndex;
    }

    public String getPrimaryColName() {
        return primaryColName;
    }

    public void setPrimaryColName(String primaryColName) {
        this.primaryColName = primaryColName;
    }

    public Table1 getTable() {
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Column1[] getColumn() {
        return column;
    }

    public void setColumn(Column1[] column) {
        this.column = column;
    }
 

    public int getExceptPriIndex() {
        return exceptPriIndex;
    }

    public void setExceptPriIndex(int exceptPriIndex) {
        this.exceptPriIndex = exceptPriIndex;
    }

    public String getExceptPriColName() {
        return exceptPriColName;
    }

    public void setExceptPriColName(String exceptPriColName) {
        this.exceptPriColName = exceptPriColName;
    }
    
    public Column1[] getColumnsExceptPrimaryKey() {
        if (column == null) {
            return null;
        }

        List<Column1> col = new ArrayList<>();

        for (Column1 colu : column) {
            if (!colu.isPrimaryKey()) {
                col.add(colu);
            }
        }
        int len = col.size();
        Column1[] ca = new Column1[len];
        len = ca.length;
        for (int i = 0; i < len; i++) {
            ca[i] = col.get(i);
        }

        return ca;
    }

   
public String toString() {
        String col = "";
        for (Column1 col1 : column) {
            col = String.format("%s %n",col + " " + col1);
        }

        return String.format("%s %n",tableName + "\n" + col);
    }

    public void anyAutoincrement() {
        isAnyAutoIncrement = false;
        int i = 0;
        for (Column1 c : column) {
            if (c.getIsAutoIncrememnt().equals("YES")) {
                isAnyAutoIncrement = true;
                autoIncrementIndex = i;
                break;
            }
        }

    }

    public Column1 getAutoIncrementCol() {

        if (!isAnyAutoIncrement) {
            return null;
        }
        return column[autoIncrementIndex];
    }
    
    public Column1 getExceptPriCol() {

        if (exceptPriColName == null) {
            return null;
        }

        System.out.println("THIS:" + column[exceptPriIndex].getColName());
        return column[exceptPriIndex];
    }

    public void setColumn() {
        int i = 0;
        if (primaryColName != null) {

            for (Column1 col : column) {
                if (col.getColName().equals(primaryColName)) {
                    primaryColIndex = i;
                    break;
                }
                i++;
            }
        } else {
            
            for (Column1 co : column) {

                if (co.getDataType() != Types.DATE
                        || co.getDataType() != Types.TIME
                        || co.getDataType() != Types.TIMESTAMP
                        || co.getDataType() != Types.TIMESTAMP_WITH_TIMEZONE
                        || co.getDataType() != Types.TIME_WITH_TIMEZONE) {
                    exceptPriIndex = i;
                    exceptPriColName = co.getColName();
                    break;
                }
                i++;
            }

        }

    }

    private String tableName;
    private Column1[] column;
    private String primaryColName;
    int primaryColIndex;
    private int exceptPriIndex;
    String exceptPriColName;
    private boolean isAnyAutoIncrement;
    private int autoIncrementIndex;

    public int getAutoIncrementIndex() {
        return autoIncrementIndex;
    }

    public void setAutoIncrementIndex(int autoIncrementIndex) {
        this.autoIncrementIndex = autoIncrementIndex;
    }

    public boolean isAnyAutoIncrement() {
        return isAnyAutoIncrement;
    }

    public void setIsAnyAutoIncrement(boolean isAnyAutoIncrement) {
        this.isAnyAutoIncrement = isAnyAutoIncrement;
    }

    public Column1[] getColumnExceptAuto(){
         if (column == null) {
            return null;
        }

        List<Column1> col = new ArrayList<>();

        for (Column1 colu : column) {
            if (colu.getIsAutoIncrememnt().equals("NO")) {
                col.add(colu);
            }
        }
        int len = col.size();
        Column1[] ca = new Column1[len];
        len = ca.length;
        for (int i = 0; i < len; i++) {
            ca[i] = col.get(i);
        }

        return ca;
    }
}
