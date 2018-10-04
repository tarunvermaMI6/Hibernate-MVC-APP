package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.RegisterService;


public class Registercontroller extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	 response.setContentType("text/html;charset=UTF-8");
	 
	 PrintWriter out = response.getWriter();
	 String firstName = request.getParameter("firstName"); //getting form data from register
	 String middleName = request.getParameter("middleName");
	 String lastName = request.getParameter("lastName");
	 String email = request.getParameter("email");
	 String userId = request.getParameter("userId");
	 String password = request.getParameter("password");
	 User user = new User(firstName,middleName,lastName, email,userId, password); //calling the constructor of user bean class
			
	 try {	
		 RegisterService registerService = new RegisterService();//making object of registerService to run bussiness logic i.e to save user values or 
		 boolean result = registerService.register(user);//check username if already exist 
		 out.println("<html>"); //executing html 
		 out.println("<head>");		
		 out.println("<title>Registration Successful</title>");		
		 out.println("</head>");
		 out.println("<body>");
		 out.println("<center>");
		 if(result){//userid doesnt exist
			 out.println("<h1>Thanks for Registering with us :</h1>");
			 out.println("To login with new UserId and Password<a href=login.jsp>Click here</a>");
		 }else{ //if exist
			 out.println("<h1>Registration Failed UserId Must be Unique</h1>");
			 out.println("To try again<a href=register.jsp>Click here</a>");
		 }
		 out.println("</center>");
		 out.println("</body>");
		 out.println("</html>");
	 } finally {		
		 out.close();
	 }
}

}