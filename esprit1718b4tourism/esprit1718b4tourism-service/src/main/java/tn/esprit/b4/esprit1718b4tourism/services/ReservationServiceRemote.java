package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4tourism.entities.Reservation;


@Remote
public interface ReservationServiceRemote {
	
	int create(Reservation reservation);
	
	void removeById(int id);
	
	void modifyById(int id, Reservation reservation);
	
	List<Reservation> findAll();
	
}
