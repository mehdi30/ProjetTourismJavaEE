package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4tourism.entities.ExperienceLike;

@Remote
public interface ExperienceLikeServiceRemote {
	void AddLikeExperience(int idUser, int idExp, String name);

	void deleteLike(ExperienceLike ExperienceR);
	void DeleteById(int idExp);

	ExperienceLike retExperiencefromLike(int idExp);
	List<ExperienceLike> CheckExpAndUser(int idExp, int user);
	List<ExperienceLike> ReturnExpLikeByUser(int user);

	List<ExperienceLike> ReturnExpLikeByExp(int idExp);
	Long countNbLike(int idExp);


}
