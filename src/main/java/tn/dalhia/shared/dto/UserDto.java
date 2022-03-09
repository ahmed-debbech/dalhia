package tn.dalhia.shared.dto;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import tn.dalhia.entities.enumerations.Job;
import tn.dalhia.entities.enumerations.Role;
import tn.dalhia.entities.enumerations.Speciality;

public class UserDto implements Serializable {


	private static final long serialVersionUID = 1L;

	private Long id;
	private String userId;
	@Enumerated(EnumType.STRING)
	private Role role;

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

	private LocalTime start_hour;
	private LocalTime end_hour;
	@Enumerated(EnumType.STRING)
	private Job job;

	@Enumerated(EnumType.STRING)
	private Speciality speciality;


	private String encryptedPaswword;

	private Boolean emailVerificationStatus = false;
	private String emailVerificationToken;

	// zid mtaa product f lkol rest ,req...


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
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
	public LocalTime getStart_hour() {
		return start_hour;
	}
	public void setStart_hour(LocalTime start_hour) {
		this.start_hour = start_hour;
	}
	public LocalTime getEnd_hour() {
		return end_hour;
	}
	public void setEnd_hour(LocalTime end_hour) {
		this.end_hour = end_hour;
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
	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public String getEncryptedPaswword() {
		return encryptedPaswword;
	}

	public void setEncryptedPaswword(String encryptedPaswword) {
		this.encryptedPaswword = encryptedPaswword;
	}

}
