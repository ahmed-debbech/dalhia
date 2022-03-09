package tn.dalhia.shared.dto;

import java.util.Date;

public class SubscriptionDto {
	private Long id;
	private Date date_debut;
	private Date date_fin;
	private String message;
	private String subscritpionId; 
	private Long planId;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getSubscritpionId() {
		return subscritpionId;
	}
	public void setSubscritpionId(String subscritpionId) {
		this.subscritpionId = subscritpionId;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	
	
	
}
