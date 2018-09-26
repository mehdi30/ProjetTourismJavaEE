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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4tourism.entities.Event;
import tn.esprit.b4.esprit1718b4tourism.entities.EventReservation;
import tn.esprit.b4.esprit1718b4tourism.services.EventReservationServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote;

public class Consulter_details_reservationController  implements Initializable,Serializable{

	
	public static int available;
	public static int nb_place_booked;
	public static int nbparticipant;
	
	@FXML
	private Label erreur;
	@FXML
	private Label name;
	@FXML
	private Label description;
	@FXML
	private Label begindate;
	@FXML
	private Label enddate;
	@FXML
	private Label adress;
	@FXML
	private Label type;
	@FXML
	private Label price;
	@FXML
	private Label avaiplaces;
	@FXML
	private Label partinbr;
	@FXML
	private Label book;
	
	@FXML
    private ImageView imageView;
	
	
	@FXML
	private JFXButton retour;
	
	
	@FXML
	private JFXButton updatereser;
	
	@FXML 
	private JFXTextField texupdate;
	
	@FXML
	private JFXButton gotoforum;
	
	List<EventReservation> list;
	
	@FXML
	private JFXButton logout;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			InitialContext ctx;
			
			
			
					ctx = new InitialContext();
					EventReservationServiceRemote proxy;

					proxy = (EventReservationServiceRemote) 
			ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventReservationService!tn.esprit.b4.esprit1718b4tourism.services.EventReservationServiceRemote");
			
					
					//list=  proxy.findReservationByIdEvent(AllReservationsController.euuf);
					list=  proxy.findReservationByIdUser(AllReservationsController.iduser);
			        
				      if(!list.isEmpty()){
				          
				          EventReservation rand = list.get(0);
				        // int x= rand.get
					
				name.setText(AllReservationsController.nom_event);
				description.setText(AllReservationsController.description_event);
				avaiplaces.setText(String.valueOf(AllReservationsController.nb_places_event));
				price.setText(String.valueOf(rand.getEvent().getPrice()));
				
				begindate.setText(String.valueOf(AllReservationsController.date_event));
				enddate.setText(String.valueOf(AllReservationsController.date_fin));
				adress.setText(AllReservationsController.adresse_event);
				
				type.setText(AllReservationsController.type_event);
				partinbr.setText(String.valueOf(AllReservationsController.nbr_part));
				//imageView.setText(String.valueOf(rand.getEvent().getImage_event()));
				book.setText(String.valueOf(AllReservationsController.nb_place_booked));
				//book.setText(String.valueOf(nb_place_booked));
				available = rand.getEvent().getNbplaces();
			
				
				
				      }
					
					 //Event rand = proxy.getEventByName(em,EventController.nomRando).getId();
					
