package lk.dialog.ist.reslo.services.entities;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lk.dialog.ist.reslo.services.util.AllocationStatus;


@Entity
@Table(name = "ALLOCATION")

public class Allocation implements Serializable {
	
	static final long serialVersionUID = 1L;
	
	
	@Id
	@GenericGenerator(name = "allocation_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "allocation_id_seq"),
			@org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1"), })
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allocation_seq")
	@Column(name="ALLOCATION_ID")
	private long id;

	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "EMP_ID", nullable = false)
	private Employee employee;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({
	    @JoinColumn(name="PROJECT_NAME" , referencedColumnName="projectName", nullable = false),
	    @JoinColumn(name="CR_NO", referencedColumnName="crNo", nullable = false)
	})
	private Project project;
	
	@Column(name="ALLOCATION_DATE")
	private Date allocationDate;
	
	@Column(name="ALLOCATION")
	private int allocation;
	
	@Column(name="ADDED_ON")
	private Date addedOn;
	
	@Column(name="ADDED_BY")
	private String addedBy;
	
	@Column(name="UPDATED_ON")
	private Date updatedOn;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@JoinColumn(name="ALLOCATION_STATUS")
	@Enumerated(EnumType.ORDINAL)
	private AllocationStatus allocationStatus;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the allocationDate
	 */
	public Date getAllocationDate() {
		return allocationDate;
	}

	/**
	 * @param allocationDate the allocationDate to set
	 */
	public void setAllocationDate(Date allocationDate) {
		this.allocationDate = allocationDate;
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
