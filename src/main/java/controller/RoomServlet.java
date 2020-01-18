package controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Room;
import persistence.DBManager;
import persistence.Dao;

@WebServlet(value = "/rooms_servlet", name = "rooms_servlet")
public class RoomServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tipo = req.getParameter("Tipo");
        String descrizione = req.getParameter("Descrizione");
        String numMaxPersone = req.getParameter("numMaxPersone");
        String prezzo= req.getParameter("prezzo");
        Room r=new Room();
        r.setTipo(tipo);
        r.setDescrizione(descrizione);
        String numero =numMaxPersone;
        int intero = Integer.parseInt(numero);
        r.setMaxPersoneStanza(intero);
        r.setOccupata(false);
        String numero1 =prezzo;
        int intero1 = Integer.parseInt(numero1);
        r.setPrezzo(intero1);
        if(tipo.equals("singola")) {
        	r.setImg("images/s1");
        }
        if(tipo.equals("doppia")) {
        	r.setImg("images/2");
        }
        Dao<Room> roomdao = DBManager.getInstance().getDAOFactory().getRoomDao();
        roomdao.save(r);
        resp.sendRedirect("Addroom.jsp");

	}
}
