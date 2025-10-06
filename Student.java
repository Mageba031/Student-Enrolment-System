/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package za.ac.cput.domain;

/**
 *
 * @author Admin
 */
public class Student {
    private String stdID;
    private String stdName;
    private String stdSurname;
    private String stdNmbr;
    
    public Student(){
        
    }
    
    public Student (String stdID, String stdName, String stdSurname, String stdNmbr){
        this.stdID = stdID;
        this.stdName = stdName;
        this.stdSurname = stdSurname;
        this.stdNmbr = stdNmbr;
    }
    
    //GETTERS AND SETTERS
    public String getStID(){
        return stdID;
    }
    public void setStID(){
        this.stdID = stdID;
    }
    
    public String getStdName(){
        return stdName;
    }
    public void setStdName(){
        this.stdName = stdName;
    }
    
    public String getStdSurname(){
        return stdSurname;
    }
    public void setStdSurname(){
        this.stdSurname = stdSurname;
    }
    
    public String getStdNmbr(){
        return stdNmbr;
    }
    public void setStdNo(){
        this.stdNmbr = stdNmbr;
    }
    
    //toString
    public String toString(){
        return "StudentID:" + stdID + "/n" + "Student Name:" + stdName + "/n" +
                "Student Surname:" + stdSurname + "/n" + "Student Number: " +
                stdNmbr;
    }
}

   
