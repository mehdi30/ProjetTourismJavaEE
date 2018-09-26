package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Event implements Serializable {
	
	 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 private int id;
	 private String nom;
	 private String description;
	 private String adresse;
	 public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	private String TypeEvent;
	 private int nbparticipant;
	 private String image_event;
     private int idUser;
	 
	

	 public String getImage_event() {
		return image_event;
	}
	public void setImage_event(String image_event) {
		this.image_event = image_event;
	}
	public int getNbparticipant() {
		return nbparticipant;
	}
	public void setNbparticipant(int nbparticipant) {
		this.nbparticipant = nbparticipant;
	}
	public Date getDateEvent() {
		return dateEvent;
	}
	public void setDateEvent(Date dateEvent) {
		this.dateEvent = dateEvent;
	}

	@Temporal(TemporalType.DATE)
		private Date dateEvent;
	 
	 @Temporal(TemporalType.DATE)
		private Date datefin;
		
		
		private int price;
		private int nbplaces;
	 
		
		
	
		public Date getDatefin() {
			return datefin;
		}
		public void setDatefin(Date datefin) {
			this.datefin = datefin;
		}
	
	public String getAdresse() {
			return adresse;
		}
		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

	@OneToMany(mappedBy="event")
	 private List<EventReservation> listReservations;
	
	 public List<EventReservation> getListReservations() {
		return listReservations;
	}
	public void setListReservations(List<EventReservation> listReservations) {
		this.listReservations = listReservations;
	}
	
	public Event()
	{
		
		
	}
	
	/*public Event(String nom)
	
	{
		this.nom=nom;
		
	}*/
	public Event(String nom, String description)
	{
		

	this.nom=nom;
	this.description=description;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNbplaces() {
		return nbplaces;
	}
	public void setNbplaces(int nbplaces) {
		this.nbplaces = nbplaces;
	}
	
	public Event(String nom, String description, String adresse, int price, int nbplaces) {
		super();
		this.nom = nom;
		this.description = description;
		this.adresse = adresse;
		this.price = price;
		this.nbplaces = nbplaces;
	}
	
	public Event(String nom, String description, String adresse, int price, int nbplaces,String typeEvent) {
		super();
		this.nom = nom;
		this.description = description;
		this.adresse = adresse;
		this.price = price;
		this.nbplaces = nbplaces;
		this.TypeEvent=typeEvent;
	}
	
	public Event(String nom, String description, String adresse, int price, int nbplaces,String typeEvent, int idUser) {
		super();
		this.nom = nom;
		this.description = description;
		this.adresse = adresse;
		this.price = price;
		this.nbplaces = nbplaces;
		this.TypeEvent=typeEvent;
		this.idUser=idUser;
	}
	
	
	
	public Event(String nom, String description, String adresse) {
		super();
		this.nom = nom;
		this.description = description;
		this.adresse = adresse;
	}
	

	public Event(String nom, String description, String adresse, int nbplaces) {
		super();
		this.nom = nom;
		this.description = description;
		this.adresse = adresse;
		
		this.nbplaces = nbplaces;
	}
	
	
	
	public String getTypeEvent() {
		return TypeEvent;
	}
	public void setTypeEvent(String typeEvent) {
		TypeEvent = typeEvent;
	}
	public Event(int id, String nom, int nbplaces) {
		super();
		this.id = id;
		this.nom = nom;
		this.nbplaces = nbplaces;
	}
	/*
	public Event(String nom, String description, String adresse, int price, int nbplaces,String typeEvent, int nbpart) {
		super();
		this.nom = nom;
		this.description = description;
		this.adresse = adresse;
		this.price = price;
		this.nbplaces = nbplaces;
		this.TypeEvent=typeEvent;
		this.nbparticipant=nbpart;
	}*/
	
	public Event(String typeEvent) {
		
		this.TypeEvent = typeEvent;
	}
	public Event(int price) {
		super();
		this.price = price;
	}
	

}
	


