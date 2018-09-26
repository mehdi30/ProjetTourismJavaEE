package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4tourism.entities.Souvenir;
import tn.esprit.b4.esprit1718b4tourism.entities.Panier;
import tn.esprit.b4.esprit1718b4tourism.entities.PanierPK;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

@Stateless
public class PanierService  implements PanierServiceRemote,PanierServiceLocal{

	@PersistenceContext
	private EntityManager em;
	
	public PanierService()
	{
		
		
	}
	
/*
	@SuppressWarnings("unchecked")
	@Override
	public List<Panier> CheckEvent(int idevent, int idUser) {
		String jpql = "SELECT z FROM Panier z WHERE z.PanierPK.idSouvenir=:param AND z.PanierPK.idUser=:param";
		Query query = em.createQuery(jpql);
		query.setParameter("param", idSouvenir);
		query.setParameter("param", idUser);
		return query.getResultList();
	}
	*/
	
	
	@Override
	public void deleteReservation( Panier ER) {
		
		
		em.remove(em.merge(ER));
	}
	

	@Override
	public void UpdateMyReser( Panier ER, int nb) {
		Souvenir ee= ER.getSouvenirs();
		ER.setQuantity(nb);
		em.merge(ER);
	}
	

	

	@Override
	public List<Panier> findAllReservation() {
		String jpql = "SELECT res FROM Panier res";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public void ReserverSouvenir( User u, Souvenir com, int quantity) {
		
		
		   Panier EV = new Panier(u,com,quantity);
		    em.persist(EV);		
	}

	
	@Override
	public void AddReservationSouvenir( int idUsr, int idSouvenir, int quantity, String name) {
		
		PanierPK ev = new PanierPK();
		ev.setIdSouvenir(idSouvenir);
		ev.setIdUser(idUsr);
		
		Panier eve = new Panier();
		eve.setPanierPk(ev);
		eve.setName(name);
		eve.setQuantity(quantity);

		em.persist(eve);
		
			
	}
	/*
	@Override
	public void UpdateReservationSouvenir(int idUser, int idSouvenir, int nb)
	{
		Panier panier = em.find(Panier .class, idSouvenir);
		PanierPK ev = new PanierPK();
	     ev.setIdSouvenir(idSouvenir);
	ev.setIdUser(idUsr);
	
	Panier eve = new Panier ();
	eve.setPanierPK(ev);
	eve.setQuantity(quantity);
	//Event e = new Event();
	//e.setNbplaces(nbplaces--);
	em.persist(eve);
	}*/
	/*
	@Override
	public List<Souvenir> findAllById(int idUser)
	{
		TypedQuery<Souvenir> query = em.createQuery("select DISTINCT m from Souvenir m "
				+ "join m.listReservations t join t.user u where u.id=:idUser",Souvenir.class);//////////////////listReservation??
		query.setParameter("idUser", idUser);
		return query.getResultList();
		
	}*/
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Panier> findReservationByUser(User content) {
		Query query=em.createQuery("Select r FROM Panier WHERE r.panierPk.idUser=?1", Panier.class).setParameter("1", content.getId());
		List<Panier> ratings=query.getResultList();
		return ratings;
	}



	@Override
	public List<Panier> findReservationByIdUser(int idUser) {
		Query query=em.createQuery("Select r FROM Panier WHERE r.panierPk.idUser =:idUser", Panier.class)
				.setParameter("idUser",idUser);
		List<Panier> ratings=query.getResultList();
		return ratings;
	}
	
	@Override
	public List<Panier> findReservationByIdSouvenir(int idSouvenir) {
		Query query=em.createQuery("Select r FROM Panier r WHERE r.panierPk.idSouvenir =:idSouvenir", Panier.class)
				.setParameter("idSouvenir",idSouvenir);
		List<Panier> ratings=query.getResultList();
		return ratings;
	}
	
	

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Panier> findContractsbyCompanybyID(int idcom) {
		String jpql = "SELECT z FROM Panier z WHERE z.panierPk.idUser=:param";
		Query query = em.createQuery(jpql);
		query.setParameter("param", idcom);
		return query.getResultList();
	}

	
	
	
	
	
	
	}
	
	

