/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import database.Database;
import database.table.Table1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import resources.File1;

/**
 *
 * @author tahir hussain
 */
public class FrameGenerator {
    
    public FrameGenerator(Database db){

        File[] file=generateFrameFile(db);
            for(File f:file)
                System.out.println(f.getName());
        
    }
    
    public static File[] generateFrameFile(Database db){
    File[] file=new File[db.getTables().length];
        //check fields 
        System.out.println("Generating frame");
    
        int i=0;
        for(Table1 table:db.getTables()){
            String[] a=table.getTableName().split("_");
            String fileName="";
            for(String a1:a){
                fileName+=Character.toUpperCase(a1.charAt(0))+a1.substring(1);
                System.out.println("creating fileName:------------> "+fileName);
            }
            file[i++]=new File(fileName.concat(".java"));
            write(file[i-1],table);
        }
        String s="";
        
     return file;
    }
    
    public static Object[] generateComponents(){
    Object[] obj=null;
    
    return obj;
    }
    
    public static JTable generateTable(){
        JTable table=null;
        
        return table;
    }
    
    
    public static void write(File file,Table1 table){
 
        
        /*
        try{
        FileWriter out=new FileWriter(file);
        
            BufferedReader buf=new BufferedReader(new FileReader("C:/Users/tahir hussain/Documents/ACS/src/resources/frame1"));
            String line="";
            while(line!=null){
            line=buf.readLine();
            
            if(line!=null){
                System.out.println(file.getAbsoluteFile());
                    if(line.indexOf("ClassName")>0){
                    line=line.replace("ClassName",file.getName().replace(".java", ""));
                    }
            out.write(String.format(line+"\n"));
            
            }
            }
            out.close();
        }catch(FileNotFoundException e){
        e.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
    */
        
    }
}
