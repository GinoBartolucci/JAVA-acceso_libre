<%@page import="java.util.LinkedList" %>
<%@page import="entities.Lugar" %>
<%@page import="entities.Ciudad" %>
<%@page import="entities.Provincia" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="./js/lugares.js" defer></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Menu Lugares</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	<%
		Lugar l = (Lugar)request.getAttribute("lugar");
		LinkedList<Ciudad> listaC = (LinkedList<Ciudad>)request.getAttribute("ciudades");
		LinkedList<Provincia> listaP = (LinkedList<Provincia>)request.getAttribute("provincias");
	%>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12">
			<h1><a href="/JAVA-acceso_libre/dashboard" class="text-decoration-none"> Home</a> Administrar Lugares</h1>		
			<hr>
			<h4>Editar lugar</h4>
			 <form id="lugar_form" class="row" action="abmlugar" method="post">
				 <div class="col-auto">
				 	<label class="col-form-label" for="id">Id: </label>
				    <input type="text" readonly name="id" placeholder="id" value="<%=l.getId()%>">
				 	<label class="col-form-label" for="nombre">Nombre: </label>
				    <input type="text" name="nombre" placeholder="Nombre" value="<%=l.getNombre()%>">
				    <label class="col-form-label" for="nombre">Dirección: </label>
				    <input type="text" name="direccion" placeholder="Dirección" value="<%=l.getDireccion()%>">
				    <label class="col-form-label" for="nombre">Capacidad: </label>
				    <input type="text" name="capacidad" type="number" placeholder="Capacidad" value="<%=l.getCapacidad()%>">
				    <label class="col-form-label" for="provincia">Provincia: </label>				    
					<select id="provincia_id" name="provincia_id">
					<option value="<%=l.getCiudad().getProvincia().getId()%>"> <%=l.getCiudad().getProvincia().getNombre()%> </option>
					<% for (Provincia pro : listaP){ %>
					  <option value="<%=pro.getId()%>"><%=pro.getNombre()%></option>
					<% } %>
					</select> 
					<select id="ciudad_id" name="ciudad_id" disabled>
					<option value="<%=l.getCiudad().getId()%>"> <%=l.getCiudad().getNombre()%> </option>
					<% 
					for (Ciudad ciudad : listaC){ %>
					  <option value="<%=ciudad.getId()%>" provincia_id="<%=ciudad.getProvincia().getId()%>"><%=ciudad.getNombre()%></option>
					<% } %>
					</select> 
					<input type="hidden" id="ciudad_id_hidden" name="ciudad_id_hidden" value="<%=l.getCiudad().getId()%>">
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