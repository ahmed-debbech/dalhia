package tn.dalhia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.dalhia.entities.enumerations.CourseProgressStatus;
import tn.dalhia.entities.enumerations.CourseStatus;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
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
    private LocalDateTime enrollDate;
    private LocalDateTime unrollDate;
    private int duration;
    private int noteQuiz;
    private int attempts;
    private LocalDateTime attDate;

    @Enumerated(EnumType.STRING)
    private CourseProgressStatus courseProgressStatus;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
