package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4tourism.app.client.businessDelegate.RestaurantServiceDelegate;
import tn.esprit.b4.esprit1718b4tourism.app.client.gui.StaticVars;
import tn.esprit.b4.esprit1718b4tourism.entities.Restaurant;
import javax.naming.NamingException;

public class MyRestauController implements Initializable {


    @FXML
    private TableView<Restaurant> List_Restau;
    @FXML
    private TableColumn<Restaurant, String> Name;
    @FXML
    private TableColumn<Restaurant, String> Country;
    @FXML
    private TableColumn<Restaurant, String> Address;
    @FXML
    private TableColumn<Restaurant, Float> Fork_Spoon;
    @FXML
    TableColumn<Restaurant , Float> Coins ;
  @FXML
    TableColumn<Restaurant, String> View ;
  @FXML
    TableColumn<Restaurant, String> Town ;
  @FXML
  private TableColumn<Restaurant, String> Speciality;
    @FXML
    private Button add_restaurant;
    @FXML
    private Button Home;
    @FXML
    private ImageView imgv;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
                   update_table();
    }    
    
     
    @FXML
    private void double_click(MouseEvent event) throws NamingException, IOException {
  
    	
        if (event.getClickCount() == 2) {
        	
        Restaurant r = new Restaurant();
        r = List_Restau.getSelectionModel().getSelectedItem();
        StaticVars.setSelectedRestaurant(r);
        
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Restaurant");
            alert.setHeaderText("Choose");
            alert.setContentText("");
            
            ButtonType buttonTypeOne = new ButtonType("Update");
            ButtonType buttonTypeTwo = new ButtonType("Delete");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){

         	   Parent root = FXMLLoader.load(getClass().getResource("ModifyRestaurant.fxml"));
                Scene newScene = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(newScene);
                window.show();
            } else if (result.get() == buttonTypeTwo) {
                        RestaurantServiceDelegate.removeById(r.getId());
                	 StaticVars.alerte("deleted !");
               
                	 update_table();
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
        
    }

    @FXML
    private void add_restaurant_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Restaurant.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
    
    private void update_table() {
       
   		Name.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("Name"));
   		
   		Country.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("Country"));
   		Town.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("Town"));
   		Address.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("Address"));
   		Fork_Spoon.setCellValueFactory(new PropertyValueFactory<Restaurant, Float>("fork_spoon"));
   		Coins.setCellValueFactory(new PropertyValueFactory<Restaurant, Float>("Coins"));
   		Speciality.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("Specialite"));
   		View.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("view"));
   		
   	  
   		ObservableList<Restaurant> data = FXCollections.observableArrayList(RestaurantServiceDelegate.showMyRestaurants(StaticVars.currentUser.getId()));
   		
   		List_Restau.setItems(data);

    }

    @FXML
    private void Home_action(ActionEvent event) throws IOException {
    	  Parent root = FXMLLoader.load(getClass().getResource("ClientHome.fxml"));
          Scene newScene = new Scene(root);
          Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
          window.setScene(newScene);
          window.show();
    }


    
}
