package tn.dalhia.services;

import java.util.List;

import tn.dalhia.entities.Request;
import tn.dalhia.entities.User;
import tn.dalhia.entities.enumerations.ReportCategory;

public interface IRequestService {
	public List<Request> getAllRequests();
	public void updateRequest(Request rq, int id);
	public void addRequest(Request rq, Long AssocId);
	public void deleteRequest(int id);
	public int getMostRequestedAssocPerAct(ReportCategory Act);
}
