
package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import tn.esprit.b4.esprit1718b4tourism.entities.BookingExperience;
import tn.esprit.b4.esprit1718b4tourism.entities.Experience;
import tn.esprit.b4.esprit1718b4tourism.entities.ExperienceAvis;
import tn.esprit.b4.esprit1718b4tourism.entities.ExperienceLike;
import tn.esprit.b4.esprit1718b4tourism.entities.Notification;
import tn.esprit.b4.esprit1718b4tourism.entities.User;
import tn.esprit.b4.esprit1718b4tourism.services.BookingExperienceServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.ExperienceAvisServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.ExperienceLikeServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.ExperienceServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.NotificationServiceRemote;

public class ExperienceBController implements Initializable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String nomRando;
	public static String local;
	public static String namex;
	public static int idUserExp;

	public static int idEvent;
	public static int nb;
	public static int currentUser;
	public static int pricett;

	@FXML
	private VBox box;
	@FXML
	private Label number;
	@FXML
	private ImageView image;
	@FXML
	private JFXButton notifBtn;



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
	private ImageView exp1;
	@FXML
	private ImageView exp2;
	@FXML
	private JFXTextField search;

	@FXML
	private JFXComboBox<String> forumBox;

	@FXML
	private JFXComboBox<String> sortCategory;

	@FXML
	private JFXComboBox<String> sortByCommentsNumb;

	@FXML
	private JFXButton goToStat;
	@FXML
	private JFXButton Create;

	@FXML
	private Label errorCom;

	@FXML
	private TableView<Experience> tableView;

	@FXML
	private TableColumn<Experience, String> ColumnName;

	@FXML
	private TableColumn<Experience, String> ColumnLocation;

	@FXML
	private JFXButton BookExperience;

	@FXML
	private Text price;

	@FXML
	private Text description;
	@FXML
	private Text description1;
	@FXML
	private Text location;
	@FXML
	private Text nbAvis;
	@FXML
	private Text name;
	@FXML
	private Text name1;
	@FXML
	private Text duration;

	@FXML
	private Text starTime;

	@FXML
	private Text date;
	@FXML
	private ImageView Dislike;

	@FXML
	private ImageView Like;
	@FXML
	private ImageView mehdi;
	@FXML
	private ImageView terry;

	@FXML
	private JFXHamburger hamburger;

	@FXML
	private JFXDrawer drawer;

	@FXML
	private Label forumOwner;
	@FXML
	private AnchorPane anchoor1;
	@FXML
	private AnchorPane anchoor2;
	@FXML
	private AnchorPane anchoor3;
	@FXML
	private AnchorPane anchoor31;
	@FXML
	private Rating ratingbaa;

	@FXML
	void BookExperience(ActionEvent event) throws Exception {
		Switch("MyListExperience.fxml");


	}

	@FXML
	void Search(KeyEvent event) throws NamingException {
		InitialContext ctx;
		ctx = new InitialContext();
		ExperienceServiceRemote proxy;
		proxy = (ExperienceServiceRemote) ctx.lookup(
				"esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceServiceRemote");
		ObservableList<Experience> data = FXCollections
				.observableList(proxy.advancedSearchExperience(search.getText()));
		tableView.setItems(data);
	}
	
	@FXML
	void notifications(ActionEvent event) {
		InitialContext ctx1;

		try {
			ctx1 = new InitialContext();
			NotificationServiceRemote proxy = (NotificationServiceRemote) ctx1.lookup(
					"esprit1718b4tourism-ear/esprit1718b4tourism-service/NotificationService!tn.esprit.b4.esprit1718b4tourism.services.NotificationServiceRemote");
			for (Notification notification : proxy.getAllNotificationByUserBySeen(StaticVars.currentUser.getId())) {
				if (notification.getSeen() == false) {
					notification.setSeen(true);
					proxy.updateNotification(notification);
				}
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Switch("Notifications.fxml");
	}
	@FXML
	void get(MouseEvent event) throws NamingException {
		if(StaticVars.getConnectedAs()==3) {//if connected as Admin
	       }else if(StaticVars.getConnectedAs()==2) {//if connected as Premium
	    	   goToStat.setVisible(true);
				BookExperience.setVisible(true);
				Create.setVisible(true);

	       }else if(StaticVars.getConnectedAs()==1) {//if connected as Standard
	   		goToStat.setVisible(true);
			BookExperience.setVisible(true);
			Create.setVisible(false);

	       }else if(StaticVars.getConnectedAs()==0) {//if connected as Visitor
	    	   goToStat.setVisible(false);
				BookExperience.setVisible(false);
				Create.setVisible(false);
	       }
		anchoor1.setVisible(true);
		anchoor2.setVisible(true);
		anchoor3.setVisible(true);
		exp1.setVisible(false);
		exp2.setVisible(false);
		
            

		int i = tableView.getSelectionModel().getSelectedIndex();

		Experience o = new Experience();

		o = tableView.getItems().get(i);

		name.setText("" + o.getName());

		date.setText("" + o.getDateExperience());
		duration.setText(o.getDuration());
		location.setText(o.getLocation());
		 price.setText(String.valueOf("$" + o.getPrice()));

		starTime.setText(o.getStarTime());
		description.setText(o.getDescription());
		int available = tableView.getSelectionModel().getSelectedItem().getNbplaces();
		if(tableView.getSelectionModel().getSelectedItem().getUser().getFirstName().equals("Mehdi")){
			name1.setText("Proposed by : "+tableView.getSelectionModel().getSelectedItem().getUser().getFirstName());
			mehdi.setVisible(true);
			terry.setVisible(false);

		}
		else {
			name1.setText("Proposed by : "+tableView.getSelectionModel().getSelectedItem().getUser().getFirstName());
			terry.setVisible(true);
			mehdi.setVisible(false);

		}
		if(available<8){
			description1.setText("There are only a few places. Join "+available+" more for "+tableView.getSelectionModel().getSelectedItem().getUser().getFirstName()+"'s experience this Wednesday.");
			anchoor31.setVisible(true);

		}
		else {
			anchoor31.setVisible(false);

		}

		InitialContext ctx3;

		ctx3 = new InitialContext();

		ExperienceAvisServiceRemote proxy;
		proxy = (ExperienceAvisServiceRemote) ctx3.lookup(
				"esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceAvisService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceAvisServiceRemote");
		
           if(proxy.CheckExp(tableView.getSelectionModel().getSelectedItem().getId()).isEmpty())
           {
       		ratingbaa.setVisible(false);
       		nbAvis.setVisible(false);

           }
           else {
        	   ratingbaa.setVisible(true);
          		nbAvis.setVisible(true);

       		double moy = proxy.AvgRate(tableView.getSelectionModel().getSelectedItem().getId());
             ratingbaa.setRating(moy);
				nbAvis.setText(""+proxy.countNbAvis(tableView.getSelectionModel().getSelectedItem().getId()));

           }
           
           InitialContext ctx5; 
 		  try { ctx5 = new
 		  InitialContext();
 		  
 		  ExperienceLikeServiceRemote proxy4; 
 		 proxy4 = (ExperienceLikeServiceRemote) ctx5.lookup(
 		  "esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceLikeService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceLikeServiceRemote"); 
 		  if(proxy4.CheckExpAndUser(tableView.getSelectionModel().getSelectedItem().getId(),StaticVars.currentUser.getId()).isEmpty()){
 			  Like.setVisible(false);

 			  Dislike.setVisible(true);
 		  }else {
 			  Like.setVisible(true);

 			  Dislike.setVisible(false);
 		  }
 		  
 		   } catch
 		  (NamingException e) { 
 		  e.printStackTrace(); } 

	}

	@FXML
	void goToStats(ActionEvent event) {
		Switch("MyExperiencesBookings.fxml");
	}
	@FXML
	void Create(ActionEvent event) {
		Switch("Experience.fxml");
	}

	@FXML
	void sortByCategory(ActionEvent event) {

	}

	@FXML
	void sortByCommentsNumber(ActionEvent event) {

	}
	@FXML
    void Dislike(MouseEvent event) {
    	Dislike.setVisible(false);
    	Like.setVisible(true);
    	
    	InitialContext ctx; 
    	InitialContext ctx2; 

		  try { ctx = new
		  InitialContext();
		  ctx2 = new
				  InitialContext();
		  NotificationServiceRemote proxy2 = (NotificationServiceRemote) ctx2.lookup(
					"esprit1718b4tourism-ear/esprit1718b4tourism-service/NotificationService!tn.esprit.b4.esprit1718b4tourism.services.NotificationServiceRemote");
			
		  ExperienceLikeServiceRemote proxy; 
		  proxy = (ExperienceLikeServiceRemote) ctx.lookup(
		  "esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceLikeService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceLikeServiceRemote"); 
		 proxy.AddLikeExperience(StaticVars.currentUser.getId(), tableView.getSelectionModel().getSelectedItem().getId(),tableView.getSelectionModel().getSelectedItem().getName());
         String content2 = ""+StaticVars.getCurrentUser().getFirstName()+" "+StaticVars.getCurrentUser().getLastName()+" like your experience '"+tableView.getSelectionModel().getSelectedItem().getName()+"";

		 Notification n2 = new Notification(content2, false,tableView.getSelectionModel().getSelectedItem().getUser());
		 Date dd = new Date();
			n2.setSendDate(dd);

			proxy2.addNotification(n2);
		 
		 Notifications notificationBuilder = Notifications.create().title("Like")
					.text("Add Experience "+tableView.getSelectionModel().getSelectedItem().getName()+" to wish list !").graphic(null)
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
		  
		  ExperienceLike E = proxy.retExperiencefromLike(tableView.getSelectionModel().getSelectedItem().getId());
		  proxy.deleteLike(E);
		  
		  
		  Notifications notificationBuilder = Notifications.create().title("Dislike")
					.text("Delete Experience "+tableView.getSelectionModel().getSelectedItem().getName()+" from wish list !").graphic(null)
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// ratingbaa.setRating(Float.parseFloat("4.5"));
		// rate_bar.setRating(0.0f);
		anchoor1.setVisible(false);
		anchoor2.setVisible(false);
		anchoor3.setVisible(false);
		anchoor31.setVisible(false);

		BookExperience.setVisible(false);
		goToStat.setVisible(false);
		Create.setVisible(false);

		
		
	
		//notif
		InitialContext ctx1;

		try {
			ctx1 = new InitialContext();
			NotificationServiceRemote proxy = (NotificationServiceRemote) ctx1.lookup(
					"esprit1718b4tourism-ear/esprit1718b4tourism-service/NotificationService!tn.esprit.b4.esprit1718b4tourism.services.NotificationServiceRemote");
			if (proxy.getAllNotificationByUserBySeen(StaticVars.currentUser.getId()).isEmpty()) {
				image.setVisible(false);

			} else
				number.setText("" + proxy.countNotifNotSeen(StaticVars.currentUser.getId()));

		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		
		
		
		
		
		
		
		

		search.setStyle("-fx-text-inner-color: white;");
		drawer.blendModeProperty();
		drawer.setSidePane(box);

		ObservableList<String> items = FXCollections.observableArrayList("");
		forumBox.setItems(items);
		sortCategory.setItems(items);

		ObservableList<String> itemss = FXCollections.observableArrayList("");
		sortByCommentsNumb.setItems(itemss);

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

		InitialContext ctx;
		try {
			ctx = new InitialContext();
			ExperienceServiceRemote proxy;

			proxy = (ExperienceServiceRemote) ctx.lookup(
					"esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceServiceRemote");

			ColumnName.setCellValueFactory(new PropertyValueFactory<Experience, String>("name"));
			ColumnLocation.setCellValueFactory(new PropertyValueFactory<Experience, String>("location"));
			ObservableList<Experience> data = FXCollections.observableArrayList(proxy.showAllExperiences());
			tableView.setItems(data);
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// envoyer les donnees

		tableView.setRowFactory(new Callback<TableView<Experience>, TableRow<Experience>>() {
			@Override
			public TableRow<Experience> call(TableView<Experience> tableView) {
				final TableRow<Experience> row = new TableRow<>();
				final ContextMenu contextMenu = new ContextMenu();
				final MenuItem detailMenuItem = new MenuItem("Book This experience");

				detailMenuItem.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						nomRando = row.getItem().getName();
						local = row.getItem().getLocation();

						idEvent = row.getItem().getId();
						nb = row.getItem().getNbplaces();
						pricett = row.getItem().getPrice();
                           idUserExp = row.getItem().getUser().getId();
						Switch("ExperienceBookingValidation.fxml");
					}

				});

				contextMenu.getItems().add(detailMenuItem);
				row.contextMenuProperty()
						.bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));
				return row;
			}
		});

	}

	private void Switch(String fxml) {
		try {
			Stage stage = new Stage();
			Stage currentStage = (Stage) BookExperience.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource(fxml));

			Scene scene = new Scene(root);

			stage.setScene(scene);
			stage.setResizable(false);

			stage.show();
			currentStage.close();
		} catch (IOException ex) {
		}
	}

}
