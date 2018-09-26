package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4tourism.app.client.businessDelegate.RestaurantServiceDelegate;
import tn.esprit.b4.esprit1718b4tourism.app.client.gui.StaticVars;
import tn.esprit.b4.esprit1718b4tourism.entities.Restaurant;
import tn.esprit.b4.esprit1718b4tourism.services.RestaurantServiceRemote;

public class ModifyRestaurantController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private ComboBox<String> Town;
    @FXML
    private ComboBox<String> Country;
    @FXML
    private TextArea adress;
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
    

    String[] CountryTab = {"Tunisia", "France", "Italy"};
      String[] Tunisia = {"Tunis", "Lac", "Ben Arous", "Bizerte"};
      String[] France = {"Paris", "Marseille", "Nice"};
      String[] Italy = {"Rome", "Venise"};

      String[] A[] = {Tunisia, France, Italy};
      
      String[] SpecialityTab = {"local","italian", "asian"};
      
      int etoilee;
    @FXML
    private Button add;
    @FXML
    private RadioButton radio1;
    @FXML
    private RadioButton radio2;
    @FXML
    private RadioButton radio3;
    @FXML
    private ComboBox<String> speciality;
    @FXML
    private TextField coins;
    @FXML
    private Button Cancel;
    @FXML
    private ImageView imgv;
    
      public void remplirComboBox(ComboBox<String> C, String[] T) {
          C.getItems().addAll(Arrays.asList(T));
          C.getSelectionModel().select(0);
      }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	final ToggleGroup group = new ToggleGroup();
        radio1.setToggleGroup(group);
        radio2.setToggleGroup(group);
        radio3.setToggleGroup(group);
        radio3.setSelected(true);
        
    	remplirComboBox(Country, CountryTab);
    	remplirComboBox(Town, Tunisia);
    	rad1.setSelected(true);
    	etoilee=1;
    	
    	remplirComboBox(speciality, SpecialityTab);
    	
    	coins.setText(StaticVars.getSelectedRestaurant().getCoins().toString());
    	name.setText(StaticVars.getSelectedRestaurant().getName());
    	Country.setValue(StaticVars.getSelectedRestaurant().getCountry());
    	Town.setValue(StaticVars.getSelectedRestaurant().getTown());
    	adress.setText(StaticVars.getSelectedRestaurant().getAddress());
    	speciality.setValue(StaticVars.getSelectedRestaurant().getSpecialite());
    	if(StaticVars.getSelectedRestaurant().getView().equals("Sea")) radio1.setSelected(true);
		if(StaticVars.getSelectedRestaurant().getView().equals("Natural")) radio2.setSelected(true);
		if(StaticVars.getSelectedRestaurant().getView().equals("None")) radio3.setSelected(true);
    	
    }    


    @FXML
    private void Country_click(ActionEvent event) {
    	Town.getItems().clear();
        int x = Country.getSelectionModel().getSelectedIndex();
        remplirComboBox(Town, A[x]);
    }

    @FXML
    private void rad1_click(ActionEvent event) {
    	rad1.setSelected(true);
    	rad2.setSelected(false);
    	rad3.setSelected(false);
    	rad4.setSelected(false);
    	rad5.setSelected(false);
    	etoilee=1;
    }

    @FXML
    private void rad2_click(ActionEvent event) {
    	rad1.setSelected(true);
    	rad2.setSelected(true);
    	rad3.setSelected(false);
    	rad4.setSelected(false);
    	rad5.setSelected(false);
    	etoilee=2;
    }

    @FXML
    private void rad3_click(ActionEvent event) {
    	rad1.setSelected(true);
    	rad2.setSelected(true);
    	rad3.setSelected(true);
    	rad4.setSelected(false);
    	rad5.setSelected(false);
    	etoilee=3;
    }

    @FXML
    private void rad4_click(ActionEvent event) {
    	rad1.setSelected(true);
    	rad2.setSelected(true);
    	rad3.setSelected(true);
    	rad4.setSelected(true);
    	rad5.setSelected(false);
    	etoilee=4;
    }

    @FXML
    private void rad5_click(ActionEvent event) {
    	rad1.setSelected(true);
    	rad2.setSelected(true);
    	rad3.setSelected(true);
    	rad4.setSelected(true);
    	rad5.setSelected(true);
    	etoilee=5;
    }

    @FXML
    private void add_click(ActionEvent event) throws NamingException, IOException {
    	
	
			String msg;
			if((msg=StaticVars.VerifierLongChaine(name.getText(), "<<Name>>", 4, 20))!="valide") StaticVars.alerte(msg);
			else if((msg=StaticVars.VerifierLongChaine(adress.getText(), "<<Address>>", 4, 50))!="valide") StaticVars.alerte(msg);
			else{
				Restaurant r = new Restaurant();
				r.setId(StaticVars.getSelectedRestaurant().getId());
				r.setId_user(StaticVars.currentUser.getId()); 
				r.setName(name.getText());
				r.setCountry(Country.getValue());
				r.setTown(Town.getValue());
				r.setAddress(adress.getText());
				r.setFork_spoon(etoilee);
				r.setCoins(Integer.parseInt(coins.getText()));
				r.setSpecialite(speciality.getValue());
				if(radio1.isSelected()) r.setView("Sea");
				if(radio2.isSelected()) r.setView("Natural");
				if(radio3.isSelected()) r.setView("None");
				RestaurantServiceDelegate.updateRestaurant(r);
				StaticVars.alerte("modified restaurant : "+r.getName()) ;
				
				 Parent root = FXMLLoader.load(getClass().getResource("MyRestau.fxml"));
			        Scene newScene = new Scene(root);
			        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			        window.setScene(newScene);
			        window.show();
				
		
				}
				}

    @FXML
    private void corriger_coins2(KeyEvent event) {
        try {
            int opt = Integer.parseInt(coins.getText());
            if (opt < 0) {
            	coins.setText("0");
            }
        } catch (Exception e) {
        	coins.setText("0");
        }

    }

    @FXML
    private void Cancel_action(ActionEvent event) throws IOException {
    	  Parent root = FXMLLoader.load(getClass().getResource("MyRestau.fxml"));
          Scene newScene = new Scene(root);
          Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
          window.setScene(newScene);
          window.show();
    }

    }
    

