/**
 * 
 */
package lk.dialog.ist.reslo.dto;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lk.dialog.ist.reslo.dao.ApplictionUserDao;
import lk.dialog.ist.reslo.dao.EmployeeDao;
import lk.dialog.ist.reslo.dao.HolidayDao;
import lk.dialog.ist.reslo.dao.ProjectDao;
import lk.dialog.ist.reslo.request.EmployeeCreateRequest;
import lk.dialog.ist.reslo.request.ProjectAddRequest;
import lk.dialog.ist.reslo.request.UserCreateRequest;
import lk.dialog.ist.reslo.services.entities.ApplicationUser;
import lk.dialog.ist.reslo.services.entities.Employee;
import lk.dialog.ist.reslo.services.entities.Holidays;
import lk.dialog.ist.reslo.services.entities.Project;



/**
 * @author amel_33251
 *
 */
public class HolidayDto {
	
	@Autowired
	private HolidayDao holidayDao;
	
		
	public List<Holidays> getAllHolidays() throws Exception{
		return holidayDao.getAllHolidays();	
	}


	
	

}
