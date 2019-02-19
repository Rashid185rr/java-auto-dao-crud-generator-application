/* 
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor. 
 */ 
package  utility; 
 
import com.toedter.calendar.JDateChooser;
 import java.awt.Color; 
import java.sql.Timestamp;
import java.sql.Types; 
import javax.swing.BorderFactory; 
import javax.swing.JButton; 
import javax.swing.JCheckBox; 
import javax.swing.JComboBox; 
import javax.swing.JPasswordField; 
import javax.swing.JRadioButton; 
import javax.swing.JTable; 
import javax.swing.JTextField; 
//import org.jdesktop.swingx.JXDatePicker; 
 
/** 
 * 
 * @author tahir hussain 
 */ 
public class CheckComponents { 
 
   public static boolean isAllFilled(Object[] object,String[] heading) { 
int i=0; 
        boolean bool = true; 
        for (Object obj : object) { 
            if (obj instanceof JTextField) { 
                JTextField field = (JTextField) obj; 
 
                if (field.getText() == null || field.getText().trim().equals("") || field.getText().equals(heading[i]) || field.getText().equals("null")) { 
                    System.out.println("JTextField " + field); 
                    field.setBorder(BorderFactory.createEtchedBorder(Color.RED, Color.RED)); 
                    return false; 
                } 
 
            } else if (obj instanceof JComboBox) { 
                if ((((JComboBox) obj).getSelectedIndex()) == 0) { 
                    System.out.println("JComBox: "); 
                    return false; 
                } 
            } else if (obj instanceof JCheckBox) { 
                if (!((JCheckBox) obj).isSelected()) { 
                    System.out.println("JCheckBox: "); 
                    bool = false; 
                } else { 
                    bool = true; 
                } 
            } else if (obj instanceof JRadioButton) { 
                if (!((JRadioButton) obj).isSelected()) { 
                    System.out.println("JRadioButton "); 
                    bool = false; 
                } else { 
                    bool = true; 
                } 
            }
            else if(obj instanceof JDateChooser){
                 if(obj==null)
                    return false;
                 JDateChooser chooser=(JDateChooser)obj;
                 if(chooser==null || chooser.getDate()==null)
                     return false;
            }
            else if(obj instanceof Timestamp){
                if(obj==null){
                return false;    
                }
            }
i++; 
        } 
        return bool; 
    } 
 
 
    public static void reset(Object[] object) { 
 
        for (Object obj : object) { 
 
            if (obj instanceof JTextField) { 
                ((JTextField) obj).setText(""); 
            } else if (obj instanceof JButton) { 
                ((JButton) obj).setEnabled(false); 
            } else if (obj instanceof JComboBox) { 
                ((JComboBox) obj).setSelectedIndex(0); 
            } else if (obj instanceof JCheckBox) { 
                ((JCheckBox) obj).setSelected(false); 
            } else if (obj instanceof JRadioButton) { 
                ((JRadioButton) obj).setSelected(false); 
            } 
 
        } 
 
    } 
 public static void fillValueToField(Object object,Object value){  
            if (object instanceof JTextField) {   
                JTextField field = (JTextField) object;  
                    field.setText(""+value);  
  
            } else if (object instanceof JComboBox) {  
                JComboBox combo=(JComboBox)object;  
                combo.setSelectedItem(value);  
            } else if (object instanceof JCheckBox) {  
                JCheckBox check=(JCheckBox)object;  
                check.setSelected(Boolean.parseBoolean(""+value));  
            } else if (object instanceof JRadioButton) {  
                JRadioButton radio=(JRadioButton)object;  
                radio.setSelected(Boolean.parseBoolean(""+value));  
            }  
            else if(object instanceof JDateChooser){  
                JDateChooser date=(JDateChooser)object;  
                date.setDate((java.util.Date)value);  
            }  
              
    }  
 
