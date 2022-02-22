package tn.dalhia.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class CourseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> courseList;
}
