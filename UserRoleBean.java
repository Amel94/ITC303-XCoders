/**
 * 
 */
package lk.dialog.ist.reslo.beans;

import java.io.Serializable;

/**
 * @author amel_inova
 *
 */
public class UserRoleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 99207150050126060L;
	private int userId;
	private int roleID;
	private String roleName;
	private int status;
	private int parentRoleId;

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
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public int getParentRoleId() {
		return parentRoleId;
	}

	public void setParentRoleId(int parentRoleId) {
		this.parentRoleId = parentRoleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserRoleBean [userId=").append(userId)
				.append(", roleID=").append(roleID).append(", roleName=")
				.append(roleName).append(", status=").append(status)
				.append(", parentRoleId=").append(parentRoleId).append("]");
		return builder.toString();
	}

}

