/**
 * 
 */
package lk.dialog.ist.reslo.dto;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import lk.dialog.ist.reslo.dao.AllocationDao;
import lk.dialog.ist.reslo.dao.ApplictionUserDao;
import lk.dialog.ist.reslo.dao.ChartDao;
import lk.dialog.ist.reslo.dao.EmployeeDao;
import lk.dialog.ist.reslo.dao.ProjectDao;
import lk.dialog.ist.reslo.request.AllocationRequest;
import lk.dialog.ist.reslo.request.EmployeeCreateRequest;
import lk.dialog.ist.reslo.request.ProjectAddRequest;
import lk.dialog.ist.reslo.request.UserCreateRequest;
import lk.dialog.ist.reslo.response.AllocationChartResponse;
import lk.dialog.ist.reslo.response.AllocationResponse;
import lk.dialog.ist.reslo.response.ChartListResponse;
import lk.dialog.ist.reslo.response.ChartResponse;
import lk.dialog.ist.reslo.response.DeptCountResponse;
import lk.dialog.ist.reslo.response.IdelResponse;
import lk.dialog.ist.reslo.response.ProjectWiseResponse;
import lk.dialog.ist.reslo.services.entities.Allocation;
import lk.dialog.ist.reslo.services.entities.ApplicationUser;
import lk.dialog.ist.reslo.services.entities.Employee;
import lk.dialog.ist.reslo.services.entities.Project;



/**
 * @author amel_33251
 *
 */
public class ChartDto {
	
	@Autowired
	private ChartDao chartDao;
	
	
	public ChartResponse getGanttChartData(Date startDate, Date endDate) throws Exception{
		return chartDao.getGanttChartData(startDate,endDate);		
	}
	
	public ChartResponse getAllProjectsGanttChartData(Date startDate) throws Exception{
		return chartDao.getAllProjectsGanttChartData(startDate);		
	}
	
	public List<ChartListResponse> downloadAllProjectsData(Date startDate) throws Exception{
		return chartDao.downloadAllProjectsData(startDate);		
	}
	
	public List<AllocationResponse> getAllEmployeeDataByUserRole(Date startDate, String userRole) throws Exception{
		return chartDao.getAllEmployeeDataByUserRole(startDate,userRole);		
	}
	
	public List<AllocationResponse> downlaodAllEmployeeDataByUserRole(Date startDate, String userRole) throws Exception{
		return chartDao.downlaodAllEmployeeDataByUserRole(startDate,userRole);		
	}
	
	
	public List<AllocationChartResponse> getEmployeeAllocationbetweenStartEndDates(Date startDate, Date endDate) throws Exception{
		return chartDao.getEmployeeAllocationbetweenStartEndDates(startDate,endDate);		
	}
	
	public List<ProjectWiseResponse> downloadGetProjectWiseData(String projectName,String crNo,String userRole) throws Exception{
		return chartDao.downloadGetProjectWiseData(projectName,crNo,userRole);		
	}
	
	
	public List<ChartListResponse> getBookingEmployeeSummary(String id,Date startDate) throws Exception{
		return chartDao.getBookingEmployeeSummary(id,startDate);		
	}
	
	public List<ChartListResponse> downloadgetBookingEmployeeSummary(String id,Date startDate) throws Exception{
		return chartDao.getBookingEmployeeSummary(id,startDate);		
	}
	
	public List<ChartListResponse> getBookingProjects(String projectName,String crNo,String userRole) throws Exception{
		return chartDao.getBookingProjects(projectName,crNo,userRole);		
	}
	
	public List<ChartListResponse> getEmployeeWiseData(Date startDate,Date endDate, String empName) throws Exception{
		return chartDao.getEmployeeWiseData(startDate,endDate,empName);		
	}
	
	public List<ChartListResponse> getworkloadSummaryData(Date startDate,Date endDate, String userRole,String workloadType) throws Exception{
		return chartDao.getworkloadSummaryData(startDate,endDate,userRole,workloadType);		
	}
	
	public List<ChartListResponse> getIdleEmployeeData(Date startDate,Date endDate, String userRole) throws Exception{
		return chartDao.getIdleEmployeeData(startDate,endDate,userRole);		
	}
	
	public List<ChartListResponse> getAllocatedEmployeeData(Date startDate,Date endDate, String userRole) throws Exception{
		return chartDao.getAllocatedEmployeeData(startDate,endDate,userRole);		
	}
	
	public List<IdelResponse> getIdleData(Date startDate,Date endDate, String userRole) throws Exception{
		return chartDao.getIdleData(startDate,endDate,userRole);		
	}
	
	public List<IdelResponse> getIdleEmpforHomePage(Date startDate,Date endDate) throws Exception{
		return chartDao.getIdleEmpforHomePage(startDate,endDate);		
	}
	
	public List<ChartListResponse> getworkloadSummaryforHomePage(Date startDate,Date endDate) throws Exception{
		return chartDao.getworkloadSummaryforHomePage(startDate,endDate);		
	}
	
	public List<ChartListResponse> getBookingEmployeesforHomePage(Date startDate,Date endDate) throws Exception{
		return chartDao.getBookingEmployeesforHomePage(startDate,endDate);		
	}
}
