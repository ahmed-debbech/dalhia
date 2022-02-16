package tn.dalhia.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
}
