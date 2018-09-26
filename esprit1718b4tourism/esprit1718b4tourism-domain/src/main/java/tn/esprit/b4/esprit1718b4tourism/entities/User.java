package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import java.util.List;
import javax.persistence.*;


@Entity
public class User implements Serializable {
	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String firstName;
	private String lastName;
	@Temporal(TemporalType.DATE)
	private Date birthdayDate;
	private String password;
	@Temporal(TemporalType.DATE)
	private Date lastLogin;
	private String email;
	private String phone;
	private Boolean orienMember;
	
	 @Lob
	 @Column(length=10000000, columnDefinition="longblob")
	private byte[]  im;
	 
	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToMany
	private List<PlaceToGo> placetogos;
	@OneToMany(mappedBy="user")
	private List<History> historys;
	@OneToMany(mappedBy="user")
	private List<Comment> comments;
	@OneToMany(mappedBy="user")
	private List<Rating> ratings;
	@OneToMany(mappedBy="user")
	private List<Reservation> reservations;
	@OneToMany(mappedBy="user")
	private List<BookingExperience> bookingEps;
	@OneToMany(mappedBy="user")
	private List<EventReservation> listReservations;
	@OneToMany(mappedBy = "user")
	private List<ExperienceAvis> experienceAvis;
    @OneToMany(mappedBy = "user")
	private List<ExperienceAvis> experienceLike;
    @OneToMany(mappedBy="user")
	private List<Experience> listExperiences;
	private static final long serialVersionUID = 1L;
	
	@ManyToMany
	private List<Restaurant> restaurants;
	
	public List<EventReservation> getListReservations() {
		return listReservations;
	}
	public void setListReservations(List<EventReservation> listReservations) {
		this.listReservations = listReservations;
	}
	
	


	

	public List<ExperienceAvis> getExperienceAvis() {
		return experienceAvis;
	}
	public void setExperienceAvis(List<ExperienceAvis> experienceAvis) {
		this.experienceAvis = experienceAvis;
	}
	public List<ExperienceAvis> getExperienceLike() {
		return experienceLike;
	}
	public void setExperienceLike(List<ExperienceAvis> experienceLike) {
		this.experienceLike = experienceLike;
	}
	public List<Experience> getListExperiences() {
		return listExperiences;
	}
	public void setListExperiences(List<Experience> listExperiences) {
		this.listExperiences = listExperiences;
	}






	//saifeddine
	@OneToMany(mappedBy="user")
	private List<Hotel> hotels ;
			
	//saifeddine
	@OneToMany(mappedBy="user")
	private List<ReservationRoom> reservationsRoom;
		
		@OneToMany(mappedBy = "user")
		private List<Panier> paniers;
		

	public List<Panier> getPaniers() {
			return paniers;
		}
		public void setPaniers(List<Panier> paniers) {
			this.paniers = paniers;
		}
		@OneToMany(mappedBy="user")
		private List<Notification> listNotifications;


public List<Notification> getListNotifications() {
		 	return listNotifications;
		}
		public void setListNotifications(List<Notification> listNotifications) {
			this.listNotifications = listNotifications;
		}
		
public User( String firstName, String lastName, Date birthdayDate, String password,
			Date lastLogin, String email, String phone, Boolean orienMember, byte[] im, Role role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdayDate = birthdayDate;
		this.password = password;
		this.lastLogin = lastLogin;
		this.email = email;
		this.phone = phone;
		this.orienMember = orienMember;
		this.im = im;
		this.role = role;
	
}

	public User() {
	
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}   
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}   
 
	public Date getBirthdayDate() {
		return this.birthdayDate;
	}

	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}   
	public Boolean getOrienMember() {
		return this.orienMember;
	}

	public void setOrienMember(Boolean orienMember) {
		this.orienMember = orienMember;
	}   
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<PlaceToGo> getPlacetogos() {
		return placetogos;
	}
	public void setPlacetogos(List<PlaceToGo> placetogos) {
		this.placetogos = placetogos;
	}
	public List<History> getHistorys() {
		return historys;
	}
	public void setHistorys(List<History> historys) {
		this.historys = historys;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<Rating> getRatings() {
		return ratings;
	}
	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	public List<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public byte[] getIm() {
		return im;
	}
	public void setIm(byte[] im) {
		this.im = im;
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	public List<Hotel> getHotels() {
		return hotels;
	}
	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public List<BookingExperience> getBookingEps() {
		return bookingEps;
	}

	public void setBookingEps(List<BookingExperience> bookingEps) {
		this.bookingEps = bookingEps;
	}
	
	public List<ReservationRoom> getReservationsRoom() {
		return reservationsRoom;
	}

	public void setReservationsRoom(List<ReservationRoom> reservationsRoom) {
		this.reservationsRoom = reservationsRoom;
	}

	
	
	
   
}