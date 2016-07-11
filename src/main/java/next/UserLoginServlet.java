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


@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(UserLoginServlet.class);
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		 
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		
		if(DataBase.findUserById(userId)!=null && password.equals(DataBase.findUserById(userId).getPassword())){
			resp.sendRedirect("/index.html");
			return;
		}
		resp.sendRedirect("/user/login_failed.html");
	}
}