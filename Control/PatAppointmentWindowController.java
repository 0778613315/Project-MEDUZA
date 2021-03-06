/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import static Control.UserLoginController.primaryKey;
import static Control.UserLoginController.profilePicture;
import static Control.UserLoginController.staticUserID;
import static Control.UserLoginController.staticUserName;
import Model.Appointment;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PatAppointmentWindowController extends DashboardUIController implements Initializable {
    
    //Triggers the Add new appointmend button in the appointment window
    @FXML
    public void addNewAppointment(ActionEvent event) throws IOException{
         FXMLLoader loader =new FXMLLoader(getClass().getResource("/View/Dashboards/Patient/PatAddAppointmentWindow.fxml"));
         Parent root = loader.load();

         DashboardUIController welcome =loader.getController();
         welcome.showInformation(staticUserName, staticUserID);
         welcome.showInformation(staticUserName);
         welcome.showProfilePicture(profilePicture);
         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
         window.setScene(new Scene(root));
         window.show();
         window.centerOnScreen();  
        
       
    }
    
    
     @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<Appointment, String> idCol;

    @FXML
    private TableColumn<Appointment, String>  dateCol;

    @FXML
    private TableColumn<Appointment, String>  timeCol;

    @FXML
    private TableColumn<Appointment, String>  symptomsCol;

    @FXML
    private TableColumn<Appointment, String>  moCol;

    @FXML
    private TableColumn<Appointment, String>  statusCol;

    @FXML
    private TableColumn<Appointment, String>  optionsCol;
    
    
    String filepathpending ="user data//appointment//"+primaryKey+".txt";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try{
          ArrayList<Appointment> appointmentArrayList = Appointment.viewAppointment(filepathpending); //grt data and set arraylist
          
          int appointmentCount = appointmentArrayList.size();
          
          ObservableList<Appointment> appointments = FXCollections.observableArrayList();   
          for(int i =0;i< appointmentCount;i++){
          appointments.add(appointmentArrayList.get(i));
      
          }
          idCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("userID"));
        
          dateCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("appDate"));
          timeCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("appTime"));
          symptomsCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("symptoms"));
          moCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("specArea"));
          statusCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("status"));
          optionsCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("options"));
            //set data in each colloms and row
          appointmentTable.setItems(appointments);
           
         }catch(Exception e){}
    }    
    
}
