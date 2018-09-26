/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4tourism.app.client.businessDelegate.RestaurantServiceDelegate;
import tn.esprit.b4.esprit1718b4tourism.app.client.gui.StaticVars;
import tn.esprit.b4.esprit1718b4tourism.entities.Restaurant;
import javax.naming.NamingException;

public class AdminCherRestauController implements Initializable {

    @FXML
    private ComboBox<String> SearchB; 
    @FXML
    private ComboBox<String> Type;
    @FXML
    private TableView<Restaurant> List_Restau;
    @FXML
    private TableColumn<Restaurant, String> Name;
    @FXML
    private TableColumn<Restaurant, String> Country;
    TableColumn<Restaurant , Float> Coins ;
  @FXML
    TableColumn<Restaurant, String> View ;
  @FXML
    TableColumn<Restaurant, String> Town ;
  @FXML
  private TableColumn<Restaurant, String> Speciality;
  @FXML
  private ComboBox<String> min_max;
  

     
    String[] CountryTab = {"All","Tunisia", "France", "Italy"};
    String[] Tunisia = {"All","Tunis", "Lac", "Ben Arous", "Bizerte"};
    String[] France = {"All","Paris", "Marseille", "Nice"};
    String[] Italy = {"All","Rome", "Venise"};

    String[] A[] = {Tunisia, France, Italy};
    String[] minMax = {"0_10","10_30", "30_more"};
    String[] empty = {"All"};
    @FXML
    private Button home;
    @FXML
    private Pane pane;
    @FXML
    private RadioButton rad1;
    @FXML
    private RadioButton rad2;
    @FXML
    private RadioButton rad3;
    @FXML
    private RadioButton rad4;
    @FXML
    private RadioButton rad5;
    @FXML
    private Label Country1;
    @FXML
    private Label name;
    @FXML
    private Label town;
    @FXML
    private Label adress;
    @FXML
    private Label coins;
    @FXML
    private Label speciality;
    @FXML
    private Label view;
    @FXML
    private Button cancel;
    @FXML
    private Button Delete_restaurant;
    @FXML
    private TableColumn<Restaurant, String> Owner;
    @FXML
    private ImageView imgv;
    


      public void remplirComboBox(ComboBox<String> C, String[] T) {
          C.getItems().addAll(Arrays.asList(T));
          C.getSelectionModel().select(0);
      }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    	remplirComboBox(min_max, minMax);
    	
         remplirComboBox(SearchB, CountryTab);
         
         remplirComboBox(Type, empty);
         Type.setDisable(true);
         pane.setVisible(false);
    
                update_table();
    }    
    
       

    @FXML
    private void searchB_Click(ActionEvent event) throws NamingException {
    	
    	///////////////////////////////////
        Type.getItems().clear();
        int x = SearchB.getSelectionModel().getSelectedIndex();
        
        if(x==0){
            remplirComboBox(Type, empty);
        	Type.setDisable(true);
        }
        else{
        	Type.setDisable(false);
        	remplirComboBox( Type, A[x-1]);        	
        }
        ///////////////////////////:
        
        update_table();
        
    }


    @FXML
    private void double_click(MouseEvent event) throws NamingException {
    	
    	if (event.getClickCount() == 1) {
    		
    		
            Restaurant r = new Restaurant();
            r = List_Restau.getSelectionModel().getSelectedItem();
    		
    		name.setText(r.getName());
    		Country1.setText(r.getCountry());
    		town.setText(r.getTown());
    		adress.setText(r.getAddress());
    		view.setText(r.getView());
    		coins.setText(r.getCoins().toString());
    		speciality.setText(r.getSpecialite());
    		if(r.getFork_spoon()==1) {
    			rad1.setSelected(true);
    	    	rad2.setSelected(false);
    	    	rad3.setSelected(false);
    	    	rad4.setSelected(false);
    	    	rad5.setSelected(false);
    		} else if(r.getFork_spoon()==2) {
    			rad1.setSelected(true);
    	    	rad2.setSelected(true);
    	    	rad3.setSelected(false);
    	    	rad4.setSelected(false);
    	    	rad5.setSelected(false);
    		}else if(r.getFork_spoon()==3) {
    			rad1.setSelected(true);
    	    	rad2.setSelected(true);
    	    	rad3.setSelected(true);
    	    	rad4.setSelected(false);
    	    	rad5.setSelected(false);
    		}else if(r.getFork_spoon()==4) {
    			rad1.setSelected(true);
    	    	rad2.setSelected(true);
    	    	rad3.setSelected(true);
    	    	rad4.setSelected(true);
    	    	rad5.setSelected(false);
    		}else if(r.getFork_spoon()==5) {
    			rad1.setSelected(true);
    	    	rad2.setSelected(true);
    	    	rad3.setSelected(true);
    	    	rad4.setSelected(true);
    	    	rad5.setSelected(true);
    		}
    		
    		pane.setVisible(true);	
    	}
  
    
    }

    
    private void update_table() {
       
   		Name.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("Name"));
   		Country.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("Country"));
   		Town.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("Town"));
   	//	Address.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("Address"));
   	//	Fork_Spoon.setCellValueFactory(new PropertyValueFactory<Restaurant, Float>("fork_spoon"));
   	//	Coins.setCellValueFactory(new PropertyValueFactory<Restaurant, Float>("Coins"));
   		Speciality.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("Specialite"));
   		View.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("view"));
   		Owner.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("Owner"));
   		
   		Integer prixmin=0;
   		Integer prixmax=0;
   		if(min_max.getSelectionModel().getSelectedIndex()==0) {prixmin=0;prixmax=10;}
   		else if(min_max.getSelectionModel().getSelectedIndex()==1) {prixmin=10;prixmax=30;}
   		else if(min_max.getSelectionModel().getSelectedIndex()==2) {prixmin=30;prixmax=10000;}
   		
     		
   		ObservableList<Restaurant> data = FXCollections.observableArrayList(RestaurantServiceDelegate.findAllRestaurants(prixmin,prixmax,SearchB.getValue(),Type.getValue()));
   		List_Restau.setItems(data);

    }

    @FXML
    private void Type_action(ActionEvent event) {
    	update_table();
    }

    @FXML
    private void coins_action(ActionEvent event) {
    	update_table();
    }

    @FXML
    private void home_action(ActionEvent event) throws IOException {
    	  Parent root = FXMLLoader.load(getClass().getResource("ClientHome.fxml"));
          Scene newScene = new Scene(root);
          Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
          window.setScene(newScene);
          window.show();
    }


    @FXML
    private void cancel_action(ActionEvent event) {
    	 pane.setVisible(false);
    }

    @FXML
    private void Delete_restaurant_action(ActionEvent event) {
    	 Restaurant r = new Restaurant();
         r = List_Restau.getSelectionModel().getSelectedItem();
         StaticVars.setSelectedRestaurant(r);
         
    	 Alert alert = new Alert(AlertType.CONFIRMATION);
         alert.setTitle("Restaurant");
         alert.setHeaderText("Deleting Restaurant : " + StaticVars.getSelectedRestaurant().getName());
         alert.setContentText("that cant be undone !!");
         
        
         ButtonType buttonTypeTwo = new ButtonType("Delete");
         ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

         alert.getButtonTypes().setAll(buttonTypeTwo, buttonTypeCancel);

         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == buttonTypeTwo) {
               RestaurantServiceDelegate.removeById(r.getId());
             	 StaticVars.alerte("deleted !");
             	 pane.setVisible(false);
             	 update_table();
         } else {
             // ... user chose CANCEL or closed the dialog
         }

    }
    
}
