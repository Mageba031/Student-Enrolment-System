/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.domain;

/**
 *
 * @author Admin
 */
public class Course {
    private String crsID;
    private String crsCode;
    private String crsName;
    
    public Course() {
        
    }
    
    public Course(String crsID, String crsCode, String crsName){
        this.crsID = crsID;
        this.crsCode = crsCode;
        this.crsName = crsName;
    }
    
    //GETTERS AND SETTERS
    public String getCrsID(){
        return crsID;
    }
    public void setCrsID(){
        this.crsID = crsID;
    }
    
    public String getCrsCode(){
        return crsCode;
    }
    public void setCrsCode(){
        this.crsCode = crsCode;
    }
    
    public String getCrsName(){
        return crsName;
    }
    public void setCrsName(){
        this.crsName = crsName;
    }
    
    //toString
    public String toString(){
       return "Course ID: " + crsID + "/n" + "Course Code: " + crsCode + "/n"
                + "Course Name: " + crsName;
    }
}
