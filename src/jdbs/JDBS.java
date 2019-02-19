 
package jdbs;

import database.Database;
import database.TableDB;
import database.connect.ConnectDB;
import database.connect.DB;
import database.table.Table1;
import frame.jdbs.InformUser;
import frame.jdbs.JDBs;
import java.awt.EventQueue;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tahir hussain
 */
public class JDBS {
//C:/Users/tahir hussain/Documents/JDBC1/CrudSoft
//C:/Users/tahir hussain/Documents/JDBC1/CrudSoft
//C:/Users/tahir hussain/NewProject/CrudeProj    
    //C:/Users/tahir hussain/Documents/DBandJava/CrudSoft
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable(){
        @Override
            public void run(){
                new JDBs()
                .setVisible(true);
            }
        });
   
    }

}
