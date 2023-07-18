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
		Provincia p = (Provincia)request.getAttribute("provincia");
	%>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12">
			<h1>Administrar Provincias</h1>		
			<hr>
			<h4>Editar provincia</h4>
			 <form class="row" action="abmprovincia" method="post">
				 <div class="col-auto">
				 	<label class="col-form-label" for="id">Id: </label>
				    <input type="text" readonly name="id" placeholder="id" value="<%=p.getId()%>">
				 	<label class="col-form-label" for="nombre">Nombre: </label>
				    <input type="text" name="nombre" placeholder="Nombre" value="<%=p.getNombre()%>">
				</div>
				<div class="col-auto">		  
				   <button type="submit" name="modo" value="MG" class="btn btn-success mx-2">Guardar</button> 
				</div>
			</form>
			</div>
		</div>
	</div>
</body>
</html>