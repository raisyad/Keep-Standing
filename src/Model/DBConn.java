package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
public class DBConn {
    // konfigurasi koneksi
    private String connAddress = "jdbc:mysql://localhost/db_keep_standing?user=root&password=";
    private Statement stmt = null; // koneksi query
    private Connection conn = null; // koneksi mysql dan basis data
    private ResultSet rs = null; // hasil query
    
    public DBConn() throws Exception, SQLException {
        // Method DB for connection to MySQL
        try {
            // membuat driver mysql
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // membuat koneksi ke mysql dan basis data
            conn = DriverManager.getConnection(connAddress);
            conn.setTransactionIsolation(conn.TRANSACTION_READ_UNCOMMITTED);
        } catch (SQLException er) {
            // error jika koneksi gagal
            System.out.println(er.getMessage());
            throw er;
        }
    }
    
    public void SelectQuery(String Query) throws Exception, SQLException {
        // Method for execute query or get the data
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(Query);
            if(stmt.execute(Query)) {
                rs = stmt.getResultSet();
            }
        } catch (SQLException er) {
            System.out.println(er.getMessage());
            throw er;
        }
    }
    
    public void UpdateQuery(String Query)throws Exception, SQLException {
        // Method for execute query data like insert, update or deleted data
        
        try {
            stmt = conn.createStatement();
            int res = stmt.executeUpdate(Query);
        } catch (SQLException er) {
            System.out.println(er.getMessage());
            throw er;
        }
    }
    
    public ResultSet getRes() throws Exception {
        // Method get data and return data
        
        ResultSet Temp = null;
        try {
            return rs;
        } catch (Exception er) {
            System.out.println(er.getMessage());
            return Temp;
        }
    }
    
    public void closeRes() throws Exception, SQLException {
        // Close get data or relations from the query
        
        if(rs != null) {
            try {
                rs.close();
            }
            catch(SQLException er){
                rs = null;
                System.out.println(er.getMessage());
                throw er;
            }
        }
        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException er) {
                stmt = null;
                System.out.println(er.getMessage());
                throw er;
            }
        }
    }
    
    public void closeConn() throws Exception, SQLException {
        // Close relation with MySQL 
        
        if(conn != null) {
            try {
                conn.close();
            }
            catch(SQLException er){
                System.out.println(er.getMessage());
                conn = null;
            }
        }
    }
}
