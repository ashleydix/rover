package com.ashleydix.rover.reviews;

import com.ashleydix.rover.booking.Booking;
import com.ashleydix.rover.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    LocalDateTime reviewDate;

    @OneToOne()
    @JoinColumn(name = "ownerId", referencedColumnName = "id")
    private User owner;

    @OneToOne()
    @JoinColumn(name = "sitterId", referencedColumnName = "id")
    private User sitter;

    @OneToOne()
    @JoinColumn(name = "bookingId", referencedColumnName = "id")
    private Booking booking;

    @Column(name="review_text", length = 100000)
    private String text;

    private int rating;

    public Review() {
    }

    public Review(User owner, User sitter, Booking booking, String text, int rating) {
        this.owner = owner;
        this.sitter = sitter;
        this.booking = booking;
        this.text = text;
        this.rating = rating;
        this.reviewDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", reviewDate=" + reviewDate +
                ", owner=" + owner.getId() +
                ", sitter=" + sitter.getId() +
                ", booking=" + booking.getId() +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getSitter() {
        return sitter;
    }

    public void setSitter(User sitter) {
        this.sitter = sitter;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
