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
public class CourseDAO {
    
    public void insertCourse(int crsID, String crsCode, String crsName) throws SQLException{
        try{
            String DATABASE_URL = "jdbc:derby://localhost:1527/ADPAssignment2.0";
            String username = "Tanatswa";
            String password = "admin";
            
            Connection con = DriverManager.getConnection(DATABASE_URL, username, password);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO COURSES VALUES (?, ?, ?)");
            pstmt.setInt(1, crsID);
            pstmt.setString(2, crsCode);
            pstmt.setString(3, crsName);
            
            int i = pstmt.executeUpdate();
            System.out.println(i +"The record has been inserted into Course class.");
            con.close();
            
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public void getAllCourse(){
        try{
            String DATABASE_URL = "jdbc:derby://localhost:1527/ADPAssignment2.0";
            String username = "Tanatswa";
            String password = "admin";
            
            Connection con = DriverManager.getConnection(DATABASE_URL, username, password);
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM course");
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                System.out.println(rs.getInt("ID")+"\n" + rs.getString("Course code")+ "\n" +
                        rs.getString("Course Name"));
            }
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    }
    
