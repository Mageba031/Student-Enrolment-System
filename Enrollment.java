/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.domain;

/**
 *
 * @author Admin
 */
public class Enrollment {
    private int ID;
    private Student std;
    private Course crs;
    
    public Enrollment(){
        
    }
    
    public Enrollment(int ID,Student std, Course crs){
         this.ID = ID;
         this.std = std;
         this.crs = crs;
    }
    
    //GETTERS AND SETTERS
    
    public int getID(){
        return ID;
    }
    public void setID(){
        this.ID = ID;
    }
    
    public Student getStd(){
        return std;
    }
    public void setStd(){
        this.std = std;
    }
    
    public Course getCrs(){
        return crs;
    }
    public void setCrs(){
        this.crs = crs;
    }
    
    //toString
    public String toString(){
        return "ID:" + ID + "/n" + "Student Details: " + std + "/n" +
                "Course Details: " + crs;
    }
}
