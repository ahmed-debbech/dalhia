package tn.dalhia.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
