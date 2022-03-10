package tn.dalhia.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "event_id")
    private Event event;


}
