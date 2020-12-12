/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class Sign_Up_as_MOController extends DashboardUIController implements Initializable {
    
    //create new object as fileChooser           
    FileChooser fileChooser = new FileChooser();
    Stage primaryStage = new Stage();
    
    @FXML
    private JFXTextField moFirstName;
    @FXML
    private JFXTextField moLastName;
    @FXML
    private JFXTextField moAddress;
    @FXML
    private JFXTextField moPhoneNum;
    @FXML
    private JFXTextField moStaffID;
    @FXML
    private JFXTextField moStaffEmail;
    @FXML
    private JFXDatePicker moDateOfBirth;
    @FXML
    private JFXDatePicker moDateOfJoin;
    @FXML
    private JFXComboBox<String> moGender;
    @FXML
    private JFXComboBox<String> moSpecialityArea;
    @FXML
    private JFXButton btnAddFile;
    @FXML
    private File path;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnLoad;
    
    
    // Cancel Button. it's go to sign up as menu  
    @FXML
    public void cancelBtn(ActionEvent event) throws IOException{
        Parent signUpAsParent = FXMLLoader.load(getClass().getResource("/View/Sign_Up_as.fxml"));
        Scene signUpAsviewScene = new Scene(signUpAsParent);
        
        //This Line gets the Stage Information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(signUpAsviewScene);
        window.show();
        window.centerOnScreen();
        
       }
    
    @FXML
    private void signupbtn(ActionEvent event) throws IOException {
        String value = moDateOfJoin.getValue().toString();//get date as a String value
        String value1 = moDateOfBirth.getValue().toString();//get date as a String value
       
        
        //write details in a text file
         try
       {
           File file = new File("user data\\medical officer\\data\\"+moStaffID.getText()+".txt");
           PrintWriter printer = new PrintWriter(new FileOutputStream(file,true));
           printer.append(moFirstName.getText()+"\n"+moLastName.getText()
                   +"\n"+moAddress.getText()+"\n"+moPhoneNum.getText()+"\n"+moDateOfBirth.getValue().toString()+"\n"+moStaffID.getText()
                   +"\n"+moStaffEmail.getText()+"\n"+moDateOfJoin.getValue().toString()+"\n" +moGender.getValue().toString()+"\n"+moSpecialityArea.getValue().toString()+"\n");
          
           printer.close();
       }
        catch(Exception e){}
         
         
          //Write credentials file of MO
         try{
         
         FileWriter fw = new FileWriter("user data\\medical officer\\credentials\\medicalofficerlogin.txt",true);
         BufferedWriter bw = new BufferedWriter(fw);
         PrintWriter pw = new PrintWriter(bw);
         pw.write(moStaffID.getText()+","+moStaffID.getText()+"\n");
         pw.close();
         }catch(FileNotFoundException  e){}
         
         
         //setiing values for declared variables
         moFirstName.setText(null);
         moLastName.setText(null);
         moAddress.setText(null);
         moPhoneNum.setText(null);
         moDateOfBirth.setValue(null);
         moStaffID.setText(null);
         moStaffEmail.setText(null);
         moDateOfJoin.setValue(null);        
         moGender.setValue(null);
         moSpecialityArea.setValue(null);
      }
    
    @FXML
    private void Select(ActionEvent event) {
        
        String value = moGender.getValue().toString();//get combo gender value as String value
        String value1 = moSpecialityArea.getValue().toString();//get combo specialityArea value as String value
        
       
        
        
        
                 
    }
     //to add a photograph for staff photograph
    @FXML
     private void onclickbtnAddFile(ActionEvent event)throws IOException{
         Stage stage =new Stage();
         FileChooser fileChooser =new FileChooser();
        
         File selectedFile = fileChooser.showOpenDialog(stage);
         
        
         Image moImg1= new Image(selectedFile.toURI().toString());
         saveToFile(moImg1,"Photo");
         
     }
     //saving photogrgaph
     private void saveToFile(Image image,String name)throws IOException{
         File fileoutput = new File ("user data\\medical officer\\photo\\"+moStaffID.getText()+".jpg");
         BufferedImage BI= SwingFXUtils.fromFXImage(image,null);
         ImageIO.write(BI,"jpg",fileoutput);
     
     }
     
      //opening and saving document file    
    @FXML
    public void openFile(ActionEvent actionEvent) throws IOException {
       
       
        fileChooser.setInitialDirectory(new File("C:\\Users\\Theja"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("doc file","*.doc","*.docx"));

        File file = fileChooser.showOpenDialog(primaryStage);
       // File desination = fileChooser.showSaveDialog(primaryStage);
        path=file.getAbsoluteFile();
        
        
         //saving file given path
          try {
                Files.copy(file.toPath(),Paths.get("user data\\medical officer\\cv\\"+moStaffID.getText()+".doc"));
            } catch (Exception ioException) {
               ioException.printStackTrace();
            }
          
          //create new object file1
          File file1 = new File(String.valueOf(path));
       
        fileChooser.setInitialFileName(moStaffID.getText()+".txt");  
        //getting type of files 
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("doc file","*.doc","*.docx"));
        
         
    }

   
    
   
            
            
      
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //add combo box values
       ObservableList<String>list=FXCollections.observableArrayList("Male","Female");
       moGender.setItems(list);
       ObservableList<String>list1=FXCollections.observableArrayList("A","B","C");
       moSpecialityArea.setItems(list1);
       }



} 