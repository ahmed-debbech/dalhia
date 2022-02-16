package tn.dalhia.entities;
import tn.dalhia.entities.enumerations.CourseStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private float price;
    private String modality;
    private Date dateAdded;
    private Date datePublished;
    private Date dateRemoved;
    private float rate;
    private CourseStatus courseStatus;

}
