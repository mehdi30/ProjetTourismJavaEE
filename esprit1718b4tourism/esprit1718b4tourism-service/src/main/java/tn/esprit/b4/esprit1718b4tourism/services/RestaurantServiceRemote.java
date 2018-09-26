package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Remote;


import tn.esprit.b4.esprit1718b4tourism.entities.Restaurant;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

@Remote
public interface RestaurantServiceRemote {

int create(Restaurant restaurant);
	
	void removeById(int id);
	void updateRestaurant(Restaurant restaurant);
	List<Restaurant> findAllRestaurants(Integer prixmin, Integer prixmax, String country,String town) ;
	List<Restaurant> showAllRestaurants();

	List<Restaurant> showMyRestaurants(Integer id_user);
	
	
}
