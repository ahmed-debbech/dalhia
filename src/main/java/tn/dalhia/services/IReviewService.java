package tn.dalhia.services;

import java.util.List;

import tn.dalhia.entities.Review;

public interface IReviewService {
	public List<Review> getAllReviews();
	public void updateReview(Review rv, int id);
	public void addReview(Review rq, Long ExpertId);
	public void deleteReview(int id);
}
