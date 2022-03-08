package tn.dalhia.services;

import java.util.List;

import tn.dalhia.entities.Request;

public interface IRequestService {
	public List<Request> getAllRequests();
	public void updateRequest(Request rq, int id);
	public void addRequest(Request rq, Long AssocId);
	public void deleteRequest(int id);
}
