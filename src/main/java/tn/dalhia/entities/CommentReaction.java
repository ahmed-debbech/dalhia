package tn.dalhia.entities;

import lombok.*;
import tn.dalhia.entities.enumerations.ReactionType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
