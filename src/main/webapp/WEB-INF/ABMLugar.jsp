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
		LinkedList<Lugar> listaL = (LinkedList<Lugar>)request.getAttribute("lugares");
		LinkedList<Ciudad> listaC = (LinkedList<Ciudad>)request.getAttribute("ciudades");
		LinkedList<Provincia> listaP = (LinkedList<Provincia>)request.getAttribute("provincias");
	%>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12">
			<h1>Administrar Lugares</h1>		
			<hr>
			<h4>Nuevo Lugar</h4>
			 <form id="lugar_form" class="row" action="abmlugar" method="post">
				 <div class="col-auto">
				 	<label class="col-form-label" for="nombre">Nombre: </label>
				    <input id="nombre" type="text" name="nombre">						    							
				</div>
				<div class="col-auto">
				 	<label class="col-form-label" for="direccion">Direccion: </label>
				    <input id="direccion" type="text" name="direccion">						    							
				</div>
				<div class="col-auto">
				 	<label class="col-form-label" for="capacidad">Capacidad: </label>
				    <input id="capacidad" type="number" name="capacidad" >						    							
				</div>
				<div class="col-auto">
				 	<label class="col-form-label" for="provincia">Provincia: </label>				    
					<select id="provincia_id" name="provincia_id">
					<option value=""> </option>
					<% for (Provincia pro : listaP){ %>
					  <option value="<%=pro.getId()%>"><%=pro.getNombre()%></option>
					<% } %>
					</select> 					    							
				</div>
				<div class="col-auto">
				 	<label class="col-form-label" for="ciudad">Ciudad: </label>				    
					<select id="ciudad_id" name="ciudad_id" disabled>
					<option value=""> </option>
					<% 
					for (Ciudad ciudad : listaC){ %>
					  <option value="<%=ciudad.getId()%>" provincia_id="<%=ciudad.getProvincia().getId()%>"><%=ciudad.getNombre()%></option>
					<% } %>
					</select> 					    							
				</div>
				<div class="col-auto">		  
				   <button type="submit" name="modo" value="1" class="btn btn-success mx-2">Crear</button> 
				</div>
			</form> 
			<hr>
			<h4>Listado Ciudades</h4>
				<table class="table table table-striped">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Nombre</th>							
							<th scope="col">Ciudad</th>						
							<th scope="col">Direccion</th>						
							<th scope="col">Capacidad</th>
							<th scope="col">Editar</th>
							<th scope="col">Eliminar</th>
						</tr>
					</thead>
					<tbody>
						<% for (Lugar lugar : listaL){ %>
						<tr>
							<form action="abmlugar" method="post">
								<input type="hidden" name="id" value="<%=lugar.getId()%>" ><%-- Esta en hidden y sin tabla para pasarlo como parametro --%>
								<input type="hidden" name="ciudad_id" value="<%=lugar.getCiudad().getId()%>" >
								<td><%=lugar.getId()%></td>
								<td><%=lugar.getNombre()%></td>	
								<td><%=lugar.getCiudad().getNombre()%></td>
								<td><%=lugar.getDireccion()%></td>		
								<td><%=lugar.getCapacidad()%></td>					
								<td><button type="submit" name="modo" value="3" class="btn btn-primary" >Editar</button></td>
								<td><button type="submit" name="modo" value="2" class="btn btn-danger" >Eliminar</button></td>
							</form>
						</tr>				
						<% } %>		
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>