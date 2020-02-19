package lk.dialog.ist.reslo.dao;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lk.dialog.ist.reslo.request.AllocationRequest;
import lk.dialog.ist.reslo.request.EmployeeCreateRequest;
import lk.dialog.ist.reslo.request.ProjectAddRequest;
import lk.dialog.ist.reslo.request.UserCreateRequest;
import lk.dialog.ist.reslo.response.AllocationResponse;
import lk.dialog.ist.reslo.response.AllocationWrapper;
import lk.dialog.ist.reslo.response.DeptCountResponse;
import lk.dialog.ist.reslo.services.entities.Allocation;
import lk.dialog.ist.reslo.services.entities.ApplicationUser;
import lk.dialog.ist.reslo.services.entities.Employee;
import lk.dialog.ist.reslo.services.entities.Project;
import lk.dialog.ist.reslo.services.repository.AllocationRepo;
import lk.dialog.ist.reslo.services.repository.ApplicationUserRepo;
import lk.dialog.ist.reslo.services.repository.EmployeeRepo;
import lk.dialog.ist.reslo.services.repository.ProjectRepo;
import lk.dialog.ist.reslo.services.util.AllocationStatus;
import lk.dialog.ist.reslo.services.util.EmployeeStatus;
import lk.dialog.ist.reslo.services.util.ProjectStatus;
import lk.dialog.ist.reslo.services.util.UserStatus;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Component
public class AllocationDao {

	@Autowired
	private AllocationRepo allocationRepo;

	@Autowired
	private ProjectRepo projectRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	public List<Allocation> addAllocation(AllocationRequest allocationAddRequest) throws Exception {
		// TODO Auto-generated method stub
		List<Allocation> allocationList = new ArrayList<>();

		Project project = projectRepo.findByProjectNameAndCrNo(allocationAddRequest.getProjectName(),allocationAddRequest.getCrNo());
		Employee employee = employeeRepo.findByEmpId(allocationAddRequest.getEmpId());

		for (Date allocationDate : allocationAddRequest.getAllocationDate()) {
			Allocation allocation = new Allocation();
			allocation.setProject(project);
			allocation.setEmployee(employee);
			allocation.setAllocation(allocationAddRequest.getAllocation());
			allocation.setAddedBy(allocationAddRequest.getCreatedUser());
			allocation.setAddedOn(new Date());
			allocation.setAllocationStatus(AllocationStatus.ALLOCATED);
			allocation.setAllocationDate(allocationDate);
			allocationList.add(allocation);
		}

		allocationRepo.saveAll(allocationList);
		return allocationList;
	}
	
	public List<Allocation> addBooking(AllocationRequest allocationAddRequest) throws Exception {
		// TODO Auto-generated method stub
		List<Allocation> allocationList = new ArrayList<>();

		Project project = projectRepo.findByProjectNameAndCrNo(allocationAddRequest.getProjectName(),allocationAddRequest.getCrNo());
		Employee employee = employeeRepo.findByEmpId(allocationAddRequest.getEmpId());

		for (Date allocationDate : allocationAddRequest.getAllocationDate()) {
			Allocation allocation = new Allocation();
			allocation.setProject(project);
			allocation.setEmployee(employee);
			allocation.setAllocation(allocationAddRequest.getAllocation());
			allocation.setAddedBy(allocationAddRequest.getCreatedUser());
			allocation.setAddedOn(new Date());
			allocation.setAllocationStatus(AllocationStatus.BOOKED);
			allocation.setAllocationDate(allocationDate);
			allocationList.add(allocation);
		}

		allocationRepo.saveAll(allocationList);
		return allocationList;
	}

	public List<Allocation> updateAllocation(AllocationRequest allocationRemoveRequest) throws Exception {
		// TODO Auto-generated method stub
		List<Allocation> allocationList = new ArrayList<>();
		String addedBy = null;
		Date addedOn = null;

		Project project = projectRepo.findByProjectNameAndCrNo(allocationRemoveRequest.getProjectName(),
				allocationRemoveRequest.getCrNo());
		Employee employee = employeeRepo.findByEmpId(allocationRemoveRequest.getEmpId());

		List<Allocation> allList = allocationRepo.findByProjectNameAndCrNoAndEmpId(allocationRemoveRequest.getProjectName(), allocationRemoveRequest.getCrNo(), allocationRemoveRequest.getEmpId());
		
		if(!allList.isEmpty()){
			addedBy = allList.get(0).getAddedBy();
			addedOn = allList.get(0).getAddedOn();
			
			
			allocationRepo.deleteAll(allList);
			
			if(project!=null && employee!=null)
			{
				for (Date allocationDate : allocationRemoveRequest.getAllocationDate()) {
					Allocation allocation = new Allocation();
					allocation.setProject(project);
					allocation.setEmployee(employee);
					allocation.setAllocation(allocationRemoveRequest.getAllocation());
					allocation.setAddedBy(addedBy);
					allocation.setAddedOn(addedOn);
					allocation.setUpdatedBy(allocationRemoveRequest.getCreatedUser());
					allocation.setUpdatedOn(new Date());
					allocation.setAllocationStatus(AllocationStatus.ALLOCATED);
					allocation.setAllocationDate(allocationDate);
					allocationList.add(allocation);
				}

				allocationRepo.saveAll(allocationList);
			}
			else{
				System.out.println("No Allocation found");
			}
		}
		
		
		
		return allocationList;
	}
	
