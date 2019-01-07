package bean;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import doa.NewUserFormDoa;
import pojo.ErrorMessagesPojo;
import pojo.UserInfoPojo;
import utilities.ErrorMappings;

public class NewUserFormBean 
{
	
	public void validateParameters(UserInfoPojo tray, List<ErrorMessagesPojo> errorList) 
	{
		ErrorMappings em = new ErrorMappings();
		Map<String, String> errorMap = em.getErrorMap();
		ErrorMessagesPojo error;
		
		String key;
		String parameter = tray.getEmailId();
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$";                   
		Pattern pat = Pattern.compile(emailRegex);   
		if(pat.matcher(parameter).matches() == false || parameter.isEmpty())
		{
			error = new ErrorMessagesPojo();
			key = em.getINVALID_EMAIL_FORMAT();
			error.setErrorIdentifier(key);
			error.setErrorDescription(errorMap.get(key));
			errorList.add(error);
			System.out.println("invalid emailId "+parameter);
		}
		
		parameter = tray.getFirstName();
		if(!parameter.matches("[a-zA-Z]+") || parameter.isEmpty())
		{
			error = new ErrorMessagesPojo();
			key = em.getINVALID_FIRSTNAME();  
			error.setErrorIdentifier(key);
			error.setErrorDescription(errorMap.get(key));
			errorList.add(error);
			System.out.println("invalid firstName "+parameter);
		}
		
		parameter = tray.getLastName();
		if(!parameter.matches("[a-zA-Z]+") || parameter.isEmpty())
		{
			error = new ErrorMessagesPojo();
			key = em.getINVALID_LASTNAME();
			error.setErrorIdentifier(key);
			error.setErrorDescription(errorMap.get(key));
			errorList.add(error);
			System.out.println("invalid lastName "+parameter);
		}
		
		String password = tray.getPassword();
		String confirmPassword = tray.getConfirmPassword();
		if(password.equals(confirmPassword) && !password.isEmpty())
		{
			/*if(password.contains(" "));
			{
				error = new ErrorMessagesPojo();
				key = em.getBAD_PASSWORD();
				error.setErrorIdentifier(key);
				error.setErrorDescription(errorMap.get(key));
				errorList.add(error);
				System.out.println("invalid spaces in password "+password);
			}*/
		}
		else
		{
			error = new ErrorMessagesPojo();
			key = em.getPASSWORDS_MISMATCH();
			error.setErrorIdentifier(key);
			error.setErrorDescription(errorMap.get(key));
			errorList.add(error);
			System.out.println("Password mismatch/Empty Password: "+password+" != "+confirmPassword);			
		}
		if(errorList.isEmpty())
		{
			NewUserFormDoa nufd = new NewUserFormDoa();
			nufd.insertCustomerEntry(tray, errorList);
		}
		
	}
}
