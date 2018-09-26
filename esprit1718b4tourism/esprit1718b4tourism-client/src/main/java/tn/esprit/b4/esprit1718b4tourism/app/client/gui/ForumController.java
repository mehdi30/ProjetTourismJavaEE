package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import java.awt.TextArea;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.esprit.b4.esprit1718b4tourism.entities.Event;
import tn.esprit.b4.esprit1718b4tourism.entities.CommentEvent;
import tn.esprit.b4.esprit1718b4tourism.entities.Topic;
import tn.esprit.b4.esprit1718b4tourism.entities.User;
import tn.esprit.b4.esprit1718b4tourism.services.CommentEventServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.TopicServiceRemote;


public class ForumController extends AnchorPane  implements Initializable,Serializable{

	@FXML
	private ImageView poubelle;
	@FXML
	private JFXButton jobOffer;
	@FXML
    private JFXButton UpdateForumm;
	 @FXML
	    private JFXComboBox<String> forumBox;
	@FXML
    private JFXButton AddForum;
	  @FXML
	    private JFXButton goToStat;
	@FXML
    private JFXButton deleteForum;

	@FXML
    private ImageView imgv;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private VBox box;

    @FXML
    private JFXHamburger hamburger;  
    
    @FXML
    private TableView<Topic> ForumTable;
    
    @FXML
    private TableColumn<Topic,String> TitleColonne;

    @FXML
    private ScrollBar BarForum;
    
    @FXML
    private TableColumn<Topic, Date> DateForum;
    
    @FXML
    private JFXTextField searchForum;
    
    @FXML
    private TableView<CommentEvent> CommentTable;
    
    @FXML
    private Label forumOwner;
   
    @FXML
    private TableColumn<CommentEvent,String > owner;

    @FXML
    private TableColumn<CommentEvent,Date> date;
    
    @FXML
    private TableColumn<CommentEvent,String> content;
    
    @FXML
    private ScrollBar s;
    @FXML
    private JFXTextField ForumDescription;
    @FXML
    private JFXTextField ForumTitle;
    @FXML
    private Label updateError;
    @FXML
    private JFXButton addComment;
    @FXML
    private JFXButton deleteComment;
    @FXML
    private javafx.scene.control.TextArea commentContent;
    @FXML
    private JFXButton updateComment;
    @FXML
    private JFXComboBox<String> sortCategory;
    @FXML
    private JFXComboBox<String> sortByCommentsNumb;
    @FXML
    private Label errorCom;
    @FXML
    private JFXButton inappropriate;
    @FXML
    private JFXButton event;
    @FXML
    private JFXButton message;
    @FXML
    private JFXButton logOut;
    @FXML
    private JFXButton notification;
    ObservableList<CommentEvent> data1;
    ObservableList<Topic> data;
    
    @FXML
	private JFXButton logout;
    
    @FXML
    private JFXButton baack;
    
    @FXML
    private JFXButton btnfiltrer;
    
    
    MainFx application; 
    public MainFx getApplication() {
        return application;
    }

    public void setApplication(MainFx application) {
        this.application = application;
    }


//esprit1718b4tourism-ear/esprit1718b4tourism-service/CommentEventService!tn.esprit.b4.esprit1718b4tourism.services.CommentEventServiceRemote
//esprit1718b4tourism-ear/esprit1718b4tourism-service/TopicService!tn.esprit.b4.esprit1718b4tourism.services.TopicServiceRemote
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		ForumTitle.setStyle("-fx-text-inner-color: black;");
		ForumDescription.setStyle("-fx-text-inner-color: black;");
		searchForum.setStyle("-fx-text-inner-color: white;");
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
        
        ObservableList<String> items = FXCollections.observableArrayList("Events",
		        "Hotels","Restaurants","Tradition products","Experiences");
        forumBox.setItems(items);
		sortCategory.setItems(items);	
		
		ObservableList<String> itemss = FXCollections.observableArrayList("Having most Comments number" ,"Having least Comments number");
        sortByCommentsNumb.setItems(itemss);
        
