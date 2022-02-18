package tn.dalhia.entities;

import javax.persistence.*;
import javax.xml.crypto.Data;

@Entity
public class CourseProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Data enrollDate;
    private Data unrollDate;
    private int duration;
    private String status;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
