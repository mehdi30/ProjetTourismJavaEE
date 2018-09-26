package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4tourism.entities.Comment;

@Local
public interface CommentServiceLocal {
	
	int create(Comment comment);
	
	void removeById(int id);
	
	void modifyById(int id, Comment comment);
	
	List<Comment> findAll();
	
}
