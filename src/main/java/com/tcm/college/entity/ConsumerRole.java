package com.tcm.college.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consumer_role")
public class ConsumerRole implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "role_id")
	private int roleId;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "role_display")
	private String roleDisplay;

	@Column(name = "role_precedence")
	private int precedence;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDisplay() {
		return roleDisplay;
	}

	public void setRoleDisplay(String roleDisplay) {
		this.roleDisplay = roleDisplay;
	}

	public int getPrecedence() {
		return precedence;
	}

	public void setPrecedence(int precedence) {
		this.precedence = precedence;
	}

	@Override
	public String toString() {
		return "ConsumerRole [roleId=" + roleId + ", roleName=" + roleName + ", roleDisplay=" + roleDisplay
				+ ", precedence=" + precedence + "]";
	}
}
