package com.tcm.college.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.tcm.college.entity.Consumer;
import com.tcm.college.exception.CollegeException;
import com.tcm.college.repository.ConsumerRepository;
import com.tcm.college.utill.Constant;

@Component
public class CollegeAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authenticationException) throws IOException, ServletException {

		String email = request.getParameter("email");

		String errMsg = "Error in log-in.";

		String forwardUrl = "/";

		try {
			if (authenticationException instanceof BadCredentialsException) {
				
				Consumer consumer = consumerRepository.getConsumer(email);

				if (consumer == null) {
					errMsg = "Consumer is not exists.";
				} else {
					
					boolean accountLockRequire = false;

					if (3 == (consumer.getInvalidLoginCount() + 1)) {
						errMsg = "User account has been locked, due to invalid password for 3 times.";
						accountLockRequire = true;
					} else {
						errMsg = "Incorrect password";
					}
					consumerRepository.updateLogInFailCount(email, Constant.AUTH_FAIL, accountLockRequire);
				}
			} else if (authenticationException instanceof DisabledException) {
				errMsg = "User is disabled.";
			} else if (authenticationException instanceof LockedException) {
				errMsg = "User account is locked.";
			} else if (authenticationException instanceof UsernameNotFoundException) {
				errMsg = "Consumer is not exists.";
			} else if (authenticationException instanceof CredentialsExpiredException) {
				errMsg = "User's credentials is expired.";
			}
		} catch (CollegeException collegeException) {
			//TODO : add log
		}

		request.setAttribute(Constant.RA.ERR, errMsg);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(forwardUrl);
		requestDispatcher.forward(request, response);
	}

}
