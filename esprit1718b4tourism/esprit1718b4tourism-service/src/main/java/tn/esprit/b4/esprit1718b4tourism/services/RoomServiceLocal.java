package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4tourism.entities.Hotel;
import tn.esprit.b4.esprit1718b4tourism.entities.Room;

@Local
public interface RoomServiceLocal {

	public Room add(Room room);

	public void delete(int id);

	public Room update(Room room);

	public Room findById(int id);

	public List<Room> findRoomsByHotel(Hotel hotel);
	
	public List<Room> findAll();
	
	public List findByType(Hotel hotelId ,String type) ;
	
	public List<Room> findByAvailability(Hotel hotel, String availability);
	
	public List<Room> searchRoom(Hotel hotel, String num);
}
