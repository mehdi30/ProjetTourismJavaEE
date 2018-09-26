package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b4.esprit1718b4tourism.entities.Experience;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

@Stateless
public class ExperienceService implements ExperienceServiceRemote, ExperienceServiceLocal {

	@PersistenceContext
	private EntityManager em;

  
    public ExperienceService() {
	}

	@Override
	public void AddExperience(Experience experience) {

		em.persist(experience);
	}

	@Override
	public void UpdateExperience(Experience experience) {

		em.merge(experience);
	}

	@Override
	public void DeleteExperience(Experience experience) {

		em.remove(em.merge(experience));
	}
	@Override
	public void DeleteById(int id)
	{
		
		em.remove(em.find(Experience.class, id));

		
	}
	@Override
	public List<Experience> advancedSearchExperience(String name) {
		List<Experience> list = new ArrayList<Experience>();
		 return list=em.createQuery("SELECT t FROM Experience t WHERE t.name LIKE  '%' || :name || '%' ", Experience.class).setParameter("name",name).getResultList(); 
		 
		 }
	@SuppressWarnings("unchecked")
	@Override
	public List<User> userBestExperiencesBooked() {
		return  em.createQuery(
		        "SELECT u.* FROM User u join Experience e ON u.id=e.user.id GROUP BY e.user.id ORDER BY SUM(e.nbpart) DESC")
		        .getResultList();
		 
		 }
	@Override
	public Experience FindExperienceById(Integer id) {

		return em.find(Experience.class, id);

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Experience> showAllExperiences() {
		return em.createQuery("select e from Experience e where e.dateExperience >= CURRENT_DATE").getResultList();

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Experience> showBestbookedExperiences() {
		return em.createQuery("SELECT e FROM Experience e GROUP BY e.id ORDER BY SUM(e.nbpart) DESC").getResultList();

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Experience> showAllExperiencesbyUser(int idUser) {
		String jpql = "SELECT z FROM Experience z WHERE z.user.id=:idUser";
		Query query = em.createQuery(jpql);
		query.setParameter("idUser", idUser);
		return query.getResultList();

	}
	@Override
	public void UpdatePlaces(int nb, int id)
	{
		Experience experience = em.find(Experience.class, id);
		experience.setNbplaces(nb); //nbrplaces
	}
	
	@Override
	public int Participation(int id, int num) {
		Experience exp = em.find(Experience.class, id);
		int ancien=exp.getNbpart();
		exp.setNbpart(num+ancien);
		return 1;

	}
	@Override
	public void UpdatePlacesD(int nb, int id)
	{
		Experience experience = em.find(Experience.class, id);
		int ancien = experience.getNbplaces();
		experience.setNbplaces(ancien+nb); //nbrplaces
	}
	@Override
	public int ParticipationD(int id, int num) {
		Experience exp = em.find(Experience.class, id);
		int ancien=exp.getNbpart();
		exp.setNbpart(ancien-num);
		return 1;

	}
	@Override
	public int retNbdePlaces(int id) {
		Experience exp = em.find(Experience.class, id);
		int num=exp.getNbplaces();
		return num;

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Experience> sortBookedExperience() {
	  
		return em.createQuery("SELECT t FROM Experience t WHERE t.nbpart > 0").getResultList(); 

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Experience> sortNotBookedExperience() {
	  
		return em.createQuery("SELECT t FROM Experience t WHERE t.nbpart = 0").getResultList(); 

	}
}
