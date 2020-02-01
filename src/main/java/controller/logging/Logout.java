package controller.logging;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//FIXME: davvero dobbiamo fare così per ogni link?
@WebServlet("/logout")
public class Logout extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3499056463190953357L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getSession().setAttribute("logged", false);
		req.getSession().removeAttribute("userId");
		resp.addCookie(new Cookie("logged", "false"));
		req.getSession().removeAttribute("admin");
		resp.addCookie(new Cookie("admin", "false"));
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
		// resp.sendRedirect(req.getHeader("referer"));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(405); // Permission denied, only GET here
	}
}
