package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4tourism.entities.Hotel;
import tn.esprit.b4.esprit1718b4tourism.entities.ReservationRoom;
import tn.esprit.b4.esprit1718b4tourism.entities.Room;
import tn.esprit.b4.esprit1718b4tourism.entities.User;
import tn.esprit.b4.esprit1718b4tourism.services.ReservationRoomService;
import tn.esprit.b4.esprit1718b4tourism.services.ReservationRoomServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.RoomServiceRemote;
import tray.notification.TrayNotification;

public class ReservationRoomController implements Initializable,Serializable{
	
	TrayNotification Notification = new TrayNotification();
	String  jndiReservation ="esprit1718b4tourism-ear/esprit1718b4tourism-service/ReservationRoomService!tn.esprit.b4.esprit1718b4tourism.services.ReservationRoomServiceRemote";
	String  jndiRoom ="esprit1718b4tourism-ear/esprit1718b4tourism-service/RoomService!tn.esprit.b4.esprit1718b4tourism.services.RoomServiceRemote";
	
	

	ObservableList<String> roomList = FXCollections.observableArrayList("Single" , "Double" , "Triple" , "Suite" );
	ObservableList<String> reservationOptionList = FXCollections.observableArrayList("All-Inclusive" , "Full-Board" ,"Half-Board");
	Room room ;
	Integer  totalPrice =0 ;
	Integer roomPrice = 0 ;
	
	public Date arrivalD,departureD ;

    @FXML
    private Label as_user_name;

    @FXML
    private Pane resultat;

    @FXML
    private ComboBox<String> typeRoom;

    @FXML
    private ComboBox<String> reservationOption;

    @FXML
    private JFXDatePicker arrivalDate;

    @FXML
    private JFXDatePicker departureDate;

    @FXML
    private Label calculate;

    @FXML
    private JFXButton back;
    


	@SuppressWarnings("deprecation")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		typeRoom.setItems(roomList);
		reservationOption.setItems(reservationOptionList);
		Calendar calendar = Calendar.getInstance();
        LocalDate dateNow = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        
        arrivalDate.setDayCellFactory((p) -> new DateCell() {
            @Override
            public void updateItem(LocalDate ld, boolean bln) {
                super.updateItem(ld, bln);
                setDisable(ld.isBefore(dateNow)); 
            }
        });
        
        Platform.runLater(() -> {
        	arrivalDate.getEditor().clear();
        });
        
		

        departureDate.setDayCellFactory((p) -> new DateCell() {
            @Override
            public void updateItem(LocalDate ld, boolean bln) {
                super.updateItem(ld, bln);
                setDisable(ld.isBefore(arrivalDate.getValue()));
            }
        });
        Platform.runLater(() -> {
        	departureDate.getEditor().clear();
        });
    }

	@FXML
	void TotalPrice(ActionEvent event) throws NamingException{
		List<Room> roomByHotel ;
//		List<Room> roomByHotelType ;
		
		if(!Empty()){
			long date1 = arrivalDate.getValue().toEpochDay();
			long date2 = departureDate.getValue().toEpochDay();
			long days = Math.abs(date1 - date2);
			
			System.out.println("days");
			System.out.println(days);
			try {
				InitialContext ctx ;
				ctx = new InitialContext();
				RoomServiceRemote proxy;
				proxy = (RoomServiceRemote) ctx.lookup(jndiRoom);
				System.out.println(typeRoom.getValue().toString());
				System.out.println(HotelController.hotelId);
				System.out.println("srfgxdfgxdfgdxfxdgfxdfgdxfgxdf");
				List rooms = proxy.findByType(HotelController.hotelSelected, typeRoom.getValue().toString());
//				roomByHotel = proxy.findRoomsByHotel(HotelController.hotelSelected) ;
				System.out.println("je suis ici") ;
				
				if(!rooms.isEmpty()){
					room =  (Room) rooms.get(0);
					System.out.println("room");
					System.out.println(room);
					System.out.println(room.getPrice());
					if(typeRoom.getValue().toString() != null ) {
						if(reservationOption.getValue().toString().equals("Half-Board")){
							roomPrice = (room.getPrice() + ((room.getPrice()*2)/100));
						}else if(reservationOption.getValue().toString().equals("Full-Board")){
							roomPrice = (room.getPrice() + ((room.getPrice()*35)/100));
						}else if(reservationOption.getValue().toString().equals("All-Inclusive")){
							roomPrice = (room.getPrice() + ((room.getPrice()*50)/100));
						}else{
							Alerts("Which Catering Service Do You Choose ?");
						} 
						totalPrice =  (int) (roomPrice * days)  ;
						String totalPriceString = String.valueOf(totalPrice);
						calculate.setText(totalPriceString + "£"); 
					}else {
						Alerts("Which Room Do You Choose ?");
					}
				}else{
					Alerts("Sorry, we don't have this Room");
					calculate.setText("");
				}
			} catch (NamingException e) {
				e.printStackTrace();
			}	
		}else{
			Alerts("You must verify cases");
			calculate.setText("");
		}
			
	}
	
	@FXML
    void booking(ActionEvent event) {
			Date date1 = new Date(arrivalDate.getValue().getYear() - 1900, arrivalDate.getValue().getMonthValue() - 1,
					arrivalDate.getValue().getDayOfMonth()); 
			Date date2 = new Date(departureDate.getValue().getYear() - 1900, departureDate.getValue().getMonthValue() - 1,
					departureDate.getValue().getDayOfMonth());
			ReservationRoom newReservation = new ReservationRoom(date1,date2,typeRoom.getValue().toString(),totalPrice);
			newReservation.setHotel(HotelController.hotelSelected) ;
//			newReservation.setUser(StaticVars.currentUser);
			System.out.println(newReservation.getType());
			System.out.println(newReservation.getArrivalDate());
			System.out.println(newReservation.getDepartureDate());
			System.out.println(newReservation.getHotel().getId());
			newReservation.setUser(StaticVars.getCurrentUser());
			
			
		try {
			InitialContext ctxx ;
			ctxx = new InitialContext();
			ReservationRoomServiceRemote proxy1;
			proxy1 = (ReservationRoomServiceRemote) ctxx.lookup(jndiReservation);
			System.out.println("cnnnnnnnnnnnnnxxxx");
			proxy1.add(newReservation);
			goback(event);
		} catch (NamingException e) {
			e.printStackTrace();
		}		
			
    	
    	
    }
	
	public void Alerts(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
	
	public boolean Empty(){
		String type = typeRoom.getValue().toString() ;
		String service = reservationOption.getValue().toString() ;
		if( (arrivalDate.getValue() == null)  || (departureDate.getValue() == null) || typeRoom.getValue().equals("") || reservationOption.getValue().equals("")){
			return true ;
		}else {
			return false;
		}
	}
	
	@FXML
    void goback(ActionEvent event) {
		try {
			Parent home_page_parent=FXMLLoader.load(getClass().getResource("Hotel.fxml"));
		    Scene home_page_scene=new Scene(home_page_parent);
		    Stage app_stage=(Stage)((Node) event.getSource()).getScene().getWindow();
		    app_stage.setScene(home_page_scene);
		    app_stage.show();
		} catch (IOException ex) {
		    Logger.getLogger(ReservationRoomController.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
}

