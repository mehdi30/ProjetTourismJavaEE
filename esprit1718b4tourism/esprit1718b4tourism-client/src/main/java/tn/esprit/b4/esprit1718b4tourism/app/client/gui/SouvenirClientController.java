package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.esprit.b4.esprit1718b4tourism.services.ExperienceServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.SouvenirService;
import tn.esprit.b4.esprit1718b4tourism.services.SouvenirServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.entities.Event;
import tn.esprit.b4.esprit1718b4tourism.entities.Souvenir;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;



public class SouvenirClientController extends AnchorPane implements Initializable {

	public static String nomRando;
	public static int nb;
	public static int idSouv;
	public static float pricee;
	    @FXML
	    private VBox box;

	    @FXML
	    private JFXButton event;

	    @FXML
	    private JFXButton message;

	    @FXML
	    private JFXButton notification;

	    @FXML
	    private JFXButton jobOffer;

	    @FXML
	    private JFXButton logOut;
	    
	    
	    @FXML
	    private JFXButton btn;
	    
	    

	    @FXML
	    private ImageView imgv;

	    @FXML
	    private JFXTextField searchForum;

	    @FXML
	    private JFXButton DeleteSouvenir;

	    @FXML
	    private JFXButton UpdateForumm;

	    @FXML
	    private JFXComboBox<String> forumBox;

	    @FXML
	    private JFXComboBox<String> sortCategory;

	    @FXML
	    private JFXComboBox<String> sortByCommentsNumb;

	    @FXML
	    private JFXButton goToStat;

	    @FXML
	    private Label errorCom;

	    @FXML
	    private JFXButton inappropriate;

	    @FXML
	    private JFXButton AddSouvenir;

	    @FXML
	    private JFXDatePicker dateExperience;

	    @FXML
	    private JFXTextField name;

	    @FXML
	    private JFXTextField quantity;

	    @FXML
	    private JFXTextField price;

	    @FXML
	    private JFXTextField category;

	    //@FXML
	    //private JFXTextField starTime;

	    @FXML
	    private JFXTextArea description;

	    @FXML
	    private TableView<Souvenir> tableView;
	    
	    @FXML
	    private TableColumn<Souvenir, String> ColumnName;

	   // @FXML
	   // private TableColumn<Souvenir, String> ColumnLocation;

	    @FXML
	    private TableColumn<Souvenir, Integer> ColumnPrice;

	    @FXML
	    private TableColumn<Souvenir, Integer> ColumnQuantity;

	    @FXML
	    private TableColumn<Souvenir, String> ColumnCategory;

	    //@FXML
	   // private TableColumn<Souvenir, String> ColumnTime;

	    @FXML
	    private TableColumn<Souvenir, String> ColumnDescription;



	    @FXML
	    private JFXHamburger hamburger;

	    @FXML
	    private JFXDrawer drawer;

	    @FXML
	    private Label forumOwner;
	    
	    
	    @FXML 
	    private JFXButton UpdateSouvenir;


	    
	    @FXML
	    void DeleteExperience(ActionEvent event) {
	    	 
	    	    	  
	    	    	
	    	    }

	   
	    @FXML
	    void Search(KeyEvent event) throws NamingException {
				
	    }



		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			searchForum.setStyle("-fx-text-inner-color: white;");
			drawer.blendModeProperty();
	    	drawer.setSidePane(box);
	    	
	    	
	    	ObservableList<String> items = FXCollections.observableArrayList("recruitment",
			        "Student life","Professional life","Website quality of services");
	        forumBox.setItems(items);
			sortCategory.setItems(items);	
			
			ObservableList<String> itemss = FXCollections.observableArrayList("Having most comments number" ,"Having least comments number");
	        sortByCommentsNumb.setItems(itemss);

