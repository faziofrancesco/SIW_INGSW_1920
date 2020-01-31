package controller.admin;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.tables.Post;
import persistence.DBManager;
import persistence.Dao;

@WebServlet("/updatepost")
public class updatePost extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//TODO
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String id = request.getParameter("Identificativo");
    	String titolo = request.getParameter("Titolo2");
    	String testo = request.getParameter("Messaggio2");
        String img = request.getParameter("Immagine2");
        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        
        Post u = new Post();
        String n = id;
        int i = Integer.parseInt(n);
        u.setidPost(i);
        u.setTitolo(titolo);
        u.setImg(img);
        u.setMessaggio(testo);
        u.setData(sDate);
        
        Dao<Post> prodao = DBManager.getInstance().getDAOFactory().getPostDao();
        prodao.update(u);
        
        response.setStatus(201);
    }
}