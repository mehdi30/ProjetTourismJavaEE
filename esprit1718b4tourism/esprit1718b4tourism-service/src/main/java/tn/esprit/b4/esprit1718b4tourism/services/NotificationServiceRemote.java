package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4tourism.entities.Notification;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

@Remote
public interface NotificationServiceRemote {
	void addNotification(Notification n);

	void updateNotification(Notification n);

	Notification findNotificationByID(int idNotification);

	void deleteNotification(Notification n);



	List<Notification> getAllNotificationByUserBySeen(int user);

	
	List<Notification> getAllNotificationByUser(int user);

	Long countNotifNotSeen(int user);
	void deleteAllNotification(int n);

	void deleteAllNotificationAll(int n);


}
