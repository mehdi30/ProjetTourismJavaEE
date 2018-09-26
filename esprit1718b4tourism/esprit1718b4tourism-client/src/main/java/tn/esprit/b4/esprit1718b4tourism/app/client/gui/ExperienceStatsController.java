package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.Rating;

import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4tourism.entities.Experience;
import tn.esprit.b4.esprit1718b4tourism.entities.User;
import tn.esprit.b4.esprit1718b4tourism.services.ExperienceAvisServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.ExperienceLikeServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.ExperienceServiceRemote;

public class ExperienceStatsController extends AnchorPane implements Initializable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private ImageView imgv;

	@FXML
	private ImageView backToExperience;

	@FXML
	private BarChart<?, ?> barchart;

	@FXML
	private JFXComboBox<String> statBox;

	@FXML
	private BarChart<?, ?> barchartC;

	@FXML
	private Label avg;

	@FXML
	private TableView<Experience> UserSuccess;

	@FXML
	private TableColumn<Experience, String> name;

	@FXML
	private TableColumn<Experience, String> date;
	 @FXML
	    private Rating ratingbaa;

	    @FXML
	    private Label textt;

	    @FXML
	    private ImageView img1;

	    @FXML
	    private Text num;
	    
	    
	    @FXML
	    void get(MouseEvent event) throws NamingException {
	    	InitialContext ctx3;

			ctx3 = new InitialContext();

			ExperienceAvisServiceRemote proxy;
			proxy = (ExperienceAvisServiceRemote) ctx3.lookup(
					"esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceAvisService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceAvisServiceRemote");
			
	           if(proxy.CheckExp(UserSuccess.getSelectionModel().getSelectedItem().getId()).isEmpty())
	           {
	       		ratingbaa.setVisible(false);
	       		textt.setVisible(false);

	           }
	           else {
	        	   ratingbaa.setVisible(true);
	        	   textt.setVisible(true);

	       		double moy = proxy.AvgRate(UserSuccess.getSelectionModel().getSelectedItem().getId());
	             ratingbaa.setRating(moy);
	             textt.setText("Average Rating per "+proxy.countNbAvis(UserSuccess.getSelectionModel().getSelectedItem().getId())+" Travelers");

	           }
	           
	           InitialContext ctx5; 
	  		  try { ctx5 = new
	  		  InitialContext();
	  		  
	  		  ExperienceLikeServiceRemote proxy4; 
	  		 proxy4 = (ExperienceLikeServiceRemote) ctx5.lookup(
	  		  "esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceLikeService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceLikeServiceRemote"); 
	  		  if(proxy4.ReturnExpLikeByExp(UserSuccess.getSelectionModel().getSelectedItem().getId()).isEmpty()){
	  			  img1.setVisible(false);

	  			  num.setVisible(false);
	  		  }else {
	  			img1.setVisible(true);
	             num.setText(""+proxy4.countNbLike(UserSuccess.getSelectionModel().getSelectedItem().getId()));

	  			num.setVisible(true);
	  		  }
	  		  
	  		   } catch
	  		  (NamingException e) { 
	  		  e.printStackTrace(); } 


	    }
	@FXML
	void statBoxFunction(ActionEvent event) throws NamingException {
		if (statBox.getSelectionModel().getSelectedItem().equals("Number of Experience Booked and Not Booked")) {
			
			
			barchartC.setVisible(false);
			barchart.setVisible(true);
			InitialContext ctx;
			ctx = new InitialContext();
			ExperienceServiceRemote proxy;
			proxy = (ExperienceServiceRemote) ctx.lookup(
					"esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceServiceRemote");
			XYChart.Series series1 = new XYChart.Series();
			series1.setName("Booked");
			series1.getData().add(new XYChart.Data("0,100", proxy.sortBookedExperience().size()));

			XYChart.Series series2 = new XYChart.Series();
			series2.setName("Not Booked");
			series2.getData().add(new XYChart.Data("0,100", proxy.sortNotBookedExperience().size()));

			barchart.getData().addAll(series1, series2);
			barchart.setBackground(Background.EMPTY);
			barchart.setTitle("Statistics in relation to Experiences Booked and Not-Booked");
			barchart.setStyle("-fx-background-color: transparent;");

		}

	}
	private void Switch(String fxml) {
		try {
			Stage stage = new Stage();
			Stage currentStage = (Stage) backToExperience.getScene().getWindow();
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
		ratingbaa.setVisible(false);
   		textt.setVisible(false);
   		img1.setVisible(false);
   		num.setVisible(false);

		ObservableList<String> stats = FXCollections.observableArrayList("Number of Experience Booked and Not Booked",
				"Number of Not Booked");
		statBox.setItems(stats);
		
		InitialContext ctx;
		try {
			ctx = new InitialContext();
		
		ExperienceServiceRemote proxy;
		proxy = (ExperienceServiceRemote) ctx.lookup(
				"esprit1718b4tourism-ear/esprit1718b4tourism-service/ExperienceService!tn.esprit.b4.esprit1718b4tourism.services.ExperienceServiceRemote");
		
		
		List<Experience> users = new ArrayList<Experience>();
        users=proxy.showBestbookedExperiences();
        name.setCellValueFactory(new PropertyValueFactory<Experience, String>("name"));
		date.setCellValueFactory(new PropertyValueFactory<Experience, String>("nbpart"));

		ObservableList<Experience> data = FXCollections.observableArrayList(users);
		UserSuccess.setItems(data);
		
		
		
		
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		backToExperience.setOnMouseClicked(a -> {
			try {
				Switch("Experience.fxml");
			} catch (Exception e) {
			}
		});
	}
	

}
