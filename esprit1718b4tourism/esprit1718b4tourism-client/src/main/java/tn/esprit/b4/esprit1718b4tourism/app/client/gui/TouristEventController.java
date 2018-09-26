package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

import javafx.application.Platform;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.esprit.b4.esprit1718b4tourism.entities.Event;
import tn.esprit.b4.esprit1718b4tourism.entities.User;
import tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote;

public class TouristEventController extends AnchorPane implements Serializable,Initializable{

	public static String nomRando;
	public static int idEvent;
	public static int nb;
	//public static User currentUser;
	public static int prix;
	public static int nbparticipants;
	 
	 private final FileChooser fileChooser = new FileChooser();
	 
	 private String pathImage = "", fileName = "";
	
	 @FXML
	private ImageView imageview;
	 
	@FXML
	private ImageView imgv;
	
	@FXML
	private ImageView photo;

	@FXML
	private JFXDrawer drawer;

	@FXML
	private VBox box;

	@FXML
	private JFXHamburger hamburger;

	@FXML
	private JFXButton idforum;

	
	@FXML
	private JFXButton upload;
	
	
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
		private JFXButton boutonsearch;
	 
	 @FXML
		private JFXButton promo;
	 
	 @FXML
	    private Label UserName;
	 @FXML
	    private Label UserName1;
	 
	 

	    @FXML
	    private JFXTextField searchForum;
	    
	    @FXML
		private JFXButton logout;

	    @FXML
	  		private JFXButton forum;
	    
	List<Event> list;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		
		 //C:\Users\azuz\Desktop
		/*drawer.setSidePane(box);

		HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
		transition.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
			transition.setRate(transition.getRate() * -1);
			transition.play();

			if (drawer.isShown()) {

				drawer.close();
			} else {
				drawer.open();
			}
		});*/
		
		
		//UserName.setText( currentUser.getCurrentUser().getFirstName());
		
		 //currentUser.getCurrentUser().getId();
		
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			EventServiceRemote proxy;

			proxy = (EventServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");

			
			colonetitre.setCellValueFactory(new PropertyValueFactory<Event, String>("nom"));
			//coloneadress.setCellValueFactory(new PropertyValueFactory<Event, String>("adresse"));
			//colonedescription.setCellValueFactory(new PropertyValueFactory<Event, String>("description"));
			colonedebut.setCellValueFactory(new PropertyValueFactory<Event, Date>("dateEvent"));
			colonefin.setCellValueFactory(new PropertyValueFactory<Event, Date>("datefin"));
			colonePlaces.setCellValueFactory(new PropertyValueFactory<Event, Integer>("nbplaces"));
			colonePrice.setCellValueFactory(new PropertyValueFactory<Event, Integer>("price"));
			ObservableList<Event> data = FXCollections.observableArrayList(proxy.findAllEvents());
			tableaffichage.setItems(data);
			//System.out.println(currentUser.getCurrentUser().getFirstName());
		UserName.setText(StaticVars.currentUser.getFirstName());
		UserName1.setText(StaticVars.currentUser.getLastName());
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
	               // final MenuItem ReserverMenuItem = new MenuItem("Update Event");

	                detailMenuItem.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent event) {

	                        nomRando = row.getItem().getNom();
	                        idEvent= row.getItem().getId();
	                        nb = row.getItem().getNbplaces();
                            prix = row.getItem().getPrice();
                            nbparticipants= row.getItem().getNbparticipant();
                        
	                        navigate("Tourist_Consulter_details_event.fxml");
	                    }
	                    
	                  
	                });
	                
	            
	                
	                
	                contextMenu.getItems().add(detailMenuItem);
	                //contextMenu.getItems().add(ReserverMenuItem);
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
	void lister (ActionEvent event) throws NamingException{
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			EventServiceRemote proxy;

			proxy = (EventServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");

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
		
}
	
	
	
	private void showMessageDialoge(String msg, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerte");
        alert.setHeaderText(msg);
        alert.setContentText(description);

        alert.showAndWait();
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
	public void filtrer(ActionEvent event) {
	
		
		}

@FXML
public void searchh(ActionEvent event) {

	navigate("TouristSearch.fxml");
	}





private void setExtFilters(FileChooser chooser) {
    chooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
    );
}

private void openNewImageWindow(File file) throws URISyntaxException {

    Image image = new Image(file.toURI().toString());

    pathImage = file.getAbsolutePath();

    fileName = file.getName();
    System.out.println("file name ");
    System.out.println(fileName);
    imageview.setPreserveRatio(true);
    imageview.setImage(image);
    imageview.setSmooth(true);
    imageview.setCache(true);

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
void logoutt(ActionEvent event) {
	
	navigate("Login.fxml");
	
}






}

       


 	
 	
	
	

	

	
	
	


	
	
	
	
	