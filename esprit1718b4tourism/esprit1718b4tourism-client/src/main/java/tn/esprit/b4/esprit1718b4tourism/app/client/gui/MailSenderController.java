package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tn.esprit.b4.esprit1718b4tourism.app.client.gui.StaticVars;
import tn.esprit.b4.esprit1718b4tourism.app.client.other.EMailSender;
import tn.esprit.b4.esprit1718b4tourism.entities.Role;

public class MailSenderController implements Initializable {

    @FXML
    private TextArea body;
    @FXML
    private TextField subject;
    @FXML
    private Label contact;
    @FXML
    private AnchorPane back;
    @FXML
    private Button mail;
    @FXML
    private Button facebook;
    @FXML
    private ImageView imgv;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (StaticVars.currentUser.getRole() == Role.Admin) {
            mail.setVisible(false);
            contact.setText("Publish a facebook Status");
        } else {
            facebook.setVisible(false);
            contact.setText("send mail to Orient Tourism Admin");
        }
    }


    @FXML
    private void mail_action(ActionEvent event) {
    	
    	 String msg;
         if ((msg = StaticVars.VerifierLongChaine(subject.getText(), "<< Subject >>", 3, 20)) != "valide") {
             StaticVars.alerte(msg);
         }else  if ((msg = StaticVars.VerifierLongChaine(body.getText(), "<< Body >>", 10, 200)) != "valide") {
             StaticVars.alerte(msg);
         }else {
    
                 String[] to = {"tourism.orientt@gmail.com"};
                 EMailSender.sendMail("tourism.orientt@gmail.com", "123azert", body.getText(), to, "from"+ StaticVars.currentUser.getEmail() +" "+subject.getText());
                 StaticVars.alerte("Your email was successfully sent, check your mail ! you will recieve a reply in 24 hours");

             
             } 
 
 
    }

    @FXML
    private void facebook_action(ActionEvent event) {
    	 String msg;
         if ((msg = StaticVars.VerifierLongChaine(subject.getText(), "<< Subject >>", 3, 20)) != "valide") {
             StaticVars.alerte(msg);
         }else  if ((msg = StaticVars.VerifierLongChaine(body.getText(), "<< Body >>", 10, 200)) != "valide") {
             StaticVars.alerte(msg);
         }else {
    	String accessToken ="EAACEdEose0cBAGxyBB544Ko37630zJbX85kKbTNZASuBGwbl3NX2XdW0o2Fo5YjVqX4Wp4nPXu6ZCJdaGVDBJfLBgfKMo39fdYm4iUdvioFzSDwzevnfDeTqQITgR1ZCExhuwoyi9GKbFblmROQ8O7BrT9pPVg9ZBhiGkejSEZB6WJncIWF5G7AxWSLrGG68ZD";
 		FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
 		 FacebookType response = facebookClient.publish("me/feed", FacebookType.class, Parameter.with("message", subject.getText() + " : " +body.getText())); 
	      StaticVars.alerte("you successfully publish a new status to Orient account. link to status : fb.com/"+response.getId());
 		 System.out.println("fb.com/"+response.getId());
         }
    }

    @FXML
    private void back_action(MouseEvent event) throws IOException {
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

}
