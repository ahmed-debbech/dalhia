package tn.dalhia.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float amount;
    private Date donDate;
}
