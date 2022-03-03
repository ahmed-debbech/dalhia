package tn.dalhia.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.dalhia.entities.Product;
import tn.dalhia.entities.User;

public class CommandRest {

	private String commandId;
	private String name;
	private String email;
	private int card;
	private int code;
	private List<Product> products;
	@JsonIgnore
	private User users;
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
	public int getCard() {
		return card;
	}
	public void setCard(int card) {
		this.card = card;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
	
	
}
