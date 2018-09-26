package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4tourism.entities.JobOffer;


@Remote
public interface JobOfferEJBRemote {
	List<JobOffer> FindAllJobOffers();
	List<JobOffer> FindByType(String category);
	Float FindMoyOfferByContractType(String category);
	List<JobOffer> advancedsearchJobOffer(String title); //job offer
	List<JobOffer> FindByState(boolean state );
	void updateJobOffer(JobOffer jobOffer);
	void deleteJobOffer(JobOffer jobOffer);
	JobOffer findJobOfferById(Integer id);
	List<JobOffer> FindByContractType(String category); //job offer
	
}
