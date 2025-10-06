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

/**
 *
 * @author Admin
 */
public class StudentDAO {
    public void insertStudent(int ID, String stdName, String stdSurname, String stdNmbr){
        try{
            String DATABASE_URL =  "jdbc:derby://localhost:1527/ADPAssignment2.0";
            String username = "Tanatswa";
            String password = "admin";
            
            Connection con = DriverManager.getConnection(DATABASE_URL, username, password);
            
            PreparedStatement pstmt = con.prepareStatement("add into Student class(?,?,?,?)");
            pstmt.setInt(1, ID);
            pstmt.setString(2,stdName);
            pstmt.setString(3, stdSurname);
            pstmt.setString(4, stdNmbr);
            
             int i = pstmt.executeUpdate();
             System.out.println(i + "The record is inserted into Students");
             con.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    
    public void updateStudent(int ID, String newStdName, String newStdSurname, String newStdNmbr){
        try{
            String DATABASE_URL = "jdbc:derby://localhost:1527/ADPAssignment2.0";
            String username = "Tanatswa";
            String password = "admin";
            
            Connection con = DriverManager.getConnection(DATABASE_URL, username, password);
            
            PreparedStatement pstmt = con.prepareStatement("Update Student class.(?,?,?,?)");
            pstmt.setInt(1, ID);
            pstmt.setString(2, newStdName);
            pstmt.setString(3, newStdSurname);
            pstmt.setString(4, newStdNmbr);
            
            int i = pstmt.executeUpdate();
            System.out.println(i + "Records are updated");
            con.close();
        }catch(Exception ex){
            System.out.println(ex);
            
        }
        
    }
    
    public void deleteStudent(int ID){
        try{
            String DATABASE_URL = "jdbc:derby://localhost:1527/ADPAssignment2.0";
            String username = "Tanatswa";
            String password = "admin";
            
            Connection con = DriverManager.getConnection(DATABASE_URL, username, password);
            
            PreparedStatement pstmt = con.prepareStatement("Delete from student class");
            pstmt.setInt(1, ID);
            
            int i = pstmt.executeUpdate();
            System.out.println("The record is deleted from Student class");
            con.close();
        }catch(Exception ex){
            System.out.println(ex);
        }   
    }
    
    public void getAllStudent(){
        try{
            String DATABASE_URL = "jdbc:derby://localhost:1527/ADPAssignment2.0";
            String username = "Tanatswa";
            String password = "admin";
            
            Connection con = DriverManager.getConnection(DATABASE_URL, username, password);
            
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Student");
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                System.out.println(rs.getInt("id") + "\n" + rs.getString("name") + "\n" + rs.getString("surname") + "\n" + rs.getString("studentNmbr"));
            }
            con.close();
            
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
