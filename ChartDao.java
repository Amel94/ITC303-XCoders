package lk.dialog.ist.reslo.dao;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lk.dialog.ist.reslo.response.AllocationChartResponse;
import lk.dialog.ist.reslo.response.AllocationResponse;
import lk.dialog.ist.reslo.response.ChartListResponse;
import lk.dialog.ist.reslo.response.ChartResponse;
import lk.dialog.ist.reslo.response.DeptCountResponse;
import lk.dialog.ist.reslo.response.IdelResponse;
import lk.dialog.ist.reslo.response.ProjectWiseResponse;
import lk.dialog.ist.reslo.services.entities.Allocation;
import lk.dialog.ist.reslo.services.entities.Employee;
import lk.dialog.ist.reslo.services.entities.Project;
import lk.dialog.ist.reslo.services.repository.AllocationRepo;
import lk.dialog.ist.reslo.services.repository.EmployeeRepo;
import lk.dialog.ist.reslo.services.repository.ProjectRepo;
import lk.dialog.ist.reslo.services.util.AllocationStatus;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;

@Component
public class ChartDao {

	@Autowired
	private AllocationRepo allocationRepo;

	@Autowired
	private ProjectRepo projectRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;

	public ChartResponse getGanttChartData(Date startDate, Date endDate) throws Exception {
		// TODO Auto-generated method stub

		List<ChartListResponse> chartResponseList = new ArrayList<>();
		ChartResponse chartResponse = new ChartResponse();
		String projectStatus = null;

		int inProgressCount = 0;
		int onHoldCount = 0;
		int closeCount = 0;
		int newProjectCount = 0;

		List<Project> projects = projectRepo.getProjectsBetweenStartEndActualDates(startDate, endDate);

		
		for (Project project : projects) {

			ChartListResponse chartListResponse = new ChartListResponse();

			Date projectStart = null;
			Date projectEnd = null;
			Date projectActualStart = null;
			Date projectActualEnd = null;

			projectStatus = project.getProjectStatus();

			if (projectStatus.equals("InProgress")) {
				inProgressCount = inProgressCount + 1;
			}
			if (projectStatus.equals("OnHold")) {
				onHoldCount = onHoldCount + 1;
			}
			if (projectStatus.equals("Close")) {
				closeCount = closeCount + 1;
			}
			
			if (projectStatus.equals("New")) {
				newProjectCount = newProjectCount + 1;
			}

			projectStart = project.getStartDate();
			projectEnd = project.getEndDate();
			projectActualStart = project.getActualStartDate();
			projectActualEnd = project.getActualEndDate();

			// chart colum values
			chartListResponse.setActualStartTrue(project.getStartDate());
			chartListResponse.setActualEndTrue(project.getEndDate());
			chartListResponse.setBaselineStartTrue(project.getActualStartDate());
			chartListResponse.setBaselineEndTrue(project.getActualEndDate());

			if (startDate.after(project.getStartDate())) {
				projectStart = startDate;
			}

			if (project.getEndDate().after(endDate)) {
				projectEnd = endDate;
			}

			if (projectActualStart != null && projectActualEnd != null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectActualStart);
				chartListResponse.setBaselineEnd(projectActualEnd);
			}
			if (projectActualStart == null && projectActualEnd != null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectStart);
				chartListResponse.setBaselineEnd(projectActualEnd);
			}

