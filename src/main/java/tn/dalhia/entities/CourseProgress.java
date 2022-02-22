package tn.dalhia.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CourseProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date enrollDate;
    private Date unrollDate;
    private int duration;
    private String status;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
