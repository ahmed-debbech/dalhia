package tn.dalhia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tn.dalhia.entities.enumerations.TopicClaimType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TopicClaim {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private LocalDateTime date_created;
    private boolean approved;
    @Enumerated(EnumType.STRING)
    private TopicClaimType topicClaimType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
