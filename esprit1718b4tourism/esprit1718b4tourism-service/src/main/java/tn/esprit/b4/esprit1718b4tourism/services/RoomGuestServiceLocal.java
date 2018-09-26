package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4tourism.entities.RoomGuests;

@Local
public interface RoomGuestServiceLocal {

	public RoomGuests add(RoomGuests guest);

	public void delete(int id);

	public RoomGuests update(RoomGuests guest);

	public RoomGuests findById(int id);

	public List<RoomGuests> findAll(int idRoom);
}
