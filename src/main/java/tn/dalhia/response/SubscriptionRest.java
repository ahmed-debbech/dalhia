package tn.dalhia.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class SubscriptionRest {
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date_debut;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date_fin;
	private String message ;
	private String subscriptionId;
	private String userId;
	private Long planId;
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
	
	public String getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	
	
}
