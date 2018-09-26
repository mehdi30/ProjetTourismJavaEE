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
import tn.esprit.b4.esprit1718b4tourism.entities.Panier;
import tn.esprit.b4.esprit1718b4tourism.entities.Souvenir;
import tn.esprit.b4.esprit1718b4tourism.entities.User;
import tn.esprit.b4.esprit1718b4tourism.services.EventReservationServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.PanierServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.SouvenirServiceRemote;

@SuppressWarnings("serial")
public class AllPanierController extends AnchorPane  implements Serializable,Initializable {
	
	public static Event selectedEvent;
	public static EventReservation selectedEventReservation;
	public static  int euuf;
	public static String nom_event;
    public static String adresse_event;
    public static Date date_event;
    public static Date date_fin;
    public static String description_event;
    public static int nb_places_event;
    public static float price_event;
    public static String type_event;
    public static int nbr_part;
    public static int nb;
    public static int iduser;
    public static String image_event;
    public static User UserForum;
    public static int idReservationevent;
    public static int nbplacesorig;
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
	private TableView<Panier> tableaffichage;
	@FXML
	private TableColumn<Panier, String> colonetitre;

	@FXML
	private TableColumn<Souvenir, String> coloneadress;

	@FXML
	private TableColumn<Souvenir, String> colonedescription;

	@FXML
	private TableColumn<Souvenir, Date> colonedebut;

	@FXML
	private TableColumn<Souvenir, Date> colonefin;
	
	@FXML
	private TableColumn<Panier, Integer> colonePlaces;
	
	
	@FXML
	private TableColumn<Panier, String> idevent;

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
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
		
		
		
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			SouvenirServiceRemote proxy;
			PanierServiceRemote proxy1;

			proxy = (SouvenirServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/SouvenirService!tn.esprit.b4.esprit1718b4tourism.services.SouvenirServiceRemote");
			proxy1 = (PanierServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/PanierService!tn.esprit.b4.esprit1718b4tourism.services.PanierServiceRemote");
			
			colonetitre.setCellValueFactory(new PropertyValueFactory<Panier, String>("name"));
			
			
			
			colonePlaces.setCellValueFactory(new PropertyValueFactory<Panier, Integer>("quantity"));
           idevent.setCellValueFactory(new PropertyValueFactory<Panier, String>("panierPk"));
           ObservableList<Panier> data1 = FXCollections.observableArrayList(proxy1.findContractsbyCompanybyID(StaticVars.currentUser.getId()));         

			
		tableaffichage.setItems(data1);
	
		
		tableaffichage.setRowFactory(new Callback<TableView<Panier>, TableRow<Panier>>() {
            @Override
            public TableRow<Panier> call(TableView<Panier> tableView) {
                final TableRow<Panier> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem detailMenuItem = new MenuItem("Show Details");
                
                detailMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                    	
                      euuf= row.getItem().getSouvenirs().getIdSouvenir();
                      nom_event= row.getItem().getSouvenirs().getName();
                     
                     nbplacesorig=row.getItem().getSouvenirs().getQuantity();
                      
                      price_event = row.getItem().getSouvenirs().getPrice();
                     
                      nb= row.getItem().getQuantity();
                     
                        navigate("Consulter_details_panier.fxml");
                    }
                    
                  
                });
                
              
                
                
                contextMenu.getItems().add(detailMenuItem);
                
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
	
	/*
	
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
	
	*/
	
	
	@FXML
	public void baaaaaack(ActionEvent event) {
	
		navigate("SouvenirClient.fxml");
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
                        + tableaffichage.getSelectionModel().getSelectedItem().getSouvenirs().getName());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                
                	  Context ctxe = new InitialContext();
            			SouvenirServiceRemote proxyy;
            			proxyy = (SouvenirServiceRemote) ctxe.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/SouvenirService!tn.esprit.b4.esprit1718b4tourism.services.SouvenirServiceRemote");
                        
                	
                	
                	Context context = new InitialContext();
                	PanierServiceRemote serviceRemote =

            				(PanierServiceRemote) context
            						.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/PanierService!tn.esprit.b4.esprit1718b4tourism.services.PanierServiceRemote");
                	serviceRemote.deleteReservation((tableaffichage.getSelectionModel().getSelectedItem()));
                     int nb=  	tableaffichage.getSelectionModel().getSelectedItem().getQuantity();
                	
                	int idevente = tableaffichage.getSelectionModel().getSelectedItem().getSouvenirs().getIdSouvenir();
            
                     proxyy.UpdateQuantityDelete(nb, idevente);
              
              
              
                	InitialContext ctx;
            		try {
            			ctx = new InitialContext();
            			SouvenirServiceRemote proxy;
            			PanierServiceRemote proxy1;

            			proxy = (SouvenirServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/SouvenirService!tn.esprit.b4.esprit1718b4tourism.services.SouvenirServiceRemote");
            			proxy1 = (PanierServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/PanierService!tn.esprit.b4.esprit1718b4tourism.services.PanierServiceRemote");
            			
            			colonetitre.setCellValueFactory(new PropertyValueFactory<Panier, String>("name"));
            			
            			
            			
            			ObservableList<Souvenir> data = FXCollections.observableArrayList(proxy.showAllSouvenir());
            			
            			
            			colonePlaces.setCellValueFactory(new PropertyValueFactory<Panier, Integer>("quantity"));
                       idevent.setCellValueFactory(new PropertyValueFactory<Panier, String>("PanierPK"));
            			ObservableList<Panier> data1 = FXCollections.observableArrayList(proxy1.findContractsbyCompanybyID(1));
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
/*
		@FXML
		public void forum(ActionEvent event)
		{
			
			navigate("Forum.fxml");
			
		}
		
		*/
	@FXML
	public void filtrer(ActionEvent event) {
	
		
		}
		
	
	@FXML
	public void someInfo(ActionEvent event) {
	
		
		}
	
	}
	
	

