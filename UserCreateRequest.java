/**
 * 
 */
package lk.dialog.ist.reslo.request;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Amel_33251
 *
 */
public class UserCreateRequest implements Serializable {
	/**
	 * 
	 */	/**
	 * @return the employeeName
	 */
	private static final long serialVersionUID = -2157033074673645732L;
	private String userName;
	private String createdUser;
	private String employeeName;
	private String contactNo;
	private String email;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}
	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserCreateRequest [userName=" + userName + ", createdUser=" + createdUser + ", employeeName="
				+ employeeName + ", contactNo=" + contactNo + ", email=" + email + "]";
	}

	
	

}
