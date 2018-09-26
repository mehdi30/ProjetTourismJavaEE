package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.NamingException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tn.esprit.b4.esprit1718b4tourism.app.client.businessDelegate.UserServiceDelegate;
import tn.esprit.b4.esprit1718b4tourism.entities.Role;
import tn.esprit.b4.esprit1718b4tourism.entities.User;


public class AdminManageAccountsController implements Initializable {

    @FXML
    private TextField first_name_input;
    @FXML
    private TextField last_name_input;
    @FXML
    private Label first_name;
    @FXML
    private Label last_name;
    @FXML
    private JFXDatePicker birthday;
    @FXML
    private Label email;
    @FXML
    private CheckBox OrientMember;
    @FXML
    private Button delete_account;
    @FXML
    private TableView<User> table_users;
    @FXML
    private TableColumn<User, String> c1;
    @FXML
    private TableColumn<User, String> c2;
    @FXML
    private TableColumn<User, Boolean> c3;
    @FXML
    private TableColumn<User, String> c4;
    @FXML
    private Label phone;
    @FXML
    private Pane pane3;
    @FXML
    private AnchorPane arriere;
    @FXML
    private ImageView imgv;
    @FXML
    private JFXButton Premium_access;
    @FXML
    private JFXButton Orient_member;
    @FXML
    private Label acessLevel;
    @FXML
    private ImageView client_image;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //init staticVar for selectedUser

        User x = new User();
        x.setFirstName("none");
        StaticVars.setSelectedUser(x);

        //Hide 3d part of window
        pane3.setVisible(false);

 
            c1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            c2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            c3.setCellValueFactory(new PropertyValueFactory<>("orienMember"));
            c4.setCellValueFactory(new PropertyValueFactory<>("role"));

            ObservableList<User> data = FXCollections.observableArrayList(UserServiceDelegate.findAll());
     