			if (projectActualStart != null && projectActualEnd == null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectActualStart);
				chartListResponse.setBaselineEnd(projectEnd);
			}

			if (projectActualStart == null && projectActualEnd == null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectStart);
				chartListResponse.setBaselineEnd(projectStart);
			}

			chartListResponse.setId(project.getProjectName());
			chartListResponse.setName(project.getProjectName());
			chartListResponse.setCrNo(project.getCrNo());
			chartListResponse.setProgressValue(project.getProjectProgress() + "%");
			chartListResponse.setRowHeight(30);
			chartListResponse.setStatus(project.getProjectStatus());

			chartResponseList.add(chartListResponse);
		}

		chartResponse.setChartList(chartResponseList);
		chartResponse.setInProgressCount(inProgressCount);
		chartResponse.setOnHoldCount(onHoldCount);
		chartResponse.setCloseCount(closeCount);
		chartResponse.setNewProjectCount(newProjectCount);

		return chartResponse;
	}
	
	public ChartResponse getAllProjectsGanttChartData(Date startDate) throws Exception {
		// TODO Auto-generated method stub

		List<ChartListResponse> chartResponseList = new ArrayList<>();
		ChartResponse chartResponse = new ChartResponse();
		String projectStatus = null;

		List<Project> projects = projectRepo.getAllProjectsfromStartDate(startDate);

		for (Project project : projects) {

			ChartListResponse chartListResponse = new ChartListResponse();

			Date projectStart = null;
			Date projectEnd = null;
			Date projectActualStart = null;
			Date projectActualEnd = null;

			projectStatus = project.getProjectStatus();
			projectStart = project.getStartDate();
			projectEnd = project.getEndDate();
			projectActualStart = project.getActualStartDate();
			projectActualEnd = project.getActualEndDate();

			// chart colum values
			chartListResponse.setActualStartTrue(project.getStartDate());
			chartListResponse.setActualEndTrue(project.getEndDate());
			chartListResponse.setBaselineStartTrue(project.getActualStartDate());
			chartListResponse.setBaselineEndTrue(project.getActualEndDate());

			if (startDate.after(project.getStartDate())) {
				projectStart = startDate;
			}

			
			if (projectActualStart != null && projectActualEnd != null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectActualStart);
				chartListResponse.setBaselineEnd(projectActualEnd);
			}
			if (projectActualStart == null && projectActualEnd != null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectStart);
				chartListResponse.setBaselineEnd(projectActualEnd);
			}

			if (projectActualStart != null && projectActualEnd == null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectActualStart);
				chartListResponse.setBaselineEnd(projectEnd);
			}

			if (projectActualStart == null && projectActualEnd == null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectStart);
				chartListResponse.setBaselineEnd(projectStart);
			}

			chartListResponse.setStatus(projectStatus);
			chartListResponse.setId(project.getProjectName());
			chartListResponse.setName(project.getProjectName());
			chartListResponse.setCrNo(project.getCrNo());
			
			chartListResponse.setProgressValue(project.getProjectProgress() + "%");
			chartListResponse.setRowHeight(30);

			chartResponseList.add(chartListResponse);
		}

		chartResponse.setChartList(chartResponseList);
		

		return chartResponse;
	}
	
	public List<ChartListResponse> downloadAllProjectsData(Date startDate) throws Exception {
		// TODO Auto-generated method stub

		List<ChartListResponse> chartResponseList = new ArrayList<>();
		
		String projectStatus = null;

		List<Project> projects = projectRepo.getAllProjectsfromStartDate(startDate);

		for (Project project : projects) {

			ChartListResponse chartListResponse = new ChartListResponse();

			Date projectStart = null;
			Date projectEnd = null;
			Date projectActualStart = null;
			Date projectActualEnd = null;

			projectStatus = project.getProjectStatus();
			projectStart = project.getStartDate();
			projectEnd = project.getEndDate();
			projectActualStart = project.getActualStartDate();
			projectActualEnd = project.getActualEndDate();

			// chart colum values
			chartListResponse.setActualStartTrue(project.getStartDate());
			chartListResponse.setActualEndTrue(project.getEndDate());
			chartListResponse.setBaselineStartTrue(project.getActualStartDate());
			chartListResponse.setBaselineEndTrue(project.getActualEndDate());

			if (startDate.after(project.getStartDate())) {
				projectStart = startDate;
			}

			
			if (projectActualStart != null && projectActualEnd != null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectActualStart);
				chartListResponse.setBaselineEnd(projectActualEnd);
			}
			if (projectActualStart == null && projectActualEnd != null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectStart);
				chartListResponse.setBaselineEnd(projectActualEnd);
			}

			if (projectActualStart != null && projectActualEnd == null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectActualStart);
				chartListResponse.setBaselineEnd(projectEnd);
			}

			if (projectActualStart == null && projectActualEnd == null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectStart);
				chartListResponse.setBaselineEnd(projectStart);
			}

			chartListResponse.setStatus(projectStatus);
			chartListResponse.setId(project.getProjectName());
			chartListResponse.setName(project.getProjectName());
			chartListResponse.setCrNo(project.getCrNo());
			chartListResponse.setProgressValue(project.getProjectProgress() + "%");
			chartListResponse.setRowHeight(30);

			chartResponseList.add(chartListResponse);
		}

		
		

		return chartResponseList;
	}
	
	
	public List<AllocationChartResponse> getEmployeeAllocationbetweenStartEndDates(Date startDate, Date endDate) throws Exception {
		// TODO Auto-generated method stub
		List<AllocationChartResponse> allocationChartResponseList = new ArrayList<>();
		
		List<Allocation> allocations = allocationRepo.getEmployeeAllocationbetweenStartEndDates(startDate, endDate);
		
		Iterator itr = allocations.iterator();
		
		while (itr.hasNext()) {
			AllocationChartResponse dao = new AllocationChartResponse();

			Object[] obj = (Object[]) itr.next();
			
			Project projct = (Project) obj[0];
			Employee emp = (Employee) obj[1];
			int allocation = Integer.parseInt(String.valueOf(obj[2]));
			Date allocationStartDate = (Date) obj[3];
			Date allocationEndDate = (Date)obj[4];
					
			
			emp.setAllocation(null);
			projct.setAllocation(null);
			dao.setAllocation(String.valueOf(allocation));
			dao.setProgressValue(emp.getEmpName());
			/*dao.setProgressValue(String.valueOf(obj[2]));*/
			dao.setName(projct.getProjectName());
			dao.setRowHeight(30);
			dao.setActualStart(allocationStartDate);
			dao.setActualEnd(allocationEndDate);
			
			allocationChartResponseList.add(dao);
		}

		return allocationChartResponseList;
	}
	
	
	public List<AllocationResponse> getAllEmployeeDataByUserRole(Date startDate, String userRole) throws Exception {
		// TODO Auto-generated method stub
		List<AllocationResponse> allocations = new ArrayList<>();
		List<Allocation> allocation = new ArrayList<>();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		/*
		 * List<ChartListResponse> chartResponseList = new ArrayList<>();
		 * ChartResponse chartResponse = new ChartResponse();
		 */

		if (userRole.equals("All Employee")) {
			allocation = allocationRepo.getAllEmployeeData(startDate);
		} else {
			allocation = allocationRepo.getAllEmployeeDataByUserRole(startDate, userRole);
		}

		Iterator itr = allocation.iterator();
		while (itr.hasNext()) {
			AllocationResponse dao = new AllocationResponse();

			Object[] obj = (Object[]) itr.next();

			Integer allocationAmount = Integer.parseInt(String.valueOf(obj[0]));
			Employee emp = (Employee) obj[1];
			Project projct = (Project) obj[2];

			emp.setAllocation(null);
			projct.setAllocation(null);
			dao.setEmployee(emp);
			dao.setProject(projct);
			dao.setAllocation(allocationAmount);

			allocations.add(dao);
		}
		return allocations;
		
	}
	
	public List<AllocationResponse> downlaodAllEmployeeDataByUserRole(Date startDate, String userRole) throws Exception {
		// TODO Auto-generated method stub
		List<AllocationResponse> allocations = new ArrayList<>();
		List<Allocation> allocation = new ArrayList<>();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		/*
		 * List<ChartListResponse> chartResponseList = new ArrayList<>();
		 * ChartResponse chartResponse = new ChartResponse();
		 */

		if (userRole.equals("All Employee")) {
			allocation = allocationRepo.getAllEmployeeData(startDate);
		} else {
			allocation = allocationRepo.getAllEmployeeDataByUserRole(startDate, userRole);
		}

		Iterator itr = allocation.iterator();
		while (itr.hasNext()) {
			AllocationResponse dao = new AllocationResponse();

			Object[] obj = (Object[]) itr.next();

			Integer allocationAmount = Integer.parseInt(String.valueOf(obj[0]));
			Employee emp = (Employee) obj[1];
			Project projct = (Project) obj[2];

			emp.setAllocation(null);
			projct.setAllocation(null);
			dao.setEmployee(emp);
			dao.setProject(projct);
			dao.setAllocation(allocationAmount);

			allocations.add(dao);
		}

	return allocations;
		
	}
	
	
	public List<ProjectWiseResponse> downloadGetProjectWiseData(String projectName,String crNo,String userRole) throws Exception {
		
		List<ProjectWiseResponse> projectResponse = new ArrayList<>();
		
		List<Allocation> deptCountList = allocationRepo.getDeptCountByProject(projectName,crNo);
		
		int devCount = 0,qaCount= 0,baCount= 0,managementCount = 0 ;
		String empRole = null;
		Iterator itr = deptCountList.iterator();
	    while (itr.hasNext()) {
			
			
			Object[] obj = (Object[]) itr.next();
			Employee emp = (Employee) obj[0];
			Project projct = (Project) obj[1];
			
		    empRole = emp.getUserRole();
			
			if(empRole.equals("Dev"))
			{
				devCount= devCount +1;
			}
			if(empRole.equals("QA"))
			{
				qaCount= qaCount +1;
			}
			if(empRole.equals("BA"))
			{
				baCount= baCount +1;
			}
			
			if(empRole.equals("Management"))
			{
				managementCount= managementCount +1;
			}
			
			
		}
	    

		
		
	    
	    List<Allocation> allocationList = new ArrayList<>();
	    
		if(userRole.equals("All Employee"))
		{
		  allocationList = allocationRepo.getAllProjectWiseData(projectName,crNo);	
		}
		else
		{
		  allocationList = allocationRepo.getProjectWiseData(projectName,crNo,userRole);
		}
		
		
		Iterator it = allocationList.iterator();
	    while (it.hasNext()) {
	    	ProjectWiseResponse projectWiseResponse = new ProjectWiseResponse();
			
			Object[] obj = (Object[]) it.next();
			Employee emp = (Employee) obj[0];
			int alloc = (int) obj[1];
			
			projectWiseResponse.setEmployee(emp);
			projectWiseResponse.setAllocation(alloc);
		    projectWiseResponse.setBaCount(baCount);
		    projectWiseResponse.setDevCount(devCount);
		    projectWiseResponse.setQaCount(qaCount);
		    projectWiseResponse.setManagementCount(managementCount);
			
			projectResponse.add(projectWiseResponse);
			
		}
	    
	    
		return projectResponse;
	}
	
	
	
	public List<ChartListResponse> getBookingEmployeeSummary(String id,Date startDate) throws Exception {
		
		
		List<ChartListResponse> chartResponseList = new ArrayList<>();
		List<Allocation> allocationList = allocationRepo.getBookingByEmp(id,startDate);
		
		Iterator itr = allocationList.iterator();
		while (itr.hasNext()) {
			ChartListResponse chartListResponse = new ChartListResponse();

			Object[] obj = (Object[]) itr.next();
			
			
			Employee emp = (Employee) obj[0];
			Project project = (Project) obj[1];
			Integer allocation = Integer.parseInt(String.valueOf(obj[2]));
		    Date bookingStartDate = (Date) obj[3];
			Date bookingEndDate = (Date)obj[4];
					
			emp.setAllocation(null);
			project.setAllocation(null);
			
			if (bookingStartDate != null && bookingEndDate != null) {
				
				chartListResponse.setId(project.getProjectName());
				chartListResponse.setName(project.getProjectName());
				chartListResponse.setCrNo(project.getCrNo());
				chartListResponse.setProgressValue(project.getProjectProgress() + "%");
				chartListResponse.setRowHeight(30);
				chartListResponse.setAllocation(allocation);
				chartListResponse.setActualStart(bookingStartDate);
				chartListResponse.setActualEnd(bookingEndDate);
				
			}
			
						
			chartResponseList.add(chartListResponse);
		}

		return chartResponseList;
	}
	
	
	public List<ChartListResponse> getBookingProjects(String projectName,String crNo,String userRole) throws Exception {
		
		
		List<ChartListResponse> chartResponseList = new ArrayList<>();
		List<Allocation> allocationList = new ArrayList<>();
		if (userRole.equals("All Employee")) {
			allocationList = allocationRepo.getAllBookingProjects(projectName,crNo);
		} else {
			allocationList = allocationRepo.getBookingProjects(projectName,crNo,userRole);
		}
		 
		
		Iterator itr = allocationList.iterator();
		while (itr.hasNext()) {
			ChartListResponse chartListResponse = new ChartListResponse();

			Object[] obj = (Object[]) itr.next();
			
			
			Employee emp = (Employee) obj[0];
			Project project = (Project) obj[1];
			Integer allocation = Integer.parseInt(String.valueOf(obj[2]));
		    Date bookingStartDate = (Date) obj[3];
			Date bookingEndDate = (Date)obj[4];
					
			emp.setAllocation(null);
			project.setAllocation(null);
			
			if (bookingStartDate != null && bookingEndDate != null) {
				
				chartListResponse.setId(emp.getEmpId());
				chartListResponse.setName(emp.getEmpId());
				chartListResponse.setCrNo(emp.getEmpName());
				chartListResponse.setProgressValue(project.getProjectProgress() + "%");
				chartListResponse.setRowHeight(30);
				chartListResponse.setAllocation(allocation);
				chartListResponse.setActualStart(bookingStartDate);
				chartListResponse.setActualEnd(bookingEndDate);
				
			}
			
						
			chartResponseList.add(chartListResponse);
		}

		return chartResponseList;
	}
	
	public List<ChartListResponse> getEmployeeWiseData(Date startDate,Date endDate, String empName) throws Exception {
		// TODO Auto-generated method stub
		List<ChartListResponse> chartResponseList = new ArrayList<>();
		List<Allocation> allocation = new ArrayList<>();
		
		allocation = allocationRepo.getEmployeeWiseSummary(startDate,endDate,empName);
		

		Iterator itr = allocation.iterator();
		while (itr.hasNext()) {
			ChartListResponse chartListResponse = new ChartListResponse();
			
			String projectStatus= null;
			Date projectStart = null;
			Date projectEnd = null;
			Date projectActualStart = null;
			Date projectActualEnd = null;
			
			Object[] obj = (Object[]) itr.next();

			Integer allocationAmount = Integer.parseInt(String.valueOf(obj[0]));
			Employee emp = (Employee) obj[1];
			Project project = (Project) obj[2];
			
			projectStatus = project.getProjectStatus();
			projectStart = project.getStartDate();
			projectEnd = project.getEndDate();
			projectActualStart = project.getActualStartDate();
			projectActualEnd = project.getActualEndDate();

			emp.setAllocation(null);
			project.setAllocation(null);
			
			chartListResponse.setId(project.getProjectName());
			chartListResponse.setName(project.getProjectName());
			chartListResponse.setCrNo(project.getCrNo());
			chartListResponse.setStatus(project.getProjectStatus());
			chartListResponse.setProgressValue(project.getProjectProgress() + "%");
			chartListResponse.setRowHeight(30);
			chartListResponse.setAllocation(allocationAmount);
			
			// chart colum values
						chartListResponse.setActualStartTrue(project.getStartDate());
						chartListResponse.setActualEndTrue(project.getEndDate());
						chartListResponse.setBaselineStartTrue(project.getActualStartDate());
						chartListResponse.setBaselineEndTrue(project.getActualEndDate());

						if (startDate.after(project.getStartDate())) {
							projectStart = startDate;
						}

						
						if (projectActualStart != null && projectActualEnd != null) {
							chartListResponse.setActualStart(projectStart);
							chartListResponse.setActualEnd(projectEnd);
							chartListResponse.setBaselineStart(projectActualStart);
							chartListResponse.setBaselineEnd(projectActualEnd);
						}
						if (projectActualStart == null && projectActualEnd != null) {
							chartListResponse.setActualStart(projectStart);
							chartListResponse.setActualEnd(projectEnd);
							chartListResponse.setBaselineStart(projectStart);
							chartListResponse.setBaselineEnd(projectActualEnd);
						}

						if (projectActualStart != null && projectActualEnd == null) {
							chartListResponse.setActualStart(projectStart);
							chartListResponse.setActualEnd(projectEnd);
							chartListResponse.setBaselineStart(projectActualStart);
							chartListResponse.setBaselineEnd(projectEnd);
						}

						if (projectActualStart == null && projectActualEnd == null) {
							chartListResponse.setActualStart(projectStart);
							chartListResponse.setActualEnd(projectEnd);
							chartListResponse.setBaselineStart(projectStart);
							chartListResponse.setBaselineEnd(projectStart);
						}

			
			
			chartResponseList.add(chartListResponse);
		}
		return chartResponseList;
		
	}
	
	public List<ChartListResponse> getworkloadSummaryData(Date startDate,Date endDate, String userRole,String workloadType) throws Exception {
		// TODO Auto-generated method stub
		List<ChartListResponse> allocations = new ArrayList<>();
		List<Allocation> allocation = new ArrayList<>();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		/*
		 * List<ChartListResponse> chartResponseList = new ArrayList<>();
		 * ChartResponse chartResponse = new ChartResponse();
		 */
		
		if (userRole.equals("All Employee")) {
			allocation = allocationRepo.getworkloadSummaryData(startDate,endDate);
		} else {
			allocation = allocationRepo.getworkloadSummaryDatawithUserRole(startDate,endDate,userRole);
		}
		
		
		Iterator itr = allocation.iterator();
		while (itr.hasNext()) {
			ChartListResponse chartListResponse = new ChartListResponse();

			Object[] obj = (Object[]) itr.next();

			String empId = (String) obj[0];
			String empName = (String) obj[1];
			String empDep = (String) obj[2];
			Integer totalAllocation = Integer.parseInt(String.valueOf(obj[3]));
			
			
			
			
			if(workloadType.equals("UnderAllocation"))
			{
				if(totalAllocation<=100)
				{
					chartListResponse.setId(empId);
					chartListResponse.setName(empName);
					chartListResponse.setStatus(empDep);
					chartListResponse.setAllocation(totalAllocation);
					allocations.add(chartListResponse);
				}
			}
			else
			{
				if(totalAllocation>100)
				{
					chartListResponse.setId(empId);
					chartListResponse.setName(empName);
					chartListResponse.setStatus(empDep);
					chartListResponse.setAllocation(totalAllocation);
					allocations.add(chartListResponse);
				}
				
			 }
			
			
	
	}
	return allocations;
		
	}
	
	public List<ChartListResponse> getIdleEmployeeData(Date startDate,Date endDate,String userRole) throws Exception {
		
		List<ChartListResponse> chartResponseList = new ArrayList<>();
		List<Allocation> allocationList = new ArrayList<>();
		List<Employee> employeeList = new ArrayList<>();
		
		if (userRole.equals("All Employee")) {
			allocationList = allocationRepo.getIdleEmpData(startDate,endDate);
			employeeList = employeeRepo.getIdleEmployees();
		} else {
			allocationList = allocationRepo.getIdleEmpDatawithUserRole(startDate,endDate,userRole);
			employeeList = employeeRepo.getIdleEmployeeswithUserRole(userRole);
		}
		 
		
		
		 
		Iterator itr = allocationList.iterator();
		Iterator itr2 = employeeList.iterator();
		
		List<Date> dateList = new ArrayList<>();

		while (itr.hasNext()) {
			ChartListResponse chartListResponse = new ChartListResponse();

			Object[] obj = (Object[]) itr.next();
			
			
			String projectStatus= null;
			Date projectStart = null;
			Date projectEnd = null;
			Date projectActualStart = null;
			Date projectActualEnd = null;
			
			
			Integer allocationAmount = Integer.parseInt(String.valueOf(obj[0]));
			Employee emp = (Employee) obj[1];
			Project project = (Project) obj[2];
			Date allocationDate =  (Date) obj[3];
			dateList.add(allocationDate);
			
			
			
			projectStatus = project.getProjectStatus();
			projectStart = project.getStartDate();
			projectEnd = project.getEndDate();
			projectActualStart = project.getActualStartDate();
			projectActualEnd = project.getActualEndDate();

			emp.setAllocation(null);
			project.setAllocation(null);
			
			chartListResponse.setId(project.getProjectName());
			chartListResponse.setName(project.getProjectName());
			chartListResponse.setCrNo(project.getCrNo());
			chartListResponse.setStatus(project.getProjectStatus());
			chartListResponse.setProgressValue(project.getProjectProgress() + "%");
			chartListResponse.setRowHeight(30);
			chartListResponse.setAllocation(allocationAmount);
			chartListResponse.setEmpName(emp.getEmpName());
			chartListResponse.setEmpRole(emp.getUserRole());
			
			// chart colum values
			chartListResponse.setActualStartTrue(project.getStartDate());
			chartListResponse.setActualEndTrue(project.getEndDate());
			chartListResponse.setBaselineStartTrue(project.getActualStartDate());
			chartListResponse.setBaselineEndTrue(project.getActualEndDate());

			if (startDate.after(project.getStartDate())) {
				projectStart = startDate;
			}

			
			if (projectActualStart != null && projectActualEnd != null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectActualStart);
				chartListResponse.setBaselineEnd(projectActualEnd);
			}
			if (projectActualStart == null && projectActualEnd != null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectStart);
				chartListResponse.setBaselineEnd(projectActualEnd);
			}

			if (projectActualStart != null && projectActualEnd == null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectActualStart);
				chartListResponse.setBaselineEnd(projectEnd);
			}

			if (projectActualStart == null && projectActualEnd == null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectStart);
				chartListResponse.setBaselineEnd(projectStart);
			}

									
			chartResponseList.add(chartListResponse);
		}
		
		while (itr2.hasNext()) {
			ChartListResponse chartListResponse = new ChartListResponse();

			Employee emp = (Employee) itr2.next();
			
			chartListResponse.setEmpName(emp.getEmpName());
			chartListResponse.setEmpRole(emp.getUserRole());
			chartListResponse.setAllocation(emp.getTotal_Allocation());
			
							
			chartResponseList.add(chartListResponse);
		}
		
		Collections.shuffle(chartResponseList);
		return chartResponseList;
	}
	
