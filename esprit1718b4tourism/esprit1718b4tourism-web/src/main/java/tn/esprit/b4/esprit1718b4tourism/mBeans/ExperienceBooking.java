package tn.esprit.b4.esprit1718b4tourism.mBeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.b4.esprit1718b4tourism.services.BookingExperienceServiceLocal;

@ManagedBean
@SessionScoped
public class ExperienceBooking {
	
	private Integer idExp;
	private Integer idUser;
	private Integer num;
	private String name;
	private String location;

	
	@EJB
	BookingExperienceServiceLocal BookingService;
	
	public void bookExperience (Integer idExp,String name,String location){
		BookingService.AddBookingExperience(3,idExp,1, name, location);
	}

	public Integer getIdExp() {
		return idExp;
	}

	public void setIdExp(Integer idExp) {
		this.idExp = idExp;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
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
	
	
	
	
	
}
