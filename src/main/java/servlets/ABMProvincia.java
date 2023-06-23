package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import entities.*;
import logic.*;
/**
 * Servlet implementation class ABMProvincia
 */
public class ABMProvincia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMProvincia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombre = request.getParameter("nombre");
		Provincia p = new Provincia(nombre);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombre = request.getParameter("nombre");
		Provincia p = new Provincia(nombre);
		p.setId(-1);
		LogicProvincia lp = new LogicProvincia();
		try {
			lp.findOne(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().append(e.getMessage());
		}
		//response.getWriter().append("Provincia creada: ").append(p.getNombre() + " " +p.getId());
	}

}
