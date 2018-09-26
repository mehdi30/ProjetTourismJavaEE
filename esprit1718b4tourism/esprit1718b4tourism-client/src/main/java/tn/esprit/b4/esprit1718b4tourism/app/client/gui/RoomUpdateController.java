package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tn.esprit.b4.esprit1718b4tourism.entities.Hotel;
import tn.esprit.b4.esprit1718b4tourism.entities.Room;
import tn.esprit.b4.esprit1718b4tourism.entities.RoomType;
import tn.esprit.b4.esprit1718b4tourism.services.HotelService;
import tn.esprit.b4.esprit1718b4tourism.services.HotelServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.RoomServiceRemote;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class RoomUpdateController implements Initializable{

	ObservableList<String> roomTypeList = FXCollections.observableArrayList("Single" , "Double" , "Triple" , "Suite");
	ObservableList<String> AvailabilityList = FXCollections.observableArrayList("YES","NO");
	
	
	
	TrayNotification Notification = new TrayNotification();
	String  jndi ="esprit1718b4tourism-ear/esprit1718b4tourism-service/RoomService!tn.esprit.b4.esprit1718b4tourism.services.RoomServiceRemote";
	
	private Hotel hotel ;
	
    @FXML
    private Label as_user_name;

    @FXML
    private TableView<Room> tableView;
    
    @FXML
    private TableColumn<Room, Integer> NumberColumn;

    @FXML
    private TableColumn<Room, String> typeColumn;

    @FXML
    private TableColumn<Room, String> availabilityColumn;

    @FXML
    private TableColumn<Room, Integer> priceColumn;

    @FXML
    private JFXComboBox<String> sortType;

    @FXML
    private JFXTextField searchRoom;

    @FXML
    private JFXComboBox<String> sortAvailability;

    @FXML
    private JFXTextField roomPrice;

    @FXML
    private JFXTextField roomNumber;

    @FXML
    private ComboBox<String> roomType;
    
    
    @FXML
    private ComboBox<String> roomAvailability;
    
    @FXML
    private JFXButton addNewRoom;

    @FXML
    private JFXButton updateRoom;

    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	hotel = HotelUpdateController.hotel ;
    	System.out.println("enaaaa  linnaaaaa");
    	System.out.println(hotel.getId());
    	System.out.println(hotel.getName());
    	roomAvailability.setItems(AvailabilityList);
    	roomType.setItems(roomTypeList);
    	sortType.setItems(roomTypeList);
    	sortAvailability.setItems(AvailabilityList);
    	
    	updateRoom.setVisible(false);
    	refresh();
    	showRooms(hotel);
		
	}
    
    @FXML
    void addRoom(ActionEvent event) throws NamingException, IOException{
    	Room room = new Room(roomNumber.getText(),roomType.getValue().toString(),roomAvailability.getValue().toString(),Integer.parseInt(roomPrice.getText()));
    	InitialContext ctx;
		ctx = new InitialContext();
		RoomServiceRemote proxy;
		proxy = (RoomServiceRemote) ctx.lookup(jndi);
		if(emptyTest())
		{
			Alerts("You must verify cases");
		}else if(Exist(room)){
			Alerts("Room Exist ");
		}else{
			
			room.setHotel(hotel);
			proxy.add(room);
			refresh();
			showRooms(hotel);
		}
    }

    @FXML
    void deleteRoom(ActionEvent event) throws NamingException{
    	try {
            if (!tableView.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("You shoud select an Item");
              	  Context context = new InitialContext();
            		RoomServiceRemote delete = (RoomServiceRemote) context.lookup(jndi);
            		delete.delete(tableView.getSelectionModel().getSelectedItem().getId());
            		showRooms(hotel);
            		refresh();
            }
        } catch (Exception ex) {
            System.out.println("error delete" + ex.getMessage());

        }
    }


    @FXML
    void updateRoom(ActionEvent event) throws NamingException {
    	Integer selected = tableView.getSelectionModel().getSelectedIndex();	
    	InitialContext ctx;
		ctx = new InitialContext();
		RoomServiceRemote proxy;
		proxy = (RoomServiceRemote) ctx.lookup(jndi);
		if (tableView.getSelectionModel().isSelected(selected)) 
		{
			Room room = tableView.getItems().get(selected);
			if (emptyTest())
			{
				Alerts("You must verify cases");
			}else if(Exist(room)){
				Alerts("Room Exist ");
			}else{	
				room.setNumbRoom(roomNumber.getText());
				room.setPrice(Integer.parseInt(roomPrice.getText()));
				room.setAvailability(roomAvailability.getValue().toString());
				room.setRoomType(roomType.getValue().toString());
				proxy.update(room);
				updateRoom.setVisible(false);
		        addNewRoom.setVisible(true);
				refresh();
				showRooms(hotel);
			}
		}
    }

    
    public void Alerts(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
    private boolean emptyTest()
    {
    	if(roomNumber.getText().equals("")|| roomPrice.getText().equals("") || roomAvailability.getValue().toString().equals("") || roomType.getValue().toString().equals("") || Float.parseFloat(roomPrice.getText())<=0 )
		{
    		return true ;
		}
		return false ;
    }
    
    private void showRooms(Hotel hotel){
    	InitialContext ctx;
		try {
			ctx = new InitialContext();
			RoomServiceRemote proxy;
			proxy = (RoomServiceRemote) ctx.lookup(jndi);
			NumberColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("numbRoom"));
			priceColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("price"));
			availabilityColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("availability"));
			typeColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("roomType"));
			List result = proxy.findRoomsByHotel(hotel) ;
			if(result.isEmpty()){
				Notification.setTray(" New Hotel", "Empty Romms", NotificationType.SUCCESS);
                Notification.showAndDismiss(Duration.millis(3000));
                Notification.setAnimationType(AnimationType.SLIDE);
			}else
			{
				ObservableList<Room> data = FXCollections.observableArrayList(result);
				tableView.setItems(data);
			}
			tableView.setRowFactory(new Callback<TableView<Room>, TableRow<Room>>() {
				            
							@Override
							public TableRow<Room> call(TableView<Room> TableView) {
								final TableRow<Room> row = new TableRow<>();
				                final ContextMenu contextMenu = new ContextMenu();
				                final MenuItem updateRoom = new MenuItem("Update ");
				                final MenuItem deleteRoom = new MenuItem("Delete ");
			
				                updateRoom.setOnAction(new EventHandler<ActionEvent>() {
				                    @Override
				                    public void handle(ActionEvent event) {
				                    	deplace();
				                    }
				                });
				                
				                deleteRoom.setOnAction(new EventHandler<ActionEvent>() {
			
									@Override
									public void handle(ActionEvent event) {
										Context context;
										try {
											int reponse = JOptionPane.showConfirmDialog(null,"Are you sur?"
								                     ,"Delete Room",JOptionPane.YES_NO_OPTION);
											if( reponse == JOptionPane.YES_OPTION){
												context = new InitialContext();
												RoomServiceRemote delete = (RoomServiceRemote) context.lookup(jndi);
							            		delete.delete(tableView.getSelectionModel().getSelectedItem().getId());
							            		Notification.setTray("Room Deleted", tableView.getSelectionModel().getSelectedItem().getNumbRoom(), NotificationType.SUCCESS);
							                    Notification.showAndDismiss(Duration.millis(3000));
							                    Notification.setAnimationType(AnimationType.SLIDE);
							            		refresh();
							            		showRooms(hotel);
											}
										} catch (NamingException e) {
											e.printStackTrace();
										}
									}
				                });
				                
				                contextMenu.getItems().add(updateRoom);
				                contextMenu.getItems().add(deleteRoom);
				                row.contextMenuProperty().bind(
				                        Bindings.when(row.emptyProperty())
				                                .then((ContextMenu) null)
				                                .otherwise(contextMenu)
				                );
				                return row;
							}
			    		});
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
    	
    }
    
    private void showRoomsByType(Hotel hotel, String type){
    	InitialContext ctx;
		try {
			ctx = new InitialContext();
			RoomServiceRemote proxy;
			proxy = (RoomServiceRemote) ctx.lookup(jndi);
			NumberColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("numbRoom"));
			priceColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("price"));
			availabilityColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("availability"));
			typeColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("roomType"));
			List result = proxy.findByType(hotel, type) ;
			ObservableList<Room> data = FXCollections.observableArrayList(result);
			System.out.println("showRoomsByType");
			tableView.setItems(data);
    		
			tableView.setRowFactory(new Callback<TableView<Room>, TableRow<Room>>() {
				            
							@Override
							public TableRow<Room> call(TableView<Room> TableView) {
								final TableRow<Room> row = new TableRow<>();
				                final ContextMenu contextMenu = new ContextMenu();
				                final MenuItem updateRoom = new MenuItem("Update ");
				                final MenuItem deleteRoom = new MenuItem("Delete ");
			
				                updateRoom.setOnAction(new EventHandler<ActionEvent>() {
				                    @Override
				                    public void handle(ActionEvent event) {
				                    	deplace();
				                    }
				                });
				                
				                deleteRoom.setOnAction(new EventHandler<ActionEvent>() {
			
									@Override
									public void handle(ActionEvent event) {
										Context context;
										try {
											int reponse = JOptionPane.showConfirmDialog(null,"Are you sur?"
								                     ,"Delete Room",JOptionPane.YES_NO_OPTION);
											if( reponse == JOptionPane.YES_OPTION){
												context = new InitialContext();
												RoomServiceRemote delete = (RoomServiceRemote) context.lookup(jndi);
							            		delete.delete(tableView.getSelectionModel().getSelectedItem().getId());
							            		Notification.setTray("Room Deleted", tableView.getSelectionModel().getSelectedItem().getNumbRoom(), NotificationType.SUCCESS);
							                    Notification.showAndDismiss(Duration.millis(3000));
							                    Notification.setAnimationType(AnimationType.SLIDE);
							            		refresh();
							            		showRoomsByType(hotel,type);
											}
										} catch (NamingException e) {
											e.printStackTrace();
										}
									}
				                });
				                
				                contextMenu.getItems().add(updateRoom);
				                contextMenu.getItems().add(deleteRoom);
				                row.contextMenuProperty().bind(
				                        Bindings.when(row.emptyProperty())
				                                .then((ContextMenu) null)
				                                .otherwise(contextMenu)
				                );
				                return row;
							}
			    		});
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
    	
    }
    
    private void showRoomsByAvailability(Hotel hotel, String availability){
    	InitialContext ctx;
		try {
			ctx = new InitialContext();
			RoomServiceRemote proxy;
			proxy = (RoomServiceRemote) ctx.lookup(jndi);
			NumberColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("numbRoom"));
			priceColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("price"));
			availabilityColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("availability"));
			typeColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("roomType"));
			System.out.println(availability);
			List result = proxy.findByAvailability(hotel, availability) ;
			ObservableList<Room> data = FXCollections.observableArrayList(result);
			System.out.println("showRoomsByType");
			tableView.setItems(data);
    		
			tableView.setRowFactory(new Callback<TableView<Room>, TableRow<Room>>() {
				            
							@Override
							public TableRow<Room> call(TableView<Room> TableView) {
								final TableRow<Room> row = new TableRow<>();
				                final ContextMenu contextMenu = new ContextMenu();
				                final MenuItem updateRoom = new MenuItem("Update ");
				                final MenuItem deleteRoom = new MenuItem("Delete ");
			
				                updateRoom.setOnAction(new EventHandler<ActionEvent>() {
				                    @Override
				                    public void handle(ActionEvent event) {
				                    	deplace();
				                    }
				                });
				                
				                deleteRoom.setOnAction(new EventHandler<ActionEvent>() {
			
									@Override
									public void handle(ActionEvent event) {
										Context context;
										try {
											int reponse = JOptionPane.showConfirmDialog(null,"Are you sur?"
								                     ,"Delete Room",JOptionPane.YES_NO_OPTION);
											if( reponse == JOptionPane.YES_OPTION){
												context = new InitialContext();
												RoomServiceRemote delete = (RoomServiceRemote) context.lookup(jndi);
							            		delete.delete(tableView.getSelectionModel().getSelectedItem().getId());
							            		Notification.setTray("Room Deleted", tableView.getSelectionModel().getSelectedItem().getNumbRoom(), NotificationType.SUCCESS);
							                    Notification.showAndDismiss(Duration.millis(3000));
							                    Notification.setAnimationType(AnimationType.SLIDE);
							            		refresh();
							            		showRoomsByType(hotel,availability);
											}
										} catch (NamingException e) {
											e.printStackTrace();
										}
									}
				                });
				                
				                contextMenu.getItems().add(updateRoom);
				                contextMenu.getItems().add(deleteRoom);
				                row.contextMenuProperty().bind(
				                        Bindings.when(row.emptyProperty())
				                                .then((ContextMenu) null)
				                                .otherwise(contextMenu)
				                );
				                return row;
							}
			    		});
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
    	
    }
    
    private void showRoomsByNumb(Hotel hotel, String numb){
    	InitialContext ctx;
		try {
			ctx = new InitialContext();
			RoomServiceRemote proxy;
			proxy = (RoomServiceRemote) ctx.lookup(jndi);
			NumberColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("numbRoom"));
			priceColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("price"));
			availabilityColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("availability"));
			typeColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("roomType"));
			List result = proxy.searchRoom(hotel, numb);
			ObservableList<Room> data = FXCollections.observableArrayList(result);
			System.out.println("showRoomsByNumb");
			tableView.setItems(data);
    		
			tableView.setRowFactory(new Callback<TableView<Room>, TableRow<Room>>() {
				            
							@Override
							public TableRow<Room> call(TableView<Room> TableView) {
								final TableRow<Room> row = new TableRow<>();
				                final ContextMenu contextMenu = new ContextMenu();
				                final MenuItem updateRoom = new MenuItem("Update ");
				                final MenuItem deleteRoom = new MenuItem("Delete ");
			
				                updateRoom.setOnAction(new EventHandler<ActionEvent>() {
				                    @Override
				                    public void handle(ActionEvent event) {
				                    	deplace();
				                    }
				                });
				                
				                deleteRoom.setOnAction(new EventHandler<ActionEvent>() {
			
									@Override
									public void handle(ActionEvent event) {
										Context context;
										try {
											context = new InitialContext();
											RoomServiceRemote delete = (RoomServiceRemote) context.lookup(jndi);
						            		delete.delete(tableView.getSelectionModel().getSelectedItem().getId());
						            		refresh();
						            		showRoomsByType(hotel,numb);
										} catch (NamingException e) {
											e.printStackTrace();
										}
									}
				                });
				                
				                contextMenu.getItems().add(updateRoom);
				                contextMenu.getItems().add(deleteRoom);
				                row.contextMenuProperty().bind(
				                        Bindings.when(row.emptyProperty())
				                                .then((ContextMenu) null)
				                                .otherwise(contextMenu)
				                );
				                return row;
							}
			    		});
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
    	
    }
    
    
    @FXML
    private void SelectRow(MouseEvent event) {
        if(event.getClickCount()==2){
            deplace();
        }
    }
    
    private Room deplace(){
    	
	Room room = tableView.getSelectionModel().getSelectedItem();
    	roomNumber.setText(room.getNumbRoom());
    	roomPrice.setText(room.getPrice().toString());
    	roomAvailability.setValue(room.getAvailability());
    	roomType.setValue(room.getRoomType());
    	updateRoom.setVisible(true);
        addNewRoom.setVisible(false);
    	return room ;
    }
    
    
    
    
    private void refresh(){
    	roomNumber.clear();
    	roomPrice.clear();
    	roomAvailability.setValue("");
    	roomType.setValue("");
    }
    
    
    @FXML
    void back(ActionEvent event) {
    	try {
            Stage stage = new Stage();
            Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("HotelUpdate.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            currentStage.close();
        } catch (IOException ex) {
            Logger.getLogger(RoomUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void sortByAvailability(ActionEvent event) {
    	searchRoom.clear();
    	sortType.setValue("");
    	showRoomsByAvailability(hotel, sortAvailability.getValue().toString());
    }

    @FXML
    void sortByType(ActionEvent event) {
    	sortAvailability.setValue("");
    	searchRoom.clear();
    	showRoomsByType(hotel,sortType.getValue().toString());
    }

    
    @FXML
    void Search(KeyEvent event) throws NamingException {
    	sortType.setValue("");
    	sortAvailability.setValue("");
    	showRoomsByNumb(hotel, searchRoom.getText());
    }

    boolean Exist(Room room) throws NamingException{
    	InitialContext ctx;
		ctx = new InitialContext();
		RoomServiceRemote proxy;
		proxy = (RoomServiceRemote) ctx.lookup(jndi);
		List rooms = proxy.findRoomsByHotel(hotel);
		for (Object element : rooms) {
			Room r = (Room)element ;
			if(r.getNumbRoom().equals(room.getNumbRoom())){
				return true ;
			}
		}
    	return false ;
    }
}



