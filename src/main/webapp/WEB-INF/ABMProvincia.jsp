<%@page import="java.util.LinkedList" %>
<%@page import="entities.Provincia" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Menu Provincias</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	<%
		LinkedList<Provincia> lp = (LinkedList<Provincia>)request.getAttribute("provincias");
	%>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12">
			<h1>Administrar Provincias</h1>		
			<hr>
			<h4>Nueva provincia</h4>
			 <form class="row" action="abmprovincia" method="post">
				 <div class="col-auto">
				 	<label class="col-form-label" for="nombre">Nombre: </label>
				    <input type="text" name="nombre" placeholder="Nombre">						    							
				</div>
				<div class="col-auto">		  
				   <button type="submit" name="modo" value="A" class="btn btn-success mx-2">Crear</button> 
				</div>
			</form> 
			<hr>
			<h4>Listado provincias</h4>
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
						<% for (Provincia per : lp){ %>
						<tr>
							<form action="abmprovincia" method="post">
								<td><input type="text" name="id" value="<%=per.getId()%>" readonly></td>
								<td><input type="text" name="nombre" value="<%=per.getNombre()%>" readonly></td>							
								<td>
									<button type="submit" name="modo" value="M" class="btn btn-primary" >Editar</button>
								</td>
								<td>
									<button type="submit" name="modo" value="B" class="btn btn-danger" >Eliminar</button>
								</td>
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