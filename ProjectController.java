/**
 * 
 */
package lk.dialog.ist.reslo.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lk.dialog.ist.reslo.dto.ProjectDto;
import lk.dialog.ist.reslo.request.ProjectAddRequest;
import lk.dialog.ist.reslo.services.entities.Project;



/**
 * @author amel_33251
 *
 */
@Controller
public class ProjectController {
	
	@Autowired
	private ProjectDto projectDto;
	
	Authentication auth;    
	
	@RequestMapping(value = "/Project", method = RequestMethod.POST)
	public @ResponseBody  int createEmployee(
			@RequestParam(value = "projectName", required = false) String projectName,
			@RequestParam(value = "crNo", required = false) String crNo,
			@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "endDate", required = false) Date endDate,
			@RequestParam(value = "status", required = false) String status)
			
	{
		ProjectAddRequest projectAddRequest = new ProjectAddRequest();
		 auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {
			projectAddRequest.setCreatedUser(auth.getName());
			projectAddRequest.setProjectName(projectName);
			projectAddRequest.setCrNo(crNo);
			projectAddRequest.setStartDate(startDate);
			projectAddRequest.setEndDate(endDate);
			projectAddRequest.setStatus(status);
			
			projectDto.addProject(projectAddRequest);
		    } 
		    catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }
			}
		return 1;
	}
	
	@RequestMapping(value = "/updateProject", method = RequestMethod.POST)
	public @ResponseBody  int updateProject(
			@RequestParam(value = "projectName", required = false) String projectName,
			@RequestParam(value = "crNo", required = false) String crNo,
			@RequestParam(value = "actualStartDate", required = false) Date actualStartDate,
			@RequestParam(value = "actualEndDate", required = false) Date actualEndDate,
			@RequestParam(value = "updateReason", required = false) String updateReason,
			@RequestParam(value = "projectProgress", required = false) String projectProgress,
			@RequestParam(value = "status", required = false) String projectStatus)
			
			
	{		
		 auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {
			projectDto.updateProject(projectName, crNo, projectStatus, actualStartDate, actualEndDate, updateReason,projectProgress, auth.getName());
		    } 
		    catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }
			}
		return 2;
	}

		
		@RequestMapping(value = "/getProjectsByName", method = RequestMethod.GET)
		public @ResponseBody  List<Project> getAllProject(
				@RequestParam(value = "name") String projectName)
		{		
			 auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
			 if(auth.isAuthenticated())
				{
			try {
				return projectDto.getProjectsByName(projectName);
			    
			}catch (Exception e) 
			    {
					// TODO Auto-generated catch block
					e.printStackTrace();
			    }
			    }
			return null;
		}
		
		
		@RequestMapping(value = "/getProjectsByCrNo", method = RequestMethod.GET)
		public @ResponseBody  List<Project> getProjectsByCrNo(
				@RequestParam(value = "name") String crNo,
		        @RequestParam(value = "projectName") String projectName)
		{		
			auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
			 if(auth.isAuthenticated())
				{
			try {
				return projectDto.getProjectsByCrNo(projectName, crNo);
			    
			}catch (Exception e) 
			    {
					// TODO Auto-generated catch block
					e.printStackTrace();
			    }
			    }
			return null;
		}
		
		@RequestMapping(value = "/getAllProjects", method = RequestMethod.GET)
		public @ResponseBody  List<Project> getAllProjects()
		{	
			 auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
			 if(auth.isAuthenticated())
				{
			try {
				return projectDto.getAllProject();
			    
			}catch (Exception e) 
			    {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
			    }
			    }
			return null;
		}
		

		

}







