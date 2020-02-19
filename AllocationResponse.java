package lk.dialog.ist.reslo.response;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lk.dialog.ist.reslo.services.entities.Employee;
import lk.dialog.ist.reslo.services.entities.Project;
import lk.dialog.ist.reslo.services.util.AllocationStatus;

public class AllocationResponse implements Serializable {
	
	static final long serialVersionUID = 1L;
	
	
	private Employee employee;
	private Project project;	
	private AllocationStatus allocationStatus;
	private int allocation;
	private Date allocationStartDate;
	private Date allocationEndDate;
	
	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}
	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
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
	 * @return the allocationStartDate
	 */
	public Date getAllocationStartDate() {
		return allocationStartDate;
	}
	/**
	 * @param allocationStartDate the allocationStartDate to set
	 */
	public void setAllocationStartDate(Date allocationStartDate) {
		this.allocationStartDate = allocationStartDate;
	}
	/**
	 * @return the allocationEndDate
	 */
	public Date getAllocationEndDate() {
		return allocationEndDate;
	}
	/**
	 * @param allocationEndDate the allocationEndDate to set
	 */
	public void setAllocationEndDate(Date allocationEndDate) {
		this.allocationEndDate = allocationEndDate;
	}
	/**
	 * @return the allocationStatus
	 */
	public AllocationStatus getAllocationStatus() {
		return allocationStatus;
	}
	/**
	 * @param allocationStatus the allocationStatus to set
	 */
	public void setAllocationStatus(AllocationStatus allocationStatus) {
		this.allocationStatus = allocationStatus;
	}

	
	
	
	
	
	
	
	
}
