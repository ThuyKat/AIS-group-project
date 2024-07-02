<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Menu</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/style.css">
</head>

<body class="bg-primary-yellow">

	<nav class="d-flex align-items-center menu-nav-bar">
		<i class="fa-solid fa-bars me-3 hambuger-icon"></i>
		<p class="mb-0 fs-5 menu-nav-brand">All-In Small</p>
	</nav>
	
	<section id="categories">
		<ul class="nav category-section ms-2">
			<c:forEach items="${allCategory}" var="category">
				<li class="nav-item"><a class="nav-link" href="?action=SHOW_PRODUCT_BY_CATEGORY&categoryId=${category.id}">${category.name}</a></li>

			</c:forEach>
			
			<li class="nav-item"><a class="nav-link" href="?action=SHOW_ALL">All</a></li>
			<li class="nav-item"><a class="nav-link" href="">+</a></li>
		</ul>

	</section>

	<section class="product-section">
		<c:forEach items="${products}" var="product">
			<div class="product-container">
			<c:if test="${product.base64Image != null }">
				<img src="data:image/jpeg;base64,${product.base64Image}" alt="">
				</c:if>
				<div class="product-details">
					<div class="product-name">${product.name}</div>
					<div class="product-price">$${product.price}</div>
				</div>
				<div class="product-quantity">
					<input type="button" value="-" class="button-minus " data-field="quantity">
					 <input type="number" step="1" min="0" value="1" name="quantity" class="quantity-field text-center"> 
					 <input type="button" value="+" class="button-plus" data-field="quantity">
				</div>
			</div>
		</c:forEach>
	</section>
	<footer class="">
		<!-- <footer class="fixed-bottom"></footer> -->
		<button class="order-btn">
			<a href="/addToOrder">Add To Order</a>
		</button>
		<button class="order-btn">
			<a href="/viewOrder">View Order</a>
		</button>
		<button class="order-btn">
			<a href="?action=RESET_ORDER">Reset Order</a>
		</button>
	</footer>

</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="js/script.js"></script>

</html>