package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4tourism.entities.Rating;


@Remote
public interface RatingServiceRemote {
	
	int create(Rating rating);
	
	void removeById(int id);
	
	void modifyById(int id, Rating rating);
	
	List<Rating> findAll();
	
}
