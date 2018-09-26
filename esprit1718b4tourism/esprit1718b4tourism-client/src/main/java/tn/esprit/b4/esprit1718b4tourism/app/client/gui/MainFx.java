package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.InputStream;
import java.util.logging.Level;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

public class MainFx extends Application {
	Stage stage;
	public static User person;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		gotoForum();
		primaryStage.setTitle("Tourism");
		primaryStage.show();
	}
	
	
	public void gotoForum() {
        try {
            ForumController a = (ForumController) replaceSceneContent("/tn.esprit.b4.esprit1718b4tourism.app.client.gui/Forum.fxml");
            a.setApplication(this);
            
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MainFx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	
	public static void main(String[] args) {
		launch(args);
	}

	private Initializable replaceSceneContent(String fxml) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		InputStream in = MainFx.class.getResourceAsStream(fxml);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(MainFx.class.getResource(fxml));
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load(in);
		} finally {
			in.close();
		}
		Scene scene = new Scene(page);
		stage.setScene(scene);
		stage.sizeToScene();
		return (Initializable) loader.getController();
	}
}
