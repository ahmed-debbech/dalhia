package tn.dalhia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.dalhia.entities.enumerations.RequestStatus;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request implements Serializable {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer RequestId;
    private String RequestBody;
    private String RequestHeader;

    @Temporal(TemporalType.DATE)
    private Date RequestDate;

    @Enumerated(EnumType.STRING)
    private RequestStatus Status;
    
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonIgnore
    private User sender;
}