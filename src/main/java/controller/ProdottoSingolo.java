package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Prodotto;
import persistence.DBManager;

@WebServlet(value="/singolo",name="singolo")
public class ProdottoSingolo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ProdottoSingolo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
		Prodotto p = DBManager.getInstance().getDAOFactory().getProdottoDao().retrieveByType("Pomodoro");
		if(p!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("prodotto", p);
			request.setAttribute("prodotto",p);
			request.getRequestDispatcher("prodottoSingolo.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("prodottoSingolo.jsp").forward(request, response);

			}
		
		
	}
}
