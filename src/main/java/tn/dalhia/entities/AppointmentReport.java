package tn.dalhia.entities;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import tn.dalhia.entities.enumerations.AppReportCategory;


public class AppointmentReport {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Enumerated(EnumType.STRING)
    private AppReportCategory category;
	
    private String report;
    
    

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}
