package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4tourism.entities.Restaurant;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

@Stateless
public class RestaurantService implements RestaurantServiceRemote, RestaurantServiceLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public int create(Restaurant restaurant) {
		em.persist(restaurant);
		return restaurant.getId();
	}

	@Override
	public void removeById(int id) {
		// TODO Auto-generated method stub
		em.remove(em.find(Restaurant.class, id));
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		em.merge(restaurant);
	}
	@Override
	public List<Restaurant> findAllRestaurants(Integer prixmin, Integer prixmax, String country,String town) {    
		if(country.equals("All")) country="";
		if(town.equals("All")) town="";
		
		 return em.createQuery(
				    "SELECT e FROM Restaurant e WHERE e.country like CONCAT(?1,'%') AND e.town like CONCAT(?2,'%') AND  (e.coins BETWEEN  :prixmin AND :prixmax ) ")  
				 .setParameter(1,country)
				 .setParameter(2,town)
				 .setParameter("prixmin", prixmin)  
				  .setParameter("prixmax",prixmax ) 
				  .getResultList();
		  

		}
	@Override
	public List<Restaurant> showAllRestaurants(){
		return em.createQuery("select r from Restaurant r").getResultList();

	}

	@Override
	public List<Restaurant> showMyRestaurants(Integer id_user) {
		 return em.createQuery(
				    "SELECT e FROM Restaurant e WHERE e.id_user=:id_user")  
				 .setParameter("id_user",id_user)
				  .getResultList();
		  
	}

}
