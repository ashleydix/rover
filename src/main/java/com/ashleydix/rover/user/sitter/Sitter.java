package com.ashleydix.rover.user.sitter;

import com.ashleydix.rover.user.User;
import com.google.common.annotations.VisibleForTesting;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "sitterRank")
public class Sitter extends User {

    /** sitter Score is 5 times the fraction of the English alphabet comprised by
     * the distinct letters in what we've recovered of the sitter's name.
     */
    double sitterScore;

    /**Ratings Score is sum the average of their stay ratings.*/
    double ratingsScore;

    /** The Overall sitter Rank is a weighted average of the sitter Score and Ratings Score,
     * weighted by the number of stays. When a sitter has no stays, their Overall sitter Rank
     * is equal to the sitter Score. When a sitter has 10 or more stays, their Overall sitter
     * Rank is equal to the Ratings Score.
     */

    double sitterRank;

    int stayCount;

    public Sitter(){
        super();
    }

    public Sitter(String email, String name, String phone, String image) {
        super(email, name, phone, image);
        this.sitterScore = calculateSitterScore(name);
        this.ratingsScore = 0;
        this.stayCount = 0;
        this.sitterRank = sitterScore;
    }

    public double getSitterScore() {
        return sitterScore;
    }

    public void setSitterScore(double sitterScore) {
        this.sitterScore = sitterScore;
        this.sitterRank = calculateOverallSitterRank();
    }

    public double getRatingsScore() {
        return ratingsScore;
    }

    public int getStayCount() {
        return stayCount;
    }

    private double calculateOverallSitterRank() {
        if (stayCount == 0) {
            return sitterScore;
        } else {
            return ((sitterScore * Math.max(0, 10 - stayCount)) +
                    (ratingsScore * Math.min(10, stayCount))) / 10.00;
        }
    }

    public void addRating(double newRating) {
        this.ratingsScore = this.ratingsScore * (stayCount / (stayCount +1.00)) + (newRating * (1.00/(stayCount+1)));
        this.stayCount++;
        this.sitterRank = calculateOverallSitterRank();
    }

    @VisibleForTesting
    protected double calculateSitterScore(String name){
        int uniqueLetterCount = 0;
        int[] characters = new int[26];
        name = name.toLowerCase().replaceAll("[^a-z]", "");
        for(char c: name.toCharArray()){
            if(characters[c-'a'] == 0){
                uniqueLetterCount++;
                characters[c-'a']++;
            }
        }
        return (uniqueLetterCount/26.0)*5.0;
    }


    public double getSitterRank() {
        return sitterRank;
    }

    public void setSitterRank(double sitterRank) {
        this.sitterRank = sitterRank;
    }

    @Override
    public String toString() {
        return "sitter{" +
                "sitterScore=" + sitterScore +
                ", ratingsScore=" + ratingsScore +
                ", stayCount=" + stayCount +
                '}';
    }
}
