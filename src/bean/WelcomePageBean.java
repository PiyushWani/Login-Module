package bean;

import java.util.regex.Pattern;

import doa.WelcomePageDoa;

public class WelcomePageBean 
{

	public int verifyEmailId(String emailId) 
	{		
		int returnCode = 300;
		WelcomePageDoa wpd = null;
		if(isValidFormat(emailId))
		{
			System.out.println("In verifyEmailId() - "+emailId+" : Email is valid");
			wpd = new WelcomePageDoa();
			int statusCode = wpd.registeredUser(emailId);
			switch(statusCode)
			{
				case 201: //EmailId not Present
					returnCode = statusCode;
					break;
				case 202: //EmailId present
					returnCode = statusCode;
					break;
				default:
					returnCode = 300; //Exception or error occured
					break;
			}
			
		}
		else
		{
			
			returnCode = 200; //Invalid EmailID Format
		}
		return returnCode;
	}

	private boolean isValidFormat(String emailId) 
	{
		boolean valid = false;
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$";                   
		Pattern pat = Pattern.compile(emailRegex); 
		if (emailId.isEmpty()) 
		{
			valid =  false; 
		}
		else
		{
			valid = pat.matcher(emailId).matches();
		}	
		return valid;
	}
	
}
