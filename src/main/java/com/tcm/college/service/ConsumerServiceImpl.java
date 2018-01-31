package com.tcm.college.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.tcm.college.entity.Consumer;
import com.tcm.college.entity.ConsumerRole;
import com.tcm.college.exception.CollegeException;
import com.tcm.college.repository.ConsumerRepository;
import com.tcm.college.utill.Constant;
import com.tcm.college.utill.Helper;

@Component
public class ConsumerServiceImpl implements ConsumerService {

	private static Logger logger = Logger.getLogger(ConsumerServiceImpl.class);

	@Autowired
	private ConsumerRepository consumerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Cacheable
	public Consumer getConsumer(String email) throws CollegeException {
		return consumerRepository.getConsumer(email);
	}

	@Override
	public void createConsumer(Consumer consumer) throws CollegeException {

		if (!validateCreateUser(consumer)) {
			logger.error("Invalid User." + consumer);
			throw new CollegeException("Invalid User creation.");
		}

		Consumer exsitConsumer = consumerRepository.getConsumer(consumer.getEmail());

		if (exsitConsumer != null) {
			throw new CollegeException("Consumer alreay exsits.");
		}

		String rawPassword = consumer.getPassword();
		consumer.setTmporaryPassword(rawPassword);
		consumer.setPassword(passwordEncoder.encode(rawPassword));
		consumer.setCreateDate(Helper.getCurrentDate());
		consumer.setInvalidLoginCount(0);
		consumer.setStatus(Constant.CONSUMER_ACTIVE);

		consumerRepository.createConsumer(consumer);
	}

	private Boolean validateCreateUser(Consumer consumer) throws CollegeException {

		if (consumer == null) {
			logger.error("Create Consumer validation fail. Consumer object is null.");
			return false;
		}

		// Super Admin account can't create through Reservation System.
		if (!validateUserRole(consumer.getConsumerRole().getRoleId())) {
			logger.error(
					"Create Consumer validation fail. Can't create super admin. " + consumer.getConsumerRole().getRoleId());
			return false;
		}

		if (StringUtils.isEmpty(consumer.getName()) || !consumer.getName().matches(Constant.REGEX.STRING_VALIDATE)) {
			logger.info("Create Consumer validation fail. Consumer name is not correct. " + consumer.getName());
			return false;
		}
		if (StringUtils.isEmpty(consumer.getEmail()) || !consumer.getEmail().matches(Constant.REGEX.EMAIL_VALIDATE)) {
			logger.info("Create Consumer validation fail. Email is not correct. " + consumer.getEmail());
			return false;
		}
		if (StringUtils.isEmpty(consumer.getPassword()) || (consumer.getPassword().trim().length() != 8)) {
			logger.info("Create Consumer validation fail. Password should be 8 characters long.");
			return false;
		}
		if (consumer.getConsumerRole() == null) {
			logger.info("Create Consumer validation fail. Consumer role is null.");
			return false;
		}

		return true;
	}

	private boolean validateUserRole(int roleId) throws CollegeException {
		
		List<ConsumerRole> consumerRoleList = consumerRepository.getConsumerRoleList();

		for (ConsumerRole role : consumerRoleList) {
			if (Constant.ROLE.SUPER_ADMIN.equals(role.getRoleName()) && role.getRoleId() == roleId) {
				return false;
			} else if (role.getRoleId() == roleId) {
				return true;
			}
		}

		return false;
	}

}
