package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tn.esprit.b4.esprit1718b4tourism.entities.Role;


public class ClientHomeController implements Initializable {

    @FXML
    private AnchorPane logout_menu;
    @FXML
    private Button contact;
    @FXML
    private VBox box;
    @FXML
    private JFXButton chatroom;
    @FXML
    private JFXButton profile;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label forumOwner;
    @FXML
    private ImageView imgv;
    @FXML
    private Label logged_as;
    @FXML
    private AnchorPane manage_acc_menu;
    @FXML
    private JFXButton h_hotels;
    @FXML
    private JFXButton h_experiences;
    @FXML
    private JFXButton h_event;
    @FXML
    private JFXButton h_articles;
    @FXML
    private JFXButton h_restaurants;
    @FXML
    private RadioButton radio1;
    @FXML
    private RadioButton radio2;
    @FXML
    private RadioButton radio3;
    @FXML
    private WebView webview;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	String[] links = {"https://www.youtube.com/embed/cRkFYSlTPnE?autoplay=1",
    					  "https://www.youtube.com/embed/XGzlDz5M5qM?autoplay=1",
    					  "https://www.youtube.com/embed/8Ulrn4D3M_Y?autoplay=1"//
    				      };
    	int random = (int) (Math.random() * links.length);
    	  webview.getEngine().load(links[random] );
    	  
        final ToggleGroup group = new ToggleGroup();
        radio1.setToggleGroup(group);
        radio2.setToggleGroup(group);
        radio3.setToggleGroup(group);
        


        if (StaticVars.currentUser.getRole() == Role.Admin) {
           } else if (StaticVars.currentUser.getRole() == Role.Premium) {
            radio3.setDisable(true);    
        } else if (StaticVars.currentUser.getRole() == Role.Standard) {
            radio2.setDisable(true);
            radio3.setDisable(true);
         } else if (StaticVars.currentUser.getRole() == Role.Visitor) {
            radio1.setDisable(true);
            radio2.setDisable(true);
            radio3.setDisable(true);
          
        }

        if (StaticVars.currentUser.getRole() != Role.Admin) {
            manage_acc_menu.setVisible(false);
        }
        
        if(StaticVars.getConnectedAs()==3) {//connected as Admin
        	   radio3.setSelected(true);
        	   logged_as.setText("logged as Admin");
        	 //set image
        }else if(StaticVars.getConnectedAs()==2) {//connected as Premium
        	radio2.setSelected(true);
        	logged_as.setText("logged as Premium");
        	//set image
        }else if(StaticVars.getConnectedAs()==1) {//connected as Standard
        	radio1.setSelected(true);
        	logged_as.setText("logged as Client");
        	//set image
        }else if(StaticVars.getConnectedAs()==0) {//connected as Visitor
        	logged_as.setText("logged as Visitor");
        	//set image
        }

