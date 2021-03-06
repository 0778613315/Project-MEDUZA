/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import static Control.UserLoginController.profilePicture;
import static Control.UserLoginController.staticUserName;
import Model.Appointment;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class RecAppointmentWindowController extends DashboardUIController implements Initializable {
    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<Appointment,String> idCol;

    @FXML
    private TableColumn<Appointment,String>patientCol;

    @FXML
    private TableColumn<Appointment,String> dateCol;

    @FXML
    private TableColumn<Appointment,String> timeCol;

    @FXML
    private TableColumn<Appointment,String> symptomsCol;

    @FXML
    private TableColumn<Appointment,String> moCol;

    @FXML
    private TableColumn<Appointment,String> statusCol;

    @FXML
    private TableColumn<Appointment,String>optionsCol;
    
    public static String userIDAppointment,appointmentNo;
    private static String status;
    
    private static String pending ="pending";
    
    //assign the file path in string variable because we can't pass the parameter into initialize methode
    String filepathpending ="user data//database//pendingappointment.txt";

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
          ArrayList<Appointment> appointmentArrayList = Appointment.viewAppointment(filepathpending);
          
          int appointmentCount = appointmentArrayList.size();
          
          ObservableList<Appointment> appointments = FXCollections.observableArrayList();
          for(int i =0;i< appointmentCount;i++){
          appointments.add(appointmentArrayList.get(i));
      
          }
          idCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("userID"));
          patientCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("name"));
          dateCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("appDate"));
          timeCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("appTime"));
          symptomsCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("symptoms"));
          moCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("specArea"));
          statusCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("status"));
          optionsCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("options"));
       
          appointmentTable.setItems(appointments);
           
         }catch(Exception e){}
        
        
        
        
        
    }  
    
     @FXML
    void deleteAppointment(ActionEvent event) {  // deleteaction event
        
        try{
        String name; 
        ObservableList<Appointment> allAppointment,singleAppointment;
        allAppointment = appointmentTable.getItems();
        singleAppointment =appointmentTable.getSelectionModel().getSelectedItems();
        userIDAppointment = appointmentTable.getSelectionModel().getSelectedItem().getUserID(); // get user id in select row and set it static variable
        name=appointmentTable.getSelectionModel().getSelectedItem().getName(); //get name in table set it name
        appointmentNo =appointmentTable.getSelectionModel().getSelectedItem().getOptions();//get user id in selected row
        
        singleAppointment.forEach(allAppointment::remove); //delete select row
        Appointment appObj = new Appointment();            //create object in appointmen class
        appObj.deleteAppointment("user data//database//pendingappointment.txt", "user data//database//temp.txt"); //call appointment delete methode
        appObj.deleteAppointment("user data\\appointment\\"+userIDAppointment+".txt", "user data\\appointment\\temp.txt");
        Alert alert = new Alert(Alert.AlertType.WARNING); //display Warning message
        alert.setContentText("You deleted "+name.toUpperCase()+"'s appointment..!");
        alert.show();
        }catch(Exception e){
        Alert alert = new Alert(Alert.AlertType.WARNING); //display Warning message
        alert.setContentText("Selecet Appointment and press delete button");
        alert.show();   
        }
        
        
    }

    @FXML // edit button
    void editAppointment(ActionEvent event) throws IOException {
        try{
        ObservableList<Appointment> allAppointment,singleAppointment;
        allAppointment = appointmentTable.getItems();
        singleAppointment =appointmentTable.getSelectionModel().getSelectedItems();
        userIDAppointment = appointmentTable.getSelectionModel().getSelectedItem().getUserID(); // get user id in select row and set it static variable
        appointmentNo =appointmentTable.getSelectionModel().getSelectedItem().getOptions();//get user id in selected row
        // all items store in static object in appointment class 
        RecAppointmentEditController.selectedFeield=(Appointment)appointmentTable.getSelectionModel().getSelectedItem();
        singleAppointment.forEach(allAppointment::remove);
        // load appointment edit window   
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/View/Dashboards/Receptionist/RecAppointmentEdit.fxml"));
        Parent root = loader.load();
        DashboardUIController welcome =loader.getController();
        welcome.showInformation(staticUserName);
        welcome.showProfilePicture(profilePicture);
         
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
        window.centerOnScreen();
        }catch(Exception e){
        
        Alert alert = new Alert(Alert.AlertType.WARNING); //display Warning message
        alert.setContentText("Selecet Appointment and then press edit  button");
        alert.show();
        System.out.println(e);
        }
        
    }
    
        
    
    @FXML  //approved appointment action event
    void approveAppointment(ActionEvent event) {
        try{
         String name;   
        ObservableList<Appointment> allAppointment,singleAppointment;
        allAppointment = appointmentTable.getItems();
        singleAppointment =appointmentTable.getSelectionModel().getSelectedItems();
        userIDAppointment = appointmentTable.getSelectionModel().getSelectedItem().getUserID(); // get user id in select row and set it static variable
        status=appointmentTable.getSelectionModel().getSelectedItem().getStatus();
        appointmentNo =appointmentTable.getSelectionModel().getSelectedItem().getOptions();//get user id in selected row
        name=appointmentTable.getSelectionModel().getSelectedItem().getName();
        //get status in table and asign the value in static variable
        if (status.equals(pending)){        //compare  status (pending is an static variable)
        Appointment appObj = new Appointment();       //create object in appointment class
        
        appObj.approveAppointment("user data//database//pendingappointment.txt", "user data//database//approveappointment.txt");  //call approved appointment methode
        appObj.deleteAppointment("user data//database//pendingappointment.txt", "user data//database//temp.txt");  //call delete appointment methode
        singleAppointment.forEach(allAppointment::remove);   // delete selected row in table
        // userid are equal display message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You approved "+name.toUpperCase()+"'s appointment..!");
        alert.show();
        
        }
        }
        catch(Exception e){
         Alert alert = new Alert(Alert.AlertType.WARNING); //display Warning message
        alert.setContentText("Selecet Appointment and press approved button");
        alert.show();  
        }
    }
    
    
    
    
}  
