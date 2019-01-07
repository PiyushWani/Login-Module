package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.NewUserFormBean;
import pojo.ErrorMessagesPojo;
import pojo.UserInfoPojo;

@WebServlet("/NewUserFormServ")
public class NewUserFormServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NewUserFormServ() 
    {
        super();
     }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		System.out.println("In NewuserFormServ");
		NewUserFormBean nufb = new NewUserFormBean();
		UserInfoPojo tray = new UserInfoPojo();
		List<ErrorMessagesPojo> errorList = new ArrayList<>();		
		
		tray.setEmailId(request.getParameter("emailId"));
		tray.setFirstName(request.getParameter("firstName"));
		tray.setLastName(request.getParameter("lastName"));
		tray.setPassword(request.getParameter("password"));
		tray.setConfirmPassword(request.getParameter("confirmPassword"));
		
		nufb.validateParameters(tray, errorList);
		
		RequestDispatcher dispatcher=null;
		
		if(errorList.isEmpty())
		{
			HttpSession session = request.getSession();
			ServletContext context = request.getServletContext();			
			context.setAttribute("sessionId", session.getId());
			System.out.println("NUFP ServletContext: "+context.getAttribute("sessionId"));
			
			dispatcher = request.getRequestDispatcher("/UserMainPage.jsp");	
			request.setAttribute("userName", tray.getLastName()+", "+tray.getFirstName());
			
		}
		else
		{
			Iterator<ErrorMessagesPojo> itr = errorList.iterator();			
			int i=0;
			while(itr.hasNext())
			{
				itr.next();
				ErrorMessagesPojo error = errorList.get(i);
				i++;
				System.out.println(error.getErrorIdentifier()+": "+error.getErrorDescription());
				request.setAttribute(error.getErrorIdentifier(), error.getErrorDescription());
			}
			request.setAttribute("emailId", tray.getEmailId());
			request.setAttribute("tray", tray);
			dispatcher = request.getRequestDispatcher("/NewUserFormPage.jsp");
			System.out.println("There are errors in parameter field or Database Insert");		
		}
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
