package tn.dalhia.response;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;

import tn.dalhia.entities.Subscription;
import tn.dalhia.entities.enumerations.Role;

public class UserRest {

	private String userId;
	private String first_name;
	private String last_name;
	private String email;
	@Enumerated(EnumType.STRING)
	private Role role;
	private String phone;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date_birth;
	private String address;
	private String encryptedPaswword;
	private Long subscriptionId;

	private String city;

	private String state;

	private int zipCode;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDate_birth() {
		return date_birth;
	}
	public void setDate_birth(Date date_birth) {
		this.date_birth = date_birth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getEncryptedPaswword() {
		return encryptedPaswword;
	}
	public void setEncryptedPaswword(String encryptedPaswword) {
		this.encryptedPaswword = encryptedPaswword;
	}
	public Long getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}


	

}
