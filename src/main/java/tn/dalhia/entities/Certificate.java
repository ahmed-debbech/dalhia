package tn.dalhia.entities;

import tn.dalhia.entities.enumerations.CertificateType;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Date dateAffection;
    private Date dateAdded;
    @Enumerated(EnumType.STRING)
    private CertificateType certificateType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
