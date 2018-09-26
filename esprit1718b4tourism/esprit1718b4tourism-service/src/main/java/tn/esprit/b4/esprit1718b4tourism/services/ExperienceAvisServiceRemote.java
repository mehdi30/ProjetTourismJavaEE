package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4tourism.entities.ExperienceAvis;

@Remote
public interface ExperienceAvisServiceRemote {
	void AddBookingExperience(int idUser, int idExp, double rate, String type);
	double AvgRate(int idExp);
	List<ExperienceAvis> CheckExpAndUser(int idExp, int user);
	void UpdateExperienceAvis(ExperienceAvis Bexperience);
	List<ExperienceAvis> CheckExp(int idExp);
	Long countNbAvis(int idExp);
	double RetRate(int idExp, int idUser);

}
