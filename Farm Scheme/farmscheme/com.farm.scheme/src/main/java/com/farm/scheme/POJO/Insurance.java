package com.farm.scheme.POJO;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Insurance")
public class Insurance implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String insuranceCompany;

	private String sumInsuredPerHectare;

	private String sharePremium;

	private String premiumAmount;

	private String cropName;

	private String area;

	private String sumInsured;

	private String insuredName;

	private String farmerId;

	// -------------------------

	private String status;

	private String causeOfLoss;

	private String dateOfLoss;

	private String supportingDocument;
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCauseOfLoss() {
		return causeOfLoss;
	}

	public void setCauseOfLoss(String causeOfLoss) {
		this.causeOfLoss = causeOfLoss;
	}

	public String getDateOfLoss() {
		return dateOfLoss;
	}

	public void setDateOfLoss(String dateOfLoss) {
		this.dateOfLoss = dateOfLoss;
	}

	public String getSupportingDocument() {
		return supportingDocument;
	}

	public void setSupportingDocument(String supportingDocument) {
		this.supportingDocument = supportingDocument;
	}

	public String getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(String farmerId) {
		this.farmerId = farmerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public String getSumInsuredPerHectare() {
		return sumInsuredPerHectare;
	}

	public void setSumInsuredPerHectare(String sumInsuredPerHectare) {
		this.sumInsuredPerHectare = sumInsuredPerHectare;
	}

	public String getSharePremium() {
		return sharePremium;
	}

	public void setSharePremium(String sharePremium) {
		this.sharePremium = sharePremium;
	}

	public String getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(String premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(String sumInsured) {
		this.sumInsured = sumInsured;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public Insurance() {

	}

	public Insurance(String id, String insuranceCompany, String sumInsuredPerHectare, String sharePremium,
			String premiumAmount, String cropName, String area, String sumInsured, String insuredName, String farmerId,
			String status, String causeOfLoss, String dateOfLoss, String supportingDocument) {
		super();
		this.id = id;
		this.insuranceCompany = insuranceCompany;
		this.sumInsuredPerHectare = sumInsuredPerHectare;
		this.sharePremium = sharePremium;
		this.premiumAmount = premiumAmount;
		this.cropName = cropName;
		this.area = area;
		this.sumInsured = sumInsured;
		this.insuredName = insuredName;
		this.farmerId = farmerId;
		this.status = status;
		this.causeOfLoss = causeOfLoss;
		this.dateOfLoss = dateOfLoss;
		this.supportingDocument = supportingDocument;
	}

	

}
