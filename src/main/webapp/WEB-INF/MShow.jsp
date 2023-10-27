<%@page import="java.util.LinkedList" %>
<%@page import="entities.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="./js/shows.js" defer></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Editar Show</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	<%
		Show show = (Show) request.getAttribute("show");
		LinkedList<Artista> listaA = (LinkedList<Artista>) request.getAttribute("artistas");
		LinkedList<Lugar> listaL = (LinkedList<Lugar>)request.getAttribute("lugares");
		LinkedList<Ciudad> listaC = (LinkedList<Ciudad>)request.getAttribute("ciudades");
		LinkedList<Provincia> listaP = (LinkedList<Provincia>)request.getAttribute("provincias");
	%>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12">
			<h1>Administrar Show</h1>		
			<hr>
			<h4>Editar show</h4>
			 <form id="show_form" class="row" action="abmshow" method="post">
				 <div class="col-auto">
				 	<input id="id" type="hidden" name="id" value="<%=show.getId()%>">
				 	<label class="col-form-label" for="nombre">Nombre: </label>
				    <input id="nombre" type="text" name="nombre" placeholder="Nombre" value="<%=show.getNombre()%>">
				    <label class="col-form-label" for="precio">Precio: </label> 
				    <input id="precio" type="number" name="precio" step="0.01" value="<%=show.getPrecio()%>">
				    <label class="col-form-label" for="fecha">Fecha: </label> 
				    <input id="fecha" type="datetime-local" name="fecha" value="<%=show.getFecha()%>">
				    <label class="col-form-label" for="artista_id">Artista: </label>
					<select id="artista_id" name="artista_id">
						<option value="<%=show.getArtista().getId()%>"><%=show.getArtista().getNombre()%></option>
						<%
						for (Artista art : listaA) {
						%>
						<option value="<%=art.getId()%>"><%=art.getNombre()%></option>
						<%
						}
						%>
					</select>
				    <label class="col-form-label" for="provincia_id">Provincia: </label>
					<select id="provincia_id" name="provincia_id">
						<option value="<%=show.getLugar().getCiudad().getProvincia().getId()%>" default="yes"><%=show.getLugar().getCiudad().getProvincia().getNombre()%></option>
						<%
						for (Provincia pro : listaP) {
						%>
						<option value="<%=pro.getId()%>"><%=pro.getNombre()%></option>
						<%
						}
						%>
					</select>
					<label class="col-form-label" for="ciudad_id">Ciudad: </label> 
					<select id="ciudad_id" name="ciudad_id" disabled>
						<option value="<%=show.getLugar().getCiudad().getId()%>" default="yes" provincia_id="<%=show.getLugar().getCiudad().getProvincia().getId()%>">
						 <%=show.getLugar().getCiudad().getNombre()%>
						 </option>
						<%
						for (Ciudad ciudad : listaC) {
						%>
						<option value="<%=ciudad.getId()%>" default="no" provincia_id="<%=ciudad.getProvincia().getId()%>"><%=ciudad.getNombre()%></option>
						<%
						}
						%>
					</select>
					<label class="col-form-label" for="lugar_id">Lugar: </label> 
					<select id="lugar_id" name="lugar_id" disabled>
						<option value="<%=show.getLugar().getId()%>" default="yes" ciudad_id="<%=show.getLugar().getCiudad().getId()%>"><%=show.getLugar().getNombre()%></option>
						<%
						for (Lugar lugar : listaL) {
						%>
						<option value="<%=lugar.getId()%>" default="no" ciudad_id="<%=lugar.getCiudad().getId()%>"><%=lugar.getNombre()%></option>
						<%
						}
						%>
					</select>
				</div>
				<div class="col-auto">		  
				   <button type="submit" name="modo" value="4" class="btn btn-success mx-2">Guardar</button> 
				</div>
			</form>
			</div>
		</div>
	</div>
</body>
</html>