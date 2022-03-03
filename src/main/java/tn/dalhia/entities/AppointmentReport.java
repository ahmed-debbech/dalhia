package tn.dalhia.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.dalhia.entities.enumerations.AppReportCategory;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AppointmentReport {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Enumerated(EnumType.STRING)
    private AppReportCategory category;
	
    private String report;

    
    @JsonIgnore

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}
