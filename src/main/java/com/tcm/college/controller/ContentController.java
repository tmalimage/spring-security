package com.tcm.college.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tcm.college.utill.Constant;
import com.tcm.college.utill.Helper;

@Controller
public class ContentController {

	private Logger logger = Logger.getLogger(ContentController.class);
	
	@RequestMapping(value = Constant.URL.CREATE_CONTENT, method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView createContent()
    {
        Authentication authentication = Helper.getAuthentication();
        
        String email = (authentication != null) ? authentication.getName() : Constant.NOT_AVALIABLE;
         
        ModelAndView modelAndView = null;
        try
        {
            modelAndView = new ModelAndView(Constant.IRV.CREATE_CONTENT);
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
	
	@RequestMapping(value = Constant.URL.VIEW_CONTENT, method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView viewContent()
    {
        Authentication authentication = Helper.getAuthentication();
        
        String email = (authentication != null) ? authentication.getName() : Constant.NOT_AVALIABLE;
         
        ModelAndView modelAndView = null;
        try
        {
            modelAndView = new ModelAndView(Constant.IRV.VIEW_CONTENT);
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
	
}
