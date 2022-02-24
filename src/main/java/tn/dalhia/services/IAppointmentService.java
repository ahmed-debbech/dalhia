package tn.dalhia.services;

import java.util.List;

import tn.dalhia.entities.Appointment;



public interface IAppointmentService {

	
	public void deleteAppointment(int id);
	public List<Appointment> getAllAppointments();
	public void updateAppointment(Appointment app, int id);
	public void addAppointment(Appointment app, Long ExpertId);
}
