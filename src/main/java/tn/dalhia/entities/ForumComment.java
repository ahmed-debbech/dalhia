package tn.dalhia.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class ForumComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private Date datePublished;
    private Date dateRemoved;
    private boolean banned;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "forumComment")
    private List<CommentReaction> commentReactionList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ForumComment> replies;
}
