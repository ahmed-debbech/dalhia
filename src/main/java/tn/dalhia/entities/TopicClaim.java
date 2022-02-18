package tn.dalhia.entities;

import tn.dalhia.entities.enumerations.TopicClaimType;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TopicClaim {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private Date date_created;
    private boolean approved;
    @Enumerated(EnumType.STRING)
    private TopicClaimType topicClaimType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
