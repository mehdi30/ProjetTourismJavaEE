package tn.esprit.b4.esprit1718b4tourism.utilities;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import tn.esprit.b4.esprit1718b4tourism.services.UserServiceLocal;

@Singleton
@Startup
public class DBPopulator {
	@EJB
	private UserServiceLocal userServiceLocal;

	public DBPopulator() {
	}

	@PostConstruct
	public void init() {
	//	User user = new User("user", "u", "u", "user@bitbox.tn");

	//	userServiceLocal.update(user);
	}
}
