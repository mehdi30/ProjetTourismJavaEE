package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ExperienceLike implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ExperienceLikePk ExperienceLikePk;

	private String type;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="idExp",referencedColumnName="id",insertable=false,updatable=false)
	private Experience experience;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
	private User user;
	
	public ExperienceLike(String type, Experience experience, User user) {
		this.ExperienceLikePk = new ExperienceLikePk(user.getId(), experience.getId());
		this.type = type;
		this.experience = experience;
		this.user = user;
	}

	public ExperienceLikePk getExperienceLikePk() {
		return ExperienceLikePk;
	}

	public void setExperienceLikePk(ExperienceLikePk experienceLikePk) {
		ExperienceLikePk = experienceLikePk;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ExperienceLikePk == null) ? 0 : ExperienceLikePk.hashCode());
		result = prime * result + ((experience == null) ? 0 : experience.hashCode());
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
		ExperienceLike other = (ExperienceLike) obj;
		if (ExperienceLikePk == null) {
			if (other.ExperienceLikePk != null)
				return false;
		} else if (!ExperienceLikePk.equals(other.ExperienceLikePk))
			return false;
		if (experience == null) {
			if (other.experience != null)
				return false;
		} else if (!experience.equals(other.experience))
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

	public ExperienceLike() {
	}

	@Override
	public String toString() {
		return "ExperienceLike [ExperienceLikePk=" + ExperienceLikePk + ", type=" + type + ", experience=" + experience
				+ ", user=" + user + "]";
	}
	
	
	
	
	

}
