package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4tourism.entities.History;

@Local
public interface HistoryServiceLocal {
	
	int create(History history);
	
	void removeById(int id);
	
	void modifyById(int id, History history);
	
	List<History> findAll();
	
}
