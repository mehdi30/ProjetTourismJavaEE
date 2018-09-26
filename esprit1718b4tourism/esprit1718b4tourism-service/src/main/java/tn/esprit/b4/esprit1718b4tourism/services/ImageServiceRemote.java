package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4tourism.entities.Image;


@Remote
public interface ImageServiceRemote {
	
	int create(Image image);
	
	void removeById(int id);
	
	void modifyById(int id, Image image);
	
	List<Image> findAll();
	
}
