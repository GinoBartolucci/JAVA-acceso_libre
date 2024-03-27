<%@page import="java.util.LinkedList"%>
<%@page import="entities.Entrada"%>
<%@page import="entities.Show"%>
<%@page import="utils.Validaciones"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="./js/buscarShow.js" defer></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Shows</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<%
LinkedList<Show> listaS = (LinkedList<Show>) request.getAttribute("shows");
LinkedList<Entrada> listaE = (LinkedList<Entrada>) request.getAttribute("entradas");
%>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="d-flex justify-content-between align-items-center">
					<h1><a href="/JAVA-acceso_libre/dashboard" class="text-decoration-none"> Home</a> Entradas Shows</h1>
					 <div>
						 <p><%=session.getAttribute("nombre_usuario") %></p>
						 <a href="/JAVA-acceso_libre">Logout</a>
					 </div>
				</div>				
				<hr>
				<h4>Tus entradas</h4>
				<table class="table table table-striped">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Nombre</th>
							<th scope="col">Fecha</th>
							<th scope="col">Artista</th>
							<th scope="col">Direccion</th>
							<th scope="col">Lugar</th>
							<th scope="col">Entrada</th>
						</tr>
					</thead>
					<tbody>
						<%
						for (Show show : listaS) {
						%>
						<tr>
							<form action="abmentrada" method="post">
								<input type="hidden" name="show_id" value="<%=show.getId()%>"
									readonly>
								<td><%=show.getId()%></td>
								<td><%=show.getNombre()%></td>
								<td><%=show.getFecha()%></td>
								<td><%=show.getArtista().getNombre()%></td>
								<td><%=show.getLugar().getDireccion()%></td>
								<td><%=show.getLugar().getCiudad().getNombre()%></td>
								<td><%=show.getLugar().getNombre()%></td>
								<td><button type="submit" name="modo" value="2"
										class="btn btn-success">Ver entrada</button></td>
							</form>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>