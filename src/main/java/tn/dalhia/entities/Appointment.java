package tn.dalhia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.dalhia.entities.enumerations.AppointmentStatus;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer AppId;

    private Integer AppHour;

    @Temporal(TemporalType.DATE)
    private Date AppDate;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus AppStatus;


    @OneToOne(cascade = CascadeType.REMOVE)
    private AppointmentRate appointmentRate;
    
    @OneToOne(cascade = CascadeType.REMOVE)
    private AppointmentReport appointmentReport;
   
   
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonIgnore
    private User sender;

}

