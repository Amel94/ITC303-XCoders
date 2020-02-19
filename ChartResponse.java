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
public class ChartResponse implements Serializable {
	/**
	 * 
	 */	/**
	 * @return the employeeName
	 */
	private static final long serialVersionUID = -2157033074673645732L;
	
	private int newProjectCount;
	private int inProgressCount;
	private int onHoldCount;
	private int closeCount;
	private List<ChartListResponse> chartList;
	private List<Date> AllocationDateList;
	
	
	/**
	 * @return the inProgressCount
	 */
	public int getInProgressCount() {
		return inProgressCount;
	}
	/**
	 * @param inProgressCount the inProgressCount to set
	 */
	public void setInProgressCount(int inProgressCount) {
		this.inProgressCount = inProgressCount;
	}
	/**
	 * @return the onHoldCount
	 */
	public int getOnHoldCount() {
		return onHoldCount;
	}
	/**
	 * @param onHoldCount the onHoldCount to set
	 */
	public void setOnHoldCount(int onHoldCount) {
		this.onHoldCount = onHoldCount;
	}
	/**
	 * @return the closeCount
	 */
	public int getCloseCount() {
		return closeCount;
	}
	/**
	 * @param closeCount the closeCount to set
	 */
	public void setCloseCount(int closeCount) {
		this.closeCount = closeCount;
	}
	/**
	 * @return the chartList
	 */
	public List<ChartListResponse> getChartList() {
		return chartList;
	}
	/**
	 * @param chartList the chartList to set
	 */
	public void setChartList(List<ChartListResponse> chartList) {
		this.chartList = chartList;
	}
	/**
	 * @return the newProjectCount
	 */
	public int getNewProjectCount() {
		return newProjectCount;
	}
	/**
	 * @param newProjectCount the newProjectCount to set
	 */
	public void setNewProjectCount(int newProjectCount) {
		this.newProjectCount = newProjectCount;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChartResponse [newProjectCount=" + newProjectCount + ", inProgressCount=" + inProgressCount
				+ ", onHoldCount=" + onHoldCount + ", closeCount=" + closeCount + ", chartList=" + chartList
				+ ", AllocationDateList=" + AllocationDateList + "]";
	}
	

	



		
	
	

	


}
