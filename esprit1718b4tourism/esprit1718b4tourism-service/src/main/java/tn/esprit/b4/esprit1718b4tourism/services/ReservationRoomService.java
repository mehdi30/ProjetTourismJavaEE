package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b4.esprit1718b4tourism.entities.Hotel;
import tn.esprit.b4.esprit1718b4tourism.entities.ReservationRoom;
import tn.esprit.b4.esprit1718b4tourism.entities.ReservationRoomPk;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

@Stateless
public class ReservationRoomService implements ReservationRoomServiceLocal,ReservationRoomServiceRemote{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public ReservationRoom add(ReservationRoom resvationRoom) {
		ReservationRoomPk resevPK  = new ReservationRoomPk();
		resevPK.setIdHotel(resvationRoom.getHotel().getId());
		resevPK.setIdUser(resvationRoom.getUser().getId());
		ReservationRoom reservRoom = new ReservationRoom();
		reservRoom.setReservationRoomPk(resevPK);
		reservRoom.setArrivalDate(resvationRoom.getArrivalDate());
		reservRoom.setDepartureDate(resvationRoom.getDepartureDate());
		reservRoom.setPrice(resvationRoom.getPrice());
		reservRoom.setType(resvationRoom.getType());
		em.persist(reservRoom);
		return reservRoom;
	}

	@Override
	public void delete(int id) {
		em.remove(em.find(ReservationRoom.class, id));
		
	}

	@Override
	public ReservationRoom update(ReservationRoom resvationRoom) {
		em.merge(resvationRoom);
		return resvationRoom ;
	}

	@Override
	public ReservationRoom findById(int id) {
		return em.find(ReservationRoom.class, id);
	}

	@Override
	public List<ReservationRoom> findAllReservationRoom() {
		String jpql = "select r from ReservationRoom r ";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}
	

	@Override
	public List<ReservationRoom> findReservationByHotel(Hotel hotel) {
		String jpql = "select res from ReservationRoom res,Room r where res.ReservationRoomPk.idRoom =:u ";
		Query query = em.createQuery(jpql).setParameter("h", hotel);
		return query.getResultList();
	}

	@Override
	public List<ReservationRoom> findReservationByRoomDate(Hotel hotel, String type, Date arriv, Date Depart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReservationRoom> findReservationByUser(User user) {
		String jpql = "select res from ReservationRoom res where res.ReservationRoomPk.idUser =:u ";
		Query query = em.createQuery(jpql).setParameter("u", user);
		return query.getResultList();
	}
	
	
}