public List<ChartListResponse> getAllocatedEmployeeData(Date startDate,Date endDate,String userRole) throws Exception {
		
		List<ChartListResponse> chartResponseList = new ArrayList<>();
		List<Allocation> allocationList = new ArrayList<>();
		
		
		if (userRole.equals("All Employee")) {
			allocationList = allocationRepo.getAllocatedEmpData(startDate,endDate);
			
		} else {
			allocationList = allocationRepo.getAllocatedEmpDatawithUserRole(startDate,endDate,userRole);
			
		}
		 
		
		Iterator itr = allocationList.iterator();
		

		while (itr.hasNext()) {
			ChartListResponse chartListResponse = new ChartListResponse();

			Object[] obj = (Object[]) itr.next();
			
			
			String projectStatus= null;
			Date projectStart = null;
			Date projectEnd = null;
			Date projectActualStart = null;
			Date projectActualEnd = null;
			
			
			Integer allocationAmount = Integer.parseInt(String.valueOf(obj[0]));
			Employee emp = (Employee) obj[1];
			Project project = (Project) obj[2];
			
			projectStatus = project.getProjectStatus();
			projectStart = project.getStartDate();
			projectEnd = project.getEndDate();
			projectActualStart = project.getActualStartDate();
			projectActualEnd = project.getActualEndDate();

			emp.setAllocation(null);
			project.setAllocation(null);
			
			chartListResponse.setId(project.getProjectName());
			chartListResponse.setName(project.getProjectName());
			chartListResponse.setCrNo(project.getCrNo());
			chartListResponse.setStatus(project.getProjectStatus());
			chartListResponse.setProgressValue(project.getProjectProgress() + "%");
			chartListResponse.setRowHeight(30);
			chartListResponse.setAllocation(allocationAmount);
			chartListResponse.setEmpName(emp.getEmpName());
			chartListResponse.setEmpRole(emp.getUserRole());
			
			// chart colum values
			chartListResponse.setActualStartTrue(project.getStartDate());
			chartListResponse.setActualEndTrue(project.getEndDate());
			chartListResponse.setBaselineStartTrue(project.getActualStartDate());
			chartListResponse.setBaselineEndTrue(project.getActualEndDate());

			if (startDate.after(project.getStartDate())) {
				projectStart = startDate;
			}

			
			if (projectActualStart != null && projectActualEnd != null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectActualStart);
				chartListResponse.setBaselineEnd(projectActualEnd);
			}
			if (projectActualStart == null && projectActualEnd != null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectStart);
				chartListResponse.setBaselineEnd(projectActualEnd);
			}

			if (projectActualStart != null && projectActualEnd == null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectActualStart);
				chartListResponse.setBaselineEnd(projectEnd);
			}

			if (projectActualStart == null && projectActualEnd == null) {
				chartListResponse.setActualStart(projectStart);
				chartListResponse.setActualEnd(projectEnd);
				chartListResponse.setBaselineStart(projectStart);
				chartListResponse.setBaselineEnd(projectStart);
			}

									
			chartResponseList.add(chartListResponse);
		}
		
		return chartResponseList;
	}

