package tn.esprit.b4.esprit1718b4tourism.services;



import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4tourism.entities.Hotel;

@Remote
public interface HotelServiceRemote {

	public Hotel add(Hotel hotel);

	public void delete(int id);

	public Hotel update(Hotel hotel);

	public Hotel findById(int id);
	
	public List<Hotel> findByName(String name);

	public List<Hotel> findAll();
	
	public int exist(Hotel hotel);
	
	public List<String> findAllName();
	
	public List<Hotel> searchByManyCriteria(String txt);
}
