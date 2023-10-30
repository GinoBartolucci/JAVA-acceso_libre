package servlets;

import logic.*;
import entities.*;
import utils.Validaciones;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int productora_id = (int) session.getAttribute("id");
		LogicShow ls = new LogicShow();
		LogicProvincia lp = new LogicProvincia();
		LogicCiudad lc = new LogicCiudad();
		LogicLugar ll = new LogicLugar();
		LogicArtista la = new LogicArtista();
		LinkedList<Artista> artistas = null;
		LinkedList<Provincia> provincias = null;
		LinkedList<Ciudad> ciudades = null;
		LinkedList<Lugar> lugares = null;
		LinkedList<Show> shows = null;
		try {
			shows = ls.getAll();
			provincias = lp.getAll();
			ciudades = lc.getAll();
			lugares = ll.getAll();
			artistas = la.getAll();
			request.setAttribute("lugares", lugares);
			request.setAttribute("ciudades", ciudades);
			request.setAttribute("provincias", provincias);
			request.setAttribute("shows", shows);
			request.setAttribute("artistas", artistas);
		} catch (ClassNotFoundException | SQLException e) {
			response.sendError(502);
		}
		request.getRequestDispatcher("WEB-INF\\BuscarShow.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Listas de elementos necesarios para mostrar y crear el objeto
		HttpSession session = request.getSession();
		int user_id = (int) session.getAttribute("id");
		LogicShow ls = new LogicShow();
		LogicProvincia lp = new LogicProvincia();
		LogicCiudad lc = new LogicCiudad();
		LogicLugar ll = new LogicLugar();
		LogicArtista la = new LogicArtista();
		LinkedList<Artista> artistas = null;
		LinkedList<Provincia> provincias = null;
		LinkedList<Ciudad> ciudades = null;
		LinkedList<Lugar> lugares = null;
		LinkedList<Show> shows = null;
		try {
			shows = ls.getAll();
			provincias = lp.getAll();
			ciudades = lc.getAll();
			lugares = ll.getAll();
			artistas = la.getAll();
			request.setAttribute("lugares", lugares);
			request.setAttribute("ciudades", ciudades);
			request.setAttribute("provincias", provincias);
			request.setAttribute("shows", shows);
			request.setAttribute("artistas", artistas);
		} catch (ClassNotFoundException | SQLException e) {
			response.sendError(502);
		}

		// Tipo de resquest del fomulario Alta Baja Modicicar (lleva al jsp de
		// modicicar) ModificarGuardar
		int modo = (request.getParameter("modo") == null) ? 0 : Integer.parseInt(request.getParameter("modo"));
		if (modo != 0) {
			int id = (request.getParameter("id") == null) ? 0 : Integer.parseInt(request.getParameter("id"));
			Show s = new Show();
			for (Show show : shows) {
				if (show.getId() == id) {
					s = show;
					break;
				}
			}

			request.setAttribute("show", s);
			try {
				String nombre;
				Float precio;
				String fecha;
				Integer lugar_id;
				Integer artista_id;
				switch (modo) {
				case 1:
					nombre = Validaciones.validateNombre(request.getParameter("nombre"));
					precio = Validaciones.validateFloat(request.getParameter("precio"));
					fecha = Validaciones.validateSQLDateTime(request.getParameter("fecha"));
					lugar_id = Validaciones.validateInt(request.getParameter("lugar_id"));
					artista_id = Validaciones.validateInt(request.getParameter("artista_id"));
					if (nombre != null && precio != null && fecha != null && lugar_id != null && artista_id != null) {
						ls.create(nombre, precio, fecha, lugar_id, user_id, artista_id);
						response.setStatus(201);
					}
					else {
						//response.setStatus(400);
						response.sendError(400, "WEB-INF\\Error.jsp");
					}

					break;
				case 2:
					ls.delete(s.getId());
					response.setStatus(200);
					break;
				case 3:
					response.setStatus(307);
					request.setAttribute("show", s);
					request.getRequestDispatcher("WEB-INF\\MShow.jsp").forward(request, response);
					break;
				case 4:
					Integer show_id = Validaciones.validateInt(request.getParameter("id"));
					nombre = Validaciones.validateNombre(request.getParameter("nombre"));
					precio = Validaciones.validateFloat(request.getParameter("precio"));
					fecha = Validaciones.validateSQLDateTime(request.getParameter("fecha"));
					lugar_id = Validaciones.validateInt(request.getParameter("lugar_id"));
					artista_id = Validaciones.validateInt(request.getParameter("artista_id"));
					response.setStatus(200);
					ls.update(nombre, precio, fecha, lugar_id, user_id, artista_id, show_id);
					break;
				case 6:
					response.setStatus(307);
					shows = ls.getByCiudad(Validaciones.validateInt(request.getParameter("ciudad_id")));
					request.setAttribute("shows", shows);
					request.getRequestDispatcher("WEB-INF\\BuscarShow.jsp").forward(request, response);
					break;
				case 7:
					response.setStatus(307);
					request.setAttribute("show", s);
					request.setAttribute("asistente_id", user_id);
					request.getRequestDispatcher("WEB-INF\\ComprarEntrada.jsp").forward(request, response);
					break;
				}

			} catch (ClassNotFoundException | SQLException e) {
				response.sendError(502);
			} finally {
			}
			if (modo != 3) {
				try {
					shows = ls.getAll();
					request.setAttribute("shows", shows);
				} catch (ClassNotFoundException | SQLException e) {
					response.sendError(502);
				}
				request.getRequestDispatcher("WEB-INF\\ABMShow.jsp").forward(request, response);
			}

		} else {
			request.getRequestDispatcher("WEB-INF\\ABMShow.jsp").forward(request, response);
		}

	}
}
