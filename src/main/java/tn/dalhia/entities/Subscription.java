package tn.dalhia.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Subscription implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private Date date_debut;
	@Column(nullable=false)
	private Date date_fin;
	@Column(nullable=false)
	private String subscritpionId;
	
	@OneToOne(mappedBy="subscriptions") //bi
	private User userDetails;
	
	@ManyToOne
	private Plan plans ; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public User getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}

	public String getSubscritpionId() {
		return subscritpionId;
	}

	public void setSubscritpionId(String subscritpionId) {
		this.subscritpionId = subscritpionId;
	}

	public Plan getPlans() {
		return plans;
	}

	public void setPlans(Plan plans) {
		this.plans = plans;
	} 


}
