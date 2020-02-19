/**
 * 
 */
package lk.dialog.ist.reslo.dto;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import lk.dialog.ist.reslo.dao.ApplictionUserDao;
import lk.dialog.ist.reslo.request.UserCreateRequest;
import lk.dialog.ist.reslo.services.entities.ApplicationUser;



/**
 * @author amel_33251
 *
 */
public class UserDto {
	@Autowired
	private ApplictionUserDao applictionUserDao;
	
	public void createApplicationUser(UserCreateRequest userCreateRequest) throws Exception{
		applictionUserDao.createApplicationUser(userCreateRequest);
		
	}

}
