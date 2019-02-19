/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.connect;

/**
 *
 * @author tahir hussain
 */
public enum DB {
    MySQL(1),Oracle(2),SQLite(3);
    final int value;
    DB(){
        this.value=1;
    }
    
    DB(int value){
    this.value=value;
    }
     
}
