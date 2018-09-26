package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue("Experience")
public class Experience implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String img;
	private String location;
	private String description;
	private String duration;
	private Integer price;
	private int nbplaces;
	private int nbpart;
	private String starTime;
	@ManyToOne
	private User user;
	@OneToMany(mappedBy = "experience")
	private List<BookingExperience> bookingEps;
	@OneToMany(mappedBy = "experience")
	private List<ExperienceAvis> experienceAvis;
	@OneToMany(mappedBy = "experience")
	private List<ExperienceAvis> experienceLike;


	@Temporal(TemporalType.DATE)
	private Date dateExperience;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public List<BookingExperience> getBookingEps() {
		return bookingEps;
	}

	public void setBookingEps(List<BookingExperience> bookingEps) {
		this.bookingEps = bookingEps;
	}

	public Date getDateExperience() {
		return dateExperience;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDateExperience(Date dateExperience) {
		this.dateExperience = dateExperience;
	}

	public String getStarTime() {
		return starTime;
	}

	public void setStarTime(String starTime) {
		this.starTime = starTime;
	}

	public List<ExperienceAvis> getExperienceLike() {
		return experienceLike;
	}

	public void setExperienceLike(List<ExperienceAvis> experienceLike) {
		this.experienceLike = experienceLike;
	}

	

	public Experience() {
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getNbplaces() {
		return nbplaces;
	}

	public void setNbplaces(int nbplaces) {
		this.nbplaces = nbplaces;
	}

	public int getNbpart() {
		return nbpart;
	}

	public void setNbpart(int nbpart) {
		this.nbpart = nbpart;
	}

	public List<ExperienceAvis> getExperienceAvis() {
		return experienceAvis;
	}

	public void setExperienceAvis(List<ExperienceAvis> experienceAvis) {
		this.experienceAvis = experienceAvis;
	}

	public Experience(String name, String location, String description, String duration, Integer price,
			String starTime, Integer nbplaces,User user) {
		this.name = name;
		this.location = location;
		this.description = description;
		this.duration = duration;
		this.price = price;
		this.starTime = starTime;
		this.nbplaces = nbplaces;
		this.user = user;

	}

	public Experience(String name, String img, String location, String description, String duration, Integer price,
			int nbplaces, int nbpart, String starTime) {
		super();
		this.name = name;
		this.img = img;
		this.location = location;
		this.description = description;
		this.duration = duration;
		this.price = price;
		this.nbplaces = nbplaces;
		this.nbpart = nbpart;
		this.starTime = starTime;
	}

	public Experience(String name, String location, String description, String duration, Integer price, int nbplaces,
			String starTime,Date dateExperience) {
		super();
		this.name = name;
		this.location = location;
		this.description = description;
		this.duration = duration;
		this.price = price;
		this.nbplaces = nbplaces;
		this.starTime = starTime;
		this.dateExperience = dateExperience;

	}
	public Experience(String name, String location, String description, String duration, Integer price, int nbplaces,
			String starTime,Date dateExperience, Integer id) {
		super();
		this.id=id;
		this.name = name;
		this.location = location;
		this.description = description;
		this.duration = duration;
		this.price = price;
		this.nbplaces = nbplaces;
		this.starTime = starTime;
		this.dateExperience = dateExperience;

	}
	@Override
	public String toString() {
		return "Experience [id=" + id + ", name=" + name + ", img=" + img + ", location=" + location + ", description="
				+ description + ", duration=" + duration + ", price=" + price + ", nbplaces=" + nbplaces + ", nbpart="
				+ nbpart + ", starTime=" + starTime + ", user=" + user + ", bookingEps=" + bookingEps
				+ ", experienceAvis=" + experienceAvis + ", experienceLike=" + experienceLike + ", dateExperience="
				+ dateExperience + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingEps == null) ? 0 : bookingEps.hashCode());
		result = prime * result + ((dateExperience == null) ? 0 : dateExperience.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((experienceAvis == null) ? 0 : experienceAvis.hashCode());
		result = prime * result + ((experienceLike == null) ? 0 : experienceLike.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((img == null) ? 0 : img.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + nbpart;
		result = prime * result + nbplaces;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((starTime == null) ? 0 : starTime.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Experience other = (Experience) obj;
		if (bookingEps == null) {
			if (other.bookingEps != null)
				return false;
		} else if (!bookingEps.equals(other.bookingEps))
			return false;
		if (dateExperience == null) {
			if (other.dateExperience != null)
				return false;
		} else if (!dateExperience.equals(other.dateExperience))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (experienceAvis == null) {
			if (other.experienceAvis != null)
				return false;
		} else if (!experienceAvis.equals(other.experienceAvis))
			return false;
		if (experienceLike == null) {
			if (other.experienceLike != null)
				return false;
		} else if (!experienceLike.equals(other.experienceLike))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (img == null) {
			if (other.img != null)
				return false;
		} else if (!img.equals(other.img))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nbpart != other.nbpart)
			return false;
		if (nbplaces != other.nbplaces)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (starTime == null) {
			if (other.starTime != null)
				return false;
		} else if (!starTime.equals(other.starTime))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	

	
	
}
