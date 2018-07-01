package com.ashleydix.rover.reviews;

import com.ashleydix.rover.booking.Booking;
import com.ashleydix.rover.user.User;
import com.ashleydix.rover.user.sitter.Sitter;
import com.ashleydix.rover.user.sitter.SitterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReviewControllerTest {

    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private SitterRepository sitterRepository;
    @Mock
    private Model model;

    private ReviewController reviewController;
    private List<Review> reviews;
    private Sitter sitter;

    @Captor
    ArgumentCaptor<List<Review>> reviewListCaptor;
    @Captor
    ArgumentCaptor<Sitter> sitterCaptor;

    @Before
    public void setup(){
        reviewController = new ReviewController();
        reviewController.sitterRepository = sitterRepository;
        reviewController.reviewRepository = reviewRepository;

        sitter = new Sitter();
        sitter.setName("John");
        sitter.setSitterRank(4);

        reviews = new ArrayList<Review>();
        reviews.add(generateReview("1998-04-14", "Review1 - 1998", 3, sitter));
        reviews.add(generateReview("2017-05-25", "Review3 - 2017", 4, sitter));
        reviews.add(generateReview("2012-05-12", "Review2 - 2012", 5, sitter));
        when(sitterRepository.findOne(anyLong())).thenReturn(sitter);
        when(reviewRepository.findAllBySitter(sitter)).thenReturn(reviews);
    }

    @Test
    public void testIndex(){
        String viewName = reviewController.index(model, "123");
        assertEquals(ReviewController.REVIEW_LISTING_VIEW_NAME, viewName);
        verify(model, times(1))
                .addAttribute(eq(ReviewController.SITTER_MODEL_ATTRIBUTE), sitterCaptor.capture());
        Sitter capturedSitter = sitterCaptor.getValue();
        assertEquals("John", capturedSitter.getName());

        verify(model, times(1))
                .addAttribute(eq(ReviewController.REVIEW_LIST_MODEL_ATTRIBUTE), reviewListCaptor.capture());
        List<Review> capturedReviews = reviewListCaptor.getValue();
        assertEquals("Review3 - 2017", capturedReviews.get(0).getText());
        assertEquals("Review2 - 2012", capturedReviews.get(1).getText());
        assertEquals("Review1 - 1998", capturedReviews.get(2).getText());
    }


    private Review generateReview(String date, String reviewText, int rating, Sitter sitter){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime reviewDate = LocalDateTime.from(LocalDate.parse(date, formatter).atStartOfDay());
        Review review = new Review();
        review.setBooking(new Booking());
        review.setReviewDate(reviewDate);
        review.setOwner(new User());
        review.setSitter(sitter);
        review.setText(reviewText);
        review.setRating(rating);
        return review;
    }


}
