package tn.esprit.b4.esprit1718b4tourism.services;
import tn.esprit.b4.esprit1718b4tourism.entities.Souvenir;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface SouvenirServiceRemote {
	List<Souvenir> showAllSouvenir();

	Souvenir FindSouvenirById(Integer idSouvenir);

	void DeleteSouvenir(Souvenir souvenir);

	void UpdateSouvenir(Souvenir souvenir);

	void AddSouvenir(Souvenir souvenir);
	
	public void DeleteById(int idSouvenir);
	List<Souvenir> advancedSearchSouvenir(String name);
	List<Souvenir> findEventByTitle(String nom) ;
	void UpdateQuantity(int nb, int idSouv);

	void UpdateQuantityDelete(int nb, int idSouv);
	void UpdatePlacesorigparti(int nb, int idSouv);
}
