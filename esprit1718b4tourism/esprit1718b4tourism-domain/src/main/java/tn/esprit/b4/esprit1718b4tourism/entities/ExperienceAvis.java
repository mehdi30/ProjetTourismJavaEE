package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ExperienceAvis implements Serializable {

	@EmbeddedId
	private ExperienceAvisPK ExperienceAvisPK;

	private double rate;
	private String type;

	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="idExp",referencedColumnName="id",insertable=false,updatable=false)
	private Experience experience;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
	private User user;
	public ExperienceAvisPK getExperienceAvisPK() {
		return ExperienceAvisPK;
	}
	public void setExperienceAvisPK(ExperienceAvisPK experienceAvisPK) {
		ExperienceAvisPK = experienceAvisPK;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}

	public Experience getExperience() {
		return experience;
	}
	public void setExperience(Experience experience) {
		this.experience = experience;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ExperienceAvis() {
	}
	public ExperienceAvis(double rate, String type, Experience experience, User user) {
		this.ExperienceAvisPK = new ExperienceAvisPK(user.getId(), experience.getId());
		this.rate = rate;
		this.type = type;
		this.experience = experience;
		this.user = user;
	}
	@Override
	public String toString() {
		return "ExperienceAvis [ExperienceAvisPK=" + ExperienceAvisPK + ", rate=" + rate + ", type=" + type
				+ ", experience=" + experience + ", user=" + user + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ExperienceAvisPK == null) ? 0 : ExperienceAvisPK.hashCode());
		result = prime * result + ((experience == null) ? 0 : experience.hashCode());
		long temp;
		temp = Double.doubleToLongBits(rate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ExperienceAvis other = (ExperienceAvis) obj;
		if (ExperienceAvisPK == null) {
			if (other.ExperienceAvisPK != null)
				return false;
		} else if (!ExperienceAvisPK.equals(other.ExperienceAvisPK))
			return false;
		if (experience == null) {
			if (other.experience != null)
				return false;
		} else if (!experience.equals(other.experience))
			return false;
		if (Double.doubleToLongBits(rate) != Double.doubleToLongBits(other.rate))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
		
	
}
