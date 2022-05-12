package tn.dalhia.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import tn.dalhia.entities.enumerations.Access;
import tn.dalhia.entities.enumerations.ChannelType;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private ChannelType channelType;
    private Access access; // private by default for INDIV chats
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime dateCreated;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Message> messageList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
