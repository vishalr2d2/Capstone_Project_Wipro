package com.farm.scheme.POJO;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sellrequest")
public class SellRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

//	@DBRef
	private String farmerId;

	private String cropType;

	private String cropName;

	private String fertilizerType;

	private Integer quantity;

	private String soilPhCertificate;

	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(String farmerId) {
		this.farmerId = farmerId;
	}

	public String getCropType() {
		return cropType;
	}

	public void setCropType(String cropType) {
		this.cropType = cropType;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getFertilizerType() {
		return fertilizerType;
	}

	public void setFertilizerType(String fertilizerType) {
		this.fertilizerType = fertilizerType;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSoilPhCertificate() {
		return soilPhCertificate;
	}

	public void setSoilPhCertificate(String soilPhCertificate) {
		this.soilPhCertificate = soilPhCertificate;
	}

	public String getStatus() {
		return status;
	}

	public void setStauts(String status) {
		this.status = status;
	}

	public SellRequest() {
	}

	public SellRequest(String id, String farmerId, String cropType, String cropName, String fertilizerType,
			Integer quantity, String soilPhCertificate, String status) {
		super();
		this.id = id;
		this.farmerId = farmerId;
		this.cropType = cropType;
		this.cropName = cropName;
		this.fertilizerType = fertilizerType;
		this.quantity = quantity;
		this.soilPhCertificate = soilPhCertificate;
		this.status = status;
	}

}
