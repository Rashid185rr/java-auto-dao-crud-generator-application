/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import database.table.Column1;

/**
 *
 * @author tahir hussain
 */
public class Component1 {

  private  String comName;
  private    String bgColor;
  private    String fgColor;
  private    String font;
  private    String compHeading;
  
    public String getCompHeading() {
        return compHeading;
    }

    public void setCompHeading(String compHeading) {
        String heading = "";
        for (String j : compHeading.split("_")) {
            heading += heading.equals("") ? Character.toUpperCase(j.charAt(0)) + j.substring(1) : " " + Character.toUpperCase(j.charAt(0)) + j.substring(1);
        }
        this.compHeading = heading;

    }
   private   Integer fontSize;

    public String getComName() {
        return comName;
    }

    public void setComName(String colName) {
        String n = "";
        Integer i = 0;
        for (String j : colName.split("_")) {
            n += n.equals("") ? j : Character.toUpperCase(j.charAt(0)) + j.substring(1);
        }
        this.comName = n;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getFgColor() {
        return fgColor;
    }

    public void setFgColor(String fgColor) {
        this.fgColor = fgColor;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Integer getCompSizeX() {
        return compSizeX;
    }

    public void setCompSizeX(Integer compSizeX) {
        this.compSizeX = compSizeX;
    }

    public Integer getCompSizeY() {
        return compSizeY;
    }

    public void setCompSizeY(Integer compSizeY) {
        this.compSizeY = compSizeY;
    }

    public static String getName(String name) {
        String n = "";
        Integer i = 0;
        for (String j : name.split("_")) {
            n += n.equals("") ? j : Character.toUpperCase(j.charAt(0)) + j.substring(1);
        }

        return n;
    }

    public Column1 getColumn() {
        return column;
    }

    public void setColumn(Column1 column) {
        this.column = column;
    }

    public String getImportText() {
        return importText;
    }

    public void setImportText(String importText) {
        this.importText = importText;
    }

    public Component1(Column1 column, String bgColor, String fgColor, Integer xx, Integer yy, Integer compSizeX, Integer compSizeY) {

        this.column = column;
        if(column!=null)
        //setComName(this.column.getColName());
        this.comName=column.getColName();
        this.bgColor = bgColor;
        this.fgColor = fgColor;
        this.compSizeX = compSizeX;
        this.compSizeY = compSizeY;
        x = xx;
        y = yy;

    }
    public Component1(String comName,String compHeading,String bgColor, String fgColor, Integer xx, Integer yy, Integer compSizeX, Integer compSizeY) {

        
        this.comName=comName;
        this.compHeading=compHeading;
        this.bgColor = bgColor;
        this.fgColor = fgColor;
        this.compSizeX = compSizeX;
        this.compSizeY = compSizeY;
        x = xx;
        y = yy;
        
    }
    

    public Component1(){
    
    
    }
    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
    private Integer y = 30;
    private Integer compSizeX = 30;
    private Integer compSizeY = 150;

    private Column1 column;
    private String importText;
    private Integer x = 30;

}
