package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

import tn.esprit.b4.esprit1718b4tourism.entities.Event;
import tn.esprit.b4.esprit1718b4tourism.entities.EventReservation;
import tn.esprit.b4.esprit1718b4tourism.entities.User;
@Remote
public interface EventServiceRemote {

	

	int addEvent(Event event);
	void DeleteEvent(int id);
	List <Event> findAllEvents();
	void MettreAjourPlace(String place, int idEvent);
	List<Event> showall() ;
	void DeleteeEvent(Event event);
	void UpdateEvent(Event event);
	Event findEventById(int id);
	List<Event> findEventByTitle(String nom);
	void UpdatePlaces(int nb, int idEvent);
	int deleteEve(int id );
	void Participer(Event event, User C);
	int Participation(int idevent, int nb);
	List<Event> findContentByTitle(String nom);
	List<Event> findContentByType(String typeEvent);
	//List<Event> FindEventByPriceDate(int prixmin,int prixmax, String datemin,String datemax ) ;
	List<Event> findAllEvents(Date startDate, Date endDate);
	List<Event> findAllEventsByprice(Integer prixmin, Integer prixmax) ;
	List<Event> sortEventByDateDesc();
	void Promo(Event event);
	int Promoo(int event);
	void UpdatePlacesDelete(int nb, int idEvent);
	List<Event> findAllEventsDate(String startDate, String endDate);
	void UpdatePlacesorigparti(int nb,int nbb, int idEvent);
	List<Event> advancedsearchTopic(String nom);
	void DeleteeEventToday(Date date);
	List<User> findUserById(int id);
	List<Event> findAllEventsNew(int idUser);
	List<Event> showAllExperiencesbyUser(int idUser);
	List<Event> findWithNamePriceAdress(String name, Integer Price, String address);

	
}
