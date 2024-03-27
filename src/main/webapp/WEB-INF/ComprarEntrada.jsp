<%@page import="java.util.LinkedList" %>
<%@page import="entities.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="./js/comprarEntrada.js" defer></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Comprar Entrada</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	<%
		Show show = (Show) request.getAttribute("show");
		int asistente_id = (int) request.getAttribute("asistente_id");
	%>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12">
			<h1><a href="/JAVA-acceso_libre/dashboard" class="text-decoration-none"> Home</a> Comprar entrada</h1>		
			<hr>
			<h4>Datos Entrada</h4>
			 <form id="show_form" class="row" action="abmentrada" method="post">
				 <div class="col-auto">
				 	<input id="asistente_id" type="hidden" name="asistente_id" value="<%=asistente_id%>">
				 	<input id="show_id" type="hidden" name="show_id" value="<%=show.getId()%>">
				 	<label class="col-form-label" for="nombre">Nombre: </label>
				    <input id="nombre" type="text" name="nombre" placeholder="Nombre">
				    <label class="col-form-label" for="apellido">Apellido: </label>
				    <input id="apellido" type="text" name="apellido" placeholder="Apellido">
				    <label class="col-form-label" for="tipo_doc">Tipo documento:</label>
				    <select id="tipo_doc" name="tipo_doc">
							<option value="DNI">DNI</option>
							<option value="CUIL">CUIL</option>
							<option value="CUIL">CUIT</option>
					</select>
				    <input id="documento" type="number" name="documento" placeholder="nro. Documento">
				    <h3 class="col-form-label">Precio: $<%=show.getPrecio()%></h3> 
				    <h3 class="col-form-label">Fecha: <%=show.getFecha()%></h3> 
				    <h3 class="col-form-label">Artista: <%=show.getArtista().getNombre()%></h3>
				    <h3 class="col-form-label" >Ciudad: <%=show.getLugar().getCiudad().getNombre()%></h3> 
				    <h3 class="col-form-label">Dirección: <%=show.getLugar().getDireccion()%> </h3>
					<h3 class="col-form-label" for="lugar_id">Lugar: <%=show.getLugar().getNombre()%></h3> 
				</div>
				<div class="col-auto">		  
				   <button type="submit" name="modo" value="1" class="btn btn-success mt-2 mx-2">Comprar</button> 
				</div>
			</form>
			</div>
		</div>
	</div>
</body>
</html>