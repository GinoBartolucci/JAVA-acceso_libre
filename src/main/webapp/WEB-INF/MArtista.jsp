<%@page import="java.util.LinkedList" %>
<%@page import="entities.Artista" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Menu Artistas</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	<%
		Artista a = (Artista)request.getAttribute("artista");
	%>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12">
			<h1><a href="/JAVA-acceso_libre/dashboard" class="text-decoration-none"> Home</a> Administrar Artistas</h1>		
			<hr>
			<h4>Editar Artista</h4>
			 <form class="row" action="abmartista" method="post">
				 <div class="col-auto">
				 	<label class="col-form-label" for="id">Id: </label>
				    <input type="text" readonly name="id" placeholder="id" value="<%=a.getId()%>">
				 	<label class="col-form-label" for="nombre">Nombre: </label>
				    <input type="text" name="nombre" placeholder="Nombre" value="<%=a.getNombre()%>">
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