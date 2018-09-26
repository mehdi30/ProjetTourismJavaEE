package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4tourism.entities.Rating;

@Local
public interface RatingServiceLocal {

	int create(Rating rating);
	
	void removeById(int id);
	
	void modifyById(int id, Rating rating);
	
	List<Rating> findAll();
	
	
}
