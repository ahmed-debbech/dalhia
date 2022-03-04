package tn.dalhia.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import java.util.Date;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private Long PersonId;
    private String title;
    private Date date;
	private boolean Etat;
	
	@ManyToOne
	Offer offer;

}
