package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4tourism.entities.Event;

import tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote;

public class Premium_Consulter_details_eventController extends AnchorPane implements Initializable,Serializable{

	@FXML
	private JFXTextField titre;
	@FXML
	private JFXTextField description;
	@FXML
	private JFXTextField adresse;
	@FXML
	private Label erreur;
	
	@FXML
	private JFXTextField nbplaces;
	@FXML
	private JFXTextField part;
	
	@FXML
	private JFXTextField price;
	
	@FXML
    private TextField txtDate;
	
	@FXML
    private TextField datefin;
	

	@FXML
	private JFXTextField nom;
	

	@FXML
	private JFXButton back;
	
	@FXML
	private JFXButton book;
	
	
	@FXML
	private JFXTextField nbplaceRes;
	
	@FXML
    private TextField eventType;
	
	
	@FXML
	private JFXButton reservation;
	
	
	@FXML
	private JFXButton logout;
	
	
	@FXML
    private ImageView imageView;
	
	 private final FileChooser fileChooser = new FileChooser();
	 private String pathImage, fileName;
	
	List<Event> list;
	EntityManager em;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
	InitialContext ctx;
	
			ctx = new InitialContext();
			EventServiceRemote proxy;

			proxy = (EventServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");
	
			
			list=  proxy.findEventByTitle(PremiumEventController.nomRando);
	        
		      if(!list.isEmpty()){
		          
		          Event rand = list.get(0);
		         int x= rand.getId();
			
		titre.setText(rand.getNom());
		description.setText(rand.getDescription());
		nbplaces.setText(String.valueOf(rand.getNbplaces()));
		price.setText(String.valueOf(rand.getPrice()));
		txtDate.setText(String.valueOf(rand.getDateEvent()));
		datefin.setText(String.valueOf(rand.getDatefin()));
		adresse.setText(rand.getAdresse());
		nom.setText(rand.getNom());
		eventType.setText(rand.getTypeEvent());
		part.setText(String.valueOf(rand.getNbparticipant()));
		
		
		pathImage = rand.getImage_event();
        File file = new File(pathImage);
        Image image = new Image(file.toURI().toString());

        imageView.setImage(image);
		
		
		
		      }
			
			 //Event rand = proxy.getEventByName(em,EventController.nomRando).getId();
			
		//list=  proxy.findByName(Randonneur_consulter_listeController.nomRando);
	
		
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				


	
	}
	@FXML
	private void retour(ActionEvent event) {
        
        navigate("PremiumEvent.fxml");
}
	
	private void navigate(String fxml) {
        try {
            Stage stage = new Stage();
            Stage currentStage = (Stage) back.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxml));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setResizable(false);

            stage.show();
            currentStage.close();
        } catch (IOException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	

	@FXML
	void logoutt(ActionEvent event)  {
		
		
		navigate("ClientHome.fxml");
		
	}

	


public static boolean isFloat(String s) {
    try {
        Float.parseFloat(s);
    } catch (NumberFormatException e) {
        return false;
    } catch (NullPointerException e) {
        return false;
    }
    // only got here if we didn't return false
    return true;
}
private void showMessageDialoge(String msg, String description) {
       Alert alert = new Alert(AlertType.WARNING);
       alert.setTitle("Alerte");
       alert.setHeaderText(msg);
       alert.setContentText(description);

       alert.showAndWait();
   }







private void navigate1(String fxml) {
    try {
        Stage stage = new Stage();
        Stage currentStage = (Stage) reservation.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxml));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
        currentStage.close();
    } catch (IOException ex) {
        Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
    }
}









}
	
