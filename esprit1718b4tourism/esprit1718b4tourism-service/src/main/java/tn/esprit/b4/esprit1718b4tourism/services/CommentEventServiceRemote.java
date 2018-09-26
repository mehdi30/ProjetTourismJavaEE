package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4tourism.entities.CommentEvent;
import tn.esprit.b4.esprit1718b4tourism.entities.Topic;

@Remote
public interface CommentEventServiceRemote {
	
	void AddComment(CommentEvent comment);
	void UpdateComment(CommentEvent comment);
	void DeleteComment(CommentEvent comment);
	CommentEvent FindCommentById(Integer id);
	List<CommentEvent> FindCommentByTopic(Topic topic);
	List<CommentEvent> FindAllComments() ;
	List<CommentEvent> sortCommentByCategory(String category);
	void DeleteCommentByTopic(Topic topic);

}
