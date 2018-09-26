package tn.esprit.b4.esprit1718b4tourism.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Panier implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;

	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idSouvenir", referencedColumnName = "idSouvenir", insertable = false, updatable = false)
private Souvenir souvenirs;
	
	@EmbeddedId
	private PanierPK panierPk;
	
	private String name;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	
	
	
	public Souvenir getSouvenirs() {
		return souvenirs;
	}

	public void setSouvenirs(Souvenir souvenirs) {
		this.souvenirs = souvenirs;
		
		

}

	public Panier() {
		super();
	}
	

	public PanierPK getPanierPk() {
		return panierPk;
	}

	public void setPanierPk(PanierPK panierPk) {
		this.panierPk = panierPk;
	}
	private Date DateCmd;
	
	private float price;
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

	public Date getDateCmd() {
		return DateCmd;
	}

	public void setDateCmd(Date dateCmd) {
		DateCmd = dateCmd;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Panier(User user, Souvenir souvenirs, int quantity) {
		super();
		this.panierPk=new PanierPK(user.getId(),souvenirs.getIdSouvenir());
		
		this.user = user;
		this.souvenirs = souvenirs;
		this.quantity = quantity;
	}


}
