package tn.dalhia.entities;

//import jdk.jfr.Category;
import tn.dalhia.entities.enumerations.EventCategory;
import tn.dalhia.entities.enumerations.EventStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Date start_date;
    private String description;
    private EventStatus eventStatus;
    private EventCategory category;
    private int score;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Participation> participationList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Donation> donations;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Advertisement> advertisements;
}
