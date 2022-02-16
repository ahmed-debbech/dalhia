package tn.dalhia.entities;
import tn.dalhia.entities.enumerations.CourseStatus;

import javax.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

}
