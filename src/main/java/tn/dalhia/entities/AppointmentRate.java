package tn.dalhia.entities;

import tn.dalhia.entities.enumerations.AppointmentStatus;
import tn.dalhia.entities.enumerations.Satisfaction;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float stars;
    @Enumerated(EnumType.STRING)
    private Satisfaction satisfaction;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}
