package com.ashleydix.rover.reviews;

import com.ashleydix.rover.user.sitter.Sitter;
import com.ashleydix.rover.user.sitter.SitterRepository;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ReviewController {
    public static final String REVIEW_LIST_MODEL_ATTRIBUTE = "reviews";
    public static final String SITTER_MODEL_ATTRIBUTE = "sitter";
    public static final String REVIEW_LISTING_VIEW_NAME = "jsp/review";

    @Autowired
    @VisibleForTesting
    protected ReviewRepository reviewRepository;

    @Autowired
    @VisibleForTesting
    protected SitterRepository sitterRepository;

    @ModelAttribute("module")
    String module() {
        return "review";
    }

    @GetMapping("/review/{id}")
    String index(Model model, @PathVariable("id")String id) {
        Sitter sitter = sitterRepository.findOne(Long.parseLong(id));
        model.addAttribute(SITTER_MODEL_ATTRIBUTE, sitter);
        List<Review> reviews = reviewRepository.findAllBySitter(sitter);
        reviews.sort((r1, r2) -> r2.getReviewDate().compareTo(r1.getReviewDate()));
        model.addAttribute(REVIEW_LIST_MODEL_ATTRIBUTE, reviews);
        return REVIEW_LISTING_VIEW_NAME;
    }
}
