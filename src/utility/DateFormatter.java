/* 
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor. 
 */ 
package  utility; 
 
import java.sql.Timestamp; 
import java.text.SimpleDateFormat; 
import java.sql.Date; 
import java.text.ParseException; 
 
/** 
 * 
 * @author tahir hussain 
 */ 
public class DateFormatter { 
    
  /*  public static Date getDate(String dateStr){
        
        //yyyy-MM-dd
        //MM
    
    }
*/ 
    public static java.util.Date getDateyMd(java.util.Date date) { 
        //will be written 
        try { 
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
            date = sdf.parse(sdf.format(date)); 
        } catch (ParseException ex) { 
            ex.printStackTrace(); 
        } 
        return date; 
    } 
 
    public static Date getDateyMd(String dateStr) { 
        //will be written 
        java.util.Date date = null; 
        try { 
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
            date = sdf.parse(dateStr); 
        } catch (ParseException ex) { 
            ex.printStackTrace(); 
        } 
        return new java.sql.Date(date.getDate()); 
    } 
 
    public static Timestamp getTimeyMd(Timestamp time) { 
        java.util.Date d = null; 
        try { 
 
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
 
            d = sdf.parse(sdf.format(time)); 
 
        } catch (ParseException ex) { 
            ex.printStackTrace(); 
        } 
 
        return new Timestamp(d.getDate()); 
    } 
 
    public static Timestamp getTimeyMd(java.util.Date date) { 
        try { 
 
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
 
            date = sdf.parse(sdf.format(date)); 
 
        } catch (ParseException ex) { 
            ex.printStackTrace(); 
        } 
 
        return new Timestamp(date.getDate()); 
    } 
 
    public static Date getDate(SimpleDateFormat sdf, String str) { 
 
        Date date = null; 
        try { 
            //date=sdf.parse(sdf.format(date)); 
 
            java.util.Date d = sdf.parse(str); 
 
            date = new java.sql.Date(d.getDate()); 
 
        } catch (ParseException e) { 
            e.printStackTrace(); 
        } 
 
        return date; 
    } 
 
    public static Timestamp getDateyMd(Timestamp time) { 
 
        //will be written 
        SimpleDateFormat sdf = new SimpleDateFormat(""); 
 
        return time; 
    } 
 
    public static Date getDateEdy(java.util.Date date) { 
 
        try { 
            SimpleDateFormat sdf = new SimpleDateFormat("MMM DD, yyyy"); 
            System.out.println(sdf.format(date)); 
 
            System.out.println(sdf.parse(sdf.format(date))); 
 
        } catch (ParseException ex) { 
            System.out.println(ex); 
        } 
        return new java.sql.Date(date.getDate()); 
 
    } 
 
    public static Timestamp getTimeyMdhms(String str) { 
        java.util.Date date = null; 
        try { 
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
            date = sdf.parse(str); 
        } catch (ParseException e) { 
            e.printStackTrace(); 
        } 
        return new Timestamp(date.getTime()); 
    } 
 
    public static String getDateEdy(String dateStr) { 
        java.util.Date date = new Date(getTimeyMdhms(dateStr).getTime()); 
        String str = ""; 
        SimpleDateFormat sdf = new SimpleDateFormat("MMM DD, yyyy"); 
        System.out.println(sdf.format(date)); 
        str = sdf.format(date); 
        return str; 
    } 
 
    public static Timestamp getTimeyMd(StringBuilder str) { 
        Timestamp time = null; 
 
        return time; 
    } 
 
    public static Timestamp getTimeyMd(String str) { 
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd"); 
        return new Timestamp(getDate(new SimpleDateFormat("yyyy-dd-MM"), str).getDate()); 
    } 
 
    public static Timestamp getTimeMdY(String str) { 
        return new Timestamp(getDate(new SimpleDateFormat("MM-dd-yyyy"), str).getDate()); 
    } 
 
    public static Timestamp getTimedMy(String str) { 
        return new Timestamp(getDate(new SimpleDateFormat("dd-MM-yyyy"), str).getDate()); 
    } 
 
    public static Timestamp getTimeyMdWithEMdy(String str) { 
        return new Timestamp(getDate(new SimpleDateFormat("E dd-MM-yyyy"), str).getDate()); 
    } 
 
    public static Timestamp getTimeEMdy(String str) { 
        return new Timestamp(getDate(new SimpleDateFormat("E MM-dd-yyyy"), str).getDate()); 
    } 
 
    public static java.util.Date getDate(String str){ 
    java.util.Date date=null; 
     
    if(str==null || str.equals("null") || str.equals("")) 
        return null; 
 
 
    if(str.length()==4){ 
        java.util.Date d=new java.util.Date(); 
        d.setYear(Integer.parseInt(str)); 
        System.out.println("date is parsed--->"+d); 
        return d; 
    } 
     
    try{ 
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        date=sdf.parse(str); 
         
        } 
        catch(ParseException e){ 
        e.printStackTrace(); 
        } 
    return date; 
    } 
 
    public static java.sql.Date getDate(java.sql.Date date) { 
        SimpleDateFormat sdf = new SimpleDateFormat(); 
        java.sql.Date d = null; 
        try { 
            d = new java.sql.Date(sdf.parse(sdf.format(date)).getDate()); 
        } catch (ParseException e) { 
            System.out.println(e); 
        } 
        return d; 
    } 
 
    public static Date getDateEMdy(String str) { 
        SimpleDateFormat sdf = null; 
        /*String[] s=str.split("-"); 
     
     
        if(str.indexOf("Mon")>0 ||  
                str.indexOf("Tue")>0 ||  
                str.indexOf("Wed")>0 ||  
                str.indexOf("Thu")>0 ||  
                str.indexOf("Fri")>0 || 
                str.indexOf("Sat")>0 || 
                str.indexOf("Sun")>0 )  { 
            sdf=new SimpleDateFormat("E"); 
    } 
         */ 
        java.sql.Date date = null; 
        try { 
 
            sdf = new SimpleDateFormat("E MM-dd-yyyy"); 
 
            date = new Date(sdf.parse(str).getDate()); 
 
        } catch (ParseException e) { 
            System.out.println(e); 
        } 
        return date; 
    } 

    static void getDateyMd(Date date) {
        try{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d=sdf.parse(sdf.format(date));
            new java.sql.Date(d.getTime()); 
        }catch(ParseException e){
            e.printStackTrace();
        }
    }

    static String getYear(java.util.Date date) {
           SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
                return sdf.format(date);
    
    }
 
} 
