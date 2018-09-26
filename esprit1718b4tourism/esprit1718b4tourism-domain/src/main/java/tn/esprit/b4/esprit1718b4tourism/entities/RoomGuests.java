package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class RoomGuests implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idGuest ;
	@ManyToOne
	private ReservationRoom reservationRoom ; 
	private String firstName;
	private String lastName;
	private String sex ;
	@Temporal(TemporalType.DATE)
	private Date birthdayDate;
	private String phone;
	private Boolean children ;
	
	
	
	public RoomGuests() {
	}



	public Integer getIdGuest() {
		return idGuest;
	}



	public void setIdGuest(Integer idGuest) {
		this.idGuest = idGuest;
	}



	public ReservationRoom getReservationRoom() {
		return reservationRoom;
	}



	public void setReservationRoom(ReservationRoom reservationRoom) {
		this.reservationRoom = reservationRoom;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public Date getBirthdayDate() {
		return birthdayDate;
	}



	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public Boolean getChildren() {
		return children;
	}



	public void setChildren(Boolean children) {
		this.children = children;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthdayDate == null) ? 0 : birthdayDate.hashCode());
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((idGuest == null) ? 0 : idGuest.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((reservationRoom == null) ? 0 : reservationRoom.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
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
		RoomGuests other = (RoomGuests) obj;
		if (birthdayDate == null) {
			if (other.birthdayDate != null)
				return false;
		} else if (!birthdayDate.equals(other.birthdayDate))
			return false;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (idGuest == null) {
			if (other.idGuest != null)
				return false;
		} else if (!idGuest.equals(other.idGuest))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (reservationRoom == null) {
			if (other.reservationRoom != null)
				return false;
		} else if (!reservationRoom.equals(other.reservationRoom))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "RoomGuests [idGuest=" + idGuest + ", reservationRoom=" + reservationRoom + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", sex=" + sex + ", birthdayDate=" + birthdayDate + ", phone=" + phone
				+ ", children=" + children + "]";
	}
	
	

}
