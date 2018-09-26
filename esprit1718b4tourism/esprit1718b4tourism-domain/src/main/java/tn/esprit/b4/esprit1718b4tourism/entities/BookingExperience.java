package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BookingExperience implements Serializable{
	
	
	@EmbeddedId
	private BookingExperiencePk bookingExPk ;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="idExp",referencedColumnName="id",insertable=false,updatable=false)
	private Experience experience;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
	private User user;
	
	private int num;
	private String name;
	private String location;

	private static final long serialVersionUID = 1L;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public BookingExperiencePk getBookingExPk() {
		return bookingExPk;
	}

	public void setBookingExPk(BookingExperiencePk bookingExPk) {
		this.bookingExPk = bookingExPk;
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BookingExperience(User user,Experience experience, int num) {
		super();

		this.bookingExPk = new BookingExperiencePk(user.getId(), experience.getId());
		this.experience = experience;
		this.user = user;
		this.num = num;
		

	}

	public BookingExperience( User user,Experience experience, int num, String name,
			String location) {
		super();
		this.bookingExPk = new BookingExperiencePk(user.getId(), experience.getId());
		this.experience = experience;
		this.user = user;
		this.num = num;
		this.name = name;
		this.location = location;
	}

	public BookingExperience() {
		super();

	}

	
	
	
}
