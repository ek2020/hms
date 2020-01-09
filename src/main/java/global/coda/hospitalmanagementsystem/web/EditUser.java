package global.coda.hospitalmanagementsystem.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import global.coda.hospitalmanagementsystem.models.Login;
import global.coda.hospitalmanagementsystem.models.User;
import global.coda.hospitalmanagementsystem.services.PatientServices;
import global.coda.hospitalmanagementsystem.services.UserService;

/**
 * Servlet implementation class EditUser
 */
@WebServlet(name = "EditUser", urlPatterns = { "/edit" }, loadOnStartup = 1)
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String phoneNumber = request.getParameter("phonenumber");
		User user = new User();
		out.print(phoneNumber);
		HttpSession session=request.getSession();
		Login login = new Login();
		login=(Login) session.getAttribute("loggedUser");
		user.setUserName(login.getUserName());
		user.setPassword(login.getPassword());
		user.setPhoneNumber(phoneNumber);
		out.print(user);
		//user.setPhoneNumber(phoneNumber);
		UserService userService=new UserService();
		try {
			if(userService.insert(user))
			{
				out.print("SUCCESSFULLY UPDATED :)");
			}
			else
			{
				out.print("Error : NULL");
			}
		} catch (SQLException e) {
			out.print("Error: "+e.getErrorCode());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
