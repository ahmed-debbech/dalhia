package tn.dalhia.response;

import java.util.List;

public class CommandProductRest {

	private String commandId;
	private String name;
	private String email;
	private String userId;
	private List<Long> idProducts;
	private List<Integer> quantity;
	public String getCommandId() {
		return commandId;
	}
	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Long> getIdProducts() {
		return idProducts;
	}
	public void setIdProducts(List<Long> idProducts) {
		this.idProducts = idProducts;
	}
	public List<Integer> getQuantity() {
		return quantity;
	}
	public void setQuantity(List<Integer> quantity) {
		this.quantity = quantity;
	}

	
	
	
}
