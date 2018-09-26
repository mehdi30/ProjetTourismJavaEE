package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Float;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

@Entity 
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Type_Place")
public class PlaceToGo implements Serializable {
	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer id_user;
	private String name;
	private String country;
	private String town;
	private String address;
	private Integer visitors;
	private Double longitude;
	private Double latitude;
	private Float promotion;
	private Category category;
	@ManyToMany(mappedBy= "placetogos")
	private List<User> users;
	
	@OneToMany(mappedBy = "placetogo")
	private List<Image> images;
	
	@OneToMany(mappedBy="placetogo")
	private List<History> historys;
	@OneToMany(mappedBy="placetogo")
	private List<Comment> comments;
	@OneToMany(mappedBy="placetogo")
	private List<Rating> ratings;
	@OneToMany(mappedBy="placetogo")
	private List<Reservation> reservations;
	private static final long serialVersionUID = 1L;

	public PlaceToGo() {
		super();
	}   
	
	
	public PlaceToGo(Integer id_user, String name, String country, String town, String address,
			Integer visitors, Double longitude, Double latitude, Float promotion, Category category) {
		super();
	
		this.id_user = id_user;
		this.name = name;
		this.country = country;
		this.town = town;
		this.address = address;
		this.visitors = visitors;
		this.longitude = longitude;
		this.latitude = latitude;
		this.promotion = promotion;
		this.category = category;
	
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public Integer getId_user() {
		return this.id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}   
	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}   
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}   
	public Integer getVisitors() {
		return this.visitors;
	}

	public void setVisitors(Integer visitors) {
		this.visitors = visitors;
	}   
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}   
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}   
	public Float getPromotion() {
		return this.promotion;
	}

	public void setPromotion(Float promotion) {
		this.promotion = promotion;
	}   
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public List<History> getHistorys() {
		return historys;
	}
	public void setHistorys(List<History> historys) {
		this.historys = historys;
	}
   
	
	
}
