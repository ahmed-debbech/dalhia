package tn.dalhia.entities;

import lombok.*;
import tn.dalhia.entities.enumerations.CourseStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course implements Comparable<Course>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private float price;
    private String modality;
    private LocalDateTime dateAdded;
    private LocalDateTime datePublished;
    private LocalDateTime dateRemoved;
    private int nbrPhases;
    private float rate;
    private int nbrOfEnrolls;

    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Place> places;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CourseComment> courseComments;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Phase> phases;

    @Override
    public int compareTo(Course o) {
        return (o.getNbrOfEnrolls() - this.nbrOfEnrolls) ;
    }
}
