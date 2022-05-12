package tn.dalhia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.dalhia.entities.Appointment;
import tn.dalhia.entities.Request;
import tn.dalhia.entities.Review;
import tn.dalhia.entities.User;
import tn.dalhia.implementations.RequestService;
import tn.dalhia.implementations.ReviewService;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins="*")
public class ReviewController {

	@Autowired
	ReviewService rvs;
	
	//http://localhost:8089/Dahlia/review/add-review/8
	 @PostMapping("/add-review/{expert-id}")
		public void addReview(@RequestBody Review rv,@PathVariable("expert-id") Long ExpertId) {
		 rvs.addReview(rv, ExpertId);
		}
	 
	//http://localhost:8089/Dahlia/review/retrieve-all-reviews
		 @GetMapping("/retrieve-all-reviews")
		 public List <Review> getReviews(){
			 
			 List <Review> listRvs = rvs.getAllReviews();
			 return listRvs;
		 }
		 
		 @CrossOrigin(origins="*")
		 @GetMapping("/retrieve-expert-reviews/{expert-id}")
		 public List <Review> getReviewsByExpert(@PathVariable("expert-id") Long expertId){
			 
			 List <Review> listRvs = rvs.getAllReviewsByExpert(expertId);
			 return listRvs;
		 }
		 
		 
		//http://localhost:8089/Dahlia/appointment/retrieve-all-appointments
	     @CrossOrigin(origins="*")
		 @GetMapping("/retrieve-my-reviews/{sender-id}")
		 public List <Review> getMyReviews(@PathVariable ("sender-id")Long senderId){
			 
			 List <Review> listApps = rvs.getMyReviews(senderId);
			 return listApps;
		 }

		 
	//http://localhost:8089/Dahlia/review/retrieve-all-reviews
		 @GetMapping("/retrieve-best&worst-expert-with-score")
		 public void retrieveBestAndWorstExpertWithScore(){
			 rvs.getBestAndWorstExpertWithScore();
		 }
		 
	// http://localhost:8089/Dahlia/review/remove-review/{rv-id}
		@DeleteMapping("/remove-review/{rv-id}")
		public void removeReview(@PathVariable("rv-id") Integer RvId) {
			rvs.deleteReview(RvId);
			}

	// http://localhost:8089/Dahlia/review/edit-review/{rv-id}
		@PutMapping("/edit-review/{rv-id}")
		public void editReview(@RequestBody Review rv,@PathVariable("rv-id") Integer RqId) {
			 rvs.updateReview(rv, RqId);
			}
		
		 @GetMapping("/retrieve-best-scored")
		 public User retrieveBestScored(){
			 return rvs.getMostScoredExpert();
		 }
		 
		 @GetMapping("/retrieve-worst-scored")
		 public User retrieveWorstScored(){
			 return rvs.getWorstScoredExpert();
		 }
		 
		 @GetMapping("/retrieve-best-score")
		 public Float retrieveBestScore(){
			return rvs.getBestScore();
		 }
		 
		 @GetMapping("/retrieve-worst-score")
		 public Float retrieveWorstScore(){
			 return rvs.getWorstScore();
		 }
		 
		 @GetMapping("/retrieve-expert-score/{expert-id}")
		 public Float retrieveExpertScore(@PathVariable("expert-id")Long expId){
			 return rvs.getExpertScore(expId);
		 }
	   
}
