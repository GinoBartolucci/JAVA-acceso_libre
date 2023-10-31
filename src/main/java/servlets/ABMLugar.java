package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.LogicLugar;
import logic.LogicCiudad;
import logic.LogicProvincia;
import utils.Validaciones;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Ciudad;
import entities.Lugar;
import entities.Provincia;

/**
 * Servlet implementation class ABMLugar
 */
public class ABMLugar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMLugar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Listas de elementos necesarios para mostrar y crear el objeto
		LinkedList<Lugar> lugares = null;
		LogicLugar ll = new LogicLugar();
		LinkedList<Ciudad> ciudades = null;
		LogicCiudad lc = new LogicCiudad();
		LinkedList<Provincia> provincias = null;
		LogicProvincia lp = new LogicProvincia();
		try {
			lugares = ll.getAll();
			request.setAttribute("lugares", lugares);
			ciudades = lc.getAll();
			request.setAttribute("ciudades", ciudades);
			provincias = lp.getAll();
			request.setAttribute("provincias", provincias);
		} catch (ClassNotFoundException | SQLException e) {
			response.sendError(502);
		}	
		int modo = (request.getParameter("modo") == null) ? 0: Integer.parseInt(request.getParameter("modo"));		//Tipo de resquest del fomulario Alta Baja Modicicar (lleva al jsp de modicicar) ModificarGuardar
		if(modo != 0) {	
			int id = (request.getParameter("id") == null) ? 0 : Integer.parseInt(request.getParameter("id"));//Traer por id en la lista el elemento que se selecciono
			Lugar lugar = new Lugar();				
			for(Lugar l : lugares) {
				if(l.getId() == id) {
					lugar = l;
					break;
				}
			}
			lugar.setNombre((request.getParameter("nombre") == null) ? lugar.getNombre(): Validaciones.validateNombre(request.getParameter("nombre")));
			lugar.setDireccion((request.getParameter("direccion") == null) ? lugar.getDireccion(): request.getParameter("direccion"));
			lugar.setCapacidad((request.getParameter("capacidad") == null) ? lugar.getCapacidad(): Validaciones.validateInt(request.getParameter("capacidad")));
			int idCiudad = (request.getParameter("ciudad_id") == null) ? Integer.parseInt(request.getParameter("ciudad_id_hidden")): Integer.parseInt(request.getParameter("ciudad_id"));//Parametros que se pueden haber modificado en jsp de modificar. O en el form de crear		
			for(Ciudad c : ciudades) {
				if(c.getId() == idCiudad) {
					lugar.setCiudad(c);
					break;
				}
			}
			request.setAttribute("lugar", lugar);
			try {
				switch (modo) {
					case 1:
						if (lugar.getNombre() != null) {
							ll.create(lugar);	
							response.setStatus(201);
						}
						else {
							request.setAttribute("error", "Nombre inválido.");
							request.getRequestDispatcher("WEB-INF\\Error.jsp").forward(request, response);
						}
						break;
					case 2:
						ll.delete(lugar);	
						response.setStatus(200);
						break;
					case 3:
						response.setStatus(307);
						request.getRequestDispatcher("WEB-INF\\MLugar.jsp").forward(request, response);
						break;
					case 4:
						if (lugar.getNombre() != null) {
							ll.update(lugar);	
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
			if(modo != 3) {
				try {
					lugares = ll.getAll();
					request.setAttribute("lugares", lugares);
				}catch (ClassNotFoundException | SQLException e) {
					response.sendError(502);
				}
				request.getRequestDispatcher("WEB-INF\\ABMLugar.jsp").forward(request, response);
			}
		} 
		else {
			request.getRequestDispatcher("WEB-INF\\ABMLugar.jsp").forward(request, response);
		}
	}

}
