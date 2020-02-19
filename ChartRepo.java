package lk.dialog.ist.reslo.services.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lk.dialog.ist.reslo.services.entities.Allocation;
import lk.dialog.ist.reslo.services.entities.Employee;
import lk.dialog.ist.reslo.services.entities.Project;

@Repository
public interface ChartRepo extends CrudRepository<Allocation, Long> {
	
	
	
	
	
	
	
/*  @Query("select distinct a.employee,a.project,a.allocation,min(a.allocationDate),max(a.allocationDate) from Allocation a JOIN a.employee e ON e.empId = :id  GROUP BY a.project")
	List<Allocation> getAllocationByEmp(@Param("id") String id);
	
	@Query("from Allocation where project_name=:projectName and cr_no=:crNo and emp_id=:empId")
	List<Allocation> findByProjectNameAndCrNoAndEmpId(@Param("projectName") String projectName,@Param("crNo") String crNo,@Param("empId") String empId);
	
//	select distinct a.employee,a.project,a.allocation from Allocation a JOIN a.employee e ON e.empId = :id
	@Query("from Allocation where project_name LIKE %:projectName%")
	List<Allocation> getProjectName(@Param("projectName") String projectName);*/
	
	/*Allocation findByProject();*/
	
	/*@Query("from Allocation where projectName=:projectName and cr_no LIKE %:crNo%")
	List<Allocation> getcrNoFromProjectName(@Param("projectName") String projectName,@Param("crNo") String crNo);*/
	
	/*@Query("from Project where project_name LIKE %:projectName% and cr_no LIKE %:crNo%")
	List<Project> getProjectNameAndCrNo(@Param("projectName") String projectName,@Param("crNo") String crNo);*/
	
		


	
}
