package tn.dalhia.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date start_date;
    private Date end_date;
    private int views;
    private int target_views;
}
