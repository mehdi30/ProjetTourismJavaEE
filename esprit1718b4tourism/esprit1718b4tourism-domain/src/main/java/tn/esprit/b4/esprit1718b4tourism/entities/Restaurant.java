package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Restaurant  implements Serializable  {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer id_user;
	private String name;
	private String country;
	private String town;
	private String address;
	private float fork_spoon;
	private String view;
	private Integer coins;
	private String specialite;
	private String image;
	private String owner;
	
	@ManyToMany(mappedBy= "restaurants")
	private List<User> users;
	
	
	public Restaurant() {
		super();
	}


	public Restaurant(Integer id, Integer id_user, String name, String country, String town, String address,
			float fork_spoon, String view, Integer coins, String specialite, String image,String owner) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.name = name;
		this.country = country;
		this.town = town;
		this.address = address;
		this.fork_spoon = fork_spoon;
		this.view = view;
		this.coins = coins;
		this.specialite = specialite;
		this.image = image;
		this.owner = owner;
	}

	
	

	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getId_user() {
		return id_user;
	}


	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getTown() {
		return town;
	}


	public void setTown(String town) {
		this.town = town;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public float getFork_spoon() {
		return fork_spoon;
	}


	public void setFork_spoon(float fork_spoon) {
		this.fork_spoon = fork_spoon;
	}


	public String getView() {
		return view;
	}


	public void setView(String view) {
		this.view = view;
	}


	public Integer getCoins() {
		return coins;
	}


	public void setCoins(Integer coins) {
		this.coins = coins;
	}


	public String getSpecialite() {
		return specialite;
	}


	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}
	
	

}
