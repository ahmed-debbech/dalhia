package tn.dalhia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.dalhia.entities.enumerations.ReportCategory;
import tn.dalhia.entities.enumerations.ReportStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ReportId;

    @Temporal(TemporalType.DATE)
    private Date ReportDate;
    private String ReportBody;

    @Enumerated(EnumType.STRING)
    private ReportCategory category;

    @Enumerated(EnumType.STRING)
    private ReportStatus status;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> suggestions;
    

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}