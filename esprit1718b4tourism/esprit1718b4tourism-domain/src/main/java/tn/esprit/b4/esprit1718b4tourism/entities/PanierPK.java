package tn.esprit.b4.esprit1718b4tourism.entities;


import java.io.Serializable;
import javax.persistence.*;
@Embeddable
public class PanierPK implements Serializable {
	
	private int idSouvenir;
	private int idUser;
	private static final long serialVersionUID = 1L;
	public int getIdSouvenir() {
		return idSouvenir;
	}
	public void setIdSouvenir(int idSouvenir) {
		this.idSouvenir = idSouvenir;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idSouvenir;
		result = prime * result + idUser;
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
		PanierPK other = (PanierPK) obj;
		if (idSouvenir != other.idSouvenir)
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}
	public PanierPK()
	{
		
		super();
	}
	public PanierPK(int idSouvenir, int idUser) {
		super();
		this.idSouvenir = idSouvenir;
		this.idUser = idUser;
	}
	
}
