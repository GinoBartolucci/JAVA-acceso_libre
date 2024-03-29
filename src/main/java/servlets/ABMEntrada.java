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
		HttpSession session = request.getSession();
		int asistente_id = (int) session.getAttribute("id");
		LogicShow ls = new LogicShow();
		LogicEntrada le = new LogicEntrada();
		LinkedList<Entrada> entradas = null;
		LinkedList<Show> shows = new LinkedList<Show>();
		try {
			Entrada entrada = new Entrada();
			entrada.setAsistente_id(asistente_id);
			entradas = le.findByAsistenteId(entrada);
			
			for (Entrada e : entradas) {
			   Show show = new Show();
			   show.setId(e.getShow_id());
			   ls.findById(show);
			   shows.add(show);
			}
			request.setAttribute("entradas", entradas);
			request.setAttribute("shows", shows);
		} catch (ClassNotFoundException | SQLException e) {
			response.sendError(502);
		}
		request.getRequestDispatcher("WEB-INF\\VerEntradas.jsp").forward(request, response);
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
		LogicShow ls = new LogicShow();

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
				Show show =new Show();
				show_id = Validaciones.validateInt(request.getParameter("show_id"));
				show.setId(show_id);
				ls.findById(show);
				int cantidad_entradas = le.countEntriesByShowId(show);
				if (show.getLugar().getCapacidad() > cantidad_entradas) {
					asistente_id = Validaciones.validateInt(request.getParameter("asistente_id"));
					codigo = "" + Validaciones.generateRandomNumber();
					nombre = Validaciones.validateNombre(request.getParameter("nombre"));
					apellido = Validaciones.validateNombre(request.getParameter("apellido"));
					tipo_doc = Validaciones.validateTipoDoc(request.getParameter("tipo_doc"));
					if (tipo_doc != null) {
						documento = Validaciones.validateDocumento(request.getParameter("documento"), tipo_doc);
					} else
						documento = null;
					validez = true;

					if (asistente_id != null && show_id != null && nombre != null && apellido != null && tipo_doc != null
							&& documento != null) {

						Entrada entrada = new Entrada(asistente_id, show_id, codigo, nombre, apellido, tipo_doc, documento,
								validez);
						if (le.findById(entrada) == null) {
							
							le.create(entrada);
							response.setStatus(201);
							request.getRequestDispatcher("WEB-INF\\HomeAsistente.jsp").forward(request, response);
						}
						else {
							request.setAttribute("error", "Ya posee esta entrada.");
							request.getRequestDispatcher("WEB-INF\\Error.jsp").forward(request, response);
						}

					} else {
						request.setAttribute("error", "Uno de los valores es incorrecto.");
						request.getRequestDispatcher("WEB-INF\\Error.jsp").forward(request, response);
					}
				}
				else {
					request.setAttribute("error", "No quedan más entradas para el show.");
					request.getRequestDispatcher("WEB-INF\\Error.jsp").forward(request, response);
				}
				
				break;
			case 2:
				asistente_id = (int) session.getAttribute("id");
				show_id = Validaciones.validateInt(request.getParameter("show_id"));
				Entrada entrada = new Entrada();
				entrada.setAsistente_id(asistente_id);
				entrada.setShow_id(show_id);
				entrada = le.findById(entrada);
				request.setAttribute("entrada", entrada);
				request.getRequestDispatcher("WEB-INF\\Entrada.jsp").forward(request, response);
				break;
			case 3:
				response.setStatus(307);
				request.getRequestDispatcher("WEB-INF\\MShow.jsp").forward(request, response);
				break;
			case 4:
				response.setStatus(307);
				request.getRequestDispatcher("WEB-INF\\ScanearEntrada.jsp").forward(request, response);
				break;
			case 5:
				Entrada entradaScaner = new Entrada();
				entradaScaner.setCodigo(request.getParameter("codigo")); 
				entradaScaner.setDocumento(request.getParameter("documento"));
				entradaScaner = le.scanearEntrada(entradaScaner);
				if (entradaScaner != null) {
					if(entradaScaner.isValidez()) {
						entradaScaner.setValidez(false);
						le.update(entradaScaner);
						request.setAttribute("resultado", "Entrada Scaneada exitosamente!");
						request.getRequestDispatcher("WEB-INF\\ResultadoScanner.jsp").forward(request, response);
					}
					else {
						request.setAttribute("resultado", "La entrada ya ha sido scaneada");
						request.getRequestDispatcher("WEB-INF\\ResultadoScanner.jsp").forward(request, response);
						}
				}
				else {
					request.setAttribute("resultado", "Datos invalidos");
					request.getRequestDispatcher("WEB-INF\\ResultadoScanner.jsp").forward(request, response);
					}
			break;
		}
		} catch (ClassNotFoundException | SQLException e) {
			response.sendError(503);
		} 
	}
}
