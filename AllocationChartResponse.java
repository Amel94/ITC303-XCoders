/**
 * 
 */
package lk.dialog.ist.reslo.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lk.dialog.ist.reslo.services.entities.Allocation;
import lk.dialog.ist.reslo.services.entities.Employee;
import lk.dialog.ist.reslo.services.entities.Project;
import lk.dialog.ist.reslo.services.util.AllocationStatus;

/**
 * @author Amel_33251
 *
 */
public class AllocationChartResponse implements Serializable {
	/**
	 * 
	 */
	/**
	 * @return the employeeName
	 */
	private static final long serialVersionUID = -2157033074673645732L;

	private String ChartId; 
	private String name; /* projectName */
	private Date actualStart; /* minAllocationDate */
	private Date actualEnd; /* maxAllocationDate */
	private String progressValue; /* employeeName */
	private Date baselineStart;/* allocationDate */
	private Date baselineEnd;
	private int rowHeight;
	private String allocation;/* allocation */
	/* private AllocationStatus actualEnd; allocationStatus */



	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getChartId() {
		return ChartId;
	}

	public void setChartId(String chartId) {
		ChartId = chartId;
	}

	public Date getActualStart() {
		return actualStart;
	}

	public void setActualStart(Date actualStart) {
		this.actualStart = actualStart;
	}

	public Date getActualEnd() {
		return actualEnd;
	}

	public void setActualEnd(Date actualEnd) {
		this.actualEnd = actualEnd;
	}

	public String getProgressValue() {
		return progressValue;
	}

	public void setProgressValue(String progressValue) {
		this.progressValue = progressValue;
	}

	public Date getBaselineStart() {
		return baselineStart;
	}

	public void setBaselineStart(Date baselineStart) {
		this.baselineStart = baselineStart;
	}

	public Date getBaselineEnd() {
		return baselineEnd;
	}

	public void setBaselineEnd(Date baselineEnd) {
		this.baselineEnd = baselineEnd;
	}

	public int getRowHeight() {
		return rowHeight;
	}

	public void setRowHeight(int rowHeight) {
		this.rowHeight = rowHeight;
	}

	
	/**
	 * @return the allocation
	 */
	public String getAllocation() {
		return allocation;
	}

	/**
	 * @param allocation the allocation to set
	 */
	public void setAllocation(String allocation) {
		this.allocation = allocation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AllocationChartResponse [ChartId=" + ChartId + ", name=" + name + ", actualStart=" + actualStart
				+ ", actualEnd=" + actualEnd + ", progressValue=" + progressValue + ", baselineStart=" + baselineStart
				+ ", baselineEnd=" + baselineEnd + ", rowHeight=" + rowHeight + ", allocation=" + allocation + "]";
	}

	
}
