package com.ashleydix.rover.user;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements java.io.Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private Instant created;

	@Column(unique = true, nullable = false)
	private String email;

	private String name;
	
	private String phone;
	
	private String image;

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", created=" + created +
				", email='" + email + '\'' +
				", name='" + name + '\'' +
				", phone='" + phone + '\'' +
				", image='" + image + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return Objects.equals(id, user.id) &&
				Objects.equals(created, user.created) &&
				Objects.equals(email, user.email) &&
				Objects.equals(name, user.name) &&
				Objects.equals(phone, user.phone) &&
				Objects.equals(image, user.image);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, created, email, name, phone, image);
	}

	public User() {
	}

	public User(String email, String name, String phone, String image) {
		this.email = email;
		this.created = Instant.now();
		this.name = name;
		this.phone = phone;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getCreated() {
		return created;
	}

	public void setCreated(Instant created) {
		this.created = created;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
