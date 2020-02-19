package lk.dialog.ist.reslo.services.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import lk.dialog.ist.reslo.services.entities.ApplicationUser;
import lk.dialog.ist.reslo.services.entities.Employee;


/**
 * @author amel_33251
 *
 */
@Repository
public interface EmployeeRepo extends CrudRepository<Employee, String> {
	
	@Modifying
	@Query("update Employee set userStatus=0,updatedBy=:updatedBy,updatedOn=sysdate where userName=:userName")
	void updateEmployeeStatus(@Param("userName") String userName,@Param("updatedBy") String updatedUser);
	
	@Query("from Employee where emp_name LIKE %:name%")
	List<Employee> getEmployeesByname(@Param("name") String name);
	
	@Query("from Employee where total_Allocation = 0")
	List<Employee> getIdleEmployees();
	
	@Query("from Employee where total_Allocation = 0 and user_role=:userRole")
	List<Employee> getIdleEmployeeswithUserRole(@Param("userRole") String userRole);
	
	Employee findByEmpId(String userId);
}
