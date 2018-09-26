package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.b4.esprit1718b4tourism.entities.Experience;
import tn.esprit.b4.esprit1718b4tourism.entities.ExperienceLike;
import tn.esprit.b4.esprit1718b4tourism.services.ExperienceAvisServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.ExperienceLikeServiceRemote;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

public class MyListExperiences extends AnchorPane implements Initializable, Serializable {

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
    private ImageView imgv;

    @FXML
    private Rating Ratingbaa;

    @FXML
    private Text location1;

    @FXML
    private Text duration;

    @FXML
    private Text description;

    @FXML
    private Text date;

    @FXML
    private Text starTime;

    @FXML
    private Text starTime1;

    @FXML
    private TableView<ExperienceLike> tableView;

    @FXML
    private TableColumn<ExperienceLike, String> ColumnName;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private Label forumOwner;

    @FXML
    private ImageView retour;
    @FXML
	private AnchorPane anchor;
    @FXML
	private ImageView Dislike;

	@FXML
	private ImageView Like;



    @FXML
    void get(MouseEvent event) throws NamingException {
		anchor.setVisible(true);

    	location1.setText(tableView.getSelectionModel().getSelectedItem().getExperience().getLocation());
    	starTime.setText(tableView.getSelectionModel().getSelectedItem().getExperience().getDuration());
    	description.setText(tableView.getSelectionModel().getSelectedItem().getExperience().getDescription());
    	starTime1.setText(tableView.getSelectionModel().getSelectedItem().getExperience().getStarTime());
    	date.setText(String.valueOf(tableView.getSelectionModel().getSelectedItem().getExperience().getDateExperience()));
    	
    	InitialContext ctx3;

		ctx3 = new InitialContext();

		ExperienceAvisServiceRemote proxy;
		proxy = (ExperienceAvisServiceRemote) ctx3.lookup(
				"esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceAvisService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceAvisServiceRemote");
		
           if(proxy.CheckExp(tableView.getSelectionModel().getSelectedItem().getExperience().getId()).isEmpty())
           {
        	   Ratingbaa.setVisible(false);
           

           }
           else {        	   Ratingbaa.setVisible(true);
       	

       		double moy = proxy.AvgRate(tableView.getSelectionModel().getSelectedItem().getExperience().getId());
       		Ratingbaa.setRating(moy);

           }
       	InitialContext ctx; 
       	ctx = new InitialContext();

           ExperienceLikeServiceRemote proxy3; 
 		  proxy3 = (ExperienceLikeServiceRemote) ctx.lookup(
 		  "esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceLikeService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceLikeServiceRemote"); 
 		 if(proxy3.CheckExpAndUser(tableView.getSelectionModel().getSelectedItem().getExperience().getId(),StaticVars.getCurrentUser().getId()).isEmpty()){
 			Dislike.setVisible(true);
           	Like.setVisible(false);
 		 }else {
 			Like.setVisible(true);
 	       	Dislike.setVisible(false);
 		 }

    }
    @FXML
    void Dislike(MouseEvent event) {
    	Dislike.setVisible(false);
    	Like.setVisible(true);
    	
    	InitialContext ctx; 
		  try { ctx = new
		  InitialContext();
		  
		  ExperienceLikeServiceRemote proxy; 
		  proxy = (ExperienceLikeServiceRemote) ctx.lookup(
		  "esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceLikeService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceLikeServiceRemote"); 
		 proxy.AddLikeExperience(StaticVars.currentUser.getId(), tableView.getSelectionModel().getSelectedItem().getExperience().getId(),tableView.getSelectionModel().getSelectedItem().getExperience().getName());
		  
		 Notifications notificationBuilder = Notifications.create().title("Like")
					.text("Add Experience "+tableView.getSelectionModel().getSelectedItem().getExperience().getName()+" to wish list !").graphic(null)
					.hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_RIGHT)
					.onAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							System.out.println("Clicked on Notifications");

						}
					});
			notificationBuilder.darkStyle();
			notificationBuilder.showWarning();
		  
		   } catch
		  (NamingException e) { 
		  e.printStackTrace(); } 
	

    }
    @FXML
    void Like(MouseEvent event) {
    	
    	InitialContext ctx; 
		  try { ctx = new
		  InitialContext();
		  
		  ExperienceLikeServiceRemote proxy; 
		  proxy = (ExperienceLikeServiceRemote) ctx.lookup(
		  "esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceLikeService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceLikeServiceRemote"); 
		 //proxy.DeleteById(tableView.getSelectionModel().getSelectedItem().getId());
		  
		  ExperienceLike E = proxy.retExperiencefromLike(tableView.getSelectionModel().getSelectedItem().getExperience().getId());
		  proxy.deleteLike(E);
		  
		  
		  Notifications notificationBuilder = Notifications.create().title("Dislike")
					.text("Delete Experience "+tableView.getSelectionModel().getSelectedItem().getExperience().getName()+" from wish list !").graphic(null)
					.hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_RIGHT)
					.onAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							System.out.println("Clicked on Notifications");

						}
					});
			notificationBuilder.darkStyle();
			notificationBuilder.showWarning();
		   } catch
		  (NamingException e) { 
		  e.printStackTrace(); } 
	    	Like.setVisible(false);

		  Dislike.setVisible(true);
	

    }
    private void Switch(String fxml) {
		try {
			Stage stage = new Stage();
			Stage currentStage = (Stage) retour.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource(fxml));

			Scene scene = new Scene(root);

			stage.setScene(scene);
			stage.setResizable(false);

			stage.show();
			currentStage.close();
		} catch (IOException ex) {
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		anchor.setVisible(false);
		
		 InitialContext ctx5; 
		  try { ctx5 = new
		  InitialContext();
		  
		  ExperienceLikeServiceRemote proxy4; 
		 proxy4 = (ExperienceLikeServiceRemote) ctx5.lookup(
		  "esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceLikeService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceLikeServiceRemote"); 
		  
		 ColumnName.setCellValueFactory(new PropertyValueFactory<ExperienceLike, String>("type"));
			ObservableList<ExperienceLike> data = FXCollections.observableArrayList(proxy4.ReturnExpLikeByUser(StaticVars.currentUser.getId()));
			tableView.setItems(data);
	 } catch
	  (NamingException e) { 
	  e.printStackTrace(); } 
		  
		  retour.setOnMouseClicked(a -> {
				try {
					Switch("ExperienceBooking.fxml");
				} catch (Exception e) {
				}
			});

	}
	
}
