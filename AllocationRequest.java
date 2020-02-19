/**
 * 
 */
package lk.dialog.ist.reslo.request;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Amel_33251
 *
 */
public class AllocationRequest implements Serializable {
	/**
	 * 
	 */	/**
	 * @return the employeeName
	 */
	private static final long serialVersionUID = -2157033074673645732L;
	private String projectName;
	private String crNo;
	private String empId;
	private List<Date> allocationDate;
	private int allocation;
	private String createdUser;
	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * @return the crNo
	 */
	public String getCrNo() {
		return crNo;
	}
	/**
	 * @param crNo the crNo to set
	 */
	public void setCrNo(String crNo) {
		this.crNo = crNo;
	}
	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	/**
	 * @return the allocation
	 */
	public int getAllocation() {
		return allocation;
	}
	/**
	 * @param allocation the allocation to set
	 */
	public void setAllocation(int allocation) {
		this.allocation = allocation;
	}
	/**
	 * @return the createdUser
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser the createdUser to set
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	
	
	/**
	 * @return the allocationDate
	 */
	public List<Date> getAllocationDate() {
		return allocationDate;
	}
	/**
	 * @param allocationDate the allocationDate to set
	 */
	public void setAllocationDate(List<Date> allocationDate) {
		this.allocationDate = allocationDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AllocationAddRequest [projectName=" + projectName + ", crNo=" + crNo + ", empId=" + empId
				+ ", allocationDate=" + allocationDate + ", allocation=" + allocation + ", createdUser=" + createdUser
				+ "]";
	}
	
	
	
	

}
