package tn.dalhia.entities;
import lombok.*;
import tn.dalhia.entities.enumerations.CourseStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull private String name;
    @NonNull private float price;
    @NonNull private String modality;
    @NonNull private Date dateAdded;
    @NonNull private Date datePublished;
    @NonNull private Date dateRemoved;
    @NonNull private float rate;

    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Place> places;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CourseComment> courseComments;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Phase> phases;
}
