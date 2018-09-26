//package tn.esprit.b4.esprit1718b4tourism.app.client.gui;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import javax.naming.NamingException;
//
//import com.jfoenix.controls.JFXListView;
//import com.jfoenix.controls.JFXTextField;
//import com.lynden.gmapsfx.GoogleMapView;
//import com.lynden.gmapsfx.javascript.object.GoogleMap;
//import com.lynden.gmapsfx.service.geocoding.GeocodingService;
//
//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//public class HotelController  implements Initializable {
//
//    
//
//    @FXML
//    private Label as_user_name;
//
//    @FXML
//    private GoogleMapView mapview;
//
//    @FXML
//    private JFXTextField search;
//
//    @FXML
//    private JFXListView<String> listeView;
//
//    @FXML
//    private Label nameHotel;
//
//    @FXML
//    private ImageView imgHotel;
//
//    @FXML
//    private Label star;
//
//    @FXML
//    private Label town;
//
//    @FXML
//    private Label country;
//
//    @FXML
//    private Label address;
//
//    @FXML
//    private Label email;
//
//    @FXML
//    private Label tel;
//
//    @FXML
//    private Label wifi;
//
//    @FXML
//    private Label parking;
//
//    private GoogleMap map;
//    
//    private GeocodingService geocodingService;
//    
//    
//    
////    @FXML
////    void AdminHotel(ActionEvent event) throws IOException{
////    	try {
////    		Parent home_page_parent=FXMLLoader.load(getClass().getResource("HotelUpdate.fxml"));
////            Scene home_page_scene=new Scene(home_page_parent);
////            Stage app_stage=(Stage)((Node) event.getSource()).getScene().getWindow();
////            app_stage.setScene(home_page_scene);
////            app_stage.show();
////        } catch (IOException ex) {
////            Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
////        }
////    }
//
//    @FXML
//    void booking(ActionEvent event) {
//
//    }
//
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		ObservableList<String> Liste = FXCollections.observableArrayList("Ariana","Ben Arous","Béja","Bizerte","Gabès","Gafsa","Jendouba","Kairouan","Kasserine","Kébili","Le Kef","Mahdia","Manouba","Médenine","Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse","Tataouine","Tozeur","Tunis","Zaghouan");
//	       listeView.setItems(Liste) ;
//		
//	}
//	
//}
//

package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4tourism.entities.Hotel;
import tn.esprit.b4.esprit1718b4tourism.services.HotelServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.RoomServiceRemote;
import tray.notification.TrayNotification;

public class HotelController implements Initializable, MapComponentInitializedListener  {
	TrayNotification Notification = new TrayNotification();
	String  jndi ="esprit1718b4tourism-ear/esprit1718b4tourism-service/HotelService!tn.esprit.b4.esprit1718b4tourism.services.HotelServiceRemote";


	public static Integer hotelId ;
	public static Hotel hotelSelected ;
	private GoogleMap map;
    
    private GeocodingService geocodingService;

	private List result;
	
	private String hotelName ;
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GoogleMapView mapview;

    @FXML
    private JFXTextField search;

    @FXML
    private JFXListView<String> listeView;

    @FXML
    private Label as_user_name;

    @FXML
    private Label nameHotel;

    @FXML
    private ImageView imgHotel;

    @FXML
    private Label star;

    @FXML
    private Label town;

    @FXML
    private Label country;

    @FXML
    private Label address;

    @FXML
    private Label email;

    @FXML
    private Label tel;

    @FXML
    private Label wifi;

    @FXML
    private Label parking;

    @FXML
    void AdminHotel(ActionEvent event)throws IOException {
		try {
			Parent home_page_parent=FXMLLoader.load(getClass().getResource("HotelUpdate.fxml"));
		    Scene home_page_scene=new Scene(home_page_parent);
		    Stage app_stage=(Stage)((Node) event.getSource()).getScene().getWindow();
		    app_stage.setScene(home_page_scene);
		    app_stage.show();
		} catch (IOException ex) {
		    Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
		}
    }

