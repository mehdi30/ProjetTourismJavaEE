package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4tourism.entities.Notification;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

@Local
public interface NotificationServiceLocal {

	void addNotification(Notification n);

	void updateNotification(Notification n);

	Notification findNotificationByID(int idNotification);

	void deleteNotification(Notification n);

	List<Notification> getAllNotificationByUser(int user);

	List<Notification> getAllNotificationByUserBySeen(int user);

	Long countNotifNotSeen(int user);

	void deleteAllNotification(int n);

	void deleteAllNotificationAll(int n);

}
