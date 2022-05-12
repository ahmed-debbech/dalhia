package tn.dalhia.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime dateSent;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime dateRemoved;
    private boolean banned; // when in group chat

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
