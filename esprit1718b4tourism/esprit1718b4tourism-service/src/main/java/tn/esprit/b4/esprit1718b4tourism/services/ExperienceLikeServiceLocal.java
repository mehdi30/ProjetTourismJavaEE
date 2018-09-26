package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4tourism.entities.ExperienceLike;

@Local
public interface ExperienceLikeServiceLocal {

	void deleteLike(ExperienceLike ExperienceR);
	void DeleteById(int idExp);
	ExperienceLike retExperiencefromLike(int idExp);
	List<ExperienceLike> CheckExpAndUser(int idExp, int user);
	List<ExperienceLike> ReturnExpLikeByUser(int user);
	void AddLikeExperience(int idUser, int idExp, String name);
	Long countNbLike(int idExp);
	List<ExperienceLike> ReturnExpLikeByExp(int idExp);


}
