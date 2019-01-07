package doa;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import utilities.SqlQueries;

public class WelcomePageDoa 
{
	static Connection con;
	Statement st=null;
	CallableStatement cst=null;
	public WelcomePageDoa()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:system/password-1@localhost");
			System.out.println("Connection to SQL established");
			st = con.createStatement();
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Class Not Found Exception");
		}
		catch(SQLException e)
		{
			System.out.println("SQL Exception");
			e.printStackTrace();
		}
	}
	
	public int registeredUser(String emailId) 
	{
		int returnCode=300;
		String procedure = SqlQueries.getEmailidcheckproc();		
		try 
		{
			int count = -1;
			cst = con.prepareCall(procedure);
			cst.setString(1, emailId);
			cst.registerOutParameter(2, java.sql.Types.INTEGER);
			cst.executeQuery();
			count = cst.getInt(2);
			switch(count)
			{
			case 1:
				returnCode=202;
				break;
			case 0:
				returnCode=201;
				break;
			default:
				System.out.println("Bug spotted! The count of emailId "+emailId+" should be 1 or 0 but is "+count+"."); 
				break;
			}			
			con.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("SQL Exception 123");
			e.printStackTrace();
		}
		return returnCode;
	}
	
	/*public int registeredUser(String emailId) 
	{
		System.out.println("In registeredUser()");
		int returnCode = 300;
		String searchQuery = SqlQueries.getEmailidsearchquery(emailId);
		System.out.println("Search Query: "+searchQuery);
		try {
			ResultSet rs = st.executeQuery(searchQuery);
			if(rs.next())
			{
				returnCode = 202;
			}
			else
			{
				returnCode = 201;
			}
			con.close();
		} catch (SQLException e) 
		{
			System.out.println("SQLException");
			e.printStackTrace();
		}
		
		return returnCode;
	}*/
	
}