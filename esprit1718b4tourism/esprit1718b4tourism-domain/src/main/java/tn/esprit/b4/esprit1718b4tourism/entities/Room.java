package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Room implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id ;
	private String numbRoom ;
	//@Enumerated(EnumType.STRING)
	private String roomType ;
	private String availability ;
	private Integer price ; 
	
	
	
	@ManyToOne
	private Hotel hotel ; 
	
//	@OneToMany(mappedBy="room")
//	private List<ReservationRoom> reservationsRoom;
	
	
	public Room() {
		super();
	}
	
	
	
	
	public Room(String numbRoom, String roomType, String availability, Integer price) {
		super();
		this.numbRoom = numbRoom;
		this.roomType = roomType;
		this.availability = availability;
		this.price = price;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumbRoom() {
		return numbRoom;
	}
	public void setNumbRoom(String numbRoom) {
		this.numbRoom = numbRoom;
	}
	
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	public String getRoomType() {
		return roomType;
	}
	
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	
//	public List<ReservationRoom> getReservationsRoom() {
//		return reservationsRoom;
//	}
//	public void setReservationsRoom(List<ReservationRoom> reservationsRoom) {
//		this.reservationsRoom = reservationsRoom;
//	}
	
	
	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	
	
	
	
	
}