	    /*	
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
	        });
	        */
	    	InitialContext ctx;
	    	try {
	    		ctx = new InitialContext();
	    		SouvenirServiceRemote proxy;

	    		proxy = (SouvenirServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/SouvenirService!tn.esprit.b4.esprit1718b4tourism.services.SouvenirServiceRemote");

	    		ColumnName.setCellValueFactory(new PropertyValueFactory<Souvenir, String>("name"));
	    		//ColumnLocation.setCellValueFactory(new PropertyValueFactory<Souvenir, String>("location"));
	    		ColumnPrice.setCellValueFactory(new PropertyValueFactory<Souvenir, Integer>("price"));
	    		ColumnQuantity.setCellValueFactory(new PropertyValueFactory<Souvenir, Integer>("quantity"));
	    		ColumnCategory.setCellValueFactory(new PropertyValueFactory<Souvenir, String>("category"));
	    	//	ColumnTime.setCellValueFactory(new PropertyValueFactory<Souvenir, String>("starTime"));
	    		ColumnDescription.setCellValueFactory(new PropertyValueFactory<Souvenir, String>("description"));
	    		ObservableList<Souvenir> data = FXCollections.observableArrayList(proxy.showAllSouvenir());
	    		tableView.setItems(data);
	    	} catch (NamingException e1) {
	    		// TODO Auto-generated catch block
	    		e1.printStackTrace();
		}
	    	
	    	tableView.setRowFactory(new Callback<TableView<Souvenir>, TableRow<Souvenir>>() {
	            @Override
	            public TableRow<Souvenir> call(TableView<Souvenir> tableView) {
	                final TableRow<Souvenir> row = new TableRow<>();
	                final ContextMenu contextMenu = new ContextMenu();
	                final MenuItem detailMenuItem = new MenuItem("Show Details");
	               // final MenuItem ReserverMenuItem = new MenuItem("Update Event");

	                detailMenuItem.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent event) {

	                       
                            
	                    	nomRando= row.getItem().getName();
	                    	nb=row.getItem().getQuantity();
	                    	idSouv=row.getItem().getIdSouvenir();
	                    	pricee = row.getItem().getPrice();
                        
	                        navigate("clientConsulteDetailSouvenir.fxml");
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
		
		
		private void navigate(String fxml) {
	        try {
	            Stage stage = new Stage();
	            Stage currentStage = (Stage) btn.getScene().getWindow();
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
		    void goToStats(ActionEvent event) {

		    }
		 
		 

		    @FXML
		    void inappropriateComments(ActionEvent event) {

		    }

		    @FXML
		    void sortByCategory(ActionEvent event) {

		    }

		    @FXML
		    void sortByCommentsNumber(ActionEvent event) {

	
		    
		    
		    }
		    
		  
/*
@SuppressWarnings("deprecation")
@FXML
void DeleteSouvenir(ActionEvent event) throws NamingException {
	try {

        if (!tableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Can not Delete Table is Empty");
          
          	  Context context = new InitialContext();
          	  SouvenirServiceRemote souvenirserviceRemote = (SouvenirServiceRemote) context.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/SouvenirService!tn.esprit.b4.esprit1718b4tourism.services.SouvenirServiceRemote");
          	  souvenirserviceRemote.DeleteById(tableView.getSelectionModel().getSelectedItem().getIdSouvenir());
        		ObservableList<Souvenir> data = FXCollections.observableArrayList(souvenirserviceRemote.showAllSouvenir());
  			tableView.setItems(data);
        }
    } catch (Exception ex) {
        System.out.println("erreur lors du chargement " + ex.getMessage());

    }

	
}



@SuppressWarnings("deprecation")
	@FXML
   void AddSouvenir(ActionEvent event) throws NamingException {
   

		InitialContext ctx;
		ctx = new InitialContext();
		SouvenirServiceRemote proxy;
		proxy = (SouvenirServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/SouvenirService!tn.esprit.b4.esprit1718b4tourism.services.SouvenirServiceRemote");


		if (!name.getText().equals("") && !description.getText().equals("") && !quantity.getText().equals("")  && !category.getText().equals("")&& !price.getText().equals("")) {

		//Souvenir e1= new Souvenir(name.getText(), quantity.getText(), description.getText(),category.getText(),Integer.parseInt (price.getText()));
			Souvenir e1= new Souvenir(name.getText(), Integer.parseInt(quantity.getText()), description.getText(),Integer.parseInt (price.getText()),category.getText());
		
		
		
		
			//e1.setDateExperience(d);
			proxy.AddSouvenir(e1);
			name.clear();
			description.clear();
			category.clear();
			quantity.clear();
			price.clear();

			//location.clear(); 
			ObservableList<Souvenir> data = FXCollections.observableArrayList(proxy.showAllSouvenir());
			tableView.setItems(data);
		} else {
			JOptionPane.showMessageDialog(null,"Please Complete All Fields");

		}
   	
	}



@SuppressWarnings("deprecation")
		@FXML
	    void UpdateSouvenir(ActionEvent event) throws NamingException {
	    	Integer selected = tableView.getSelectionModel().getSelectedIndex();
			InitialContext ctx;
			ctx = new InitialContext();
			SouvenirServiceRemote proxy;
			proxy = (SouvenirServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/SouvenirService!tn.esprit.b4.esprit1718b4tourism.services.SouvenirServiceRemote");
			if (tableView.getSelectionModel().isSelected(selected)) {

				Souvenir ee = tableView.getItems().get(selected);
				ee.setName(name.getText());
				//ee.setQuantity((Integer.parseInt(quantity.getText())));
				ee.setCategory(category.getText());
				//ee.setPrice(String.valueOf (price.getText()));
				ee.setDescription(description.getText());



				//ee.setDateExperience(d);
				proxy.UpdateSouvenir(ee);
				//UpdateEvent(ee);

			}
			ObservableList<Souvenir> data = FXCollections.observableArrayList(proxy.showAllSouvenir());
			tableView.setItems(data);
   

	    }


*/


@FXML
	public void btnFiltrer(ActionEvent event) {
	
		
		}

}
	


		