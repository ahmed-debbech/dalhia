package tn.dalhia.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ForumAdTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int ageMax;
    private int ageMin;
    private int donationCount; // the donation made count
    private int applicationCount; // the ad shows to who has the application to offers
    private int certCount; // the ad shows to who has a certificate equal to that number or more
    private boolean any; // any criteria
}
