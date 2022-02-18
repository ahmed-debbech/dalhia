package tn.dalhia.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private float price;
    private int duration;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Subscription> subscriptionList;
}
