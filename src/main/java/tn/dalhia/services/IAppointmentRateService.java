package tn.dalhia.services;

import java.util.List;

import tn.dalhia.entities.AppointmentRate;

public interface IAppointmentRateService {

	public void deleteAppointmentRate(Long id);
	public List<AppointmentRate> getAllAppointmentsRates();
	public void updateAppointmentRate(AppointmentRate ar, Long id);
	public void addAppointmentRate(AppointmentRate ar, Integer AppId);
}
