/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Prasad Chamika
 */
public class Sign_Up_as_PATIENTController extends DashboardUIController implements Initializable {

    
    @FXML
    private JFXTextField firstNameTxt;

    @FXML
    private JFXTextField lastNameTxt;

    @FXML
    private JFXTextField addressTxt;

    @FXML
    private JFXTextField phoneNumberTxt;

    @FXML
    private JFXDatePicker dateOfBirthDate;

    @FXML
    private ComboBox genderTxt;

    @FXML
    private JFXTextArea allergiesTxt;

    @FXML
    private ComboBox bloodGroupTxt;
    
    @FXML
    private JFXTextField nicTxt;

    
    //signup button. It's write data

    /**
     *
     * @param event
     */
    
      @FXML
    public void signupBtn(ActionEvent event) throws IOException{
        
        if(validateFields()&& validatePhoneNum()){
        try{
        File file = new File("user data\\patient\\data\\"+nicTxt.getText()+".txt");    
        PrintWriter printer = new PrintWriter(new FileOutputStream(file,true));  
        printer.append(firstNameTxt.getText()+" "  + lastNameTxt.getText()+ "\n" +  addressTxt.getText()+"\n"+ nicTxt.getText()+"\n"+ bloodGroupTxt.getValue() + "\n"+
                 dateOfBirthDate.getValue()+ "\n"+ genderTxt.getValue() +"\n" +phoneNumberTxt.getText() +"\n"+ allergiesTxt.getText());
             printer.close();
        }catch(FileNotFoundException e){}
         
       
        
        //Write credentials file of patients
        try{
         
         FileWriter fw = new FileWriter("user data\\patient\\credentials\\patientlogin.txt",true);
         BufferedWriter bw = new BufferedWriter(fw);
         PrintWriter pw = new PrintWriter(bw);
         pw.write(nicTxt.getText()+","+nicTxt.getText()+"\n");
         pw.close();
         }catch(FileNotFoundException  e){}
          firstNameTxt.setText(null);
          lastNameTxt.setText(null);
         addressTxt.setText(null);
         nicTxt.setText(null);
         bloodGroupTxt.setValue(null);
         dateOfBirthDate.setValue(null);
         phoneNumberTxt.setText(null);
         allergiesTxt.setText(null);
         genderTxt.setValue(null);    
        
        }
        
        
        
    }
    
    
    
    
     // Cancel Button. it's go to sign up as menu  
    public void cancelBtn(ActionEvent event) throws IOException{
        Parent signUpAsParent = FXMLLoader.load(getClass().getResource("/View/Sign_Up_as.fxml"));
        Scene signUpAsviewScene = new Scene(signUpAsParent);
        
        //This Line gets the Stage Information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(signUpAsviewScene);
        window.show();
        window.centerOnScreen();
        
       }
    // warning message for null validation
     private boolean validateFields(){
           
         
         if(firstNameTxt.getText().isEmpty() | lastNameTxt.getText().isEmpty()| addressTxt.getText().isEmpty()|
                 phoneNumberTxt.getText().isEmpty()| nicTxt.getText().isEmpty()| allergiesTxt.getText().isEmpty())
         {
            Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Validate Fields");
             alert.setHeaderText(null);
             alert.setContentText("Please Enter Into The Fields");
             alert.showAndWait();
             
             return false;
            
         }
         return true;
          }
       //warnig message to invalide Phone Number 
     private boolean validatePhoneNum(){
         Pattern p=Pattern.compile("[0][0-9]{9}");
         Matcher m = p.matcher(phoneNumberTxt.getText());
         if(m.find() && m.group().equals(phoneNumberTxt.getText())){
           return true;
         }else{
              Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Validate Phone Number");
             alert.setHeaderText(null);
             alert.setContentText("Please Enter The Valid Phone Number");
             alert.showAndWait();
             return false;
         }
       }
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<String>list1=FXCollections.observableArrayList("Male","Female");
       genderTxt.setItems(list1);
       
       ObservableList<String>list2=FXCollections.observableArrayList("A+","O+","B+","AB+","A-","O-","B-","AB-");
       bloodGroupTxt.setItems(list2);
       
        //show validation status
        RequiredFieldValidator validator = new RequiredFieldValidator();
        NumberValidator numvalidator = new  NumberValidator();
        numvalidator.setMessage("Invalied Number");
        validator.setMessage("Required Field");
    
      
        
        //validation for phone number
        phoneNumberTxt.getValidators().add(numvalidator);
        firstNameTxt.getValidators().add(validator);
        lastNameTxt.getValidators().add(validator);
        addressTxt.getValidators().add(validator);
        phoneNumberTxt.getValidators().add(validator);
        dateOfBirthDate.getValidators().add(validator);
        allergiesTxt.getValidators().add(validator);
        nicTxt.getValidators().add(validator);
   
        
        
       phoneNumberTxt.focusedProperty().addListener(new ChangeListener <Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                {
                phoneNumberTxt.validate();
                }}
        });       
        
        firstNameTxt.focusedProperty().addListener(new ChangeListener <Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                {
                firstNameTxt.validate();
                }}
        });
        
        lastNameTxt.focusedProperty().addListener(new ChangeListener <Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                {
                lastNameTxt.validate();
                }}
        });
      
        addressTxt.focusedProperty().addListener(new ChangeListener <Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                {
                addressTxt.validate();
                }}
        }); 
       
        phoneNumberTxt.focusedProperty().addListener(new ChangeListener <Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                {
                phoneNumberTxt.validate();
                }}
        }); 
    
        dateOfBirthDate.focusedProperty().addListener(new ChangeListener <Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                {
               dateOfBirthDate.validate();
                }}
        }); 
            
       allergiesTxt.focusedProperty().addListener(new ChangeListener <Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                {
                allergiesTxt.validate();
                }}
        }); 
        
       nicTxt.focusedProperty().addListener(new ChangeListener <Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                {
               nicTxt.validate();
                }}
        }); 

    }    
    
}
