package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.esprit.b4.esprit1718b4tourism.entities.Event;
import tn.esprit.b4.esprit1718b4tourism.entities.EventReservation;
import tn.esprit.b4.esprit1718b4tourism.entities.User;
import tn.esprit.b4.esprit1718b4tourism.services.EventReservationServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote;

@SuppressWarnings("serial")
public class AllReservationsController extends AnchorPane  implements Serializable,Initializable {
	
	public static Event selectedEvent;
	public static EventReservation selectedEventReservation;
	public static  int euuf;
	public static String nom_event;
    public static String adresse_event;
    public static Date date_event;
    public static Date date_fin;
    public static String description_event;
    public static int nb_places_event;
    public static int price_event;
    public static String type_event;
    public static int nbr_part;
    public static int nb;
    public static int iduser;
    public static String image_event;
    public static User UserForum;
    public static int idReservationevent;
    public static int nb_place_booked;
    public static String address;
	@FXML
	private ImageView imgv;

	@FXML
	private JFXDrawer drawer;

	@FXML
	private VBox box;

	@FXML
	private JFXHamburger hamburger;

	@FXML
	private JFXButton idforum;

	@FXML
	private TableView<EventReservation> tableaffichage;
	@FXML
	private TableColumn<EventReservation, String> colonetitre;

	@FXML
	private TableColumn<Event, String> coloneadress;

	@FXML
	private TableColumn<Event, String> colonedescription;

	@FXML
	private TableColumn<EventReservation, Integer> colonePlaces;
	
	
	@FXML
	private TableColumn<EventReservation, String> idevent;

	@FXML
	private JFXDatePicker datefin;
	@FXML
	private JFXButton js;
	@FXML
	private JFXDatePicker datedebut;
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
    private Button btnFiltrer;
	
	@FXML
	private JFXButton update;
	
	@FXML
    private TextField ancienDateDeb;
	
	@FXML
    private TextField ancienDateFin;
	
	 @FXML
	    private ComboBox typeEvent;

	 @FXML
		private Label haha;
	 
	 @FXML
	    private Button supp;
	 
	 
	 @FXML
	 private JFXButton info;
	 
	List<Event> list;

	@FXML
	private JFXButton gotoforum;
	
	
	@FXML
	private JFXButton baaack;
	
	
	
	@FXML
	private JFXButton logout;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
		
		
		
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			EventServiceRemote proxy;
			EventReservationServiceRemote proxy1;

