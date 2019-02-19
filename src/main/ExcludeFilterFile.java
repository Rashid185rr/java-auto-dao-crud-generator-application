/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author tahir hussain
 */
public class ExcludeFilterFile implements FilenameFilter {

    String ext="";
    public ExcludeFilterFile(String ext) {
    this.ext=ext;
    }
    @Override
    public boolean accept(File file,String name){
      
        if(name.endsWith(".png")||name.endsWith(".jpg")|| name.endsWith(".class") || name.endsWith(ext)){
        return false;
        }
        return true;
    }
}
