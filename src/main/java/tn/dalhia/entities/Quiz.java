package tn.dalhia.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Date dateAdded;
    private Date dateRemoved;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answers;
}
