package com.ridho.cloudinary.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Images {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String imagesUrl;
	private String imagesId;
	
	public Images() {
		super();
	}
	
	public Images(String name, String imagesUrl, String imagesId) {
		this.name = name;
		this.imagesUrl = imagesUrl;
		this.imagesId = imagesId;
	}
	
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
	public String getImagesUrl() {
		return imagesUrl;
	}
	public void setImagesUrl(String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}
	public String getImagesId() {
		return imagesId;
	}
	public void setImagesId(String imagesId) {
		this.imagesId = imagesId;
	}
	
}
