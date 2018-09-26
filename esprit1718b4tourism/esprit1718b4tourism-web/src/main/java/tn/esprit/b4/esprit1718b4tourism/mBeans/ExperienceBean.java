package tn.esprit.b4.esprit1718b4tourism.mBeans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.b4.esprit1718b4tourism.entities.Experience;
import tn.esprit.b4.esprit1718b4tourism.services.ExperienceServiceLocal;

@ManagedBean
@SessionScoped
public class ExperienceBean {
	
	private String name;
	private String location;
	private String description;
	private String duration;
	private Integer price;
	private String starTime;
	private int nbplaces;
	private int user;
	private Date datExperience;
	static Integer idToUpdate;

	public Date getDatExperience() {
		return datExperience;
	}

	public void setDatExperience(Date datExperience) {
		this.datExperience = datExperience;
	}


	private List<Experience> experiences ;
	
	/*public List<Experience> getExperience(){
		return experience= experienceService.showAllExperiences();
	}
	
	public void setExperience(List<Experience> experiences) {
		this.experience = experiences;
	}*/
	public ExperienceServiceLocal getExperienceService() {
		return experienceService;
	}
	public List<Experience> getExperiences() {
		 experiences= experienceService.showAllExperiences();
		return experiences;
	}

	public void setExperiences(List<Experience> experience) {
		this.experiences = experience;
	}

	public void setExperienceService(ExperienceServiceLocal experienceService) {
		this.experienceService = experienceService;
	}

	@EJB
	ExperienceServiceLocal experienceService ;
	
	@PostConstruct
	public void init(){
		datExperience = new Date();
	}
	public void addExperience(){
		experienceService.AddExperience(new Experience(name, location, description, duration, price, nbplaces, starTime,datExperience));
	}
	public void editExperience(){
		experienceService.UpdateExperience(new Experience(name, location, description, duration, price, nbplaces, starTime,datExperience,idToUpdate));

	}
	public void modifierExperience(Experience e){
		this.setName(e.getName());
		this.setLocation(e.getLocation());
		this.setDescription(e.getDescription());
		this.setDuration(e.getDuration());
		this.setPrice(e.getPrice());
		this.setNbplaces(e.getNbplaces());
		this.setStarTime(e.getStarTime());
		this.setDatExperience(e.getDateExperience());
		idToUpdate=e.getId();
	}
	public void deleteExp(int id){
		experienceService.DeleteById(id);
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

	public String getStarTime() {
		return starTime;
	}

	public void setStarTime(String starTime) {
		this.starTime = starTime;
	}

	public int getNbplaces() {
		return nbplaces;
	}

	public void setNbplaces(int nbplaces) {
		this.nbplaces = nbplaces;
	}
	

}
