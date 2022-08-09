package com.farm.scheme.POJO;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "purchaseRequest")
public class PurchaseRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String farmerId;

	private String cropType;

	private String cropName;

	private String fertilizerType;

	private Integer quantity;

	private String soilPhCertificate;

	private String bidderId;

	private String amount;

	private String status;

	private String sellRequestId;

	private String fullName;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

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

	public String getBidderId() {
		return bidderId;
	}

	public void setBidderId(String bidderId) {
		this.bidderId = bidderId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSellRequestId() {
		return sellRequestId;
	}

	public void setSellRequestId(String sellRequestId) {
		this.sellRequestId = sellRequestId;
	}

	public PurchaseRequest() {

	}

	public PurchaseRequest(String id, String farmerId, String cropType, String cropName, String fertilizerType,
			Integer quantity, String soilPhCertificate, String bidderId, String amount, String status,
			String sellRequestId, String fullName) {
		super();
		this.id = id;
		this.farmerId = farmerId;
		this.cropType = cropType;
		this.cropName = cropName;
		this.fertilizerType = fertilizerType;
		this.quantity = quantity;
		this.soilPhCertificate = soilPhCertificate;
		this.bidderId = bidderId;
		this.amount = amount;
		this.status = status;
		this.sellRequestId = sellRequestId;
		this.fullName = fullName;
	}

}