	public List<Allocation> updateBooking(AllocationRequest allocationRemoveRequest) throws Exception {
		// TODO Auto-generated method stub
		List<Allocation> allocationList = new ArrayList<>();
		String addedBy = null;
		Date addedOn = null;

		Project project = projectRepo.findByProjectNameAndCrNo(allocationRemoveRequest.getProjectName(),
				allocationRemoveRequest.getCrNo());
		Employee employee = employeeRepo.findByEmpId(allocationRemoveRequest.getEmpId());

		List<Allocation> allList = allocationRepo.findByProjectNameAndCrNoAndEmpId(allocationRemoveRequest.getProjectName(), allocationRemoveRequest.getCrNo(), allocationRemoveRequest.getEmpId());
		
		if(!allList.isEmpty()){
			addedBy = allList.get(0).getAddedBy();
			addedOn = allList.get(0).getAddedOn();
			
			
			allocationRepo.deleteAll(allList);
			
			if(project!=null && employee!=null)
			{
				for (Date allocationDate : allocationRemoveRequest.getAllocationDate()) {
					Allocation allocation = new Allocation();
					allocation.setProject(project);
					allocation.setEmployee(employee);
					allocation.setAllocation(allocationRemoveRequest.getAllocation());
					allocation.setAddedBy(addedBy);
					allocation.setAddedOn(addedOn);
					allocation.setUpdatedBy(allocationRemoveRequest.getCreatedUser());
					allocation.setUpdatedOn(new Date());
					allocation.setAllocationStatus(AllocationStatus.BOOKED);
					allocation.setAllocationDate(allocationDate);
					allocationList.add(allocation);
				}

				allocationRepo.saveAll(allocationList);
			}
			else{
				System.out.println("No Booking found");
			}
		}
		
		
		
		return allocationList;
	}
	
	
	public List<Allocation> deleteAllocation(AllocationRequest allocationRemoveRequest) throws Exception {
		// TODO Auto-generated method stub
		List<Allocation> allocationList = new ArrayList<>();
		
		String addedBy = null;
		Date addedOn = null;

		Project project = projectRepo.findByProjectNameAndCrNo(allocationRemoveRequest.getProjectName(),
				allocationRemoveRequest.getCrNo());
		Employee employee = employeeRepo.findByEmpId(allocationRemoveRequest.getEmpId());

		List<Allocation> allList = allocationRepo.findByProjectNameAndCrNoAndEmpId(allocationRemoveRequest.getProjectName(), allocationRemoveRequest.getCrNo(), allocationRemoveRequest.getEmpId());
		
		
		if(!allList.isEmpty()){
			
			addedBy = allList.get(0).getAddedBy();
			addedOn = allList.get(0).getAddedOn();
			allocationRepo.deleteAll(allList);
						
			if(project!=null && employee!=null)
			{
				for (Date allocationDate : allocationRemoveRequest.getAllocationDate()) {
					Allocation allocation = new Allocation();
					allocation.setProject(project);
					allocation.setEmployee(employee);
					allocation.setAllocation(allocationRemoveRequest.getAllocation());
					allocation.setAddedBy(addedBy);
					allocation.setAddedOn(addedOn);
					allocation.setUpdatedBy(allocationRemoveRequest.getCreatedUser());
					allocation.setUpdatedOn(new Date());
					allocation.setAllocationStatus(AllocationStatus.DELETED);
					allocation.setAllocationDate(allocationDate);
					allocationList.add(allocation);
				}

				allocationRepo.saveAll(allocationList);
			}
			else{
				System.out.println("No Allocation found");
			}
		}
		
		
		
		return allocationList;
	}

	public List<Allocation> getAllAllocation() throws Exception {
		return (List<Allocation>) allocationRepo.findAll();
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
	    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
	    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	  }


	public List<AllocationResponse> getAllocationByEmp(String id) throws Exception {
		List<AllocationResponse> allocations = new ArrayList<>();
		List<Allocation> allocationList = allocationRepo.getAllocationByEmp(id);
		

		Iterator itr = allocationList.iterator();
		while (itr.hasNext()) {
			AllocationResponse dao = new AllocationResponse();

			Object[] obj = (Object[]) itr.next();
			
			
			Employee emp = (Employee) obj[0];
			Project projct = (Project) obj[1];
			AllocationStatus allocationStatus = (AllocationStatus) obj[2];
			Integer allocation = Integer.parseInt(String.valueOf(obj[3]));
		    Date allocationStartDate = (Date) obj[4];
			Date allocationEndDate = (Date)obj[5];
					
			
			emp.setAllocation(null);
			projct.setAllocation(null);
			dao.setEmployee(emp);
			dao.setProject(projct);
			dao.setAllocationStatus(allocationStatus);
			dao.setAllocation(allocation);
			dao.setAllocationStartDate(allocationStartDate);
			dao.setAllocationEndDate(allocationEndDate);
			
			allocations.add(dao);
		}

		return allocations;
	}
	
	
	public List<AllocationResponse> getAllocationByProject(String projectName,String userRole) throws Exception {
		List<AllocationResponse> allocations = new ArrayList<>();
		List<Allocation> allocationList = allocationRepo.getAllocationByProject(projectName,userRole);
		

		Iterator itr = allocationList.iterator();
	    while (itr.hasNext()) {
			AllocationResponse dao = new AllocationResponse();

			Object[] obj = (Object[]) itr.next();
			
			int allocation = (int) obj[0];
			Employee emp = (Employee) obj[1];
			Project projct = (Project) obj[2];
			
			dao.setEmployee(emp);
			dao.setProject(projct);
			dao.setAllocation(allocation);
			
			
			allocations.add(dao);
		}

		return allocations;
	}
	
