
package format;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateFormatter{
	

	public static void main(String[] args){

/*
try{ 
		PreparedStatement pre=ConnectDB.getConnection("sms").prepareStatement("select * from certificate",ResultSet.TYPE_SCROLL_SENSITIVE
                        ,ResultSet.CONCUR_READ_ONLY);
		
		ResultSet rs=pre.executeQuery();
                ResultSetMetaData meta=rs.getMetaData();
                
		while(rs.next()){
                    rs.getType();
                    Timestamp date=rs.getTimestamp("created_date");
                    
                    getFormat(date);
                }
     


}
catch(SQLException e){
	e.printStackTrace();
}

  */  


//    insertData();
  insertTime();  
	}
        
        
        public static String getFormat(Timestamp time){
            String str="";
            
            //SimpleDateFormat sdf=new SimpleDateFormat();
            System.out.println(time);
            return str;
        }


        public static void insertData(){
        
            String user_id="f16sw65";
            String user_name="Eidan";
            Date date=new Date();//2018-06-29 00:00:00.0;
            
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
            
        //sdf.format(date);
            try{
            date=sdf.parse(sdf.format(date));
            
            }
            catch(ParseException e){
                e.printStackTrace();
            }
            
            try{
                
            PreparedStatement pre=ConnectDB.getConnection("freedb").prepareStatement("INSERT INTO user values (?,?,?)");
            pre.setString(1,user_id);
            pre.setString(2, user_name);
            pre.setDate(3, new java.sql.Date(date.getDate()));
            pre.executeUpdate();
            
             
            
//            while(rs.next()){
//                for(int i=1; i<=rs.getMetaData().getColumnCount(); i++)
//                System.out.print(rs.getString(i)+"  ");
//                System.out.println();
//            }
//            
            }catch(SQLException e){
                e.printStackTrace();
            }
            
        }
        public static void insertTime(){
            
             String user_id="F16SW67";
            String user_name="Tahir";
            Date date=new Date();//2018-06-29 00:00:00.0;
            
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
            try{
            date=sdf.parse(sdf.format(date));
            
            PreparedStatement pre=ConnectDB.getConnection("freedb").prepareStatement("INSERT INTO user(user_id,user_name,t) values (?,?,?)");
            pre.setString(1, user_id);
            pre.setString(2, user_id);
            

            System.out.println(new Timestamp(date.getDate()));
            pre.setTimestamp(3, new Timestamp(date.getDate()));
            pre.execute();
            
            }catch(ParseException e){
                e.printStackTrace();
            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
}