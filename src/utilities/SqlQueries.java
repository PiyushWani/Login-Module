package utilities;

import pojo.UserInfoPojo;

public class SqlQueries 
{
	private static String emailIdSearchQuery;
	private static String verifyPasswordQuery;
	private static String insertCustomerEntry;
	private static String insertCustomerPassword;
	
	
	private static final String EMAILIDCHECKPROC="{CALL  emailIdCheck (?,?)}"; //2 ?'s
	private static final String INSERTCUSTOMERENTRYPROC="{CALL insertCustomerEntry (?,?,?,?,?,?)}"; //6 ?'s
	private static final String VERIFYPASSWORDPROC="{CALL verifyPassword(?,?,?,?,?)}"; //5 ?'s
	private static final String FECTHSALTPROC="CALL fetchSalt(?, ?, ?)"; //3?'s
	
	public static String getEmailidsearchquery(String emailId) 
	{
		emailIdSearchQuery ="SELECT * FROM user_info_table WHERE emailid = '"+emailId+"'";
		return emailIdSearchQuery;
	}

	public static String getVerifyPasswordQuery(String emailId, String password) 
	{
		verifyPasswordQuery = "SELECT I.firstname, I.lastname FROM user_info_table I INNER JOIN password_table P ON I.emailid = P.emailid WHERE I.emailid='"+emailId+"' AND P.password='"+password+"'";
		return verifyPasswordQuery;
	}

	public static String getInsertCustomerEntry(UserInfoPojo tray)
	{
		insertCustomerEntry = "INSERT INTO user_info_table VALUES ('"+tray.getEmailId()+"', '"+tray.getFirstName()+"', '"+tray.getLastName()+"')";
		return insertCustomerEntry;
	}

	public static String getInsertCustomerPassword(UserInfoPojo tray) 
	{
		insertCustomerPassword= "INSERT INTO password_table VALUES('"+tray.getEmailId()+"', '"+tray.getPassword()+"', '"+tray.getHash()+"')";
		return insertCustomerPassword;
	}

	
	public static String getEmailidcheckproc() {
		return EMAILIDCHECKPROC;
	}
	public static String getInsertcustomerentryproc() {
		return INSERTCUSTOMERENTRYPROC;
	}

	public static String getVerifypasswordproc() {
		return VERIFYPASSWORDPROC;
	}

	public static String getFecthsaltproc() {
		return FECTHSALTPROC;
	}	
}
