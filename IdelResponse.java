/**
 * 
 */
package lk.dialog.ist.reslo.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Amel_33251
 *
 */
public class IdelResponse implements Serializable {
	/**
	 * 
	 */	/**
	 * @return the employeeName
	 */
	private static final long serialVersionUID = -2157033074673645732L;
	
	private String employeeName;
	private String employeeRole;
	private List<Date> AllocationDateList;
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the allocationDateList
	 */
	public List<Date> getAllocationDateList() {
		return AllocationDateList;
	}
	/**
	 * @param allocationDateList the allocationDateList to set
	 */
	public void setAllocationDateList(List<Date> allocationDateList) {
		AllocationDateList = allocationDateList;
	}
	/**
	 * @return the employeeRole
	 */
	public String getEmployeeRole() {
		return employeeRole;
	}
	/**
	 * @param employeeRole the employeeRole to set
	 */
	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IdelResponse [employeeName=" + employeeName + ", employeeRole=" + employeeRole + ", AllocationDateList="
				+ AllocationDateList + "]";
	}
	
	
	
	



}
