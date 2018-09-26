package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Local;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.esprit.b4.esprit1718b4tourism.entities.Event;
import tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote;

public class SearchController  implements Initializable,Serializable{
	public static String nomRando;
	public static int idEvent;
	public static int nb;

	
	
	
	@FXML
	private JFXButton sort;
	
	@FXML
	private JFXButton what;
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
	private TableView<Event> tableaffichage;
	@FXML
	private TableColumn<Event, String> colonetitre;

	@FXML
	private TableColumn<Event, String> coloneadress;

	@FXML
	private TableColumn<Event, String> colonedescription;

	@FXML
	private TableColumn<Event, Date> colonedebut;

	@FXML
	private TableColumn<Event, Date> colonefin;
	
	@FXML
	private TableColumn<Event, Integer> colonePlaces;
	
	
	@FXML
	private TableColumn<Event, Integer> colonePrice;

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
	    private Button boutonsearch;
	 
	 @FXML
	    private TextField pricemin;
	 
	 @FXML
	    private TextField pricemax;
	 
	 @FXML
	    private Button boutonsearch1;
	 

	 @FXML
	    private Button back;
	 
	 
	 @FXML
	    private TextField priceee;
	 @FXML
	    private TextField adress;
	 @FXML
	    private TextField name;
	 
	 
	List<Event> list;

    @FXML
    private JFXTextField searchForum;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			EventServiceRemote proxy;

			proxy = (EventServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");

			typeEvent.getItems().addAll("Music", "Tradition", "Sport");
			colonetitre.setCellValueFactory(new PropertyValueFactory<Event, String>("nom"));
			coloneadress.setCellValueFactory(new PropertyValueFactory<Event, String>("adresse"));
			colonedescription.setCellValueFactory(new PropertyValueFactory<Event, String>("description"));
			colonedebut.setCellValueFactory(new PropertyValueFactory<Event, Date>("dateEvent"));
			colonefin.setCellValueFactory(new PropertyValueFactory<Event, Date>("datefin"));
			colonePlaces.setCellValueFactory(new PropertyValueFactory<Event, Integer>("nbplaces"));
			colonePrice.setCellValueFactory(new PropertyValueFactory<Event, Integer>("price"));
			ObservableList<Event> data = FXCollections.observableArrayList(proxy.findAllEvents());
			tableaffichage.setItems(data);
		
		
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			tableaffichage.setRowFactory(new Callback<TableView<Event>, TableRow<Event>>() {
	            @Override
	            public TableRow<Event> call(TableView<Event> tableView) {
	                final TableRow<Event> row = new TableRow<>();
	                final ContextMenu contextMenu = new ContextMenu();
	               final MenuItem detailMenuItem = new MenuItem("Show Details");
	                final MenuItem ReserverMenuItem = new MenuItem("Update Event");

	                detailMenuItem.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent event) {

	                        nomRando = row.getItem().getNom();
	                        idEvent= row.getItem().getId();
	                        nb = row.getItem().getNbplaces();

	                        //navigate("Consulter_details_event.fxml");
	                    }
	                    
	                  
	                });
	                
	                ReserverMenuItem.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent event) {

	                        nomRando = row.getItem().getNom();

	                        //navigate("UpdateEvent.fxml");
	                    }
	                    
	                  
	                });
	                
	                
	                contextMenu.getItems().add(detailMenuItem);
	                contextMenu.getItems().add(ReserverMenuItem);
	                // Set context menu on row, but use a binding to make it only show for non-empty rows:  
	                row.contextMenuProperty().bind(
	                        Bindings.when(row.emptyProperty())
	                                .then((ContextMenu) null)
	                                .otherwise(contextMenu)
	                );
	                return row;
	            }
	 });
 
		}	
		
	
	
	

	@FXML
	void Search(KeyEvent event) throws Throwable   {
	  InitialContext ctx;			
	  ctx = new InitialContext();
	  EventServiceRemote proxy;
	  proxy = (EventServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");
	  ObservableList<Event> data = FXCollections.observableList(proxy.advancedsearchTopic(searchForum.getText()));
	   tableaffichage.setItems(data);

	}	
	
	@FXML
	public void search(ActionEvent event) throws NamingException {


		 boolean showAlerte = false;
	     String des = "";
	     String msg = "Error you must Verify";
		
		if (pricemin.getText().equals("") || pricemax.getText().equals("")|| !isFloat((pricemin.getText())) || !isFloat((pricemax.getText())) ||
				Float.parseFloat(pricemin.getText())<=0 || 
				Float.parseFloat(pricemax.getText())<=0  ){
			
			showAlerte = true;
			 des = "You must verify cases";
			 
			 if (showAlerte) {
		         showMessageDialoge(msg, des);
		     } 
			
			
		}else {
			InitialContext ctx;
			ctx = new InitialContext();
			EventServiceRemote proxy;
			proxy = (EventServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");
		int gggg= Integer.parseInt(pricemin.getText());
		int hhhh = Integer.parseInt(pricemax.getText());
		
		ObservableList<Event> data = FXCollections.observableArrayList(proxy.findAllEventsByprice(gggg, hhhh));
		tableaffichage.setItems(data);
		
		//proxy.findAllEventsByprice(gggg, hhhh);
			
			
		}
		
		
		
		
		
		}
	
	@FXML
	public void searchDate(ActionEvent event) throws NamingException {

		
		

		 boolean showAlerte = false;
	     String des = "";
	     String msg = "Error you must Verify";
		
		if (name.getText().equals("") || adress.getText().equals("")|| priceee.getText().equals("")||!isFloat((priceee.getText())) || !isFloat((priceee.getText())) ||
				Float.parseFloat(priceee.getText())<=0 || 
				Float.parseFloat(priceee.getText())<=0  ){
			
			showAlerte = true;
			 des = "You must verify cases";
			 
			 if (showAlerte) {
		         showMessageDialoge(msg, des);
		     } 
			
			
		}else {
			InitialContext ctx;
			ctx = new InitialContext();
			EventServiceRemote proxy;
			proxy = (EventServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");
		
			int gggg= Integer.parseInt(priceee.getText());
			String naamee= name.getText().toString();
			String addresss = adress.getText().toString();
		ObservableList<Event> data = FXCollections.observableArrayList(proxy.findWithNamePriceAdress(naamee, gggg, addresss));
		tableaffichage.setItems(data);
		
		}}	
			
		
		
		
		
		@FXML
		public void sortHere(ActionEvent event) throws NamingException {


				InitialContext ctx;
				ctx = new InitialContext();
				EventServiceRemote proxy;
				proxy = (EventServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");
			
				
			//Date dd= 	(Date)datedebut.getConverter();
				
				
			ObservableList<Event> data = FXCollections.observableArrayList(proxy.sortEventByDateDesc());
			tableaffichage.setItems(data);
			
			//proxy.findAllEventsByprice(gggg, hhhh);
				
	
		
	
		}
	
	


	private void showMessageDialoge(String msg, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
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
	public void whatdo(ActionEvent event) throws NamingException {
		
		InitialContext ctx;
		ctx = new InitialContext();
		EventServiceRemote proxy;
		proxy = (EventServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");
	
		
	String str= typeEvent.getValue().toString();
		
		
	ObservableList<Event> data = FXCollections.observableArrayList(proxy.findContentByType(str));
	tableaffichage.setItems(data);
	


	}
	
	
	@FXML
	public void backup(ActionEvent event) throws NamingException {
		
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
	
}


