package tn.dalhia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.Review;
import tn.dalhia.entities.User;




@Repository
public interface ReviewRepository extends CrudRepository<Review,Integer> {

	
	@Query(value="SELECT AVG(review.stars) FROM review JOIN user GROUP BY review.user_id ORDER BY AVG(review.stars) DESC LIMIT 1", nativeQuery= true)
	public Float getBestExpertScore();
	
	@Query(value="SELECT review.user_id FROM review JOIN user GROUP BY review.user_id ORDER BY AVG(review.stars) DESC LIMIT 1", nativeQuery= true)
	public Long getBestExpertId();
	
	@Query(value="SELECT COUNT(*) FROM review WHERE review.user_id = :expId", nativeQuery= true)
	public int getBestExpertReviewsNbr(@Param("expId")Long expId);
	
	@Query(value="SELECT AVG(review.stars) FROM review JOIN user GROUP BY review.user_id ORDER BY AVG(review.stars) ASC LIMIT 1", nativeQuery= true)
	public Float getWorstExpertScore();
	
	@Query(value="SELECT review.user_id FROM review JOIN user GROUP BY review.user_id ORDER BY AVG(review.stars) ASC LIMIT 1", nativeQuery= true)
	public Long getWorstExpertId();
	

}
