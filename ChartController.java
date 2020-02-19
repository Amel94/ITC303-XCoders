/**
 * 
 */
package lk.dialog.ist.reslo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import lk.dialog.ist.reslo.dto.ChartDto;
import lk.dialog.ist.reslo.response.AllocationChartResponse;
import lk.dialog.ist.reslo.response.AllocationResponse;
import lk.dialog.ist.reslo.response.ChartListResponse;
import lk.dialog.ist.reslo.response.ChartResponse;
import lk.dialog.ist.reslo.response.IdelResponse;
import lk.dialog.ist.reslo.response.ProjectWiseResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

/**
 * @author amel_33251
 *
 */
@Controller
public class ChartController {

	@Autowired
	private ChartDto chartDto;

	Authentication auth;

	@RequestMapping(value = "/getGanttChartData", method = RequestMethod.GET)
	public @ResponseBody ChartResponse getGanttChartData(Date startDate, Date endDate) {
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		ChartResponse chartResponse = new ChartResponse();
		if (auth.isAuthenticated()) {
			try {
				chartResponse = chartDto.getGanttChartData(startDate, endDate);
			    } catch (Exception e) {
				e.printStackTrace();
			}
		}
		return chartResponse;
	}
	
	@RequestMapping(value = "/getAllProjectsGanttChartData", method = RequestMethod.GET)
	public @ResponseBody ChartResponse getAllProjectsGanttChartData(Date startDate) {
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		ChartResponse chartResponse = new ChartResponse();
		if (auth.isAuthenticated()) {
			try {
				chartResponse = chartDto.getAllProjectsGanttChartData(startDate);
			    } catch (Exception e) {
				e.printStackTrace();
			}
		}
		return chartResponse;
	}
	
