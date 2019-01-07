package doa;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import pojo.ErrorMessagesPojo;
import pojo.UserInfoPojo;
import utilities.ErrorMappings;
import utilities.Hash;
import utilities.SqlQueries;

public class NewUserFormDoa 
{
	static Connection con;
	Statement st=null;
	CallableStatement cst=null;
	public NewUserFormDoa()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:system/password-1@localhost");
			System.out.println("NewUserFormData- Connection Established");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Class Not Found Exception.");
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			System.out.println("SQL Exception.");
			e.printStackTrace();
		}
	}
	
	
	
	public void insertCustomerEntry(UserInfoPojo tray, List<ErrorMessagesPojo> errorList) 
	{
		Hash h = new Hash();
		h.saltAndHash(tray);
		
		ErrorMessagesPojo error=null;
		ErrorMappings em = new ErrorMappings();
		Map<String, String> errorMap = em.getErrorMap();
		String key;
		//tray.setHash("hashGenesisPending");
		
		String insertCustomerEntry = SqlQueries.getInsertcustomerentryproc();
		try
		{
			cst = con.prepareCall(insertCustomerEntry);
			cst.setString(1, tray.getEmailId());
			cst.setString(2, tray.getFirstName());
			cst.setString(3, tray.getLastName());
			cst.setBytes(4, tray.getHash());
			cst.setBytes(5, tray.getSalt());
			cst.registerOutParameter(6, java.sql.Types.INTEGER); // status
			cst.executeUpdate();
			
			int status = cst.getInt(6);
			System.out.println("Status: "+status);
			switch(status)
			{
			case 1:
				System.out.println("New User Entry Inserted");
				break;
			default:
				System.out.println("Bug! the status from insertCustomerEntry must be 1 but is "+status+".");
				break;
			}
			con.close();
		}
		catch(SQLException e)
		{
			key = em.getDATABASE_SQL_EXCEPTION();
			error = new ErrorMessagesPojo();
			error.setErrorIdentifier(key);
			error.setErrorDescription(errorMap.get(key));
			errorList.add(error);
			e.printStackTrace();
		}
	}
	
	/*public void insertCustomerEntry(UserInfoPojo tray, List<ErrorMessagesPojo> errorList) 
	{
			
		ErrorMessagesPojo error=null;
		ErrorMappings em = new ErrorMappings();
		Map<String, String> errorMap = em.getErrorMap();
		String key;
		tray.setHash("hashGenesisPending");
		
		String insertCustomerEntry = SqlQueries.getInsertcustomerentryproc();
		try
		{
			cst = con.prepareCall(insertCustomerEntry);
			cst.setString(1, tray.getEmailId());
			cst.setString(2, tray.getFirstName());
			cst.setString(3, tray.getLastName());
			cst.setString(4, tray.getPassword());
			cst.setString(5, tray.getHash());
			cst.registerOutParameter(6, java.sql.Types.INTEGER); // status
			cst.executeUpdate();
			
			int status = cst.getInt(6);
			System.out.println("Status: "+status);
			switch(status)
			{
			case 1:
				System.out.println("New User Entry Inserted");
				break;
			default:
				System.out.println("Bug! the status from insertCustomerEntry must be 1 but is "+status+".");
				break;
			}
			con.close();
		}
		catch(SQLException e)
		{
			key = em.getDATABASE_SQL_EXCEPTION();
			error = new ErrorMessagesPojo();
			error.setErrorIdentifier(key);
			error.setErrorDescription(errorMap.get(key));
			errorList.add(error);
			e.printStackTrace();
		}
	}*/
/*	public void insertCustomerEntry(UserInfoPojo tray, List<ErrorMessagesPojo> errorList) 
	{
		ErrorMessagesPojo error=null;
		ErrorMappings em = new ErrorMappings();
		Map<String, String> errorMap = em.getErrorMap();
		String key;
		tray.setHash("hashGenesisPending"); //Generate unique hash for each user.
		
		String insertCustomerPassword = SqlQueries.getInsertCustomerPassword(tray);
		String insertCustomerEntry = SqlQueries.getInsertCustomerEntry(tray);
		
		System.out.println("insertCustomerPassword: "+insertCustomerPassword);
		System.out.println("insertCustomerEntry: "+insertCustomerEntry);
		
		try 
		{
			st = con.createStatement();		
			int ICPRowsAffected = st.executeUpdate(insertCustomerPassword);
			int ICERowsAffected = st.executeUpdate(insertCustomerEntry);
			if(ICERowsAffected == 1 && ICPRowsAffected == 1)
			{
				System.out.println("Entry Inserted for "+tray.getFirstName()+" "+tray.getLastName());
			}
			else if(ICERowsAffected==0 || ICPRowsAffected==0) //If any one of the two statements don't execute, display error message to user and report in the table 
			{
				//Undo commit;
				key = em.getDATABASE_CROSS_VALIDATION_FAILURE();
				error = new ErrorMessagesPojo();
				error.setErrorIdentifier(key);
				error.setErrorDescription(errorMap.get(key));
				errorList.add(error);
				System.out.println("Only one inserted statement executed update. (ICE | ICP):("+ICERowsAffected+" | "+ICPRowsAffected+")");
			}
			else
			{
				System.out.println("NewUseFormDoa - rowsAffected should be 0 or 1 but are "+ICERowsAffected+" "+ICPRowsAffected);				
			}
			st.execute("commit");
			con.close();
		} 
		catch (SQLException e) 
		{
			key = em.getDATABASE_SQL_EXCEPTION();
			error = new ErrorMessagesPojo();
			error.setErrorIdentifier(key);
			error.setErrorDescription(errorMap.get(key));
			errorList.add(error);
			e.printStackTrace();		
		}	
	}*/
	
}
