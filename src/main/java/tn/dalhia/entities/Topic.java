package tn.dalhia.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import tn.dalhia.entities.enumerations.Tag;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @Length(min = 10, max = 1000)
    private String text;
    @Enumerated(EnumType.STRING)
    private Tag tag;
    private int score;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private LocalDateTime datePublished;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private LocalDateTime dateRemoved;
    private boolean banned;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private LocalDateTime lastBeTopicOfTheDay; //last time this topic was the topic of the day

    @OneToMany(cascade = CascadeType.ALL)
    private List<TopicRate> topicRateList;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ForumComment> forumComments;
    @OneToMany(cascade = CascadeType.ALL)
    private List<TopicClaim> topicClaims;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
