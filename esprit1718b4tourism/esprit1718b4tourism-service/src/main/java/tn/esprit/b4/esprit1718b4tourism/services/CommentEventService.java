package tn.esprit.b4.esprit1718b4tourism.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4tourism.entities.CommentEvent;
import tn.esprit.b4.esprit1718b4tourism.entities.Topic;
@Stateless
public class CommentEventService implements CommentEventServiceLocal,CommentEventServiceRemote {

	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public  CommentEventService() {
		
	} 
       
    

	@Override
	public void AddComment(CommentEvent comment) {
		// TODO Auto-generated method stub
		em.persist(comment);
	}

	@Override
	public void UpdateComment(CommentEvent comment) {
		// TODO Auto-generated method stub
		em.merge(comment);
	}

	@Override
	public void DeleteComment(CommentEvent comment) {
		// TODO Auto-generated method stub
		em.remove(em.merge(comment));
	}

	@Override
	public CommentEvent FindCommentById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(CommentEvent.class, id);
	}

	@Override
	public List<CommentEvent> FindCommentByTopic(Topic topic) {
		// TODO Auto-generated method stub
		List<CommentEvent> list = new ArrayList<CommentEvent>();
		 return list=em.createQuery("SELECT t FROM CommentEvent t WHERE t.topic = ?1 ", CommentEvent.class).setParameter(1,topic).getResultList(); 
		 
	}
	@Override
	public List<CommentEvent> FindAllComments() {
		// TODO Auto-generated method stub
		List<CommentEvent> list = new ArrayList<CommentEvent>();
		 return list=em.createQuery("SELECT t FROM CommentEvent t ", CommentEvent.class).getResultList(); 
		 
	}
	@Override
	public List<CommentEvent> sortCommentByCategory(String category) {
	 return em.createQuery("SELECT c FROM CommentEvent c  WHERE c.topic.category=?1 ", CommentEvent.class).setParameter(1,category).getResultList(); 
	}

	@Override
	public void DeleteCommentByTopic(Topic topic) {
		em.createQuery("DELETE FROM CommentEvent c WHERE c.topic=?1").setParameter(1,topic).executeUpdate();
		
	}

	
	
	
	
	
	
	
	
	
}
