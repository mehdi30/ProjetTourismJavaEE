package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4tourism.entities.Rating;

@Stateless
public class RatingService implements RatingServiceRemote, RatingServiceLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public int create(Rating rating) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyById(int id, Rating rating) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Rating> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
