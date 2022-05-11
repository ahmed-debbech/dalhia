package tn.dalhia.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
public class CommandProduct implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
		
		private int quantityProduct;
		
		@ManyToOne
		Product products;
		
		@ManyToOne
		Command commands;
	
}
