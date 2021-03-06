package com.olify.eprice.microservice.markets.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.olify.eprice.microservice.markets.Enums.Sex;
import com.olify.eprice.microservice.markets.Enums.Speciality;

import javax.persistence.EnumType;

@Embeddable
public class User {
	@Column(name="user_id")
	private Long userId;
	private String name;
	private String email;
	private String password;
	@Column(name="phone_no")
	private String phoneNo;
	@Enumerated(EnumType.STRING)
	private Sex sex;
	@Enumerated(EnumType.STRING)
	private Speciality speciality;
	@Column(name="created_date", insertable=false, updatable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createdAt;
	
	public User(String name, String email, String password, String phoneNo, Sex sex, Speciality speciality,
			Date createdAt) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNo = phoneNo;
		this.sex = sex;
		this.speciality = speciality;
		this.createdAt = createdAt;
	}

	public User() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(userId).append(name).append(email).append(password).append(phoneNo).append(sex)
				.append(speciality).append(createdAt);
		return builder.toString();
	}
}