    public static String getValues(Object[] object) { 
        String str = ""; 
         
        for (Object obj : object) { 
            str = str + ","; 
 
            if (obj instanceof JTextField) { 
                JTextField field = (JTextField) obj; 
        System.out.println("Text field:"+field.getText()); 
 
                if (field.getText() == null || field.getText().trim().equals("")) { 
                    System.out.println("JTextField " + field); 
                    str = str + "null"; 
                } else { 
                    str = str + field.getText(); 
                } 
            } else if (obj instanceof JComboBox) { 
                JComboBox combo = ((JComboBox) obj); 
                System.out.println("Combo box:"+combo.getSelectedItem()); 
                 
                if (combo.getSelectedIndex() == 0) { 
                    str = str + "null"; 
                } else { 
                    str = str + combo.getSelectedItem(); 
                } 
 
            } else if (obj instanceof JCheckBox) { 
                JCheckBox check = (JCheckBox) obj; 
                System.out.println("CheckBox:"+check.getText()); 
 
                if (!(check.isSelected())) { 
                    System.out.println("JCheckBox: "); 
                    str = str + "null"; 
                } else { 
                    str = str + check.getName(); 
                } 
            } else if (obj instanceof JRadioButton) { 
                JRadioButton radio = (JRadioButton) obj; 
                System.out.println("radio.getName()" + radio.getName()); 
                if (radio==null || !(radio.isSelected())) { 
                    System.out.println("JRadioButton "); 
                    str = str + "null"; 
                } else { 
                    str = str + radio.getName(); 
                } 
 
            } else if(obj instanceof JDateChooser){ 
                System.out.println("No Object found"); 
                 JDateChooser date=(JDateChooser)obj; 
                   if (date==null || (date.getDate()==null)) { 
                    System.out.println("JRadioButton "); 
                    str = str + "null"; 
                } else { 
                    str = str + date.getDate(); 
                } 
            } 
           /* else 
                if(obj instanceof JXDatePicker){ 
                    JXDatePicker date=(JXDatePicker)obj; 
                    if (date==null || (date.getDate())==null) { 
                    System.out.println("JRadioButton "); 
                    str = str + "null"; 
                } else { 
                    str = str + date.getDate(); 
                } 
                     
                } 
*/ 
            else 
                if(obj instanceof JPasswordField){ 
                    JPasswordField pwd=(JPasswordField)obj; 
                    if ((pwd.getText())==null) { 
                    System.out.println("JRadioButton "); 
                    str = str + "null"; 
                } else { 
                    str = str + pwd.getName(); 
                } 
                     
                } 
             
        } 
        str = str.substring(1, str.length() - 1); 
        System.out.println("value: --------------------> " + str); 
        return str; 
    } 
 
    public static String check(Object[] object) { 
        String str = ""; 
         
        for (Object obj : object) { 
            if (obj instanceof JTextField) { 
                    System.out.println("Text:"); 
            } else if (obj instanceof JComboBox) { 
                    System.out.println("Combo box:"); 
  
            } else if (obj instanceof JCheckBox) { 
                    System.out.println("CheckBox:"); 
             
            } else if (obj instanceof JRadioButton) { 
                    System.out.println("Radio Button:"); 
            } else { 
                System.out.println("No Object found"); 
                //str=str+; 
 
            } 
             
        } 
        //str = str.substring(1, str.length() - 1); 
         
        System.out.println("value: --------------------> " + str); 
        return str; 
    } 
 
    /* 
     
    public static Object getConstructor(Column1 col, Object obj) { 
 
        String colName = Functions.getJcName(col.getColName()); 
        String returnValue = null; 
        returnValue = new String(String.format("set" + colName + "(resultSet.get#Type(#);\n")); 
 
        switch (col.getDataType()) { 
            case Types.ARRAY: 
                System.out.println("ARRAY"); 
                break; 
            case Types.BIGINT: 
                System.out.println("BIGINT"); 
                if (obj.equals("null")) { 
                    returnValue = returnValue.replace("(#)", "(0)"); 
                } 
                break; 
 
            case Types.BINARY: 
                System.out.println("BINARY"); 
 
                if (obj.equals("null")) { 
                    returnValue = returnValue.replace("(#)", "(0)"); 
                } 
                break; 
            case Types.BIT: 
                System.out.println("BIT"); 
               if (obj.equals("null")) { 
                    returnValue = returnValue.replace("(#)", "(0)"); 
                } 
               break; 
            case Types.BLOB: 
                System.out.println("BLOB"); 
                if (obj.equals("null")) { 
                    returnValue = returnValue.replace("(#)", "(null)"); 
                }break; 
            case Types.BOOLEAN: 
                System.out.println("BOOLEAN"); 
                if (obj.equals("null")) { 
                    returnValue = returnValue.replace("(#)", "(false)"); 
                } 
                break; 
            case Types.CHAR: 
                System.out.println("CHAR"); 
  
                if (obj.equals("null")) { 
                    returnValue = returnValue.replace("(#)", "('\'0')"); 
                } 
 
                break; 
            case Types.CLOB: 
                System.out.println("CLOB"); 
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
  
                if (obj.equals("null")) { 
                    returnValue = returnValue.replace("(#)", "(0)"); 
                } 
                break; 
            case Types.DISTINCT: 
                System.out.println("DISTINCT"); 
                break; 
            case Types.DOUBLE: 
                System.out.println("DOUBLE");if (obj.equals("null")) { 
                    returnValue = returnValue.replace("(#)", "(0)"); 
                } 
                break; 
            case Types.FLOAT: 
                System.out.println("FLOAT"); 
                if (obj.equals("null")) { 
                    returnValue = returnValue.replace("(#)", "(0)"); 
                } 
                break; 
            case Types.INTEGER: 
                System.out.println("INTEGER"); 
 
                if (obj.equals("null")) { 
                    returnValue = returnValue.replace("(#)", "(0)"); 
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
                    return returnValue = returnValue.replace("(#)", "(0)"); 
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
                    return returnValue = returnValue.replace("(#)", "(0.0)"); 
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
                    return returnValue = returnValue.replace("(#)", "(0)"); 
                } 
//                    int intData=(int)obj; 
 
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
        return returnValue = returnValue.replace("(#)", "(" + obj + ")"); 
 
    } 
    */ 
 
} 
