package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);
		String userId = req.getParameter("userId").toString();
		String pw = req.getParameter("password").toString();
		
		if(userId==null || pw==null || DataBase.findUserById(userId)==null){
			resp.sendRedirect("/user/login_failed.html");
			return;
		}
		log.debug("userId : " + DataBase.findUserById(userId));
		
		if(pw!=null && pw.equals(DataBase.findUserById(userId).getPassword())){
			HttpSession session = req.getSession();
			session.setAttribute("user", userId);
			resp.sendRedirect("/index.jsp");
		}else{
			RequestDispatcher rd = req.getRequestDispatcher("/user/login_failed.html");
	        rd.forward(req, resp);
		}
		
		
		
	}
}
