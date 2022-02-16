package tn.dalhia.entities;

import tn.dalhia.entities.enumerations.ReactionType;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CommentReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;
}
