package utilities;

import java.util.HashMap;
import java.util.Map;
public class ErrorMappings 
{
	private final String PASSWORDS_MISMATCH = "PASSWORDS_MISMATCH";  
	private final String INVALID_EMAIL_FORMAT = "INVALID_EMAIL_FORMAT";
	private final String EMAILID_ABSENT = "EMAIL_ABSENT";
	private final String INVALID_FIRSTNAME = "INVALID_FIRSTNAME";
	private final String INVALID_LASTNAME = "INVALID_LASTNAME";
	private final String WRONG_PASSWORD = "WRONG_PASSWORD";
	private final String DATABASE_SQL_EXCEPTION = "DATABASE_SQL_EXCEPTION";
	private final String DATABASE_CLASS_EXCEPTION = "DATABASE_CLASS_EXCEPTION";
	private final String DATABASE_CROSS_VALIDATION_FAILURE = "DATABASE_CROSS_VALIDATION_FAILURE";	
	private final String BAD_PASSWORD = "BAD_PASSWORD";
	private final Map<String, String> errorMap = new HashMap<>();
	
	public Map<String, String> getErrorMap()
	{		
		errorMap.put(INVALID_EMAIL_FORMAT, "Invalid Email ID");
		errorMap.put(EMAILID_ABSENT, "Email ID not registered");
		errorMap.put(INVALID_FIRSTNAME, "First name should contain only alphabets");
		errorMap.put(INVALID_LASTNAME, "Last name should contain only alphabets");
		errorMap.put(WRONG_PASSWORD, "Incorrect Password entered");
		errorMap.put(BAD_PASSWORD, "Space/tabs are not allowed");
		errorMap.put(PASSWORDS_MISMATCH, "Passwords do not match");
		errorMap.put(DATABASE_SQL_EXCEPTION, "Something went wrong on our server. Please Try after some time");
		errorMap.put(DATABASE_CLASS_EXCEPTION, "Something went wrong on our server. Please Try after some time");
		errorMap.put(DATABASE_CROSS_VALIDATION_FAILURE, "Something went wrong on our server. Please Try after some time");
		
		return errorMap;
	}

	public String getPASSWORDS_MISMATCH() {
		return PASSWORDS_MISMATCH;
	}

	public String getINVALID_EMAIL_FORMAT() {
		return INVALID_EMAIL_FORMAT;
	}

	public String getEMAILID_ABSENT() {
		return EMAILID_ABSENT;
	}

	public String getINVALID_FIRSTNAME() {
		return INVALID_FIRSTNAME;
	}

	public String getINVALID_LASTNAME() {
		return INVALID_LASTNAME;
	}

	public String getWRONG_PASSWORD() {
		return WRONG_PASSWORD;
	}

	public String getDATABASE_SQL_EXCEPTION() {
		return DATABASE_SQL_EXCEPTION;
	}

	public String getDATABASE_CLASS_EXCEPTION() {
		return DATABASE_CLASS_EXCEPTION;
	}

	public String getDATABASE_CROSS_VALIDATION_FAILURE() {
		return DATABASE_CROSS_VALIDATION_FAILURE;
	}

	public String getBAD_PASSWORD() {
		return BAD_PASSWORD;
	}
	
}
