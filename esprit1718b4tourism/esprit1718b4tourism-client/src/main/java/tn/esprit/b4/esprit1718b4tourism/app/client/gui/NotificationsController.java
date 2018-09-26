package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4tourism.entities.Notification;
import tn.esprit.b4.esprit1718b4tourism.services.NotificationServiceRemote;

public class NotificationsController extends AnchorPane implements Initializable, Serializable  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
    private JFXDrawer drawer;

    @FXML
    private VBox vbox;

    @FXML
    private JFXButton events;

    @FXML
    private JFXButton forums;

    @FXML
    private JFXButton jobOffers;

    @FXML
    private TableView<Notification> notificationsView;

    @FXML
    private TableColumn<Notification, String> colContent;

    @FXML
    private TableColumn<Notification, Date> colDate;

    @FXML
    private JFXHamburger hamburger;
    
	private ObservableList<Notification> data;

    @FXML
    void Messages(ActionEvent event) {

    }

    @FXML
    void clearNotifications(ActionEvent event) throws NamingException {
    	Integer selected = notificationsView.getSelectionModel().getSelectedIndex();

		InitialContext ctx;

    	ctx = new InitialContext();

		NotificationServiceRemote proxy = (NotificationServiceRemote) ctx.lookup(
				"esprit1718b4tourism-ear/esprit1718b4tourism-service/NotificationService!tn.esprit.b4.esprit1718b4tourism.services.NotificationServiceRemote");

          proxy.deleteAllNotificationAll(StaticVars.currentUser.getId());
          
          data = FXCollections.observableArrayList(proxy.getAllNotificationByUser(StaticVars.currentUser.getId()));
			notificationsView.setItems(data);

		
    }

    @FXML
    void event(ActionEvent event) {

    }

    @FXML
    void forum(ActionEvent event) {
		Switch("ExperienceBooking.fxml");

    }

    @FXML
    void jobOffer(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		drawer.setSidePane();
		
		drawer.setVisible(true);
		drawer.setSidePane(vbox);
		HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
		transition.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
			transition.setRate(transition.getRate() * -1);
			transition.play();
			if (drawer.isShown())
				drawer.close();
			else
				drawer.open();
		});	
		
		
		
		
		InitialContext ctx;

		try {
			ctx = new InitialContext();

			NotificationServiceRemote proxy = (NotificationServiceRemote) ctx.lookup(
					"esprit1718b4tourism-ear/esprit1718b4tourism-service/NotificationService!tn.esprit.b4.esprit1718b4tourism.services.NotificationServiceRemote");

			colContent.setCellValueFactory(new PropertyValueFactory<Notification, String>("content"));
			colDate.setCellValueFactory(new PropertyValueFactory<Notification, Date>("sendDate"));
			//System.out.println(1);
     
			data = FXCollections.observableArrayList(proxy.getAllNotificationByUser(StaticVars.currentUser.getId()));
			notificationsView.setItems(data);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private void Switch(String fxml) {
		try {
			Stage stage = new Stage();
			Stage currentStage = (Stage) forums.getScene().getWindow();
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