    @FXML
    void booking(ActionEvent event) throws IOException{
    	try {
			Parent home_page_parent=FXMLLoader.load(getClass().getResource("ReservationRoom.fxml"));
		    Scene home_page_scene=new Scene(home_page_parent);
		    Stage app_stage=(Stage)((Node) event.getSource()).getScene().getWindow();
		    app_stage.setScene(home_page_scene);
		    app_stage.show();
		} catch (IOException ex) {
		    Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> HotelsList = FXCollections.observableArrayList();
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			HotelServiceRemote proxy;
			proxy = (HotelServiceRemote) ctx.lookup(jndi);
			result = proxy.findAll();
			for (Object element : result) {
				Hotel hotel = (Hotel)element ;
				HotelsList.add(hotel.getName());
				System.out.println("hotel");
				System.out.println(hotel.getName());
			}
			//data = allHotelName();
			//ObservableList Hotels = AllHotels();
			listeView.setItems(HotelsList);
			mapview.addMapInializedListener(this); 
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	
//	public ObservableList<String> AllHotels() throws NamingException{
//		ObservableList<String> HotelsList = FXCollections.observableArrayList();
//		
//		try {
//			List result;
//			for (Object element : result) {
//				Hotel hotel = (Hotel)element ;
////				data.add(hotel);
////				data.add(((Hotel) hotel).getId(),((Hotel) hotel).getName(),((Hotel) hotel).getCountry(),((Hotel) hotel).getTown(),((Hotel) hotel).getAddress(),((Hotel) hotel).getEmail(),((Hotel) hotel).getStar(),
////						((Hotel) hotel).getWifi(),((Hotel) hotel).getParking());
//				HotelsList.add(hotel.getName());
//				System.out.println("hotel");
//				System.out.println(hotel.getName());
//			}
//			return HotelsList ;
//					
//		} catch (NamingException e1) {
//			e1.printStackTrace();
//		}
//		return null;
//	}
	
	
	
//    public ObservableList<String> allHotelName() throws NamingException{
//    	InitialContext ctx;
//    	ObservableList<String> data ; 
//		try {
//			ctx = new InitialContext();
//			HotelServiceRemote proxy;
//			proxy = (HotelServiceRemote) ctx.lookup(jndi);
//			return data = FXCollections.observableArrayList(proxy.findAllName());
//			
//		} catch (NamingException e1) {
//			e1.printStackTrace();
//		}
//		return null;
//    }


    
    public Hotel HotelByName(String hotelName) throws NamingException {
    	for (Object element : result) {
			Hotel hotel = (Hotel)element ;
			if (hotel.getName().equals(hotelName)){
				Hotel result = new Hotel(hotel.getName(),hotel.getCountry(),hotel.getTown(),hotel.getAddress(),hotel.getEmail(),hotel.getStar(),
						hotel.getWifi(),hotel.getParking());
				result.setId(hotel.getId());
				hotelId = hotel.getId() ;
				hotelSelected = hotel ;
				System.out.println("result");
				System.out.println(result);
				return result ;
			}
		}
    	
		return null;
    	
    }
    
    @FXML
    void SearchHotel(KeyEvent event) throws NamingException{
    	InitialContext ctx;
		try {
			ObservableList<String> HotelsList = FXCollections.observableArrayList();
			ObservableList<String> HotelsListSearch = FXCollections.observableArrayList();
			ctx = new InitialContext();
			HotelServiceRemote proxy;
			proxy = (HotelServiceRemote) ctx.lookup(jndi);
			result = proxy.searchByManyCriteria(search.getText());
			for (Object element : result) {
				Hotel hotel = (Hotel)element ;
				HotelsList.add(hotel.getName());
//				for (Object Hotel2 : HotelsList) {
//					Hotel hotel1 = (Hotel)element ;
//					if(!hotel.equals(Hotel2)){
//						
//						HotelsListSearch.add(hotel.getName());
//					}
//				}
			}
			listeView.setItems(HotelsList);
			mapview.addMapInializedListener(this); 
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }
    
	@Override
	public void mapInitialized() {
		geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(40.4557677,-7.643627))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(7);

        map = mapview.createMap(mapOptions);
        
    
        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position( new LatLong(41.14961,-8.61099) )
                .visible(Boolean.TRUE)
                .title("My Marker");

        Marker marker = new Marker( markerOptions );

        map.addMarker(marker);
	}
	
	@FXML
    void getHotel(MouseEvent event) throws NamingException {
		hotelName = listeView.getSelectionModel().getSelectedItem();
		 
		Hotel hotel = HotelByName(hotelName);
		System.out.println("hotel");
		System.out.println(hotel.toString());
		System.out.println(hotel.getId() +" "+ hotel.getName() +" "+ hotel.getAddress() +" "+  hotel.getCountry() +" "+  hotel.getTown() +" "+ hotel.getEmail() +" "+ hotel.getStar() +" "+ hotel.getParking() +" "+ hotel.getWifi());
		String placeSearch =hotel.getName()+" "+ hotel.getTown()+" "+hotel.getAddress();
		System.out.println("placeSearch");
		System.out.println(placeSearch);
		star.setText(hotel.getStar());
		town.setText(hotel.getTown());
		country.setText(hotel.getCountry());
		nameHotel.setText(hotel.getName());
		address.setText(hotel.getAddress());
		email.setText(hotel.getEmail());
		parking.setText(hotel.getParking());
		wifi.setText(hotel.getWifi());
		
		
        geocodingService.geocode(placeSearch, (GeocodingResult[] results, GeocoderStatus status) -> {
        	
            LatLong latLong = null;
            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                map.clearMarkers();
                MapOptions mapOptions = new MapOptions();
                mapOptions.center(latLong)
	                        .mapType(MapTypeIdEnum.ROADMAP)
	                        .overviewMapControl(false)
	                        .panControl(false)
	                        .rotateControl(false)
	                        .scaleControl(false)
	                        .streetViewControl(false)
	                        .zoomControl(false)
	                        .zoom(9);
                map = mapview.createMap(mapOptions);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position( latLong )
                            .visible(Boolean.TRUE)
                            .title("My Marker");
                Marker marker = new Marker( markerOptions );

                map.addMarker(marker);
            }
            map.setCenter(latLong);
        });
    }  
	
	@FXML
    void MyReservations(ActionEvent event) throws IOException {
		try {
			Parent home_page_parent=FXMLLoader.load(getClass().getResource("MyReservations.fxml"));
		    Scene home_page_scene=new Scene(home_page_parent);
		    Stage app_stage=(Stage)((Node) event.getSource()).getScene().getWindow();
		    app_stage.setScene(home_page_scene);
		    app_stage.show();
		} catch (IOException ex) {
		    Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
	
	@FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
}
