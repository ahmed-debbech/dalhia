package tn.dalhia.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.dalhia.entities.Report;
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

}
