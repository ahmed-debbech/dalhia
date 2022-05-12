package tn.dalhia.entities;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;

    private LocalDateTime dateSent;
    private LocalDateTime dateRemoved;
    private boolean banned; // when in group chat

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
