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

public class ProjectWiseResponse implements Serializable {
	
	static final long serialVersionUID = 1L;
	
	
	private int devCount;
	private int qaCount;	
	private int baCount;
	private int managementCount;
	private Employee employee;
	private Project project;	
	private int allocation;
	/**
	 * @return the devCount
	 */
	public int getDevCount() {
		return devCount;
	}
	/**
	 * @param devCount the devCount to set
	 */
	public void setDevCount(int devCount) {
		this.devCount = devCount;
	}
	/**
	 * @return the qaCount
	 */
	public int getQaCount() {
		return qaCount;
	}
	/**
	 * @param qaCount the qaCount to set
	 */
	public void setQaCount(int qaCount) {
		this.qaCount = qaCount;
	}
	/**
	 * @return the baCount
	 */
	public int getBaCount() {
		return baCount;
	}
	/**
	 * @param baCount the baCount to set
	 */
	public void setBaCount(int baCount) {
		this.baCount = baCount;
	}
	/**
	 * @return the managementCount
	 */
	public int getManagementCount() {
		return managementCount;
	}
	/**
	 * @param managementCount the managementCount to set
	 */
	public void setManagementCount(int managementCount) {
		this.managementCount = managementCount;
	}
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
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProjectWiseResponse [devCount=" + devCount + ", qaCount=" + qaCount + ", baCount=" + baCount
				+ ", managementCount=" + managementCount + ", employee=" + employee + ", project=" + project
				+ ", allocation=" + allocation + "]";
	}
	
	
	

	
	
	
	
	
	
	
	
	
}
