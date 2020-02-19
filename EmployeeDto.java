/**
 * 
 */
package lk.dialog.ist.reslo.dto;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lk.dialog.ist.reslo.dao.ApplictionUserDao;
import lk.dialog.ist.reslo.dao.EmployeeDao;
import lk.dialog.ist.reslo.request.EmployeeCreateRequest;
import lk.dialog.ist.reslo.request.ProjectAddRequest;
import lk.dialog.ist.reslo.request.UserCreateRequest;
import lk.dialog.ist.reslo.services.entities.ApplicationUser;
import lk.dialog.ist.reslo.services.entities.Employee;



/**
 * @author amel_33251
 *
 */
public class EmployeeDto {
	@Autowired
	private EmployeeDao employeeDao;
	
	public void createEmployee(EmployeeCreateRequest employeeCreateRequest) throws Exception{
		employeeDao.addEmployee(employeeCreateRequest);
		
	}
	
	public void removeEmployee(String userId, String updatedUser) throws Exception{
		employeeDao.updateReourseStatus(userId,updatedUser);		
	}
	
	public List<Employee> getEmployeesByName(String name) throws Exception{
		return employeeDao.getEmployeesByName(name);		
	}
	
	public List<Employee> getAllEmployee() throws Exception{
		return employeeDao.getAllEmployee();		
	}
	
	public void updateResource(String employeeNo, String employeeName,String contactNo, String empRole,String updatedUser) throws Exception{
		employeeDao.updateResource(employeeNo, employeeName, contactNo, empRole, updatedUser);		
	}
	

}
