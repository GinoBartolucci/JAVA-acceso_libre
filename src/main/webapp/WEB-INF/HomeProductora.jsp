<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="author" content="Muhamad Nauval Azhar">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<meta name="description" content="This is a login page template based on Bootstrap 5">
	<title>Acceso libre</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>

<body>
	<section class="h-100">
		<div class="container h-100 mt-4">
			<div class="row justify-content-sm-center h-100">
				<div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
					<div class="card shadow-lg">
						<div class="card-body p-5">
							<h1 class="fs-4 card-title text-center fw-bold mb-4">Administración Shows</h1>
								<div class="d-flex align-items-center">
									<form action="verShows.jsp" method="GET">
										<button type="submit" class="btn btn-primary p-3">
											Ver Shows
										</button>
									</form>
									<form action="crearShows.jsp" method="GET" class="ms-auto ">
										<button type="submit" class="btn btn-primary p-3">
											Crear Show
										</button>
									</form>
								</div>
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

	
</body>

</html>