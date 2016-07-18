package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

@WebServlet("/user/modify")
public class UpdateUserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UpdateUserServlet.class);

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("user", DataBase.findUserById((String) req.getParameter("userId")));
		log.debug("userId : " + req.getParameter("userId"));
		RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
        rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);
		User modUser = new User(req.getParameter("userId").toString(), req.getParameter("password").toString(), req.getParameter("name").toString(), req.getParameter("email").toString());
		DataBase.addUser(modUser);
		resp.sendRedirect("/user/list");
	}
}
