package lk.dialog.ist.reslo.services.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import lk.dialog.ist.reslo.services.entities.Allocation;



@Repository
public interface AllocationRepo extends CrudRepository<Allocation, Long> {
	
	
	
	@Query("select distinct a.employee,a.project,a.allocationStatus,a.allocation,min(a.allocationDate),max(a.allocationDate) from Allocation a JOIN a.employee e ON e.empId = :id  GROUP BY a.project")
	List<Allocation> getAllocationByEmp(@Param("id") String id);
	
	@Query("select distinct a.employee,a.project,a.allocation,min(a.allocationDate),max(a.allocationDate) from Allocation a JOIN a.employee e ON e.empId = :id  WHERE a.allocationDate>=:startDate AND a.allocationStatus = 0 GROUP BY a.project")
	List<Allocation> getBookingByEmp(@Param("id") String id, @Param("startDate") Date startDate);
	
	@Query("select distinct a.employee,a.project,a.allocation,min(a.allocationDate),max(a.allocationDate) from Allocation a JOIN a.employee e JOIN a.project p WHERE p.projectName=:projectName AND p.crNo=:crNo AND e.userRole=:userRole AND a.allocationStatus = 0 GROUP BY a.employee")
	List<Allocation> getBookingProjects(@Param("projectName") String projectName, @Param("crNo") String crNo,@Param("userRole") String userRole);
	
	@Query("select distinct a.employee,a.project,a.allocation,min(a.allocationDate),max(a.allocationDate) from Allocation a JOIN a.employee e JOIN a.project p WHERE p.projectName=:projectName AND p.crNo=:crNo AND a.allocationStatus = 0 GROUP BY a.employee")
	List<Allocation> getAllBookingProjects(@Param("projectName") String projectName, @Param("crNo") String crNo);
	
	@Query("from Allocation where project_name=:projectName and cr_no=:crNo and emp_id=:empId")
	List<Allocation> findByProjectNameAndCrNoAndEmpId(@Param("projectName") String projectName,@Param("crNo") String crNo,@Param("empId") String empId);
	
	@Query("select distinct a.allocation,a.employee,a.project from Allocation a join a.employee e join a.project p WHERE p.projectName=:projectName AND e.userRole=:userRole")
	List<Allocation> getAllocationByProject(@Param("projectName") String projectName, @Param("userRole") String userRole);
	
	@Query("select distinct a.employee,a.project,a.allocation from Allocation a join a.employee join a.project p WHERE p.projectName=:projectName")
	List<Allocation> getAllocationBySelectedProject(@Param("projectName") String projectName);

	@Query("select distinct a.allocation,a.employee,a.project from Allocation a join a.employee e join a.project p WHERE a.allocationDate>=:startDate AND e.userRole=:userRole")
	List<Allocation> getAllEmployeeDataByUserRole(@Param("startDate") Date startDate, @Param("userRole") String userRole);
	
	@Query("select distinct a.allocation,a.employee,a.project from Allocation a join a.employee e join a.project p WHERE a.allocationDate>=:startDate")
	List<Allocation> getAllEmployeeData(@Param("startDate") Date startDate);
	
	@Query("select distinct a.project from Allocation a join a.project p")
	List<Allocation> getAllProjectsByName();
	
	@Query("select distinct a.project from Allocation a join a.project p where a.allocationStatus = 0")
	List<Allocation> getAllFutureProjectsByName();
	
	@Query("select distinct a.employee,a.project from Allocation a join a.employee e join a.project p WHERE p.projectName=:projectName AND p.crNo=:crNo")
	List<Allocation> getDeptCountByProject(@Param("projectName") String projectName, @Param("crNo") String crNo);
	
	@Query("select distinct a.employee,a.allocation from Allocation a join a.employee e join a.project p WHERE p.projectName=:projectName AND p.crNo=:crNo AND e.userRole=:userRole")
	List<Allocation> getProjectWiseData(@Param("projectName") String projectName, @Param("crNo") String crNo,@Param("userRole") String userRole);
	
	@Query("select distinct a.employee,a.allocation from Allocation a join a.employee e join a.project p WHERE p.projectName=:projectName AND p.crNo=:crNo")
	List<Allocation> getAllProjectWiseData(@Param("projectName") String projectName, @Param("crNo") String crNo);
	
	@Query("select distinct a.project, a.employee, a.allocation, min(a.allocationDate),max(a.allocationDate) from Allocation a where allocation_date >= :startDate and allocation_date <= :endDate GROUP BY a.employee,a.project")
	List<Allocation> getEmployeeAllocationbetweenStartEndDates(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query("select distinct a.project, a.employee, a.allocation from Allocation a  join a.employee e where allocation_date >= :startDate and allocation_status = 0 and e.empName =:empName  GROUP BY a.employee,a.project")
	List<Allocation> getBookingEmployeeSummary(@Param("startDate") Date startDate, @Param("empName") String empName);
	
	@Query("select distinct a.allocation, a.employee, a.project from Allocation a  join a.employee e join a.project p where allocation_date >= :startDate and allocation_date <= :endDate and allocation_status = 1 and e.empName =:empName  GROUP BY a.project")
	List<Allocation> getEmployeeWiseSummary(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("empName") String empName);
	
	@Query("SELECT e.empId, e.empName,e.userRole, sum(a.allocation)/count(DISTINCT a.allocationDate) as allocation_sum from Allocation a join a.employee e where a.allocationDate between :startDate AND :endDate group by a.employee.empId")
	List<Allocation> getworkloadSummaryData(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	
	@Query("SELECT e.empId, e.empName,e.userRole, sum(a.allocation)/count(DISTINCT a.allocationDate) as allocation_sum from Allocation a join a.employee e where e.userRole=:userRole AND a.allocationDate between :startDate AND :endDate group by a.employee.empId")
	List<Allocation> getworkloadSummaryDatawithUserRole(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("userRole") String userRole);
	
	@Query("select distinct a.allocation, a.employee, a.project from Allocation a join a.project p join a.employee e where  p.projectStatus IN ('OnHold', 'Close', 'New') AND e.userRole=:userRole AND allocation_date between :startDate AND :endDate GROUP BY a.employee, a.project")
	List<Allocation> getIdleEmpDatawithUserRole(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("userRole") String userRole);
	
	@Query("select distinct a.allocation, a.employee, a.project from Allocation a join a.project p join a.employee e where  p.projectStatus IN ('OnHold', 'InProgress', 'New') AND e.userRole=:userRole AND allocation_date between :startDate AND :endDate GROUP BY a.employee, a.project")
	List<Allocation> getAllocatedEmpDatawithUserRole(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("userRole") String userRole);
	
	@Query("select distinct a.allocation, a.employee, a.project from Allocation a join a.project p join a.employee e where  p.projectStatus IN ('OnHold', 'InProgress', 'New') AND allocation_date between :startDate AND :endDate GROUP BY a.employee, a.project")
	List<Allocation> getAllocatedEmpData(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	
	@Query("select a.employee,a.allocationDate from Allocation a join a.employee e where e.userRole=:userRole AND allocation_date between :startDate AND :endDate")
	List<Allocation> getIdleEmpwithEmpRole(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("userRole") String userRole);
	
	@Query("select a.employee,a.allocationDate from Allocation a join a.employee e where allocation_date between :startDate AND :endDate")
	List<Allocation> getIdleEmpData(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	
	@Query("select distinct a.employee,a.allocation from Allocation a JOIN a.employee e WHERE a.allocationStatus = 0 AND allocation_date between :startDate AND :endDate GROUP BY a.employee")
	List<Allocation> getBookingEmployeesforHomePage(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	
	
	
	
}
