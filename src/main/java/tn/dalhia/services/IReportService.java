package tn.dalhia.services;

import java.util.List;

import tn.dalhia.entities.Report;
import tn.dalhia.entities.User;

public interface IReportService {
	
	public List<Report> getAllReports();
	public void updateReport(Report rp, int id);
	public void addReport(Report rp);
	public void deleteReport(int id);
	public void manageReportStatus(Report rp, int id);
	//public List<Report> getReportsWithNbrSuggests();
	public List<User> getAssociationsByActivityCount();
}
