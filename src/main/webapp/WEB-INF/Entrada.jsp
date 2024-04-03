<%@page import="java.util.LinkedList"%>
<%@page import="entities.Entrada"%>
<%@page import="entities.Show"%>
<%@page import="utils.Validaciones"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Entrada</title>
    <script src="./js/qr.js" defer></script>
    <style>
        .qr-code-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
        }

        #qrImage {
            width: 200px; /* Cambia el tamaño del código QR según tu preferencia */
            height: 200px; /* Cambia el tamaño del código QR según tu preferencia */
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <%
        Entrada entrada = (Entrada) request.getAttribute("entrada");
    %>
</head>

<body>
<div class="container">
	<div class="d-flex justify-content-between">
		<h2><a href="/JAVA-acceso_libre/dashboard" class="text-decoration-none"> Home </a></h2>
		<h1>Entrada</h1>
		<h2><a href="/JAVA-acceso_libre/logout" class="text-decoration-none text-danger">Logout</a></h2>
	</div>
    <section class="h-100">
        <div class="container h-100 mt-4">
            <div class="row justify-content-sm-center h-100">
                <div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
                    <div class="card shadow-lg">
                        <div class="card-body p-5">
                            <h1 class="display-4 card-title fw-bold text-center mb-4">Entrada</h1>
                            <form class="needs-validation" novalidate="" autocomplete="off">
                            <div id="pdf">
                                <div class="mb-3">
                                    <label class="mb-2 text-muted"><%=entrada.getTipo_doc()%>: <%=entrada.getDocumento()%></label>
                                </div>
                                <div class="mb-3">
                                    <div class="mb-2 w-100 qr-code-container">
                                        <img src="" id="qrImage">
                                        <label class="text-muted"><p id="codigo"><%=entrada.getCodigo()%></p></label>
                                    </div>
                                </div>
                            </div>
							<h3 class="display-4  fw-bold text-center mb-4">
							    <a href="javascript:history.back()" class="text-decoration-none small"> Volver Atrás</a>
							</h3>
                            </form>
                        </div>
                    </div>
                    <div class="text-center mt-5 text-muted">
                        Copyright &copy; 2023 &mdash; Gino bartolucci, Joaquín Betes
                    </div>
                </div>
            </div>
        </div>
    </section>
   </div>
</body>
</html>