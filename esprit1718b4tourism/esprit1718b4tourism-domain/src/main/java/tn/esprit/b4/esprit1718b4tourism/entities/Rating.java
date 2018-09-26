package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;

@Entity
public class Rating implements Serializable {
	   
	@EmbeddedId
	private RatingPK ratingPK;   
	
	private Integer rate;

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
	private User user;
	@ManyToOne
	@JoinColumn(name="idPlaceToGo",referencedColumnName="id",insertable=false,updatable=false)
	private PlaceToGo placetogo;

	public Rating() {
		super();
	}
	

	public Rating(RatingPK ratingPK, Integer rate, User user, PlaceToGo placetogo) {
		super();
		this.ratingPK = ratingPK;
		this.rate = rate;
		this.user = user;
		this.placetogo = placetogo;
	}


	public RatingPK getRatingPK() {
		return ratingPK;
	}

	public void setRatingPK(RatingPK ratingPK) {
		this.ratingPK = ratingPK;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
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
