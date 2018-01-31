package com.tcm.college.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import com.tcm.college.entity.Consumer;
import com.tcm.college.entity.ConsumerRole;
import com.tcm.college.exception.CollegeException;
import com.tcm.college.repository.ConsumerRepository;
import com.tcm.college.utill.Constant;
import com.tcm.college.utill.Helper;

@Component
public class CollegeAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private static Logger logger = Logger.getLogger(CollegeAuthenticationSuccessHandler.class);
	
	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String email = authentication.getName();
    
		String targetUrl = Helper.getRoleDefaultUrl(authentication);

        try
        {
        	consumerRepository.updateLogInFailCount(email, Constant.AUTH_SUCCESS, false);

            Consumer consumer = consumerRepository.getConsumer(email);

            HttpSession session = request.getSession(false);
            
            if (session != null)
            {               
                session.setAttribute(Constant.SA.CONSUMER_NAME, consumer.getName());
                session.setAttribute(Constant.SA.CONSUMER_PRECEDENCE, consumer.getConsumerRole().getPrecedence());
                session.setAttribute(Constant.SA.CONSUMER_ID, consumer.getUserId());   
                
                String role = consumer.getConsumerRole().getRoleName();
                
                if(Constant.ROLE.SUPER_ADMIN.equalsIgnoreCase(role))
                {
                    List<ConsumerRole> roleList = consumerRepository.getConsumerRoleList(role);
                    logger.info(email + ": corresponding role list for " + Constant.ROLE.SUPER_ADMIN + " : " + roleList);
                    session.setAttribute(Constant.SA.CONSUMER_ROLE_LIST, roleList);
                }
            }
            else
            {
                SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
                logoutHandler.logout(request, response, authentication);
                targetUrl = Constant.URL.ROOT;
            }
        }
        catch (CollegeException collegeException)
        {
            //TODO: add log
            targetUrl = Constant.URL.ROOT;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(targetUrl);
        requestDispatcher.forward(request, response);
	}

}
