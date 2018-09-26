package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ExperienceAvisPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idUser;
	private Integer idExp;
	@Override
	public String toString() {
		return "ExperienceAvisPK [idUser=" + idUser + ", idExp=" + idExp + "]";
	}
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
		ExperienceAvisPK other = (ExperienceAvisPK) obj;
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
	public ExperienceAvisPK(Integer idUser, Integer idExp) {
		this.idUser = idUser;
		this.idExp = idExp;
	}
	public ExperienceAvisPK() {
	}
	
    
}
