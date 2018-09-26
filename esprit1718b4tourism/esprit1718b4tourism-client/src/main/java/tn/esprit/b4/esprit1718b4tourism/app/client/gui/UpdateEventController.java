package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4tourism.entities.Event;
import tn.esprit.b4.esprit1718b4tourism.entities.EventReservation;
import tn.esprit.b4.esprit1718b4tourism.entities.User;
import tn.esprit.b4.esprit1718b4tourism.services.EventReservationServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.UserService;

public class UpdateEventController  extends AnchorPane  implements Serializable,Initializable{

	
	
	
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
	private JFXTextField price;
	
	@FXML
    private TextField txtDate;
	
	@FXML
    private TextField datefinn;
	

	//@FXML
	//private JFXTextField nom;
	

	@FXML
	private JFXButton retour;
	
	@FXML
	private JFXButton update;
	
	@FXML
	private JFXDatePicker datefin;
	
	@FXML
	private JFXDatePicker datedebut;
	
	@FXML
    private ComboBox typeEvent;
	
	@FXML
    private ImageView imageView;

    private String pathImage = "", fileName = "";
    
    @FXML
	private JFXButton logout;
	
	
	
	List<Event> list;
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	
		
		Calendar calendar = Calendar.getInstance();
        LocalDate minDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

        datedebut.setDayCellFactory((p) -> new DateCell() {
            @Override
            public void updateItem(LocalDate ld, boolean bln) {
                super.updateItem(ld, bln);
                setDisable(ld.isBefore(minDate)); //pour désactiver la date li inf à date aujourd'hui
            }
        });
        Platform.runLater(() -> {
            datedebut.getEditor().clear();
        });

		
        Calendar calendarr = Calendar.getInstance();
        LocalDate minDater = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

        datefin.setDayCellFactory((p) -> new DateCell() {
            @Override
            public void updateItem(LocalDate ld, boolean bln) {
                super.updateItem(ld, bln);
                setDisable(ld.isBefore(minDate)); //pour désactiver la date li inf à date aujourd'hui
            }
        });
        Platform.runLater(() -> {
            datefin.getEditor().clear();
        });
        
		
		
