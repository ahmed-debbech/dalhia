package tn.dalhia.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.dalhia.entities.Appointment;
import tn.dalhia.entities.AppointmentReport;
import tn.dalhia.entities.User;
import tn.dalhia.repositories.AppointmentReportRepository;
import tn.dalhia.repositories.AppointmentRepository;
import tn.dalhia.repositories.UserRepository;
import tn.dalhia.services.IAppointmentReportService;

@Service
@Slf4j
public class AppointmentReportService implements IAppointmentReportService {

	@Autowired
	private AppointmentReportRepository AppReportRepo;
	
	@Autowired
	private AppointmentRepository AppRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void deleteAppointmentReport(Long id) {
		AppReportRepo.deleteById(id);
		log.info("Appointment Report removed.");
		
	}
	@Override
	public List<AppointmentReport> getAllAppointmentsReports() {
		List<AppointmentReport> reports = (List<AppointmentReport>) AppReportRepo.findAll();
		return reports;
	}
	@Override
	public void updateAppointmentReport(AppointmentReport rp, Long id) {
		AppointmentReport report = AppReportRepo.findById(id).get();
		report.setReport(rp.getReport());
		report.setCategory(rp.getCategory());
		AppReportRepo.save(rp);
		log.info("Appointment Report edited.");
	}
	@Override
	public void addAppointmentReport(AppointmentReport rp, Integer AppId) {
		Appointment app = AppRepo.findById(AppId).get();
		rp.setCategory(rp.getCategory());
		rp.setReport(rp.getReport());
		rp.setAppointment(app);
		AppReportRepo.save(rp);
		log.info("Appointment Report of Appointment: "+app.getAppDate()+"at: "+app.getAppHour()+"H with: "+app.getUser().getJob()+" "+app.getUser().getFirst_name()+" "+app.getUser().getLast_name()+"added successfully.");
		
	}

}
