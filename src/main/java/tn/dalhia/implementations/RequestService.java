package tn.dalhia.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.dalhia.entities.Report;
import tn.dalhia.entities.Request;
import tn.dalhia.entities.User;
import tn.dalhia.repositories.RequestRepository;
import tn.dalhia.repositories.UserRepository;
import tn.dalhia.services.IReportService;
import tn.dalhia.services.IRequestService;

@Service
@Slf4j
public class RequestService implements IRequestService {
	
	@Autowired
	private RequestRepository rqr;
	
	@Autowired
	private UserRepository userRepo;
	
	public List<Request> getAllRequests() {
		List<Request> rqs = (List<Request>) rqr.findAll();
		return rqs;
	}

	@Override
	public void updateRequest(Request rq, int id) {
		Request Rq = rqr.findById(id).get();
		Rq.setRequestBody(rq.getRequestBody());
		Rq.setRequestDate(rq.getRequestDate());
		Rq.setRequestHeader(rq.getRequestHeader());
		rqr.save(Rq);
		log.info("Request edited.");
		
	}

	@Override
	public void addRequest(Request rq, Long AssocId) {
		User user = userRepo.findById(AssocId).get();
		rq.setRequestBody(rq.getRequestBody());
		rq.setRequestDate(rq.getRequestDate());
		rq.setRequestHeader(rq.getRequestHeader());

		rq.setUser(user);
		rqr.save(rq);
		log.info("Request sent to: "+user.getRole()+": "+user.getFirst_name()+" successfully.");
		
	}

	@Override
	public void deleteRequest(int id) {
		rqr.deleteById(id);
		log.info("Request removed.");
		
	}

}
