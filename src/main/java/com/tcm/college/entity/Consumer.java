package com.tcm.college.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "consumer")
public class Consumer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "invalid_login_count")
	private int invalidLoginCount;

	@Column(name = "status")
	private int status;
	
	@Transient
    private String tmporaryPassword;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private ConsumerRole consumerRole;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getInvalidLoginCount() {
		return invalidLoginCount;
	}

	public void setInvalidLoginCount(int invalidLoginCount) {
		this.invalidLoginCount = invalidLoginCount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ConsumerRole getConsumerRole() {
		return consumerRole;
	}

	public void setConsumerRole(ConsumerRole consumerRole) {
		this.consumerRole = consumerRole;
	}

	public String getTmporaryPassword() {
		return tmporaryPassword;
	}

	public void setTmporaryPassword(String tmporaryPassword) {
		this.tmporaryPassword = tmporaryPassword;
	}

	@Override
	public String toString() {
		return "Consumer [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", createDate=" + createDate + ", invalidLoginCount=" + invalidLoginCount + ", status=" + status
				+ ", consumerRole=" + consumerRole + "]";
	}
	
	
	
}
