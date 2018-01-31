package com.tcm.college.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tcm.college.entity.Consumer;
import com.tcm.college.entity.ConsumerRole;
import com.tcm.college.exception.CollegeException;

@Repository
public interface ConsumerRepository {

	public abstract Consumer getConsumer(String email) throws CollegeException;

	public abstract void updateLogInFailCount(String email, String authType, boolean accountLockRequire)
			throws CollegeException;

	public abstract List<ConsumerRole> getConsumerRoleList() throws CollegeException;

	public List<ConsumerRole> getConsumerRoleList(String role) throws CollegeException;

	public abstract void createConsumer(Consumer consumer) throws CollegeException;

}
