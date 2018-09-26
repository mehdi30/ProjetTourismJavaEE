package tn.esprit.b4.esprit1718b4tourism.services;
import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4tourism.entities.Souvenir;

@Local
public interface SouvenirServiceLocal {


	Souvenir FindSouvenirById(Integer idSouvenir);

	void DeleteSouvenir(Souvenir souvenir);

	void UpdateSouvenir(Souvenir souvenir);

	void AddSouvenir(Souvenir souvenir);

	void DeleteById(int idSouvenir);

	List<Souvenir> advancedSearchSouvenir(String name);
	void UpdatePlacesorigparti(int nb, int idSouv);

	void UpdateQuantityDelete(int nb, int idSouv);
	List<Souvenir> showAllSouvenir();
	List<Souvenir> findEventByTitle(String nom) ;
	void UpdateQuantity(int nb, int idSouv);
	

}
