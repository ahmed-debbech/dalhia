package tn.dalhia.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.dalhia.entities.Report;
import tn.dalhia.entities.User;
import tn.dalhia.repositories.ReportRepository;
import tn.dalhia.services.IReportService;

@Service
@Slf4j
public class ReportService implements IReportService{

	@Autowired
	private ReportRepository rr;
	
	public List<Report> getAllReports() {

		List<Report> apps = (List<Report>) rr.findAll();
		return apps;
	}

	public void updateReport(Report Report, int id) {
     
		Report Rp = rr.findById(id).get();
		Rp.setCategory(Report.getCategory());
		Rp.setReportBody(Report.getReportBody());
		Rp.setReportDate(Report.getReportDate());
	
		rr.save(Rp);
		log.info("Report edited.");
		
	}

	public void addReport(Report rp) {
		rr.save(rp);
		log.info("Report inserted successfully.");
		
	}

	public void deleteReport(int id) {
		rr.deleteById(id);
		log.info("Report removed.");
		
	}

	@Override
	public void manageReportStatus(Report report, int id) {
		//int nbr = 0;
		Report Rp = rr.findById(id).get();
		Rp.setStatus(report.getStatus());
		if( report.getStatus().getValue()==1){
			List<User> suggests = rr.getSuggestions(Rp.getCategory());
			Rp.getSuggestions().addAll(suggests);
			//nbr = rr.countSuggestions(id);
			log.info("Report is CONFIRMED by Admin and list of Report Category Matched suggestions assigned.");
		}
		else{
			log.info("Report is DECLINED by Admin.");	
		}
		rr.save(Rp);
		log.info("end of process.");	
	}

}
