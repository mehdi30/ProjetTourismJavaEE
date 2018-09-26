package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b4.esprit1718b4tourism.entities.Event;
import tn.esprit.b4.esprit1718b4tourism.entities.Souvenir;

@Stateless
public class SouvenirService implements SouvenirServiceRemote, SouvenirServiceLocal {

	@PersistenceContext
	EntityManager em;

	public SouvenirService()
	{}
  
    @Override
	public void AddSouvenir(Souvenir souvenir) {

		em.persist(souvenir);
	}

	@Override
	public void UpdateSouvenir(Souvenir Souvenir) {

		em.merge(Souvenir);
	}

	@Override
	public void DeleteSouvenir(Souvenir souvenir) {

		em.remove(em.merge(souvenir));
	}
	@Override
	public void DeleteById(int idSouvenir)
	{
		
		em.remove(em.find(Souvenir.class, idSouvenir));

		
	}
	
	@Override
	public List<Souvenir> advancedSearchSouvenir(String name) {
		List<Souvenir> list = new ArrayList<Souvenir>();
		 return list=em.createQuery("SELECT t FROM Souvenir t WHERE t.name LIKE  '%' || :name || '%' ", Souvenir.class).setParameter("name",name).getResultList(); 
		 
		 }
		 
		 
	/*
	  @Override
	public List<Souvenir> advancedSearchSouvenir(String name, int price, int quantity) {
		return em.createQuery(
			    "SELECT c FROM Souvenir c WHERE  c.name LIKE :name AND c.price LIKE :price AND c.quantity LIKE :quantity")
			 .setParameter("name", name) 
			 .setParameter("quantity",quantity )
			 .setParameter("price",price ) 

			    .getResultList();	}
*/
	@Override
	public Souvenir FindSouvenirById(Integer idSouvenir) {

		return em.find(Souvenir.class, idSouvenir);

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Souvenir> showAllSouvenir() {
		return em.createQuery("select e from Souvenir e").getResultList();

	}
	
	@Override
	public List<Souvenir> findEventByTitle(String nom) {
		Query query = em.createQuery("select c from Souvenir c where c.name=:tit");
		query.setParameter("tit", nom);
		
		return query.getResultList();


	}
	
	
	@Override
	public void UpdateQuantity(int nb, int idSouv)
	{
		Souvenir event = em.find(Souvenir.class, idSouv);
		event.setQuantity(nb);
	}
	
	
	
	@Override
	public void UpdateQuantityDelete(int nb, int idSouv)
	{
		Souvenir souv = em.find(Souvenir.class, idSouv);
		int nbancien= souv.getQuantity();
		souv.setQuantity(nb+nbancien);
		//event.setNbplaces(nb+nbancien);
		
		
	}
	
	@Override
	public void UpdatePlacesorigparti(int nb, int idSouv)
	{
		Souvenir souv = em.find(Souvenir.class, idSouv);
		souv.setQuantity(nb);
		//event.setNbparticipant(nbb);
	}

	
	
}
	