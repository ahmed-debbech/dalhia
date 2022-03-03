package tn.dalhia.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.dalhia.entities.Appointment;
import tn.dalhia.entities.User;
import tn.dalhia.repositories.AppointmentRepository;
import tn.dalhia.repositories.UserRepository;
import tn.dalhia.services.IAppointmentService;



@Slf4j
@Service
public class AppointmentService implements IAppointmentService {
	
	@Autowired
	private AppointmentRepository AppRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void addAppointment(Appointment app, Long ExpertId) {
		User user = userRepo.findById(ExpertId).get();
		app.setAppHour(app.getAppHour());
		app.setAppDate(app.getAppDate());
		//log.info(user.toString());
		app.setUser(user);
		AppRepo.save(app);
		log.info("Appointment with"+user.getJob()+": "+user.getFirst_name()+" "+user.getLast_name()+" is taken successfully.");
		
	}

	@Override
	public void deleteAppointment(int id) {
		AppRepo.deleteById(id);
		log.info("Appointment removed.");
		
	}

	@Override
	public List<Appointment> getAllAppointments() {
		List<Appointment> apps = (List<Appointment>) AppRepo.findAll();
		return apps;
	}

	@Override
	public void updateAppointment(Appointment app, int id) {
		Appointment a = AppRepo.findById(id).get();
		
		a.setAppHour(app.getAppHour());
		a.setAppDate(app.getAppDate());
		a.setAppStatus(app.getAppStatus());
		a.setUser(app.getUser());
		AppRepo.save(a);
		log.info("Appointment edited.");
		
	}
	

}

