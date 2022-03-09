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
		rate.setStars(Ar.getStars());
		rate.setSatisfaction(Ar.getSatisfaction());
		AppRateRepo.save(rate);
		log.info("Appointment Rate edited.");
	}
	
	
	@Override
	public void addAppointmentRate(AppointmentRate Ar, Integer AppId) {
		Appointment app = AppRepo.findById(AppId).get();
		if(Ar.getStars()>5){
			
		log.info("Appointment Rate MAX value limit is 5, no values beyond limit can be assigned.");	
		}
		else if(Ar.getStars()>=4){
			Ar.setSatisfaction(Ar.getSatisfaction().EXCELLENT);
			AppRateRepo.save(Ar);
			app.setAppointmentRate(Ar);
			AppRepo.save(app);
			
			log.info("Appointment: "+app.getAppDate()+"at: "+app.getAppHour()+"H with: "+app.getUser().getJob()+" "+app.getUser().getFirst_name()+" "+app.getUser().getLast_name()+" rated successfully.");
			
			
		}else if(Ar.getStars()<=1.5){
			Ar.setSatisfaction(Ar.getSatisfaction().UNSATISFIED);
			AppRateRepo.save(Ar);
			app.setAppointmentRate(Ar);
			AppRepo.save(app);
			
			log.info("Appointment: "+app.getAppDate()+"at: "+app.getAppHour()+"H with: "+app.getUser().getJob()+" "+app.getUser().getFirst_name()+" "+app.getUser().getLast_name()+" rated successfully.");
			
		}
		else if(Ar.getStars()>=3 && Ar.getStars()<4)
		{
			Ar.setSatisfaction(Ar.getSatisfaction().GOOD);
			AppRateRepo.save(Ar);
			app.setAppointmentRate(Ar);
			AppRepo.save(app);
			
			log.info("Appointment: "+app.getAppDate()+"at: "+app.getAppHour()+"H with: "+app.getUser().getJob()+" "+app.getUser().getFirst_name()+" "+app.getUser().getLast_name()+" rated successfully.");
			
		}
		else if(Ar.getStars()>=2 && Ar.getStars()<3)
		{
			Ar.setSatisfaction(Ar.getSatisfaction().MODERATE);
			AppRateRepo.save(Ar);
			app.setAppointmentRate(Ar);
			AppRepo.save(app);
			
			log.info("Appointment: "+app.getAppDate()+"at: "+app.getAppHour()+"H with: "+app.getUser().getJob()+" "+app.getUser().getFirst_name()+" "+app.getUser().getLast_name()+" rated successfully.");
			
		}
	}

	
}
