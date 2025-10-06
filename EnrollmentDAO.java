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
public class EnrollmentDAO {
     public void insertEnrollment(int ID, int stdID, int crsID){
         try{
             String DATABASE_URL = "jdbc:derby://localhost:1527/ADPAssignment2.0";
             String username = "Tanatswa";
             String password = "admin";
             
             Connection con  = DriverManager.getConnection(DATABASE_URL, username, password);
             PreparedStatement pstmt = con.prepareStatement( "INSERT INTO ENROLLMENT VALUES(?,?,?)");
             pstmt.setInt(1, ID);
             pstmt.setInt(2, stdID);
             pstmt.setInt(3,crsID);
             
             int i = pstmt.executeUpdate();
             System.out.println(i+ "The records have been inserted into Enrollment Class");
             con.close();
             
         } catch (SQLException ex) {
             System.out.println(ex);
          
         }
     }
     
     public void getAllEnrollment(){
         try{
             String DATABASE_URL = "jdbc:derby://localhost:1527/ADPAssignment2.0";
             String username = "Tanatswa";
             String password = "admin";
             
              Connection con  = DriverManager.getConnection(DATABASE_URL, username, password);
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM enrollment");
             
             ResultSet rs = pstmt.executeQuery();
             
             while(rs.next()){
                 System.out.println(rs.getInt(1)+"" +rs.getInt(2)+ "" +rs.getInt(3));  
             }
             con.close();
              
         } catch (SQLException ex) {
             System.out.println(ex);
         }
     }
}
