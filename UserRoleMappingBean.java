/**
 * 
 */
package lk.dialog.ist.reslo.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author amel_inova
 *
 */
public class UserRoleMappingBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6864962073174610278L;
	private int userId;
	private int roleID;
	private String roleName;
	private int status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date addedDate;
	private String addedUser;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date updatedDate;
	private String updatedUser;

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the roleID
	 */
	public int getRoleID() {
		return roleID;
	}

	/**
	 * @param roleID
	 *            the roleID to set
	 */
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the addedDate
	 */
	public Date getAddedDate() {
		return addedDate;
	}

	/**
	 * @param addedDate
	 *            the addedDate to set
	 */
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	/**
	 * @return the addedUser
	 */
	public String getAddedUser() {
		return addedUser;
	}

	/**
	 * @param addedUser
	 *            the addedUser to set
	 */
	public void setAddedUser(String addedUser) {
		this.addedUser = addedUser;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the updatedUser
	 */
	public String getUpdatedUser() {
		return updatedUser;
	}

	/**
	 * @param updatedUser
	 *            the updatedUser to set
	 */
	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRoleMappingBean [userId=" + userId + ", roleID=" + roleID + ", roleName=" + roleName + ", status="
				+ status + ", addedDate=" + addedDate + ", addedUser=" + addedUser + ", updatedDate=" + updatedDate
				+ ", updatedUser=" + updatedUser + "]";
	}




}
