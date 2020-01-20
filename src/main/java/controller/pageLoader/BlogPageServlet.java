package controller.pageLoader;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/blog", name = "blog")
public class BlogPageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.getRequestDispatcher("blog.jsp").include(req, resp);
		
//		resp.setContentType("text/html");
//		
//		RequestDispatcher rd = req.getRequestDispatcher("header.html");
//		rd.include(req, resp);
////		rd = req.getRequestDispatcher("");
////		rd.include(req, resp);
//		rd = req.getRequestDispatcher("footer.html");
//		rd.include(req, resp);
//		 
//		if(req.getSession().getAttribute("attempts") == null) {
//			req.getSession().setAttribute("attempts", 0);
//	    }
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
}