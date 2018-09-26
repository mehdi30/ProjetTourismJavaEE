package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;


@Entity
public class Image implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String image;
	@ManyToOne
	private PlaceToGo placetogo;
	private static final long serialVersionUID = 1L;

	public Image() {
		super();
	}   
	
	
	
	public Image( String image, PlaceToGo placetogo) {
		super();
		this.image = image;
		this.placetogo = placetogo;
	}



	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	public PlaceToGo getPlacetogo() {
		return placetogo;
	}
	public void setPlacetogo(PlaceToGo placetogo) {
		this.placetogo = placetogo;
	}
	
	
   
}
