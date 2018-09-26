package tn.esprit.b4.esprit1718b4tourism.app.client.businessDelegate;
//

import java.util.List;

import tn.esprit.b4.esprit1718b4tourism.app.client.ServiceLocator.ServiceLocator;
import tn.esprit.b4.esprit1718b4tourism.entities.Restaurant;
import tn.esprit.b4.esprit1718b4tourism.entities.User;
import tn.esprit.b4.esprit1718b4tourism.services.RestaurantServiceRemote;
import tn.esprit.b4.esprit1718b4tourism.services.UserServiceRemote;

public class RestaurantServiceDelegate {
	
	private static String jndiName ="esprit1718b4tourism-ear/esprit1718b4tourism-service/RestaurantService!"
            + "tn.esprit.b4.esprit1718b4tourism.services.RestaurantServiceRemote";

	public static RestaurantServiceRemote getProxy(){
		return (RestaurantServiceRemote)ServiceLocator.getInstance().getRemoteProxy(jndiName);
	}
	
	  //RestaurantServiceDelegate
	public static int create(Restaurant restaurant) {
		return getProxy().create(restaurant); 
	
	}
	public static void removeById(int id){
		getProxy().removeById(id);
	}
	
	public static void updateRestaurant(Restaurant restaurant){
		 getProxy().updateRestaurant(restaurant);
	}
	
	public static List<Restaurant> showAllRestaurants(){
		return getProxy().showAllRestaurants();
	}
	
	public static List<Restaurant> findAllRestaurants(Integer prixmin, Integer prixmax, String country,String town) {
	      return getProxy().findAllRestaurants(prixmin,prixmax,country,town);
	}
	
	public static List<Restaurant> showMyRestaurants(Integer id_user) {
	      return getProxy().showMyRestaurants(id_user);
	}
	
	
	
	
}