public List<IdelResponse> getIdleData(Date startDate,Date endDate,String userRole) throws Exception {
	// TODO Auto-generated method stub
	
	List<Allocation> allocationList = new ArrayList<>();
	List<Employee> employeeList = new ArrayList<>();
	List<Date> dateList = new ArrayList<>();
	
	if (userRole.equals("All Employee")) {
		allocationList = allocationRepo.getIdleEmpData(startDate,endDate);
		employeeList = employeeRepo.getIdleEmployees();
	} else {
		allocationList = allocationRepo.getIdleEmpwithEmpRole(startDate,endDate,userRole);
		employeeList = employeeRepo.getIdleEmployeeswithUserRole(userRole);
	}
	
	Iterator itr = allocationList.iterator();
	Iterator itr2 = employeeList.iterator();
	
	
	List<IdelResponse> idleEmployeeList = new ArrayList<>();
	
	while (itr.hasNext()) {
		IdelResponse idelResponse = new IdelResponse();
		final String empName,empRole ;
		Object[] obj = (Object[]) itr.next();
		

		Employee emp = (Employee) obj[0];
		Date allocationDate =  (Date) obj[1];
		
		empName = emp.getEmpName();
		empRole = emp.getUserRole();
		if(idleEmployeeList.stream().filter(o -> o.getEmployeeName().equals(empName)).findFirst().isPresent())
		{
			for(IdelResponse response : idleEmployeeList) {
				
				
				if(response.getEmployeeName().equals(empName))
				{
					List<Date> allocationDateList = response.getAllocationDateList();
					if(allocationDateList.stream().filter(o -> o.equals(allocationDate)).findFirst().isPresent())
					{}
					else
					{
					allocationDateList.add(allocationDate);
					response.setAllocationDateList(allocationDateList);
					}
				}
				response.setEmployeeRole(empRole);
			}
		}
		 else
		{
			IdelResponse idel = new IdelResponse();
		    idel.setEmployeeName(empName);
		    idel.setEmployeeRole(empRole);
		    List<Date> allocationDateList = new ArrayList<>();
		    allocationDateList.add(allocationDate);
			idel.setAllocationDateList(allocationDateList);

			idleEmployeeList.add(idel);
		
		}
	
	}
	while (itr2.hasNext()) {
		IdelResponse idel = new IdelResponse();

		Employee emp = (Employee) itr2.next();
		
		idel.setEmployeeName(emp.getEmpName());
		idel.setEmployeeRole(emp.getUserRole());
		
		idleEmployeeList.add(idel);
	}
	
	
	return idleEmployeeList;
}

