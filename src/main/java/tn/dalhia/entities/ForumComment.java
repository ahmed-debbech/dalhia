package tn.dalhia.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
public class ForumComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime datePublished;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime dateRemoved;
    private boolean banned;

    @ManyToOne
    @JoinColumn(name = "user_id")

    //@JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CommentReaction> commentReactionList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ForumComment> replies;
}
