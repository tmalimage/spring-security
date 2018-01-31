package com.tcm.college.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tcm.college.entity.Consumer;
import com.tcm.college.exception.CollegeException;
import com.tcm.college.service.ConsumerService;
import com.tcm.college.utill.Constant;
import com.tcm.college.utill.Helper;

@Controller
public class ConsumerController {
	
	private static Logger logger = Logger.getLogger(ConsumerController.class);
	
	@Autowired
	private ConsumerService consumerService;

	@RequestMapping(value ={ Constant.URL.ROOT, Constant.URL.LOGIN, Constant.URL.AUTHENTICATE }, method = { RequestMethod.GET, RequestMethod.POST })
    public String welcome(Authentication authentication)
    {
        if (authentication != null)
        {
            return "forward:/" + Helper.getRoleDefaultUrl(authentication);
        }
        return Constant.IRV.LOGIN;
    }
	
	@RequestMapping(value = Constant.URL.MANAGE_CONSUMER, method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView manageUser()
    {
        Authentication authentication = Helper.getAuthentication();
        
        String email = (authentication != null) ? authentication.getName() : Constant.NOT_AVALIABLE;
         
        ModelAndView modelAndView = null;
        try
        {
            modelAndView = new ModelAndView(Constant.IRV.MANAGE_CONSUMER);
            //TODO : Depends on business logic exception can occur.
                       
        }
        catch (Exception exception)
        {
            logger.error(email + ": error.", exception);
            modelAndView = new ModelAndView(Constant.IRV.ERROR);
            modelAndView.addObject(Constant.RA.ERR, "Unexpected error.");
        }
        return modelAndView;
    }
 
	@RequestMapping(value = Constant.URL.CREATE_CONSUMER, method = RequestMethod.POST)
    public ModelAndView createConsumer(Consumer consumer, Authentication authentication)
    {
        String email = (authentication != null) ? authentication.getName() : Constant.NOT_AVALIABLE;
        
        logger.info(email + ": is going to create new user.");
        ModelAndView modelAndView = null;
        try
        {  
            consumerService.createConsumer(consumer);
            logger.info(email + ": is successfully created a new consumer. " + consumer);
            modelAndView = new ModelAndView(Constant.IRV.MANAGE_CONSUMER);
            modelAndView.addObject(Constant.RA.CONSUMER, "Consumer is created.");
        }
        catch (CollegeException collegeException)
        {
            logger.error(email + ": error in creating consumer.", collegeException);
            modelAndView = new ModelAndView(Constant.IRV.ERROR);
            modelAndView.addObject(Constant.RA.ERR, "Error in creating consumer.");
        }
        return modelAndView;
    }
}
