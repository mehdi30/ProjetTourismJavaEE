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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4tourism.entities.Event;
import tn.esprit.b4.esprit1718b4tourism.entities.Souvenir;
import tn.esprit.b4.esprit1718b4tourism.services.EventReservationServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.PanierServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.SouvenirServiceRemote;

public class clientConsulteDetailSouvenirController extends AnchorPane implements Serializable,Initializable {
	

	@FXML
	private JFXTextField titre;
	@FXML
	private JFXTextField description;
	@FXML
	private JFXTextField category;
	@FXML
	private Label erreur;
	
	@FXML
	private JFXTextField quantity;
	
	
	@FXML
	private JFXTextField price;
	
	
	

	@FXML
	private JFXTextField nom;
	

	@FXML
	private JFXButton back;
	
	@FXML
	private JFXButton book;
	
	
	@FXML
	private JFXTextField quantityRes;
	
	
	
	
	@FXML
	private JFXButton reservation;
	
	@FXML
    private ImageView imageView;
	
	@FXML
	private Text totalprice;
	
	 private final FileChooser fileChooser = new FileChooser();
	 private String pathImage, fileName;
	
	List<Souvenir> list;
	EntityManager em;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
	InitialContext ctx;
	
			ctx = new InitialContext();
			SouvenirServiceRemote proxy;

			proxy = (SouvenirServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/SouvenirService!tn.esprit.b4.esprit1718b4tourism.services.SouvenirServiceRemote");
	
			
			list=  proxy.findEventByTitle(SouvenirController.nomRando);
	        
		      if(!list.isEmpty()){
		          
		          Souvenir rand = list.get(0);
		         int x= rand.getIdSouvenir();
			String nom = rand.getName();
			System.out.println("nom"+nom);
		titre.setText(rand.getName());
		description.setText(rand.getDescription());
		quantity.setText(String.valueOf(rand.getQuantity()));
		price.setText(String.valueOf(rand.getPrice()));
		
		category.setText(rand.getCategory());
		
		
		//part.setText(String.valueOf(rand.getNbparticipant()));
		
		
		//pathImage = rand.getImage_event();
      /*  File file = new File(pathImage);
        Image image = new Image(file.toURI().toString());

        imageView.setImage(image);
		*/
		
		
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
        
        navigate("SouvenirClient.fxml");
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
	
	
	try {
		InitialContext ctx;
		
				ctx = new InitialContext();
				PanierServiceRemote proxy;
				SouvenirServiceRemote proxy1;

proxy = (PanierServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/PanierService!tn.esprit.b4.esprit1718b4tourism.services.PanierServiceRemote");
proxy1 = (SouvenirServiceRemote)ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/SouvenirService!tn.esprit.b4.esprit1718b4tourism.services.SouvenirServiceRemote");
				
						
					
			int nbplacestapes=	Integer.parseInt((quantityRes.getText()));
			
			int nbporiginale =SouvenirController.nb;
		
			
			int available =  nbporiginale- nbplacestapes;
			System.out.println(available);
			 boolean showAlerte = false;
		     String des = "";
		     String msg = "Error you must Verify";
			
		    // String number= String.valueOf(nbplacestapes);
		    
		     if (quantityRes.getText().length()==0|| nbporiginale == 0 || nbplacestapes > nbporiginale || nbplacestapes ==0 
		    		|| !isFloat((quantityRes.getText())) ||
		 			Float.parseFloat(quantityRes.getText())<=0 ){
				
				showAlerte = true;
				 des = "You must Verify your Booking Operation";
				 
				 if (showAlerte) {
			         showMessageDialoge(msg, des);
			     } 
				
			}else {
				// currentUser.getCurrentUser().getId();
				proxy.AddReservationSouvenir(StaticVars.currentUser.getId(),SouvenirController.idSouv, nbplacestapes,SouvenirController.nomRando);
				proxy1.UpdateQuantity(available, SouvenirController.idSouv);
				
				
				int nbplacecalcul = Integer.parseInt((quantityRes.getText()));
				float ttprice = nbplacecalcul * SouvenirController.pricee;

				//System.out.println(ttprice);

					totalprice.setText("$" + String.valueOf(ttprice));
				
				
				showAlerte = true;
				 des = "Succefully Operation";
				 String msge = "Succefully Operation";
				 
				 if (showAlerte) {
			         showMessageDialoge(msge, des);
			     } 
				 navigate1("SouvenirClient.fxml");
			
			}
			}
		     
	
	
	
	 catch (NamingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		System.out.println("error");
		
		
	}
	
	 

}



@FXML
private void reservationhaha(ActionEvent event)
{
navigate1("AllPanier.fxml");



}





}
	
