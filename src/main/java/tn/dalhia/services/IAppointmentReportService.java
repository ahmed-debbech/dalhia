package tn.dalhia.services;

import java.util.List;

import tn.dalhia.entities.Appointment;
import tn.dalhia.entities.AppointmentReport;

public interface IAppointmentReportService {

	public void deleteAppointmentReport(Long id);
	public List<AppointmentReport> getAllAppointmentsReports();
	public void updateAppointmentReport(AppointmentReport rp, Long id);
	public void addAppointmentReport(AppointmentReport rp, Integer AppId);
}
