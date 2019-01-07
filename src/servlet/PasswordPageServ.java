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

import bean.PasswordPagebean;
import pojo.ErrorMessagesPojo;
import pojo.UserInfoPojo;

@WebServlet("/PasswordPageServ")
public class PasswordPageServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PasswordPageServ() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserInfoPojo tray=new UserInfoPojo();	
		List<ErrorMessagesPojo> errorList = new ArrayList<>();	
		tray.setPassword(request.getParameter("password"));
		tray.setEmailId(request.getParameter("emailId").toLowerCase());
		System.out.println("In PasswordPageServ\n"+tray.getEmailId());
		System.out.println(tray.getPassword());
		
		RequestDispatcher dispatcher=null;
		PasswordPagebean ppb = new PasswordPagebean();
		ppb.verifyPassword(tray, errorList);
		if(errorList.isEmpty()){
			HttpSession session = request.getSession();
			ServletContext context = request.getServletContext();
			context.setAttribute("sessionId", session.getId());			
			dispatcher = request.getRequestDispatcher("/UserMainPage.jsp");
			request.setAttribute("emailId", tray.getEmailId());
			System.out.println("Hi! "+tray.getFirstName()+" "+tray.getLastName());
		}
		else{
			Iterator<ErrorMessagesPojo> itr = errorList.iterator();
			Integer i=0;
			ErrorMessagesPojo error=null;
			while(itr.hasNext()){
				itr.next();
				error = errorList.get(i);			
				request.setAttribute(error.getErrorIdentifier(), error.getErrorDescription());
				System.out.println(error.getErrorIdentifier()+": "+error.getErrorDescription());
			}
			request.setAttribute("emailId", tray.getEmailId());
			dispatcher = request.getRequestDispatcher("/PasswordPage.jsp");
		}
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
