package bean;
import java.util.List;
import java.util.Map;
import doa.PasswordPageDoa;
import pojo.ErrorMessagesPojo;
import pojo.UserInfoPojo;
import utilities.ErrorMappings;

public class PasswordPagebean 
{
	public void verifyPassword(UserInfoPojo tray, List<ErrorMessagesPojo> errorList)
	{
		ErrorMappings em = new ErrorMappings();
		Map<String, String> errorMap = em.getErrorMap();
		String key;
		if(validatePasswordFormat(tray.getPassword())) 
		{
			PasswordPageDoa ppd = new PasswordPageDoa();
			ppd.verifyPassword(tray, errorList);
		}
		else
		{
			key = em.getWRONG_PASSWORD(); // Invalid Format of the password. Not connecting to database
			ErrorMessagesPojo error = new ErrorMessagesPojo();
			error.setErrorIdentifier(key);
			error.setErrorDescription(errorMap.get(key));
			errorList.add(error);
		}
	}

	private boolean validatePasswordFormat(String password) 
	{
		boolean valid=false;		
		if(!(password.length()>25) && !password.contains(" "))
			valid = true;
		return valid;
	}
}
