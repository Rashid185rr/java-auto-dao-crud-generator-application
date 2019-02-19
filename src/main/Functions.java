/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author tahir hussain
 */
public class Functions {
    
    public static String getName(String name) {
        String n = "";
        int i = 0;
        for (String j : name.split("_")) {
            try{
            n += n.equals("") ? j : Character.toUpperCase(j.charAt(0)) + j.substring(1);
            }catch(StringIndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }
        
        
        return n.replace(" ", "");
    }
    
    public static String getJcName(String name){
        String n = "";
        int i = 0;
        if(name!=null && !name.equals("")){
        for (String j : name.split("_")) {
            try{
            n +=Character.toUpperCase(j.charAt(0)) + j.substring(1);
            }catch(StringIndexOutOfBoundsException e){
            e.printStackTrace();
            }
        }
        }
        
        return n;
    
        
    }
}
