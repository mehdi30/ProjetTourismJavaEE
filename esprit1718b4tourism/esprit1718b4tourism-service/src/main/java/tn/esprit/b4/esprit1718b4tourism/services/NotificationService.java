package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4tourism.entities.Notification;
import tn.esprit.b4.esprit1718b4tourism.entities.User;


/**
 * Session Bean implementation class NotificationService
 */
@Stateless
@LocalBean
public class NotificationService implements NotificationServiceRemote, NotificationServiceLocal {
	@PersistenceContext
	EntityManager em;

    /**
     * Default constructor. 
     */
    public NotificationService() {
        // TODO Auto-generated constructor stub
    }
    

	@Override
	public void addNotification(Notification n) {
		em.persist(n);
		
	}

	@Override
	public void updateNotification(Notification n) {
		em.merge(n);
	}

	@Override
	public Notification findNotificationByID(int idNotification) {
		return em.find(Notification.class, idNotification );
		
	}

	@Override
	public void deleteNotification(Notification n) {
		em.remove(em.merge(n));
		
	}

	@Override
	public List<Notification> getAllNotificationByUser(int user) {
			if(em.createQuery("select n from Notification n where n.user.id=?1",Notification.class)
					.setParameter(1, user).getResultList()==null){
				 return new ArrayList<Notification>();
			 }
			else 
				return em.createQuery("select n from Notification n where n.user.id=?1",Notification.class)
						.setParameter(1, user).getResultList();
			
		}

	@Override
	public void deleteAllNotification(int n) {
		em.createQuery("delete from Notification n where n.user.id=?1 and n.sendDate < CURRENT_DATE").setParameter(1, n).executeUpdate();
		
	}
	@Override
	public void deleteAllNotificationAll(int n) {
		em.createQuery("delete from Notification n where n.user.id=?1").setParameter(1, n).executeUpdate();
		
	}

	@Override
	public List<Notification> getAllNotificationByUserBySeen(int user) {
		if(em.createQuery("select n from Notification n where n.user.id=?1 and n.seen=?2",Notification.class)
				.setParameter(1, user).setParameter(2, false).getResultList()==null){
			 return new ArrayList<Notification>();
		 }
		else 
			return em.createQuery("select n from Notification n where n.user.id=?1 and n.seen=?2",Notification.class)
					.setParameter(1, user).setParameter(2, false).getResultList();
	
	}

	@Override
	public Long countNotifNotSeen(int user) {
		return (Long) em.createQuery("select count(n) from Notification n where n.seen=?1 and n.user.id=?2")
		.setParameter(1, false).setParameter(2, user)
		.getSingleResult();
		 
	}
}
