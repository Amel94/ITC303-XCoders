/**
 * 
 */
package lk.dialog.ist.reslo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lk.dialog.ist.reslo.dto.EmployeeDto;
import lk.dialog.ist.reslo.request.EmployeeCreateRequest;
import lk.dialog.ist.reslo.services.entities.Employee;


/**
 * @author amel_33251
 *
 */
@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeDto employeeDto;
	
	Authentication auth;    
	
	@RequestMapping(value = "/Resource", method = RequestMethod.POST)
	public @ResponseBody  int createEmployee(
			@RequestParam(value = "employeeNo", required = false) String empId,
			@RequestParam(value = "employeeName", required = false) String empName,
			@RequestParam(value = "contactNo", required = false) String mobile,
			@RequestParam(value = "empRole", required = false) String empRole)
			
	{
		EmployeeCreateRequest employeeCreateRequest = new EmployeeCreateRequest();
		 auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {
			
			employeeCreateRequest.setCreatedUser(auth.getName());
			employeeCreateRequest.setEmpId(empId);
			employeeCreateRequest.setEmpName(empName);
			employeeCreateRequest.setMobile(mobile);
			employeeCreateRequest.setEmpRole(empRole);
			
			
				employeeDto.createEmployee(employeeCreateRequest);
		    } 
		    catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }
			}
		return 1;
	}
	
		@RequestMapping(value = "/removeResource", method = RequestMethod.POST)
	public @ResponseBody  int removeEmployee(
			@RequestParam(value = "employeeNo", required = false) String empId)
	{		
			auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
			 if(auth.isAuthenticated())
				{
		try {
			    
				employeeDto.removeEmployee(empId,auth.getName());
		    } 
		    catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }
				}
		return 0;
	}
		
		@RequestMapping(value = "/updateResource", method = RequestMethod.POST)
		public @ResponseBody  int updateProject(
				@RequestParam(value = "employeeNo", required = false) String employeeNo,
				@RequestParam(value = "employeeName", required = false) String employeeName,
				@RequestParam(value = "contactNo", required = false) String contactNo,
				@RequestParam(value = "empRole", required = false) String empRole)
		{		
			auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
			 if(auth.isAuthenticated())
				{
			try {
				employeeDto.updateResource(employeeNo, employeeName, contactNo, empRole, auth.getName());
			    } 
			    catch (Exception e) 
			    {
					// TODO Auto-generated catch block
					e.printStackTrace();
			    }
			  }
			return 2;
		}

		
		@RequestMapping(value = "/getEmployeeByName", method = RequestMethod.GET)
		public @ResponseBody  List<Employee> getAllEmployee(
				@RequestParam(value = "name") String userName)
		{		
			auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
			 if(auth.isAuthenticated())
				{
			try {
				return employeeDto.getEmployeesByName(userName);
			    
			}catch (Exception e) 
			    {
					// TODO Auto-generated catch block
					e.printStackTrace();
			    }
			    }
			return null;
		}
		

}







