package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Remote;

import javafx.collections.ObservableList;
import tn.esprit.b4.esprit1718b4tourism.entities.User;


@Remote
public interface UserServiceRemote {
	
	public int create(User user);
	
	public void removeById(int id);
	
	public List<User> findAll();
	
	public List<User> findByParam(User u);
	
	public int findUserByEmail(String email);
	
	public User findUserByEmailPass(String email,String password);

	public void updateUser(User u);
	
	public User findUserById(Integer id);
	
	
}
