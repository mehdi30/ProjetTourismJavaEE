package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ReservationEventPK implements Serializable {

	private int idUser;
	private int idevent;
	//private int nbplaces;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUser;
		result = prime * result + idevent;
		//result = prime * result + nbplaces;
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
		ReservationEventPK other = (ReservationEventPK) obj;
		if (idUser != other.idUser)
			return false;
		if (idevent != other.idevent)
			return false;
		//if (nbplaces != other.nbplaces)
			//return false;
		return true;
	}

	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdevent() {
		return idevent;
	}
	public void setIdevent(int idevent) {
		this.idevent = idevent;
	}
	
	
	
	public ReservationEventPK()
	{
		
		
	}
	
	public  ReservationEventPK(int idUser,int idevent) {
	
		super();
		this.idUser = idUser;
		this.idevent = idevent;
	}
	
	
	/*public int getNbplaces() {
		return nbplaces;
	}
	public void setNbplaces(int nbplaces) {
		this.nbplaces = nbplaces;
	}*/
	
	
	
	
}
