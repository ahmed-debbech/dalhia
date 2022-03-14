package tn.dalhia.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Command implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
	
	@Column(nullable=false)
	private String commandId;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String email;
	@Column(nullable=false)
	private int card;
	@Column(nullable=false , length=3)
	private int code;
//	@Column(nullable=false )
//	private int quantity;
	
	@ManyToOne
	User users;
	
//	@ManyToMany()
//	private List<Product> products;
	
	@OneToMany(mappedBy="commands")
	private List<CommandProduct> quantities;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

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
	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
//	public List<Product> getProducts() {
//		return products;
//	}
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
//	public int getQuantity() {
//		return quantity;
//	}
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}
//	
	public List<CommandProduct> getQuantities() {
		return quantities;
	}
	public void setQuantities(List<CommandProduct> quantities) {
		this.quantities = quantities;
	}
	


}