        InitialContext ctx;
		try {
			ctx = new InitialContext();
			TopicServiceRemote proxy = (TopicServiceRemote) ctx
					.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/TopicService!tn.esprit.b4.esprit1718b4tourism.services.TopicServiceRemote");
			TitleColonne.setCellValueFactory(new PropertyValueFactory<Topic, String>("title"));
			DateForum.setCellValueFactory(new PropertyValueFactory<Topic, Date>("date"));
			data = FXCollections.observableArrayList(proxy.FindAllTopics());
			ForumTable.setItems(data);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			

		
	}

	
	@FXML
    void selectionAction() throws NamingException {
		int i=ForumTable.getSelectionModel().getSelectedIndex(); 	
		
			InitialContext ctx;
		 	ctx = new InitialContext();
		 	TopicServiceRemote proxy;
			proxy = (TopicServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/TopicService!tn.esprit.b4.esprit1718b4tourism.services.TopicServiceRemote");
			Topic t =proxy.FindTopicById((ForumTable.getItems()).get(i).getIdTopic());
			ForumTitle.setText(t.getTitle());
			ForumDescription.setText(t.getDescription());
			forumBox.setValue(t.getCategory());
			forumOwner.setText(t.getUser().getFirstName()+" "+t.getUser().getLastName());
			updateError.setText("");
			errorCom.setText("");
			
			 try {
					InitialContext ctx1 = new InitialContext();
					Object obj1 =ctx1.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/CommentEventService!tn.esprit.b4.esprit1718b4tourism.services.CommentEventServiceRemote");
					CommentEventServiceRemote prox = (CommentEventServiceRemote) obj1;
					date.setCellValueFactory(new PropertyValueFactory<CommentEvent, Date>("date"));
					content.setCellValueFactory(new PropertyValueFactory<CommentEvent, String>("content"));
					
					owner.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommentEvent, String>, ObservableValue<String>>() {

					    @Override
					    public ObservableValue<String> call(TableColumn.CellDataFeatures<CommentEvent, String> m) {
					        if (m.getValue() != null) {
					            return new SimpleStringProperty(m.getValue().getUser().getFirstName()+" "+m.getValue().getUser().getLastName());
					        } else {
					            return new SimpleStringProperty("<no name>");
					        }
					    }
					});
					data1 = FXCollections.observableArrayList(prox.FindCommentByTopic(ForumTable.getItems().get(i)));
					CommentTable.setItems(data1);
					updateError.setText("");
					errorCom.setText("");
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		
    }

	
	  @FXML
	    void inappropriateComments(ActionEvent event) throws NamingException {
		 
			  InitialContext ctx1;
			
				ctx1 = new InitialContext();
				Object obj1 =ctx1.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/CommentEventService!tn.esprit.b4.esprit1718b4tourism.services.CommentEventServiceRemote");
				CommentEventServiceRemote prox = (CommentEventServiceRemote) obj1;
				List<CommentEvent> allComments = new ArrayList<CommentEvent>();
				String[] bad={"inapcomments","badcomment"} ;
				allComments=prox.FindAllComments();
		
			for (CommentEvent comment : allComments) {
					boolean state;
					String c =comment.getContent();
					String[] commentParts  ;
					commentParts=c.split(" ");	
				for (String badd : bad) {
					for (String s : commentParts) {
						if( (state= s.equals(badd))==true)
							{	
								prox.DeleteComment(comment);
								
								   String mail="chadha.omezine@esprit.tn";
							       String pass="ninaomezzine12345";
							       String[] to={comment.getUser().getEmail()};
							       String subject="Blame From Tourism Administration";
							       String body="we're sending  you this mail to inform you that we have to delete your comment '"+c
							       +"' which contains inappropriate words which are '"+s+"' in the forum '"+comment.getTopic().getTitle()+"', please do not "
							       		+ " repeat this kind of stuff in our website which is purely professional.\n Cordially ";
							       SendMailTLS sendMail = new SendMailTLS();
							       sendMail.sendFromGMail(mail,pass,to,subject,body);
							        System.out.println("done");	  
							        //String name=comment.getUser().getFirstName();
							        //String Last=comment.getUser().getLastName();
							        
						        
						   }
					    }
						
					 }
					
				   }
			
			 try {
				 int i=ForumTable.getSelectionModel().getSelectedIndex(); 	
					InitialContext ctx2 = new InitialContext();
					Object obj2 =ctx2.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/CommentEventService!tn.esprit.b4.esprit1718b4tourism.services.CommentEventServiceRemote");
					CommentEventServiceRemote proxx = (CommentEventServiceRemote) obj1;
					date.setCellValueFactory(new PropertyValueFactory<CommentEvent, Date>("date"));
					content.setCellValueFactory(new PropertyValueFactory<CommentEvent, String>("content"));
					
					owner.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommentEvent, String>, ObservableValue<String>>() {

					    @Override
					    public ObservableValue<String> call(TableColumn.CellDataFeatures<CommentEvent, String> m) {
					        if (m.getValue() != null) {
					            return new SimpleStringProperty(m.getValue().getUser().getFirstName()+" "+m.getValue().getUser().getLastName());
					        } else {
					            return new SimpleStringProperty("<no name>");
					        }
					    }
					});
					data1 = FXCollections.observableArrayList(prox.FindCommentByTopic(ForumTable.getItems().get(i)));
					CommentTable.setItems(data1);
					updateError.setText("");
					errorCom.setText("");
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			} 
				
		    
    	
	    
	
	  @FXML
	    void Search(KeyEvent event) throws Throwable   {
		  InitialContext ctx;			
		  ctx = new InitialContext();
		  TopicServiceRemote proxy;
	      proxy = (TopicServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/TopicService!tn.esprit.b4.esprit1718b4tourism.services.TopicServiceRemote");
	      ObservableList<Topic> data = FXCollections.observableList(proxy.advancedsearchTopic(searchForum.getText()));
	        ForumTable.setItems(data);
	
	    }		
	 
	  
	  
	  @FXML
	    void deleteForum(ActionEvent event) throws NamingException {
		 	InitialContext ctx;	
			ctx = new InitialContext();
			TopicServiceRemote proxy;
			proxy = (TopicServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/TopicService!tn.esprit.b4.esprit1718b4tourism.services.TopicServiceRemote");
			int i = ForumTable.getSelectionModel().getSelectedIndex();
			Topic T1= ForumTable.getSelectionModel().getSelectedItem();
			//CommentEvent t =CommentTable.getSelectionModel().getSelectedItem();
			if(ForumTable.getSelectionModel().isSelected(i) && 
					(AllReservationsController.iduser)==(T1.getUser().getId())){
			proxy.DeleteTopic(ForumTable.getSelectionModel().getSelectedItem());
			ForumTitle.clear();
	        ForumDescription.clear();
	        forumOwner.setText("");
	        forumBox.setPromptText("Categories");
			InitialContext ctx1 = new InitialContext();
			Object obj1 =ctx1.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/CommentEventService!tn.esprit.b4.esprit1718b4tourism.services.CommentEventServiceRemote");
			CommentEventServiceRemote prox = (CommentEventServiceRemote) obj1;
			prox.DeleteCommentByTopic(ForumTable.getSelectionModel().getSelectedItem());
			ObservableList<Topic> productSelected, allProducts;
	        allProducts = ForumTable.getItems();
	        productSelected = ForumTable.getSelectionModel().getSelectedItems();
	        productSelected.forEach(allProducts::remove);
	        updateError.setText("operation succeed");
	        data1 = FXCollections.observableArrayList(prox.FindCommentByTopic(ForumTable.getSelectionModel().getSelectedItem()));
			CommentTable.setItems(data1);}
				
				else{
				updateError.setText("NO FORUM SELECTED");				}
	        
	    }
	  @FXML
	    void UpdateForumm(ActionEvent event) throws NamingException {
		 InitialContext ctx;
		  	ctx = new InitialContext();
		  	TopicServiceRemote proxy;
			proxy = (TopicServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/TopicService!tn.esprit.b4.esprit1718b4tourism.services.TopicServiceRemote");
			int i = ForumTable.getSelectionModel().getSelectedIndex();
			if(ForumTable.getSelectionModel().isSelected(i)){
			Topic t =ForumTable.getSelectionModel().getSelectedItem();
			if((AllReservationsController.UserForum.getId())==(t.getUser().getId())&& !ForumTitle.getText().equals("")&& !ForumDescription.getText().equals("") ){
			t.setTitle(ForumTitle.getText());
			t.setDescription((ForumDescription.getText()));
			t.setCategory(forumBox.getSelectionModel().getSelectedItem());
			proxy.UpdateTopic(t);
			data = FXCollections.observableArrayList(proxy.FindAllTopics());
	        ForumTable.setItems(data);
	        ForumTitle.clear();
	        ForumDescription.clear();
	        forumOwner.setText("");
	        updateError.setText("operation succeed");
	        forumBox.setPromptText("Categories");
			}}else{
				updateError.setText("CHECK YOUR PARAMETERS");
			}
		
	  }
	  @FXML
	    void AddForum(ActionEvent event) throws NamingException {
		 InitialContext ctx;
		  ctx = new InitialContext();
		  TopicServiceRemote proxy;
		  proxy = (TopicServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/TopicService!tn.esprit.b4.esprit1718b4tourism.services.TopicServiceRemote");
		  if(!ForumTitle.getText().equals("")&& !ForumDescription.getText().equals("") && !forumBox.getValue().toString().isEmpty())
		  {Date d = new Date();
		  String selected=forumBox.getSelectionModel().getSelectedItem();
		  Topic t=new Topic(ForumTitle.getText(),ForumDescription.getText(),selected,d,AllReservationsController.UserForum);	
		 
		  //System.out.println(AllReservationsController.UserForum.getFirstName());
		  proxy.AddTopic(t);
		  data = FXCollections.observableArrayList(proxy.FindAllTopics());
		  ForumTable.setItems(data);
		  ForumTitle.clear();
		  ForumDescription.clear();
		  forumBox.setPromptText("Categories");
		  updateError.setText("operation succeed");
	  }else{
    	updateError.setText("You have an empty textfield");
    }
	    }
	  @FXML
	    void jobOffer(ActionEvent event) {
		 // application.gotoJobOffer();
	    }
	  
	  
	  
	  @FXML
	    void addComment(ActionEvent event) throws NamingException {
		int i = ForumTable.getSelectionModel().getSelectedIndex();
		  	if(!commentContent.getText().equals("") && ForumTable.getSelectionModel().isSelected(i)){
		  	InitialContext ctx;
		  	ctx = new InitialContext();
		  	TopicServiceRemote proxy;
			proxy = (TopicServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/TopicService!tn.esprit.b4.esprit1718b4tourism.services.TopicServiceRemote");
			Topic t =ForumTable.getSelectionModel().getSelectedItem();
			Date d = new Date();
			//Administrator a = application.person;
			User a = AllReservationsController.UserForum;
			CommentEvent c = new CommentEvent(commentContent.getText(),d,a,t); 
			InitialContext ctx1 = new InitialContext();
			Object obj1 =ctx1.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/CommentEventService!tn.esprit.b4.esprit1718b4tourism.services.CommentEventServiceRemote");
			CommentEventServiceRemote prox = (CommentEventServiceRemote) obj1;
			prox.AddComment(c);
			Long result=proxy.TopicCommentsNumber(t.getIdTopic());
			Long longResult = Long.valueOf(result);	
			
			//String stringResult = Objects.toString(longResult, null);
			t.setCommentsNumber(longResult); 
			proxy.UpdateTopic(t);
			
			data1 = FXCollections.observableArrayList(prox.FindCommentByTopic(ForumTable.getSelectionModel().getSelectedItem()));
			//System.out.println("id topic"+ForumTable.getSelectionModel().getSelectedItem().getIdTopic());
			CommentTable.setItems(data1);
			commentContent.clear();}else{
				errorCom.setText("NO FORUM SELECTED");
			}
			
			
	    }
	  @FXML
	    void updateComment(ActionEvent event) throws NamingException {
		 InitialContext ctx1 = new InitialContext();
			Object obj1 =ctx1.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/CommentEventService!tn.esprit.b4.esprit1718b4tourism.services.CommentEventServiceRemote");
			CommentEventServiceRemote prox = (CommentEventServiceRemote) obj1;				
			int i = CommentTable.getSelectionModel().getSelectedIndex();
			if( CommentTable.getSelectionModel().isSelected(i)){
			CommentEvent t =CommentTable.getSelectionModel().getSelectedItem();
			if((AllReservationsController.UserForum.getId())==(t.getUser().getId())&& !commentContent.getText().equals("")){
			t.setContent(commentContent.getText());
			prox.UpdateComment(t);
			data1 = FXCollections.observableArrayList(prox.FindCommentByTopic(ForumTable.getSelectionModel().getSelectedItem()));
			//System.out.println("id topic"+ForumTable.getSelectionModel().getSelectedItem().getIdTopic());
			CommentTable.setItems(data1);
			commentContent.clear();
			
	        
			}}else{
				errorCom.setText("NO COMMENT SELECTED");
			}
	    }
	  @FXML
	    void deleteComment(ActionEvent event) throws NamingException {
		 	int i = CommentTable.getSelectionModel().getSelectedIndex();
		  	if(CommentTable.getSelectionModel().isSelected(i)){
		  	InitialContext ctx1 = new InitialContext();
			Object obj1 =ctx1.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/CommentEventService!tn.esprit.b4.esprit1718b4tourism.services.CommentEventServiceRemote");
			CommentEventServiceRemote prox = (CommentEventServiceRemote) obj1;
			CommentEvent t =CommentTable.getSelectionModel().getSelectedItem();
			if((AllReservationsController.UserForum.getId())==(t.getUser().getId()))
			{
			prox.DeleteComment(CommentTable.getSelectionModel().getSelectedItem());
			ObservableList<CommentEvent> productSelected, allProducts;
	        allProducts = CommentTable.getItems();
	        productSelected = CommentTable.getSelectionModel().getSelectedItems();
	        productSelected.forEach(allProducts::remove);
	        commentContent.clear();
			}
			}else{
	        	errorCom.setText("NO COMMENT SELECTED");}

	        }
	        
	    
	 
	  
	  @FXML
	    void sortByCategory(ActionEvent event) throws NamingException {
		 InitialContext ctx;
		  ctx = new InitialContext();
		  TopicServiceRemote proxy;
			proxy = (TopicServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/TopicService!tn.esprit.b4.esprit1718b4tourism.services.TopicServiceRemote");
			 ObservableList<Topic> data = FXCollections.observableList(proxy.sortTopicByCategory(sortCategory.getSelectionModel().getSelectedItem()));
		     ForumTable.setItems(data);
		     sortCategory.setPromptText("sort by");
	    }
	  
	  @FXML
	    void sortByCommentsNumber(ActionEvent event) throws NamingException {
		 InitialContext ctx;
		  ctx = new InitialContext();
		  TopicServiceRemote proxy;
			proxy = (TopicServiceRemote) ctx.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/TopicService!tn.esprit.b4.esprit1718b4tourism.services.TopicServiceRemote");
			if(sortByCommentsNumb.getSelectionModel().getSelectedItem().equals("Having most comments number"))
			{ObservableList<Topic> data = FXCollections.observableList(proxy.sortTopicsByCommentsNumberDesc());
		        ForumTable.setItems(data);
		        sortCategory.setPromptText("sort by");
			}else{
				if(sortByCommentsNumb.getSelectionModel().getSelectedItem().equals("Having least comments number"))
				{
					ObservableList<Topic> data = FXCollections.observableList(proxy.sortTopicsByCommentsNumberAsc());
			        ForumTable.setItems(data);
			        sortCategory.setPromptText("sort by");
				}

			}
	    }
	  @FXML
	    void getComment() throws NamingException {
			int i=CommentTable.getSelectionModel().getSelectedIndex();
		  	
		  	InitialContext ctx1 = new InitialContext();
			Object obj1 =ctx1.lookup("esprit1718b4tourism-ear/esprit1718b4tourism-service/CommentEventService!tn.esprit.b4.esprit1718b4tourism.services.CommentEventServiceRemote");
			CommentEventServiceRemote prox = (CommentEventServiceRemote) obj1;
			CommentEvent t =prox.FindCommentById((CommentTable.getItems().get(i)).getIdCm());
			commentContent.setText(t.getContent());
	    }

	  

	  @FXML
	    void goToStats(ActionEvent event) {
		 navigate("ForumStat.fxml");
	    }

	  private void navigate(String fxml) {
		    try {
		        Stage stage = new Stage();
		        Stage currentStage = (Stage) goToStat.getScene().getWindow();
		        Parent root = FXMLLoader.load(getClass().getResource(fxml));

		        Scene scene = new Scene(root);

		        stage.setScene(scene);
		        stage.setResizable(false);

		        stage.show();
		        currentStage.close();
		    } catch (IOException ex) {
		        Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}

	@FXML
	public void baaaack(ActionEvent event)
	{
		
		navigate1("TouristEvent.fxml");
		
	}
	
	 private void navigate1(String fxml) {
		    try {
		        Stage stage = new Stage();
		        Stage currentStage = (Stage) goToStat.getScene().getWindow();
		        Parent root = FXMLLoader.load(getClass().getResource(fxml));

		        Scene scene = new Scene(root);

		        stage.setScene(scene);
		        stage.setResizable(false);

		        stage.show();
		        currentStage.close();
		    } catch (IOException ex) {
		        Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
	 private void navigate2(String fxml) {
		    try {
		        Stage stage = new Stage();
		        Stage currentStage = (Stage) btnfiltrer.getScene().getWindow();
		        Parent root = FXMLLoader.load(getClass().getResource(fxml));

		        Scene scene = new Scene(root);

		        stage.setScene(scene);
		        stage.setResizable(false);

		        stage.show();
		        currentStage.close();
		    } catch (IOException ex) {
		        Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
	 
	 @FXML
	    void logoutt(ActionEvent event) {
			
			navigate2("Login.fxml");
			
		}
	 
}
