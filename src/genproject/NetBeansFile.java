/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genproject;

import java.io.File;
import main.File1;

/**
 *
 * @author tahir hussain
 */
public class NetBeansFile {
    
    public NetBeansFile(){

    }    
/*
    
nbFiles netbean files    
    */
    public static void copyNetbeansFile(String nbFiles,String newProjPath,String projectName){
       /* File newProject=new File(newProjPath+"/"+projectName);
        //manually setting this project path
        File thisProject=new File(nbFiles);
        //C:\Users\tahir hussain\Documents\ACS\src\generateProject
        byte[] replace="JDBS".getBytes();
        File1.createDirectoriesAndFiles(thisProject, newProject, "JDBS", projectName, "src");
        
        //File1.createBuildDirectory("C:/Users/tahir hussain/Documents/ACS/src/buildfile/");
    */
        File newProject=new File(newProjPath+"/"+projectName);
        
        File thisProject=new File(nbFiles);
        File1.createDirectoriesAndFiles(thisProject, newProject, "JDBS", projectName, "src");
   
        
    }

}