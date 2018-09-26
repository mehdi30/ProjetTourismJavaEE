package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4tourism.entities.PlaceToGo;


@Remote
public interface PlaceToGoServiceRemote {
	
	int create(PlaceToGo placeToGo);
	
	void removeById(int id);
	
	void modifyById(int id, PlaceToGo placeToGo);
	
	List<PlaceToGo> findAll();
	
}
