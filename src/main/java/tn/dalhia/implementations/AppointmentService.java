package tn.dalhia.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.dalhia.entities.Appointment;
import tn.dalhia.entities.User;
import tn.dalhia.repositories.AppointmentReportRepository;
import tn.dalhia.repositories.AppointmentRepository;
import tn.dalhia.repositories.UserRepository;
import tn.dalhia.services.IAppointmentService;



@Slf4j
@Service
public class AppointmentService implements IAppointmentService {
	
	@Autowired
	private AppointmentRepository AppRepo;

	@Autowired
	private AppointmentReportRepository AppRpRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MailService Ms;
	
	@Override
	public void addAppointment(Appointment app, Long ExpertId) {
		User user = userRepo.findById(ExpertId).get();
		
		if(user.isBan()){
			log.info("The following Expert: "+user.getFirst_name()+" "+user.getLast_name()+" ,Profession: "+user.getJob()+" is BANNED by Admin therefore no appointments can be taken.");
		}
		else{
		app.setAppHour(app.getAppHour());
		app.setAppDate(app.getAppDate());
		app.setAppStatus(app.getAppStatus().PENDING);
		app.setUser(user);
		AppRepo.save(app);
		log.info("Appointment with"+user.getJob()+": "+user.getFirst_name()+" "+user.getLast_name()+" is taken successfully.");
		
		}
		
		try {
			Ms.sendEmail(user);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		log.info("Appointment Mail has been sent to Expert successfully.");
		
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
		User user = app.getUser();
		
		a.setAppHour(app.getAppHour());
		a.setAppDate(app.getAppDate());
		a.setAppStatus(app.getAppStatus());
		if(user.isBan())
		{
			log.info("The following Expert: "+user.getJob()+": "+user.getFirst_name()+" "+user.getLast_name()+" is BANNED by Admin therefore no appointments can be taken.");
		}
		else{
		a.setUser(user);
		}
		AppRepo.save(a);
		log.info("Appointment edited.");
		
	}

	@Override
	public void getMostAndLeastVisitedExpert() {
		
		Long Exp_id = AppRepo.getMostVisitedExpertId();
		Long Exp_id2= AppRepo.getLeastVisitedExpertId();
		
		int nbr = AppRepo.getMostVisitedExpertAppNbr();
		int nbr2 = AppRepo.getLeastVisitedExpertAppNbr();
		
		User user = userRepo.findById(Exp_id).get();
		User user2 = userRepo.findById(Exp_id2).get();
		
		
		log.info("The Most Visited Expert is : "+user.getFirst_name()+" "+user.getLast_name()+" ,Profession: "+user.getJob()+" ,Address: "+user.getAddress()+" ,Phone: "+user.getPhone()+" with a total of "+nbr+" appointments.");
		log.info("The Least Visited Expert is : "+user2.getFirst_name()+" "+user2.getLast_name()+" ,Profession: "+user2.getJob()+" ,Address: "+user2.getAddress()+" ,Phone: "+user2.getPhone()+" with a total of "+nbr2+" appointments.");
			
	}

	@Override
	public void banReportedExpert() {
		
		Integer AppId = AppRpRepo.retrieveAppId();
		Long UsrId = AppRpRepo.retrieveUserId(AppId);
		
		
		User user = userRepo.findById(UsrId).get();
		AppRpRepo.updateBan(UsrId);
		log.info("Reported Expert: "+user.getFirst_name()+" "+user.getLast_name()+" ,Profession: "+user.getJob()+" ,Address: "+user.getAddress()+" ,Phone: "+user.getPhone()+" is BANNED by Admin.");
		AppRepo.deleteById(AppId);
		
	}
	
	

}

