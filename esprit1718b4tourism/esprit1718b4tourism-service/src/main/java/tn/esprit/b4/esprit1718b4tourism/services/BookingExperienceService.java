package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;
import javax.persistence.TypedQuery;


import tn.esprit.b4.esprit1718b4tourism.entities.BookingExperience;
import tn.esprit.b4.esprit1718b4tourism.entities.BookingExperiencePk;
import tn.esprit.b4.esprit1718b4tourism.entities.Experience;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

/**
 * Session Bean implementation class BookingExperienceService
 */
@Stateless
public class BookingExperienceService implements BookingExperienceServiceRemote, BookingExperienceServiceLocal {

	@PersistenceContext
	private EntityManager em;

	public BookingExperienceService() {
	}

	@Override
	public void deleteBooking(BookingExperience ExperienceR) {
		// em.remove(ER);
		em.remove(em.merge(ExperienceR));
	}
	@Override
	public void UpdateBExperience(BookingExperience Bexperience) {

		em.merge(Bexperience);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<BookingExperience> findAllReservation() {
		String jpql = "SELECT res FROM BookingExperience res";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public void BookExperience(User user, Experience ex, int num) {

		BookingExperience EV = new BookingExperience(user, ex, num);
		em.persist(EV);
	}

	@Override
	public void AddBookingExperience(int idUser, int idExp, int num,String name,String location) {

		BookingExperiencePk ev = new BookingExperiencePk();
		ev.setIdExp(idExp);
		ev.setIdUser(idUser);
		BookingExperience eve = new BookingExperience();
		eve.setBookingExPk(ev);
		eve.setNum(num);
		eve.setName(name);
		eve.setLocation(location);

		em.persist(eve);
	}
	
	@Override
	public void UpdateBookingExperience(int num,Experience Experience) {
		BookingExperience eve = em.find(BookingExperience.class, Experience);
		eve.setNum(num);
	}

	@Override
	public List<Experience> findAllById(int idUser) {
		TypedQuery<Experience> query = em.createQuery(
				"select DISTINCT m from Experience m " + "join m.bookingEps t join t.user u where u.id=:idUser",
				Experience.class);
		query.setParameter("idUser", idUser);
		return query.getResultList();

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<BookingExperience> findAllByJoin() {
		return em.createNativeQuery
				("SELECT t.*,c.* FROM bookingexperience t LEFT JOIN experience c ON t.idExp=c.id and t.idUser=1")
		        .getResultList(); 
		 
		 }
	@SuppressWarnings("unchecked")
	@Override
	public List<BookingExperience> findExperiencesbyIdUser(int idUser) {
		String jpql = "SELECT z FROM BookingExperience z WHERE z.bookingExPk.idUser=:param";
		Query query = em.createQuery(jpql);
		query.setParameter("param", idUser);
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<BookingExperience> CheckExp(int idExp) {
		String jpql = "SELECT z FROM BookingExperience z WHERE z.bookingExPk.idExp=:param";
		Query query = em.createQuery(jpql);
		query.setParameter("param", idExp);
		return query.getResultList();
	}
	
	@Override
	public List<BookingExperience> CheckExpAndUser(int idExp,int user) {
		if(em.createQuery("select z from BookingExperience z where z.bookingExPk.idExp=?1 and z.bookingExPk.idUser=?2",BookingExperience.class)
				.setParameter(1, idExp).setParameter(2, user).getResultList()==null){
			 return new ArrayList<BookingExperience>();
		 }
		else 
			return em.createQuery("select z from BookingExperience z where z.bookingExPk.idExp=?1 and z.bookingExPk.idUser=?2",BookingExperience.class)
					.setParameter(1, idExp).setParameter(2, user).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookingExperience> findReservationByUser(User content) {
		Query query = em
				.createQuery("Select r FROM BookingExperience r WHERE r.bookingExPk.idUser=?1", BookingExperience.class)
				.setParameter("1", content.getId());
		List<BookingExperience> exps = query.getResultList();
		return exps;
	}

	
}
