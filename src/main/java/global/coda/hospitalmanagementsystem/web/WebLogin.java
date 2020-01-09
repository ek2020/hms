package global.coda.hospitalmanagementsystem.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import global.coda.hospitalmanagementsystem.models.Login;
import global.coda.hospitalmanagementsystem.models.User;
import global.coda.hospitalmanagementsystem.services.AuthenticationService;

/**
 * Servlet implementation class Login
 * 
 */
@WebServlet(name = "LoginServlet", urlPatterns = { "/login" }, loadOnStartup = 1)
public class WebLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WebLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// s response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		Login login = new Login();
		login.setUserName(name);
		login.setPassword(password);
		out.println(login);
		AuthenticationService auth = new AuthenticationService();
		User user = new User();
		// out.println(user);
		user = auth.login(login);
		// out.println(user);
		if (user != null) {

			out.print("SUCCESS");
			// session
			HttpSession session = request.getSession();
			session.setAttribute("loggedUser", login);
			request.setAttribute("user", user);
			
			RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/display.jsp");
			RequetsDispatcherObj.forward(request, response);
			
		} else {
			out.println(user);
			out.print("Bad Credentials");
		}

	}

}
