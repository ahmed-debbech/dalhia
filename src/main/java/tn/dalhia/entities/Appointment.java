package tn.dalhia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.dalhia.entities.enumerations.AppointmentStatus;

import javax.persistence.*;
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

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private AppointmentRate appointmentRate;
    
    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private AppointmentReport appointmentReport;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}