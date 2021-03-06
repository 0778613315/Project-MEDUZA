/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Postal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class RecAddReceivedPostController extends DashboardUIController implements Initializable {
    
    //create new object as fileChooser           
    FileChooser fileChooser = new FileChooser();
    Stage primaryStage = new Stage();
    
    
    //Declaring Variable
    @FXML
    private JFXTextField postName;

    @FXML
    private JFXTextArea postNote;

    @FXML
    private JFXTextField postReff;

    @FXML
    private JFXTextArea postFrom;

    @FXML
    private JFXTextField postTo;
    
    @FXML
    private JFXDatePicker postDate;
    
    @FXML
    private File path;
    
    @FXML
    private JFXButton btnSave;
    
    @FXML
    private JFXButton btnLoad;
    
    
   // add received post -Submit Button Action Methode    
    @FXML  
      public void submitBtn(ActionEvent event) throws FileNotFoundException {
          if(validateFields()){
    //write values in another text file
      Postal obj = new Postal();
      obj.setFrom(postFrom.getText());
      obj.setTo(postTo.getText());
      obj.setAddress(postName.getText());
      obj.setRefferenceNum(postReff.getText());
      obj.setNote(postNote.getText());
      obj.setDate( postDate.getValue().toString());
      
      obj.AddReceivedPost(event);
      
         postName.setText(null);
         postFrom.setText(null);
         postTo.setText(null);
         postReff.setText(null);
         postDate.setValue(null);
         postNote.setText(null);
      
      }}
     // warning message for null validation
     private boolean validateFields(){
        if(   postName.getText().isEmpty() | postNote.getText().isEmpty()|postReff.getText().isEmpty()|postFrom.getText().isEmpty()|
                postDate.getEditor().getText().isEmpty()|postTo.getText().isEmpty())
         {
              Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Validate Fields");
             alert.setHeaderText(null);
             alert.setContentText("Please fill all the fields");
             alert.showAndWait();
            return false;
          }
        return true;
        }
      
        //opening and saving document file    
    @FXML
    public void openFile(ActionEvent actionEvent) throws IOException {
       
       
        fileChooser.setInitialDirectory(new File("C:\\Users\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf file","*.pdf","*.PDF"));

        File file = fileChooser.showOpenDialog(primaryStage);
       // File desination = fileChooser.showSaveDialog(primaryStage);
        path=file.getAbsoluteFile();
        
        
         //saving file given path
          try {
                Files.copy(file.toPath(),Paths.get("user data\\receptionist\\postal\\received postal\\cv\\"+postReff.getText()+".pdf"));
            } catch (Exception ioException) {
               ioException.printStackTrace();
            }
          
          //create new object file1
          File file1 = new File(String.valueOf(path));
       
        fileChooser.setInitialFileName(postReff.getText()+".pdf");  
        //getting type of files 
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf file","*.pdf","*.PDF"));
        
         
    }
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //show validation status
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Required Field");
        
       postName.getValidators().add(validator);
       postNote.getValidators().add(validator);
       postReff.getValidators().add(validator);
       postFrom.getValidators().add(validator);
       postTo.getValidators().add(validator);
       postDate.getValidators().add(validator);
        
        
       postName.focusedProperty().addListener(new ChangeListener <Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                {
                postName.validate();
                }}
        });
       
        postNote.focusedProperty().addListener(new ChangeListener <Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                {
                postNote.validate();
                }}
        });
         
       postReff.focusedProperty().addListener(new ChangeListener <Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                {
                postReff.validate();
                }}
        }); 
       
        postFrom.focusedProperty().addListener(new ChangeListener <Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                {
                postFrom.validate();
                }}
        }); 
      
       postTo.focusedProperty().addListener(new ChangeListener <Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                {
               postTo.validate();
                }}
        }); 
       
         postDate.focusedProperty().addListener(new ChangeListener <Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                {
                 postDate.validate();
                }}
        }); 
    }    
    
}
