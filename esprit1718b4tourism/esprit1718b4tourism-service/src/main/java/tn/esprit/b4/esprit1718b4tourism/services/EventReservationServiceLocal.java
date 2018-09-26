package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4tourism.entities.Event;
import tn.esprit.b4.esprit1718b4tourism.entities.EventReservation;
import tn.esprit.b4.esprit1718b4tourism.entities.User;
@Local
public interface EventReservationServiceLocal {

	void deleteReservation( EventReservation ER);
	List<EventReservation> findAllReservation();
	void ReserverEvent( User u, Event com, int nbplaces);
	void AddReservationEvent( int idUsr, int idEvent, int nbplaces);
	List<Event> findAllById(int idUser);
	int findRatingByUserContent(User user, Event event);
	
	List<EventReservation> findReservationByUser(User content);
	List<EventReservation> findReservationByIdUser(int idUser);
	
	
	List<EventReservation> findContractsbyCompanybyID(int idcom);
	List<EventReservation> findReservationByIdEvent(int idevent);
	void UpdateMyReser( EventReservation ER, int nb);
	List<EventReservation> CheckEvent(int idevent, int idUser);
	List<EventReservation> CheckExpAndUser(int idExp,int user);
	void AddReservationEventNew( int idUsr, int idEvent, int nbplaces,String nom);
}
