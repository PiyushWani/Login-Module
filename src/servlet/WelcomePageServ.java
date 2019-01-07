package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.WelcomePageBean;
import pojo.UserInfoPojo;

@WebServlet("/WelcomePageServ")
public class WelcomePageServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public WelcomePageServ() 
    {
        super();
    }
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
			
			RequestDispatcher dispatcher = null;
			String emailId = request.getParameter("emailId").toLowerCase();		
			System.out.println("emailId: "+emailId);		
			WelcomePageBean wpb = new WelcomePageBean();	
			int statusCode = wpb.verifyEmailId(emailId);
			UserInfoPojo tray = new UserInfoPojo();
			tray.setEmailId(emailId);
			tray.setFirstName("");
			tray.setLastName("");
			
			switch(statusCode)
			{
				case 200:
					dispatcher = request.getRequestDispatcher("/WelcomePage.jsp");					
					request.setAttribute("ErrorCode", "Invalid Email Format");
					break;
				case 201:
					request.setAttribute("emailId", emailId);
					request.setAttribute("tray", tray);
					dispatcher = request.getRequestDispatcher("/NewUserFormPage.jsp");
					break;
				case 202:
					request.setAttribute("emailId", emailId);
					dispatcher = request.getRequestDispatcher("/PasswordPage.jsp");	
					break;
				case 300:
					dispatcher = request.getRequestDispatcher("/ErrorPage.jsp");
					break;
				default:
					dispatcher = request.getRequestDispatcher("/ErrorPage.jsp");
					break;
			}		
		dispatcher.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
