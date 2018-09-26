package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Expression;

import tn.esprit.b4.esprit1718b4tourism.entities.Event;
import tn.esprit.b4.esprit1718b4tourism.entities.EventReservation;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

@Stateless
public class EventService implements EventServiceLocal,EventServiceRemote {

	@PersistenceContext
	private EntityManager em;
	
	public EventService()
	{
		
	}
	
	@Override
	public int addEvent(Event event)
	{
		em.persist(event);
		return event.getId();
	}
	
	@Override
	public void DeleteEvent(int id)
	{
		
		/*em.remove(em.find(Event.class, idEvent));
		return idEvent;*/
		
		//em.remove(em.find(Event.class, id));
		em.remove(em.merge(em.find(Event.class, id)));
		

		
	}
	


	@Override
	public List<Event> findAllEvents() {
		String jpql = "SELECT com FROM Event com";
		Query query = em.createQuery(jpql);
		return query.getResultList();
//
	}
	
	
	@Override
	public List<Event> findEventByTitle(String nom) {
		Query query = em.createQuery("select c from Event c where c.nom=:tit");
		query.setParameter("tit", nom);
		
		return query.getResultList();


	}
	
	@Override
	public List<Event> findAllEventsNew(int idUser) {
		Query query = em.createQuery("SELECT com FROM Event com where com.idUser=:tit");
		query.setParameter("tit", idUser);
		return query.getResultList();
//
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> showAllExperiencesbyUser(int idUser) {
		String jpql = "SELECT z FROM Event z WHERE z.idUser=:idUser";
		Query query = em.createQuery(jpql);
		query.setParameter("idUser", idUser);
		return query.getResultList();

	}
	
	
	@Override
	public List<Event> showall() {
		return em.createQuery("select e from Event e").getResultList();

	}

	
	public void MettreAjourPlace(String place, int idEvent)
	{
		
		Event emp= em.find(Event.class, idEvent);
		emp.setDescription(place);
	}
	
	@Override
	public void DeleteeEvent(Event event) {

		em.remove(em.merge(event));
	}

	@Override
	public void UpdateEvent(Event event) {

		em.merge(event);
	
	}


	@Override
	public Event findEventById(int id) {
		return em.find(Event.class, id);
	}
/*
	@Override
 public Event getEventByName(String nom)
 {
	 TypedQuery<Event> query= em.createQuery("SELECT c FROM Event c WHERE c.nom = :nom",Event.class);
	 return query.setParameter(nom).getSingleResult();
	 
 }*/
	
	

	@Override
	public void UpdatePlaces(int nb, int idEvent)
	{
		Event event = em.find(Event.class, idEvent);
		event.setNbplaces(nb);
	}
	
	@Override
	public void UpdatePlacesorigparti(int nb,int nbb, int idEvent)
	{
		Event event = em.find(Event.class, idEvent);
		event.setNbplaces(nb);
		event.setNbparticipant(nbb);
	}
	
	@Override
	public void UpdatePlacesDelete(int nb, int idEvent)
	{
		Event event = em.find(Event.class, idEvent);
		int nbancien= event.getNbplaces();
		int nbb= event.getNbparticipant();
		event.setNbplaces(nb+nbancien);
		event.setNbparticipant(nbb -nb);
		
	}
	
	
	
	
	
	
	@Override
	public int deleteEve(int id ) {
		try{
			em.remove(em.find(Event.class, id));
		}catch(Exception e) {
			System.out.println("Delete problem");
			return 0;	
		}
		return 1;
	}

		
	
	@Override
	public void Participer(Event event, User C) {
		int evplus = event.getNbparticipant();
		event.setNbparticipant(evplus + 1);
		em.merge(event);

	}
	
	@Override
	public int Promoo(int event) {
		Event ev = em.find(Event.class, event);
		int price = ev.getPrice();
		int evplus = ev.getNbparticipant();
		int nbplaces= ev.getNbplaces();
		if(evplus >= nbplaces/2)
		{
		ev.setPrice(price/2);
		em.merge(ev);
		
		}
return 1;
	}

	
	@Override
	public int Participation(int idevent, int nb) {
		Event event = em.find(Event.class, idevent);
		int ancien=event.getNbparticipant();
		event.setNbparticipant(nb+ancien);
		return 1;

	}

	
	@Override
	public List<Event> findContentByTitle(String nom) {
		Query query = em.createQuery("select c from Event c where c.nom=:tit");
		query.setParameter("tit", nom);
		
		return query.getResultList();

		// query.setParameter("cat", category);
		// return query;

	}
	
	public List<Event> findContentByType(String typeEvent) {
		Query query = em.createQuery("select c from Event c where c.TypeEvent=:tit");
		query.setParameter("tit", typeEvent);
		
		return query.getResultList();

		// query.setParameter("cat", category);
		// return query;

	}
	/*@Override
	public List<Event> FindEventByPriceDate(int prixmin,int prixmax, String datemin,String datemax ) {
		return em.createQuery("SELECT t FROM Event t WHERE t.price BETWEEN :prixmin AND :prixmax AND t.dateEvent BETWEEN :datemin AND :datemax", Event.class)
				.setParameter(1,prixmin)
				.setParameter(2,prixmax)
				.setParameter(3,datemin)
				.setParameter(4,datemax)
				.getResultList();

	}*/
	
	public List<Event> findAllEvents(Date startDate, Date endDate) {    
		  List<Event> allEvents = em.createQuery(
		    "SELECT e FROM Event e WHERE e.dateEvent BETWEEN :startDate AND :endDate")  
		  .setParameter("startDate", startDate, TemporalType.DATE)  
		  .setParameter("endDate", endDate, TemporalType.DATE)  
		  .getResultList();
		  return allEvents ;  
		}
	
	public List<Event> findAllEventsDate(String startDate, String endDate) {    
		  List<Event> allEvents = em.createQuery(
		    "SELECT e FROM Event e WHERE e.dateEvent BETWEEN :startDate AND :endDate")  
		  .setParameter("startDate", startDate)  
		  .setParameter("endDate", endDate)  
		  .getResultList();
		  return allEvents ;  
		}
	
	
	
	public List<Event> findAllEventsByprice(Integer prixmin, Integer prixmax) {    
		  List<Event> allEvents = em.createQuery(
		    "SELECT e FROM Event e WHERE e.price BETWEEN :prixmin AND :prixmax")  
		  .setParameter("prixmin", prixmin)  
		  .setParameter("prixmax",prixmax )  
		  .getResultList();
		  return allEvents ;  
		}
	

	@Override
	public List<Event> sortEventByDateDesc() {
		return em.createQuery("SELECT t FROM Event t ORDER BY t.dateEvent ASC", Event.class).getResultList();

	}

	@Override
	public void Promo(Event event) {
		// TODO Auto-generated method stub
		
	}

	

	  @Override
		public List<Event> advancedsearchTopic(String nom) {
			List<Event> list = new ArrayList<Event>();
			 return list=em.createQuery("SELECT t FROM Event t WHERE t.nom LIKE  '%' || :nom || '%' ", Event.class).setParameter("nom",nom).getResultList(); 
			 
			 }

	  @Override
		public void DeleteeEventToday(Date date) {
		 
		  //Expression<javax.sql.Date> date = cb.currentDate();
		  Query query = em.createQuery("DELETE FROM Event c WHERE c.dateEvent =:date");
			 query.setParameter("date",new java.util.Date(),TemporalType.DATE ).executeUpdate();
		}
	  
	 /* @Override
		public void DeleteeEventExpired() {
		 
		  //Expression<javax.sql.Date> date = cb.currentDate();
		  Query query = em.createQuery("DELETE FROM Event t WHERE t.dateEvent < CURRENT_DATE");
			 query.setParameter("date",new java.util.Date(),TemporalType.DATE ).executeUpdate();
		}
	*/
	  //Query q = em.createQuery("DELETE FROM Statustable t WHERE t.ts < CURRENT_TIMESTAMP");
	  @Override
		public List<User> findUserById(int id) {
			return em.createQuery("SELECT c FROM User c WHERE c.id=?1" ,User.class)
					.setParameter(1,id)
					.getResultList(); 
		}
	  
	  @SuppressWarnings("unchecked")
	@Override
		public List<Event> findWithNamePriceAdress(String name, Integer Price, String address) {
			return em.createQuery(
				    "SELECT c FROM Event c WHERE c.nom LIKE :nom AND c.price LIKE :price AND c.adresse LIKE :adresse")
					 
				 .setParameter("nom", name) 
				 .setParameter("price",Price )
				 .setParameter("adresse",address) 

				    .getResultList();	}
	  
	  
	}
	
	

