package tn.dalhia.services;

import java.util.List;

import tn.dalhia.entities.Appointment;
import tn.dalhia.entities.AppointmentRate;
import tn.dalhia.entities.AppointmentReport;
import tn.dalhia.entities.User;



public interface IAppointmentService {

	
	public void deleteAppointment(int id);
	public List<Appointment> getAllAppointments();
	public void updateAppointment(Appointment app, int id);
	public void addAppointment(Appointment app, Long ExpertId);
	public void getMostAndLeastVisitedExpert();
	public User getMostExpert();
	public User getLeastExpert();
	public User banReportedExpert();
	public List<User> getExperts();
	public List<Appointment> getMyAppointments(Long senderId);
	public User getExpertDetails(Long expertId);
	public List<AppointmentReport> getAppointmentReports(Long id);
	public List<AppointmentRate> getAppointmentRates(Long id);
}
