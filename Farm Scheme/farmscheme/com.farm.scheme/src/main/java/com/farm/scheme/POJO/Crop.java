package com.farm.scheme.POJO;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "crop")
public class Crop implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String cropName;

	private String sumInsured;

	private String farmerShare;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(String sumInsured) {
		this.sumInsured = sumInsured;
	}

	public String getFarmerShare() {
		return farmerShare;
	}

	public void setFarmerShare(String farmerShare) {
		this.farmerShare = farmerShare;
	}

	public Crop() {

	}

	public Crop(String id, String cropName, String sumInsured, String farmerShare) {
		super();
		this.id = id;
		this.cropName = cropName;
		this.sumInsured = sumInsured;
		this.farmerShare = farmerShare;
	}

}
