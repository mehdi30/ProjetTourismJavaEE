package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class ReservationPK  implements Serializable {   
   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;         
	private Integer idUser;         
	private Integer idPlaceToGo;

	private static final long serialVersionUID = 1L;

	public ReservationPK() {}
	
	

	public ReservationPK( Integer idUser, Integer idPlaceToGo) {
		super();
		this.idUser = idUser;
		this.idPlaceToGo = idPlaceToGo;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdPlaceToGo() {
		return idPlaceToGo;
	}

	public void setIdPlaceToGo(Integer idPlaceToGo) {
		this.idPlaceToGo = idPlaceToGo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idPlaceToGo == null) ? 0 : idPlaceToGo.hashCode());
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
		ReservationPK other = (ReservationPK) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idPlaceToGo == null) {
			if (other.idPlaceToGo != null)
				return false;
		} else if (!idPlaceToGo.equals(other.idPlaceToGo))
			return false;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		return true;
	}

	
   
}