public List<IdelResponse> getIdleEmpforHomePage(Date startDate,Date endDate) throws Exception {
	// TODO Auto-generated method stub

	List<Date> dateList = new ArrayList<>();
	
	
	List<Allocation> allocationList = allocationRepo.getIdleEmpData(startDate,endDate);
 	List<Employee> employeeList = employeeRepo.getIdleEmployees();
	
	
	Iterator itr = allocationList.iterator();

	Iterator itr2 = employeeList.iterator();
	
	List<IdelResponse> idleEmployeeList = new ArrayList<>();
	
	while (itr.hasNext()) {
		IdelResponse idelResponse = new IdelResponse();
		final String empName,empRole;
		Object[] obj = (Object[]) itr.next();
		

		Employee emp = (Employee) obj[0];
		Date allocationDate =  (Date) obj[1];
		
		empName = emp.getEmpName();
		empRole = emp.getUserRole();
		
		if(idleEmployeeList.stream().filter(o -> o.getEmployeeName().equals(empName)).findFirst().isPresent())
		{
			for(IdelResponse response : idleEmployeeList) {
				
				
				if(response.getEmployeeName().equals(empName))
				{
					List<Date> allocationDateList = response.getAllocationDateList();
					if(allocationDateList.stream().filter(o -> o.equals(allocationDate)).findFirst().isPresent())
					{}
					else
					{
					allocationDateList.add(allocationDate);
					response.setAllocationDateList(allocationDateList);
					}
				}
				response.setEmployeeRole(empRole);
			}
			
		}
		 else
		{
			IdelResponse idel = new IdelResponse();
		    idel.setEmployeeName(empName);
		    idel.setEmployeeRole(empRole);
		    List<Date> allocationDateList = new ArrayList<>();
		    allocationDateList.add(allocationDate);
			idel.setAllocationDateList(allocationDateList);

			idleEmployeeList.add(idel);
		
		}
		
	
	}
	while (itr2.hasNext()) {
		IdelResponse idel = new IdelResponse();

		Employee emp = (Employee) itr2.next();
		
		idel.setEmployeeName(emp.getEmpName());
		idel.setEmployeeRole(emp.getUserRole());
		
		idleEmployeeList.add(idel);
	}
	
	
	
	return idleEmployeeList;
}

