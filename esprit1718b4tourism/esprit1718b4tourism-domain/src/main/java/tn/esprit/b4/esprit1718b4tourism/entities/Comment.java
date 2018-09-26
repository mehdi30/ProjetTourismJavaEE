package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Comment implements Serializable {

	   
	@EmbeddedId
	private CommentPK commentPK;   

	
	private String comment;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
	private User user;
	@ManyToOne
	@JoinColumn(name="idPlaceToGo",referencedColumnName="id",insertable=false,updatable=false)
	private PlaceToGo placetogo;

	public Comment() {
		super();
	}
	
	

	public Comment(CommentPK commentPK, String comment, User user, PlaceToGo placetogo) {
		super();
		this.commentPK = commentPK;
		this.comment = comment;
		this.user = user;
		this.placetogo = placetogo;
	}



	public CommentPK getCommentPK() {
		return commentPK;
	}

	public void setCommentPK(CommentPK commentPK) {
		this.commentPK = commentPK;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PlaceToGo getPlacetogo() {
		return placetogo;
	}

	public void setPlacetogo(PlaceToGo placetogo) {
		this.placetogo = placetogo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	}
