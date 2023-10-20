package servlets;
import logic.*;
import entities.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Show;
import logic.LogicShow;

/**
 * Servlet implementation class ABMProvincia
 */
public class ABMShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ABMShow() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LogicShow ls = new LogicShow();
		LogicLugar ll = new LogicLugar();
		LinkedList<Lugar> lugares = null;
		LinkedList<Show> shows = null;
		Lugar lugar = new Lugar();
		lugar.setId(2);
		
		
		try {
			shows = ls.getAll();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int id = (request.getParameter("id") == null) ? 0 : Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		response.getWriter().append("Get request: " + shows.getFirst().getNombre()) ;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Listas de elementos necesarios para mostrar y crear el objeto
		LogicShow ls = new LogicShow();
		LogicProvincia lp = new LogicProvincia();
		LogicCiudad lc = new LogicCiudad();
		LogicLugar ll = new LogicLugar();
		LinkedList<Provincia> provincias = null;
		LinkedList<Ciudad> ciudades = null;
		LinkedList<Lugar> lugares = null;
		LinkedList<Show> shows = null;
		try {
			shows = ls.getAll();
			provincias = lp.getAll();
			ciudades = lc.getAll();
			lugares = ll.getAll();
			request.setAttribute("lugares", lugares);
			request.setAttribute("ciudades", ciudades);
			request.setAttribute("provincias", provincias);
			request.setAttribute("shows", shows);
		} catch (ClassNotFoundException | SQLException e) {
			response.sendError(502);
		}
		//Tipo de resquest del fomulario Alta Baja Modicicar (lleva al jsp de modicicar) ModificarGuardar
		int modo = (request.getParameter("modo") == null) ? 0: Integer.parseInt(request.getParameter("modo"));
		if(modo != 0) {
			//Traer por id elemento que se selecciono
			int id = (request.getParameter("id") == null) ? 0 : Integer.parseInt(request.getParameter("id"));
			Show s = new Show();
			for(Show show : shows) {
				if(show.getId() == id) {
					s = show;
					break;
				}
			}
			s.setNombre((request.getParameter("nombre") == null) ? s.getNombre(): request.getParameter("nombre"));
			request.setAttribute("show", s);
			try {
				switch (modo) {
					case 1:						
						ls.create(s);	
						response.setStatus(201);
						break;
					case 2:
						ls.delete(s);
						response.setStatus(200);
						break;
					case 3:
						response.setStatus(307);
						request.setAttribute("show", s); 
						request.getRequestDispatcher("WEB-INF\\MAShow.jsp").forward(request, response);
						break;
					case 4:
						response.setStatus(200);
						ls.update(s);					
						break;
				}
				
			}
			catch (ClassNotFoundException | SQLException e) {
				response.sendError(502);
			}
			finally{					
			}
			if(modo != 3) {
					try {
						shows = ls.getAll();
						request.setAttribute("shows", shows);
					} catch (ClassNotFoundException | SQLException e) {
						response.sendError(502);
					}
					request.getRequestDispatcher("WEB-INF\\ABMShow.jsp").forward(request, response);
				}
			
		}
		else {
			request.getRequestDispatcher("WEB-INF\\ABMShow.jsp").forward(request, response);
		}
		
	}
}
