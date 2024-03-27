package servlets;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/DashboardServlet")
public class Dashboard extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        
        // Verificar si el usuario ha iniciado sesión
        if (email != null) {
        	if((boolean)session.getAttribute("productora")) {
            	try {
            		request.getRequestDispatcher("WEB-INF\\HomeProductora.jsp").forward(request, response);
    			} catch (ServletException | IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	}
        	else {
				try {
					request.getRequestDispatcher("WEB-INF\\HomeAsistente.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}

        } else {
            // Si el usuario no ha iniciado sesión, redirige al inicio de sesión
            try {
				response.sendRedirect("index.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
}
