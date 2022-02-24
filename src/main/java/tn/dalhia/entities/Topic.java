package tn.dalhia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tn.dalhia.entities.enumerations.Tag;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String text;
    @Enumerated(EnumType.STRING)
    private Tag tag;
    private int score;
    private LocalDateTime datePublished;
    private LocalDateTime dateRemoved;
    private boolean banned;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TopicRate> topicRateList;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ForumComment> forumComments;
    @OneToMany(cascade = CascadeType.ALL)
    private List<TopicClaim> topicClaims;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
