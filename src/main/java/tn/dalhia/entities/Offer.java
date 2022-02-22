package tn.dalhia.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String address;
    private Date startDate;
    private Date endDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Application> applicationList;
}
