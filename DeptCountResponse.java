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

public class DeptCountResponse implements Serializable {
	
	static final long serialVersionUID = 1L;
	
	
	private int devCount;
	private int qaCount;	
	private int baCount;
	private int managementCount;
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
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeptCountResponse [devCount=" + devCount + ", qaCount=" + qaCount + ", baCount=" + baCount
				+ ", managementCount=" + managementCount + "]";
	}
	
	

	
	
	
	
	
	
	
	
	
}
