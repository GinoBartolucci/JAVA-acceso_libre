<%@page import="java.util.LinkedList" %>
<%@page import="entities.Ciudad" %>
<%@page import="entities.Provincia" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="./js/ciudades.js" defer></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Menu Ciudades</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	<%
		LinkedList<Ciudad> listaC = (LinkedList<Ciudad>)request.getAttribute("ciudades");
		LinkedList<Provincia> listaP = (LinkedList<Provincia>)request.getAttribute("provincias");
	%>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12">
			<h1>Administrar Ciudades</h1>		
			<hr>
			<h4>Nueva ciudad</h4>
			 <form id="ciudad_form" class="row" action="abmciudad" method="post">
				 <div class="col-auto">
				 	<label class="col-form-label" for="nombre">Nombre: </label>
				    <input id="nombre" type="text" name="nombre" placeholder="Nombre">						    							
				</div>
				<div class="col-auto">
				 	<label class="col-form-label" for="provincia">Provincia: </label>				    
					<select id="provincia_id" name="provincia_id">
					<% for (Provincia pro : listaP){ %>
					  <option value="<%=pro.getId()%>"><%=pro.getNombre()%></option>
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
							<th scope="col">Provincia</th>
							<th scope="col">Editar</th>
							<th scope="col">Eliminar</th>
						</tr>
					</thead>
					<tbody>
						<% for (Ciudad ciu : listaC){ %>
						<tr>
							<form action="abmciudad" method="post">
								<input type="hidden" name="id" value="<%=ciu.getId()%>" ><%-- Esta en hidden y sin tabla para pasarlo como parametro --%>
								<input type="hidden" name="provincia_id" value="<%=ciu.getProvincia().getId()%>" >
								<td><%=ciu.getId()%></td>
								<td><%=ciu.getNombre()%></td>	
								<td><%=ciu.getProvincia().getNombre()%></td>						
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