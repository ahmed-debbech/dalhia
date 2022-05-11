package tn.dalhia.request;

import java.util.List;

public class CommandRequestModel {


	
	private String name;
	private String email;
	private int card;
	private int code;

	private List<CommandRequestProducts> products;
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

	public List<CommandRequestProducts> getProducts() {
		return products;
	}
	public void setProducts(List<CommandRequestProducts> products) {
		this.products = products;
	}



}
