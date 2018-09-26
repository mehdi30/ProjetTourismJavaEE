package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.naming.NamingException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tn.esprit.b4.esprit1718b4tourism.app.client.businessDelegate.UserServiceDelegate;
import tn.esprit.b4.esprit1718b4tourism.entities.Role;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

public class LoginMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException, NamingException {
    	
    	//read file text
    	String fileName = "temp.txt";
        String line = null;
        String result = "vide";
        
        try {
            FileReader fileReader =  new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                result=line;
            }   
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" +  fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println( "Error reading file '"  + fileName + "'");                  
        }
        
        if(result.equals("vide")) {
        	System.out.println("aaaaaaaaaaaaaa");
            Parent root = FXMLLoader.load(  getClass().getResource("Login.fxml")   )  ;
            Scene scene = new Scene (root);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    Platform.exit();
                    System.exit(0);
                }
            });
    }else {
    	
    	String[] t=result.split(" ");
    	






        User u = new User();
        u = UserServiceDelegate.findUserByEmailPass(t[0].toLowerCase(), t[1]);

        String fenetre = "";

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

        Parent root = FXMLLoader.load(  getClass().getResource("ClientHome.fxml")   )  ;
        Scene scene = new Scene (root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
    
    
    	
    
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
