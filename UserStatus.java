
package lk.dialog.ist.reslo.services.Config;

/**
 * This represents User Status
 *
 */
public enum UserStatus {
	
	INACTIVE("Inactive"), 
	ACTIVE("Active"), 
	BLOCKED("Blocked"),
	NOT_REGISTERED_USER("The user is not a registered user."), 
	NOT_AUTHORISED_USER("User is not authorised to NFC Settlement System"), 
	NOT_ACTIVE_USER("Your account has been inactive"),
	BAD_CREDENTIALS("Bad credentials"),
	INVALID_CREDENTIALS("Invalid user name or password. Please re-enter."),
	LOGOUT_SUCCESS("You have successfully logged out..!"),
	SESSION_EXPIRED("Session expired. Please login..!");
 
	private String status;
 
	private UserStatus(String s) {
		status = s;
	}
 
	public String getMsg() {
		return status;
	}
	
}
