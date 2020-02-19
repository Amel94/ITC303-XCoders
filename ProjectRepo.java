package lk.dialog.ist.reslo.services.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import lk.dialog.ist.reslo.services.entities.Project;


/**
 * @author amel_33251
 *
 */
@Repository
public interface ProjectRepo extends CrudRepository<Project, String> {
	
	
	@Query("from Project where project_name LIKE %:projectName% GROUP BY project_name")
	List<Project> getProjectName(@Param("projectName") String projectName);
	
	Project findByProjectNameAndCrNo(String projectName,String crNo);
	
	@Query("from Project where projectName=:projectName and cr_no LIKE %:crNo%")
	List<Project> getcrNoFromProjectName(@Param("projectName") String projectName,@Param("crNo") String crNo);
	
	@Query("from Project where " +
					"start_date BETWEEN :startDate AND :endDate OR " +
					"end_date BETWEEN :startDate AND :endDate OR " +
					"actual_end_date BETWEEN :startDate AND :endDate OR " +
					"(start_date <= :startDate AND (end_date > :endDate OR actual_end_date > :endDate)) OR " +
					"(start_date >= :startDate AND (end_date < :endDate OR actual_end_date < :endDate))")
	List<Project> getProjectsBetweenStartEndActualDates(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	
	@Query("from Project where " +
			"start_date >=:startDate OR " +
			"end_date >=:startDate OR " +
			"actual_start_date >=:startDate OR " +
			"actual_end_date >=:startDate")
   List<Project> getAllProjectsfromStartDate(@Param("startDate") Date startDate);
	
	/*@Query("from Project where project_name LIKE %:projectName% and cr_no LIKE %:crNo%")
	List<Project> getProjectNameAndCrNo(@Param("projectName") String projectName,@Param("crNo") String crNo);*/
	

	
	
	
}
