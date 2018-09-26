package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4tourism.entities.Restaurant;

@Local
public interface RestaurantServiceLocal {

int create(Restaurant restaurant);
	
	void removeById(int id);

	void updateRestaurant(Restaurant restaurant);
	List<Restaurant> findAllRestaurants(Integer prixmin, Integer prixmax, String country,String town) ;
	List<Restaurant> showAllRestaurants();
	
	List<Restaurant> showMyRestaurants(Integer id_user);

}
