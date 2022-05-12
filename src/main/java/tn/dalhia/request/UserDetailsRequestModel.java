package tn.dalhia.request;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import tn.dalhia.entities.enumerations.Job;
import tn.dalhia.entities.enumerations.Speciality;

public class UserDetailsRequestModel {

 
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String password;
    private Date date_birth;
    private String address;
    private String city;
    private String state;
    private int zipCode;
    //private SubscriptionRequestModel subscriptions;
    // zid mtaa product f lkol rest ,req...
    
    
    @Enumerated(EnumType.STRING)
    private Job job;
    
    @Enumerated(EnumType.STRING)
    private Speciality speciality;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	/*public SubscriptionRequestModel getSubscription() {
		return subscriptions;
	}

	public void setSubscription(SubscriptionRequestModel subscription) {
		this.subscriptions = subscription;
	}


	public SubscriptionRequestModel getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(SubscriptionRequestModel subscriptions) {
		this.subscriptions = subscriptions;
	}*/

}
