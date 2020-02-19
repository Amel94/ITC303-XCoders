package lk.dialog.ist.reslo.dao;

import java.util.ArrayList;
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
import lk.dialog.ist.reslo.services.repository.ProjectRepo;
import lk.dialog.ist.reslo.services.util.EmployeeStatus;
import lk.dialog.ist.reslo.services.util.ProjectStatus;
import lk.dialog.ist.reslo.services.util.UserStatus;


/**
 * @author amel_33251
 *
 */
@Component
public class ProjectDao{
	
	@Autowired
	private ProjectRepo projectRepo;
	
	public Project addProject(ProjectAddRequest projectAddRequest) throws Exception{
		// TODO Auto-generated method stub
		Project project = new Project();
		project.setProjectName(projectAddRequest.getProjectName());
		project.setCrNo(projectAddRequest.getCrNo());
		project.setStartDate(projectAddRequest.getStartDate());
		project.setEndDate(projectAddRequest.getEndDate());
		project.setAddedBy(projectAddRequest.getCreatedUser());
		project.setAddedOn(new Date());
		project.setProjectStatus(projectAddRequest.getStatus());
		projectRepo.save(project);
		
		return project;
	}
	

	public void updateProfileStatus(String projectName,String crNo,String projectStatus,Date actualStartDate, Date actualEndDate, String updatedReason,String projectProgress ,String updatedUser) throws Exception{
	
		Project project = projectRepo.findByProjectNameAndCrNo(projectName, crNo);
		
		
		if(project!=null){
			if(actualEndDate!=null)
			{
			project.setActualEndDate(actualEndDate);
			}
			
			if(actualStartDate!=null)
			{
			project.setActualStartDate(actualStartDate);
			}
			project.setProjectStatus(projectStatus);
			project.setReason(updatedReason);
			project.setProjectProgress(projectProgress);
			project.setUpdatedBy(updatedUser);
			project.setUpdatedOn(new Date());
			projectRepo.save(project);
		}else{
			System.out.println("No Project fuond");
		}
		
	}
	
	public List<Project> getAllProject() throws Exception{
		return (List<Project>) projectRepo.findAll();
	}
	
	
	
	public List<Project> getProjectsByName(String projectName) throws Exception{
		return (List<Project>) projectRepo.getProjectName(projectName);
	}
	
	public List<Project> getProjectsByCrNo(String projectName,String crNo) throws Exception{
		return (List<Project>) projectRepo.getcrNoFromProjectName(projectName, crNo);
	}
	
}
