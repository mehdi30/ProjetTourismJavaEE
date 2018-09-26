package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.persistence.TypedQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.b4.esprit1718b4tourism.entities.Hotel;
import tn.esprit.b4.esprit1718b4tourism.entities.Room;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

@Stateless
public class RoomService implements RoomServiceLocal,RoomServiceRemote{
	@PersistenceContext
	private EntityManager em;
	
	

	@Override
	public Room add(Room room) {
		em.persist(room);
		return room;
	}

	@Override
	public void delete(int id) {
		em.remove(em.find(Room.class, id));
	}

	@Override
	public Room update(Room room) {
		em.merge(room);
		return room ;
	}

	@Override
	public Room findById(int id) {
		return em.find(Room.class, id);
	}


	@SuppressWarnings("unchecked")
	@Override
	public  List<Room> findRoomsByHotel(Hotel hotel) {
		String jpql = "select r from Room r where r.hotel =:h";
		Query query = em.createQuery(jpql).setParameter("h", hotel);
		return query.getResultList();
	}
	
	
	
	@Override
	public List<Room> findByType(Hotel hotel, String type) {
		String jpql = "select r from Room r where r.roomType =:t and r.hotel =:h";
		Query query = em.createQuery(jpql).setParameter("h", hotel).setParameter("t", type);
		return  query.getResultList();
	}

	@Override
	public List<Room> findByAvailability(Hotel hotel, String availability) {
		String jpql = "select r from Room r where r.availability =:t and r.hotel =:h";
		Query query = em.createQuery(jpql).setParameter("h", hotel).setParameter("t", availability);
		return  query.getResultList();
	}
	
	
	@Override
	public List<Room> findAll() {
		String jpql = "select r from Room r ";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public List<Room> searchRoom(Hotel hotel, String num) {
		return em.createQuery("SELECT r FROM Room r WHERE r.hotel =:h and r.numbRoom LIKE  '%' || :num || '%' ", Room.class).setParameter("h", hotel).setParameter("num",num).getResultList(); 
		
	}

}
