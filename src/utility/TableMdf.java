/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utility;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author tahir hussain
 */
public class TableMdf {
 
    
    
    
    public static DefaultTableModel getDefaultTableModel(ResultSet resultSet){
//        =(DefaultTableModel)table.getModel();
          DefaultTableModel defaultModel=(DefaultTableModel)DbUtils.resultSetToTableModel(resultSet);
            return defaultModel;
   
    }
    
    public static void tableSort(JTable table,JTextField field){
        TableModel tableModel=  table.getModel();
        TableRowSorter table1=new TableRowSorter(tableModel);
        table.setRowSorter(table1);
        table1.setRowFilter( RowFilter.regexFilter(field.getText()) );
    }
    
   
    public static void fillTableColumn(DefaultTableModel tableModel,JTable table,ResultSet rs){
        tableModel=(DefaultTableModel)table.getModel();
        tableModel.setColumnCount(0);
        column(rs);
        table.setModel(tableModel);
    }
    
    
    public static Vector<String> row(ResultSet resultSet) {
        if (resultSet == null) {
            throw new NullPointerException("ResultSet is Null");
        }

        Vector<String> row = new Vector<>();
        try {
            ResultSetMetaData meta =  resultSet.getMetaData();
            if (meta == null) {
                System.out.println("meta null");
                System.exit(0);
            }

            Integer col_len = meta.getColumnCount();
            String str = "";
            while (resultSet.next()) {
                for (Integer i = 1; i <= col_len; i++) {
                    row.add(resultSet.getString(meta.getColumnName(i)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
       return row;
    }
 

    public static Vector<Vector> rowData(ResultSet resultSet) {
        Vector<Vector> vector = new Vector<>();
       try{
           Vector<String> row = row(resultSet);
       if (row == null ) {
            System.out.println("null value");
       return null; 
       }
       
        Integer row_len = row.size();
        ResultSetMetaData meta=resultSet.getMetaData();
            Integer col_len = meta.getColumnCount();
            Vector vect = new Vector();
            for (Integer j = 0; j < row_len; j++) {
                vect.addElement(row.get(j));
                if ( (1 + j) % col_len == 0) {
                    vector.add(vect);
                    vect = new Vector();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vector;
    }
    
     public static Vector<String> column(ResultSet resultSet) throws NullPointerException {

         //DefaultTableModel tableModel=new DefaultTableModel();
        Vector<String> column = new Vector<>();

        try {
            ResultSetMetaData meta = resultSet.getMetaData();

            Integer col_len = meta.getColumnCount();

            for (Integer i = 1; i <= col_len; i++) {
                //tableModel.addColumn( meta.getColumnName(i));
                column.add(meta.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return column;
    }

    
    public static void fillTable(JTable table,ResultSet rs){
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        model.setRowCount(0);
        model.addRow(rowData(rs));
        model.setDataVector(rowData(rs), column(rs));
        table.setModel(model);
        
    }
}
