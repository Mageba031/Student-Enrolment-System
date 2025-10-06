/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class UserDAO {
    
    public void insertUser(int ID, String name, String password, String role){
        try{
            String DATABASE_URL = "jdbc:derby://localhost:1527/ADPAssignment2.0";
            String username = "Tanatswa";
            String password2 = "admin";
            
            Connection con = DriverManager.getConnection(DATABASE_URL, username, password);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO USER VALUES (?, ?, ?)");
            
            pstmt.setInt(1, ID);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.setString(4,role);
            
            int i = pstmt.executeUpdate();
            System.out.println(i+"The users have been inserted");
            con.close();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    public void getAllUser(){
       try{
           String DATABASE_URL = "jdbc:derby://localhost:1527/ADPAssignment2.0";
            String username = "Tanatswa";
            String password2 = "admin";
            
           Connection con = DriverManager.getConnection(DATABASE_URL, username, password2);
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM user"); 
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                System.out.println(rs.getInt("ID")+"" +rs.getString("Name")+ "" +
                        rs.getString("Password")+ "" + rs.getString("Role"));
            }
            rs.close();
            con.close();
            
       } catch (SQLException ex) { 
            System.out.println(ex);
        } 
    }
    
}
