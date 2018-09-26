package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;
import java.lang.Integer;

import javax.persistence.Embeddable;

/**
 * ID class for entity: History
 *
 */ 
@Embeddable
public class RatingPK  implements Serializable {   
   
	         
	private Integer idUser;         
	private Integer idPlaceToGo;
	private static final long serialVersionUID = 1L;

	public RatingPK() {}

	

	public RatingPK(Integer idUser, Integer idPlaceToGo) {
		super();
		this.idUser = idUser;
		this.idPlaceToGo = idPlaceToGo;
	}



	public Integer getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	

	public Integer getIdPlaceToGo() {
		return this.idPlaceToGo;
	}

	public void setIdPlaceToGo(Integer idPlaceToGo) {
		this.idPlaceToGo = idPlaceToGo;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof RatingPK)) {
			return false;
		}
		RatingPK other = (RatingPK) o;
		return true
			&& (getIdUser() == null ? other.getIdUser() == null : getIdUser().equals(other.getIdUser()))
			&& (getIdPlaceToGo() == null ? other.getIdPlaceToGo() == null : getIdPlaceToGo().equals(other.getIdPlaceToGo()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getIdUser() == null ? 0 : getIdUser().hashCode());
		result = prime * result + (getIdPlaceToGo() == null ? 0 : getIdPlaceToGo().hashCode());
		return result;
	}
   
   
}
