package next;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DataBase;
import model.User;

@WebServlet("/user/create")
public class UserCreateServlet extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(UserCreateServlet.class);
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		 String userId = req.getParameter("userId");
		 String password = req.getParameter("password");
		 String name = req.getParameter("name");
		 String email = req.getParameter("email");

		 User user = new User(userId, password, name, email);
		 System.out.println("User : " + user);
		 DataBase.addUser(user);
		 resp.sendRedirect("/index.html");
		 log.debug("user : " + DataBase.findUserById(userId).getUserId());
		 log.debug("user : " + DataBase.findUserById(userId).getName());
		 log.debug("user : " + DataBase.findUserById(userId).getPassword());
		 log.debug("user : " + DataBase.findUserById(userId).getEmail());
	}
}