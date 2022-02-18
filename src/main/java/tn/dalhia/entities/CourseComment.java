package tn.dalhia.entities;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.List;

@Entity
public class CourseComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private Data date;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CourseComment> replies;
}
