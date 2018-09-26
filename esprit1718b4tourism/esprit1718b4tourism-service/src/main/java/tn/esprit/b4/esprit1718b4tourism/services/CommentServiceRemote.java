package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4tourism.entities.Comment;


@Remote
public interface CommentServiceRemote {
	
	int create(Comment comment);
	
	void removeById(int id);
	
	void modifyById(int id, Comment comment);
	
	List<Comment> findAll();
	
}
