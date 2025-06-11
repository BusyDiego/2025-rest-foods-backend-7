package ch.noseryoung.backend.domain.review;

import ch.noseryoung.backend.domain.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }
    
    public Review getReview(UUID id) {
        return reviewRepository.findById(id).orElse(null);
    }
    
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }
    
    public Review updateReview(UUID id, Review review) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()) {
            reviewOptional.get().setContent(review.getContent());
            reviewOptional.get().setStars(review.getStars());
            reviewOptional.get().setBook(review.getBook());
            reviewRepository.save(reviewOptional.get());
            return reviewOptional.get();
        }
        throw new NotFoundException("Review with id " + id + " not found");
    }
    
    public void deleteReview(UUID id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
        } else {
            throw new NotFoundException("Review with id " + id + " not found");
        }
    }
}