		try {
			InitialContext ctx;
			
					ctx = new InitialContext();
					EventServiceRemote proxy;

					proxy = (EventServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");
					typeEvent.getItems().addAll("Music", "Tradition", "Sport");
					
					list=  proxy.findEventByTitle(PremiumEventController.nomRando);
			        
				      if(!list.isEmpty()){
				          
				          Event rand = list.get(0);
				         int x= rand.getId();
					
				titre.setText(rand.getNom());
				description.setText(rand.getDescription());
				nbplaces.setText(String.valueOf(rand.getNbplaces()));
				price.setText(String.valueOf(rand.getPrice()));
				//txtDate.setText(String.valueOf(rand.getDateEvent()));
				//datefinn.setText(String.valueOf(rand.getDatefin()));
				adresse.setText(rand.getAdresse());
				 pathImage = list.get(0).getImage_event();
		            File file = new File(pathImage);
		            Image image = new Image(file.toURI().toString());

		            imageView.setImage(image);
				
				//nom.setText(rand.getNom());
				      }
					
					 //Event rand = proxy.getEventByName(em,EventController.nomRando).getId();
					
				//list=  proxy.findByName(Randonneur_consulter_listeController.nomRando);
			
				
						} catch (NamingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
}
	
	
	@SuppressWarnings("deprecation")
	@FXML
	void update(ActionEvent event) throws NamingException {

		 boolean showAlerte = false;
		     String des = "";
		     String msg = "You can not Update this event, Missed Cases";
		
		InitialContext ctx;
		ctx = new InitialContext();
		EventServiceRemote proxy;
		proxy = (EventServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");

		

		if (titre.getText().equals("") || description.getText().equals("") || 
				adresse.getText().equals("")  || nbplaces.getText().equals("") ||
				price.getText().equals("") || datedebut.getValue() == null || 
				datefin.getValue() == null||  !isFloat((nbplaces.getText())) || !isFloat((price.getText())) ||
						Float.parseFloat(price.getText())<=0 || 
						Float.parseFloat(nbplaces.getText())<=0 || 
						datedebut.getValue().getDayOfWeek().getValue() > datefin.getValue().getDayOfWeek().getValue()
						|| typeEvent.getValue().toString().equals("")) 
					
				
					
					{
						//erreur.setText("champs vide!!");
						showAlerte = true;
						 des = "You must verify cases";
						 
						 if (showAlerte) {
					         showMessageDialoge(msg, des);
					     } 
						 
						 
					} 
			
					
					else 
					     {
			
			
			
	
						
		Event eve = new Event(titre.getText(), description.getText(), adresse.getText(), Integer.parseInt (price.getText()), Integer.parseInt (nbplaces.getText()),typeEvent.getValue().toString());
			//Event eve = new Event();
			Date d;
			if (datedebut.getValue() == null) {
				d = new Date(0);
			} else {
				d = new Date(datedebut.getValue().getYear() - 1900, datedebut.getValue().getMonthValue() - 1,
						datedebut.getValue().getDayOfMonth());
			}
			Date d1;
			if (datefin.getValue() == null) {
				d1 = new Date(0);
			} else {
				d1 = new Date(datefin.getValue().getYear() - 1900, datefin.getValue().getMonthValue() - 1,
				datefin.getValue().getDayOfMonth());
			}
			eve.setId(list.get(0).getId());
			eve.setDateEvent(d);
			eve.setDatefin(d1);
			eve.setIdUser(StaticVars.currentUser.getId());
			//eve.setNbparticipant(TouristEventController.nbparticipants);
			
			 pathImage = list.get(0).getImage_event();
	            File file = new File(pathImage);
	            Image image = new Image(file.toURI().toString());

	            imageView.setImage(image);
			eve.setImage_event(pathImage);
			
			proxy.UpdateEvent(eve);
			
			 //List<EventReservation> list ;
			 List<User>liste;
			 InitialContext ctxe;
				ctxe = new InitialContext();
				EventReservationServiceRemote proxyy;
				proxyy = (EventReservationServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventReservationService!tn.esprit.b4.esprit1718b4tourism.services.EventReservationServiceRemote");
				List<EventReservation> listee = proxyy.findReservationByIdEvent(list.get(0).getId());
for(EventReservation res : listee)
			
{
	
	 InitialContext ctxee;
		ctxe = new InitialContext();
		EventServiceRemote proxyyy;
		proxyyy = (EventServiceRemote) ctxe.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");
		//List<EventReservation> list = proxyy.findReservationByIdEvent(PremiumEventController.idEvent);
liste = proxyyy.findUserById(res.getUser().getId());

String c = eve.getDateEvent().toString();
String namee= eve.getNom();

  String mail="chadha.omezine@esprit.tn";
  String pass="ninaomezzine12345";
  String[] to={liste.get(0).getEmail()};
  String subject="Information From Tourism";
  String body="We're sending  you this mail to inform you that the event"+namee+" has been modified to this date'"+c+"and the Adress will be"+eve.getAdresse();
  SendMailTLS sendMail = new SendMailTLS();
  sendMail.sendFromGMail(mail,pass,to,subject,body);
   System.out.println("done");	     


//user.get
}
	//= reservationservice.findByRandonnee(randonnee.getId_randonnee());
          /*              for (Reservation res : list) {
                            UserService userService = new UserService();
                            User user = userService.findById(res.getId_randonneur()).get(0);
                            String sujet = "Ranndonnée modifié";
                            SendEmail.envoyerEmail(user.getAdresse_email(), sujet, "Modification de prix de randonnée à 25Dt");
                        }
			
			*/
			
			
			showAlerte = true;
			 des = "Succefully Updated";
			 String msge="Update succefully";
			 if (showAlerte) {
		         showMessageDialoge(msge, des);
		     } 
			
			titre.clear();
			description.clear();
			adresse.clear();
			nbplaces.clear();
			price.clear();
			navigate("PremiumEvent.fxml");
			
			//adresse.clear();
			//ObservableList<Event> data = FXCollections.observableArrayList(proxy.findAllEvents());
			//tableaffichage.setItems(data);
			
			
					     }}


	
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
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerte");
        alert.setHeaderText(msg);
        alert.setContentText(description);

        alert.showAndWait();
    }
		
	
	
	@FXML
	void logoutt(ActionEvent event)  {
		
		
		navigate("ClientHome.fxml");
		
	}

	
	
	@FXML
	void retour(ActionEvent event) throws NamingException {
	navigate("PremiumEvent.fxml");
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
	
}
