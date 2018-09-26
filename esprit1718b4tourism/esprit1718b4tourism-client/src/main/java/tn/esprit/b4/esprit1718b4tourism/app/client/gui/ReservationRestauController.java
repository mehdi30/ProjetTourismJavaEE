/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4tourism.app.client.businessDelegate.UserServiceDelegate;
import tn.esprit.b4.esprit1718b4tourism.app.client.gui.StaticVars;
import tn.esprit.b4.esprit1718b4tourism.app.client.other.EMailSender;
import tn.esprit.b4.esprit1718b4tourism.entities.User;


public class ReservationRestauController implements Initializable {

    @FXML
    private DatePicker date;
    @FXML
    private TextField people;
    @FXML
    private TextField time;
    @FXML
    private TextArea add;
    @FXML
    private Button home;
    @FXML
    private Button send;
    @FXML
    private ImageView imgv;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
      date.setValue(LocalDate.now());
    }    

    @FXML
    private void home_action(ActionEvent event) throws IOException {
    	  Parent root = FXMLLoader.load(getClass().getResource("CherRestau.fxml"));
          Scene newScene = new Scene(root);
          Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
          window.setScene(newScene);
          window.show();
    }

    @FXML
    private void send_action(ActionEvent event) throws IOException, ParseException {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	Date today = dateFormat.parse(StaticVars.dateSysteme());
    	
    	LocalDate localDate = date.getValue();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	 String reservation_date_Str = dtf.format(localDate);
    	Date reservation_date = dateFormat.parse(reservation_date_Str);
    	
    	String msg;
        if ((msg = StaticVars.VerifierLongChaine(people.getText(), "<< how many people ? >>", 1, 20)) != "valide") {
            StaticVars.alerte(msg);
        }else if (reservation_date.compareTo(today) < 0) {
            StaticVars.alerte("Put a valid date");
        } else if ((msg = StaticVars.VerifierLongChaine(time.getText(), "<< when ? >>", 1, 20)) != "valide") {
            StaticVars.alerte(msg);
        } else {
    	
    	String mail = "Demanding reservation from "+ StaticVars.currentUser.getEmail();
    	mail += "\n People = "  +people.getText();
    	mail += "\n Date : " + date.getValue().toString() ;
    	mail += "\n Time = " + time.getText();
    	mail += "\n \n + " + add.getText() ;
    	
    //	StaticVars.alerte("!"+ StaticVars.getSelectedRestaurant().getOwner() +"!");
    	
       	  try {
				String[] to = { StaticVars.getSelectedRestaurant().getOwner() };
				EMailSender.sendMail("tourism.orientt@gmail.com", "123azert", mail , to, "Restaurant reservation");
				StaticVars.alerte("your reservation was sent ! check your mail for response");
			} catch (Exception e) {
				System.out.println("no mail was sent");
				  StaticVars.alerte("there is an error, try later");
				  
				 Parent root = FXMLLoader.load(getClass().getResource("CherRestau.fxml"));
		          Scene newScene = new Scene(root);
		          Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		          window.setScene(newScene);
		          window.show();
			}
    	
    	  
    	  
    	  Parent root = FXMLLoader.load(getClass().getResource("CherRestau.fxml"));
          Scene newScene = new Scene(root);
          Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
          window.setScene(newScene);
          window.show();
        }
    }
    
}
