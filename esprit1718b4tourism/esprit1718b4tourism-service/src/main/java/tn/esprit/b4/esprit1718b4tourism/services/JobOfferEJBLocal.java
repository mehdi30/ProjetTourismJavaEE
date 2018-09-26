package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4tourism.entities.JobOffer;

@Local
public interface JobOfferEJBLocal {
	List<JobOffer> FindAllJobOffers();
	List<JobOffer> FindByType(String category);
	Float FindMoyOfferByContractType(String category);
	List<JobOffer> advancedsearchJobOffer(String title);
	List<JobOffer> FindByState(boolean state );
	void updateJobOffer(JobOffer jobOffer);
	void deleteJobOffer(JobOffer jobOffer);
	void addJobOffer(JobOffer jobOffer);
	JobOffer findJobOfferById(Integer id);
	List<JobOffer> FindByContractType(String category);
	List<JobOffer> advancedsearchJobOfferA(String title);
	List<JobOffer> FindBySector(String sector);
	List<JobOffer> FindByContractTypeA(String c);
	List<JobOffer> FindByContractTypeAndSectorA(String c,String s ,String t);
	List<JobOffer> sortJoByApplyNumberDesc();
	
}
