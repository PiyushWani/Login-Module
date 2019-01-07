package doa;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import pojo.ErrorMessagesPojo;
import pojo.UserInfoPojo;
import utilities.ErrorMappings;
import utilities.Hash;
import utilities.SqlQueries;

public class PasswordPageDoa 
{
	private static Connection con;
	private CallableStatement cst=null;
	public PasswordPageDoa()
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:system/password-1@localhost");
			System.out.println("PasswordPageDoa: Database Connected");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public void verifyPassword(UserInfoPojo tray, List<ErrorMessagesPojo> errorList) 
	{
		ErrorMappings em = new ErrorMappings();
		Map<String, String> errorMap = em.getErrorMap();
		ErrorMessagesPojo error=null;
		String key;
		Hash h = new Hash();
		
		setSalt(tray);
		h.setHash(tray);
		String verifyPasswordQuery = SqlQueries.getVerifypasswordproc();
		try 
		{
			cst = con.prepareCall(verifyPasswordQuery);
			cst.setString(1, tray.getEmailId());
			cst.setBytes(2, tray.getHash());
			cst.registerOutParameter(3, java.sql.Types.VARCHAR); //firstname
			cst.registerOutParameter(4, java.sql.Types.VARCHAR); //lastname
			cst.registerOutParameter(5, java.sql.Types.INTEGER); //Verify Flag
			cst.executeQuery();
			int verify = cst.getInt(5);
			switch(verify)
			{
			case 0:
				System.out.println("Incorrect Password case 0");
				key = em.getWRONG_PASSWORD();
				error = new ErrorMessagesPojo();
				error.setErrorIdentifier(key);
				error.setErrorDescription(errorMap.get(key));
				errorList.add(error);
				break;
			case 1:
				System.out.println("Correct Password case 1");
				tray.setFirstName(cst.getString(3));
				tray.setLastName(cst.getString(4));
				break;
			default:
				System.out.println("Bug! Procedure verifyPassword should return either 0 or 1 but has returned "+verify);
				break;
			}
			con.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
			key = em.getDATABASE_SQL_EXCEPTION();
			error = new ErrorMessagesPojo();
			error.setErrorIdentifier(key);
			error.setErrorDescription(errorMap.get(key));
			errorList.add(error);
			e.printStackTrace();
		}
	}
	private boolean setSalt(UserInfoPojo tray) 
	{
		boolean set=false;
		String fetchSaltProc = SqlQueries.getFecthsaltproc();
		try 
		{
			cst = con.prepareCall(fetchSaltProc);
			cst.setString(1, tray.getEmailId());
			cst.registerOutParameter(2, java.sql.Types.INTEGER);
			cst.registerOutParameter(3, java.sql.Types.BINARY);
			cst.executeQuery();
			int status = cst.getInt(2);
			switch(status)
			{
			case 0:
				System.out.println("Email should be present in the database but not present.");
				break;
			case 1:
				set=true;
				System.out.println("Salt: "+Arrays.toString(cst.getBytes(3)));
				tray.setSalt(cst.getBytes(3));
				break;
			default:
				System.out.println("Bug! Procedure fetchSalt should return status as 0 or 1 but status is "+status);
				break;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return set;
	}
	/*public void verifyPassword(UserInfoPojo tray, List<ErrorMessagesPojo> errorList) 
	{
		ErrorMappings em = new ErrorMappings();
		Map<String, String> errorMap = em.getErrorMap();
		ErrorMessagesPojo error=null;
		String key;
		String verifyPasswordQuery = SqlQueries.getVerifyPasswordQuery(tray.getEmailId(), tray.getPassword());                                                                           
		System.out.println("VerifyPasswordQuery: "+verifyPasswordQuery);
		ResultSet rs=null;
		try 
		{
			st = con.createStatement();
			rs = st.executeQuery(verifyPasswordQuery);
			
			if(rs.next())
			{
				tray.setFirstName(rs.getString(1));
				tray.setLastName(rs.getString(2));
			}
			else
			{
				key = em.getWRONG_PASSWORD();
				error = new ErrorMessagesPojo();
				error.setErrorIdentifier(key);
				error.setErrorDescription(errorMap.get(key));
				errorList.add(error);
			}
			
		} catch (SQLException e) 
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
