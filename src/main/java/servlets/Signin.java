package servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.LogicAsistente;
import logic.LogicProductora;
import entities.Productora;
import entities.Asistente;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class Signin
 */
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LogicAsistente la;
    private LogicProductora lp;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signin() {
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
    	la = new LogicAsistente();
    	lp = new LogicProductora();
		// Recupera los datos del formulario
        String email = request.getParameter("email");
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        boolean isProductora = request.getParameter("productora") != null;
        if ( isProductora ) {
            String name = request.getParameter("name");
            String cuil = request.getParameter("cuil");
            String telefono = request.getParameter("telefono");
            Productora productora = new Productora(usuario, email, password,
                    name, cuil, telefono);
            try {
				lp.create(productora);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else {
        	String name = "";
        	String cuil = "";
        	String telefono = "";
            Asistente asistente = new Asistente(usuario, email, password);
            try {
				la.create(asistente);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
            }
        }
        response.sendRedirect("index.jsp");
	}

}
