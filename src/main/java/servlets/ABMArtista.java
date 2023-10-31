package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Ciudad;
import entities.Artista;
import logic.LogicArtista;
import utils.Validaciones;

/**
 * Servlet implementation class ABMProvincia
 */
public class ABMArtista extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ABMArtista() {
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
		//Listas de elementos necesarios para mostrar y crear el objeto
		LogicArtista la = new LogicArtista();
		LinkedList<Artista> artistas = null;
		try {
			artistas = la.getAll();
			request.setAttribute("artistas", artistas);
		} catch (ClassNotFoundException | SQLException e) {
			response.sendError(502);
		}
		//Tipo de resquest del fomulario Alta Baja Modicicar (lleva al jsp de modicicar) ModificarGuardar
		int modo = (request.getParameter("modo") == null) ? 0: Integer.parseInt(request.getParameter("modo"));
		if(modo != 0) {
			//Traer por id elemento que se selecciono
			int id = (request.getParameter("id") == null) ? 0 : Integer.parseInt(request.getParameter("id"));
			Artista a = new Artista();
			for(Artista art : artistas) {
				if(art.getId() == id) {
					a = art;
					break;
				}
			}
			a.setNombre((request.getParameter("nombre") == null) ? a.getNombre(): Validaciones.validateNombre(request.getParameter("nombre")));
			try {
				switch (modo) {
					case 1:				
						if (a.getNombre() != null) {
							la.create(a);	
							response.setStatus(201);
						}else {
							request.setAttribute("error", "Nombre inválido.");
							request.getRequestDispatcher("WEB-INF\\Error.jsp").forward(request, response);
						}

						break;
					case 2:
						la.delete(a);
						response.setStatus(200);
						break;
					case 3:
						response.setStatus(307);
						request.setAttribute("artista", a); //pasar la provincia a modificar al jsp de modificar
						request.getRequestDispatcher("WEB-INF\\MArtista.jsp").forward(request, response);
						break;
					case 4:
						response.setStatus(200);
						if (a.getNombre() != null) {
							la.update(a);		
							response.setStatus(201);
						}else {
							request.setAttribute("error", "Nombre inválido.");
							request.getRequestDispatcher("WEB-INF\\Error.jsp").forward(request, response);
						}			
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
						artistas = la.getAll();
						request.setAttribute("artistas", artistas);
					} catch (ClassNotFoundException | SQLException e) {
						response.sendError(502);
					}
					request.getRequestDispatcher("WEB-INF\\ABMArtista.jsp").forward(request, response);
				}
			
		}
		else {
			request.getRequestDispatcher("WEB-INF\\ABMArtista.jsp").forward(request, response);
		}
		
	}
}