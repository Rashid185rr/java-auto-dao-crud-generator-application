/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr;

import database.table.Table1;
import main.Functions;

/**
 *
 * @author tahir hussain
 */
public class FrameInfo {

    private Boolean insertBtn;
    private Boolean updateBtn;
    private Boolean deleteBtn;
    private Boolean viewBtn;
    private Boolean nextBtn;
    private Boolean backBtn;
    private Boolean searchTxt;
    private Boolean isTable;
    private Boolean allBtn;
    private boolean cancelBtn;

    private Table1 table;
    private String title;
    private String appTitle;

    public FrameInfo() {

    }

    public FrameInfo(Table1 table) {
        this.table = table;
        if (table != null) {
            isTable = true;
        }
        title = Functions.getJcName(table.getTableName());
        System.out.println("Frame title ----->"+title);
        allBtn = true;
        insertBtn = true;
        updateBtn = true;
        deleteBtn = true;
        viewBtn = true;
        nextBtn = true;
        backBtn = true;
        searchTxt = true;
        isTable = true;

    }

    public FrameInfo(Table1 table, String title, Boolean allBtn, Boolean insertBtn, Boolean updateBtn, Boolean deleteBtn, Boolean viewBtn, Boolean nextBtn, Boolean backBtn, Boolean searchTxt, Boolean isTable) {
        this.table = table;
        if (table != null) {
            isTable = true;
        }

        this.title = title;
        this.allBtn = allBtn;
        this.insertBtn = insertBtn;
        this.updateBtn = updateBtn;
        this.deleteBtn = deleteBtn;
        this.viewBtn = viewBtn;
        this.nextBtn = nextBtn;
        this.backBtn = backBtn;
        this.searchTxt = searchTxt;
        this.isTable = isTable;
    }

    public Boolean getInsertBtn() {
        return insertBtn;
    }

    public void setInsertBtn(Boolean insertBtn) {
        this.insertBtn = insertBtn;
    }

    public Boolean getUpdateBtn() {
        return updateBtn;
    }

    public void setUpdateBtn(Boolean updateBtn) {
        this.updateBtn = updateBtn;
    }

    public Boolean getAllBtn() {
        return allBtn;
    }

    public void setAllBtn(Boolean allBtn) {
        this.allBtn = allBtn;
    }

    public Boolean getDeleteBtn() {
        return deleteBtn;
    }

    public void setDeleteBtn(Boolean deleteBtn) {
        this.deleteBtn = deleteBtn;
    }

    public Boolean getViewBtn() {
        return viewBtn;
    }

    public void setViewBtn(Boolean viewBtn) {
        this.viewBtn = viewBtn;
    }

    public Boolean getNextBtn() {
        return nextBtn;
    }

    public void setNextBtn(Boolean nextBtn) {
        this.nextBtn = nextBtn;
    }

    public Boolean getBackBtn() {
        return backBtn;
    }

    public void setBackBtn(Boolean backBtn) {
        this.backBtn = backBtn;
    }

    public Boolean getSearchTxt() {
        return searchTxt;
    }

    public void setSearchTxt(Boolean searchTxt) {
        this.searchTxt = searchTxt;
    }

    public Boolean getIsTable() {
        return isTable;
    }

    public void setIsTable(Boolean isTable) {
        this.isTable = isTable;
    }

    public Table1 getTable() {
        return table;
    }

    public void setTable(Table1 table) {
        if (table != null) {
            isTable = true;
        }

        this.table = table;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCancelBtn() {
        return cancelBtn;
    }

    public void setCancelBtn(boolean cancelBtn) {
        this.cancelBtn = cancelBtn;
    }

    public String toString() {
        return table + " "
                + title + " "
                + allBtn + " "
                + insertBtn + " "
                + updateBtn + " "
                + deleteBtn + " "
                + viewBtn + " "
                + nextBtn + " "
                + backBtn + " "
                + searchTxt + " "
                + isTable + " ";
    }
}
