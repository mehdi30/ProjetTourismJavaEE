package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import org.controlsfx.control.Notifications;

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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.b4.esprit1718b4tourism.entities.BookingExperience;
import tn.esprit.b4.esprit1718b4tourism.entities.Experience;
import tn.esprit.b4.esprit1718b4tourism.entities.Notification;
import tn.esprit.b4.esprit1718b4tourism.entities.User;
import tn.esprit.b4.esprit1718b4tourism.services.BookingExperienceServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.ExperienceServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.NotificationServiceRemote;

public class ExperienceController extends AnchorPane implements Initializable {

	@FXML
	private VBox box;

	@FXML
	private JFXButton event;

	@FXML
	private JFXButton message;

	@FXML
	private JFXButton notifBtn;

	@FXML
	private ImageView image;

	@FXML
	private Label number;

	@FXML
	private JFXButton logOut;

	@FXML
	private JFXButton jobOffer;

	@FXML
	private ImageView imgv;
	@FXML
	private ImageView name11;
	@FXML
	private JFXTextField searchForum;
	@FXML
	private JFXTextField available;
	@FXML
	private JFXButton DeleteExperience;

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
	private JFXButton AddExperience;

	@FXML
	private JFXDatePicker dateExperience;

	@FXML
	private JFXTextField name;

	@FXML
	private JFXTextField location;

	@FXML
	private JFXTextField price;

	@FXML
	private JFXTextField duration;

	@FXML
	private JFXTextField starTime;

	@FXML
	private JFXTextArea description;

	@FXML
	private TableView<Experience> tableView;

	@FXML
	private TableColumn<Experience, String> ColumnName;

	@FXML
	private TableColumn<Experience, String> ColumnLocation;

	@FXML
	private TableColumn<Experience, Integer> ColumnPrice;
	@FXML
	private TableColumn<Experience, Integer> availablee;
	@FXML
	private TableColumn<Experience, Integer> parts;


	@FXML
	private TableColumn<Experience, Date> ColumnDate;

	@FXML
	private TableColumn<Experience, String> ColumnDuration;

	@FXML
	private TableColumn<Experience, String> ColumnTime;

	@FXML
	private TableColumn<Experience, String> ColumnDescription;

	@FXML
	private JFXHamburger hamburger;

	@FXML
	private JFXDrawer drawer;

	@FXML
	private Label forumOwner;
	@FXML
    private Text name1;

