package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4tourism.entities.Souvenir;
import tn.esprit.b4.esprit1718b4tourism.entities.Panier ;
import tn.esprit.b4.esprit1718b4tourism.entities.User;
@Remote
public interface PanierServiceRemote {

	//List<Panier> CheckEvent(int idevent, int idUser);
	void deleteReservation( Panier ER);
	void UpdateMyReser( Panier ER, int nb);
	List<Panier> findAllReservation();
	void ReserverSouvenir( User u, Souvenir com, int quantity);
	void AddReservationSouvenir( int idUsr, int idSouvenir, int quantity, String name);
	//List<Souvenir> findAllById(int idUser);
	List<Panier> findReservationByUser(User content);
	List<Panier> findReservationByIdUser(int idUser);
	List<Panier> findReservationByIdSouvenir(int idSouvenir);
	List<Panier> findContractsbyCompanybyID(int idcom) ;
}
