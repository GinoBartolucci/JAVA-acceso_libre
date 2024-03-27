<%@page import="java.util.LinkedList" %>
<%@page import="entities.Artista" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="./js/artistas.js" defer></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Menu Artista</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	<%
		LinkedList<Artista> la = (LinkedList<Artista>)request.getAttribute("artistas");
	%>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12">
			<h1>Administrar Artistas</h1>		
			<hr>
			<h4>Nuevo Artista</h4>
			 <form id="artista_form" class="row" action="abmartista" method="post">
				 <div class="col-auto">
				 	<label class="col-form-label" for="nombre">Nombre: </label>
				    <input id="nombre" type="text" name="nombre" placeholder="Nombre">						    							
				</div>
				<div class="col-auto">		  
				   <button type="submit" name="modo" value="1" class="btn btn-success mx-2">Crear</button> 
				</div>
			</form> 
			<hr>
			<h4>Listado artistas</h4>
				<table class="table table table-striped">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Nombre</th>
							<th scope="col">Editar</th>
							<th scope="col">Eliminar</th>
						</tr>
					</thead>
					<tbody>
						<% for (Artista art : la){ %>
						<tr>
							<form action="abmartista" method="post">
								<input type="hidden" name="id" value="<%=art.getId()%>" readonly>
								<td><%=art.getId()%></td>							
								<td><%=art.getNombre()%></td>							
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