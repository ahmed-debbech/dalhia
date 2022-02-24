package tn.dalhia.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.dalhia.entities.Request;
import tn.dalhia.entities.Review;
import tn.dalhia.entities.User;
import tn.dalhia.repositories.ReviewRepository;
import tn.dalhia.repositories.UserRepository;
import tn.dalhia.services.IReviewService;

@Service
@Slf4j
public class ReviewService implements IReviewService {
	
	@Autowired
	private ReviewRepository rvr;
	
	@Autowired
	private UserRepository userRepo;
	
	public List<Review> getAllReviews() {
		List<Review> rvs = (List<Review>) rvr.findAll();
		return rvs;
	}

	@Override
	public void updateReview(Review rv, int id) {
		Review Rv = rvr.findById(id).get();
		Rv.setReviewHeader(rv.getReviewHeader());
		Rv.setReviewBody(rv.getReviewBody());
		Rv.setReviewDate(rv.getReviewDate());
		Rv.setStars(rv.getStars());
		rvr.save(Rv);
		log.info("Review edited.");
	}

	@Override
	public void addReview(Review rv, Long ExpertId) {
		User user = userRepo.findById(ExpertId).get();
		rv.setReviewHeader(rv.getReviewHeader());
		rv.setReviewBody(rv.getReviewBody());
		rv.setReviewDate(rv.getReviewDate());
		rv.setStars(rv.getStars());
		rv.setUser(user);
		rvr.save(rv);
		log.info("Review submitted and assigned to: "+user.getJob()+": "+user.getFirst_name()+" "+user.getLast_name()+" successfully.");
	}

	@Override
	public void deleteReview(int id) {
		rvr.deleteById(id);
		log.info("Review removed.");
		
	}

}
