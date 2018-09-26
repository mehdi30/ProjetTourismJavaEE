package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import com.jfoenix.controls.JFXCheckBox;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.NamingException;

import javafx.application.Platform;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tn.esprit.b4.esprit1718b4tourism.app.client.businessDelegate.UserServiceDelegate;
import tn.esprit.b4.esprit1718b4tourism.app.client.other.MD5;
import tn.esprit.b4.esprit1718b4tourism.entities.User;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class ManageAccountController implements Initializable {
 
	
	 @FXML
	    private Label pathLabel;
    @FXML
    private ImageView my_image;
    @FXML
    private ImageView edit_image;
    @FXML
    private Button select_image;
    @FXML
    private Label first_name;
    @FXML
    private Label last_name;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    @FXML
    private Button save_button;
    @FXML
    private Button cancel_button;
    @FXML
    private Button delete_button;
    @FXML
    private Button edit_button;
    @FXML
    private Label birthday;
    @FXML
    private CheckBox orient;
    @FXML
    private Pane Pane3;
    @FXML
    private TextField first_name_edit;
    @FXML
    private TextField last_name_edit;

    @FXML
    private TextField phone_edit;
    @FXML
    private DatePicker birthday_edit;
    @FXML
    private PasswordField password_edit1;
    @FXML
    private PasswordField password_edit2;
    @FXML
    private AnchorPane back;
    @FXML
    private ImageView imgv;
    @FXML
    private JFXCheckBox premium;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Pane3.setVisible(false);

        first_name.setText(StaticVars.currentUser.getFirstName());
        last_name.setText(StaticVars.currentUser.getLastName());

        birthday.setText(StaticVars.currentUser.getBirthdayDate().toString());
        email.setText(StaticVars.currentUser.getEmail());
        phone.setText(StaticVars.currentUser.getPhone());
        orient.setSelected(StaticVars.currentUser.getOrienMember());
       
        try {
        if(StaticVars.currentUser.getIm()!=null) {
        	BufferedImage bi = ImageIO.read(new ByteArrayInputStream(StaticVars.currentUser.getIm()));
        	Image image = SwingFXUtils.toFXImage(bi, null);
        	my_image.setImage(image);
        	}
		} catch (Exception e) {
			System.out.println("error with my image");
		}
    
        pathLabel.setVisible(false);
        pathLabel.setText("null");
    }



    @FXML
    private void save_button_action(ActionEvent event) throws NamingException, ParseException, IOException {

          LocalDate localDate = birthday_edit.getValue();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String birthday_edit_Str = dtf.format(localDate);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthdayDate = dateFormat.parse(birthday_edit_Str);
        Date lastLogin = dateFormat.parse(StaticVars.dateSysteme());

        String msg;
        if ((msg = StaticVars.VerifierLongChaine(first_name_edit.getText(), "<< first name >>", 3, 20)) != "valide") {
            StaticVars.alerte(msg);
        } else if ((msg = StaticVars.VerifierLongChaine(last_name_edit.getText(), "<< last name >>", 3, 20)) != "valide") {
            StaticVars.alerte(msg);
        } else if (birthdayDate.compareTo(lastLogin) > 0) {
            StaticVars.alerte("inserer une date valide");
        } else if ((msg = StaticVars.VerifierLongChaine(password_edit1.getText(), "<< password >>", 8, 20)) != "valide") {
            StaticVars.alerte(msg);
        } else if (!password_edit1.getText().equals(password_edit2.getText())) {
            StaticVars.alerte("retape password correctly");
        } else if ((msg = StaticVars.VerifierLongChaine(phone_edit.getText(), "<< phone number >>", 3, 20)) != "valide") {
            StaticVars.alerte(msg);
        } else {

            String md5password = MD5.crypt(password_edit1.getText());

            User u = new User();
            u = StaticVars.getCurrentUser();
            u.setFirstName(first_name_edit.getText());
            u.setLastName(last_name_edit.getText());
            u.setBirthdayDate(birthdayDate);
            u.setPassword(md5password);
            u.setPhone(phone_edit.getText());
            
            //////image///////////////////
            if(pathLabel.getText().equals("null")) u.setIm(null);
            else {
            	File file = new File(pathLabel.getText());
            	
            	byte[] picInBytes = new byte[(int) file.length()];
            	FileInputStream fileInputStream = new FileInputStream(file);
            	fileInputStream.read(picInBytes);
            	fileInputStream.close();
            	u.setIm(picInBytes); 
            }
            //////////////////////////////

            UserServiceDelegate.updateUser(u);

            StaticVars.alerte("editing account successfull");
            
            pathLabel.setText("null");
            Pane3.setVisible(false);

            StaticVars.setCurrentUser(u);

            first_name.setText(StaticVars.currentUser.getFirstName());
            last_name.setText(StaticVars.currentUser.getLastName());

            birthday.setText(StaticVars.currentUser.getBirthdayDate().toString());
            email.setText(StaticVars.currentUser.getEmail());
            phone.setText(StaticVars.currentUser.getPhone());
            orient.setSelected(StaticVars.currentUser.getOrienMember());
            
            try {
                if(StaticVars.currentUser.getIm()!=null) {
                	BufferedImage bi = ImageIO.read(new ByteArrayInputStream(StaticVars.currentUser.getIm()));
                	Image image = SwingFXUtils.toFXImage(bi, null);
                	my_image.setImage(image);
                	}
        		} catch (Exception e) {
        			System.out.println("error with my image");
        		}

         //   premium_button.setDisable(StaticVars.currentUser.getPremium());
        }

    }

    @FXML
    private void cancel_button_action(ActionEvent event) {
        Pane3.setVisible(false);

    }

    @FXML
    private void delete_button_action(ActionEvent event) throws NamingException, IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete your account");
        alert.setHeaderText("Be careful !!!!");
        alert.setContentText("That can't be undone, Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

   
        	UserServiceDelegate.removeById(StaticVars.currentUser.getId());

            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
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

        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    private void edit_button_action(ActionEvent event) {
    	
    	 try {
    	        if(StaticVars.currentUser.getIm()!=null) {
    	        	BufferedImage bi = ImageIO.read(new ByteArrayInputStream(StaticVars.currentUser.getIm()));
    	        	Image image = SwingFXUtils.toFXImage(bi, null);
    	        	edit_image.setImage(image);
    	        	}
    			} catch (Exception e) {
    				System.out.println("error with my image");
    			}
    	 
        Pane3.setVisible(true);

        first_name_edit.setText(StaticVars.currentUser.getFirstName());
        last_name_edit.setText(StaticVars.currentUser.getLastName());
        birthday_edit.setValue(Instant.ofEpochMilli(StaticVars.currentUser.getBirthdayDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
        //   email_edit.setText(StaticVars.currentUser.getEmail());
        phone_edit.setText(StaticVars.currentUser.getPhone());
        orient.setSelected(StaticVars.currentUser.getOrienMember());
     //   premium_button.setDisable(StaticVars.currentUser.getPremium());
    }

    private void corriger_champ_phone(KeyEvent event) {
        try {
            int opt = Integer.parseInt(phone_edit.getText());
            if (opt < 0) {
                phone_edit.setText("");
            }
        } catch (NumberFormatException e) {
            phone_edit.setText("");
        }
    }

    @FXML
    private void back_click(MouseEvent event) throws IOException {
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
    

    private final FileChooser fileChooser = new FileChooser();
  	 private String pathImage = "", fileName = "";
    @FXML
    private void select_image_action(ActionEvent event) {
   	 Stage stage = new Stage();
   	 Stage currentStage = (Stage) select_image.getScene().getWindow();
   	// setExtFilters(fileChooser);
   	 File file = fileChooser.showOpenDialog(currentStage);
   	 if (file != null) {
   	     try {
   	         openNewImageWindow(file);
   	     } catch (Exception ex) {
   	         System.out.println("error");
   	     }
   	 }
    }
    
    private void openNewImageWindow(File file) throws URISyntaxException {

    Image image = new Image(file.toURI().toString());

    pathImage = file.getAbsolutePath();

    fileName = file.getName();
    edit_image.setPreserveRatio(true);
    edit_image.setImage(image);
    edit_image.setSmooth(true);
    edit_image.setCache(true);
    
    pathLabel.setText(pathImage);
    
  //  System.out.println(pathLabel.getText());

}

}