	@FXML
	void DeleteExperience(ActionEvent event) throws NamingException {
		Context context1 = new InitialContext();
		BookingExperienceServiceRemote serviceRemote1 = (BookingExperienceServiceRemote) context1.lookup(
				"esprit1718b4tourism-ear/esprit1718b4tourism-service/BookingExperienceService!tn.esprit.b4.esprit1718b4tourism.services.BookingExperienceServiceRemote");
		List<BookingExperience> all = new ArrayList<BookingExperience>();

		all = serviceRemote1.CheckExp(tableView.getSelectionModel().getSelectedItem().getId());

		try {

			if (!tableView.getSelectionModel().isEmpty() && all.isEmpty()) {
				Context context = new InitialContext();
				ExperienceServiceRemote serviceRemote = (ExperienceServiceRemote) context.lookup(
						"esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceServiceRemote");
				serviceRemote.DeleteById(tableView.getSelectionModel().getSelectedItem().getId());
				ObservableList<Experience> data = FXCollections.observableArrayList(serviceRemote.showAllExperiencesbyUser(StaticVars.currentUser.getId()));
				tableView.setItems(data);
				
				Notifications notificationBuilder = Notifications.create().title("Delete Complete")
						.text("Deleted from Your Experience List !").graphic(null).hideAfter(Duration.seconds(5))
						.position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								System.out.println("Clicked on Notifications");

							}
						});
				notificationBuilder.darkStyle();
				notificationBuilder.showError();

			} else {
				JOptionPane.showMessageDialog(null, "Cannot delete this experience !");

			}
		} catch (Exception ex) {
			System.out.println("erreur lors du chargement " + ex.getMessage());

		}

	}

	@FXML
	void Search(KeyEvent event) throws NamingException {
		InitialContext ctx;
		ctx = new InitialContext();
		ExperienceServiceRemote proxy;
		proxy = (ExperienceServiceRemote) ctx.lookup(
				"esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceServiceRemote");
		ObservableList<Experience> data = FXCollections
				.observableList(proxy.advancedSearchExperience(searchForum.getText()));
		tableView.setItems(data);

	}

	@SuppressWarnings("deprecation")
	@FXML
	void UpdateExperience(ActionEvent event) throws NamingException {
		Integer selected = tableView.getSelectionModel().getSelectedIndex();
		InitialContext ctx;
		ctx = new InitialContext();
		ExperienceServiceRemote proxy;
		proxy = (ExperienceServiceRemote) ctx.lookup(
				"esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceServiceRemote");

		InitialContext ctx2;

		ctx2 = new InitialContext();
		InitialContext ctx8;

		ctx8 = new InitialContext();
		BookingExperienceServiceRemote proxy8;

		NotificationServiceRemote proxy2 = (NotificationServiceRemote) ctx2.lookup(
				"esprit1718b4tourism-ear/esprit1718b4tourism-service/NotificationService!tn.esprit.b4.esprit1718b4tourism.services.NotificationServiceRemote");
		proxy8 = (BookingExperienceServiceRemote) ctx8.lookup(
				"esprit1718b4tourism-ear/esprit1718b4tourism-service/BookingExperienceService!tn.esprit.b4.esprit1718b4tourism.services.BookingExperienceServiceRemote");
		
		if (tableView.getSelectionModel().isSelected(selected)) {

			Experience ee = tableView.getItems().get(selected);
			ee.setName(name.getText());
			ee.setLocation(location.getText());
			ee.setDuration(duration.getText());
			ee.setPrice(Integer.parseInt(price.getText()));
			ee.setStarTime(starTime.getText());
			ee.setDescription(description.getText());
			ee.setNbplaces(Integer.parseInt(available.getText()));


			Date d;
			if (dateExperience.getValue() == null) {
				d = new Date(0);
			} else {
				d = new Date(dateExperience.getValue().getYear() - 1900, dateExperience.getValue().getMonthValue() - 1,
						dateExperience.getValue().getDayOfMonth());
			}

			ee.setDateExperience(d);
			proxy.UpdateExperience(ee);
			
			String content = "The experience " + name.getText() + "  has been modified";

			for (BookingExperience notification : proxy8.CheckExp(tableView.getSelectionModel().getSelectedItem().getId())) {
				
					User user = notification.getUser();
					Notification n = new Notification(content, false, user);
					Date dd = new Date();
					n.setSendDate(dd);
					proxy2.addNotification(n);
				
			}
			
			
			
			
			
			
			
			

			Notifications notificationBuilder = Notifications.create().title("Update Complete")
					.text("Your Experience informations has been modified !").graphic(null)
					.hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
					.onAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							System.out.println("Clicked on Notifications");

						}
					});
			notificationBuilder.darkStyle();
			notificationBuilder.showWarning();

		}
		ObservableList<Experience> data = FXCollections.observableArrayList(proxy.showAllExperiencesbyUser(StaticVars.currentUser.getId()));
		tableView.setItems(data);

		
		
		InitialContext ctx1;

		try {
			ctx1 = new InitialContext();
			NotificationServiceRemote proxy20 = (NotificationServiceRemote) ctx1.lookup(
					"esprit1718b4tourism-ear/esprit1718b4tourism-service/NotificationService!tn.esprit.b4.esprit1718b4tourism.services.NotificationServiceRemote");
			if (proxy20.getAllNotificationByUserBySeen(StaticVars.currentUser.getId()).isEmpty()) {
				image.setVisible(false);

			} else
				number.setText("" + proxy20.countNotifNotSeen(StaticVars.currentUser.getId()));

		} catch (NamingException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	void goToStats(ActionEvent event) {
		Switch("ExperienceStats.fxml");

	}
	 
	@FXML
	void sortByCategory(ActionEvent event) {

	}

	@FXML
	void sortByCommentsNumber(ActionEvent event) {

	}

	@SuppressWarnings("deprecation")
	@FXML
	void AddExperience(ActionEvent event) throws NamingException {
		if (JOptionPane.showConfirmDialog(null, "Confirm creation of New Experience", "Add",
				JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

			InitialContext ctx;
			ctx = new InitialContext();
			ExperienceServiceRemote proxy;
			proxy = (ExperienceServiceRemote) ctx.lookup(
					"esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceServiceRemote");

			if (!name.getText().equals("") && !description.getText().equals("") && !location.getText().equals("")
					&& !duration.getText().equals("") && !starTime.getText().equals("")
					&& !price.getText().equals("")) {

				Experience e1 = new Experience(name.getText(), location.getText(), description.getText(),
						duration.getText(), Integer.parseInt(price.getText()), starTime.getText(), Integer.parseInt(available.getText()),StaticVars.getCurrentUser());
				Date d;
				if (dateExperience.getValue() == null) {
					d = new Date(0);
				} else {
					d = new Date(dateExperience.getValue().getYear() - 1900,
							dateExperience.getValue().getMonthValue() - 1, dateExperience.getValue().getDayOfMonth());
				}
				e1.setDateExperience(d);
				proxy.AddExperience(e1);
				name.clear();
				description.clear();
				duration.clear();
				location.clear();
				price.clear();
				starTime.clear();
				available.clear();

				location.clear();
				ObservableList<Experience> data = FXCollections.observableArrayList(proxy.showAllExperiencesbyUser(StaticVars.currentUser.getId()));
				tableView.setItems(data);
				Notifications notificationBuilder = Notifications.create().title("Creation Complete")
						.text("Create New Experience ! Good Luck :)").graphic(null).hideAfter(Duration.seconds(5))
						.position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								System.out.println("Clicked on Notifications");

							}
						});
				notificationBuilder.darkStyle();
				notificationBuilder.showConfirm();
			} else {
				JOptionPane.showMessageDialog(null, "Please Complete All Fields");

			}
		}
	}
	@FXML
    void experienceb(ActionEvent event) {
		Switch("ExperienceBooking.fxml");

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

	private void Switch(String fxml) {
		try {
			Stage stage = new Stage();
			Stage currentStage = (Stage) goToStat.getScene().getWindow();
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
		searchForum.setStyle("-fx-text-inner-color: white;");
		drawer.blendModeProperty();
		drawer.setSidePane(box);

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

		ObservableList<String> items = FXCollections.observableArrayList("");
		forumBox.setItems(items);
		sortCategory.setItems(items);

		ObservableList<String> itemss = FXCollections.observableArrayList("Having Booking number");
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
			ColumnPrice.setCellValueFactory(new PropertyValueFactory<Experience, Integer>("price"));
			parts.setCellValueFactory(new PropertyValueFactory<Experience, Integer>("nbpart"));
			availablee.setCellValueFactory(new PropertyValueFactory<Experience, Integer>("nbplaces"));

			ColumnDate.setCellValueFactory(new PropertyValueFactory<Experience, Date>("dateExperience"));
			ColumnDuration.setCellValueFactory(new PropertyValueFactory<Experience, String>("duration"));
			ColumnTime.setCellValueFactory(new PropertyValueFactory<Experience, String>("starTime"));
			ColumnDescription.setCellValueFactory(new PropertyValueFactory<Experience, String>("description"));
			ObservableList<Experience> data = FXCollections.observableArrayList(proxy.showAllExperiencesbyUser(StaticVars.currentUser.getId()));
			tableView.setItems(data);
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        name1.setText(StaticVars.getCurrentUser().getFirstName());		

	}

}
