package tn.esprit.b4.esprit1718b4tourism.entities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity

public class CommentEvent implements Serializable {

	@Id
	@GeneratedValue(strategy=(GenerationType.IDENTITY))
	private int idCm;
	private String content;
	private Date date;
	@ManyToOne
	private User user;
	@ManyToOne
	private Topic topic;
	private static final long serialVersionUID = 1L;

	public CommentEvent() {
		super();
	}
	

	public CommentEvent(int idCm, String content, Date date, User user, Topic topic) {
		
		this.idCm = idCm;
		this.content = content;
		this.date = date;
		this.user = user;
		this.topic = topic;
	}


	public CommentEvent(String content, Date date, User user, Topic topic) {
		
		this.content = content;
		this.date = date;
		this.user = user;
		this.topic = topic;
	}


	public Topic getTopic() {
		return topic;
	}


	public void setTopic(Topic topic) {
		this.topic = topic;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public int getIdCm() {
		return idCm;
	}

	public void setIdCm(int idCm) {
		this.idCm = idCm;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}
