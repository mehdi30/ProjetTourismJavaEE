package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity

public class Topic implements Serializable {

	@Id
	@GeneratedValue(strategy = (GenerationType.IDENTITY))
	private int idTopic;
	private String title;
	private String description;
	private String category;
	private Date date;
	private Long commentsNumber;
	
	@ManyToOne
	private User user;
	
	
	@OneToMany(mappedBy="topic", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
	List<CommentEvent> comments;
	
	private static final long serialVersionUID = 1L;

	public Topic() {

	}

	public Topic(String title, String description,String category, Date date, User user) {

		this.title = title;
		this.description = description;
		this.category=category;
		this.date = date;
		
		this.user = user;

	}

	public Topic(int idTopic, String title, String description,String category, Date date, User user, List<CommentEvent> comments) {

		this.idTopic = idTopic;
		this.title = title;
		this.description = description;
		this.category=category;
		this.date = date;
		this.user = user;
		this.comments = comments;
	}

	public List<CommentEvent> getComments() {
		return comments;
	}

	public void setComments(List<CommentEvent> comments) {
		this.comments = comments;
	}

	public int getIdTopic() {
		return idTopic;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getCommentsNumber() {
		return commentsNumber;
	}

	public void setCommentsNumber(Long commentsNumber) {
		this.commentsNumber = commentsNumber;
	}

	public void setIdTopic(int idTopic) {
		this.idTopic = idTopic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
