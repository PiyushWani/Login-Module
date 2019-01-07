package pojo;

import java.util.List;

public class UserInfoPojo 
{
	private String firstName;
	private String lastName;
	private String password;
	private String ConfirmPassword;
	private String emailId;
	//private String hash;
	private byte[] hash;
	private byte[] salt;
	private Integer ActionSuccess;
	private List<Integer> errorList;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return ConfirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		ConfirmPassword = confirmPassword;
	}

	public Integer getActionSuccess() {
		return ActionSuccess;
	}

	public void setActionSuccess(Integer actionSuccess) {
		ActionSuccess = actionSuccess;
	}

	public List<Integer> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<Integer> errorList) {
		this.errorList = errorList;
	}

	public byte[] getHash() {
		return hash;
	}

	public void setHash(byte[] hash) {
		this.hash = hash;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}


	

}
