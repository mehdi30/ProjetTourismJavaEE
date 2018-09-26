package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4tourism.entities.BookingExperience;
import tn.esprit.b4.esprit1718b4tourism.entities.Event;
import tn.esprit.b4.esprit1718b4tourism.entities.EventReservation;
import tn.esprit.b4.esprit1718b4tourism.entities.ReservationEventPK;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

@Stateless
public class EventReservationService  implements EventReservationServiceRemote,EventReservationServiceLocal{

	@PersistenceContext
	private EntityManager em;
	
	public EventReservationService()
	{
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EventReservation> CheckEvent(int idevent, int idUser) {
		String jpql = "SELECT z FROM EventReservation z WHERE z.reservationeventPK.idevent=:param AND z.reservationeventPK.idUser=:param";
		Query query = em.createQuery(jpql);
		query.setParameter("param", idevent);
		query.setParameter("param", idUser);
		return query.getResultList();
	}
	
	
	
	@Override
	public void deleteReservation( EventReservation ER) {
		
		
		em.remove(em.merge(ER));
	}
	

	@Override
	public void UpdateMyReser( EventReservation ER, int nb) {
		Event ee= ER.getEvent();
		ER.setNbplaces(nb);
		em.merge(ER);
	}
	

	

	@Override
	public List<EventReservation> findAllReservation() {
		String jpql = "SELECT res FROM EventReservation res";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public void ReserverEvent( User u, Event com, int nbplaces) {
		
		
		   EventReservation EV = new EventReservation(u,com,nbplaces);
		    em.persist(EV);		
	}

	
	@Override
	public void AddReservationEvent( int idUsr, int idEvent, int nbplaces) {
		
		ReservationEventPK ev = new ReservationEventPK();
		ev.setIdevent(idEvent);
		ev.setIdUser(idUsr);
		
		EventReservation eve = new EventReservation();
		eve.setReservationeventPK(ev);
		eve.setNbplaces(nbplaces);
		
		//Event e = new Event();
		//e.setNbplaces(nbplaces--);
		em.persist(eve);
		
			
	}
	
	@Override
	public void AddReservationEventNew( int idUsr, int idEvent, int nbplaces,String nom) {
		
		ReservationEventPK ev = new ReservationEventPK();
		ev.setIdevent(idEvent);
		ev.setIdUser(idUsr);
		
		EventReservation eve = new EventReservation();
		eve.setReservationeventPK(ev);
		eve.setNbplaces(nbplaces);
		eve.setNomevent(nom);
		
		//Event e = new Event();
		//e.setNbplaces(nbplaces--);
		em.persist(eve);
		
			
	}
	
	
	
	/*
	@Override
	public void UpdateReservationEvent(int idUser, int idEvent, int nb)
	{
		EventReservation event = em.find(EventReservation.class, idEvent);
		ReservationEventPK ev = new ReservationEventPK();
	     ev.setIdevent(idEvent);
	ev.setIdUser(idUsr);
	
	EventReservation eve = new EventReservation();
	eve.setReservationeventPK(ev);
	eve.setNbplaces(nbplaces);
	//Event e = new Event();
	//e.setNbplaces(nbplaces--);
	em.persist(eve);
	}*/
	
	@Override
	public List<Event> findAllById(int idUser)
	{
		TypedQuery<Event> query = em.createQuery("select DISTINCT m from Event m "
				+ "join m.listReservations t join t.user u where u.id=:idUser",Event.class);
		query.setParameter("idUser", idUser);
		return query.getResultList();
		
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EventReservation> findReservationByUser(User content) {
		Query query=em.createQuery("Select r FROM EventReservation r WHERE r.reservationeventPK.idUser=?1", EventReservation.class).setParameter("1", content.getId());
		List<EventReservation> ratings=query.getResultList();
		return ratings;
	}



	@Override
	public List<EventReservation> findReservationByIdUser(int idUser) {
		Query query=em.createQuery("Select r FROM EventReservation r WHERE r.reservationeventPK.idUser =:idUser", EventReservation.class)
				.setParameter("idUser",idUser);
		List<EventReservation> ratings=query.getResultList();
		return ratings;
	}
	
	@Override
	public List<EventReservation> findReservationByIdEvent(int idevent) {
		Query query=em.createQuery("Select r FROM EventReservation r WHERE r.reservationeventPK.idevent =:idevent", EventReservation.class)
				.setParameter("idevent",idevent);
		List<EventReservation> ratings=query.getResultList();
		return ratings;
	}
	
	@Override
	public int findRatingByUserContent(User user, Event event) {
		return em.createQuery("Select r.nbplaces FROM EventReservation r WHERE r.reservationeventPK.idUser=?1 AND f.reservationeventPK.idevent=?2",Integer.class).
				setParameter(1,user.getId()).setParameter(2, event.getId()).getSingleResult();
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EventReservation> findContractsbyCompanybyID(int idcom) {
		String jpql = "SELECT z FROM EventReservation z WHERE z.reservationeventPK.idUser=:param";
		Query query = em.createQuery(jpql);
		query.setParameter("param", idcom);
		return query.getResultList();
	}

	
	
	@Override
	public List<EventReservation> CheckExpAndUser(int idExp,int user) {
		if(em.createQuery("select z from EventReservation z where z.reservationeventPK.idevent=?1 and z.reservationeventPK.idUser=?2",EventReservation.class)
				.setParameter(1, idExp).setParameter(2, user).getResultList()==null){
			 return new ArrayList<EventReservation>();
		 }
		else 
			return em.createQuery("select z from EventReservation z where z.reservationeventPK.idevent=?1 and z.reservationeventPK.idUser=?2",EventReservation.class)
					.setParameter(1, idExp).setParameter(2, user).getResultList();
	}
	
	
	
	
	
	
	}
	
	

