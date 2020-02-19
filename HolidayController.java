/**
 * 
 */
package lk.dialog.ist.reslo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lk.dialog.ist.reslo.dao.HolidayDao;
import lk.dialog.ist.reslo.dto.HolidayDto;
import lk.dialog.ist.reslo.dto.HolidayRequestDto;
import lk.dialog.ist.reslo.services.entities.Holidays;



/**
 * @author amel_33251
 *
 */
@Controller
public class HolidayController {
	
	@Autowired
	private HolidayDao holidayDao;
	
	@Autowired
	private HolidayDto holidayDto;
	
	Authentication auth;    

	@RequestMapping(value = "/DialogHolidays", method = RequestMethod.POST)
	public @ResponseBody  int createHoliday(
			@RequestBody List<HolidayRequestDto> requestDto)
			
	{
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {
			
			holidayDao.addHoliday(requestDto);
			
			/*employeeCreateRequest.setEmpId(empId);
			employeeCreateRequest.setEmpName(empName);
			employeeCreateRequest.setMobile(mobile);
			employeeCreateRequest.setEmpRole(empRole);
			employeeCreateRequest.setCreatedUser(createdBy);
			
				employeeDto.createEmployee(employeeCreateRequest);*/
		    } 
		    catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }
			}
		return 1;
	}
	
	@RequestMapping(value = "/getAllHolidays", method = RequestMethod.GET)
	public @ResponseBody  List<Holidays> getAllHolidays()
	{	
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {
			return holidayDto.getAllHolidays();
		    
		}catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
		    }
		
			}
		 return null;
	}	

}







