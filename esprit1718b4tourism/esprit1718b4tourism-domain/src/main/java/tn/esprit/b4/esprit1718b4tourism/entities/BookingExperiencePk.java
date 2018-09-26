package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class BookingExperiencePk implements Serializable{

	        
	private Integer idUser;         
	private Integer idExp;
	
	private static final long serialVersionUID = 1L;

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdExp() {
		return idExp;
	}

	public void setIdExp(Integer idExp) {
		this.idExp = idExp;
	}

	
	@Override
	public String toString() {
		return "BookingExperiencePk [idUser=" + idUser + ", idExp=" + idExp + "]";
	}

	public BookingExperiencePk(Integer idUser, Integer idExp) {
		this.idUser = idUser;
		this.idExp = idExp;
	}

	public BookingExperiencePk() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idExp == null) ? 0 : idExp.hashCode());
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
		BookingExperiencePk other = (BookingExperiencePk) obj;
		if (idExp == null) {
			if (other.idExp != null)
				return false;
		} else if (!idExp.equals(other.idExp))
			return false;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		return true;
	}

	


}
