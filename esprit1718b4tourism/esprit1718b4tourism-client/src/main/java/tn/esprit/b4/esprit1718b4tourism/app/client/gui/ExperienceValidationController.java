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

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

public class ExperienceValidationController implements Initializable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private ImageView imgv;

	@FXML
	private ImageView retour;

	@FXML
	private JFXTextField iduser;

	@FXML
	private JFXButton bookvalidaion;

	@FXML
	private JFXTextField num;

	@FXML
	private Text name;
	@FXML
	private Text idexperience;

	@FXML
	private Text totalprice;
	
	@FXML
    private ProgressIndicator progressIndi;
	@FXML
    private ProgressIndicator progressIndi1;
	@FXML
	private Text totalprice2;
	@FXML
    private FontAwesomeIconView creditCard;
	@FXML
	private AnchorPane anchor1;
	@FXML
	private AnchorPane anchor0;
	@FXML
	void bookvalidaion(ActionEvent event) throws NamingException {
		
		InitialContext ctx;
		InitialContext ctx1;
		InitialContext ctx2;

		ctx = new InitialContext();
		ctx1 = new InitialContext();

		ctx2 = new InitialContext();
		User user = new User();
		BookingExperienceServiceRemote proxy;
		ExperienceServiceRemote proxy1;
		proxy = (BookingExperienceServiceRemote) ctx.lookup(
				"esprit1718b4tourism-ear/esprit1718b4tourism-service/BookingExperienceService!tn.esprit.b4.esprit1718b4tourism.services.BookingExperienceServiceRemote");
		proxy1 = (ExperienceServiceRemote) ctx1.lookup(
				"esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceServiceRemote");
		NotificationServiceRemote proxy2 = (NotificationServiceRemote) ctx2.lookup(
				"esprit1718b4tourism-ear/esprit1718b4tourism-service/NotificationService!tn.esprit.b4.esprit1718b4tourism.services.NotificationServiceRemote");
		
		// int idexp = Integer.parseInt((idexperience.getText()));
		// int numm = Integer.parseInt((num.getText()));
		int nbplacestapes = Integer.parseInt((num.getText()));
		int nbactuelle = ExperienceBController.nb;
		int dispo = nbactuelle - nbplacestapes;

		System.out.println(ExperienceBController.idEvent);
		boolean showAlerte = false;
		String des = "";
		String msg = "";
		progressIndi.setProgress(0.20f);

		if (num.getText().trim().length() == 0 || nbactuelle == 0 || nbplacestapes > nbactuelle || nbplacestapes == 0) {
			progressIndi.setProgress(-1.0f);

			showAlerte = true;
			des = "You must Verify your Booking Operation";

			if (showAlerte) {
				showMessageDialoge(msg, des);
			}
			// JOptionPane.showMessageDialog(null,"Please Complete All Fields");

		} else {
			progressIndi.setProgress(0.75f);

			proxy.AddBookingExperience(StaticVars.currentUser.getId(),ExperienceBController.idEvent, nbplacestapes,ExperienceBController.nomRando,ExperienceBController.local);
			proxy1.UpdatePlaces(dispo, ExperienceBController.idEvent);
			proxy1.Participation(ExperienceBController.idEvent, nbplacestapes);
			showAlerte = true;
			des = "You have booked this Experience";
			String mail="ch8bane.mehdi@gmail.com";
		       String pass="mehdimehdi30";
		       String[] to={"chaabaanemehdi08@gmail.com"};
		       String subject="Booking Confirmation";
		       String body="we're sending  you this mail to inform you that you have booked the experience" + ExperienceBController.nomRando + "";
		       SendMailTLS sendMail = new SendMailTLS();
		       sendMail.sendFromGMail(mail,pass,to,subject,body);
		        System.out.println("done");
				progressIndi.setProgress(1.0f);
				bookvalidaion.setVisible(false);
				creditCard.setVisible(false);
				String content = "The experience " + name.getText() + " by " + StaticVars.getCurrentUser().getFirstName() +" "+ StaticVars.getCurrentUser().getLastName() + "  has been Booked";
                String content2 = "Your booking for the experience "+ExperienceBController.nomRando+"has been confimed";
				
				user.setId(ExperienceBController.idUserExp);
				Notification n = new Notification(content, false,user);
				Date dd = new Date();
				n.setSendDate(dd);
				proxy2.addNotification(n);

				Notification n2 = new Notification(content2, false,StaticVars.getCurrentUser());
				n2.setSendDate(dd);

				proxy2.addNotification(n2);

				Notifications notificationBuilder = Notifications.create().title("Booking Confirmed")
						.text("Congratulations ! You have booked "+ExperienceBController.nomRando+" !").graphic(null)
						.hideAfter(Duration.seconds(7)).position(Pos.BOTTOM_RIGHT)
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

	public void setItems(String id_experience, String name, String price) {
		this.idexperience.setText(id_experience);
		this.name.setText(name);
		this.totalprice.setText(price);

	}

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
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

	@FXML
	void calcil(KeyEvent event) {
		int nbplacestapes = Integer.parseInt((num.getText()));
		int ttprice = nbplacestapes * ExperienceBController.pricett;

		if (num.getText().length() == 0 || num.getText().equals("")) {

			totalprice.setText("$0");

		} else if (!num.getText().equals("")) {

			totalprice.setText("$" + String.valueOf(ttprice));
		} else {
			totalprice.setText("$0");

		}

	}

	private void showMessageDialoge(String msg, String description) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Alerte");
		alert.setHeaderText(msg);
		alert.setContentText(description);

		alert.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(StaticVars.currentUser.getId()== ExperienceBController.idUserExp){
			anchor1.setVisible(true);
			anchor0.setVisible(false);
			totalprice2.setText("You can not book your own experiences");
			progressIndi1.setProgress(-1.0f);

		}
		InitialContext ctx;
		
		try {
		ctx = new InitialContext();
		BookingExperienceServiceRemote proxy;
		proxy = (BookingExperienceServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/BookingExperienceService!tn.esprit.b4.esprit1718b4tourism.services.BookingExperienceServiceRemote");
		
		
		if(proxy.CheckExpAndUser(ExperienceBController.idEvent,StaticVars.currentUser.getId()).isEmpty()){
			progressIndi.setProgress(-1.0f);

		}else {
			anchor1.setVisible(true);
			anchor0.setVisible(false);
			totalprice2.setText("You have booked "+ExperienceBController.nomRando+" Please check your bookings list ! ");
			progressIndi1.setProgress(1.0f);


		}
		}catch (NamingException e) {
			e.printStackTrace();
		}

		retour.setOnMouseClicked(a -> {
			try {
				Switch("ExperienceBooking.fxml");
			} catch (Exception e) {
			}
		});
		int nbplacestapes = Integer.parseInt((num.getText()));
		int ttprice = nbplacestapes * ExperienceBController.pricett;
		if (num.getText().trim().length() == 0 || num.getText().equals("")) {

			totalprice.setText("$0");

		} else if (!num.getText().equals("")) {

			totalprice.setText("$" + String.valueOf(ttprice));
		} else {
			totalprice.setText("$0");

		}
	}

}
