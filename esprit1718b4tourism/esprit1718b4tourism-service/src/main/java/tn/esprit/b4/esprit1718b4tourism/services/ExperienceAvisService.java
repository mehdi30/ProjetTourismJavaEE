package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4tourism.entities.BookingExperience;
import tn.esprit.b4.esprit1718b4tourism.entities.ExperienceAvis;
import tn.esprit.b4.esprit1718b4tourism.entities.ExperienceAvisPK;

/**
 * Session Bean implementation class ExperienceAvisService
 */
@Stateless
public class ExperienceAvisService implements ExperienceAvisServiceRemote, ExperienceAvisServiceLocal {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ExperienceAvisService() {
        // TODO Auto-generated constructor stub
    }
    @Override
	public void AddBookingExperience(int idUser, int idExp, double rate,String type) {

    	ExperienceAvisPK ev = new ExperienceAvisPK();
		ev.setIdExp(idExp);
		ev.setIdUser(idUser);
		ExperienceAvis eve = new ExperienceAvis();
		eve.setExperienceAvisPK(ev);
		eve.setRate(rate);
		eve.setType(type);
		em.persist(eve);
	}
    @Override
	public double AvgRate(int idExp) {
		return (double) em.createQuery("select avg(n.rate) from ExperienceAvis n where n.ExperienceAvisPK.idExp=?1").setParameter(1, idExp)
		.getSingleResult();
		 
	}
    @Override
	public double RetRate(int idExp,int idUser) {
		return (double) em.createQuery("select n.rate from ExperienceAvis n where n.ExperienceAvisPK.idExp=?1 and n.ExperienceAvisPK.idUser=?2").setParameter(1, idExp).setParameter(2, idUser)
		.getSingleResult();
		 
	}
    @Override
	public List<ExperienceAvis> CheckExpAndUser(int idExp,int user) {
		if(em.createQuery("select z from ExperienceAvis z where z.ExperienceAvisPK.idExp=?1 and z.ExperienceAvisPK.idUser=?2",ExperienceAvis.class)
				.setParameter(1, idExp).setParameter(2, user).getResultList()==null){
			 return new ArrayList<ExperienceAvis>();
		 }
		else 
			return em.createQuery("select z from ExperienceAvis z where z.ExperienceAvisPK.idExp=?1 and z.ExperienceAvisPK.idUser=?2",ExperienceAvis.class)
					.setParameter(1, idExp).setParameter(2, user).getResultList();
	}
    @Override
	public void UpdateExperienceAvis(ExperienceAvis Bexperience) {

		em.merge(Bexperience);
	}
    @Override
	public List<ExperienceAvis> CheckExp(int idExp) {
		if(em.createQuery("select z from ExperienceAvis z where z.ExperienceAvisPK.idExp=?1",ExperienceAvis.class)
				.setParameter(1, idExp).getResultList()==null){
			 return new ArrayList<ExperienceAvis>();
		 }
		else 
			return em.createQuery("select z from ExperienceAvis z where z.ExperienceAvisPK.idExp=?1",ExperienceAvis.class)
					.setParameter(1, idExp).getResultList();
	}
    @Override
	public Long countNbAvis(int idExp) {
		return (Long) em.createQuery("select count(n) from ExperienceAvis n where n.ExperienceAvisPK.idExp=?1")
		.setParameter(1, idExp).getSingleResult();
		 
	}

}