	@RequestMapping(value = "/downloadAllProjectsData", method = RequestMethod.GET)
	public @ResponseBody List<ChartListResponse> downloadAllProjectsData(Date startDate,HttpServletResponse response) throws JRException, IOException {
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		List<ChartListResponse> chartResponse = new ArrayList<>();
		if (auth.isAuthenticated()) {
			try {
				chartResponse = chartDto.downloadAllProjectsData(startDate);
			    } catch (Exception e) {
				e.printStackTrace();
			}
			
			DateFormat srcDf = new SimpleDateFormat("dd/MM/yyyy");
			String date = srcDf.format(startDate);

			Map params = new HashMap();
			params.put("Start", "" +date);
			
			Collection<Map<String, ?>> collist = new ArrayList<>();
			for (ChartListResponse chartResponses : chartResponse) {
				Map<String, Object> hashMap = new HashMap<>();
				
				
				hashMap.put("ProjectName", "" + chartResponses.getId());
				hashMap.put("CrNo", "" + chartResponses.getCrNo());
				hashMap.put("Status", "" + chartResponses.getStatus());
				hashMap.put("StartDate", "" + chartResponses.getActualStartTrue());
				hashMap.put("EndDate", "" + chartResponses.getActualEndTrue());
				hashMap.put("ActualStartDate", "" + chartResponses.getBaselineStartTrue());
				hashMap.put("ActualEndDate", "" + chartResponses.getBaselineEndTrue());
				hashMap.put("projectProgress", "" + chartResponses.getProgressValue());
				

				collist.add(hashMap);
			}
			
            JasperReport report = JasperCompileManager.compileReport(this.getClass().getResourceAsStream("/AllProjectReport.jrxml"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, new JRMapCollectionDataSource(collist));
            
            response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename=AllProjectReport.pdf");

         final OutputStream outStream = response.getOutputStream();
         JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
         
			
		}
		return chartResponse;
	}
	
	
	@RequestMapping(value = "/getAllEmployeeData", method = RequestMethod.GET)
	public @ResponseBody List<AllocationResponse> getAllEmployeeData(Date startDate, String userRole,HttpServletResponse response) throws JRException, IOException {
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();		
		if (auth.isAuthenticated()) {
			try {
				return chartDto.getAllEmployeeDataByUserRole(startDate, userRole);
			    } catch (Exception e) {
				e.printStackTrace();
			}
			
         
     }

		
		return null;
	}
	
	
	@RequestMapping(value = "/downloadAllEmployeeData", method = RequestMethod.GET)
	public @ResponseBody List<AllocationResponse> downloadAllEmployeeData(Date startDate, String userRole,HttpServletResponse response) throws JRException, IOException {
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();	
		List<AllocationResponse> allocations = new ArrayList<>();
		if (auth.isAuthenticated()) {
			
			try {
				
				allocations= chartDto.downlaodAllEmployeeDataByUserRole(startDate, userRole);
			    } catch (Exception e) {
				e.printStackTrace();
			}
			
			DateFormat srcDf = new SimpleDateFormat("dd/MM/yyyy");
			String date = srcDf.format(startDate);

			Map params = new HashMap();
			params.put("userRole", "" +userRole);
			params.put("Start", "" +date);
			
			Collection<Map<String, ?>> collist = new ArrayList<>();
			for (AllocationResponse allocat : allocations) {
				Map<String, Object> hashMap = new HashMap<>();
				
				hashMap.put("EmpName", "" + allocat.getEmployee().getEmpName());
				hashMap.put("ProjectName", "" + allocat.getProject().getProjectName());
				hashMap.put("CrNo", "" + allocat.getProject().getCrNo());
				hashMap.put("Status", "" + allocat.getProject().getProjectStatus());
				hashMap.put("StartDate", "" + allocat.getProject().getStartDate());
				hashMap.put("EndDate", "" + allocat.getProject().getEndDate());
				hashMap.put("ActualStartDate", "" + allocat.getProject().getActualStartDate());
				hashMap.put("ActualEndDate", "" + allocat.getProject().getActualEndDate());
				hashMap.put("projectProgress", "" + allocat.getProject().getProjectProgress());
				hashMap.put("Allocation", "" + allocat.getAllocation());

				collist.add(hashMap);
			}
			
			/*String path = getServletContext().getRealPath("/jasper/AllEmpReport.jrxml");
			InputStream input = new FileInputStream(new File(path));
			JasperDesign jasperDesign = JRXmlLoader.load(input);
			JasperReport report = JasperCompileManager.compileReport(jasperDesign);*/
			
            JasperReport report = JasperCompileManager.compileReport(this.getClass().getResourceAsStream("/AllEmpReport.jrxml"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, new JRMapCollectionDataSource(collist));
            
            response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename=AllEmployeeReport.pdf");

         final OutputStream outStream = response.getOutputStream();
         JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
         
     
     }

		
		return null;
	}
	
	
	
	@RequestMapping(value = "/getAllocationGanttChartData", method = RequestMethod.GET)
	public @ResponseBody List<AllocationChartResponse> getAllocationData(Date startDate, Date endDate) {
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		List<AllocationChartResponse> allocationList = new ArrayList<>();
		if (auth.isAuthenticated()) {
			try {
				allocationList = chartDto.getEmployeeAllocationbetweenStartEndDates(startDate, endDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return allocationList;
	}


	@RequestMapping(value = "/downloadGetProjectWiseData", method = RequestMethod.GET)
	public @ResponseBody  List<ProjectWiseResponse> getDeptCountByProject(
			@RequestParam(value = "projectName") String projectName,
			@RequestParam(value = "crNo") String crNo,
			@RequestParam(value = "userRole") String userRole,HttpServletResponse response) throws JRException, IOException
	{	
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 List<ProjectWiseResponse> depcount = new ArrayList<>();
		
		 
		 if(auth.isAuthenticated())
		{
		try {
			depcount= chartDto.downloadGetProjectWiseData(projectName,crNo,userRole);
		    
		}catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
		    }

		Map params = new HashMap();
		params.put("projectName", "" +projectName);
		params.put("crNo", "" +crNo);
		params.put("userRole", "" +userRole);
		for (ProjectWiseResponse project : depcount) {
		params.put("devCount", "" +project.getDevCount());
		params.put("qaCount", "" +project.getQaCount());
		params.put("baCount", "" +project.getBaCount());
		params.put("managementCount", "" +project.getManagementCount());
		}
		
		Collection<Map<String, ?>> collist = new ArrayList<>();
		for (ProjectWiseResponse allocat : depcount) {
			Map<String, Object> hashMap = new HashMap<>();
			
			hashMap.put("empId", "" + allocat.getEmployee().getEmpId());
			hashMap.put("EmpName", "" + allocat.getEmployee().getEmpName());
			hashMap.put("userRole", "" + allocat.getEmployee().getUserRole());
			hashMap.put("Allocation", "" + allocat.getAllocation());
			
			collist.add(hashMap);
		}
		
        JasperReport report = JasperCompileManager.compileReport(this.getClass().getResourceAsStream("/ProjectWiseReport.jrxml"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, new JRMapCollectionDataSource(collist));
        
        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=ProjectWiseReport.pdf");

     final OutputStream outStream = response.getOutputStream();
     JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
		return null;
	}

	
	@RequestMapping(value = "/getBookingEmployeeSummary", method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody  List<ChartListResponse> getAllocationByEmp(
			@RequestParam(value = "id") String id,@RequestParam(value = "startDate") Date startDate)
	{		
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {
			List<ChartListResponse> list = chartDto.getBookingEmployeeSummary(id,startDate);
			return list;
		    
		}catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }	
		    }
		return null;
	}
	
	@RequestMapping(value = "/downloadGetBookingEmployeeSummary", method = RequestMethod.GET)
	public @ResponseBody List<ChartListResponse> downloadgetBookingEmployeeSummary(String id,String empName,Date date,HttpServletResponse response) throws JRException, IOException {
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		List<ChartListResponse> chartResponse = new ArrayList<>();
		if (auth.isAuthenticated()) {
			try {
				chartResponse = chartDto.getBookingEmployeeSummary(id,date);
			    } catch (Exception e) {
				e.printStackTrace();
			}
			
			DateFormat srcDf = new SimpleDateFormat("dd/MM/yyyy");
			String startDate = srcDf.format(date);
			
			Map params = new HashMap();
			params.put("Start", "" +startDate);
			params.put("EmpName", "" +empName);
			
			Collection<Map<String, ?>> collist = new ArrayList<>();
			for (ChartListResponse chartResponses : chartResponse) {
				Map<String, Object> hashMap = new HashMap<>();
				
				hashMap.put("ProjectName", "" + chartResponses.getId());
				hashMap.put("CrNo", "" + chartResponses.getCrNo());
				hashMap.put("BookingStartDate", "" + chartResponses.getActualStart());
				hashMap.put("BookingEndDate", "" + chartResponses.getActualEnd());
				hashMap.put("Booking", "" + chartResponses.getAllocation());
				
				collist.add(hashMap);
			}
			
            JasperReport report = JasperCompileManager.compileReport(this.getClass().getResourceAsStream("/BookingEmpReport.jrxml"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, new JRMapCollectionDataSource(collist));
            
            response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename=BookingEmpReport.pdf");

         final OutputStream outStream = response.getOutputStream();
         JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
         
			
		}
		return chartResponse;
	}
	
	@RequestMapping(value = "/getBookingProjects", method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody  List<ChartListResponse> getBookingEmployees(
			@RequestParam(value = "projectName") String projectName,
			@RequestParam(value = "crNo") String crNo,
			@RequestParam(value = "userRole") String userRole)
	{		
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {
			List<ChartListResponse> list = chartDto.getBookingProjects(projectName,crNo,userRole);
			return list;
		    
		}catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }	
		    }
		return null;
	}
	
	@RequestMapping(value = "/downloadGetBookingProjectSummary", method = RequestMethod.GET)
	public @ResponseBody List<ChartListResponse> downloadGetBookingProjectSummary(String projectName,String crNo,String userRole,HttpServletResponse response) throws JRException, IOException {
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		List<ChartListResponse> chartResponse = new ArrayList<>();
		if (auth.isAuthenticated()) {
			try {
				chartResponse = chartDto.getBookingProjects(projectName,crNo,userRole);
			    } catch (Exception e) {
				e.printStackTrace();
			}
			
			
			Map params = new HashMap();
			params.put("ProjectName", "" +projectName);
			params.put("CrNo", "" +crNo);
			params.put("EmpRole", "" +userRole);
			
			Collection<Map<String, ?>> collist = new ArrayList<>();
			for (ChartListResponse chartResponses : chartResponse) {
				Map<String, Object> hashMap = new HashMap<>();
				
				hashMap.put("EmpId", "" + chartResponses.getId());
				hashMap.put("EmpName", "" + chartResponses.getCrNo());
				hashMap.put("BookingStartDate", "" + chartResponses.getActualStart());
				hashMap.put("BookingEndDate", "" + chartResponses.getActualEnd());
				hashMap.put("Booking", "" + chartResponses.getAllocation());
				
				collist.add(hashMap);
			}
			
            JasperReport report = JasperCompileManager.compileReport(this.getClass().getResourceAsStream("/BookingProjectReport.jrxml"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, new JRMapCollectionDataSource(collist));
            
            response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename=BookingEmpReport.pdf");

         final OutputStream outStream = response.getOutputStream();
         JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
         
			
		}
		return chartResponse;
	}
	
	@RequestMapping(value = "/getEmployeeWiseData", method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody  List<ChartListResponse> getEmployeeWiseData(
			@RequestParam(value = "startDate") Date startDate,
			@RequestParam(value = "endDate") Date endDate,
			@RequestParam(value = "employeeName") String employeeName)
	{		
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {
			List<ChartListResponse> list = chartDto.getEmployeeWiseData(startDate,endDate,employeeName);
			return list;
		    
		}catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }	
		    }
		return null;
	}
	
	@RequestMapping(value = "/downloadEmpWiseData", method = RequestMethod.GET)
	public @ResponseBody List<ChartListResponse> downloadEmpWiseData(Date startDate,Date endDate,String employeeName,String totalAlloaction,HttpServletResponse response) throws JRException, IOException {
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		List<ChartListResponse> chartResponse = new ArrayList<>();
		if (auth.isAuthenticated()) {
			try {
				chartResponse = chartDto.getEmployeeWiseData(startDate,endDate,employeeName);
			    } catch (Exception e) {
				e.printStackTrace();
			}
			
			
			DateFormat srcDf = new SimpleDateFormat("dd/MM/yyyy");
			String start = srcDf.format(startDate);
			String end = srcDf.format(endDate);
			
			Map params = new HashMap();
			params.put("Start", "" +start);
			params.put("End", "" +end);
			params.put("EmpName", "" +employeeName);
			params.put("TotAllocation", "" +totalAlloaction);
			
			Collection<Map<String, ?>> collist = new ArrayList<>();
			for (ChartListResponse chartResponses : chartResponse) {
				Map<String, Object> hashMap = new HashMap<>();
				
				hashMap.put("ProjectName", "" + chartResponses.getId());
				hashMap.put("CrNo", "" + chartResponses.getCrNo());
				hashMap.put("Status", "" + chartResponses.getStatus());
				hashMap.put("StartDate", "" + srcDf.format(chartResponses.getActualStart()));
				hashMap.put("EndDate", "" + srcDf.format(chartResponses.getActualEnd()));
				hashMap.put("ActualStartDate", "" + srcDf.format(chartResponses.getBaselineStart()));
				hashMap.put("ActualEndDate", "" + srcDf.format(chartResponses.getBaselineEnd()));
				hashMap.put("Allocation", "" + chartResponses.getAllocation());
				
				collist.add(hashMap);
			}
			
            JasperReport report = JasperCompileManager.compileReport(this.getClass().getResourceAsStream("/EmpWiseReport.jrxml"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, new JRMapCollectionDataSource(collist));
            
            response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename=EmployeeWiseReport.pdf");

         final OutputStream outStream = response.getOutputStream();
         JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
         
			
		}
		return chartResponse;
	}
	
	@RequestMapping(value = "/getworkloadSummaryData", method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody  List<ChartListResponse> getworkloadSummaryData(
			@RequestParam(value = "startDate") Date startDate,
			@RequestParam(value = "endDate") Date endDate,
			@RequestParam(value = "userRole") String userRole,
			@RequestParam(value = "workloadType") String workloadType)
	{		
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {
			List<ChartListResponse> list = chartDto.getworkloadSummaryData(startDate,endDate,userRole,workloadType);
			return list;
		    
		}catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }	
		    }
		return null;
	}
	
	@RequestMapping(value = "/downloadWorkloadSummaryData", method = RequestMethod.GET)
	public @ResponseBody List<ChartListResponse> downloadWorkloadSummaryData(Date startDate,Date endDate,String userRole,String workloadType,HttpServletResponse response) throws JRException, IOException {
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		List<ChartListResponse> chartResponse = new ArrayList<>();
		if (auth.isAuthenticated()) {
			try {
				chartResponse = chartDto.getworkloadSummaryData(startDate,endDate,userRole,workloadType);
			    } catch (Exception e) {
				e.printStackTrace();
			}
			
			
			DateFormat srcDf = new SimpleDateFormat("dd/MM/yyyy");
			String start = srcDf.format(startDate);
			String end = srcDf.format(endDate);
			
			Map params = new HashMap();
			params.put("Start", "" +start);
			params.put("End", "" +end);
			params.put("EmpRole", "" +userRole);
			params.put("WorkLoad", "" +workloadType);
			
			Collection<Map<String, ?>> collist = new ArrayList<>();
			for (ChartListResponse chartResponses : chartResponse) {
				Map<String, Object> hashMap = new HashMap<>();
				
				hashMap.put("EmpId", "" + chartResponses.getId());
				hashMap.put("EmpName", "" + chartResponses.getName());
				hashMap.put("UserRole", "" + chartResponses.getStatus());
				hashMap.put("TotalAllocation", "" + chartResponses.getAllocation());
				
				
				collist.add(hashMap);
			}
			
            JasperReport report = JasperCompileManager.compileReport(this.getClass().getResourceAsStream("/ResourceWorkloadReport.jrxml"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, new JRMapCollectionDataSource(collist));
            
            response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename=ResourceWorkloadReport.pdf");

         final OutputStream outStream = response.getOutputStream();
         JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
         
			
		}
		return chartResponse;
	}
	
	@RequestMapping(value = "/getIdleEmployeeData", method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody  List<ChartListResponse> getIdleEmployeeData(
			@RequestParam(value = "startDate") Date startDate,
			@RequestParam(value = "endDate") Date endDate,
			@RequestParam(value = "userRole") String userRole)
	{		
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {
			List<ChartListResponse> list = chartDto.getIdleEmployeeData(startDate,endDate,userRole);
			return list;
		    
		}catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }	
		    }
		return null;
	}
	
	@RequestMapping(value = "/downloadIdleEmployeeData", method = RequestMethod.GET)
	public @ResponseBody List<ChartListResponse> downloadIdleEmployeeData(Date startDate,Date endDate,String userRole,HttpServletResponse response) throws JRException, IOException {
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		List<ChartListResponse> chartResponse = new ArrayList<>();
		if (auth.isAuthenticated()) {
			try {
				chartResponse = chartDto.getIdleEmployeeData(startDate,endDate,userRole);
			    } catch (Exception e) {
				e.printStackTrace();
			}
			
			
			DateFormat srcDf = new SimpleDateFormat("yyyy/MM/dd");
			String start = srcDf.format(startDate);
			String end = srcDf.format(endDate);
			
			Map params = new HashMap();
			params.put("Start", "" +start);
			params.put("End", "" +end);
			params.put("EmpRole", "" +userRole);
			
			
			Collection<Map<String, ?>> collist = new ArrayList<>();
			for (ChartListResponse chartResponses : chartResponse) {
				Map<String, Object> hashMap = new HashMap<>();
				
				
				hashMap.put("EmpName", "" + chartResponses.getName());
				hashMap.put("UserRole", "" + chartResponses.getEmpRole());
				if(chartResponses.getAllocation()==0)
				{
					hashMap.put("ProjectName", "" + "  -  ");
					hashMap.put("CrNo", "" + "  -  ");
					hashMap.put("Status", "" + "  -  ");
					hashMap.put("StartDate", "" + "  -  ");
					hashMap.put("EnddDate", "" + "  -  ");
					hashMap.put("ActualStartDate", "" + "  -  ");
					hashMap.put("ActualEndDate", "" + "  -  ");
					hashMap.put("Allocation", "" + chartResponses.getAllocation());
				}
				else
				{
					hashMap.put("ProjectName", "" + chartResponses.getId());
					hashMap.put("CrNo", "" + chartResponses.getCrNo());
					hashMap.put("Status", "" + chartResponses.getStatus());
					hashMap.put("StartDate", "" + srcDf.format(chartResponses.getActualStartTrue()));
					hashMap.put("EnddDate", "" + srcDf.format(chartResponses.getActualEndTrue()));
					hashMap.put("ActualStartDate", "" + srcDf.format(chartResponses.getBaselineStartTrue()));
					hashMap.put("ActualEndDate", "" + srcDf.format(chartResponses.getBaselineEndTrue()));
					hashMap.put("Allocation", "" + chartResponses.getAllocation());
				}
				
				
				collist.add(hashMap);
			}
			
            JasperReport report = JasperCompileManager.compileReport(this.getClass().getResourceAsStream("/IdleEmpReport.jrxml"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, new JRMapCollectionDataSource(collist));
            
            response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename=IdleEmployeesReport.pdf");

         final OutputStream outStream = response.getOutputStream();
         JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
         
			
		}
		return chartResponse;
	}
	
	@RequestMapping(value = "/getAllocatedEmployeeData", method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody  List<ChartListResponse> getAllocatedEmployeeData(
			@RequestParam(value = "startDate") Date startDate,
			@RequestParam(value = "endDate") Date endDate,
			@RequestParam(value = "userRole") String userRole)
	{		
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {
			List<ChartListResponse> list = chartDto.getAllocatedEmployeeData(startDate,endDate,userRole);
			return list;
		    
		}catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }	
		    }
		return null;
	}
	
	@RequestMapping(value = "/downloadAllocatedEmployeeData", method = RequestMethod.GET)
	public @ResponseBody List<ChartListResponse> downloadAllocatedEmployeeData(Date startDate,Date endDate,String userRole,HttpServletResponse response) throws JRException, IOException {
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		List<ChartListResponse> chartResponse = new ArrayList<>();
		if (auth.isAuthenticated()) {
			try {
				chartResponse = chartDto.getAllocatedEmployeeData(startDate,endDate,userRole);
			    } catch (Exception e) {
				e.printStackTrace();
			}
			
			
			DateFormat srcDf = new SimpleDateFormat("yyyy/MM/dd");
			String start = srcDf.format(startDate);
			String end = srcDf.format(endDate);
			
			Map params = new HashMap();
			params.put("Start", "" +start);
			params.put("End", "" +end);
			params.put("EmpRole", "" +userRole);
			
			
			Collection<Map<String, ?>> collist = new ArrayList<>();
			for (ChartListResponse chartResponses : chartResponse) {
				Map<String, Object> hashMap = new HashMap<>();
				
				
				hashMap.put("EmpName", "" + chartResponses.getEmpName());
				hashMap.put("UserRole", "" + chartResponses.getEmpRole());
				hashMap.put("ProjectName", "" + chartResponses.getId());
				hashMap.put("CrNo", "" + chartResponses.getCrNo());
				hashMap.put("Status", "" + chartResponses.getStatus());
				hashMap.put("StartDate", "" + srcDf.format(chartResponses.getActualStartTrue()));
				hashMap.put("EndDate", "" + srcDf.format(chartResponses.getActualEndTrue()));
				if(chartResponses.getBaselineStartTrue()==null)
				{
					hashMap.put("ActualStartDate", "" + " N/A ");	
				}
				else
				{
				hashMap.put("ActualStartDate", "" + srcDf.format(chartResponses.getBaselineStartTrue()));
				}
				if(chartResponses.getBaselineEndTrue()==null)
				{
					hashMap.put("ActualEndDate", "" + " N/A ");	
				}
				else
				{
					hashMap.put("ActualEndDate", "" + srcDf.format(chartResponses.getBaselineEndTrue()));
				}
				
				hashMap.put("Allocation", "" + chartResponses.getAllocation());
							
				
				collist.add(hashMap);
			}
			
            JasperReport report = JasperCompileManager.compileReport(this.getClass().getResourceAsStream("/AllocatedEmpReport.jrxml"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, new JRMapCollectionDataSource(collist));
            
            response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename=AllocatedEmployeesReport.pdf");

         final OutputStream outStream = response.getOutputStream();
         JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
         
			
		}
		return chartResponse;
	}
	
	@RequestMapping(value = "/getIdleData", method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody  List<IdelResponse> getIdleData(
			@RequestParam(value = "startDate") Date startDate,
			@RequestParam(value = "endDate") Date endDate,
			@RequestParam(value = "userRole") String userRole)
	{		
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {
			List<IdelResponse> list = chartDto.getIdleData(startDate,endDate,userRole);
			return list;
		    
		}catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }	
		    }
		return null;
	}
	
	
	@RequestMapping(value = "/getIdleEmpforHomePage", method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody  List<IdelResponse> getIdleEmpforHomePage(
			@RequestParam(value = "startDate") Date startDate,
			@RequestParam(value = "endDate") Date endDate)
	{		
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {
			List<IdelResponse> list = chartDto.getIdleEmpforHomePage(startDate,endDate);
			return list;
		    
		}catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }	
		    }
		return null;
	}
	
	@RequestMapping(value = "/getworkloadSummaryforHomePage", method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody  List<ChartListResponse> getworkloadSummaryforHomePage(
			@RequestParam(value = "startDate") Date startDate,
			@RequestParam(value = "endDate") Date endDate)
	{		
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {
			List<ChartListResponse> list = chartDto.getworkloadSummaryforHomePage(startDate,endDate);
			return list;
		    
		}catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }	
		    }
		return null;
	}
	
	@RequestMapping(value = "/getBookingEmployeesforHomePage", method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody  List<ChartListResponse> getBookingEmployees(
			@RequestParam(value = "first") Date first,
			@RequestParam(value = "last") Date last)
	{		
		auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		 if(auth.isAuthenticated())
			{
		try {
			List<ChartListResponse> list = chartDto.getBookingEmployeesforHomePage(first,last);
			return list;
		    
		}catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }	
		    }
		return null;
	}

}
