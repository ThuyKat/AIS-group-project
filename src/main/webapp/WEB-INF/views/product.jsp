
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Product</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
</head>

<body class=" bg-custom-primary ">
	<div class="container vh-100 perfect-center">
		<div class="row">
			<h1 class="text-center">Add New Product</h1>
			<form id="product-form" class="col-12 col-md-12 g-3"
				action="/product" method="post" enctype="multipart/form-data"
				novalidate>
				<div class="form-floating mb-3 custom-shadow">
					<input type="text" class="form-control" id="floatingInputDisabled"
						name="productId" disabled> <label
						for="floatingInputDisabled">Id</label>
				</div>

				<div class="form-floating mb-3 custom-shadow">
					<input type="text" class="form-control " id="floatingInput"
						name="productName"> <label for="floatingInput">Product
						Name</label>
				</div>
				<div class="form-floating mb-3 custom-shadow">
					<input type="number" class="form-control" id="floatingPrice"
						name="price"> <label for="floatingPrice">Product
						Price</label>
				</div>
				<div class="form-floating col-9 custom-shadow">
					<select class="form-select my-3" id="floatingSelect"
						aria-label="Floating label select example" name="categoryId">
						<option selected>Open this select Category</option>
						<c:forEach items="${allCategory}" var="category">
							<option value="${category.id}">${category.name}</option>
						</c:forEach>
					</select> <label for="floatingSelect">Category</label>
				</div>
				<div class="mb-3 custom-shadow">
					<input type="file" id="upload-product-image" class="form-control"
						aria-label="file example" name="imageData" required>
					<div class="invalid-feedback">Example invalid form file
						feedback</div>
				</div>
				<div class="d-flex justify-content-center gap-3">
					<button type="submit" class="btn custom-button">Save
						Product</button>
					<a id="back-to-homepage" class="btn col-6 col-md-4 custom-button"
						type="button" href="/" data-bs-toggle="modal"
						data-bs-target="#staticBackdrop">Back To Home Page</a>
				</div>
			</form>



			<!-- Modal -->
			<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
				data-bs-keyboard="false" tabindex="-1"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-header">
							<h1 class="modal-title fs-5" id="staticBackdropLabel">Are
								you sure?</h1>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">Your Product information will not be
							saved to database</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">No</button>
							<a type="button" class="btn btn-primary" href="/">Understood</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<script
		src="https://unpkg.com/just-validate@latest/dist/just-validate.production.min.js"></script>
	<script src="js/script.js"></script>
</body>

</html>
