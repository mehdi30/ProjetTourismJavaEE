package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

@Stateless
public class UserService implements UserServiceRemote, UserServiceLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public int create(User user) {
		em.persist(user);
		return user.getId();
		
	}


	@Override
	public void removeById(int id) {
		em.remove(em.find(User.class, id));
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		return em.createQuery("select u from User u").getResultList();
	}


	@Override
	public List<User> findByParam(User u) {
		return em.createQuery("select u from User u WHERE u.firstName like CONCAT(?1,'%') AND u.lastName like CONCAT(?2,'%')",User.class)
				.setParameter(1,u.getFirstName()).setParameter(2,u.getLastName()).getResultList();
				}


	@Override
	public int findUserByEmail(String email) {
		return em.createQuery("SELECT c FROM User c WHERE c.email=?1" ,User.class)
				.setParameter(1,email)
				.getResultList().size(); 
	}


	@Override
	public User findUserByEmailPass(String email, String password) {
		try {
			return em.createQuery("SELECT c FROM User c WHERE c.email=?1 and c.password=?2" ,User.class)
					.setParameter(1,email).setParameter(2,password)
					.getSingleResult();
		} catch (Exception e) {
		return null;
		} 
				
	}


	@Override
	public void updateUser(User u) {
		em.merge(u);
	}


	@Override
	public User findUserById(Integer id) {
		
		try {
			return em.createQuery("SELECT c FROM User c WHERE c.id=?1" ,User.class)
					.setParameter(1,id)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		} 
	}



}
