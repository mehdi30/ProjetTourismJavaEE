package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4tourism.entities.BookingExperience;
import tn.esprit.b4.esprit1718b4tourism.entities.BookingExperiencePk;
import tn.esprit.b4.esprit1718b4tourism.entities.Experience;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

@Local
public interface BookingExperienceServiceLocal {

	void deleteBooking(BookingExperience ExperienceR);

	List<BookingExperience> findAllReservation();

	void BookExperience(User user, Experience ex, int num);


	List<Experience> findAllById(int idUser);

	List<BookingExperience> findReservationByUser(User content);


	List<BookingExperience> findExperiencesbyIdUser(int idUser);

	List<BookingExperience> findAllByJoin();
	List<BookingExperience> CheckExp(int idExp);

	void UpdateBookingExperience(int num, Experience Experience);

	void UpdateBExperience(BookingExperience Bexperience);

	List<BookingExperience> CheckExpAndUser(int idExp, int user);

	void AddBookingExperience(int idUser, int idExp, int num, String name, String location);

	

}
