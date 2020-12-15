/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import javafx.event.ActionEvent;

public class MedicalOfficer extends user{
    
    private String staffID,email,dateJoined,specialityArea;

    

       //Getters
    public String getStaffID() {
        return staffID;
    }
    public String getEmail() {
        return email;
    }
    public String getDateJoined() {
        return dateJoined;
    }

    public String getSpecialityArea() {
        return specialityArea;
    }
    
    //Setters
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public void setSpecialityArea(String specialityArea) {
        this.specialityArea = specialityArea;
    }
    
    
    
    public void signup() throws FileNotFoundException{
    File file = new File("user data\\medical officer\\data\\"+getStaffID()+".txt");
           PrintWriter printer = new PrintWriter(new FileOutputStream(file,true));
           printer.append(getFName()+"\n"+getLName()
                   +"\n"+getAddress()+"\n"+getPhoneNumber()+"\n"+getDOB()+"\n"+getStaffID()
                   +"\n"+getEmail()+"\n"+getDateJoined()+"\n" +getGender()+"\n"+getSpecialityArea()+"\n");
          
           printer.close();
    }
}