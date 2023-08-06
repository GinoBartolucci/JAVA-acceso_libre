package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.LogicProvincia;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Ciudad;
import entities.Provincia;
import logic.LogicCiudad;

/**
 * Servlet implementation class ABMCiudad
 */
public class ABMCiudad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMCiudad() {
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
		LogicCiudad lc = new LogicCiudad();
		LinkedList<Ciudad> ciudades = null;
		LogicProvincia lp = new LogicProvincia();
		LinkedList<Provincia> provincias = null;
		try {
			ciudades = lc.getAll();
			request.setAttribute("ciudades", ciudades);
			provincias = lp.getAll();
			request.setAttribute("provincias", provincias);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		//Tipo de resquest del fomulario Alta Baja Modicicar (lleva al jsp de modicicar) ModificarGuardar
		int modo = (request.getParameter("modo") == null) ? 0: Integer.parseInt(request.getParameter("modo"));
		if(modo != 0) {			
			//Traer por id en la lista el elemento que se selecciono
			int id = (request.getParameter("id") == null) ? 0 : Integer.parseInt(request.getParameter("id"));
			Ciudad c = new Ciudad();				
			for(Ciudad ciu : ciudades) {
				if(ciu.getId() == id) {
					c = ciu;
					break;
				}
			}	
			//Parametros que se pueden haber modificado en jsp de modificar. O en el form de crear
			c.setNombre(request.getParameter("nombre"));
			int idProvincia = Integer.parseInt(request.getParameter("provincia_id"));
			for(Provincia pro : provincias) {
				if(pro.getId() == idProvincia) {
					c.setProvincia(pro);
					break;
				}
			}
			request.setAttribute("ciudad", c);			
			try {
				switch (modo) {
					case 1:
						lc.create(c);
						break;
					case 2:
						lc.delete(c);				
						break;
					case 3:
						request.getRequestDispatcher("WEB-INF\\MCiudad.jsp").forward(request, response);
						break;
					case 4:
						lc.update(c);
						break;
				}
			}
			catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				response.getWriter().append(e.getMessage());
			}
			finally{
				if(modo != 3) {
					try {
						ciudades = lc.getAll();
						request.setAttribute("ciudades", ciudades);
					} catch (ClassNotFoundException | SQLException e) {
						response.getWriter().append(e.getMessage());
					}
					request.getRequestDispatcher("WEB-INF\\ABMCiudad.jsp").forward(request, response);
				}
			}
		}
		else {
			request.getRequestDispatcher("WEB-INF\\ABMCiudad.jsp").forward(request, response);
		}
	}

}
