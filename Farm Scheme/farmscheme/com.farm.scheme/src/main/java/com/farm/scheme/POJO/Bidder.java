package com.farm.scheme.POJO;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Bidder")
public class Bidder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String fullName;

	private String contactNo;

	private String emailId;

	private String addressOne;

	private String addressTwo;

	private String city;

	private String state;

	private String pinCode;

	private String accountNo;

	private String ifscCode;

	private String aadhaar;

	private String pan;

	private String traderLicense;

	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddressOne() {
		return addressOne;
	}

	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}

	public String getAddressTwo() {
		return addressTwo;
	}

	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getAadhaar() {
		return aadhaar;
	}

	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getTraderLicense() {
		return traderLicense;
	}

	public void setTraderLicense(String traderLicense) {
		this.traderLicense = traderLicense;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Bidder() {

	}

	public Bidder(String id, String fullName, String contactNo, String emailId, String addressOne, String addressTwo,
			String city, String state, String pinCode, String accountNo, String ifscCode, String aadhaar, String pan,
			String traderLicense, String password) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.contactNo = contactNo;
		this.emailId = emailId;
		this.addressOne = addressOne;
		this.addressTwo = addressTwo;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.accountNo = accountNo;
		this.ifscCode = ifscCode;
		this.aadhaar = aadhaar;
		this.pan = pan;
		this.traderLicense = traderLicense;
		this.password = password;
	}

}
