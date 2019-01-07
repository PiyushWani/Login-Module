package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServ")
public class LogoutServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LogoutServ() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	HttpSession session = request.getSession();
    	ServletContext context = request.getServletContext();
    	System.out.println("Logout ServletContext: "+context.getAttribute("sessionId"));
    	RequestDispatcher dispatcher=null;
    	if(session.getId().equals(context.getAttribute("sessionId")))
    	{
    		dispatcher = request.getRequestDispatcher("/WelcomePage.jsp");
    		System.out.println("Session was created. Now system can invalidate the session");
    		session.invalidate();
    		session = null;
    		request.removeAttribute("emailId");
    	    request.removeAttribute("password");    	    
    		context.removeAttribute("sessionId");
    		System.out.println("Session Invalidated");
    		dispatcher.forward(request, response);
    	}
    	else
    	{
    		dispatcher = request.getRequestDispatcher("/ErrorPage.jsp");
    		dispatcher.forward(request, response);
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
