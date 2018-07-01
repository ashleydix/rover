package com.ashleydix.rover.dogs;

import com.ashleydix.rover.user.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"ownerId", "name"})
})
public class Dog {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "ownerId", referencedColumnName = "id")
    private User owner;

    private String name;

    public Dog() {
    }

    public Dog(String name, User owner) {
        this.name = name;
        this.owner = owner;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", owner=" + owner +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dog)) return false;
        Dog dog = (Dog) o;
        return Objects.equals(id, dog.id) &&
                Objects.equals(owner, dog.owner) &&
                Objects.equals(name, dog.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, owner, name);
    }
}
