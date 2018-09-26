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
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4tourism.entities.Event;
import tn.esprit.b4.esprit1718b4tourism.entities.Role;
import tn.esprit.b4.esprit1718b4tourism.services.EventReservationServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote;

public class Tourist_Consulter_details_eventController extends AnchorPane implements Serializable,Initializable {
	

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
    private ImageView imageView;
	
	@FXML
	private Text totalprice;
	@FXML
    private ProgressIndicator progressIndi;
	
	 private final FileChooser fileChooser = new FileChooser();
	 private String pathImage, fileName;
	
	List<Event> list;
	EntityManager em;
	 @FXML
		private JFXButton logout;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//   <ProgressIndicator fx:id="progressIndi" layoutX="370.0" layoutY="293.0" prefHeight="84.0" prefWidth="83.0" progress="0.0" />
	/*	if(proxy.CheckExpAndUser(ExperienceBController.idEvent,1).isEmpty()){
			progressIndi.setProgress(-1.0f);

		}else {
			bookvalidaion.setVisible(false);
			creditCard.setVisible(false);
			progressIndi.setProgress(1.0f);

		}*/
		
		
		try {
	InitialContext ctx;
	
			ctx = new InitialContext();
			EventServiceRemote proxy;
			EventReservationServiceRemote proxy1;

			proxy = (EventServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");
			proxy1 = (EventReservationServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventReservationService!tn.esprit.b4.esprit1718b4tourism.services.EventReservationServiceRemote");
			
			list=  proxy.findEventByTitle(TouristEventController.nomRando);
	        
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
			
		  /*    
		  if(proxy1.CheckExpAndUser(ExperienceBController.idEvent,StaticVars.currentUser.getId()).isEmpty()){
					progressIndi.setProgress(-1.0f);

				}else {
					book.setVisible(false);
					//creditCard.setVisible(false);
					progressIndi.setProgress(1.0f);
				}
		    */
		      
			 //Event rand = proxy.getEventByName(em,EventController.nomRando).getId();
			
		//list=  proxy.findByName(Randonneur_consulter_listeController.nomRando);
	
		
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				


	
	}
	@FXML
	private void retour(ActionEvent event) {
        
        navigate("TouristEvent.fxml");
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



@FXML
private void Book(ActionEvent event)
{
	if(StaticVars.currentUser.getRole()==Role.Visitor) {
		StaticVars.alerte("you must logged in");
	}
	else {
		
		try {
			InitialContext ctx;
			
			ctx = new InitialContext();
			EventReservationServiceRemote proxy;
			EventServiceRemote proxy1;
			
			proxy = (EventReservationServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventReservationService!tn.esprit.b4.esprit1718b4tourism.services.EventReservationServiceRemote");
			proxy1 = (EventServiceRemote)ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");
			//EventReservationServiceRemote r1 = new EventReservationServiceRemote() 
			
			//ReservationEventPK r2 = new ReservationEventPK(int idUser, int idEvent);
			
			
			int nbplacestapes=	Integer.parseInt((nbplaceRes.getText()));
			
			int nbporiginale =TouristEventController.nb;
			
			
			int available =  nbporiginale- nbplacestapes;
			System.out.println(available);
			boolean showAlerte = false;
			String des = "";
			String msg = "Error you must Verify";
			
			// String number= String.valueOf(nbplacestapes);
			
			if (nbplaceRes.getText().length()==0|| nbporiginale == 0 || nbplacestapes > nbporiginale || nbplacestapes ==0 
					|| !isFloat((nbplaceRes.getText())) ||
					Float.parseFloat(nbplaceRes.getText())<=0  ||!proxy.CheckExpAndUser(TouristEventController.idEvent,StaticVars.currentUser.getId()).isEmpty()){
				
				showAlerte = true;
				des = "You must Verify your Booking Operation";
				
				if (showAlerte) {
					showMessageDialoge(msg, des);
				} 
				
			}else {
				// currentUser.getCurrentUser().getId();
				//proxy.AddReservationEvent( StaticVars.currentUser.getId(), TouristEventController.idEvent, nbplacestapes);
				proxy.AddReservationEventNew(StaticVars.currentUser.getId(), TouristEventController.idEvent, nbplacestapes, TouristEventController.nomRando);
				proxy1.UpdatePlaces(available, TouristEventController.idEvent);
				proxy1.Participation(TouristEventController.idEvent, nbplacestapes);
				
				
				int nbplacecalcul = Integer.parseInt((nbplaceRes.getText()));
				int ttprice = nbplacecalcul * TouristEventController.prix;
				
				System.out.println(ttprice);
				
				totalprice.setText("$" + String.valueOf(ttprice));
				
				
				showAlerte = true;
				des = "Succefully Operation";
				String msge = "Succefully Operation";
				
				if (showAlerte) {
					showMessageDialoge(msge, des);
				} 
				navigate1("TouristEvent.fxml");
				
			}
		}
		
		
		
		
		catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("error");
			
			
		}

	}
	
	 

}



@FXML
private void reservationhaha(ActionEvent event)
{
navigate1("AllReservations.fxml");



}

@FXML
void logoutt(ActionEvent event) {
	
	navigate("Login.fxml");
	
}




}
	
