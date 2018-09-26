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
import tn.esprit.b4.esprit1718b4tourism.entities.Souvenir;
import tn.esprit.b4.esprit1718b4tourism.entities.Panier;
import tn.esprit.b4.esprit1718b4tourism.services.PanierServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.SouvenirServiceRemote;

public class Consulter_details_panierController  implements Initializable,Serializable{

	
	public static int available;
	
	@FXML
	private Label erreur;
	@FXML
	private Label name;
	@FXML
	private Label description;
	@FXML
	private Label quantity;
	@FXML
	private Label category;
	@FXML
	private Label price;
	
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
	
	List<Panier> list;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			InitialContext ctx;
			
			
			
					ctx = new InitialContext();
					PanierServiceRemote proxy;

					proxy = (PanierServiceRemote) 
			ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/PanierService!tn.esprit.b4.esprit1718b4tourism.services.PanierServiceRemote");
			
					
					//list=  proxy.findReservationByIdEvent(AllReservationsController.euuf);
			        					list=  proxy.findReservationByIdSouvenir(AllPanierController.euuf);

				      if(!list.isEmpty()){
				          
				          Panier rand = list.get(0); //Panier
				       
				name.setText(rand.getSouvenirs().getName()); ///getSouvenir !!
				description.setText(rand.getSouvenirs().getDescription());
				quantity.setText(String.valueOf(rand.getSouvenirs().getQuantity())); 
				price.setText(String.valueOf(rand.getSouvenirs().getPrice()));
				
				
				
				category.setText(rand.getSouvenirs().getCategory());
				
				book.setText(String.valueOf(rand.getQuantity()));
				available = rand.getSouvenirs().getQuantity();
				
				
				      }
					
					
			
				
						} catch (NamingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
}

	
	@FXML
	private void update(ActionEvent event) throws NamingException {
		
		InitialContext ctx;
		InitialContext ctxe;
		
		boolean showAlerte = false;
	     String des = "";
	     String msg = "Error you must Verify";
	 int nbpl= Integer.parseInt(texupdate.getText())  ;
	 
	
	 if (texupdate.getText().length()==0 || nbpl > available || texupdate.getText().equals("") ||
			 nbpl ==0  ||  !isFloat((texupdate.getText()))  || Float.parseFloat(texupdate.getText())<=0){
			
			showAlerte = true;
			 des = "You must Verify your Booking Operation";
			 
			 if (showAlerte) {
		         showMessageDialoge(msg, des);
		     } 	
	 
	}else {
		
		ctx = new InitialContext();
		PanierServiceRemote proxy;

		proxy = (PanierServiceRemote) 
ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/PanierService!tn.esprit.b4.esprit1718b4tourism.services.PanierServiceRemote");

		ctxe = new InitialContext();
		SouvenirServiceRemote proxyy;

		proxyy = (SouvenirServiceRemote) 
ctxe.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/SouvenirService!tn.esprit.b4.esprit1718b4tourism.services.SouvenirServiceRemote");


        list=  proxy.findReservationByIdSouvenir(AllPanierController.euuf);
		String x =texupdate.getText();
		int xx = Integer.parseInt(texupdate.getText());
	      if(!list.isEmpty()){
	          
	          Panier rand = list.get(0);
	          proxy.UpdateMyReser(rand, xx);
	          
	         int nbplacesoriginale = list.get(0).getSouvenirs().getQuantity();
	         int nbplacesreserves = list.get(0).getQuantity();
	         
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
	        	
	        	proxyy.UpdatePlacesorigparti(nbplacesoriginale, AllPanierController.euuf);
	        	

	        	 showAlerte = true;
				 des = "Succefully Updated";
				 String msge="Succeful Operation";
				 if (showAlerte) {
			         showMessageDialoge(msge, des);
			     } 	
	        	 
	        	 
	         }else {
	        	 
	        	 int nbahhh= xx-nbplacesreserves;

	        	 nbplacesoriginale=nbplacesoriginale+nbahhh;
	        	
	        	 proxyy.UpdatePlacesorigparti(nbplacesoriginale, AllPanierController.euuf);
		        	 showAlerte = true;
					 des = "Succefully Updated";
					 String msge="Succeful Operation";
					 
					 if (showAlerte) {
				         showMessageDialoge(msge, des);
				     } 	
	        	 
	         }
	   
	      }
	
	}
	      
	}

@FXML
private void retouur(ActionEvent event) {
    
    navigate("AllPanier.fxml");
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
	
	navigate("AllPanier.fxml");
	
}

}
