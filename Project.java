package lk.dialog.ist.reslo.services.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lk.dialog.ist.reslo.services.util.ProjectStatus;


@Entity
@Table(name = "PROJECT")
@IdClass(ProjectPK.class)
public class Project implements Serializable {
	
	
	static final long serialVersionUID = 1L;
	

	@Id
	private String projectName;
	@Id
	private String crNo;
	
	@Column(name="START_DATE")
	private Date startDate;
	
	@Column(name="END_DATE")
	private Date endDate;
	
	@Column(name="ACTUAL_END_DATE")
	private Date actualEndDate;
	
	@Column(name="ACTUAL_START_DATE")
	private Date actualStartDate;
	
	@Column(name="PROJECT_STATUS")
	private String projectStatus;

	@Column(name="REASON")
	private String reason;
	
	@Column(name="ADDED_ON")
	private Date addedOn;
	
	@Column(name="ADDED_BY")
	private String addedBy;
	
	@Column(name="UPDATED_ON")
	private Date updatedOn;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="PROJECT_PROGRESS")
	private String projectProgress;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    private List<Allocation> allocation = new ArrayList<>();

	
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
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the actualStartDate
	 */
	public Date getActualStartDate() {
		return actualStartDate;
	}

	/**
	 * @param actualStartDate the actualStartDate to set
	 */
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	/**
	 * @return the actualEndDate
	 */
	public Date getActualEndDate() {
		return actualEndDate;
	}

	/**
	 * @param actualEndDate the actualEndDate to set
	 */
	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	/**
	 * @return the projectStatus
	 */
	public String getProjectStatus() {
		return projectStatus;
	}

	/**
	 * @param projectStatus the projectStatus to set
	 */
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
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
	 * @return the projectProgress
	 */
	public String getProjectProgress() {
		return projectProgress;
	}

	/**
	 * @param projectProgress the projectProgress to set
	 */
	public void setProjectProgress(String projectProgress) {
		this.projectProgress = projectProgress;
	}

	

	
}
