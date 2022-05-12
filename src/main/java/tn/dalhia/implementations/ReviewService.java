package tn.dalhia.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.dalhia.entities.Appointment;
import tn.dalhia.entities.OffensiveWord;
import tn.dalhia.entities.Request;
import tn.dalhia.entities.Review;
import tn.dalhia.entities.User;
import tn.dalhia.repositories.OffensiveWordRepository;
import tn.dalhia.repositories.ReviewRepository;
import tn.dalhia.repositories.UserRepository;
import tn.dalhia.services.IReviewService;
import tn.dalhia.shared.tools.UtilsUser;

@Service
@Slf4j
public class ReviewService implements IReviewService {
	
	@Autowired
	private ReviewRepository rvr;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private OffensiveWordRepository offensivewordRp;

	@Autowired
	private UtilsUser utilsUser;

	public List<Review> getAllReviews() {
		List<Review> rvs = (List<Review>) rvr.findAll();
		return rvs;
	}
	
	public List<Review> getAllReviewsByExpert(Long expertId) {
		List<Review> rvs = (List<Review>) rvr.getReviewsByExpert(expertId);
		return rvs;
	}


	@Override
	public void updateReview(Review rv, int id) {
		Review Rv = rvr.findById(id).get();

		String Body = rv.getReviewBody();
		String Header = rv.getReviewHeader();
		List<OffensiveWord> offensivewords = (List<OffensiveWord>) offensivewordRp.findAll();
		
		boolean checkBody = checkBadWords(offensivewords,Body);
		boolean checkHeader = checkBadWords(offensivewords,Header);
		
		if(checkBody == true || checkHeader == true)
		{
			log.info("Offensive language detected, publication failed.");
		}
		else if (checkBody == false && checkHeader == false ) 
		{
		Rv.setReviewHeader(rv.getReviewHeader());
		Rv.setReviewBody(rv.getReviewBody());
		Rv.setReviewDate(rv.getReviewDate());
		Rv.setStars(rv.getStars());
		rvr.save(Rv);
		log.info("Review edited.");
		}
	}

	@Override
	public void addReview(Review rv, Long ExpertId) {

		User logg = utilsUser.getLoggedInUser();
		rv.setSender(logg);

		User user = userRepo.findById(ExpertId).get();

		String Body = rv.getReviewBody();
		String Header = rv.getReviewHeader();
		List<OffensiveWord> offensivewords = (List<OffensiveWord>) offensivewordRp.findAll();
		
		boolean checkBody = checkBadWords(offensivewords,Body);
		boolean checkHeader = checkBadWords(offensivewords,Header);
		
		if(checkBody == true || checkHeader == true)
		{
			log.info("Offensive language detected, publication failed.");
		}
		else if (checkBody == false && checkHeader == false ) 
		{
		rv.setReviewHeader(rv.getReviewHeader());
		rv.setReviewBody(rv.getReviewBody());
		rv.setReviewDate(rv.getReviewDate());
		rv.setStars(rv.getStars());
		rv.setUser(user);

		rvr.save(rv);
		log.info("Review submitted and assigned to: "+user.getJob()+": "+user.getFirst_name()+" "+user.getLast_name()+" successfully.");
		   }
		}

	@Override
	public void deleteReview(int id) {
		rvr.deleteById(id);
		log.info("Review removed.");
		
	}


	public boolean checkBadWords(List<OffensiveWord> words, String message) 
	{
		
		int iterator=0;
		for(int i=0 ; i<words.size(); i++)
		{
			if (message.contains(words.get(i).getWord()))
			{
				iterator++; 
			}
		}
		if (iterator>0)
		{
			
			log.info("Your review contains offensive language, "+iterator+" inappropriate words found.");
			return true;
		}
			log.info("Your review is clean, valid and ready for publication.");
			return false;	
	}

	@Override
	public void getBestAndWorstExpertWithScore() {
		
		Float score= rvr.getBestExpertScore();
		Float score2= rvr.getWorstExpertScore();
		
		Long Exp_id = rvr.getBestExpertId();
		Long Exp_id2= rvr.getWorstExpertId();
		
		int nbr = rvr.getBestExpertReviewsNbr(Exp_id);
		int nbr2 = rvr.getBestExpertReviewsNbr(Exp_id2);
		
		User user = userRepo.findById(Exp_id).get();
		User user2 = userRepo.findById(Exp_id2).get();
		
		log.info("The Most Recommanded Expert is : "+user.getFirst_name()+" "+user.getLast_name()+" ,Profession: "+user.getJob()+" ,Address: "+user.getAddress()+" ,Phone: "+user.getPhone()+" with a total score of: "+score+"/5 based on "+nbr+" reviews.");
		log.info("The Least Recommanded Expert is : "+user2.getFirst_name()+" "+user2.getLast_name()+" ,Profession: "+user2.getJob()+" ,Address: "+user2.getAddress()+" ,Phone: "+user2.getPhone()+" with a total score of: "+score2+"/5 based on "+nbr2+" reviews.");
       }
	@Override
	public User getMostScoredExpert(){
		Long Exp_id = rvr.getBestExpertId();
	return userRepo.findById(Exp_id).get();
			}
  @Override
  public User getWorstScoredExpert(){
	  Long Exp_id2= rvr.getWorstExpertId();
	  return userRepo.findById(Exp_id2).get();
  }
  @Override
  public Float getBestScore(){
	  
	  return rvr.getBestExpertScore();
  }
  @Override
  public Float getWorstScore(){
	  return rvr.getWorstExpertScore();
  }
  
  @Override
  public Float getExpertScore(Long expId){
	  return rvr.getExpertScore(expId);
  }
  
  @Override
	public List<Review> getMyReviews(Long senderId) {
		
		
		return rvr.getMyReviews(senderId);
	}
}
