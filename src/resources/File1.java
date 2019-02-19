/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
/**
 *
 * @author tahir hussain
 */
public class File1 {
    
    
    public static File createFile(String fileName){
    
    File file=null;
        if(Files.exists(Paths.get(new File("File1.java").getAbsolutePath()+"/"+fileName))){
            System.out.println("Yes, it exists");
        }
        
        try{
            file=new File(fileName);
            if(file.createNewFile()){
                System.out.println("new File has been created");
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
        return file;
    }
    
    public static void writeFileContent(){
        
    }
}