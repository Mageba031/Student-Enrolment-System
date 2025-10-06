/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.domain;

/**
 *
 * @author Admin
 */
public class User {
    private int ID;
    private String username;
    private String password;
    private String role;
    
    public User(){
        
    }
    
    public User(int ID, String username, String password, String role){
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    //GETTERS AND SETTERS
    public int getID(){
        return ID;
    }
    public void setID(){
        this.ID = ID;
    }
    
    public String getUsername(){
        return username;
    }
    public void setUsername(){
        this.username = username;
    }
    
    public String getPassword(){
        return password;
    }
    public void setPassword(){
        this.password = password;
    }
    
    public String getRole(){
        return role;
    }
    public void setRole(){
        this.role = role;
    }
    
    public String toString(){
       return "ID: " +ID + "/n" +"Username: " + username+ "/n" +
               "Password: " + password + "/n" + "Role:" + role;
    }
    
}
