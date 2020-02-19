/**
 * 
 */
package lk.dialog.ist.reslo.dto;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import lk.dialog.ist.reslo.dao.AllocationDao;
import lk.dialog.ist.reslo.dao.ApplictionUserDao;
import lk.dialog.ist.reslo.dao.EmployeeDao;
import lk.dialog.ist.reslo.dao.ProjectDao;
import lk.dialog.ist.reslo.request.AllocationRequest;
import lk.dialog.ist.reslo.request.EmployeeCreateRequest;
import lk.dialog.ist.reslo.request.ProjectAddRequest;
import lk.dialog.ist.reslo.request.UserCreateRequest;
import lk.dialog.ist.reslo.response.AllocationResponse;
import lk.dialog.ist.reslo.response.DeptCountResponse;
import lk.dialog.ist.reslo.services.entities.Allocation;
import lk.dialog.ist.reslo.services.entities.ApplicationUser;
import lk.dialog.ist.reslo.services.entities.Employee;
import lk.dialog.ist.reslo.services.entities.Project;



/**
 * @author amel_33251
 *
 */
public class AllocationDto {
	
	@Autowired
	private AllocationDao allocationDao;
	
	public void addAllocation(AllocationRequest allocationAddRequest) throws Exception{
		allocationDao.addAllocation(allocationAddRequest);
		
	}
	
	public void addBooking(AllocationRequest allocationAddRequest) throws Exception{
		allocationDao.addBooking(allocationAddRequest);
		
	}
	
	public void updateAllocation(AllocationRequest allocationRemoveRequest) throws Exception{
		allocationDao.updateAllocation(allocationRemoveRequest);
		
	}
	
	public void updateBooking(AllocationRequest allocationRemoveRequest) throws Exception{
		allocationDao.updateBooking(allocationRemoveRequest);
		
	}
	
	public void deleteAllocation(AllocationRequest allocationRemoveRequest) throws Exception{
		allocationDao.deleteAllocation(allocationRemoveRequest);
		
	}

		
	public List<Allocation> getAllAllocation() throws Exception{
		return allocationDao.getAllAllocation();	
	}
	
	
	public List<AllocationResponse> getAllocationByEmp(String id) throws Exception{
		return allocationDao.getAllocationByEmp(id);		
	}
	
	public List<AllocationResponse> getAllocationByProject(String projectName, String userRole) throws Exception{
		return allocationDao.getAllocationByProject(projectName,userRole);		
	}
	
	public List<AllocationResponse> getAllocationBySelectedProject(String projectName) throws Exception{
		return allocationDao.getAllocationBySelectedProject(projectName);		
	}
	
	public List<AllocationResponse> getAllProjectsByName() throws Exception{
		return allocationDao.getAllProjectsByName();		
	}
	
	public List<AllocationResponse> getAllFutureProjectsByName() throws Exception{
		return allocationDao.getAllFutureProjectsByName();		
	}
	
	public List<DeptCountResponse> getDeptCountByProject(String projectName,String crNo) throws Exception{
		return allocationDao.getDeptCountByProject(projectName,crNo);		
	}
	
	public List<AllocationResponse> getProjectWiseData(String projectName,String crNo,String userRole) throws Exception{
		return allocationDao.getProjectWiseData(projectName,crNo,userRole);		
	}

}
