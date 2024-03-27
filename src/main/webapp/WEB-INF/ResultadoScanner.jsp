<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Scanear</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
	crossorigin="anonymous">
	<%
	String resultado = (String)request.getAttribute("resultado");
	%>

</head>
<body>
	<div class="d-flex justify-content-between">
		<h2><a href="/JAVA-acceso_libre/dashboard" class="text-decoration-none"> Home </a></h2>
		<h2><a href="/JAVA-acceso_libre/logout" class="text-decoration-none text-danger">Logout</a></h2>
	</div>
	<section class="h-100">
		<div class="container h-100 mt-4">
			<div class="row justify-content-sm-center h-100">
				<div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
					<div class="card shadow-lg">
						<div class="card-body p-6">
							<h2 class="display-4  fw-bold text-center mb-4">
								<%=resultado%>
							</h2>
						</div>
					</div>
					<div class="text-center mt-5 text-muted">Copyright &copy;
						2023 &mdash; Gino bartolucci, Joaquín Betes</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>