        drawer.blendModeProperty();
        drawer.setSidePane(box);

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

    }



    @FXML
    private void logout_menu_click(MouseEvent event) throws IOException {
    	 String fileName = "temp.txt";
         try {
             FileWriter fileWriter =new FileWriter(fileName);
             BufferedWriter bufferedWriter =
             new BufferedWriter(fileWriter);
             bufferedWriter.write("");
             bufferedWriter.close();
         }
         catch(IOException ex) {System.out.println( "Error writing to file '" + fileName + "'");

         }
         
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
    }

    @FXML
    private void contact_action(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("MailSender.fxml"));
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

    @FXML
    private void h_chat(ActionEvent event) throws IOException {
        if (StaticVars.currentUser.getRole() != Role.Visitor) {
            Parent root = FXMLLoader.load(getClass().getResource("Chat.fxml"));
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
            StaticVars.alerte("you must be logged in");
        }
    }

    @FXML
    private void h_profile(ActionEvent event) throws IOException {
        if (StaticVars.currentUser.getRole() != Role.Visitor) {
            Parent root = FXMLLoader.load(getClass().getResource("ManageAccount.fxml"));
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
            StaticVars.alerte("you must make a profile first");
        }
    }

    @FXML
    private void h_contact(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MailSender.fxml"));
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


    @FXML
    private void radio1_action(ActionEvent event) {
    	StaticVars.setConnectedAs(1);
    	StaticVars.alerte("Hi "+StaticVars.currentUser.getRole().toString()+" ! \n now you are connected as CLIENT");
    	logged_as.setText("logged as Client");
    	//change photo
    }

    @FXML
    private void radio2_action(ActionEvent event) {
    	StaticVars.setConnectedAs(2);
    	StaticVars.alerte("Hi "+StaticVars.currentUser.getRole().toString()+" ! \n now you are connected as PREMIUM");
    	logged_as.setText("logged as Premium");
    }

    @FXML
    private void radio3_action(ActionEvent event) {
    	StaticVars.setConnectedAs(3);
    	StaticVars.alerte("Hi "+StaticVars.currentUser.getRole().toString()+" ! \n now you are connected as ADMIN");
    	logged_as.setText("logged as Admin");
    }

    @FXML
    private void manage_acc_menu_click(MouseEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("AdminManageAccounts.fxml"));
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

    @FXML
    private void h_hotels_action(ActionEvent event) throws IOException {
    	
    	String fenetre="*Hotel*.fxml";
//    	if(StaticVars.getConnectedAs()==3) {//if connected as Admin
//       	 fenetre="**.fxml";
//       }else if(StaticVars.getConnectedAs()==2) {//if connected as Premium
//    	   fenetre="**.fxml";
//       }else if(StaticVars.getConnectedAs()==1) {//if connected as Standard
//    	   fenetre="**.fxml";
//       }else if(StaticVars.getConnectedAs()==0) {//if connected as Visitor
//    	   fenetre="**.fxml";
//       }
    	  
      	Parent root = FXMLLoader.load(getClass().getResource(fenetre));
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

    @FXML
    private void h_experiences_click(ActionEvent event) throws IOException {
    	String fenetre=null;
    	if(StaticVars.getConnectedAs()==3) {//if connected as Admin
       	 fenetre="**.fxml";
       }else if(StaticVars.getConnectedAs()==2) {//if connected as Premium
    	   fenetre="ExperienceBooking.fxml";
       }else if(StaticVars.getConnectedAs()==1) {//if connected as Standard
    	   fenetre="ExperienceBooking.fxml";
       }else if(StaticVars.getConnectedAs()==0) {//if connected as Visitor
    	   fenetre="**.fxml";
       }
    	  
      	Parent root = FXMLLoader.load(getClass().getResource(fenetre));
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

    @FXML
    private void h_event_click(ActionEvent event) throws IOException {
    	String fenetre=null;
    	if(StaticVars.getConnectedAs()==3) {//if connected as Admin
       	 fenetre="**.fxml";
       }else if(StaticVars.getConnectedAs()==2) {//if connected as Premium
    	   fenetre="PremiumEvent.fxml";
       }else if(StaticVars.getConnectedAs()==1) {//if connected as Standard
    	   fenetre="TouristEvent.fxml";
       }else if(StaticVars.getConnectedAs()==0) {//if connected as Visitor
    	   fenetre="TouristEvent.fxml";
       }
    	  
      	Parent root = FXMLLoader.load(getClass().getResource(fenetre));
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

    @FXML
    private void h_articles_click(ActionEvent event) throws IOException {
    	String fenetre=null;
    	if(StaticVars.getConnectedAs()==3) {//if connected as Admin
       	 fenetre="**.fxml";
       }else if(StaticVars.getConnectedAs()==2) {//if connected as Premium
    	   fenetre="souvenir.fxml";
       }else if(StaticVars.getConnectedAs()==1) {//if connected as Standard
    	   fenetre="SouvenirClient.fxml";
       }else if(StaticVars.getConnectedAs()==0) {//if connected as Visitor
    	   fenetre="**.fxml";
       }
    	  
      	Parent root = FXMLLoader.load(getClass().getResource(fenetre));
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

    @FXML
    private void h_restaurants_click(ActionEvent event) throws IOException {
    	String fenetre=null;
    	if(StaticVars.getConnectedAs()==3) {//if connected as Admin
       	 fenetre="AdminCherRestau.fxml";
       }else if(StaticVars.getConnectedAs()==2) {//if connected as Premium
    	    	   fenetre="MyRestau.fxml";
       }else if(StaticVars.getConnectedAs()==1) {//if connected as Standard
    	   fenetre="CherRestau.fxml";
       }else if(StaticVars.getConnectedAs()==0) {//if connected as Visitor
    	   fenetre="CherRestau.fxml";
       }
    	  
      	Parent root = FXMLLoader.load(getClass().getResource(fenetre));
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