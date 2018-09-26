package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4tourism.entities.RoomGuests;

@Stateless
public class RoomGuestService implements RoomGuestServiceRemote, RoomGuestServiceLocal{
	@PersistenceContext
	private EntityManager em;

	@Override
	public RoomGuests add(RoomGuests guest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RoomGuests update(RoomGuests guest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomGuests findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomGuests> findAll(int idRoom) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
