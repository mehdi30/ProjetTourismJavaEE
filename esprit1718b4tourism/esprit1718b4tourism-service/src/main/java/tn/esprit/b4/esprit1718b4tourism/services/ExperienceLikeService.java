package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4tourism.entities.BookingExperience;
import tn.esprit.b4.esprit1718b4tourism.entities.Experience;
import tn.esprit.b4.esprit1718b4tourism.entities.ExperienceAvis;
import tn.esprit.b4.esprit1718b4tourism.entities.ExperienceAvisPK;
import tn.esprit.b4.esprit1718b4tourism.entities.ExperienceLike;
import tn.esprit.b4.esprit1718b4tourism.entities.ExperienceLikePk;

/**
 * Session Bean implementation class ExperienceLikeService
 */
@Stateless
public class ExperienceLikeService implements ExperienceLikeServiceRemote, ExperienceLikeServiceLocal {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ExperienceLikeService() {
       
    }
    @Override
	public void AddLikeExperience(int idUser, int idExp,String name) {

    	ExperienceLikePk ev = new ExperienceLikePk();
		ev.setIdExp(idExp);
		ev.setIdUser(idUser);
		ExperienceLike eve = new ExperienceLike();
		eve.setExperienceLikePk(ev);
		eve.setType(name);
		em.persist(eve);
	}
    @Override
	public void deleteLike(ExperienceLike ExperienceR) {
		// em.remove(ER);
		em.remove(em.merge(ExperienceR));
	}
    @Override
	public void DeleteById(int idExp)
	{
		
		em.remove(em.find(ExperienceLikePk.class, idExp));

		
	}
    @SuppressWarnings("unchecked")
	@Override
	public ExperienceLike retExperiencefromLike(int idExp) {
	  
		List<ExperienceLike> sd= em.createQuery("select c from ExperienceLike c where c.ExperienceLikePk.idExp=:idExp").setParameter("idExp", idExp).getResultList(); 
		if(sd.isEmpty())
			return null;
		return sd.get(0);
	}
    @Override
	public List<ExperienceLike> CheckExpAndUser(int idExp,int user) {
		if(em.createQuery("select z from ExperienceLike z where z.ExperienceLikePk.idExp=?1 and z.ExperienceLikePk.idUser=?2",ExperienceLike.class)
				.setParameter(1, idExp).setParameter(2, user).getResultList()==null){
			 return new ArrayList<ExperienceLike>();
		 }
		else 
			return em.createQuery("select z from ExperienceLike z where z.ExperienceLikePk.idExp=?1 and z.ExperienceLikePk.idUser=?2",ExperienceLike.class)
					.setParameter(1, idExp).setParameter(2, user).getResultList();
	}
    @Override
   	public List<ExperienceLike> ReturnExpLikeByUser(int user) {
   		if(em.createQuery("select z from ExperienceLike z where z.ExperienceLikePk.idUser=?1",ExperienceLike.class)
   				.setParameter(1, user).getResultList()==null){
   			 return new ArrayList<ExperienceLike>();
   		 }
   		else 
   			return em.createQuery("select z from ExperienceLike z where z.ExperienceLikePk.idUser=?1",ExperienceLike.class)
   					.setParameter(1, user).getResultList();
   	}
    @Override
   	public List<ExperienceLike> ReturnExpLikeByExp(int idExp) {
   		if(em.createQuery("select z from ExperienceLike z where z.ExperienceLikePk.idExp=?1",ExperienceLike.class)
   				.setParameter(1, idExp).getResultList()==null){
   			 return new ArrayList<ExperienceLike>();
   		 }
   		else 
   			return em.createQuery("select z from ExperienceLike z where z.ExperienceLikePk.idExp=?1",ExperienceLike.class)
   					.setParameter(1, idExp).getResultList();
   	}
    @Override
	public Long countNbLike(int idExp) {
		return (Long) em.createQuery("select count(n) from ExperienceLike n where n.ExperienceLikePk.idExp=?1")
		.setParameter(1, idExp).getSingleResult();
		 
	}
}
