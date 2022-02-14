package tn.dalhia.entities;

import jdk.jfr.Category;
import tn.dalhia.entities.enumerations.EventCategory;
import tn.dalhia.entities.enumerations.EventStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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
}
