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
import entities.Provincia;
import logic.LogicProvincia;
import utils.Validaciones;

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
		//Listas de elementos necesarios para mostrar y crear el objeto
		LogicProvincia lp = new LogicProvincia();
		LinkedList<Provincia> provincias = null;
		try {
			provincias = lp.getAll();
			request.setAttribute("provincias", provincias);
		} catch (ClassNotFoundException | SQLException e) {
			response.sendError(502);
		}
		//Tipo de resquest del fomulario Alta Baja Modicicar (lleva al jsp de modicicar) ModificarGuardar
		int modo = (request.getParameter("modo") == null) ? 0: Integer.parseInt(request.getParameter("modo"));
		if(modo != 0) {
			//Traer por id elemento que se selecciono
			int id = (request.getParameter("id") == null) ? 0 : Integer.parseInt(request.getParameter("id"));
			Provincia p = new Provincia();
			for(Provincia pro : provincias) {
				if(pro.getId() == id) {
					p = pro;
					break;
				}
			}
			p.setNombre((request.getParameter("nombre") == null) ? p.getNombre(): Validaciones.validateNombre(request.getParameter("nombre")));
			try {
				switch (modo) {
					case 1:		
						if (p.getNombre() != null) {
							lp.create(p);	
							response.setStatus(201);
						}
						else {
							request.setAttribute("error", "Nombre inválido.");
							request.getRequestDispatcher("WEB-INF\\Error.jsp").forward(request, response);
						}
						break;
					case 2:
						try {
							lp.delete(p);
							}catch (ClassNotFoundException | SQLException e){
								request.setAttribute("error", "No puede borrar provincia con ciudades asignadas.");
								request.getRequestDispatcher("WEB-INF\\Error.jsp").forward(request, response);
							}
						break;
					case 3:
						response.setStatus(307);
						request.setAttribute("provincia", p); //pasar la provincia a modificar al jsp de modificar
						request.getRequestDispatcher("WEB-INF\\MProvincia.jsp").forward(request, response);
						break;
					case 4:
						if (p.getNombre() != null) {
							lp.update(p);	
							response.setStatus(200);
						}
						else {
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
						provincias = lp.getAll();
						request.setAttribute("provincias", provincias);
					} catch (ClassNotFoundException | SQLException e) {
						response.sendError(502);
					}
					request.getRequestDispatcher("WEB-INF\\ABMProvincia.jsp").forward(request, response);
				}
			
		}
		else {
			request.getRequestDispatcher("WEB-INF\\ABMProvincia.jsp").forward(request, response);
		}
		
	}
}
