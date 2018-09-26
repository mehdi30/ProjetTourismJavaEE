package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity 
public class Hotel implements Serializable{
	
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private static final long serialVersionUID = 1L;
	private String name ; 
	private String country;
	private String town;
	private String address;
	private String star ;
	private String wifi ;
	private String email ; 
	private String parking ;
	private Integer capacity ;
	private String  picture ; 
	
	@ManyToOne
	private User user ;
	
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="hotel",cascade ={CascadeType.PERSIST, CascadeType.REMOVE} )
	private List<Room> rooms ; 

	
	@OneToMany(mappedBy="hotel")
	private List<ReservationRoom> reservationsRoom;
	
	
	public Hotel() {
		super();
	}
	
	

	public Hotel(String name, String country, String town,  String address, String email, String star, String wifi, 
			String parking) {
		super();
		this.name = name;
		this.country = country;
		this.town = town;
		this.address = address;
		this.star = star;
		this.wifi = wifi;
		this.email = email;
		this.parking = parking;
		
	}
	
	

	public Hotel(int id, String name, String country, String town, String address, String star, String wifi,
			String email, String parking, String picture) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.town = town;
		this.address = address;
		this.star = star;
		this.wifi = wifi;
		this.email = email;
		this.parking = parking;
		this.picture = picture ;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getTown() {
		return town;
	}



	public void setTown(String town) {
		this.town = town;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getStar() {
		return star;
	}



	public void setStar(String star) {
		this.star = star;
	}



	public String getWifi() {
		return wifi;
	}



	public void setWifi(String wifi) {
		this.wifi = wifi;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getParking() {
		return parking;
	}



	public void setParking(String parking) {
		this.parking = parking;
	}



	public Integer getCapacity() {
		return capacity;
	}



	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}






	public List<Room> getRooms() {
		return rooms;
	}



	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String getPicture() {
		return picture;
	}



	public void setPicture(String picture) {
		this.picture = picture;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((star == null) ? 0 : star.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hotel other = (Hotel) obj;
		if (star == null) {
			if (other.star != null)
				return false;
		} else if (!star.equals(other.star))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", country=" + country + ", town=" + town + ", address=" + address
				+ ", star=" + star + ", wifi=" + wifi + ", email=" + email + ", parking=" + parking + "]";
	}




	
}
