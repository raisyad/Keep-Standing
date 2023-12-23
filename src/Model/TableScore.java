/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raisy
 * 
 * 
 * @Created by SyncZer
 * 
 *       ///\\\
 * 
 * Saya Raisyad Julfikar mengerjakan evaluasi Tugas Masa Depan dalam mata
 * kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya
 * tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.
 * 
 */
public class TableScore extends DBConn {

    public static void setModel(DefaultTableModel setTable) {
        throw new UnsupportedOperationException("ss"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private String query = "";
    
    public TableScore() throws Exception, SQLException{
        super();
    }
    
    // Get all data
    // Default Value Constructor
    public void getTScores () {
        try {
            this.query = "SELECT * from tscore";
            SelectQuery(this.query);
        } catch (Exception er) {
            System.out.println(er.getMessage());
        }
    }
    
    // Get specific data
    // Value Constructor 
    public void getTScore (String username) {
        try {
            this.query = "SELECT * from tscore WHERE username='" + username + "'";
            SelectQuery(this.query);
        } catch (Exception er) {
            System.out.println(er.getMessage());
        }
    }
    
    // Method for insert the data to DB in MySQL
    public void insertData (String username, int skor, int standing) {
        try {
            TableScore specData = new TableScore();
            specData.getTScore(username);
            
            if(specData.getRes().next()) { // If data not null or data already exists
                try {
                    this.query = "UPDATE tscore SET score=" + skor + ", standing=" + standing + " WHERE username='" + username + "'";
                    UpdateQuery(this.query);
                } catch (Exception er) {
                    System.out.println(er.getMessage());
                }
            } else { // If data null or data doesn't exists yet
                try {
                    this.query = "INSERT INTO tscore VALUES('" + username + "', " + skor + ", " + standing + ")";
                    UpdateQuery(this.query);
                } catch (Exception er) {
                    System.out.println(er.getMessage());
                }
            }
        } catch (Exception er) {
            System.out.println(er.getMessage());
        }
    }
    
    // Created data table
    public DefaultTableModel setTable(){
        
        DefaultTableModel dataTable = null;
        try{
            // Created header table
            Object[] column = {"Username", "Score", "Standing"};
            dataTable = new DefaultTableModel(null, column);
            
            // Select query or get all data from db
            this.query = "SELECT * from tscore order by score DESC";
            SelectQuery(this.query);
            
            // Get data by row
            while(this.getRes().next()){
                Object[] row = new Object[3];
                // Get 1 by 1, based on column
                row[0] = this.getRes().getString(1);
                row[1] = this.getRes().getString(2);
                row[2] = this.getRes().getString(3);
                dataTable.addRow(row);
            }
        }catch(Exception er){
            System.err.println(er.getMessage());
        }
        // Return data
        return dataTable;
    }
}
