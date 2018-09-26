package tn.esprit.b4.esprit1718b4tourism.app.client.businessDelegate;

import java.util.List;

import tn.esprit.b4.esprit1718b4tourism.app.client.ServiceLocator.ServiceLocator;
import tn.esprit.b4.esprit1718b4tourism.entities.User;
import tn.esprit.b4.esprit1718b4tourism.services.UserServiceRemote;

public class UserServiceDelegate {
	
	private static String jndiName ="esprit1718b4tourism-ear/esprit1718b4tourism-service/UserService!"
            + "tn.esprit.b4.esprit1718b4tourism.services.UserServiceRemote";

	public static UserServiceRemote getProxy(){
		return (UserServiceRemote)ServiceLocator.getInstance().getRemoteProxy(jndiName);
	}
	
	public static int create(User user) {
		return getProxy().create(user); 
	
	}
	public static void removeById(int id){
		getProxy().removeById(id);
	}

	public static List<User> findAll(){
		return getProxy().findAll();
	}
	
	public static List<User> findByParam(User u){
		return getProxy().findByParam(u);
	}
	
	public static int findUserByEmail(String email){
		return getProxy().findUserByEmail(email);
	}
	
	public static User findUserByEmailPass(String email,String password){
		return getProxy().findUserByEmailPass(email,password);
	}

	public static void updateUser(User u){
		getProxy().updateUser(u);
	}
	
	public static User findUserById(Integer id){
		return getProxy().findUserById(id);
	}
	
	
}