	public List<AllocationResponse> getAllocationBySelectedProject(String projectName) throws Exception {
		List<AllocationResponse> allocations = new ArrayList<>();
		
		List<Allocation> allocationList = allocationRepo.getAllocationBySelectedProject(projectName);
		
      
		Iterator itr = allocationList.iterator();
	    while (itr.hasNext()) {
			AllocationResponse dao = new AllocationResponse();

			Object[] obj = (Object[]) itr.next();
			
			
			Employee emp = (Employee) obj[0];
			Project projct = (Project) obj[1];
			int allocation = (int) obj[2];
			
			dao.setEmployee(emp);
			dao.setProject(projct);
			dao.setAllocation(allocation);
			
			
			allocations.add(dao);
		}

		return allocations;
	}
	
	public List<AllocationResponse> getAllProjectsByName() throws Exception {
		List<AllocationResponse> allocations = new ArrayList<>();
		
		List<Allocation> allocationList = allocationRepo.getAllProjectsByName();

		Iterator itr = allocationList.iterator();
	    while (itr.hasNext()) {
			AllocationResponse dao = new AllocationResponse();

			Project projct =  (Project) itr.next();
			
			dao.setProject(projct);
			
			allocations.add(dao);
		}
     
		

		return allocations;
	}
	
	public List<AllocationResponse> getAllFutureProjectsByName() throws Exception {
		List<AllocationResponse> allocations = new ArrayList<>();
		
		List<Allocation> allocationList = allocationRepo.getAllFutureProjectsByName();

		Iterator itr = allocationList.iterator();
	    while (itr.hasNext()) {
			AllocationResponse dao = new AllocationResponse();

			Project projct =  (Project) itr.next();
			
			dao.setProject(projct);
			
			allocations.add(dao);
		}
     
		

		return allocations;
	}
	
	public List<DeptCountResponse> getDeptCountByProject(String projectName,String crNo) throws Exception {
		
		List<DeptCountResponse> allocations = new ArrayList<>();
		List<Allocation> allocationList = allocationRepo.getDeptCountByProject(projectName,crNo);
		DeptCountResponse deptCountResponse = new DeptCountResponse();
		int devCount = 0,qaCount= 0,baCount= 0,managementCount = 0 ;
		
		Iterator itr = allocationList.iterator();
	    while (itr.hasNext()) {
			
			
			
			Object[] obj = (Object[]) itr.next();
			Employee emp = (Employee) obj[0];
			Project projct = (Project) obj[1];
			
			String userRole = emp.getUserRole();
			
			if(userRole.equals("Dev"))
			{
				devCount= devCount +1;
			}
			if(userRole.equals("QA"))
			{
				qaCount= qaCount +1;
			}
			if(userRole.equals("BA"))
			{
				baCount= baCount +1;
			}
			
			if(userRole.equals("Management"))
			{
				managementCount= managementCount +1;
			}
			
			
		}
	    
	    deptCountResponse.setBaCount(baCount);
		deptCountResponse.setDevCount(devCount);
		deptCountResponse.setQaCount(qaCount);
		deptCountResponse.setManagementCount(managementCount);
		
		allocations.add(deptCountResponse);
		

		return allocations;
	}

	public List<AllocationResponse> getProjectWiseData(String projectName,String crNo,String userRole) throws Exception {
		
		List<AllocationResponse> allocations = new ArrayList<>();
		List<Allocation> allocationList = new ArrayList<>();
		if(userRole.equals("All Employee"))
		{
		  allocationList = allocationRepo.getAllProjectWiseData(projectName,crNo);	
		}
		else
		{
		  allocationList = allocationRepo.getProjectWiseData(projectName,crNo,userRole);
		}
		
		
		Iterator itr = allocationList.iterator();
	    while (itr.hasNext()) {
	    	AllocationResponse dao = new AllocationResponse();
			
			
			Object[] obj = (Object[]) itr.next();
			Employee emp = (Employee) obj[0];
			int alloc = (int) obj[1];
			
			dao.setEmployee(emp);
			dao.setAllocation(alloc);
			
			
			allocations.add(dao);
			
		}
	    
	   
		

		return allocations;
	}
}
