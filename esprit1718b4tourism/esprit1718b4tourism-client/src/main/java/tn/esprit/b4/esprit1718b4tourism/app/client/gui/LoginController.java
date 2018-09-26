package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import com.jfoenix.controls.JFXCheckBox;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javax.naming.NamingException;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tn.esprit.b4.esprit1718b4tourism.app.client.businessDelegate.UserServiceDelegate;
import tn.esprit.b4.esprit1718b4tourism.app.client.other.EMailSender;
import tn.esprit.b4.esprit1718b4tourism.app.client.other.MD5;
import tn.esprit.b4.esprit1718b4tourism.entities.Role;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

public class LoginController implements Initializable {

    @FXML
    private Button sign_in;
    @FXML
    private Button submit;
    @FXML
    private DatePicker birthday;
    @FXML
    private TextField first_name;
    @FXML
    private TextField phone;
    @FXML
    private TextField last_name;
    @FXML
    private TextField email;
    @FXML
    private TextField password_in;
    @FXML
    private TextField email_in;
    @FXML
    private AnchorPane Visitor;
    @FXML
    private JFXCheckBox stay_connected;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        birthday.setValue(LocalDate.now());
        StaticVars.setConnectedAs(0);
    }

    @FXML
    private void sign_in_action(ActionEvent event) throws IOException, NamingException {

   

        User u = new User();
        String md5password = MD5.crypt(password_in.getText());

        if (!StaticVars.isValidEmailAddress(email_in.getText().toLowerCase())) {
            StaticVars.alerte("check out your email again");
        } 
        
        else if (password_in.getText().length() < 8) {
            StaticVars.alerte("check out your password again");
        } else if ((u = UserServiceDelegate.findUserByEmailPass(email_in.getText().toLowerCase(), md5password)) != null) {
            if (u.getRole() == Role.Admin) {
            	StaticVars.setConnectedAs(3);
            } else if (u.getRole() == Role.Premium) {
            	StaticVars.setConnectedAs(2);
            } else if (u.getRole() == Role.Standard) {
            	StaticVars.setConnectedAs(1);
            }else if (u.getRole() == Role.Visitor) {
                StaticVars.setConnectedAs(0);
              }

            StaticVars.setCurrentUser(u);
            
            //////////////////file write/////////////////////////////:::
            if(stay_connected.isSelected()) {
            ///write in text file
            String fileName = "temp.txt";
            try {
                FileWriter fileWriter =new FileWriter(fileName);
                BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);
                bufferedWriter.write(email_in.getText().toLowerCase() +" "+md5password);
                bufferedWriter.close();
            }
            catch(IOException ex) {System.out.println( "Error writing to file '" + fileName + "'");
            }
            }
            //////////////////////////////////////////////////////
        

            ////////////////////home///////////////////////
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
            ///////////////////////////////////////////////////

        } else {
            StaticVars.alerte("check out your email and password again");
        }

    }


    @FXML
    private void submit_action(ActionEvent event) throws NamingException, ParseException {
     
        LocalDate localDate = birthday.getValue();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String birthday_Str = dtf.format(localDate);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthdayDate = dateFormat.parse(birthday_Str);
        Date lastLogin = dateFormat.parse(StaticVars.dateSysteme());

        String msg;
        if ((msg = StaticVars.VerifierLongChaine(first_name.getText(), "<< first name >>", 3, 20)) != "valide") {
            StaticVars.alerte(msg);
        } else if ((msg = StaticVars.VerifierLongChaine(last_name.getText(), "<< last name >>", 3, 20)) != "valide") {
            StaticVars.alerte(msg);
        } else if (birthdayDate.compareTo(lastLogin) > 0) {
            StaticVars.alerte("check out your birthday date again");
        } else if (!StaticVars.isValidEmailAddress(email.getText().toLowerCase())) {
            StaticVars.alerte("check out your email again");
        } else if (UserServiceDelegate.findUserByEmail(email.getText().toLowerCase()) != 0) {
            StaticVars.alerte("this mail is already used");
        } else if ((msg = StaticVars.VerifierLongChaine(phone.getText(), "<< phone number >>", 3, 20)) != "valide") {
            StaticVars.alerte(msg);
        } else {
  
            Boolean orientMemb = email.getText().toLowerCase().substring(email.getText().toLowerCase().lastIndexOf("@") + 1).equals("tourism.tn");
            String password = StaticVars.generatePassword(8, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
            String md5password = MD5.crypt(password);

           
            StaticVars.alerte("creating account succesful, check your mail to get your password   \n pass: " + password);

       

            User u1 = new User(first_name.getText(), last_name.getText(), birthdayDate, md5password, lastLogin,
                    email.getText().toLowerCase(), phone.getText(), orientMemb, null, Role.Standard);
          
            int u1id = UserServiceDelegate.create(u1);
            System.out.println(u1id);
            
            try {
				String[] to = { email.getText().toLowerCase()};
				EMailSender.sendMail("tourism.orientt@gmail.com", "123azert", "creating account successful !     mail : "
				        + email.getText() + " and your password is " + password + ".", to, "Orient Account Created");
			} catch (Exception e) {
				System.out.println("no mail was sent");
			}

        }
    }

    @FXML
    private void corriger_champ_phone(KeyEvent event) {
        try {
            int opt = Integer.parseInt(phone.getText());
            if (opt < 0) {
                phone.setText("");
            }
        } catch (NumberFormatException e) {
            phone.setText("");
        }
    }

    @FXML
    private void Visitor_action(MouseEvent event) throws IOException {
        User u = new User();
        u.setEmail("visitor@visitor.tn");
        u.setRole(Role.Visitor);
        
        StaticVars.setCurrentUser(u);

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
