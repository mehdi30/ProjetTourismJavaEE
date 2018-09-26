package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4tourism.entities.Image;

@Stateless
public class ImageService implements ImageServiceRemote, ImageServiceLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public int create(Image image) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyById(int id, Image image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Image> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
