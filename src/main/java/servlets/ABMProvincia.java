package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String modo = request.getParameter("modo");
		int id = (request.getParameter("id") == null) ? 0 : Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		response.getWriter().append("Get request: "+id + nombre + modo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LogicProvincia lp = new LogicProvincia();
		LinkedList<Provincia> provincias = null;
		try {
			provincias = lp.getAll();
			request.setAttribute("provincias", provincias);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String modo = request.getParameter("modo");
		if(modo != null) {
			int id = (request.getParameter("id") == null) ? 0 : Integer.parseInt(request.getParameter("id"));
			String nombre = request.getParameter("nombre");
			Provincia p = new Provincia(id, nombre);						
			try {
				switch (request.getParameter("modo")) {
					case "A":
						lp.create(p);
						break;
					case "B":
						lp.delete(p);				
						break;
					case "MG":
						lp.update(p);
						break;
					case "M":
						request.setAttribute("provincia", p);
						request.getRequestDispatcher("WEB-INF\\MProvincia.jsp").forward(request, response);
						break;
				}
			}
			catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				response.getWriter().append(e.getMessage());
			}
			finally{
				try {
					provincias = lp.getAll();
				} catch (ClassNotFoundException | SQLException e) {
					response.getWriter().append(e.getMessage());
				}
				request.setAttribute("provincias", provincias);
				request.getRequestDispatcher("WEB-INF\\ABMProvincia.jsp").forward(request, response);
			}
		}
		else {
			request.getRequestDispatcher("WEB-INF\\ABMProvincia.jsp").forward(request, response);
		}
	}
}
