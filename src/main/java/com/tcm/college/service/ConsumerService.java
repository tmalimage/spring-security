package com.tcm.college.service;

import org.springframework.stereotype.Service;

import com.tcm.college.entity.Consumer;
import com.tcm.college.exception.CollegeException;

@Service
public interface ConsumerService {

	 public abstract Consumer getConsumer(String email) throws CollegeException;
	 public void createConsumer(Consumer consumer) throws CollegeException;
	
}
