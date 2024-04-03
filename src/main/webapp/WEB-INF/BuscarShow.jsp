<%@page import="java.util.LinkedList"%>
<%@page import="entities.Lugar"%>
<%@page import="entities.Ciudad"%>
<%@page import="entities.Provincia"%>
<%@page import="entities.Artista"%>
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
LinkedList<Ciudad> listaC = (LinkedList<Ciudad>) request.getAttribute("ciudades");
LinkedList<Provincia> listaP = (LinkedList<Provincia>) request.getAttribute("provincias");

%>
</head>
<body>	
	<div class="container">
		<div class="row">
			<div class="col-12">
			<div class="d-flex justify-content-between">
				<h2><a href="/JAVA-acceso_libre/dashboard" class="text-decoration-none"> Home </a></h2>
				<h1>Shows</h1>
				<h2><a href="/JAVA-acceso_libre/logout" class="text-decoration-none text-danger">Logout</a></h2>
			</div>
				<hr>
				<form id="lugar_form" class="row" action="abmshow" method="post">
					<div class="col-auto">
						<label class="col-form-label" for="provincia_id">Provincia:
						</label> <select id="provincia_id" name="provincia_id">
							<option value=""></option>
							<%
							for (Provincia pro : listaP) {
							%>
							<option value="<%=pro.getId()%>"><%=pro.getNombre()%></option>
							<%
							}
							%>
						</select>
					</div>
					<div class="col-auto">
						<label class="col-form-label" for="ciudad_id">Ciudad: </label> <select
							id="ciudad_id" name="ciudad_id" disabled>
							<option value="" default="yes"></option>
							<%
							for (Ciudad ciudad : listaC) {
							%>
							<option value="<%=ciudad.getId()%>"
								provincia_id="<%=ciudad.getProvincia().getId()%>"><%=ciudad.getNombre()%></option>
							<%
							}
							%>
						</select>
					</div>
					<div class="col-auto">
						<button type="submit" name="modo" value="6"
							class="btn btn-success mx-2">Buscar</button>
					</div>
				</form>
				<hr>
				<h4>Listado Shows</h4>
				<table class="table table table-striped">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Nombre</th>
							<th scope="col">Fecha</th>
							<th scope="col">Precio</th>
							<th scope="col">Artista</th>
							<th scope="col">Direccion</th>
							<th scope="col">Ciudad</th>
							<th scope="col">Lugar</th>
							<th scope="col">Entrada</th>
						</tr>
					</thead>
					<tbody>
						<%
						for (Show show : listaS) {
						%>
						<tr>
							<form action="abmshow" method="post">
								<input type="hidden" name="id" value="<%=show.getId()%>"
									readonly>
								<td><%=show.getId()%></td>
								<td><%=show.getNombre()%></td>
								<td><%=show.getFecha()%></td>
								<td>$<%=show.getPrecio()%></td>
								<td><%=show.getArtista().getNombre()%></td>
								<td><%=show.getLugar().getDireccion()%></td>
								<td><%=show.getLugar().getCiudad().getNombre()%></td>
								<td><%=show.getLugar().getNombre()%></td>
								<td><button type="submit" name="modo" value="7"
										class="btn btn-success">Comprar entrada</button></td>
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