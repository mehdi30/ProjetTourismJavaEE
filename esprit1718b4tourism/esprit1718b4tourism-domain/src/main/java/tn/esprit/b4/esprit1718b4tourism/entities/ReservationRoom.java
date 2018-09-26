package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ReservationRoom implements Serializable{
	
	@EmbeddedId
	private ReservationRoomPk reservationRoomPk;
	 
	//@Temporal(TemporalType.TIMESTAMP)
	private Date arrivalDate;
	//@Temporal(TemporalType.TIMESTAMP)
	private Date DepartureDate;
	
	private String type ;
	
	private Integer price ;
	
	@OneToMany(mappedBy="reservationRoom")
	private List<RoomGuests> guests ;
	
	
	@ManyToOne
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
	private User user;
	
//	@ManyToOne
//	@JoinColumn(name="idRoom",referencedColumnName="id",insertable=false,updatable=false)
//	private Room room ;
	
	@ManyToOne
	@JoinColumn(name="idHotel",referencedColumnName="id",insertable=false,updatable=false)
	private Hotel hotel ;
	
	private static final long serialVersionUID = 1L;
	
	public ReservationRoom() {
		super();
	}

	

	public ReservationRoom(Date arrivalDate, Date departureDate,
			String type, Integer price) {
		super();
		this.arrivalDate = arrivalDate;
		this.DepartureDate = departureDate;
		this.type = type;
		this.price = price;
	//	this.guests = guests;
	}



	public Date getArrivalDate() {
		return arrivalDate;
	}


	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}


	public Date getDepartureDate() {
		return DepartureDate;
	}


	public void setDepartureDate(Date departureDate) {
		DepartureDate = departureDate;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}



	public ReservationRoomPk getReservationRoomPk() {
		return reservationRoomPk;
	}



	public void setReservationRoomPk(ReservationRoomPk reservationRoomPk) {
		this.reservationRoomPk = reservationRoomPk;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}




 
	public Hotel getHotel() {
		return hotel;
	}



	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}



	public List<RoomGuests> getGuests() {
		return guests;
	}



	public void setGuests(List<RoomGuests> guests) {
		this.guests = guests;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
