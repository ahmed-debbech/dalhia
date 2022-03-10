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
import tn.dalhia.shared.tools.UtilsUser;


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
	
	@Autowired
	private SmsService Ss;

	@Autowired
	private UtilsUser utilsUser;
	
	@Override
	public void addAppointment(Appointment app, Long ExpertId) {
		//Date today = new Date();

		User logg = utilsUser.getLoggedInUser();
		app.setSender(logg);
		//log.info("today:"+today);
		User user = userRepo.findById(ExpertId).get();
		if(user == null || user.getRole().getValue()==2|| user.getRole().getValue()==3 || user.getRole().getValue()==4)
		{
            log.info("Expert not found, please verify chosen expert id.");
		}
		/*else if(app.getAppDate().before(today))
		{
			 log.info("chosen Appointment date is before current date, make sure to pick a valid date.");
		}*/
		else if(app.getAppHour().compareTo(user.getStart_hour())<0 || app.getAppHour().compareTo(user.getEnd_hour())>0)
		{
			log.info("chosen Appointment hour doesn't match expert's business hours.");
		}
		else if(user.isBan())
		{

			log.info("The following Expert: "+user.getFirst_name()+" "+user.getLast_name()+" ,Profession: "+user.getJob()+" is BANNED by Admin therefore no appointments can be taken.");
		}
		else{
		app.setAppHour(app.getAppHour());
		app.setAppDate(app.getAppDate());
		app.setAppStatus(app.getAppStatus().PENDING);
		app.setUser(user);
		AppRepo.save(app);
		log.info("Appointment with"+user.getJob()+": "+user.getFirst_name()+" "+user.getLast_name()+" is taken successfully.");
		
		try {
			Ms.sendAppEmail(user);
			
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		Ss.sendSms(user);
		log.info("Appointment Mail and SMS are sent to Expert successfully.");
		
		}
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
		
		Long UsrId = AppRpRepo.retrieveUserId();
		User user = userRepo.findById(UsrId).get();
		int count = AppRpRepo.getAppReportCountbyUserId(UsrId);
		
		if(count==1)
		{
			try {
			
				Ms.sendWarningEmail(user);
			} catch (MailException mailException) {
				System.out.println(mailException);
			}
			Ss.sendWarningSms(user);
			log.info("BAN WARNING Mail and SMS are sent to Expert successfully.");
			
			}
		else if(count>1)
		{
			AppRpRepo.updateBan(UsrId);
			log.info("Reported Expert: "+user.getFirst_name()+" "+user.getLast_name()+" ,Profession: "+user.getJob()+" ,Address: "+user.getAddress()+" ,Phone: "+user.getPhone()+" is BANNED by Admin.");
			
			AppRepo.DeleteAppByUsrId(UsrId);
			
			try {
				
				Ms.sendBanEmail(user);
			} catch (MailException mailException) {
				System.out.println(mailException);
			}
			Ss.sendBanSms(user);
			log.info("BAN Mail and SMS are sent to Expert successfully.");
			
		}	
		else
		{
			log.info("No Appointment Reports found therefore no experts to ban.");
		}
	}

	

}

