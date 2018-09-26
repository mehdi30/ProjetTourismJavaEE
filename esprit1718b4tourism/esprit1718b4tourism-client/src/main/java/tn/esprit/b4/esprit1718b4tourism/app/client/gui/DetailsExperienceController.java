package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DetailsExperienceController extends AnchorPane implements Initializable, Serializable {

	

	

	@FXML
	private ImageView imgv;

	@FXML
	private Text name;

	@FXML
	private Text locationn;

	@FXML
	private Text duration;

	@FXML
	private Text date;

	@FXML
	private Text starTime;

	@FXML
	private Text price;

	@FXML
	private Label errorCom;

	@FXML
	private Text description;

	@FXML
	private JFXHamburger hamburger;


	

	@FXML
	private ImageView retour;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		name.setText(MyBookingsExpController.name);
		locationn.setText(MyBookingsExpController.locationn);
		duration.setText(MyBookingsExpController.durationn);
		starTime.setText(MyBookingsExpController.time);
		description.setText(MyBookingsExpController.descriptionn);
		date.setText(String.valueOf(MyBookingsExpController.datee));
		retour.setOnMouseClicked(a -> {
			try {
				Switch("MyExperiencesBookings.fxml");
			} catch (Exception e) {
			}
		});

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
	
}
