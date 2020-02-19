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
public class ChartListResponse implements Serializable {
	/**
	 * 
	 */	/**
	 * @return the employeeName
	 */
	private static final long serialVersionUID = -2157033074673645732L;
	
	private String id;
	private String name;
	private String crNo;
	private String status;
	private Date actualStart;
	private Date actualEnd;
	private String progressValue;
	private Date baselineStart;
	private Date baselineEnd;
	private int rowHeight;
	private Date actualStartTrue;
	private Date actualEndTrue;
	private Date baselineStartTrue;
	private Date baselineEndTrue;
	private int allocation;
	private String empName;
	private String empRole;

	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the actualStart
	 */
	public Date getActualStart() {
		return actualStart;
	}
	/**
	 * @param actualStart the actualStart to set
	 */
	public void setActualStart(Date actualStart) {
		this.actualStart = actualStart;
	}
	/**
	 * @return the actualEnd
	 */
	public Date getActualEnd() {
		return actualEnd;
	}
	/**
	 * @param actualEnd the actualEnd to set
	 */
	public void setActualEnd(Date actualEnd) {
		this.actualEnd = actualEnd;
	}
	/**
	 * @return the progressValue
	 */
	public String getProgressValue() {
		return progressValue;
	}
	/**
	 * @param progressValue the progressValue to set
	 */
	public void setProgressValue(String progressValue) {
		this.progressValue = progressValue;
	}
	/**
	 * @return the baselineStart
	 */
	public Date getBaselineStart() {
		return baselineStart;
	}
	/**
	 * @param baselineStart the baselineStart to set
	 */
	public void setBaselineStart(Date baselineStart) {
		this.baselineStart = baselineStart;
	}
	/**
	 * @return the baselineEnd
	 */
	public Date getBaselineEnd() {
		return baselineEnd;
	}
	/**
	 * @param baselineEnd the baselineEnd to set
	 */
	public void setBaselineEnd(Date baselineEnd) {
		this.baselineEnd = baselineEnd;
	}
	
	
	
	/**
	 * @return the rowHeight
	 */
	public int getRowHeight() {
		return rowHeight;
	}
	/**
	 * @param rowHeight the rowHeight to set
	 */
	public void setRowHeight(int rowHeight) {
		this.rowHeight = rowHeight;
	}
	
	
	
	/**
	 * @return the actualStartTrue
	 */
	public Date getActualStartTrue() {
		return actualStartTrue;
	}
	/**
	 * @param actualStartTrue the actualStartTrue to set
	 */
	public void setActualStartTrue(Date actualStartTrue) {
		this.actualStartTrue = actualStartTrue;
	}
	/**
	 * @return the actualEndTrue
	 */
	public Date getActualEndTrue() {
		return actualEndTrue;
	}
	/**
	 * @param actualEndTrue the actualEndTrue to set
	 */
	public void setActualEndTrue(Date actualEndTrue) {
		this.actualEndTrue = actualEndTrue;
	}
	/**
	 * @return the baselineStartTrue
	 */
	public Date getBaselineStartTrue() {
		return baselineStartTrue;
	}
	/**
	 * @param baselineStartTrue the baselineStartTrue to set
	 */
	public void setBaselineStartTrue(Date baselineStartTrue) {
		this.baselineStartTrue = baselineStartTrue;
	}
	/**
	 * @return the baselineEndTrue
	 */
	public Date getBaselineEndTrue() {
		return baselineEndTrue;
	}
	/**
	 * @param baselineEndTrue the baselineEndTrue to set
	 */
	public void setBaselineEndTrue(Date baselineEndTrue) {
		this.baselineEndTrue = baselineEndTrue;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChartListResponse [id=" + id + ", name=" + name + ", crNo=" + crNo + ", status=" + status
				+ ", actualStart=" + actualStart + ", actualEnd=" + actualEnd + ", progressValue=" + progressValue
				+ ", baselineStart=" + baselineStart + ", baselineEnd=" + baselineEnd + ", rowHeight=" + rowHeight
				+ ", actualStartTrue=" + actualStartTrue + ", actualEndTrue=" + actualEndTrue + ", baselineStartTrue="
				+ baselineStartTrue + ", baselineEndTrue=" + baselineEndTrue + ", allocation=" + allocation
				+ ", empName=" + empName + ", empRole=" + empRole + "]";
	}
	
	

	

	


}
