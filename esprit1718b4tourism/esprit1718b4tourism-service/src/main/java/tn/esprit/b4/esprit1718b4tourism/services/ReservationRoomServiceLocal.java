package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4tourism.entities.Hotel;
import tn.esprit.b4.esprit1718b4tourism.entities.ReservationRoom;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

@Local
public interface ReservationRoomServiceLocal {
	public ReservationRoom add(ReservationRoom resvationRoom);

	public void delete(int id);

	public ReservationRoom update(ReservationRoom resvationRoom);

	public ReservationRoom findById(int id);

	public List<ReservationRoom> findAllReservationRoom();
	
	public List<ReservationRoom> findReservationByUser(User user);
	
	public List<ReservationRoom> findReservationByHotel(Hotel hotel);
	
	public List<ReservationRoom> findReservationByRoomDate(Hotel hotel ,String type ,Date arriv ,Date Depart);
}
