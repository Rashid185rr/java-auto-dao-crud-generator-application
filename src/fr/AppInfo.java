/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr;

import java.util.List;

/**
 *
 * @author tahir hussain
 */
public class AppInfo {
    private String appTitle;
    private String path;
    private List<FrameInfo> frameList;
    private String dbName;
    public AppInfo() {
    
    }

    public AppInfo(String path,String dbName,String appTitle) {
        this.dbName=dbName;
        this.appTitle=appTitle;
        this.path=path;
    }


    
    public AppInfo(String path,String appTitle,List<FrameInfo> frameList) {
        this.appTitle=appTitle;
        this.path=path;
        this.frameList=frameList;
    }
    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<FrameInfo> getFrameList() {
        return frameList;
    }

    public void setFrameList(List<FrameInfo> frameList) {
        this.frameList = frameList;
    }
    
    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    
    public String toString(){
        return "Application Name:"+appTitle;
    }
}
