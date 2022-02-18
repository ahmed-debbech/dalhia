package tn.dalhia.entities;

import tn.dalhia.entities.enumerations.Tag;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    @Enumerated(EnumType.STRING)
    private Tag tag;
    private int score;
    private Date datePublished;
    private Date dateRemoved;
    private boolean banned;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TopicRate> topicRateList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
    private List<ForumComment> forumComments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
    private List<TopicClaim> topicClaims;


}
