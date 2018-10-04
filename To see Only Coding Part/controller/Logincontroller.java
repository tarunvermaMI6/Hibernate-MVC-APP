package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.LoginService;


public class Logincontroller extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

	 String userId = request.getParameter("userId");	
	 String password = request.getParameter("password");
	 LoginService loginService = new LoginService();//obj of loginService class 
	 boolean result = loginService.authenticateUser(userId, password);//calling function
	 User user = loginService.getUserByUserId(userId);
	 if(result == true){
		 request.getSession().setAttribute("user", user);//starting the session of user i.e login		
		 response.sendRedirect("home.jsp");//redirect to another page
	 }
	 else{
		 response.sendRedirect("error.jsp");//redirect to error page
	 }
}

}