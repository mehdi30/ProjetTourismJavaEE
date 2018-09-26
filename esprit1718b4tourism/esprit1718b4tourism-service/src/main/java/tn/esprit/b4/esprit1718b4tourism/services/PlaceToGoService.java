package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4tourism.entities.PlaceToGo;

@Stateless
public class PlaceToGoService implements PlaceToGoServiceRemote, PlaceToGoServiceLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public int create(PlaceToGo placeToGo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyById(int id, PlaceToGo placeToGo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PlaceToGo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
