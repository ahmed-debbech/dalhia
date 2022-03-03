package tn.dalhia.entities;



import lombok.*;
import tn.dalhia.entities.enumerations.VoteType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TopicRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime date;
    private VoteType voteType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
