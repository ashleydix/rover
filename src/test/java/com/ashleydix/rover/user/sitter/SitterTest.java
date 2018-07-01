package com.ashleydix.rover.user.sitter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SitterTest {

    @Test
    public void addRating() {
        Sitter sitter = new Sitter();
        sitter.setSitterScore(2.50);

        assertEquals(2.50, sitter.getSitterRank(), 0.001);
        addRatingAndAssertOverallSitterRating(sitter, 2.75, 5.0);
        addRatingAndAssertOverallSitterRating(sitter, 3.00, 5.0);
        addRatingAndAssertOverallSitterRating(sitter, 3.25, 5.0);
        addRatingAndAssertOverallSitterRating(sitter, 3.50, 5.0);
        addRatingAndAssertOverallSitterRating(sitter, 3.75, 5.0);
        addRatingAndAssertOverallSitterRating(sitter, 4.00, 5.0);
        addRatingAndAssertOverallSitterRating(sitter, 4.25, 5.0);
        addRatingAndAssertOverallSitterRating(sitter, 4.50, 5.0);
        addRatingAndAssertOverallSitterRating(sitter, 4.75, 5.0);
        addRatingAndAssertOverallSitterRating(sitter, 5.00, 5.0);
        addRatingAndAssertOverallSitterRating(sitter, 5.00, 5.0);
        addRatingAndAssertOverallSitterRating(sitter, 5.00, 5.0);
    }

        @Test
    public void testSitterScore(){
        Sitter sitter = new Sitter();

        assertEquals(5.0 * (2.0/26),  sitter.calculateSitterScore("bob"), 0.001);
        assertEquals(5.0 * (2.0/26),  sitter.calculateSitterScore("Bob"), 0.001);
        assertEquals(5.0 * (2.0/26),  sitter.calculateSitterScore("Bobbbbb"), 0.001);
        assertEquals(5.0 * (26.0/26),  sitter.calculateSitterScore("abcdefghijklmnopqrstuvwxyz"), 0.001);
    }

    private void addRatingAndAssertOverallSitterRating(Sitter sitter, double expectedRating, double newRating){
        sitter.addRating(newRating);
        assertEquals(expectedRating, sitter.getSitterRank(), 0.001);
    }
}