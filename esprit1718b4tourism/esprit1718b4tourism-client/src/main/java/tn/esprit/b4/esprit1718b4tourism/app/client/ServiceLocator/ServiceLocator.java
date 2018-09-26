package tn.esprit.b4.esprit1718b4tourism.app.client.ServiceLocator;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {
	private Map<String, Object> cache;
	private static ServiceLocator serviceLocator;
	private Context context;

	private ServiceLocator() {
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		cache = new HashMap<String, Object>();

	}

	public static ServiceLocator getInstance() {

		if (serviceLocator == null) {
			serviceLocator = new ServiceLocator();
		}
		return serviceLocator;

	}

	public Object getRemoteProxy(String jndiName) {
		Object object = null;
		object = cache.get(jndiName);
		if (object == null) {
			try {
				object = context.lookup(jndiName);
				cache.put(jndiName, object);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return object;

	}
}
