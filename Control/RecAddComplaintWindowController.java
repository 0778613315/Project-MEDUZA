/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Complaint;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class RecAddComplaintWindowController extends DashboardUIController implements Initializable {
    
    //create new object as fileChooser           
    FileChooser fileChooser = new FileChooser();
    Stage primaryStage = new Stage();
    
    // declare variables
    @FXML
    private JFXTextField nameTxt;

    @FXML
    private JFXTextArea actionTakenTxt;

    @FXML
    private ComboBox comTypeBox;

    @FXML
    private JFXTextField phoneNumTxt;

    @FXML
    private JFXTextArea descriptionTxt;

    @FXML
    private JFXTextArea noteTxt;
    
    @FXML
    private LocalDate date;
    
    @FXML
    private File path;
    
    @FXML
    private JFXButton btnSave;
    
    @FXML
    private JFXButton btnLoad;
    
    
    
    //submit button. It's writes complaints data to file
    @FXML
    public void submitBtn(ActionEvent event)throws  IOException{
        
        Complaint recCom=new Complaint();
        recCom.setDate(date.now().toString());
        recCom.setType(comTypeBox.getValue().toString());
        recCom.setName(nameTxt.getText());
        recCom.setPhoneNo(phoneNumTxt.getText());
        recCom.setDescription(descriptionTxt.getText());
        recCom.setActionTaken(actionTakenTxt.getText());
        recCom.setNote(noteTxt.getText());
        
        recCom.addComplaint(event);
        
        comTypeBox.setValue(null);
        nameTxt.setText(null);
        phoneNumTxt.setText(null);
        descriptionTxt.setText(null);
        actionTakenTxt.setText(null);
        noteTxt.setText(null);
       
        
        
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
                Files.copy(file.toPath(),Paths.get("user data\\complaint\\cv\\"+LocalDate.now()+" " +nameTxt.getText() +".pdf"));
            } catch (Exception ioException) {
               ioException.printStackTrace();
            }
          
          //create new object file1
          File file1 = new File(String.valueOf(path));
       
        fileChooser.setInitialFileName(LocalDate.now()+" " +nameTxt.getText() +".pdf");  
        //getting type of files 
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf file","*.pdf","*.PDF"));
        
         
    }


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         ObservableList<String>list=FXCollections.observableArrayList("Type 1","Type 2", "Type 3");
       comTypeBox.setItems(list);
    }    
    
}
