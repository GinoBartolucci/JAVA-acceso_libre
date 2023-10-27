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

import logic.LogicShow;

/**
 * Servlet implementation class ABMProvincia
 */
public class ABMEntrada extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ABMEntrada() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int productora_id = (int) session.getAttribute("id");
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
		try {
			ls.create("joaquinasd", (float) 3, "2023-10-25T21:39", 5, productora_id, 2);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().append("Get request: " + session.getAttribute("id"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Listas de elementos necesarios para mostrar y crear el objeto
		HttpSession session = request.getSession();
		LogicEntrada le = new LogicEntrada();

		int modo = (request.getParameter("modo") == null) ? 0 : Integer.parseInt(request.getParameter("modo"));

		try {
			Integer asistente_id;
			Integer show_id;
			String codigo;
			String nombre;
			String apellido;
			String tipo_doc;
			String documento;
			Boolean validez;

			switch (modo) {
			case 1:
				asistente_id = Validaciones.validateInt(request.getParameter("asistente_id"));
				show_id = Validaciones.validateInt(request.getParameter("show_id"));
				codigo = "" + Validaciones.generateRandomNumber();
				System.out.println(codigo);
				nombre = Validaciones.validateNombre(request.getParameter("nombre"));
				apellido = Validaciones.validateNombre(request.getParameter("apellido"));
				tipo_doc = Validaciones.validateTipoDoc(request.getParameter("tipo_doc"));
				if (tipo_doc != null) {
					documento = "" + Validaciones.validateDocumento(request.getParameter("documento"), tipo_doc);
				} else
					documento = null;

				validez = true;

				if (asistente_id != null && show_id != null && nombre != null && apellido != null && tipo_doc != null
						&& documento != null) {
					Entrada entrada = new Entrada(asistente_id, show_id, codigo, nombre, apellido, tipo_doc, documento,
							validez);
					le.create(entrada);
					response.setStatus(201);
				} else {
					response.setStatus(400);
				}
				break;
			case 2:
				response.setStatus(200);
				break;
			case 3:
				response.setStatus(307);
				request.getRequestDispatcher("WEB-INF\\MShow.jsp").forward(request, response);
				break;
			case 4:
				response.setStatus(200);
				break;
			}
		} catch (ClassNotFoundException | SQLException e) {
			response.sendError(502);
		} finally {
		}
		if (modo != 3) {

		}

	}
}