            table_users.setItems(data);

      


    }

    @FXML
    private void first_name_input_key_releaset(KeyEvent event) throws NamingException, ParseException {
        update_output();
    }

    @FXML
    private void last_name_input_key_released(KeyEvent event) throws NamingException, ParseException {
        update_output();
    }

    @FXML
    private void delete_account_action(ActionEvent event) throws NamingException {

        if (StaticVars.getSelectedUser().getFirstName() == "none") {
            StaticVars.alerte("Please select an account");
        } else {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete account");
            alert.setHeaderText("Be careful !!!!");
            alert.setContentText("That can't be undone, Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
    
                //proxy.removeById(StaticVars.getSelectedUser().getId());
                StaticVars.alerte("Removed account");
                User x = new User();
                x.setFirstName("none");
                StaticVars.setSelectedUser(x);
                pane3.setVisible(false);

            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }

    }

    @FXML
    private void table_users_clicked(MouseEvent event) {

        if (event.getClickCount() == 1) {
            User u = table_users.getSelectionModel().getSelectedItem();
            StaticVars.setSelectedUser(u);

            first_name.setText(u.getFirstName());
            last_name.setText(u.getLastName());
            birthday.setValue(Instant.ofEpochMilli(StaticVars.selectedUser.getBirthdayDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            //birthday.setText(u.getBirthdayDate());
            email.setText(u.getEmail());
            phone.setText(u.getPhone());

            OrientMember.setSelected(u.getOrienMember());
            //premiumAccess.setSelected(true);
            acessLevel.setText(u.getRole().toString());
            
            try {
                if(StaticVars.selectedUser.getIm()!=null) {
                	client_image.setVisible(true);
                	BufferedImage bi = ImageIO.read(new ByteArrayInputStream(StaticVars.selectedUser.getIm()));
                	Image image = SwingFXUtils.toFXImage(bi, null);
                	client_image.setImage(image);
                	}else {
                		client_image.setVisible(false);	
                	}
        		} catch (Exception e) {
        			System.out.println("error with my image");
        		}
            
            pane3.setVisible(true);
        }

    }

    private void update_output() throws NamingException, ParseException {
        User u = new User();
        u.setFirstName(first_name_input.getText());
        u.setLastName(last_name_input.getText());

        	c1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            c2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            c3.setCellValueFactory(new PropertyValueFactory<>("orienMember"));
            c4.setCellValueFactory(new PropertyValueFactory<>("role"));

            ObservableList<User> data = FXCollections.observableArrayList(UserServiceDelegate.findByParam(u));
            table_users.setItems(data);

      

    }




 

    @FXML
    private void arriere_action(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ClientHome.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();

        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
    
    @FXML
    private void Premium_access_click(ActionEvent event) throws NamingException, ParseException {
    	  if (StaticVars.getSelectedUser().getFirstName() == "none") {
              StaticVars.alerte("Please select an account");
          }else if (StaticVars.getSelectedUser().getId() == StaticVars.getCurrentUser().getId()) {
              StaticVars.alerte("You can't change it, you are logged as admin");
          } else {
        	  
       

              Alert alert = new Alert(AlertType.CONFIRMATION);
              alert.setTitle("Access level");
              alert.setHeaderText("Choose");
              alert.setContentText("Make selected user as :");
              
              ButtonType buttonTypeOne = new ButtonType("Standard");
              ButtonType buttonTypeTwo = new ButtonType("Premium");
              ButtonType buttonTypeThree = new ButtonType("Admin");
              ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

              alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel,buttonTypeThree);

              Optional<ButtonType> result = alert.showAndWait();
              if (result.get() == buttonTypeOne){
            	  StaticVars.getSelectedUser().setRole(Role.Standard);
            	  pane3.setVisible(false);
            	  UserServiceDelegate.updateUser(StaticVars.getSelectedUser());
            	  StaticVars.alerte("done !");
            	  update_output();
              } else if (result.get() == buttonTypeTwo) {
            	  StaticVars.getSelectedUser().setRole(Role.Premium);
            	  pane3.setVisible(false);
            	  UserServiceDelegate.updateUser(StaticVars.getSelectedUser());
            	  StaticVars.alerte("done !");
            	  update_output();
              }else if (result.get() == buttonTypeThree) {
            	  StaticVars.getSelectedUser().setRole(Role.Admin);
            	  pane3.setVisible(false);
            	  UserServiceDelegate.updateUser(StaticVars.getSelectedUser());
            	  StaticVars.alerte("done !");
            	  update_output();
              } else {
                  // ... user chose CANCEL or closed the dialog
              }
          }

    }

    @FXML
    private void Orient_member_action(ActionEvent event) throws NamingException, ParseException {
    	 if (StaticVars.getSelectedUser().getFirstName() == "none") {
             StaticVars.alerte("Please select an account");
         } else {
       	  
     
             Alert alert = new Alert(AlertType.CONFIRMATION);
             alert.setTitle("Orient membership");
             alert.setHeaderText("Choose");
             alert.setContentText("");
             
             ButtonType buttonTypeOne = new ButtonType("Give membership");
             ButtonType buttonTypeTwo = new ButtonType("Delete membership");
             ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

             alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

             Optional<ButtonType> result = alert.showAndWait();
             if (result.get() == buttonTypeOne){
           	  StaticVars.getSelectedUser().setOrienMember(true);
           	  pane3.setVisible(false);
           	UserServiceDelegate.updateUser(StaticVars.getSelectedUser());
           	  StaticVars.alerte("done !");
           	  update_output();
             } else if (result.get() == buttonTypeTwo) {
            	 StaticVars.getSelectedUser().setOrienMember(false);
           	  pane3.setVisible(false);
           	UserServiceDelegate.updateUser(StaticVars.getSelectedUser());
           	  StaticVars.alerte("done !");
           	  update_output();
             } else {
                 // ... user chose CANCEL or closed the dialog
             }
         }


    }

}
