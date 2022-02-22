package tn.dalhia.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class CourseComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private Date date;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CourseComment> replies;
}
