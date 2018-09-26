package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.net.Socket;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Alert;
import tn.esprit.b4.esprit1718b4tourism.entities.Restaurant;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

public class StaticVars {

	public static User currentUser;
	
	public static User selectedUser;
	
	public static Socket socket;
		
	public static byte[] voiceByte;
	
	public static int ConnectedAs=0;
	
	public static Restaurant selectedRestaurant;
	

	public static byte[] getVoiceByte() {
		return voiceByte;
	}

	public static void setVoiceByte(byte[] voiceByte) {
		StaticVars.voiceByte = voiceByte;
	}

	public static Restaurant getSelectedRestaurant() {
		return selectedRestaurant;
	}

	public static void setSelectedRestaurant(Restaurant selectedRestaurant) {
		StaticVars.selectedRestaurant = selectedRestaurant;
	}

	public static int getConnectedAs() {
		return ConnectedAs;
	}

	public static void setConnectedAs(int connectedAs) {
		ConnectedAs = connectedAs;
	}

	public static Socket getSocket() {
		return socket;
	}

	public static void setSocket(Socket socket) {
		StaticVars.socket = socket;
	}

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
		StaticVars.currentUser = currentUser;
	}

	public static User getSelectedUser() {
		return selectedUser;
	}

	public static void setSelectedUser(User selectedUse) {
		StaticVars.selectedUser = selectedUse;
	}
	
    public static void alerte(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    

    public static String VerifierLongChaine(String chaine, String champ, int nbr_carac_min, int nbr_carac_max) {
        if (chaine.length() <= nbr_carac_max && chaine.length() >= nbr_carac_min) {
            return "valide";
        }
        return "the field " + champ + " must be between " + nbr_carac_min + " and " 
        + nbr_carac_max + " characters !! \n check it again please !!";
    }
	
    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
 }
    
    public static String dateSysteme() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }
    
    public static String generatePassword(int len, String dic) {
   	 SecureRandom random = new SecureRandom();
       String result = "";
       for (int i = 0; i < len; i++) {
           int index = random.nextInt(dic.length());
           result += dic.charAt(index);
       }
       return result;
       }
    

	
  
}
