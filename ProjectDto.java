/**
 * 
 */
package lk.dialog.ist.reslo.dto;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lk.dialog.ist.reslo.dao.ApplictionUserDao;
import lk.dialog.ist.reslo.dao.EmployeeDao;
import lk.dialog.ist.reslo.dao.ProjectDao;
import lk.dialog.ist.reslo.request.EmployeeCreateRequest;
import lk.dialog.ist.reslo.request.ProjectAddRequest;
import lk.dialog.ist.reslo.request.UserCreateRequest;
import lk.dialog.ist.reslo.services.entities.ApplicationUser;
import lk.dialog.ist.reslo.services.entities.Employee;
import lk.dialog.ist.reslo.services.entities.Project;



/**
 * @author amel_33251
 *
 */
public class ProjectDto {
	@Autowired
	private ProjectDao projectDao;
	
	public void addProject(ProjectAddRequest projectAddRequest) throws Exception{
		projectDao.addProject(projectAddRequest);
		
	}
	
	public void updateProject(String projectName, String crNo,String projectStatus, Date actualStartDate, Date actualEndDate,String updatedReason,String projectProgress, String updatedUser) throws Exception{
		projectDao.updateProfileStatus(projectName, crNo, projectStatus, actualStartDate, actualEndDate, updatedReason, projectProgress,updatedUser);		
	}
	
	public List<Project> getProjectsByName(String projectName) throws Exception{
		return projectDao.getProjectsByName(projectName);		
	}
	
	

	public List<Project> getProjectsByCrNo(String projectName,String crNo) throws Exception{
		return projectDao.getProjectsByCrNo(projectName, crNo);
	}
	
	public List<Project> getAllProject() throws Exception{
		return projectDao.getAllProject();	
	}



	
	

}
