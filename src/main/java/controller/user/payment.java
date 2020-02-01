package controller.user;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.naming.java.javaURLContextFactory;

import model.nonTables.ProdottoAggregato;
import model.tables.Pagamento;
import persistence.DBManager;

@WebServlet(value = "/paym", name = "paym")
public class payment extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4725397173656796295L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DBManager.getInstance().getDAOFactory().getOrdineDao()
				.pay((Integer) request.getSession().getAttribute("userId"));
		Pagamento p = new Pagamento();
		p.setIdOrdine((Integer) request.getSession().getAttribute("idordine"));
		Date d = new Date(new java.util.Date().getTime());

		p.setDataPagamento(d);
		List<ProdottoAggregato> prodc = DBManager.getInstance().getDAOFactory().getProdottoDao()
				.showProductsForCart((Integer) request.getSession().getAttribute("idordine"));
		List<Integer> prezzi = DBManager.getInstance().getDAOFactory().getOrdineDao()
				.retrievePrezzoCamere((Integer) request.getSession().getAttribute("userId"));
		int s = 0;
		for (int i = 0; i < prodc.size(); i++) {
			s += prodc.get(i).getPrezzo();
		}
		for (int j = 0; j < prezzi.size(); j++) {
			s += prezzi.get(j);
		}
		p.setImporto(s);
		DBManager.getInstance().getDAOFactory().getPagamentoDao().save(p);
		DBManager.getInstance().getDAOFactory().getOrdineDao()
				.pay((Integer) request.getSession().getAttribute("userId"));
		response.setStatus(201);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}