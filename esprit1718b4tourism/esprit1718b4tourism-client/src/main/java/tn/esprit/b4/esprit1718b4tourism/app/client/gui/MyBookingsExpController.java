package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
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
import tn.esprit.b4.esprit1718b4tourism.entities.BookingExperiencePk;
import tn.esprit.b4.esprit1718b4tourism.entities.Experience;
import tn.esprit.b4.esprit1718b4tourism.entities.ExperienceAvis;
import tn.esprit.b4.esprit1718b4tourism.entities.Notification;
import tn.esprit.b4.esprit1718b4tourism.entities.User;
import tn.esprit.b4.esprit1718b4tourism.services.BookingExperienceServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.ExperienceAvisServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.ExperienceServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.NotificationServiceRemote;

public class MyBookingsExpController extends AnchorPane implements Serializable, Initializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// public static int ;
	public static String name;
	public static String durationn;
	public static String locationn;
	public static Date datee;
	public static String time;
	public static String descriptionn;
	@FXML
	private Rating Ratingbar;
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
	private TableView<BookingExperience> tableView;

	@FXML
	private TableColumn<BookingExperience, Integer> ColumnDuration;
	@FXML
	private TableColumn<BookingExperience, String> ColumnLocation;
	@FXML
	private TableColumn<BookingExperience, String> ColumnName;

	@FXML
	private JFXButton UpdateForumm;

	@FXML
	private JFXButton DeleteExperience;

	@FXML
	private JFXTextField searchForum;

	@FXML
	private Text namee;

	@FXML
	private JFXComboBox<?> sortCategory;

	@FXML
	private JFXComboBox<?> sortByCommentsNumb;

	@FXML
	private Label errorCom;

	@FXML
	private JFXHamburger hamburger;

	@FXML
	private JFXDrawer drawer;

	@FXML
	private Label forumOwner;

	@FXML
	private ImageView retour;
	@FXML
	private JFXTextField travelers;
	@FXML
	private AnchorPane anchor;

	@FXML
	void DeleteExperience(ActionEvent event) {

		try {

			if (!tableView.getSelectionModel().isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Can not Delete Table is Empty");
				alert.setHeaderText("Are Going to delete this Booking:  ");
				// +
				// tableView.getSelectionModel().getSelectedItem().getReservationeventPK());
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {


					Context context = new InitialContext();

					BookingExperienceServiceRemote serviceRemote = (BookingExperienceServiceRemote) context.lookup(
							"esprit1718b4tourism-ear/esprit1718b4tourism-service/BookingExperienceService!tn.esprit.b4.esprit1718b4tourism.services.BookingExperienceServiceRemote");
					
					serviceRemote.deleteBooking((tableView.getSelectionModel().getSelectedItem()));
					int idexp = tableView.getSelectionModel().getSelectedItem().getBookingExPk().getIdExp();
					int numdeplace = tableView.getSelectionModel().getSelectedItem().getNum();
					InitialContext ctx1;

					ctx1 = new InitialContext();
					ExperienceServiceRemote proxy1;

					proxy1 = (ExperienceServiceRemote) ctx1.lookup(
							"esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceServiceRemote");

					proxy1.UpdatePlacesD(numdeplace, idexp);
					proxy1.ParticipationD(idexp, numdeplace);

					ObservableList<BookingExperience> data1 = FXCollections
							.observableArrayList(serviceRemote.findExperiencesbyIdUser(StaticVars.currentUser.getId()));
					tableView.setItems(data1);
					
					Notifications notificationBuilder = Notifications.create().title("Booking canceled")
							.text("You have canceled your booking !").graphic(null)
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

			}
		} catch (Exception ex) {
			System.out.println("DATA can not be loaded " + ex.getMessage());

		}

	}

	@FXML
	void Search(KeyEvent event) {

	}

	@FXML
	void UpdateExperience(ActionEvent event) throws NamingException {
		boolean showAlerte = false;
		String des = "";
		String msg = "";
		InitialContext ctx;
		InitialContext ctx1;
		ctx = new InitialContext();
		ctx1 = new InitialContext();
		InitialContext ctx8;
		User user = new User();

		ctx8 = new InitialContext();

		BookingExperienceServiceRemote proxy;
		ExperienceServiceRemote proxy1;
		NotificationServiceRemote proxy8 = (NotificationServiceRemote) ctx8.lookup(
				"esprit1718b4tourism-ear/esprit1718b4tourism-service/NotificationService!tn.esprit.b4.esprit1718b4tourism.services.NotificationServiceRemote");
		
		proxy = (BookingExperienceServiceRemote) ctx.lookup(
				"esprit1718b4tourism-ear/esprit1718b4tourism-service/BookingExperienceService!tn.esprit.b4.esprit1718b4tourism.services.BookingExperienceServiceRemote");
		proxy1 = (ExperienceServiceRemote) ctx1.lookup(
				"esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceServiceRemote");
		Integer selected = tableView.getSelectionModel().getSelectedIndex();

		if (tableView.getSelectionModel().isSelected(selected)) {

			int newplaces = Integer.parseInt((travelers.getText()));
			int exp = tableView.getSelectionModel().getSelectedItem().getBookingExPk().getIdExp();
			int numdeplace = tableView.getSelectionModel().getSelectedItem().getNum();
			int nbplacestapes = newplaces - numdeplace;
			int actuelle = proxy1.retNbdePlaces(exp);
			int dispo = actuelle - nbplacestapes;
			String content = "The Number travelers of experience " + tableView.getSelectionModel().getSelectedItem().getName() + " booked by " + StaticVars.getCurrentUser().getFirstName() +" "+ StaticVars.getCurrentUser().getLastName() + "  has been Updated";

			if (!travelers.getText().equals("") || actuelle < nbplacestapes || actuelle == 0) {
				user.setId(tableView.getSelectionModel().getSelectedItem().getExperience().getUser().getId());
				Notification n = new Notification(content,false,user);
				Date dd = new Date();
				n.setSendDate(dd);
				proxy8.addNotification(n);
				
				
				showAlerte = true;
				des = "Your booking have been modified";
				BookingExperience ee = tableView.getItems().get(selected);
				ee.setNum(newplaces);

				proxy.UpdateBExperience(ee);
				proxy1.UpdatePlaces(dispo, exp);
				proxy1.Participation(exp, nbplacestapes);
				if (showAlerte) {
					showMessageDialoge(msg, des);
				}
				ObservableList<BookingExperience> data1 = FXCollections
						.observableArrayList(proxy.findExperiencesbyIdUser(StaticVars.currentUser.getId()));
				tableView.setItems(data1);
				
				Notifications notificationBuilder = Notifications.create().title("travelers updates")
						.text("You have updated the number of travelers !").graphic(null)
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
		} else {
			showAlerte = true;
			des = "Please check your Fields";

			if (showAlerte) {
				showMessageDialoge(msg, des);
			}
		}

	}

	@FXML
	void sortByCategory(ActionEvent event) {

	}

	@FXML
	void sortByCommentsNumber(ActionEvent event) {

	}
	
	@FXML
    void get(MouseEvent event) throws NamingException {
		anchor.setVisible(true);
		namee.setText(tableView.getSelectionModel().getSelectedItem().getExperience().getDescription());
		

    	InitialContext ctx3;

		ctx3 = new InitialContext();

		ExperienceAvisServiceRemote proxy;
		proxy = (ExperienceAvisServiceRemote) ctx3.lookup(
				"esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceAvisService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceAvisServiceRemote");
		
           if(proxy.CheckExp(tableView.getSelectionModel().getSelectedItem().getExperience().getId()).isEmpty())
           {
        	   Ratingbar.setVisible(true);

           }
           else {        	   Ratingbar.setVisible(true);

       		double moy = proxy.RetRate(tableView.getSelectionModel().getSelectedItem().getBookingExPk().getIdExp(),tableView.getSelectionModel().getSelectedItem().getBookingExPk().getIdUser());
       		Ratingbar.setRating(moy);

           }

    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
         anchor.setVisible(false);
		Ratingbar.ratingProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// ratingbaa.setRating(3.0f);
				// ratingbaa.setRating(Float.parseFloat("4.0")); String rate
				// =(price.getText());
				int idUser = tableView.getSelectionModel().getSelectedItem().getBookingExPk().getIdUser();
				int idExp = tableView.getSelectionModel().getSelectedItem().getBookingExPk().getIdExp();
				Experience Exp = tableView.getSelectionModel().getSelectedItem().getExperience();
				User user = tableView.getSelectionModel().getSelectedItem().getUser();

				InitialContext ctx;
				try {
					ctx = new InitialContext();

					ExperienceAvisServiceRemote proxy;
					proxy = (ExperienceAvisServiceRemote) ctx.lookup(
							"esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceAvisService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceAvisServiceRemote");
					if (proxy.CheckExpAndUser(idExp, idUser).isEmpty()) {
						proxy.AddBookingExperience(idUser, idExp, newValue.doubleValue(), "added");
						
						Notifications notificationBuilder = Notifications.create().title("Rating Added")
								.text("You have added a rate to this experience !").graphic(null)
								.hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
								.onAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										System.out.println("Clicked on Notifications");

									}
								});
						notificationBuilder.darkStyle();
						notificationBuilder.showWarning();
						
					} else {
						ExperienceAvis aa = new ExperienceAvis(newValue.doubleValue(), "updates", Exp, user);
						proxy.UpdateExperienceAvis(aa);
						
						

					}

				} catch (NamingException e) {
					e.printStackTrace();
				}

			}

		});

		retour.setOnMouseClicked(a -> {
			try {
				Switch("ExperienceBooking.fxml");
			} catch (Exception e) {
			}
		});
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			BookingExperienceServiceRemote proxy;

			proxy = (BookingExperienceServiceRemote) ctx.lookup(
					"esprit1718b4tourism-ear/esprit1718b4tourism-service/BookingExperienceService!tn.esprit.b4.esprit1718b4tourism.services.BookingExperienceServiceRemote");

			ObservableList<Experience> data = FXCollections.observableArrayList(proxy.findAllById(1));

			ColumnDuration.setCellValueFactory(new PropertyValueFactory<BookingExperience, Integer>("num"));// number
			ColumnName.setCellValueFactory(new PropertyValueFactory<BookingExperience, String>("name"));// number
			ColumnLocation.setCellValueFactory(new PropertyValueFactory<BookingExperience, String>("location"));// number

			ObservableList<BookingExperience> data1 = FXCollections
					.observableArrayList(proxy.findExperiencesbyIdUser(StaticVars.currentUser.getId()));
			tableView.setItems(data1);
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// envoyerlesdata

		tableView.setRowFactory(new Callback<TableView<BookingExperience>, TableRow<BookingExperience>>() {
			@Override
			public TableRow<BookingExperience> call(TableView<BookingExperience> tableView) {
				final TableRow<BookingExperience> row = new TableRow<>();
				final ContextMenu contextMenu = new ContextMenu();
				final MenuItem detailMenuItem = new MenuItem("Show Details");

				detailMenuItem.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						name = row.getItem().getExperience().getName();
						locationn = row.getItem().getExperience().getLocation();
						durationn = row.getItem().getExperience().getDuration();
						time = row.getItem().getExperience().getStarTime();
						descriptionn = row.getItem().getExperience().getDescription();
						datee = row.getItem().getExperience().getDateExperience();

						Switch("BookingsDetails.fxml");
					}

				});

				contextMenu.getItems().add(detailMenuItem);
				// Set context menu on row, but use a binding to make it only
				// show for non-empty rows:
				row.contextMenuProperty()
						.bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));
				return row;
			}
		});
	}

	private void Switch(String fxml) {
		try {
			Stage stage = new Stage();
			Stage currentStage = (Stage) imgv.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource(fxml));

			Scene scene = new Scene(root);

			stage.setScene(scene);
			stage.setResizable(false);

			stage.show();
			currentStage.close();
		} catch (IOException ex) {
		}
	}

	@FXML
	void actionName(ActionEvent event) {
		int i = tableView.getSelectionModel().getSelectedIndex();

		namee.setText(tableView.getItems().get(i).getExperience().getName());
	}

	private void showMessageDialoge(String msg, String description) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Alerte");
		alert.setHeaderText(msg);
		alert.setContentText(description);

		alert.showAndWait();
	}
}
