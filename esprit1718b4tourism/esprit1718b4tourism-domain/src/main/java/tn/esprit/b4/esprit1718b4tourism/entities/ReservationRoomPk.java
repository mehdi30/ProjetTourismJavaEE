package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;

import java.lang.Integer;
import javax.persistence.Embeddable;



@Embeddable
public class ReservationRoomPk implements Serializable{
      
	private Integer idUser;
	private Integer idHotel ; 
	
	private static final long serialVersionUID = 1L;

	public ReservationRoomPk() {
		super();
	}

	public ReservationRoomPk(Integer idUser, Integer idHotel) {
		super();
		this.idUser = idUser;
		this.idHotel = idHotel;
	}

	public Integer getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Integer idHotel) {
		this.idHotel = idHotel;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idHotel == null) ? 0 : idHotel.hashCode());
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
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
		ReservationRoomPk other = (ReservationRoomPk) obj;
		if (idHotel == null) {
			if (other.idHotel != null)
				return false;
		} else if (!idHotel.equals(other.idHotel))
			return false;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		return true;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ReservationRoomPk [idUser=" + idUser + ", idHotel=" + idHotel + "]";
	}


	


	

	
}
