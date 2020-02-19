/**
 * 
 */
package lk.dialog.ist.reslo.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author amel_inova
 *
 */
public class AdminUserBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2157033074673645732L;
	private int userId;
	private String userName;
	private String name;
	private String email;
	private Date lastLogin;
	private Date addedDate;
	private String addedUser;
	private Date updatedDate;
	private String updatedUser;
	private int status;
	List<UserRoleMappingBean> userRoleBeans;
	private byte[] photo;
	private String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin
	 *            the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
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
	 * @return the userRoleBeans
	 */
	public List<UserRoleMappingBean> getUserRoleBeans() {
		return userRoleBeans;
	}

	/**
	 * @param userRoleBeans
	 *            the userRoleBeans to set
	 */
	public void setUserRoleBeans(List<UserRoleMappingBean> userRoleBeans) {
		this.userRoleBeans = userRoleBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdminUserBean [userId=").append(userId)
				.append(", userName=").append(userName).append(", name=")
				.append(name).append(", email=").append(email)
				.append(", lastLogin=").append(lastLogin)
				.append(", addedDate=").append(addedDate)
				.append(", addedUser=").append(addedUser)
				.append(", updatedDate=").append(updatedDate)
				.append(", updatedUser=").append(updatedUser)
				.append(", status=").append(status).append(", userRoleBeans=")
				.append(userRoleBeans).append("]");
		return builder.toString();
	}

}
