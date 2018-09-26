package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4tourism.entities.History;


@Remote
public interface HistoryServiceRemote {
	int create(History history);
	
	void removeById(int id);
	
	void modifyById(int id, History history);
	
	List<History> findAll();
	
}
