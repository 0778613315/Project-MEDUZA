/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import static Control.UserLoginController.primaryKey1;
import Model.user;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ashan Isuru
 */
public class RecChangePasswordController  extends DashboardUIController   implements Initializable {

     @FXML
    private JFXPasswordField currentPassword;

    @FXML
    private JFXPasswordField newPassword1;

    @FXML
    private JFXPasswordField newPassword2;

    @FXML
    private Label warningMsg;
    
    
    private  String  filepath = "user data\\receptionist\\credentials\\receptionistlogin.txt";
    private  String tempFile = "user data\\receptionist\\credentials\\temp.txt";
    

    @FXML
    void cancelBtn(ActionEvent event) throws IOException {
        
        Parent profileWindow = FXMLLoader.load(getClass().getResource("/View/Dashboards/Receptionist/ReceptionistProfileWindow.fxml"));
        Scene profileViewScene = new Scene(profileWindow);
        
        //This Line gets the Stage Information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(profileViewScene);
        window.show();
        window.centerOnScreen();


    }

    
    
    
    
    
    @FXML
    void submitBtn(ActionEvent event) throws IOException {
        if(primaryKey1.equals(currentPassword.getText())){
            warningMsg.setText(null);
            if(newPassword1.getText().equals(newPassword2.getText())){
                
               user o =new user();
               o.changePassword(filepath, currentPassword.getText(),newPassword1.getText(), newPassword2.getText(), tempFile, warningMsg);
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setContentText("Password change successfully !");
               alert.show();
            }else{
                warningMsg.setText("You must enter the same passwor twice in oder to confirm it.");
            }
        }else{
            warningMsg.setText("Your current password was incorrect.");
        }
       
    }
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
