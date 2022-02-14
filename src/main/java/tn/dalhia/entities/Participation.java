package tn.dalhia.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
}
