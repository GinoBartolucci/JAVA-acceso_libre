<%@page import="java.util.LinkedList"%>
<%@page import="entities.Lugar"%>
<%@page import="entities.Ciudad"%>
<%@page import="entities.Provincia"%>
<%@page import="entities.Artista"%>
<%@page import="entities.Show"%>
<%@page import="utils.Validaciones"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="./js/lugares.js" defer></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Shows</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<%
LinkedList<Show> listaS = (LinkedList<Show>) request.getAttribute("shows");
LinkedList<Lugar> listaL = (LinkedList<Lugar>) request.getAttribute("lugares");
LinkedList<Ciudad> listaC = (LinkedList<Ciudad>) request.getAttribute("ciudades");
LinkedList<Provincia> listaP = (LinkedList<Provincia>) request.getAttribute("provincias");
LinkedList<Artista> listaA = (LinkedList<Artista>) request.getAttribute("artistas");
%>
</head>
<body>
	<div class="d-flex justify-content-between">
		<h2><a href="/JAVA-acceso_libre/dashboard" class="text-decoration-none"> Home </a></h2>
		<h2><a href="/JAVA-acceso_libre/logout" class="text-decoration-none text-danger">Logout</a></h2>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-12">
				<h1> Administrar Shows</h1>
				<hr>
				<h4>Nuevo Show</h4>
				<form id="lugar_form" class="row" action="abmshow" method="post">
					<div class="col-auto">
						<label class="col-form-label" for="nombre">Nombre: </label> <input
							id="nombre" type="text" name="nombre">
					</div>
					<div class="col-auto">
						<label class="col-form-label" for="precio">Precio: </label> <input
							id="precio" type="number" name="precio" step="0.01">
					</div>
					<div class="col-auto">
						<label class="col-form-label" for="fecha">Fecha: </label> <input
							id="fecha" type="datetime-local" name="fecha">
					</div>
					<div class="col-auto">
						<label class="col-form-label" for="artista_id">Artista: </label>
						<select id="artista_id" name="artista_id">
							<option value=""></option>
							<%
							for (Artista art : listaA) {
							%>
							<option value="<%=art.getId()%>"><%=art.getNombre()%></option>
							<%
							}
							%>
						</select>
					</div>
					<div class="col-auto">
						<label class="col-form-label" for="provincia_id">Provincia: </label>
						<select id="provincia_id" name="provincia_id">
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
						<label class="col-form-label" for="lugar_id">Lugar: </label> <select
							id="lugar_id" name="lugar_id" disabled>
							<option value=""></option>
							<%
							for (Lugar lugar : listaL) {
							%>
							<option value="<%=lugar.getId()%>"
								ciudad_id="<%=lugar.getCiudad().getId()%>"><%=lugar.getNombre()%></option>
							<%
							}
							%>
						</select>
					</div>
					<div class="col-auto">
						<button type="submit" name="modo" value="1"
							class="btn btn-success mx-2">Crear</button>
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
							<th scope="col">Editar</th>
							<th scope="col">Eliminar</th>
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
								<td><button type="submit" name="modo" value="3"
										class="btn btn-primary">Editar</button></td>
								<td><button type="submit" name="modo" value="2"
										class="btn btn-danger">Eliminar</button></td>
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