public List<ChartListResponse> getworkloadSummaryforHomePage(Date startDate,Date endDate) throws Exception {
	// TODO Auto-generated method stub
	List<ChartListResponse> allocations = new ArrayList<>();
	
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	
	  List<ChartListResponse> chartResponseList = new ArrayList<>();
	 ChartResponse chartResponse = new ChartResponse();
	 
	
	List<Allocation> allocation = allocationRepo.getworkloadSummaryData(startDate,endDate);
	
	Iterator itr = allocation.iterator();
	while (itr.hasNext()) {
		ChartListResponse chartListResponse = new ChartListResponse();

		Object[] obj = (Object[]) itr.next();

		String empId = (String) obj[0];
		String empName = (String) obj[1];
		String empDep = (String) obj[2];
		Integer totalAllocation = Integer.parseInt(String.valueOf(obj[3]));
		
		
		chartListResponse.setId(empId);
		chartListResponse.setName(empName);
		chartListResponse.setStatus(empDep);
		chartListResponse.setAllocation(totalAllocation);
		allocations.add(chartListResponse);
		
		

	}
return allocations;
	
}
public List<ChartListResponse> getBookingEmployeesforHomePage(Date startDate,Date endDate) throws Exception {
	
	
	List<ChartListResponse> chartResponseList = new ArrayList<>();
	List<Allocation> allocationList = allocationRepo.getBookingEmployeesforHomePage(startDate,endDate);
	
	Iterator itr = allocationList.iterator();
	while (itr.hasNext()) {
		ChartListResponse chartListResponse = new ChartListResponse();

		Object[] obj = (Object[]) itr.next();
		
		
		Employee emp = (Employee) obj[0];
		Integer allocation = Integer.parseInt(String.valueOf(obj[1]));
	    
				
		emp.setAllocation(null);
		
		
		chartListResponse.setName(emp.getEmpName());
		chartListResponse.setCrNo(emp.getUserRole());
		chartListResponse.setAllocation(allocation);
		
			
				
		chartResponseList.add(chartListResponse);
	}

	return chartResponseList;
}


}