				//list=  proxy.findByName(Randonneur_consulter_listeController.nomRando);
			
				
						} catch (NamingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
}

	
	@FXML
	private void update(ActionEvent event) throws NamingException {
		
		InitialContext ctx;
		InitialContext ctxe;
		//ctx = new InitialContext();
		//EventReservationServiceRemote proxy;

		//proxy = (EventReservationServiceRemote) 
//ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventReservationService!tn.esprit.b4.esprit1718b4tourism.services.EventReservationServiceRemote");

		
		//list=  proxy.findReservationByIdEvent(AllReservationsController.euuf);
        
	     // if(!list.isEmpty()){
	          
	       //   EventReservation rand = list.get(0);
	   // int nbplaceEvent=      rand.getEvent().getNbplaces();
		boolean showAlerte = false;
	     String des = "";
	     String msg = "Error you must Verify";
	 int nbpl= Integer.parseInt(texupdate.getText())  ;
	 System.out.println("nbre de places tapés:"+nbpl);
	 System.out.println("availables places:"+AllReservationsController.nb_places_event);
	 
	 
	  //System.out.println(nbpl);
	  //System.out.println(available);
	 if (texupdate.getText().length()==0 || nbpl > AllReservationsController.nb_places_event || texupdate.getText().equals("") ||
			 nbpl ==0  ||  !isFloat((texupdate.getText()))  || Float.parseFloat(texupdate.getText())<=0){
			
			showAlerte = true;
			 des = "You must Verify your Booking Operation";
			 
			 if (showAlerte) {
		         showMessageDialoge(msg, des);
		     } 	
	 
	 
	    
	   
	}else {
		
		
		
		
		ctx = new InitialContext();
		EventReservationServiceRemote proxy;

		proxy = (EventReservationServiceRemote) 
ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventReservationService!tn.esprit.b4.esprit1718b4tourism.services.EventReservationServiceRemote");

		
		

		ctxe = new InitialContext();
		EventServiceRemote proxyy;

		proxyy = (EventServiceRemote) 
ctxe.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");

		
		
		
		
		
		
		//list=  proxy.findReservationByIdEvent(AllReservationsController.euuf);
		list=  proxy.findReservationByIdUser(StaticVars.currentUser.getId());
        
		String x =texupdate.getText();
		int xx = Integer.parseInt(texupdate.getText());
	      if(!list.isEmpty()){
	          
	          EventReservation rand = list.get(0);
	          proxy.UpdateMyReser(rand, xx);
	          
	         int nbplacesoriginale = list.get(0).getEvent().getNbplaces();
	         int nbplacesreserves = list.get(0).getNbplaces();
	         int nbparticipant = list.get(0).getEvent().getNbparticipant();
	         int ideveent= list.get(0).getEvent().getId();
	         if(xx > nbplacesoriginale) {
	        	
	        	 showAlerte = true;
				 des = "You must Verify your Booking places";
				 
				 if (showAlerte) {
			         showMessageDialoge(msg, des);
			     } 	
	        	 
	         }
	         
	         else if(xx>nbplacesreserves)
	         {
	        	 
	        	 int nbahhh= xx-nbplacesreserves;
	        	 nbplacesoriginale= nbplacesoriginale-nbahhh;
	        	 
	        	 //nbplacesoriginale= nbplacesoriginale-xx;
	        	 nbparticipant= nbparticipant+nbahhh;
	        	 //list.get(0).getEvent().setNbplaces(nbplacesoriginale);
	        	//proxyy.UpdatePlaces(nbplacesoriginale, AllReservationsController.euuf);
	        	//proxyy.UpdatePlaces(nbparticipant, AllReservationsController.euuf);
	        	//proxyy.UpdatePlacesorigparti(nbplacesoriginale, nbparticipant, AllReservationsController.euuf);
	        	 proxyy.UpdatePlacesorigparti(nbplacesoriginale, nbparticipant, ideveent);
	        	

	        	 showAlerte = true;
				 des = "Succefully Updated";
				 String msge="Succeful Operation";
				 if (showAlerte) {
			         showMessageDialoge(msge, des);
			     } 	
	        	 
	        	 
	         }else {
	        	 
	        	 
	        	 
	        	 int nbahhh= nbplacesreserves-xx;
	        	 nbplacesoriginale=nbplacesoriginale+nbahhh;
	        	 nbparticipant=nbparticipant-nbahhh;
	        		//proxyy.UpdatePlaces(nbplacesoriginale, AllReservationsController.euuf);
                    //proxyy.UpdatePlaces(nbparticipant, AllReservationsController.euuf);
	        	// proxyy.UpdatePlacesorigparti(nbplacesoriginale, nbparticipant, AllReservationsController.euuf);
	        	 proxyy.UpdatePlacesorigparti(nbplacesoriginale, nbparticipant,ideveent);
		        	 showAlerte = true;
					 des = "Succefully Updated";
					 String msge="Succeful Operation";
					 
					 if (showAlerte) {
				         showMessageDialoge(msge, des);
				     } 	
	        	 
	         }
	         
	          
	    // rand.setNbplaces(xx);
	      //rand.setNbplaces(nbpl);
	      //rand.getEvent().setNbplaces(nbpl);
	     // rand.setNbplaces(nbpl);
	     // rand.setEvent(event);
	      //rand.getEvent().setNbplaces(nbpl);
	      
	      
	      }
		
		
		
	}
	      
	}

@FXML
private void retouur(ActionEvent event) {
    
    navigate("AllReservations.fxml");
}

private void navigate(String fxml) {
    try {
        Stage stage = new Stage();
        Stage currentStage = (Stage) retour.getScene().getWindow();
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
void logoutt(ActionEvent event) {
	
	navigate("Login.fxml");
	
}
private void showMessageDialoge(String msg, String description) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Alerte");
    alert.setHeaderText(msg);
    alert.setContentText(description);

    alert.showAndWait();
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

	
@FXML
public void forum(ActionEvent event)
{
	
	navigate("Forum.fxml");
	// System.out.println(AllReservationsController.UserForum.getId());
	
}

	
}
