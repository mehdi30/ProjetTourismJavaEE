package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import tn.esprit.b4.esprit1718b4tourism.entities.Hotel;
import tn.esprit.b4.esprit1718b4tourism.entities.Room;

@Stateless
public class HotelService implements HotelServiceLocal,HotelServiceRemote {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Hotel add(Hotel hotel) {
		em.persist(hotel);
		return hotel;
	}

	@Override
	public void delete(int id) {
		em.remove(em.find(Hotel.class, id));
	}

	@Override
	public Hotel update(Hotel hotel) {
		em.merge(hotel);
		return hotel ;
	}

	@Override
	public Hotel findById(int id) {
		return em.find(Hotel.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Hotel> findAll() {
		String jpql = "select h from Hotel h ";
		Query query = em.createQuery(jpql);
		return query.getResultList();
		
	/*	TypedQuery<Hotel> query = (TypedQuery) em.createNamedQuery(
				"select h from Hotel h ",Hotel.class);
				ObservableList<Hotel> hotels = FXCollections.observableArrayList(query.getResultList());
				return hotels; */
	}

	@Override
	public int exist(Hotel hotel) {
		String jpql = "select h FROM Hotel h WHERE h.name LIKE hotel.name and h.address LIKE hotel.address ";
		Query query = em.createQuery(jpql);
		if (query.getResultList().isEmpty()){
			return 0;
		}else
		return 1;
	}

	@Override
	public List<String> findAllName() {
		String jpql = "select h.name from Hotel h ";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public List<Hotel> findByName(String name) {
		return em.createQuery("SELECT h FROM Hotel h WHERE h.name LIKE  '%' || :name || '%' ", Hotel.class).setParameter("name",name).getResultList(); 
		 
	}

	@Override
	public List<Hotel> searchByManyCriteria(String txt) {
		return em.createQuery("SELECT h FROM Hotel h WHERE  h.address LIKE  '%' || :num || '%' OR h.country LIKE  '%' || :num || '%' OR h.name LIKE  '%' || :num || '%' OR h.country LIKE  '%' || :num || '%' OR h.town LIKE  '%' || :num || '%' OR h.star LIKE  '%' || :num || '%'", Hotel.class).setParameter("num",txt).getResultList() ;
	}

}
