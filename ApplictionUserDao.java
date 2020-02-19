package lk.dialog.ist.reslo.dao;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lk.dialog.ist.reslo.request.UserCreateRequest;
import lk.dialog.ist.reslo.services.entities.ApplicationUser;
import lk.dialog.ist.reslo.services.repository.ApplicationUserRepo;
import lk.dialog.ist.reslo.services.util.UserStatus;

@Component
public class ApplictionUserDao{
	
	@Autowired
	private ApplicationUserRepo applicationUserRepo;
	
	public ApplicationUser createApplicationUser(UserCreateRequest applicationUserCreateRequest) throws Exception{
		// TODO Auto-generated method stub
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUserName(applicationUserCreateRequest.getUserName());
		applicationUser.setUserStatus(UserStatus.ACTIVE);
		applicationUser.setAddedBy(applicationUserCreateRequest.getCreatedUser());
		applicationUser.setAddedOn(new Date());
		applicationUserRepo.save(applicationUser);
		
		return applicationUser;
	}
	
	
	public Optional<ApplicationUser> getProfileObj(long profileId) throws Exception{
		return applicationUserRepo.findById(profileId);
	}
	
	public void updateProfileStatus(int status, String userName, String updatedUser) throws Exception{
		applicationUserRepo.updateProfileStatus(status, userName,updatedUser);
	}
	
	public ApplicationUser getProfileById(String userName) throws Exception{
		ApplicationUser  applicationUser = null;
		applicationUser = applicationUserRepo.findProfileByID(userName);
		return applicationUser;
	}
}
