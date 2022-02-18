package tn.dalhia.entities;

import tn.dalhia.entities.enumerations.Access;
import tn.dalhia.entities.enumerations.ChannelType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private ChannelType channelType;
    private Access access; // private by default for INDIV chats
    private Date dateCreated;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Message> messageList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
