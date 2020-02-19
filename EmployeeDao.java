package lk.dialog.ist.reslo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lk.dialog.ist.reslo.request.EmployeeCreateRequest;
import lk.dialog.ist.reslo.request.ProjectAddRequest;
import lk.dialog.ist.reslo.request.UserCreateRequest;
import lk.dialog.ist.reslo.services.entities.ApplicationUser;
import lk.dialog.ist.reslo.services.entities.Employee;
import lk.dialog.ist.reslo.services.entities.Project;
import lk.dialog.ist.reslo.services.repository.ApplicationUserRepo;
import lk.dialog.ist.reslo.services.repository.EmployeeRepo;
import lk.dialog.ist.reslo.services.util.EmployeeStatus;
import lk.dialog.ist.reslo.services.util.UserStatus;

/**
 * @author amel_33251
 *
 */
@Component
public class EmployeeDao{
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	public Employee addEmployee(EmployeeCreateRequest employeeCreateRequest) throws Exception{
		// TODO Auto-generated method stub
		Employee employee = new Employee();
		employee.setEmpId(employeeCreateRequest.getEmpId());
		employee.setEmpName(employeeCreateRequest.getEmpName());
		employee.setContactNo(employeeCreateRequest.getMobile());
		employee.setAddedBy(employeeCreateRequest.getCreatedUser());
		employee.setUserRole(employeeCreateRequest.getEmpRole());
		employee.setAddedOn(new Date());
		employee.setEmployeeStatus(EmployeeStatus.ACTIVE);
		employeeRepo.save(employee);
		
		return employee;
	}
	

	public void updateReourseStatus(String userId, String updatedUser) throws Exception{
//		employeeRepo.updateEmployeeStatus(userName,updatedUser);
		Employee employee = employeeRepo.findByEmpId(userId);
		if(employee!=null){
			employee.setEmployeeStatus(EmployeeStatus.DELETED);
			employee.setUpdatedBy(updatedUser);
			employee.setUpdatedOn(new Date());
			employeeRepo.save(employee);
		}else{
			System.out.println("No employee fuond");
		}
		
	}
	
	public List<Employee> getAllEmployee() throws Exception{
		return (List<Employee>) employeeRepo.findAll();
	}
	
	public List<Employee> getEmployeesByName(String name) throws Exception{
		return (List<Employee>) employeeRepo.getEmployeesByname(name);
	}
	
	public void updateResource(String employeeNo,String employeeName,String contactNo,String empRole,String updatedUser) throws Exception{
		
		Employee employee = employeeRepo.findByEmpId(employeeNo);
		
		
		if(employee!=null){
			
			employee.setEmpName(employeeName);
			employee.setContactNo(contactNo);
			employee.setUserRole(empRole);
			employee.setUpdatedBy(updatedUser);
			employee.setUpdatedOn(new Date());
			
			employeeRepo.save(employee);
			
		}else{
			System.out.println("No Employee found");
		}
		
	}
	
}
