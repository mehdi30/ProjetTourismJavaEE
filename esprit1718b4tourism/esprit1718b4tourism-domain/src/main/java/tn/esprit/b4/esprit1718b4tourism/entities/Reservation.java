package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Reservation implements Serializable {

	   
	@EmbeddedId
	private ReservationPK reservationPK;   

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
	private User user;
	@ManyToOne
	@JoinColumn(name="idPlaceToGo",referencedColumnName="id",insertable=false,updatable=false)
	private PlaceToGo placetogo;

	public Reservation() {
		super();
	}
	

	public Reservation(ReservationPK reservationPK, Date startDate, Date endDate, User user, PlaceToGo placetogo) {
		super();
		this.reservationPK = reservationPK;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
		this.placetogo = placetogo;
	}


	public ReservationPK getReservationPK() {
		return reservationPK;
	}

	public void setReservationPK(ReservationPK reservationPK) {
		this.reservationPK = reservationPK;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
