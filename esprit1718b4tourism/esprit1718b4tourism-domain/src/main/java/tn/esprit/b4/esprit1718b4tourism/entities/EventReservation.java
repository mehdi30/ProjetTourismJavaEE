package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class EventReservation implements Serializable {

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
	private User user;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="idevent",referencedColumnName="id",insertable=false,updatable=false)
	private Event event;
	
	@EmbeddedId
	private ReservationEventPK reservationeventPK;
	
	private static final long serialVersionUID = 1L;
	
	private int nbplaces;
	private String nomevent;
	
	

	public String getNomevent() {
		return nomevent;
	}

	public void setNomevent(String nomevent) {
		this.nomevent = nomevent;
	}

	public int getNbplaces() {
		return nbplaces;
	}

	public void setNbplaces(int nbplaces) {
		this.nbplaces = nbplaces;
	}

	public EventReservation() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public ReservationEventPK getReservationeventPK() {
		return reservationeventPK;
	}

	public void setReservationeventPK(ReservationEventPK reservationeventPK) {
		this.reservationeventPK = reservationeventPK;
	}

	public EventReservation( User user, Event event, int nbplaces) {
		super();
		this.reservationeventPK=new ReservationEventPK(user.getId(),event.getId());
		
		this.user = user;
		this.event = event;
		this.nbplaces=nbplaces;
	}

	
	
	
	
	
	
	
}
