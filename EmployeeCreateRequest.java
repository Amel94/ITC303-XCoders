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
public class EmployeeCreateRequest implements Serializable {
	/**
	 * 
	 */	/**
	 * @return the employeeName
	 */
	private static final long serialVersionUID = -2157033074673645732L;
	private String empId;
	private String empName;
	private String mobile;
	private String empRole;
	private String createdUser;
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
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the empRole
	 */
	public String getEmpRole() {
		return empRole;
	}
	/**
	 * @param empRole the empRole to set
	 */
	public void setEmpRole(String empRole) {
		this.empRole = empRole;
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
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmployeeCreateRequest [empId=" + empId + ", empName=" + empName + ", mobile=" + mobile + ", empRole="
				+ empRole + ", createdUser=" + createdUser + "]";
	}

	
	
	

	
	

}
