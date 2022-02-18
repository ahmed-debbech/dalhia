package tn.dalhia.entities;

import tn.dalhia.entities.enumerations.Satisfaction;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AppointmentRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float stars;
    @Enumerated(EnumType.STRING)
    private Satisfaction satisfaction;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}
