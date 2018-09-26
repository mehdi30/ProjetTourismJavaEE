package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4tourism.entities.PlaceToGo;

@Local
public interface PlaceToGoServiceLocal {
	

	int create(PlaceToGo placeToGo);
	
	void removeById(int id);
	
	void modifyById(int id, PlaceToGo placeToGo);
	
	List<PlaceToGo> findAll();
	
	
}
