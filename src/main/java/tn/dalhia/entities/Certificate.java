package tn.dalhia.entities;

import lombok.*;
import tn.dalhia.entities.enumerations.CertificateType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private LocalDateTime dateAffection;
    private LocalDateTime dateAdded;
    @Enumerated(EnumType.STRING)
    private CertificateType certificateType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
