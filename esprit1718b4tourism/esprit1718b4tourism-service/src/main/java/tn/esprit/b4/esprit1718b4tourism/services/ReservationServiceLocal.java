package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4tourism.entities.Reservation;

@Local
public interface ReservationServiceLocal {
	

	int create(Reservation reservation);
	
	void removeById(int id);
	
	void modifyById(int id, Reservation reservation);
	
	List<Reservation> findAll();
	
	
}
