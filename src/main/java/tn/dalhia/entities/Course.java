package tn.dalhia.entities;
import tn.dalhia.entities.enumerations.CourseStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Place> places;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CourseComment> courseComments;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Phase> phases;
}
