<%@page import="java.util.LinkedList" %>
<%@page import="entities.Provincia" %>
<%@page import="entities.Ciudad" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Menu Provincias</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	<%
		Ciudad c = (Ciudad)request.getAttribute("ciudad");
		LinkedList<Provincia> listaP = (LinkedList<Provincia>)request.getAttribute("provincias");
	%>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12">
			<h1>Administrar Ciudades</h1>		
			<hr>
			<h4>Editar provincia</h4>
			 <form class="row" action="abmciudad" method="post">
				 <div class="col-auto">				 
				 	<label class="col-form-label" for="id">Id: </label>
				    <input type="text" readonly name="id" placeholder="id" value="<%=c.getId()%>">
				 	<label class="col-form-label" for="nombre">Nombre: </label>
				    <input id="nombre" type="text" name="nombre" placeholder="Nombre" value="<%=c.getNombre()%>">						    							
				</div>
				<div class="col-auto">
				 	<label class="col-form-label" for="provincia">Provincia: </label>				    
					<select id="provincia_id" name="provincia_id">
					<% for (Provincia pro : listaP){ %>
					  <option value="<%=pro.getId()%>" <%=((pro.getId() == c.getProvincia().getId()) ? "selected": "")%> ><%=pro.getNombre()%></option>
					<% } %>
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