package tn.esprit.b4.esprit1718b4tourism.app.client.gui;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.Serializable;
import java.awt.Insets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.common.base.Predicate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DateCell;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.util.converter.LocalDateStringConverter;
import tn.esprit.b4.esprit1718b4tourism.entities.Event;
import tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote;

public class EventController extends AnchorPane  implements Serializable,Initializable  {
	
	public static String nomRando;
	public static int idEvent;
	public static int nb;
	public static int currentUser;

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
		Calendar calendar = Calendar.getInstance();
        LocalDate minDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

        datedebut.setDayCellFactory((p) -> new DateCell() {
            @Override
            public void updateItem(LocalDate ld, boolean bln) {
                super.updateItem(ld, bln);
                setDisable(ld.isBefore(minDate)); //pour dÃ©sactiver la date li inf Ã  date aujourd'hui
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
                setDisable(ld.isBefore(minDate)); //pour dÃ©sactiver la date li inf Ã  date aujourd'hui
            }
        });
        Platform.runLater(() -> {
            datefin.getEditor().clear();
        });
        
        
		
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

	                        navigate("Consulter_details_event.fxml");
	                    }
	                    
	                  
	                });
	                
	                ReserverMenuItem.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent event) {

	                        nomRando = row.getItem().getNom();

	                        navigate("UpdateEvent.fxml");
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
	
	
	@SuppressWarnings("deprecation")
	@FXML
	void ajouter(ActionEvent event) throws NamingException, IOException {
		
		InitialContext ctx;
		ctx = new InitialContext();
		EventServiceRemote proxy;
		proxy = (EventServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");
		/*long daynbr = 1000l * 60 * 60 * 24;
		 float deff; 
		 
		 Float datedeb=Float.parseFloat(datedebut.getValue().toString());
		 Float datef=Float.parseFloat(datefin.getValue().toString());
		
         deff=(datedeb-datef);
         System.out.println(deff);*/
         
		
		
		 boolean showAlerte = false;
	     String des = "";
	     String msg = "Error you must Verify";
		Date d;
		Date d1;
		//float pl = Float.parseFloat(nbplaces.getText());
		//float pl1 = Float.parseFloat(price.getText());
		if (titre.getText().equals("") || description.getText().equals("") || 
	adresse.getText().equals("")  || nbplaces.getText().equals("") ||
	price.getText().equals("") || datedebut.getValue() == null || 
	datefin.getValue() == null||  !isFloat((nbplaces.getText())) || !isFloat((price.getText())) ||
			Float.parseFloat(price.getText())<=0 || 
			Float.parseFloat(nbplaces.getText())<=0 || 
			datedebut.getValue().getDayOfWeek().getValue() > datefin.getValue().getDayOfWeek().getValue()
			|| typeEvent.getValue().toString().equals("")) 
		
	
		
		{
			erreur.setText("champs vide!!");
			showAlerte = true;
			 des = "You must verify cases";
			 
			 if (showAlerte) {
		         showMessageDialoge(msg, des);
		     } 
			 
			 
		} 
		
		else 
		     {
		    	 
		    	 d = new Date(0);
		    	d = new Date(datedebut.getValue().getYear() - 1900, datedebut.getValue().getMonthValue() - 1,
						datedebut.getValue().getDayOfMonth());
		    	 d1 = new Date(0);
				 
		    	d1 = new Date(datefin.getValue().getYear() - 1900, datefin.getValue().getMonthValue() - 1,
						datefin.getValue().getDayOfMonth());
		    	
		    	datedebut.getValue().getDayOfWeek().getValue();
				System.out.println(datedebut.getValue().getDayOfWeek().getValue());
		    	
				Event e1 = new Event(titre.getText(), description.getText(), adresse.getText(),Integer.parseInt (price.getText()) , Integer.parseInt (nbplaces.getText()),typeEvent.getValue().toString());
					
				
					

					e1.setDateEvent(d);
					e1.setDatefin(d1);
					//e1.setTypeEvent(typeEvent.getValue().toString());
				e1.setImage_event(pathImage);
					proxy.addEvent(e1);
					if (pathImage != null) {
	                    Path from = Paths.get(pathImage);
	                    Path to = Paths.get(fileName);
	                    File temp = new File(fileName);
//	                    Path to = Paths.get(destination + fileName);
//	                    File temp = new File(destination + fileName);

	                    if (temp.exists()) {
	                        temp.delete();
	                    }
	                    Files.copy(from, to);
					
					titre.clear();
					description.clear();
					adresse.clear();
					nbplaces.clear();
					price.clear();
					 fileName = "";
				        pathImage = "";
					 imageview.setImage(null);
					datedebut.setValue(null);
					datefin.setValue(null);
					
				}		//adresse.clear();
					//ObservableList<Event> data = FXCollections.observableArrayList(proxy.findAllEvents());
					//tableaffichage.setItems(data);
					
					
					
					
					colonetitre.setCellValueFactory(new PropertyValueFactory<Event, String>("nom"));
					coloneadress.setCellValueFactory(new PropertyValueFactory<Event, String>("adresse"));
					colonedescription.setCellValueFactory(new PropertyValueFactory<Event, String>("description"));
					colonedebut.setCellValueFactory(new PropertyValueFactory<Event, Date>("dateEvent"));
					colonefin.setCellValueFactory(new PropertyValueFactory<Event, Date>("datefin"));
					colonePlaces.setCellValueFactory(new PropertyValueFactory<Event, Integer>("nbplaces"));
					colonePrice.setCellValueFactory(new PropertyValueFactory<Event, Integer>("price"));
					ObservableList<Event> data = FXCollections.observableArrayList(proxy.findAllEvents());
					tableaffichage.setItems(data);
				
				
		    	
		     }
			
			
		}
	

	
    @FXML
    void delete(ActionEvent event) {

    	
    	  try {

            //  if (!tableaffichage.getSelectionModel().isEmpty()) {
              //    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                //  alert.setTitle("Can not Delete Table is Empty");
                 // alert.setHeaderText("Delete that Company :  "
                   //       + tableaffichage.getSelectionModel().getSelectedItem().getIdCompany()+" with name : "+TableCompany.getSelectionModel().getSelectedItem().getCompanyName());
                 // Optional<ButtonType> result = alert.showAndWait();
               
    		  boolean showAlerte = false;
 		     String des = "";
 		     String msg = "You can not delete this event";
    		  if(tableaffichage.getSelectionModel().getSelectedItem().getNbparticipant() >= 1) {
                	
                	showAlerte = true;
   				 des = "You must verify the number of participants";
   				 
   				 if (showAlerte) {
   			         showMessageDialoge(msg, des);
   			     } 
                	
                }else {
                     
                	  Context context = new InitialContext();
              		EventServiceRemote companyserviceRemote =

              				(EventServiceRemote) context
              						.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");

                      
              		companyserviceRemote.DeleteEvent(tableaffichage.getSelectionModel().getSelectedItem().getId());
              		//ShowItem();
              
              		colonetitre.setCellValueFactory(new PropertyValueFactory<Event, String>("nom"));
        			coloneadress.setCellValueFactory(new PropertyValueFactory<Event, String>("adresse"));
        			colonedescription.setCellValueFactory(new PropertyValueFactory<Event, String>("description"));
        			colonedebut.setCellValueFactory(new PropertyValueFactory<Event, Date>("dateEvent"));
        			colonefin.setCellValueFactory(new PropertyValueFactory<Event, Date>("datefin"));
        			colonePlaces.setCellValueFactory(new PropertyValueFactory<Event, Integer>("nbplaces"));
        			colonePrice.setCellValueFactory(new PropertyValueFactory<Event, Integer>("price"));
        			ObservableList<Event> data = FXCollections.observableArrayList(companyserviceRemote.findAllEvents());
        			tableaffichage.setItems(data);

              //}
                } } catch (Exception ex) {
              System.out.println("erreur lors du chargement des forums " + ex.getMessage());

          }

    	
    	
    }

	/*
	@SuppressWarnings("deprecation")
	@FXML
	void update(ActionEvent event) throws NamingException {
		Integer selected = tableaffichage.getSelectionModel().getSelectedIndex();
		InitialContext ctx;
		ctx = new InitialContext();
		EventServiceRemote proxy;
		proxy = (EventServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");
		if (tableaffichage.getSelectionModel().isSelected(selected)) {

			Event ee = tableaffichage.getItems().get(selected);
			ee.setNom(titre.getText());
			ee.setAdresse(adresse.getText());
			ee.setDescription(description.getText());
			ee.setNbplaces(Integer.parseInt(nbplaces.getText()));
			ee.setPrice(Integer.parseInt(price.getText()));
			ee.setTypeEvent(typeEvent.getValue().toString());
			  //nbplaces.setText(String.valueOf(ee.getNbplaces()));
			//ee.setDateEvent(datedebut.get);
			//ee.setDateEvent(ancienDateDeb.setText(String.valueOf(ee.getDateEvent()));ancienDateDeb.setText(String.valueOf(ee.getDateEvent()));

			//ancienDateFin.setText(String.valueOf(ee.getDateEvent()));
			//ancienDateDeb.setText(String.valueOf(ee.getDatefin()));
			
		list=  proxy.findEventByTitle(EventController.nomRando);
	        
		     
		          
		          Event rand = list.get(0);
		         int x= rand.getId();
			
			ancienDateFin.setText(String.valueOf(rand.getDatefin()));
			ancienDateDeb.setText(String.valueOf(rand.getDatefin()));
		 
			

			 boolean showAlerte = false;
		     String des = "";
		     String msg = "Error you must Verify";
		
			if (titre.getText().equals("") || description.getText().equals("") || 
		adresse.getText().equals("")  || nbplaces.getText().equals("") ||
		price.getText().equals("") || datedebut.getValue() == null || 
		datefin.getValue() == null||  !isFloat((nbplaces.getText())) || !isFloat((price.getText())) ||
				Float.parseFloat(price.getText())<=0 || 
				Float.parseFloat(nbplaces.getText())<=0 || 
				datedebut.getValue().getDayOfWeek().getValue() > datefin.getValue().getDayOfWeek().getValue() 
				) 
			
		
			
			{
				erreur.setText("champs vide!!");
				showAlerte = true;
				 des = "You must verify cases";
				 
				 if (showAlerte) {
			         showMessageDialoge(msg, des);
			     } 
				 
			} else {
			
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

			ee.setDatefin(d1);
			ee.setDateEvent(d);
			proxy.UpdateEvent(ee);
			//UpdateEvent(ee);

		}}
		ObservableList<Event> data = FXCollections.observableArrayList(proxy.findAllEvents());
		tableaffichage.setItems(data);
	}
		*/
	
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

	navigate("Search.fxml");
	}



@FXML
void promoh (ActionEvent event) throws NamingException{
	

	 boolean showAlerte = false;
    String des = "";
    String msg = "Error you must Verify";
    
	  Context context = new InitialContext();
		EventServiceRemote companyserviceRemote =

				(EventServiceRemote) context
						.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");
if(tableaffichage.getSelectionModel().getSelectedItem().getNbparticipant()<tableaffichage.getSelectionModel().getSelectedItem().getNbplaces()/2) {
	showAlerte = true;
	 des = "You Can't do Promotion Here, Verify the Number of Participations for this Event";
	 
	 if (showAlerte) {
        showMessageDialoge(msg, des);
    } 
	 
	
}else {
     
		companyserviceRemote.Promoo(tableaffichage.getSelectionModel().getSelectedItem().getId());
		//ShowItem();

		colonetitre.setCellValueFactory(new PropertyValueFactory<Event, String>("nom"));
	coloneadress.setCellValueFactory(new PropertyValueFactory<Event, String>("adresse"));
	colonedescription.setCellValueFactory(new PropertyValueFactory<Event, String>("description"));
	colonedebut.setCellValueFactory(new PropertyValueFactory<Event, Date>("dateEvent"));
	colonefin.setCellValueFactory(new PropertyValueFactory<Event, Date>("datefin"));
	colonePlaces.setCellValueFactory(new PropertyValueFactory<Event, Integer>("nbplaces"));
	colonePrice.setCellValueFactory(new PropertyValueFactory<Event, Integer>("price"));
ObservableList<Event> data = FXCollections.observableArrayList(companyserviceRemote.findAllEvents());
	tableaffichage.setItems(data);

	
	
}}

@FXML
void uploadimage (ActionEvent event) throws NamingException{
	
	 Stage stage = new Stage();
     Stage currentStage = (Stage) upload.getScene().getWindow();
     setExtFilters(fileChooser);
     File file = fileChooser.showOpenDialog(currentStage);
     if (file != null) {
         try {
             openNewImageWindow(file);
         } catch (URISyntaxException ex) {
             Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
	
	
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




}

         //  if (!tableaffichage.getSelectionModel().isEmpty()) {
           //    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             //  alert.setTitle("Can not Delete Table is Empty");
              // alert.setHeaderText("Delete that Company :  "
                //       + tableaffichage.getSelectionModel().getSelectedItem().getIdCompany()+" with name : "+TableCompany.getSelectionModel().getSelectedItem().getCompanyName());
              // Optional<ButtonType> result = alert.showAndWait();
            
 		
             

       


 	
 	
	
	

	

	
	
	

	
	/*
	@FXML
    void modifier(ActionEvent event) {

       	
  	  try {

            if (!tableaffichage.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Can not Update Table is Empty");
                alert.setHeaderText("Update that Company :  "
                        + tableaffichage.getSelectionModel().getSelectedItem().getId()+" with name : "+tableaffichage.getSelectionModel().getSelectedItem().getCompanyName());
               /* Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {*/
                   
              	/*  Context context = new InitialContext();
            		EventServiceRemote companyserviceRemote =

            				(EventServiceRemote) context
            						.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");

                 
            	 Event c = companyserviceRemote.findEventById((tableaffichage.getSelectionModel().getSelectedItem().getId()));
            	 c.setAdresse(adresse.getText());
            	 c.setNom(titre.getText());
            	
            	 companyserviceRemote.UpdateEvent(c);
            		ShowItem();
                }

            
        } catch (Exception ex) {
            System.out.println("erreur lors du chargement des forums " + ex.getMessage());

        }
    	
    }*/

	
	
	/*
    public List<Event> ShowItem() throws NamingException {
		//Event<List<Event>> task = new Event()
		
		List <Event> task = (List<Event>) new Event();
		{
			Context context = new InitialContext();
			EventServiceRemote companyserviceRemote =

					(EventServiceRemote) context
							.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/EventService!tn.esprit.b4.esprit1718b4tourism.services.EventServiceRemote");


			
			/*protected Object call() {
				// Platform.runLater(() -> prog.setVisible(true));

				List<Event> compa = companyserviceRemote.findAllEvents();
				return compa;
			}
		};
		((Object) task).setOnSucceeded(e -> {
			
			Company_Name.setCellValueFactory(new PropertyValueFactory<>("companyName"));

			Company_Offre.setCellValueFactory(new PropertyValueFactory<>("offreType"));
			

			TableCompany.setItems(FXCollections.observableArrayList(task.getValue()));
		});
		task.setOnFailed(e -> {
			try {
				ShowItem();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		Thread th = new Thread(task);
		th.start();
	}

	*/
	
	

	
	
	
	
	
	