			proxy = (EventServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");
			proxy1 = (EventReservationServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventReservationService!tn.esprit.b4.esprit1718b4tourism.services.EventReservationServiceRemote");
			
			colonetitre.setCellValueFactory(new PropertyValueFactory<EventReservation, String>("nomevent"));
			
		
			
			ObservableList<Event> data = FXCollections.observableArrayList(proxy.findAllEvents());
			
			
			colonePlaces.setCellValueFactory(new PropertyValueFactory<EventReservation, Integer>("nbplaces"));
           //idevent.setCellValueFactory(new PropertyValueFactory<EventReservation, String>("reservationeventPK"));
			ObservableList<EventReservation> data1 = FXCollections.observableArrayList(proxy1.findContractsbyCompanybyID(StaticVars.currentUser.getId()));
         //ObservableList<EventReservation> data1 = FXCollections.observableArrayList(proxy1.CheckEvent(euuf, 2));
           
			//tableaffichage.setItems(data);
		tableaffichage.setItems(data1);
		
		
		
		
		
		
		//tableaffichage.getSelectionModel().getSelectedItem().getEvent().getNom();
		
		tableaffichage.setRowFactory(new Callback<TableView<EventReservation>, TableRow<EventReservation>>() {
            @Override
            public TableRow<EventReservation> call(TableView<EventReservation> tableView) {
                final TableRow<EventReservation> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem detailMenuItem = new MenuItem("Show Details");
               // final MenuItem ReserverMenuItem = new MenuItem("Update Event");
                
                //final MenuItem ahhhh = new MenuItem(tableaffichage.getSelectionModel().getSelectedItem().getEvent().getNom());
                detailMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                    	
                    //int euuf =	tableaffichage.getSelectionModel().getSelectedItem().getEvent().getId();                        nomRando = row.getItem().getNom();
                      euuf= row.getItem().getEvent().getId();
                      nom_event= row.getItem().getEvent().getNom();
                      adresse_event = row.getItem().getEvent().getAdresse();
                      date_event =row.getItem().getEvent().getDateEvent();
                      date_fin = row.getItem().getEvent().getDatefin();
                      description_event = row.getItem().getEvent().getDescription();
                      nb_places_event=row.getItem().getEvent().getNbplaces();
                      price_event = row.getItem().getEvent().getPrice();
                      address= row.getItem().getEvent().getAdresse();
                      type_event = row.getItem().getEvent().getTypeEvent();
                      nbr_part = row.getItem().getEvent().getNbparticipant();
                      nb= row.getItem().getNbplaces();
                      iduser = row.getItem().getUser().getId();
                     nb_place_booked =row.getItem().getNbplaces();
                      
                      UserForum = row.getItem().getUser();
                    
                     
                      image_event= row.getItem().getEvent().getImage_event();
                    // colonetitre.setCellValueFactory(new PropertyValueFactory<EventReservation, String>(row.getItem().getEvent().getNom()));
                        navigate("Consulter_details_reservation.fxml");
                    }
                    
                  
                });
                
              
                
                
                contextMenu.getItems().add(detailMenuItem);
                //contextMenu.getItems().add(ahhhh);
               
                // Set context menu on row, but use a binding to make it only show for non-empty rows:  
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu) null)
                                .otherwise(contextMenu)
                );
                return row;
            }
 });


		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
 
		}	
	
	
	
	@FXML
    void someInfo(ActionEvent event) {
		

		 if (!tableaffichage.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Some Information Here");
            alert.setHeaderText("Information about your Event :  "
                    + tableaffichage.getSelectionModel().getSelectedItem().getEvent().getNom());
            alert.setContentText("Adresse:"+tableaffichage.getSelectionModel().getSelectedItem().getEvent().getAdresse()+"Description" +tableaffichage.getSelectionModel().getSelectedItem().getEvent().getDescription());
            //alert.setContentText("Description:"+tableaffichage.getSelectionModel().getSelectedItem().getEvent().getDescription());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
         }
      }
		}
	
	
	
	
	@FXML
	public void baaaaaack(ActionEvent event) {
	
		navigate("TouristEvent.fxml");
		}
		
	
	
		
	private void navigate(String fxml) {
        try {
            Stage stage = new Stage();
            Stage currentStage = (Stage) btnFiltrer.getScene().getWindow();
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
    void supprimer(ActionEvent event) {
		
    	
  	  try {

            if (!tableaffichage.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Can not Delete Table is Empty");
                alert.setHeaderText("Delete that Reservation :  "
                        + tableaffichage.getSelectionModel().getSelectedItem().getEvent().getNom());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                
                	  Context ctxe = new InitialContext();
            			EventServiceRemote proxyy;
            			proxyy = (EventServiceRemote) ctxe.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");
                        
                	
                	
                	Context context = new InitialContext();
                	EventReservationServiceRemote serviceRemote =

            				(EventReservationServiceRemote) context
            						.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventReservationService!tn.esprit.b4.esprit1718b4tourism.services.EventReservationServiceRemote");
                    idReservationevent = tableaffichage.getSelectionModel().getSelectedItem().getEvent().getId();
                	serviceRemote.deleteReservation((tableaffichage.getSelectionModel().getSelectedItem()));
                     int nb=  	tableaffichage.getSelectionModel().getSelectedItem().getNbplaces();
                	
                	int idevente = tableaffichage.getSelectionModel().getSelectedItem().getEvent().getId();
            
                     proxyy.UpdatePlacesDelete(nb, idevente);
              
              
              
                	InitialContext ctx;
            		try {
            			ctx = new InitialContext();
            			EventServiceRemote proxy;
            			EventReservationServiceRemote proxy1;

            			proxy = (EventServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");
            			proxy1 = (EventReservationServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventReservationService!tn.esprit.b4.esprit1718b4tourism.services.EventReservationServiceRemote");
            			
            			colonetitre.setCellValueFactory(new PropertyValueFactory<EventReservation, String>("nomevent"));
            			
            			
            			
            			ObservableList<Event> data = FXCollections.observableArrayList(proxy.findAllEvents());
            			
            			
            			colonePlaces.setCellValueFactory(new PropertyValueFactory<EventReservation, Integer>("nbplaces"));
                       //idevent.setCellValueFactory(new PropertyValueFactory<EventReservation, String>("reservationeventPK"));
                      // ObservableList<EventReservation> data1 = FXCollections.observableArrayList(proxy1.findContractsbyCompanybyID(2));
            			
                       
                       //TouristEventController.currentUser.getCurrentUser().getId()
                       ObservableList<EventReservation> data1 = FXCollections.observableArrayList(proxy1.findContractsbyCompanybyID(StaticVars.currentUser.getId()));
            			//tableaffichage.setItems(data);
            		tableaffichage.setItems(data1);	
                	
                	
                	
            		} catch (NamingException e1) {
            			// TODO Auto-generated catch block
            			e1.printStackTrace();
            		}
            		
                
                
                
                
                
                }

            }
        } catch (Exception ex) {
            System.out.println("DATA can not be loaded " + ex.getMessage());

        }
    	
    	
    }

		@FXML
		public void forum(ActionEvent event)
		{
			
			navigate("Forum.fxml");
			// System.out.println(AllReservationsController.UserForum.getId());
			
		}
		
		
	@FXML
	public void filtrer(ActionEvent event) {
	
		
		}
		
	@FXML
    void logoutt(ActionEvent event) {
		
		navigate("Login.fxml");
		
	}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

