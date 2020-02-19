/**
 * 
 */
package lk.dialog.ist.reslo.controller;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lk.dialog.ist.reslo.services.Config.UserStatus;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;


import org.springframework.security.core.Authentication;

/**
 * @author amel_33251
 *
 */
@Controller
public class WebController {
	
    private final Logger logger = LoggerFactory.getLogger(WebController.class);
    Authentication auth;    
    private ModelAndView modelValidate(String viewName) throws NamingException, Exception
    {   ModelAndView model = new ModelAndView();
	    auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
    	String userName = null;
    	String email = null;
    	String fullName = null;
    	String title = null;
    	String lastLogin = null;
    	byte[]  image = null;
    		// userName = userDetails.getUsername();
			// email = userDetails.getEmail();
			 fullName = auth.getName();
			// title = userDetails.getDesignation();
			// lastLogin = userDetails.getLastLogin();
			// image = userDetails.getPhoto();				
        if ( image != null) {                
            byte[] encodeBase64 = Base64.encode(image);
            String base64Encoded = new String(encodeBase64, "UTF-8");	
    		model.addObject("userImage",base64Encoded);
    		model.addObject("image","true");
        }
      
    	if("anonymousUser".equals(auth.getName()))
		{
			model.setViewName("login");
			model.addObject("error", UserStatus.SESSION_EXPIRED.getMsg());
		}else{			
			model.setViewName(viewName);
			model.addObject("userId",userName);
			model.addObject("userName",userName);
			model.addObject("email",email);
			model.addObject("fullName",fullName);
			model.addObject("title",title);
		}
		return model;
    }
    
    public ModelAndView modelRedurectLogin()
    {   ModelAndView model = new ModelAndView();
	    auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
    	if("anonymousUser".equals(auth.getName()))
		{
			model.setViewName("login");
			model.addObject("error", UserStatus.SESSION_EXPIRED.getMsg());
		}
		return model;
    }

	    
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView welcome(@RequestParam(required=false) String error,@RequestParam(required=false) String message) {
		ModelAndView model = new ModelAndView();
		
		if (error != null) {
			model.addObject("error", error);
		}
		if (message != null) {
			model.addObject("message", message);
		}	
		model.setViewName("login");
		return model;
	}

	    
	    
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() throws Exception, NamingException {
		return modelValidate("home");
	}
	
	
	@RequestMapping(value = "/AddResource", method = RequestMethod.GET)
	public ModelAndView AddResource() throws Exception, NamingException {
		return modelValidate("AddResource");
	}
	
	@RequestMapping(value = "/RemoveResource", method = RequestMethod.GET)
	public ModelAndView RemoveResource() throws Exception, NamingException {
		return modelValidate("RemoveResource");
	}
	
	@RequestMapping(value = "/AddProject", method = RequestMethod.GET)
	public ModelAndView AddProject() throws Exception, NamingException {
		return modelValidate("AddProject");
	}
	
	@RequestMapping(value = "/UpdateProject", method = RequestMethod.GET)
	public ModelAndView UpdateProject() throws Exception, NamingException {
		return modelValidate("UpdateProject");
	}
	
	@RequestMapping(value = "/DialogHolidays", method = RequestMethod.GET)
	public ModelAndView DialogHolidays() throws Exception, NamingException {
		return modelValidate("DialogHolidays");
	}
	
	@RequestMapping(value = "/CreateUser", method = RequestMethod.GET)
	public ModelAndView CreateUser() throws Exception, NamingException {
		return modelValidate("CreateUser");
	}

	@RequestMapping(value = "/ResourceAllocation", method = RequestMethod.GET)
	public ModelAndView ResourceAllocation() throws Exception, NamingException {
		return modelValidate("ResourceAllocation");
	}
	
	@RequestMapping(value = "/ResourceBooking", method = RequestMethod.GET)
	public ModelAndView ResourceBooking() throws Exception, NamingException {
		return modelValidate("ResourceBooking");
	}
	
	@RequestMapping(value = "/GanttChart", method = RequestMethod.GET)
	public ModelAndView GanttChart() throws Exception, NamingException {
		return modelValidate("GanttChart");
	}

	
	@RequestMapping(value = "/newHome", method = RequestMethod.GET)
	public ModelAndView newHome() throws Exception, NamingException {
		return modelValidate("newHome");
	}


	@RequestMapping(value = "/AddResource2", method = RequestMethod.GET)
	public ModelAndView AddResource2() throws Exception, NamingException {
		return modelValidate("AddResource2");
	}
	
	@RequestMapping(value = "/AllEmpReport", method = RequestMethod.GET)
	public ModelAndView AllEmpReport() throws Exception, NamingException {
		return modelValidate("AllEmpReport");
	}
	    
	@RequestMapping(value = "/AllProjectReport", method = RequestMethod.GET)
	public ModelAndView AllProjectReport() throws Exception, NamingException {
		return modelValidate("AllProjectReport");
	}
	
	@RequestMapping(value = "/WorloadSummaryReport", method = RequestMethod.GET)
	public ModelAndView WorloadSummaryReport() throws Exception, NamingException {
		return modelValidate("WorloadSummaryReport");
	}
	
	@RequestMapping(value = "/AllocationChart", method = RequestMethod.GET)
	public ModelAndView AllocationChart() throws Exception, NamingException {
		return modelValidate("AllocationChart");
	}
	
	@RequestMapping(value = "/BookingEmpSummaryReport", method = RequestMethod.GET)
	public ModelAndView BookingEmpSummaryReport() throws Exception, NamingException {
		return modelValidate("BookingEmpSummaryReport");
	}
	
	@RequestMapping(value = "/BookingProjectSummaryReport", method = RequestMethod.GET)
	public ModelAndView BookingProjectSummaryReport() throws Exception, NamingException {
		return modelValidate("BookingProjectSummaryReport");
	}
	
	@RequestMapping(value = "/ProjectWiseReport", method = RequestMethod.GET)
	public ModelAndView ProjectWiseReport() throws Exception, NamingException {
		return modelValidate("ProjectWiseReport");
	}
	@RequestMapping(value = "/IdleEmpReport", method = RequestMethod.GET)
	public ModelAndView IdleEmpReport() throws Exception, NamingException {
		return modelValidate("IdleEmpReport");
	}
	
	@RequestMapping(value = "/AllocatedEmpReport", method = RequestMethod.GET)
	public ModelAndView AllocatedEmpReport() throws Exception, NamingException {
		return modelValidate("AllocatedEmpReport");
	}
	
	@RequestMapping(value = "/EmpWiseReport", method = RequestMethod.GET)
	public ModelAndView EmpWiseReport() throws Exception, NamingException {
		return modelValidate("EmpWiseReport");
	}
	    
}
