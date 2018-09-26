package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;
import javax.persistence.*;

@Entity
public class History implements Serializable {

	   
	@EmbeddedId
	private HistoryPK historyPK;   
	
	private Integer times;
	private Date lastVisit;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
	private User user;
	@ManyToOne
	@JoinColumn(name="idPlaceToGo",referencedColumnName="id",insertable=false,updatable=false)
	private PlaceToGo placetogo;

	public History() {
		super();
	}
	
	

	public History(HistoryPK historyPK, Integer times, Date lastVisit, User user, PlaceToGo placetogo) {
		super();
		this.historyPK = historyPK;
		this.times = times;
		this.lastVisit = lastVisit;
		this.user = user;
		this.placetogo = placetogo;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public HistoryPK getHistoryPK() {
		return historyPK;
	}



	public void setHistoryPK(HistoryPK historyPK) {
		this.historyPK = historyPK;
	}



	public Integer getTimes() {
		return times;
	}



	public void setTimes(Integer times) {
		this.times = times;
	}



	public Date getLastVisit() {
		return lastVisit;
	}



	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
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
	
}
