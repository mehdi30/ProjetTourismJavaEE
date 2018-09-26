package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4tourism.entities.JobOffer;

/**
 * Session Bean implementation class JobOfferEJB
 */
@Stateless
@LocalBean
public class JobOfferEJB implements JobOfferEJBRemote, JobOfferEJBLocal {
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public JobOfferEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<JobOffer> FindAllJobOffers() {
		
		return em.createQuery("SELECT t FROM JobOffer t ", JobOffer.class).getResultList();
	}

	@Override
	public List<JobOffer> FindByType(String category) {
		
		return em.createQuery("SELECT t FROM JobOffer t WHERE t.type= ?1", JobOffer.class).setParameter(1,category).getResultList();
	}
	@Override
	public List<JobOffer> FindByContractType(String category) {
		
		return em.createQuery("SELECT t FROM JobOffer t WHERE t.ContractType= ?1", JobOffer.class).setParameter(1,category).getResultList();
	}
	@Override
	public List<JobOffer> advancedsearchJobOffer(String title) {
		
		 return em.createQuery("SELECT t FROM JobOffer t WHERE t.title LIKE  '%' || :title || '%' ", JobOffer.class).setParameter("title",title).getResultList(); 
		 
		 
	}

	@Override
	public List<JobOffer> FindByState(boolean state) {
		return em.createQuery("SELECT t FROM JobOffer t WHERE t.approuved= ?1", JobOffer.class).setParameter(1,state).getResultList();
	}

	@Override
	public void updateJobOffer(JobOffer jobOffer) {
		em.merge(jobOffer);
		
	}

	@Override
	public void deleteJobOffer(JobOffer jobOffer) {
		em.remove(em.merge(jobOffer));	
		
	}

	@Override
	public JobOffer findJobOfferById(Integer id) {
		return em.find(JobOffer.class, id);
	}

	@Override
	public Float FindMoyOfferByContractType(String category) {
		Long c = (Long) em.createQuery("SELECT COUNT(j) FROM JobOffer j WHERE j.ContractType=?1 ").setParameter(1,category).getSingleResult();
		Long total = (Long) em.createQuery("SELECT COUNT(t) FROM JobOffer t ").getSingleResult();
		return ((c.floatValue()/total.floatValue())*100);
	}

	@Override
	public void addJobOffer(JobOffer jobOffer) {
		em.persist(jobOffer);
		
	}

	@Override
	public List<JobOffer> advancedsearchJobOfferA(String title) {
		 return em.createQuery("SELECT t FROM JobOffer t WHERE t.title LIKE  '%' || :title || '%' AND t.approuved= ?1", JobOffer.class).setParameter("title",title).setParameter(1,true).getResultList(); 

	}



	

	@Override
	public List<JobOffer> FindBySector(String sector) {
		return em.createQuery("SELECT t FROM JobOffer t WHERE t.sector= ?1 AND t.approuved= ?2", JobOffer.class).setParameter(1,sector).setParameter(2,true).getResultList();

	}
	@Override
	public List<JobOffer> FindByContractTypeA(String c) {
		return em.createQuery("SELECT t FROM JobOffer t WHERE t.type= ?1 AND t.approuved= ?2", JobOffer.class).setParameter(1,c).setParameter(2,true).getResultList();

	}

	

	@Override
	public List<JobOffer> FindByContractTypeAndSectorA(String c, String s,String t) {
		return em.createQuery("SELECT t FROM JobOffer t WHERE t.type= ?1 AND t.approuved= ?2 AND t.sector= ?3 And t.title LIKE  '%' || :title || '%'", JobOffer.class)
				.setParameter(1,c)
				.setParameter(2,true)
				.setParameter(3,s)
				.setParameter("title",t)
				.getResultList();

	}

	@Override
	public List<JobOffer> sortJoByApplyNumberDesc() {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT t FROM JobOffer t ORDER BY t.applyNbr DESC", JobOffer.class).getResultList();
	}

	
	
	

}
