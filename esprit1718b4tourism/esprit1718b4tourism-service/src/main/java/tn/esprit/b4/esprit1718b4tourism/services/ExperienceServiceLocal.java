package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4tourism.entities.Experience;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

@Local
public interface ExperienceServiceLocal {

	List<Experience> showAllExperiences();

	Experience FindExperienceById(Integer id);

	void DeleteExperience(Experience experience);

	void UpdateExperience(Experience experience);

	void AddExperience(Experience experience);

	void DeleteById(int id);

	List<Experience> advancedSearchExperience(String name);

	void UpdatePlaces(int nb, int idExp);

	int Participation(int idExp, int num);

	void UpdatePlacesD(int nb, int id);

	int ParticipationD(int id, int num);

	int retNbdePlaces(int id);

	List<Experience> sortBookedExperience();

	List<Experience> sortNotBookedExperience();
	List<Experience> showAllExperiencesbyUser(int idUser);

	List<User> userBestExperiencesBooked();

	List<Experience> showBestbookedExperiences();


}
