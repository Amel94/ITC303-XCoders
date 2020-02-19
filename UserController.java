/**
 * 
 */
package lk.dialog.ist.reslo.controller;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lk.dialog.ist.reslo.dto.UserDto;
import lk.dialog.ist.reslo.request.UserCreateRequest;
import lk.dialog.ist.reslo.service.UserServices;



/**
 * @author amel_33251
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private UserDto userDto;
	
	@Autowired
	private UserServices userServices;
	
	Authentication auth;    

	@RequestMapping(value = "/userCreation", method = RequestMethod.POST)
	public @ResponseBody  int createUser(
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "userName", required = false) String userName)
	{
		 UserCreateRequest userCreateRequest = new UserCreateRequest();
		 auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {
			    userCreateRequest.setCreatedUser(auth.getName());
				userCreateRequest.setContactNo(mobile);
				userCreateRequest.setEmail(email);
				userCreateRequest.setEmployeeName(userName);
				userCreateRequest.setUserName(userId);
				userDto.createApplicationUser(userCreateRequest);
		    } 
		    catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }
			}
		return 1;
	}	
	
	@RequestMapping(value = "/getUserByName", method = RequestMethod.GET)
	public @ResponseBody JSONArray getUserByName(
		   @RequestParam(value = "name") String userName) throws ParseException {
		 auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();

		JSONArray userByName = new JSONArray();
		if (auth.isAuthenticated()) {
			userByName = userServices.sendPost(userName);
		}
		return userByName;
	}
	
}







