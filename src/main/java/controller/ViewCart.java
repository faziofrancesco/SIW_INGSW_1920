package controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Prenotazione;
import model.Prodotto;
import model.Room;
import persistence.DBManager;


@WebServlet(value="/viewcart",name="viewcart")
public class ViewCart extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ViewCart() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer idUser = (Integer)request.getSession().getAttribute("userId");
		List<Prenotazione> r = DBManager.getInstance().getDAOFactory().getPrenotazioneDao().retrieveByUserID(idUser);
		
		request.setAttribute("roomutente",r);
		
		List<Prodotto> p = DBManager.getInstance().getDAOFactory().getProdottoDao().retrieveByUserID(idUser);
		
		request.setAttribute("prodottoutente", p);
		
		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}
	

	

	

	 
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
	}

}