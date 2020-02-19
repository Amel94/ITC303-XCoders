/**
 * 
 */
package lk.dialog.ist.reslo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lk.dialog.ist.reslo.dto.AllocationDto;
import lk.dialog.ist.reslo.request.AllocationRequest;
import lk.dialog.ist.reslo.response.AllocationResponse;
import lk.dialog.ist.reslo.response.DeptCountResponse;
import lk.dialog.ist.reslo.services.entities.Allocation;



/**
 * @author amel_33251
 *
 */
@Controller
public class AllocationController {
	
	@Autowired
	private AllocationDto allocationDto;
	
    Authentication auth;    
    
	@RequestMapping(value = "/Allocation", method = RequestMethod.POST)
	public @ResponseBody  int addAllocation(@RequestBody AllocationRequest allocationAddRequest)		
	{
		 auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {	
			allocationAddRequest.setCreatedUser(auth.getName());
			allocationDto.addAllocation(allocationAddRequest);
		    } 
		    catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }
			}
		return 1;
	}
	
	@RequestMapping(value = "/Booking", method = RequestMethod.POST)
	public @ResponseBody  int addBooking(@RequestBody AllocationRequest allocationAddRequest)		
	{
		 auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {	
			allocationAddRequest.setCreatedUser(auth.getName());
			
			allocationDto.addBooking(allocationAddRequest);
		    } 
		    catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }
			}
		return 1;
	}
	
	
	@RequestMapping(value = "/updateAllocation", method = RequestMethod.POST)
	public @ResponseBody  int updateAllocation(
			@RequestBody AllocationRequest allocationAddRequest)
			
	{
		 /*auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{*/		
		try {	
			//allocationAddRequest.setCreatedUser(auth.getName());
			allocationAddRequest.setCreatedUser("admin");
			allocationDto.updateAllocation(allocationAddRequest);
		    } 
		    catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }
	//}
		
		return 0;
	}
	
	
	@RequestMapping(value = "/updateBooking", method = RequestMethod.POST)
	public @ResponseBody  int updateBooking(
			@RequestBody AllocationRequest allocationAddRequest)
			
	{
		 /*auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{	*/	
		try {	
			//allocationAddRequest.setCreatedUser(auth.getName());
			allocationAddRequest.setCreatedUser("admin");
			allocationDto.updateBooking(allocationAddRequest);
		    } 
		    catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }
//	}
		
		return 0;
	}
	
	@RequestMapping(value = "/deleteAllocation", method = RequestMethod.POST)
	public @ResponseBody  int removeAllocation(
			@RequestBody AllocationRequest allocationAddRequest)
			
	{
		/*auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{*/
			 
		try {	
			//allocationAddRequest.setCreatedUser(auth.getName());
			allocationAddRequest.setCreatedUser("admin");
			allocationDto.deleteAllocation(allocationAddRequest);
		    } 
		    catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }
			//}
		return 0;
	}
	
		
		@RequestMapping(value = "/getAllAllocations", method = RequestMethod.GET)
		public @ResponseBody  List<Allocation> getAllAllocations()
		{		
			/*auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
			 if(auth.isAuthenticated())
				{*/
			try {
				return allocationDto.getAllAllocation();
			    
			}catch (Exception e) 
			    {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
			    }
				//}
			 return null;
			
		}
		
		@RequestMapping(value = "/getAllocationByEmpId", method = RequestMethod.GET,produces = "application/json")
		public @ResponseBody  List<AllocationResponse> getAllocationByEmp(
				@RequestParam(value = "id") String id)
		{		
			/*auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
			 if(auth.isAuthenticated())
				{*/
			try {
				List<AllocationResponse> list = allocationDto.getAllocationByEmp(id);
				return list;
			    
			}catch (Exception e) 
			    {
					// TODO Auto-generated catch block
					e.printStackTrace();
			    }	
			  //  }
			return null;
		}
		
		@RequestMapping(value = "/getAllocationByProject", method = RequestMethod.GET,produces = "application/json")
		public @ResponseBody  List<AllocationResponse> getAllocationByProject(
				@RequestParam(value = "projectName") String projectName,
				@RequestParam(value = "userRole") String userRole)
		{		
			/*auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
			 if(auth.isAuthenticated())
				{*/
			try {
				List<AllocationResponse> list = allocationDto.getAllocationByProject(projectName,userRole);
				return list;
			    
			}catch (Exception e) 
			    {
					// TODO Auto-generated catch block
					e.printStackTrace();
			    }	
			   // }
			return null;
		}
		
		@RequestMapping(value = "/getAllocationBySelectedProject", method = RequestMethod.GET,produces = "application/json")
		public @ResponseBody  List<AllocationResponse> getAllocationBySelectedProject(
				@RequestParam(value = "projectName") String projectName)
		{		
			/*auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
			 if(auth.isAuthenticated())
				{*/
			try {
				List<AllocationResponse> list = allocationDto.getAllocationBySelectedProject(projectName);
				return list;
			    
			}catch (Exception e) 
			    {
					// TODO Auto-generated catch block
					e.printStackTrace();
			    }	
			  //  }
			return null;
		}
		
		@RequestMapping(value = "/getAllProjectsByName", method = RequestMethod.GET)
		public @ResponseBody  List<AllocationResponse> getAllProjectsByName()
		{	
			/*auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();;
			 if(auth.isAuthenticated())
				{*/
			try {
				return allocationDto.getAllProjectsByName();
			    
			}catch (Exception e) 
			    {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
			    }
			   // }
			return null;
		}
		
		@RequestMapping(value = "/getAllFutureProjectsByName", method = RequestMethod.GET)
		public @ResponseBody  List<AllocationResponse> getAllFutureProjectsByName()
		{	
			/*auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
			 if(auth.isAuthenticated())
				{*/
			try {
				return allocationDto.getAllFutureProjectsByName();
			    
			}catch (Exception e) 
			    {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
			    }
			    //}
			return null;
		}
		
		@RequestMapping(value = "/getDeptCountByProject", method = RequestMethod.GET)
		public @ResponseBody  List<DeptCountResponse> getDeptCountByProject(
				@RequestParam(value = "projectName") String projectName,
				@RequestParam(value = "crNo") String crNo)
		{	
			/*auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
			 if(auth.isAuthenticated())
			{*/
			try {
				return allocationDto.getDeptCountByProject(projectName,crNo);
			    
			}catch (Exception e) 
			    {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
			    }
			// }
			return null;
		}
		
		@RequestMapping(value = "/getProjectWiseData", method = RequestMethod.GET)
		public @ResponseBody  List<AllocationResponse> getProjectWiseData(
				@RequestParam(value = "projectName") String projectName,
				@RequestParam(value = "crNo") String crNo,
				@RequestParam(value = "userRole") String userRole)
		{	
			/*auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
			 if(auth.isAuthenticated())
			{*/
			try {
				return allocationDto.getProjectWiseData(projectName,crNo,userRole);
			    
			}catch (Exception e) 
			    {
					// TODO Auto-generated catch block
					e.printStackTrace();	
			    }
			// }
			return null;
		}
		
}







