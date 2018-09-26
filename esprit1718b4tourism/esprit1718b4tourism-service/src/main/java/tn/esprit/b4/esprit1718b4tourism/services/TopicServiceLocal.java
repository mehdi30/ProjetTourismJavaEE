package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4tourism.entities.Topic;
import tn.esprit.b4.esprit1718b4tourism.entities.User;

@Local
public interface TopicServiceLocal {
	void AddTopic(Topic topic);
	void UpdateTopic(Topic topic) ;
	void DeleteTopic(Topic topic);
	Topic FindTopicById(Integer idTopic);
	List<Topic> FindAllTopics() ;
	List<Topic> advancedsearchTopic(String title);
	Long TopicCommentsNumber(int id) ;
	List<Topic> topicGroupByComment();
	List<Topic> sortTopicsByCommentsNumberDesc();
	List<User> userOrderByCommentsNumber();
	
	List<Topic> sortTopicByCategory(String category);
	List<Topic> sortTopicsByCommentsNumberAsc() ;
	Double moyCommPerForum();
}
