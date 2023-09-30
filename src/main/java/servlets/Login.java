package servlets;
import logic.LogicAsistente;
import logic.LogicProductora;
import entities.Asistente;
import entities.Productora;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LogicAsistente la;
    private LogicProductora lp;

    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void setSession(HttpServletRequest request, String email, String password, String nombre_usuario, boolean productora, String cuil, String nombre, String telefono) {
    	HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("password", password);
        session.setAttribute("nombre_usuario", nombre_usuario);
        session.setAttribute("productora", productora);
        session.setAttribute("cuil", cuil);
        session.setAttribute("nombre", nombre);
        session.setAttribute("telefono", telefono);
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    	la = new LogicAsistente();
    	lp = new LogicProductora();
    	
        String email = request.getParameter("email");
        String password = request.getParameter("password");

    	Asistente asistente = new Asistente("0", email, "0");
    	Productora productora = new Productora("0", email,"0",
                "0", "0", "0");
		try {
			la.getByEmail(asistente);
			lp.getByEmail(productora);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

        
        // Aquí debes realizar la autenticación de usuario.
        // Por ejemplo, verificar las credenciales en una base de datos.

        if (asistente.getEmail().equals(email) && asistente.getPassword().equals(password)) {
        	HttpSession session = request.getSession();
        	this.setSession(request, email, password, asistente.getNombre_usuario(), false, null, null, null);
            try {
				response.sendRedirect("dashboard");
			} catch (IOException e) {
				e.printStackTrace();
			} 
        } 
        else if ( productora.getEmail().equals(email) && productora.getPassword().equals(password) ) {
        	this.setSession(request, email, password, productora.getNombre_usuario(), true, productora.getCuil(), productora.getNombre(), productora.getTelefono());
            try {
				response.sendRedirect("dashboard");
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        else {
        	// Usuario no válido, establece un mensaje de error y redirige de vuelta al formulario de inicio de sesión
            request.setAttribute("error", "El usuario no existe o la contraseña es incorrecta.");
            try {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
        }
    }

}
