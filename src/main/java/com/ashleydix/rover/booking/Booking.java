package com.ashleydix.rover.booking;

import com.ashleydix.rover.dogs.Dog;
import com.ashleydix.rover.user.User;
import com.ashleydix.rover.user.sitter.Sitter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class  Booking {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne()
    @JoinColumn(name = "ownerId", referencedColumnName = "id")
    private User owner;

    @OneToOne()
    @JoinColumn(name = "sitterId", referencedColumnName = "id")
    private Sitter sitter;

    @ManyToMany(
            cascade = {
            CascadeType.MERGE
    }
    )
    @JoinTable(name = "dogs_booking",
            joinColumns = @JoinColumn(name = "bookingId"),
            inverseJoinColumns = @JoinColumn(name = "dogId")
    )
    private Set<Dog> dogs;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    public Booking() {
    }

    public Booking(User owner, Sitter sitter, LocalDate startDate, LocalDate endDate) {
        this.owner = owner;
        this.sitter = sitter;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dogs = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Sitter getSitter() {
        return sitter;
    }

    public void setSitter(Sitter sitter) {
        this.sitter = sitter;
    }

    public Set<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(Set<Dog> dogs) {
        this.dogs = dogs;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", owner=" + owner +
                ", sitter=" + sitter +
                ", dogs=" + dogs +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
