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
public class FilterFile implements FilenameFilter{
    private final String ext;
    public FilterFile(String ext){
    this.ext=ext;
    }
 
    @Override
    public boolean accept(File file,String fileName){
        return  fileName.endsWith(ext);
    }
}
