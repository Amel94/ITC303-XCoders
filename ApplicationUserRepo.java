package lk.dialog.ist.reslo.services.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import lk.dialog.ist.reslo.services.entities.ApplicationUser;

@Repository
public interface ApplicationUserRepo extends CrudRepository<ApplicationUser, Long> {
	
	@Modifying
	@Query("update ApplicationUser set userStatus=:userStatus,updatedBy=:updatedBy,updatedOn=sysdate where userName=:userName")
	void updateProfileStatus(@Param("userStatus") int userStatus,@Param("userName") String userName,@Param("updatedBy") String updatedUser);
	
	@Query("from ApplicationUser p where p.userStatus=1 and p.userName=:userName")
	ApplicationUser findProfileByID(@Param("userName") String userName);
	
	Optional<ApplicationUser> findById(Long id);
	
	
}
