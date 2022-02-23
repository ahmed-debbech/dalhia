package tn.dalhia.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.dalhia.entities.Appointment;
import tn.dalhia.entities.AppointmentRate;
import tn.dalhia.entities.AppointmentReport;
import tn.dalhia.repositories.AppointmentRateRepository;
import tn.dalhia.repositories.AppointmentRepository;
import tn.dalhia.repositories.UserRepository;
import tn.dalhia.services.IAppointmentRateService;

@Service
@Slf4j
public class AppointmentRateService implements IAppointmentRateService {
	
	
	@Autowired
	private AppointmentRateRepository AppRateRepo;
	
	@Autowired
	private AppointmentRepository AppRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void deleteAppointmentRate(Long id) {
		AppRateRepo.deleteById(id);
		log.info("Appointment Rate removed.");
		
	}
	@Override
	public List<AppointmentRate> getAllAppointmentsRates() {
		List<AppointmentRate> rates = (List<AppointmentRate>) AppRateRepo.findAll();
		return rates;
	}
	@Override
	public void updateAppointmentRate(AppointmentRate Ar, Long id) {
		AppointmentRate rate = AppRateRepo.findById(id).get();
		rate.setStars(rate.getStars());
		rate.setSatisfaction(rate.getSatisfaction());
		AppRateRepo.save(Ar);
		log.info("Appointment Rate edited.");
	}
	@Override
	public void addAppointmentRate(AppointmentRate Ar, Integer AppId) {
		Appointment app = AppRepo.findById(AppId).get();
		Ar.setStars(Ar.getStars());
		Ar.setSatisfaction(Ar.getSatisfaction());
		Ar.setAppointment(app);
		AppRateRepo.save(Ar);
		log.info("Appointment: "+app.getAppDate()+"at: "+app.getAppHour()+"H with: "+app.getUser().getJob()+" "+app.getUser().getFirst_name()+" "+app.getUser().getLast_name()+" rated successfully.");
		
	}

	
}
