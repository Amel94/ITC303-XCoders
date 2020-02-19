package lk.dialog.ist.reslo.services.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lk.dialog.ist.reslo.services.util.EmployeeStatus;


@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {
	
	static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="EMP_ID", unique = true, nullable = false)
	private String empId;
	
	@Column(name="EMP_NAME")
	private String empName;
	
	@Column(name="CONTACT_NO")
	private String contactNo;
	
	@Column(name="USER_ROLE")
	private String userRole;
	
	@Column(name="TOTAL_ALLOCATION")
	private int total_Allocation;
	
	@Column(name="ADDED_ON")
	private Date addedOn;
	
	@Column(name="ADDED_BY")
	private String addedBy;
	
	@Column(name="UPDATED_ON")
	private Date updatedOn;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@JoinColumn(name="EMPLOYEE_STATUS")
	@Enumerated(EnumType.ORDINAL)
	private EmployeeStatus employeeStatus;
	
	@OneToMany(mappedBy = "employee")    
	private List<Allocation> allocation = new ArrayList<>();

	/**
	 * @return the allocation
	 */
	public List<Allocation> getAllocation() {
		return allocation;
	}

	/**
	 * @param allocation the allocation to set
	 */
	public void setAllocation(List<Allocation> allocation) {
		this.allocation = allocation;
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
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}



	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	/**
	 * @return the total_Allocation
	 */
	public int getTotal_Allocation() {
		return total_Allocation;
	}

	/**
	 * @param total_Allocation the total_Allocation to set
	 */
	public void setTotal_Allocation(int total_Allocation) {
		this.total_Allocation = total_Allocation;
	}

	/**
	 * @return the addedOn
	 */
	public Date getAddedOn() {
		return addedOn;
	}

	/**
	 * @param addedOn the addedOn to set
	 */
	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	/**
	 * @return the addedBy
	 */
	public String getAddedBy() {
		return addedBy;
	}

	/**
	 * @param addedBy the addedBy to set
	 */
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	/**
	 * @return the updatedOn
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the employeeStatus
	 */
	public EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}

	/**
	 * @param employeeStatus the employeeStatus to set
	 */
	public void setEmployeeStatus(EmployeeStatus employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	

	
	
	

	

	
}
